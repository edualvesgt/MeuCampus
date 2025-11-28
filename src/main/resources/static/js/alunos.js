function editarAluno(id) {
    // Redireciona para página de edição ou abre modal
    window.location.href = `/alunos/editar/${id}`;
}

function deletarAluno(id) {
    if (confirm('Tem certeza que deseja deletar este aluno?')) {
        fetch(`/api/alunos/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert('Aluno deletado com sucesso!');
                window.location.reload();
            } else {
                alert('Erro ao deletar aluno');
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao deletar aluno');
        });
    }
}
