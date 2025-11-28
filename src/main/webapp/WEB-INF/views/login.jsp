<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Meu Campus</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <!-- ========== INÍCIO: LOGIN PAGE ========== -->
    <div class="login-wrapper">
        <div class="login-card">
            <!-- Header com logo e título -->
            <div class="login-header">
                <div class="login-logo">MEU CAMPUS</div>
                <p class="login-subtitle">Sistema de Gestão Escolar</p>
            </div>

            <!-- Corpo do formulário -->
            <div class="login-body">
                <!-- Mensagem de erro -->
                <c:if test="${not empty erro}">
                    <div style="background-color: #FEE2E2; color: #991B1B; padding: 0.75rem; border-radius: 0.375rem; margin-bottom: 1rem; border-left: 4px solid #EF4444;">
                        ${erro}
                    </div>
                </c:if>
                
                <form id="formLogin" method="POST" action="${pageContext.request.contextPath}/login">
                    
                    <!-- Campo: Email -->
                    <div class="form-group">
                        <label for="email" class="form-label">E-mail</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            class="form-control"
                            placeholder="seu@email.com"
                            required
                        />
                    </div>

                    <!-- Campo: Senha -->
                    <div class="form-group">
                        <label for="senha" class="form-label">Senha</label>
                        <input
                            type="password"
                            id="senha"
                            name="senha"
                            class="form-control"
                            placeholder="••••••••"
                            required
                        />
                    </div>

                    <!-- Lembrar-me -->
                    <div class="form-check mb-3">
                        <input
                            type="checkbox"
                            id="lembrarMe"
                            name="lembrarMe"
                            class="form-check-input"
                        />
                        <label for="lembrarMe" class="form-check-label">
                            Lembrar-me
                        </label>
                    </div>

                    <!-- Botão Entrar -->
                    <button type="submit" class="btn btn-primary btn-block btn-lg">
                        Entrar
                    </button>
                </form>
            </div>

            <!-- Footer -->
            <div class="login-footer">
                <a href="${pageContext.request.contextPath}/recuperar-senha">Esqueci minha senha</a>
            </div>
        </div>
    </div>
    <!-- ========== FIM: LOGIN PAGE ========== -->
</body>
</html>
