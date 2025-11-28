<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!-- ========== INÍCIO: DASHBOARD PROFESSOR ========== -->

<!-- Page Header -->
<div class="page-header">
    <h1 class="page-title">Bem-vindo, ${professorNome}!</h1>
    <p class="text-muted">Painel de controle do professor</p>
</div>

<!-- Stats Cards -->
<div class="stats-grid">
    <div class="stat-card">
        <div class="stat-icon primary">📚</div>
        <div class="stat-content">
            <div class="stat-label">Turmas Lecionadas</div>
            <div class="stat-value">${totalTurmas}</div>
        </div>
    </div>

    <div class="stat-card">
        <div class="stat-icon success">📅</div>
        <div class="stat-content">
            <div class="stat-label">Eventos Criados</div>
            <div class="stat-value">${totalEventos}</div>
        </div>
    </div>

    <div class="stat-card">
        <div class="stat-icon warning">📋</div>
        <div class="stat-content">
            <div class="stat-label">Reservas Ativas</div>
            <div class="stat-value">5</div>
        </div>
    </div>
</div>

<!-- Minhas Turmas e Próximos Eventos -->
<div class="row">
    <!-- Minhas Turmas -->
    <div class="col-6">
        <div class="card">
            <div class="card-header">
                <h3>Minhas Turmas</h3>
            </div>
            <div class="card-body" style="padding: 0;">
                <c:choose>
                    <c:when test="${not empty minhasTurmas}">
                        <ul class="list-group">
                            <c:forEach var="turma" items="${minhasTurmas}">
                                <li class="list-group-item">
                                    <div>
                                        <strong>${turma.nome}</strong>
                                        <div class="text-muted" style="font-size: 0.875rem;">
                                            ${turma.codigo}
                                            <c:if test="${not empty turma.horario}"> • ${turma.horario}</c:if>
                                        </div>
                                    </div>
                                    <a href="${pageContext.request.contextPath}/professor/turmas/${turma.idTurma}" class="btn btn-outline btn-sm">Gerenciar</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <div style="padding: 2rem; text-align: center;">
                            <p class="text-muted">Nenhuma turma encontrada</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="card-footer">
                <a href="${pageContext.request.contextPath}/professor/turmas" class="btn btn-outline btn-sm">Ver todas</a>
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
                <c:choose>
                    <c:when test="${not empty proximosEventos}">
                        <ul class="list-group">
                            <c:forEach var="evento" items="${proximosEventos}">
                                <li class="list-group-item">
                                    <div>
                                        <strong>${evento.nome}</strong>
                                        <div class="text-muted" style="font-size: 0.875rem;">
                                            ${evento.data}
                                        </div>
                                    </div>
                                    <span class="badge badge-warning">Evento</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <div style="padding: 2rem; text-align: center;">
                            <p class="text-muted">Nenhum evento próximo</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="card-footer">
                <a href="${pageContext.request.contextPath}/professor/eventos" class="btn btn-outline btn-sm">Ver todos</a>
            </div>
        </div>
    </div>
</div>

<!-- ========== FIM: DASHBOARD PROFESSOR ========== -->
