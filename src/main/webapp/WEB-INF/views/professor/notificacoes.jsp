<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!-- Page Header -->
<div class="page-header">
    <h1 class="page-title">Notificações</h1>
    <p class="text-muted">Gerencie suas notificações</p>
</div>

<!-- Notificações List -->
<div class="row">
    <div class="col-12">
        <div class="card">
            <div class="card-header">
                <h3>Todas as Notificações</h3>
            </div>
            <div class="card-body" style="padding: 0;">
                <c:choose>
                    <c:when test="${not empty notificacoes}">
                        <ul class="list-group">
                            <c:forEach var="notificacao" items="${notificacoes}">
                                <li class="list-group-item">
                                    <div style="display: flex; gap: 1rem;">
                                        <div style="width: 8px; height: 8px; border-radius: 50%; background-color: var(--color-primary); margin-top: 0.5rem; flex-shrink: 0;"></div>
                                        <div style="flex: 1;">
                                            <strong>${notificacao.titulo}</strong>
                                            <p class="text-muted mb-0" style="font-size: 0.875rem;">
                                                ${notificacao.conteudo}
                                            </p>
                                            <small class="text-muted">
                                                <fmt:formatDate value="${notificacao.data}" pattern="dd/MM/yyyy HH:mm"/>
                                            </small>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <div style="padding: 3rem; text-align: center;">
                            <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" style="margin-bottom: 1rem; opacity: 0.3;">
                                <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
                                <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
                            </svg>
                            <h3>Nenhuma notificação</h3>
                            <p class="text-muted">Você não tem notificações no momento.</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
