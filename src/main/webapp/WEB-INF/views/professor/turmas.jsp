<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!-- Page Header -->
<div class="page-header">
    <h1 class="page-title">Minhas Turmas</h1>
    <p class="text-muted">Gerencie suas turmas e alunos</p>
</div>

<!-- Turmas List -->
<div class="row">
    <c:choose>
        <c:when test="${empty turmas}">
            <div class="col-12">
                <div class="card">
                    <div class="card-body text-center" style="padding: 3rem;">
                        <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" style="margin-bottom: 1rem; opacity: 0.3;">
                            <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                            <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
                        </svg>
                        <h3>Nenhuma turma encontrada</h3>
                        <p class="text-muted">Você ainda não possui turmas cadastradas.</p>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach var="turma" items="${turmas}">
                <div class="col-md-6 col-lg-4 mb-4">
                    <div class="card">
                        <div class="card-header">
                            <h3>${turma.materia.nome}</h3>
                            <span class="badge badge-primary">${turma.codigo}</span>
                        </div>
                        <div class="card-body">
                            <div class="mb-3">
                                <strong>Período:</strong> ${turma.periodo}
                            </div>
                            <div class="mb-3">
                                <strong>Horário:</strong> ${turma.horario}
                            </div>
                            <div class="mb-3">
                                <strong>Sala:</strong> ${turma.sala}
                            </div>
                            <div class="mb-3">
                                <strong>Alunos:</strong> 
                                <c:choose>
                                    <c:when test="${not empty turma.matriculas}">
                                        ${turma.matriculas.size()}
                                    </c:when>
                                    <c:otherwise>0</c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="card-footer">
                            <a href="${pageContext.request.contextPath}/professor/turmas/${turma.idTurma}" class="btn btn-sm btn-primary">
                                Ver Detalhes
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
