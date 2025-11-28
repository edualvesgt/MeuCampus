<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erro - Meu Campus</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div style="min-height: 100vh; display: flex; align-items: center; justify-content: center; background-color: var(--color-gray-50);">
        <div class="card" style="max-width: 500px; width: 100%; margin: 2rem;">
            <div class="card-body" style="text-align: center; padding: 3rem;">
                <div style="font-size: 4rem; margin-bottom: 1rem;">⚠️</div>
                <h1 style="color: var(--color-error); margin-bottom: 1rem;">Ops! Algo deu errado</h1>
                <p class="text-muted" style="margin-bottom: 2rem;">
                    Ocorreu um erro ao processar sua solicitação. Por favor, tente novamente.
                </p>
                
                <c:if test="${not empty erro}">
                    <div style="background-color: var(--color-error-light); border: 1px solid var(--color-error); border-radius: var(--border-radius); padding: 1rem; margin-bottom: 2rem; text-align: left;">
                        <strong style="color: var(--color-error);">Detalhes do erro:</strong>
                        <p style="margin: 0.5rem 0 0 0; font-size: 0.875rem; color: var(--color-gray-700);">
                            ${erro}
                        </p>
                    </div>
                </c:if>
                
                <div style="display: flex; gap: 1rem; justify-content: center;">
                    <a href="${pageContext.request.contextPath}/aluno/dashboard" class="btn btn-primary">
                        Voltar ao Dashboard
                    </a>
                    <a href="${pageContext.request.contextPath}/login" class="btn btn-outline">
                        Voltar ao Login
                    </a>
                </div>
                
                <p class="text-muted" style="margin-top: 2rem; font-size: 0.875rem;">
                    Se o problema persistir, entre em contato com o suporte.
                </p>
            </div>
        </div>
    </div>
</body>
</html>
