<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!-- ========== INÍCIO: PERFIL DO ALUNO ========== -->

<!-- Page Header -->
<div class="page-header">
    <h1 class="page-title">Meu Perfil</h1>
    <p class="text-muted">Informações da sua conta</p>
</div>

<!-- Perfil -->
<div class="row">
    <!-- Informações Pessoais -->
    <div class="col-6">
        <div class="card">
            <div class="card-header">
                <h3>Informações Pessoais</h3>
            </div>
            <div class="card-body">
                <div class="form-group">
                    <label class="form-label">Nome Completo</label>
                    <input type="text" class="form-control" value="${usuario.nome}" readonly>
                </div>
                
                <div class="form-group">
                    <label class="form-label">E-mail</label>
                    <input type="email" class="form-control" value="${usuario.email}" readonly>
                </div>
                
                <div class="form-group">
                    <label class="form-label">Celular</label>
                    <input type="text" class="form-control" value="${usuario.celular}" readonly>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Informações Acadêmicas -->
    <div class="col-6">
        <div class="card">
            <div class="card-header">
                <h3>Informações Acadêmicas</h3>
            </div>
            <div class="card-body">
                <div class="form-group">
                    <label class="form-label">Matrícula</label>
                    <input type="text" class="form-control" value="${aluno.matricula}" readonly>
                </div>
                
                <div class="form-group">
                    <label class="form-label">Data de Ingresso</label>
                    <input type="text" class="form-control" value="<fmt:formatDate value='${aluno.dataIngresso}' pattern='dd/MM/yyyy'/>" readonly>
                </div>
                
                <div class="form-group">
                    <label class="form-label">Situação</label>
                    <div>
                        <c:choose>
                            <c:when test="${aluno.situacao == 'Ativo'}">
                                <span class="badge badge-success">${aluno.situacao}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-secondary">${aluno.situacao}</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Estatísticas -->
<div class="card mt-4">
    <div class="card-header">
        <h3>Estatísticas</h3>
    </div>
    <div class="card-body">
        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-icon primary">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                        <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
                    </svg>
                </div>
                <div class="stat-content">
                    <div class="stat-label">Turmas Matriculadas</div>
                    <div class="stat-value">${aluno.matriculas != null ? aluno.matriculas.size() : 0}</div>
                </div>
            </div>
            
            <div class="stat-card">
                <div class="stat-icon success">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                        <line x1="16" y1="2" x2="16" y2="6"></line>
                        <line x1="8" y1="2" x2="8" y2="6"></line>
                        <line x1="3" y1="10" x2="21" y2="10"></line>
                    </svg>
                </div>
                <div class="stat-content">
                    <div class="stat-label">Eventos Inscritos</div>
                    <div class="stat-value">${usuario.inscricoesEvento != null ? usuario.inscricoesEvento.size() : 0}</div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ========== FIM: PERFIL DO ALUNO ========== -->
