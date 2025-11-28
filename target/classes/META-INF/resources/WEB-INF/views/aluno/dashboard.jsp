<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!-- ========== INÍCIO: DASHBOARD ALUNO ========== -->

<!-- Page Header -->
<div class="page-header">
    <h1 class="page-title">Bem-vindo, ${alunoNome}!</h1>
    <p class="text-muted">Aqui está um resumo das suas atividades</p>
</div>

<!-- Stats Cards -->
<div class="stats-grid">
    <div class="stat-card">
        <div class="stat-icon primary">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
            </svg>
        </div>
        <div class="stat-content">
            <div class="stat-label">Turmas Matriculadas</div>
            <div class="stat-value">${totalTurmas}</div>
        </div>
    </div>

    <div class="stat-card">
        <div class="stat-icon success">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                <line x1="16" y1="2" x2="16" y2="6"></line>
                <line x1="8" y1="2" x2="8" y2="6"></line>
                <line x1="3" y1="10" x2="21" y2="10"></line>
            </svg>
        </div>
        <div class="stat-content">
            <div class="stat-label">Eventos Inscritos</div>
            <div class="stat-value">${totalEventos}</div>
        </div>
    </div>

    <div class="stat-card">
        <div class="stat-icon warning">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
                <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
            </svg>
        </div>
        <div class="stat-content">
            <div class="stat-label">Notificações</div>
            <div class="stat-value">${notificacoesNaoLidas}</div>
        </div>
    </div>
</div>

<!-- Content Grid -->
<div class="row">
    <!-- Próximas Aulas -->
    <div class="col-6">
        <div class="card">
            <div class="card-header">
                <h3>Próximas Aulas</h3>
            </div>
            <div class="card-body" style="padding: 0;">
                <ul class="list-group">
                    <c:forEach var="matricula" items="${proximasTurmas}">
                        <li class="list-group-item">
                            <div>
                                <strong>${matricula.turma.materia.nome}</strong>
                                <div class="text-muted" style="font-size: 0.875rem;">
                                    Prof. ${matricula.turma.professor.usuario.nome} • ${matricula.turma.nome}
                                </div>
                            </div>
                            <span class="badge badge-primary">Hoje</span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="card-footer">
                <a href="${pageContext.request.contextPath}/aluno/turmas" class="btn btn-outline btn-sm">Ver todas as turmas</a>
            </div>
        </div>
    </div>

    <!-- Próximos Eventos -->
    <div class="col-6">
        <div class="card">
            <div class="card-header">
                <h3>Próximos Eventos</h3>
            </div>
            <div class="card-body" style="padding: 0;">
                <ul class="list-group">
                    <c:forEach var="evento" items="${proximosEventos}">
                        <li class="list-group-item">
                            <div>
                                <strong>${evento.nome}</strong>
                                <div class="text-muted" style="font-size: 0.875rem;">
                                    ${evento.local}
                                </div>
                            </div>
                            <span class="badge badge-success">Inscrito</span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="card-footer">
                <a href="${pageContext.request.contextPath}/aluno/eventos" class="btn btn-outline btn-sm">Ver todos os eventos</a>
            </div>
        </div>
    </div>
</div>

<!-- Últimas Notificações -->
<div class="card mt-4">
    <div class="card-header">
        <h3>Últimas Notificações</h3>
    </div>
    <div class="card-body" style="padding: 0;">
        <ul class="list-group">
            <c:forEach var="notificacao" items="${ultimasNotificacoes}">
                <li class="list-group-item">
                    <div style="display: flex; gap: 1rem;">
                        <div style="width: 8px; height: 8px; border-radius: 50%; background-color: ${notificacao.status == 'Nao lida' ? 'var(--color-primary)' : 'var(--color-gray-300)'}; margin-top: 0.5rem; flex-shrink: 0;"></div>
                        <div style="flex: 1;">
                            <strong>${notificacao.titulo}</strong>
                            <p class="text-muted mb-0" style="font-size: 0.875rem;">
                                ${notificacao.conteudo}
                            </p>
                            <small class="text-muted">Há 2 horas</small>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="card-footer">
        <a href="${pageContext.request.contextPath}/aluno/notificacoes" class="btn btn-outline btn-sm">Ver todas as notificações</a>
    </div>
</div>

<!-- ========== FIM: DASHBOARD ALUNO ========== -->
