<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - Meu Campus</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <!-- ========== INÍCIO: LAYOUT ALUNO ========== -->
    <div class="main-wrapper">
        
        <!-- Sidebar -->
        <aside class="sidebar" id="sidebar">
            <div class="sidebar-header">
                <div class="sidebar-logo">MEU CAMPUS</div>
                <button class="sidebar-toggle" id="sidebarToggle">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <line x1="3" y1="12" x2="21" y2="12"></line>
                        <line x1="3" y1="6" x2="21" y2="6"></line>
                        <line x1="3" y1="18" x2="21" y2="18"></line>
                    </svg>
                </button>
            </div>

            <nav>
                <ul class="sidebar-nav">
                    <li class="sidebar-nav-item">
                        <a href="${pageContext.request.contextPath}/aluno/dashboard" class="sidebar-nav-link ${pageActive == 'dashboard' ? 'active' : ''}">
                            <svg class="sidebar-nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <rect x="3" y="3" width="7" height="7"></rect>
                                <rect x="14" y="3" width="7" height="7"></rect>
                                <rect x="14" y="14" width="7" height="7"></rect>
                                <rect x="3" y="14" width="7" height="7"></rect>
                            </svg>
                            <span>Dashboard</span>
                        </a>
                    </li>

                    <li class="sidebar-nav-item">
                        <a href="${pageContext.request.contextPath}/aluno/turmas" class="sidebar-nav-link ${pageActive == 'turmas' ? 'active' : ''}">
                            <svg class="sidebar-nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                                <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
                            </svg>
                            <span>Minhas Turmas</span>
                        </a>
                    </li>

                    <li class="sidebar-nav-item">
                        <a href="${pageContext.request.contextPath}/aluno/eventos" class="sidebar-nav-link ${pageActive == 'eventos' ? 'active' : ''}">
                            <svg class="sidebar-nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                                <line x1="16" y1="2" x2="16" y2="6"></line>
                                <line x1="8" y1="2" x2="8" y2="6"></line>
                                <line x1="3" y1="10" x2="21" y2="10"></line>
                            </svg>
                            <span>Eventos</span>
                        </a>
                    </li>

                    <li class="sidebar-nav-item">
                        <a href="${pageContext.request.contextPath}/aluno/notificacoes" class="sidebar-nav-link ${pageActive == 'notificacoes' ? 'active' : ''}">
                            <svg class="sidebar-nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
                                <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
                            </svg>
                            <span>Notificações</span>
                            <c:if test="${notificacoesNaoLidas > 0}">
                                <span class="badge badge-danger" style="margin-left: auto;">${notificacoesNaoLidas}</span>
                            </c:if>
                        </a>
                    </li>

                    <li class="sidebar-nav-item">
                        <a href="${pageContext.request.contextPath}/aluno/perfil" class="sidebar-nav-link ${pageActive == 'perfil' ? 'active' : ''}">
                            <svg class="sidebar-nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                                <circle cx="12" cy="7" r="4"></circle>
                            </svg>
                            <span>Meu Perfil</span>
                        </a>
                    </li>

                    <li class="sidebar-nav-item" style="margin-top: auto; padding-top: 2rem; border-top: 1px solid var(--color-gray-200);">
                        <a href="${pageContext.request.contextPath}/logout" class="sidebar-nav-link">
                            <svg class="sidebar-nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
                                <polyline points="16 17 21 12 16 7"></polyline>
                                <line x1="21" y1="12" x2="9" y2="12"></line>
                            </svg>
                            <span>Sair</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </aside>

        <!-- Main Content -->
        <main class="main-content" id="mainContent">
            
            <!-- Topbar -->
            <div class="topbar">
                <div class="topbar-left">
                    <button class="sidebar-toggle" onclick="toggleSidebar()">
                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                            <line x1="3" y1="12" x2="21" y2="12"></line>
                            <line x1="3" y1="6" x2="21" y2="6"></line>
                            <line x1="3" y1="18" x2="21" y2="18"></line>
                        </svg>
                    </button>
                </div>

                <div class="topbar-right">
                    <!-- Notificações -->
                    <button class="topbar-user" style="position: relative;">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                            <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
                            <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
                        </svg>
                        <c:if test="${notificacoesNaoLidas > 0}">
                            <span style="position: absolute; top: 0.25rem; right: 0.25rem; width: 8px; height: 8px; background-color: var(--color-error); border-radius: 50%;"></span>
                        </c:if>
                    </button>

                    <!-- User Menu -->
                    <div class="topbar-user">
                        <div class="topbar-avatar">${alunoInicial}</div>
                        <span>${alunoNome}</span>
                        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                            <polyline points="6 9 12 15 18 9"></polyline>
                        </svg>
                    </div>
                </div>
            </div>

            <!-- Page Content -->
            <div class="container-fluid" style="padding: 2rem;">
                <jsp:include page="${contentPage}"/>
            </div>
        </main>
    </div>
    <!-- ========== FIM: LAYOUT ALUNO ========== -->

    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById('sidebar');
            sidebar.classList.toggle('open');
            sidebar.classList.toggle('closed');
        }
    </script>
</body>
</html>
