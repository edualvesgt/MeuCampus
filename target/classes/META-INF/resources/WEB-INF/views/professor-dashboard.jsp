<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Professor - Meu Campus</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div style="min-height: 100vh; display: flex; align-items: center; justify-content: center; background: linear-gradient(135deg, var(--color-primary) 0%, var(--color-primary-dark) 100%); padding: 2rem;">
        <div style="background: white; border-radius: 0.5rem; box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1); padding: 3rem; max-width: 600px; text-align: center;">
            <h1 style="color: var(--color-primary); margin-bottom: 1rem;">Dashboard do Professor</h1>
            <p style="color: var(--color-gray-600); margin-bottom: 2rem;">
                Bem-vindo, <strong>${professorNome}</strong>!
            </p>
            <p style="color: var(--color-gray-600); margin-bottom: 2rem;">
                A área do professor está em desenvolvimento.
            </p>
            <div style="display: flex; gap: 1rem; justify-content: center;">
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">
                    Sair
                </a>
            </div>
        </div>
    </div>
</body>
</html>
