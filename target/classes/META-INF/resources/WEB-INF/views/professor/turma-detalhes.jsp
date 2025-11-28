<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!-- Page Header -->
<div class="page-header">
    <h1 class="page-title">${turma.nome}</h1>
    <p class="text-muted">Detalhes da turma</p>
</div>

<!-- Turma Info -->
<div class="row">
    <div class="col-12">
        <div class="card">
            <div class="card-header">
                <h3>Informações da Turma</h3>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>Código:</strong> ${turma.codigo}</p>
                        <p><strong>Matéria:</strong> ${turma.materia.nome}</p>
                        <p><strong>Período:</strong> ${turma.periodo}</p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Horário:</strong> ${turma.horario}</p>
                        <p><strong>Sala:</strong> ${turma.sala}</p>
                        <p><strong>Total de Alunos:</strong> 
                            <c:choose>
                                <c:when test="${not empty turma.matriculas}">
                                    ${turma.matriculas.size()}
                                </c:when>
                                <c:otherwise>0</c:otherwise>
                            </c:choose>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Alunos Matriculados -->
<div class="row mt-4">
    <div class="col-12">
        <div class="card">
            <div class="card-header">
                <h3>Alunos Matriculados</h3>
            </div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${not empty turma.matriculas}">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Matrícula</th>
                                    <th>Nome</th>
                                    <th>Email</th>
                                    <th>Situação</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="matricula" items="${turma.matriculas}">
                                    <tr>
                                        <td>${matricula.aluno.matricula}</td>
                                        <td>${matricula.aluno.usuario.nome}</td>
                                        <td>${matricula.aluno.usuario.email}</td>
                                        <td>
                                            <span class="badge badge-success">${matricula.aluno.situacao}</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p class="text-muted">Nenhum aluno matriculado nesta turma.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<!-- Botão Voltar -->
<div class="row mt-4">
    <div class="col-12">
        <a href="${pageContext.request.contextPath}/professor/turmas" class="btn btn-outline">
            ← Voltar para Minhas Turmas
        </a>
    </div>
</div>
