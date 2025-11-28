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
import com.escola.api.model.Professor;
import com.escola.api.model.Turma;
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
    private final ProfessorService professorService;
    
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
        try {
            System.out.println("DEBUG LOGIN: Tentando autenticar - Email: " + email);
            
            // Autenticar usuário
            Usuario usuario = usuarioService.autenticar(email, senha);
            
            if (usuario == null) {
                System.out.println("DEBUG LOGIN: Usuário não encontrado ou senha incorreta");
                model.addAttribute("erro", "Email ou senha inválidos");
                return "login";
            }
            
            System.out.println("DEBUG LOGIN: Usuário autenticado: " + usuario.getNome());
            System.out.println("DEBUG LOGIN: É professor? " + (usuario.getProfessor() != null));
            System.out.println("DEBUG LOGIN: É aluno? " + (usuario.getAluno() != null));
            
            // Salvar usuário na sessão
            session.setAttribute("usuarioLogado", usuario);
            
            // Verificar tipo de usuário e redirecionar
            if (usuario.getAluno() != null) {
                // É aluno
                System.out.println("DEBUG LOGIN: Redirecionando para dashboard do aluno");
                session.setAttribute("alunoLogado", usuario.getAluno());
                return "redirect:/aluno/dashboard";
            } else if (usuario.getProfessor() != null) {
                // É professor
                System.out.println("DEBUG LOGIN: Redirecionando para dashboard do professor");
                session.setAttribute("professorLogado", usuario.getProfessor());
                return "redirect:/professor/dashboard";
            } else {
                // É admin ou outro tipo
                System.out.println("DEBUG LOGIN: Redirecionando para dashboard do admin");
                return "redirect:/admin/dashboard";
            }
        } catch (Exception e) {
            System.err.println("ERRO NO LOGIN: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao processar login: " + e.getMessage());
            return "login";
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
            System.out.println("DEBUG DASHBOARD: Acessando dashboard do professor");
            
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            if (usuario == null) {
                System.out.println("DEBUG DASHBOARD: Usuário não encontrado na sessão");
                return "redirect:/login";
            }
            
            System.out.println("DEBUG DASHBOARD: Usuário na sessão: " + usuario.getNome());
            System.out.println("DEBUG DASHBOARD: Professor: " + usuario.getProfessor());
            
            // Verificar se é professor
            if (usuario.getProfessor() == null) {
                System.err.println("DEBUG DASHBOARD: Usuário não é um professor!");
                model.addAttribute("erro", "Usuário não é um professor");
                return "error";
            }
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Dashboard Professor");
            model.addAttribute("pageActive", "dashboard");
            model.addAttribute("contentPage", "professor/dashboard.jsp");
            model.addAttribute("professorNome", primeiroNome);
            model.addAttribute("professorNomeCompleto", usuario.getNome());
            model.addAttribute("professorInicial", iniciais);
            
            // Buscar estatísticas e dados com segurança
            int totalTurmas = 0;
            int totalAlunos = 0;
            int totalEventos = 0;
            
            // Buscar dados de forma segura
            try {
                // Buscar turmas do professor usando query direta
                Professor professor = professorService.buscarPorId(usuario.getProfessor().getIdProfessor());
                
                if (professor != null && professor.getTurmas() != null) {
                    totalTurmas = professor.getTurmas().size();
                    
                    // Criar lista simplificada
                    java.util.List<java.util.Map<String, String>> turmasSimples = new java.util.ArrayList<>();
                    int count = 0;
                    for (Turma t : professor.getTurmas()) {
                        if (count >= 2) break;
                        java.util.Map<String, String> tm = new java.util.HashMap<>();
                        tm.put("idTurma", t.getIdTurma().toString());
                        tm.put("nome", t.getNome());
                        tm.put("codigo", t.getCodigo() != null ? t.getCodigo() : "");
                        tm.put("horario", t.getHorario() != null ? t.getHorario() : "");
                        turmasSimples.add(tm);
                        count++;
                    }
                    model.addAttribute("minhasTurmas", turmasSimples);
                    
                    // Contar alunos
                    for (Turma t : professor.getTurmas()) {
                        if (t.getMatriculas() != null) {
                            totalAlunos += t.getMatriculas().size();
                        }
                    }
                } else {
                    model.addAttribute("minhasTurmas", java.util.Collections.emptyList());
                }
                
                // Buscar eventos
                java.util.List<Evento> eventos = eventoService.listarTodos();
                totalEventos = eventos.size();
                
                java.util.List<java.util.Map<String, String>> eventosSimples = new java.util.ArrayList<>();
                int ecount = 0;
                for (Evento e : eventos) {
                    if (ecount >= 2) break;
                    java.util.Map<String, String> em = new java.util.HashMap<>();
                    em.put("nome", e.getNome());
                    em.put("data", e.getData() != null ? e.getData().toString() : "");
                    em.put("local", e.getLocal() != null ? e.getLocal() : "");
                    eventosSimples.add(em);
                    ecount++;
                }
                model.addAttribute("proximosEventos", eventosSimples);
                
            } catch (Exception e) {
                System.err.println("Erro ao buscar dados: " + e.getMessage());
                e.printStackTrace();
                model.addAttribute("minhasTurmas", java.util.Collections.emptyList());
                model.addAttribute("proximosEventos", java.util.Collections.emptyList());
            }
            
            model.addAttribute("totalTurmas", totalTurmas);
            model.addAttribute("totalAlunos", totalAlunos);
            model.addAttribute("totalEventos", totalEventos);
            
            return "layout-professor";
            
        } catch (Exception e) {
            System.err.println("Erro no dashboard do professor: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao carregar dashboard: " + e.getMessage());
            return "error";
        }
    }
    
    // ========== PROFESSOR - DETALHES DA TURMA ==========
    @GetMapping("/professor/turmas/{idTurma}")
    public String professorTurmaDetalhes(@PathVariable UUID idTurma, HttpSession session, Model model) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            if (usuario == null || usuario.getProfessor() == null) {
                return "redirect:/login";
            }
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Detalhes da Turma");
            model.addAttribute("pageActive", "turmas");
            model.addAttribute("contentPage", "professor/turma-detalhes.jsp");
            model.addAttribute("professorNome", primeiroNome);
            model.addAttribute("professorNomeCompleto", usuario.getNome());
            model.addAttribute("professorInicial", iniciais);
            
            // Buscar turma
            try {
                Turma turma = turmaService.buscarPorId(idTurma);
                model.addAttribute("turma", turma);
            } catch (Exception e) {
                System.err.println("Erro ao carregar turma: " + e.getMessage());
                e.printStackTrace();
                return "redirect:/professor/turmas";
            }
            
            return "layout-professor";
            
        } catch (Exception e) {
            System.err.println("Erro na página de detalhes da turma: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao carregar detalhes da turma: " + e.getMessage());
            return "error";
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
            
            // Buscar turmas do professor do banco
            try {
                Professor professor = professorService.buscarPorId(usuario.getProfessor().getIdProfessor());
                
                if (professor != null && professor.getTurmas() != null) {
                    model.addAttribute("turmas", professor.getTurmas());
                } else {
                    model.addAttribute("turmas", java.util.Collections.emptyList());
                }
            } catch (Exception e) {
                System.err.println("Erro ao carregar turmas: " + e.getMessage());
                e.printStackTrace();
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
    
    // ========== PROFESSOR - NOTIFICAÇÕES ==========
    @GetMapping("/professor/notificacoes")
    public String professorNotificacoes(HttpSession session, Model model) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            if (usuario == null || usuario.getProfessor() == null) {
                return "redirect:/login";
            }
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Notificações");
            model.addAttribute("pageActive", "notificacoes");
            model.addAttribute("contentPage", "professor/notificacoes.jsp");
            model.addAttribute("professorNome", primeiroNome);
            model.addAttribute("professorNomeCompleto", usuario.getNome());
            model.addAttribute("professorInicial", iniciais);
            
            // Buscar notificações
            try {
                model.addAttribute("notificacoes", notificacaoService.listarTodos());
            } catch (Exception e) {
                System.err.println("Erro ao carregar notificações: " + e.getMessage());
                model.addAttribute("notificacoes", java.util.Collections.emptyList());
            }
            
            return "layout-professor";
            
        } catch (Exception e) {
            System.err.println("Erro na página de notificações: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao carregar notificações: " + e.getMessage());
            return "error";
        }
    }
    
    // ========== PROFESSOR - PERFIL ==========
    @GetMapping("/professor/perfil")
    public String professorPerfil(HttpSession session, Model model) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            if (usuario == null || usuario.getProfessor() == null) {
                return "redirect:/login";
            }
            
            String primeiroNome = usuario.getNome().split(" ")[0];
            String iniciais = usuario.getNome().substring(0, Math.min(2, usuario.getNome().length())).toUpperCase();
            
            model.addAttribute("pageTitle", "Meu Perfil");
            model.addAttribute("pageActive", "perfil");
            model.addAttribute("contentPage", "professor/perfil.jsp");
            model.addAttribute("professorNome", primeiroNome);
            model.addAttribute("professorNomeCompleto", usuario.getNome());
            model.addAttribute("professorInicial", iniciais);
            
            model.addAttribute("professor", usuario.getProfessor());
            model.addAttribute("usuario", usuario);
            
            return "layout-professor";
            
        } catch (Exception e) {
            System.err.println("Erro na página de perfil: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao carregar perfil: " + e.getMessage());
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
