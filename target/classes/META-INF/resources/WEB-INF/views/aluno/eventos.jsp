<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!-- ========== INÍCIO: EVENTOS DO ALUNO ========== -->

<!-- Page Header -->
<div class="page-header">
    <h1 class="page-title">Eventos</h1>
    <p class="text-muted">Eventos disponíveis para inscrição</p>
</div>

<!-- Eventos Grid -->
<div class="row">
    <c:forEach var="evento" items="${eventos}">
        <div class="col-4">
            <div class="card">
                <div class="card-header">
                    <h3>${evento.nome}</h3>
                </div>
                <div class="card-body">
                    <div style="margin-bottom: 1rem;">
                        <div style="display: flex; align-items: center; gap: 0.5rem; margin-bottom: 0.5rem;">
                            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                                <line x1="16" y1="2" x2="16" y2="6"></line>
                                <line x1="8" y1="2" x2="8" y2="6"></line>
                                <line x1="3" y1="10" x2="21" y2="10"></line>
                            </svg>
                            <span class="text-muted" style="font-size: 0.875rem;">
                                ${evento.data}
                            </span>
                        </div>
                        <div style="display: flex; align-items: center; gap: 0.5rem;">
                            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"></path>
                                <circle cx="12" cy="10" r="3"></circle>
                            </svg>
                            <span class="text-muted" style="font-size: 0.875rem;">
                                ${evento.local}
                            </span>
                        </div>
                    </div>
                    
                    <p class="text-muted" style="font-size: 0.875rem;">
                        ${evento.informacoes}
                    </p>
                </div>
                <div class="card-footer">
                    <c:choose>
                        <c:when test="${eventosInscritos.contains(evento.idEvento)}">
                            <span class="badge badge-success">Inscrito</span>
                        </c:when>
                        <c:otherwise>
                            <form action="${pageContext.request.contextPath}/aluno/eventos/${evento.idEvento}/inscrever" method="post" style="display: inline;">
                                <button type="submit" class="btn btn-primary btn-sm">
                                    Inscrever-se
                                </button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                    <button class="btn btn-outline btn-sm" onclick="verDetalhes(${evento.idEvento})">
                        Ver Detalhes
                    </button>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<!-- Empty State -->
<c:if test="${empty eventos}">
    <div class="card">
        <div class="card-body">
            <div class="empty-state">
                <div class="empty-state-icon">
                    <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                        <line x1="16" y1="2" x2="16" y2="6"></line>
                        <line x1="8" y1="2" x2="8" y2="6"></line>
                        <line x1="3" y1="10" x2="21" y2="10"></line>
                    </svg>
                </div>
                <h3 class="empty-state-title">Nenhum evento disponível</h3>
                <p class="empty-state-text">Não há eventos cadastrados no momento.</p>
            </div>
        </div>
    </div>
</c:if>

<!-- Modal de Detalhes -->
<div id="modalDetalhes" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.5); z-index: 1000; align-items: center; justify-content: center;">
    <div class="card" style="max-width: 600px; width: 90%; margin: 2rem;">
        <div class="card-header" style="display: flex; justify-content: space-between; align-items: center;">
            <h3 id="modalTitulo">Detalhes do Evento</h3>
            <button onclick="fecharModal()" style="background: none; border: none; font-size: 1.5rem; cursor: pointer;">&times;</button>
        </div>
        <div class="card-body">
            <div id="modalConteudo"></div>
        </div>
        <div class="card-footer">
            <button onclick="fecharModal()" class="btn btn-outline btn-sm">Fechar</button>
        </div>
    </div>
</div>

<script>
const eventosData = {};
<c:forEach var="evento" items="${eventos}">
eventosData[${evento.idEvento}] = {
    nome: "<c:out value='${evento.nome}'/>",
    data: "<c:out value='${evento.data}'/>",
    local: "<c:out value='${evento.local}'/>",
    informacoes: "<c:out value='${evento.informacoes}'/>"
};
</c:forEach>

function verDetalhes(eventoId) {
    const evento = eventosData[eventoId];
    if (evento) {
        document.getElementById('modalTitulo').textContent = evento.nome;
        document.getElementById('modalConteudo').innerHTML = 
            '<div style="margin-bottom: 1rem;"><strong>Data:</strong> ' + evento.data + '</div>' +
            '<div style="margin-bottom: 1rem;"><strong>Local:</strong> ' + evento.local + '</div>' +
            '<div><strong>Informações:</strong><br>' + evento.informacoes + '</div>';
        document.getElementById('modalDetalhes').style.display = 'flex';
    }
}

function fecharModal() {
    document.getElementById('modalDetalhes').style.display = 'none';
}

// Fechar modal ao clicar fora
document.getElementById('modalDetalhes').addEventListener('click', function(e) {
    if (e.target === this) {
        fecharModal();
    }
});
</script>

<!-- ========== FIM: EVENTOS DO ALUNO ========== -->
