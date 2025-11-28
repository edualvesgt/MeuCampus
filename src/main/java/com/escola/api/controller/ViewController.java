package com.escola.api.controller;

/**
 * @author Anthony
 * @author Eduardo
 * @author Gustavo Gabriel Souza
 * @author Matheus Pegoraro
 * @author Mateus Matos
 */

import com.escola.api.model.Aluno;
import com.escola.api.model.Evento;
import com.escola.api.model.InscricaoEvento;
import com.escola.api.model.Usuario;
import com.escola.api.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ViewController {
    
    private final AlunoService alunoService;
    private final UsuarioService usuarioService;
    private final TurmaService turmaService;
    private final MatriculaService matriculaService;
    private final EventoService eventoService;
    private final NotificacaoService notificacaoService;
    private final InscricaoEventoService inscricaoEventoService;
    
    // ========== LOGIN ==========
    @GetMapping("/")
    public String home() {
        return "login";
    }
    
    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        // Se já estiver logado, redireciona
        if (session.getAttribute("usuarioLogado") != null) {
            return "redirect:/aluno/dashboard";
        }
        return "login";
    }
    
    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email, @RequestParam String senha, 
                             HttpSession session, Model model) {
        // Autenticar usuário
        Usuario usuario = usuarioService.autenticar(email, senha);
        
        if (usuario == null) {
            model.addAttribute("erro", "Email ou senha inválidos");
            return "login";
        }
        
        // Salvar usuário na sessão
        session.setAttribute("usuarioLogado", usuario);
        
        // Verificar tipo de usuário e redirecionar
        if (usuario.getAluno() != null) {
            // É aluno
            session.setAttribute("alunoLogado", usuario.getAluno());
            return "redirect:/aluno/dashboard";
        } else if (usuario.getProfessor() != null) {
            // É professor
            session.setAttribute("professorLogado", usuario.getProfessor());
            return "redirect:/professor/dashboard";
        } else {
            // É admin ou outro tipo
            return "redirect:/admin/dashboard";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    // ========== ADMIN - DASHBOARD ==========
    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("usuarioNome", usuario.getNome());
        
        return "admin-dashboard";
    }
    
    // ========== ALUNO - DASHBOARD ==========
    @GetMapping("/aluno/dashboard")
    public String alunoDashboard(HttpSession session, Model model) {
        try {
            Aluno alunoSessao = (Aluno) session.getAttribute("alunoLogado");
            if (alunoSessao == null) {
                return "redirect:/login";
            }
            
            // Buscar aluno novamente do banco para carregar as relações
            Aluno aluno = alunoService.buscarPorId(alunoSessao.getIdAluno());
            if (aluno == null || aluno.getUsuario() == null) {
                session.invalidate();
                return "redirect:/login";
            }
            
            Usuario usuario = aluno.getUsuario();
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Dashboard");
            model.addAttribute("pageActive", "dashboard");
            model.addAttribute("contentPage", "aluno/dashboard.jsp");
            model.addAttribute("alunoNome", primeiroNome);
            model.addAttribute("alunoNomeCompleto", usuario.getNome());
            model.addAttribute("alunoInicial", iniciais);
            
            // Buscar dados do banco com tratamento de erro
            try {
                int totalTurmas = aluno.getMatriculas() != null ? aluno.getMatriculas().size() : 0;
                model.addAttribute("totalTurmas", totalTurmas);
                model.addAttribute("proximasTurmas", aluno.getMatriculas() != null ? aluno.getMatriculas() : java.util.Collections.emptyList());
            } catch (Exception e) {
                System.err.println("Erro ao carregar matrículas: " + e.getMessage());
                model.addAttribute("totalTurmas", 0);
                model.addAttribute("proximasTurmas", java.util.Collections.emptyList());
            }
            
            model.addAttribute("totalEventos", 0);
            model.addAttribute("notificacoesNaoLidas", 0);
            
            try {
                model.addAttribute("proximosEventos", eventoService.listarTodos());
            } catch (Exception e) {
                System.err.println("Erro ao carregar eventos: " + e.getMessage());
                model.addAttribute("proximosEventos", java.util.Collections.emptyList());
            }
            
            try {
                model.addAttribute("ultimasNotificacoes", notificacaoService.listarTodos());
            } catch (Exception e) {
                System.err.println("Erro ao carregar notificações: " + e.getMessage());
                model.addAttribute("ultimasNotificacoes", java.util.Collections.emptyList());
            }
            
            return "layout-aluno";
            
        } catch (Exception e) {
            System.err.println("Erro no dashboard: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao carregar dashboard: " + e.getMessage());
            return "error";
        }
    }
    
    // ========== ALUNO - TURMAS ==========
    @GetMapping("/aluno/turmas")
    public String alunoTurmas(HttpSession session, Model model) {
        try {
            Aluno alunoSessao = (Aluno) session.getAttribute("alunoLogado");
            if (alunoSessao == null) {
                return "redirect:/login";
            }
            
            // Buscar aluno novamente do banco
            Aluno aluno = alunoService.buscarPorId(alunoSessao.getIdAluno());
            if (aluno == null || aluno.getUsuario() == null) {
                session.invalidate();
                return "redirect:/login";
            }
            
            Usuario usuario = aluno.getUsuario();
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Minhas Turmas");
            model.addAttribute("pageActive", "turmas");
            model.addAttribute("contentPage", "aluno/turmas.jsp");
            model.addAttribute("alunoNome", primeiroNome);
            model.addAttribute("alunoNomeCompleto", usuario.getNome());
            model.addAttribute("alunoInicial", iniciais);
            model.addAttribute("notificacoesNaoLidas", 0);
            
            // Buscar matrículas do aluno
            try {
                model.addAttribute("matriculas", aluno.getMatriculas() != null ? aluno.getMatriculas() : java.util.Collections.emptyList());
            } catch (Exception e) {
                System.err.println("Erro ao carregar matrículas: " + e.getMessage());
                model.addAttribute("matriculas", java.util.Collections.emptyList());
            }
            
            return "layout-aluno";
            
        } catch (Exception e) {
            System.err.println("Erro na página de turmas: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao carregar turmas: " + e.getMessage());
            return "error";
        }
    }
    
    // ========== ALUNO - EVENTOS ==========
    @GetMapping("/aluno/eventos")
    public String alunoEventos(HttpSession session, Model model) {
        try {
            Aluno alunoSessao = (Aluno) session.getAttribute("alunoLogado");
            if (alunoSessao == null) {
                return "redirect:/login";
            }
            
            // Buscar aluno novamente do banco
            Aluno aluno = alunoService.buscarPorId(alunoSessao.getIdAluno());
            if (aluno == null || aluno.getUsuario() == null) {
                session.invalidate();
                return "redirect:/login";
            }
            
            Usuario usuario = aluno.getUsuario();
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Eventos");
            model.addAttribute("pageActive", "eventos");
            model.addAttribute("contentPage", "aluno/eventos.jsp");
            model.addAttribute("alunoNome", primeiroNome);
            model.addAttribute("alunoNomeCompleto", usuario.getNome());
            model.addAttribute("alunoInicial", iniciais);
            model.addAttribute("notificacoesNaoLidas", 0);
            
            try {
                List<Evento> eventos = eventoService.listarTodos();
                model.addAttribute("eventos", eventos);
                
                // Buscar eventos inscritos
                List<InscricaoEvento> inscricoes = inscricaoEventoService.buscarPorUsuario(usuario.getIdUsuario());
                List<UUID> eventosInscritos = inscricoes.stream()
                    .map(i -> i.getEvento().getIdEvento())
                    .toList();
                model.addAttribute("eventosInscritos", eventosInscritos);
            } catch (Exception e) {
                System.err.println("Erro ao carregar eventos: " + e.getMessage());
                model.addAttribute("eventos", java.util.Collections.emptyList());
                model.addAttribute("eventosInscritos", java.util.Collections.emptyList());
            }
            
            return "layout-aluno";
            
        } catch (Exception e) {
            System.err.println("Erro na página de eventos: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao carregar eventos: " + e.getMessage());
            return "error";
        }
    }
    
    // ========== ALUNO - NOTIFICAÇÕES ==========
    @GetMapping("/aluno/notificacoes")
    public String alunoNotificacoes(HttpSession session, Model model) {
        try {
            Aluno alunoSessao = (Aluno) session.getAttribute("alunoLogado");
            if (alunoSessao == null) {
                return "redirect:/login";
            }
            
            // Buscar aluno novamente do banco
            Aluno aluno = alunoService.buscarPorId(alunoSessao.getIdAluno());
            if (aluno == null || aluno.getUsuario() == null) {
                session.invalidate();
                return "redirect:/login";
            }
            
            Usuario usuario = aluno.getUsuario();
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Notificações");
            model.addAttribute("pageActive", "notificacoes");
            model.addAttribute("contentPage", "aluno/notificacoes.jsp");
            model.addAttribute("alunoNome", primeiroNome);
            model.addAttribute("alunoNomeCompleto", usuario.getNome());
            model.addAttribute("alunoInicial", iniciais);
            model.addAttribute("notificacoesNaoLidas", 0);
            
            try {
                model.addAttribute("notificacoes", notificacaoService.listarTodos());
            } catch (Exception e) {
                System.err.println("Erro ao carregar notificações: " + e.getMessage());
                model.addAttribute("notificacoes", java.util.Collections.emptyList());
            }
            
            return "layout-aluno";
            
        } catch (Exception e) {
            System.err.println("Erro na página de notificações: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao carregar notificações: " + e.getMessage());
            return "error";
        }
    }
    
    // ========== ALUNO - PERFIL ==========
    @GetMapping("/aluno/perfil")
    public String alunoPerfil(HttpSession session, Model model) {
        try {
            Aluno alunoSessao = (Aluno) session.getAttribute("alunoLogado");
            if (alunoSessao == null) {
                return "redirect:/login";
            }
            
            // Buscar aluno novamente do banco
            Aluno aluno = alunoService.buscarPorId(alunoSessao.getIdAluno());
            if (aluno == null || aluno.getUsuario() == null) {
                session.invalidate();
                return "redirect:/login";
            }
            
            Usuario usuario = aluno.getUsuario();
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Meu Perfil");
            model.addAttribute("pageActive", "perfil");
            model.addAttribute("contentPage", "aluno/perfil.jsp");
            model.addAttribute("alunoNome", primeiroNome);
            model.addAttribute("alunoNomeCompleto", usuario.getNome());
            model.addAttribute("alunoInicial", iniciais);
            model.addAttribute("notificacoesNaoLidas", 0);
            
            model.addAttribute("aluno", aluno);
            model.addAttribute("usuario", usuario);
            
            return "layout-aluno";
            
        } catch (Exception e) {
            System.err.println("Erro na página de perfil: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao carregar perfil: " + e.getMessage());
            return "error";
        }
    }
    
    // ========== ALUNO - INSCRIÇÃO EM EVENTO ==========
    @PostMapping("/aluno/eventos/{idEvento}/inscrever")
    public String inscreverEvento(@PathVariable UUID idEvento, HttpSession session, Model model) {
        try {
            Aluno alunoSessao = (Aluno) session.getAttribute("alunoLogado");
            if (alunoSessao == null) {
                return "redirect:/login";
            }
            
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            
            // Verificar se já está inscrito
            if (inscricaoEventoService.jaInscrito(usuario.getIdUsuario(), idEvento)) {
                return "redirect:/aluno/eventos?erro=ja_inscrito";
            }
            
            // Buscar evento
            Evento evento = eventoService.buscarPorId(idEvento);
            
            // Criar inscrição
            InscricaoEvento inscricao = new InscricaoEvento();
            inscricao.setUsuario(usuario);
            inscricao.setEvento(evento);
            inscricaoEventoService.criar(inscricao);
            
            return "redirect:/aluno/eventos?sucesso=inscrito";
            
        } catch (Exception e) {
            System.err.println("Erro ao inscrever em evento: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/aluno/eventos?erro=inscricao";
        }
    }
    
    // ========== PROFESSOR - DASHBOARD ==========
    @GetMapping("/professor/dashboard")
    public String professorDashboardView(HttpSession session, Model model) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            if (usuario == null || usuario.getProfessor() == null) {
                return "redirect:/login";
            }
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Dashboard Professor");
            model.addAttribute("pageActive", "dashboard");
            model.addAttribute("contentPage", "professor/dashboard.jsp");
            model.addAttribute("professorNome", primeiroNome);
            model.addAttribute("professorNomeCompleto", usuario.getNome());
            model.addAttribute("professorInicial", iniciais);
            
            // Buscar estatísticas
            try {
                int totalTurmas = usuario.getProfessor().getTurmas() != null ? usuario.getProfessor().getTurmas().size() : 0;
                int totalAlunos = usuario.getProfessor().getTurmas() != null ? 
                    usuario.getProfessor().getTurmas().stream()
                        .mapToInt(t -> t.getMatriculas() != null ? t.getMatriculas().size() : 0)
                        .sum() : 0;
                
                model.addAttribute("totalTurmas", totalTurmas);
                model.addAttribute("totalAlunos", totalAlunos);
                model.addAttribute("totalEventos", eventoService.listarTodos().size());
            } catch (Exception e) {
                model.addAttribute("totalTurmas", 0);
                model.addAttribute("totalAlunos", 0);
                model.addAttribute("totalEventos", 0);
            }
            
            return "layout-professor";
            
        } catch (Exception e) {
            System.err.println("Erro no dashboard do professor: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/login";
        }
    }
    
    // ========== PROFESSOR - TURMAS ==========
    @GetMapping("/professor/turmas")
    public String professorTurmas(HttpSession session, Model model) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            if (usuario == null || usuario.getProfessor() == null) {
                return "redirect:/login";
            }
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Minhas Turmas");
            model.addAttribute("pageActive", "turmas");
            model.addAttribute("contentPage", "professor/turmas.jsp");
            model.addAttribute("professorNome", primeiroNome);
            model.addAttribute("professorNomeCompleto", usuario.getNome());
            model.addAttribute("professorInicial", iniciais);
            
            // Buscar turmas do professor
            try {
                model.addAttribute("turmas", usuario.getProfessor().getTurmas() != null ? 
                    usuario.getProfessor().getTurmas() : java.util.Collections.emptyList());
            } catch (Exception e) {
                System.err.println("Erro ao carregar turmas: " + e.getMessage());
                model.addAttribute("turmas", java.util.Collections.emptyList());
            }
            
            return "layout-professor";
            
        } catch (Exception e) {
            System.err.println("Erro na página de turmas: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao carregar turmas: " + e.getMessage());
            return "error";
        }
    }
    
    // ========== PROFESSOR - EVENTOS ==========
    @GetMapping("/professor/eventos")
    public String professorEventos(HttpSession session, Model model) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            if (usuario == null || usuario.getProfessor() == null) {
                return "redirect:/login";
            }
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Eventos");
            model.addAttribute("pageActive", "eventos");
            model.addAttribute("contentPage", "professor/eventos.jsp");
            model.addAttribute("professorNome", primeiroNome);
            model.addAttribute("professorNomeCompleto", usuario.getNome());
            model.addAttribute("professorInicial", iniciais);
            
            // Buscar eventos
            try {
                model.addAttribute("eventos", eventoService.listarTodos());
            } catch (Exception e) {
                System.err.println("Erro ao carregar eventos: " + e.getMessage());
                model.addAttribute("eventos", java.util.Collections.emptyList());
            }
            
            return "layout-professor";
            
        } catch (Exception e) {
            System.err.println("Erro na página de eventos: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao carregar eventos: " + e.getMessage());
            return "error";
        }
    }
    
    // ========== ADMIN - LISTA DE ALUNOS (antiga rota) ==========
    @GetMapping("/alunos")
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", alunoService.listarTodos());
        return "alunos";
    }
}
