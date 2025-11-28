<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!-- ========== INÍCIO: NOTIFICAÇÕES DO ALUNO ========== -->

<!-- Page Header -->
<div class="page-header">
    <h1 class="page-title">Notificações</h1>
    <p class="text-muted">Suas notificações e avisos</p>
</div>

<!-- Notificações -->
<div class="card">
    <div class="card-body" style="padding: 0;">
        <ul class="list-group">
            <c:forEach var="notificacao" items="${notificacoes}">
                <li class="list-group-item">
                    <div style="display: flex; gap: 1rem;">
                        <div style="width: 8px; height: 8px; border-radius: 50%; background-color: ${notificacao.status == 'Nao lida' ? 'var(--color-primary)' : 'var(--color-gray-300)'}; margin-top: 0.5rem; flex-shrink: 0;"></div>
                        <div style="flex: 1;">
                            <strong>${notificacao.titulo}</strong>
                            <p class="text-muted mb-0" style="font-size: 0.875rem;">
                                ${notificacao.conteudo}
                            </p>
                            <small class="text-muted">
                                ${notificacao.data}
                            </small>
                        </div>
                        <c:if test="${notificacao.status == 'Nao lida'}">
                            <span class="badge badge-primary">Nova</span>
                        </c:if>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<!-- Empty State -->
<c:if test="${empty notificacoes}">
    <div class="card">
        <div class="card-body">
            <div class="empty-state">
                <div class="empty-state-icon">
                    <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
                        <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
                    </svg>
                </div>
                <h3 class="empty-state-title">Nenhuma notificação</h3>
                <p class="empty-state-text">Você não tem notificações no momento.</p>
            </div>
        </div>
    </div>
</c:if>

<!-- ========== FIM: NOTIFICAÇÕES DO ALUNO ========== -->
