<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!-- Page Header -->
<div class="page-header">
    <h1 class="page-title">Eventos</h1>
    <p class="text-muted">Visualize e gerencie eventos</p>
</div>

<!-- Eventos List -->
<div class="row">
    <c:choose>
        <c:when test="${empty eventos}">
            <div class="col-12">
                <div class="card">
                    <div class="card-body text-center" style="padding: 3rem;">
                        <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" style="margin-bottom: 1rem; opacity: 0.3;">
                            <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                            <line x1="16" y1="2" x2="16" y2="6"></line>
                            <line x1="8" y1="2" x2="8" y2="6"></line>
                            <line x1="3" y1="10" x2="21" y2="10"></line>
                        </svg>
                        <h3>Nenhum evento encontrado</h3>
                        <p class="text-muted">Não há eventos cadastrados no momento.</p>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach var="evento" items="${eventos}">
                <div class="col-md-6 col-lg-4 mb-4">
                    <div class="card">
                        <div class="card-header">
                            <h3>${evento.nome}</h3>
                        </div>
                        <div class="card-body">
                            <p class="text-muted">${evento.informacoes}</p>
                            <div class="mb-2">
                                <strong>Data:</strong> ${evento.data}
                            </div>
                            <div class="mb-2">
                                <strong>Local:</strong> ${evento.local}
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
