<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Alunos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Alunos Matriculados</h1>
    
    <table>
        <thead>
            <tr>
                <th>Nome</th>
                <th>Matrícula</th>
                <th>Data Ingresso</th>
                <th>Situação</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="aluno" items="${alunos}">
                <tr>
                    <td>${aluno.usuario.nome}</td>
                    <td>${aluno.matricula}</td>
                    <td>${aluno.dataIngresso}</td>
                    <td>${aluno.situacao}</td>
                    <td>
                        <button onclick="editarAluno('${aluno.idAluno}')">Editar</button>
                        <button onclick="deletarAluno('${aluno.idAluno}')">Deletar</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <script src="${pageContext.request.contextPath}/js/alunos.js"></script>
</body>
</html>
