<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Page Header -->
<div class="page-header">
    <h1 class="page-title">Meu Perfil</h1>
    <p class="text-muted">Visualize e edite suas informações</p>
</div>

<!-- Perfil Info -->
<div class="row">
    <div class="col-12">
        <div class="card">
            <div class="card-header">
                <h3>Informações Pessoais</h3>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label class="form-label"><strong>Nome Completo:</strong></label>
                            <p>${usuario.nome}</p>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><strong>Email:</strong></label>
                            <p>${usuario.email}</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label class="form-label"><strong>Celular:</strong></label>
                            <p>${usuario.celular}</p>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><strong>Formação:</strong></label>
                            <p>${professor.formacao}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
