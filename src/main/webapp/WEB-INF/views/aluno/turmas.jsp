<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!-- ========== INÍCIO: TURMAS DO ALUNO ========== -->

<!-- Page Header -->
<div class="page-header">
    <h1 class="page-title">Minhas Turmas</h1>
    <p class="text-muted">Turmas em que você está matriculado</p>
</div>

<!-- Turmas Grid -->
<div class="row">
    <c:forEach var="matricula" items="${matriculas}">
        <div class="col-4">
            <div class="card">
                <div class="card-header">
                    <h3>${matricula.turma.materia.nome}</h3>
                    <span class="badge badge-primary">${matricula.turma.nome}</span>
                </div>
                <div class="card-body">
                    <div style="margin-bottom: 1rem;">
                        <div style="display: flex; align-items: center; gap: 0.5rem; margin-bottom: 0.5rem;">
                            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                                <circle cx="12" cy="7" r="4"></circle>
                            </svg>
                            <span class="text-muted" style="font-size: 0.875rem;">
                                Prof. ${matricula.turma.professor.usuario.nome}
                            </span>
                        </div>
                        <div style="display: flex; align-items: center; gap: 0.5rem;">
                            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                                <line x1="16" y1="2" x2="16" y2="6"></line>
                                <line x1="8" y1="2" x2="8" y2="6"></line>
                                <line x1="3" y1="10" x2="21" y2="10"></line>
                            </svg>
                            <span class="text-muted" style="font-size: 0.875rem;">
                                Turma ${matricula.turma.nome}
                            </span>
                        </div>
                    </div>
                    
                    <p class="text-muted" style="font-size: 0.875rem;">
                        ${matricula.turma.materia.descricao}
                    </p>
                </div>
                <div class="card-footer">
                    <a href="${pageContext.request.contextPath}/aluno/turmas/${matricula.turma.idTurma}" class="btn btn-primary btn-sm">
                        Ver Detalhes
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<!-- Empty State -->
<c:if test="${empty matriculas}">
    <div class="card">
        <div class="card-body">
            <div class="empty-state">
                <div class="empty-state-icon">
                    <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                        <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
                    </svg>
                </div>
                <h3 class="empty-state-title">Nenhuma turma encontrada</h3>
                <p class="empty-state-text">Você ainda não está matriculado em nenhuma turma.</p>
            </div>
        </div>
    </div>
</c:if>

<!-- ========== FIM: TURMAS DO ALUNO ========== -->
