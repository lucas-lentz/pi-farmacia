window.onload = function () {
    getRequest(); // Carrega os departamentos assim que a página abre
};

const departmentForm = document.getElementById('departmentForm');
const departmentList = document.getElementById('departmentList');
const searchInput = document.getElementById('searchInput');
const saveDepartmentBtn = document.getElementById('saveDepartmentBtn');
const saveEditDepartmentBtn = document.getElementById('saveEditDepartmentBtn');
let departments = [];
let currentEditIndex = null;

//url do Front
const url = 'http://localhost:8080/departamentos';

// Funções que mostram os modais

function showAddModal() {
    $('#addDepartmentModal').modal('show'); // Abre o modal para adição
}

function showEditModal(index) {
    currentEditIndex = index;
    const department = departments[index];
    document.getElementById('editDepartmentName').value = department.nome;
    $('#editDepartmentModal').modal('show'); // Abre o modal para edição
}

document.getElementById('addDepartamentBtn').onclick = function () {
   showAddModal()
};

// Função para renderizar os departamentos
// O ID DESSA LISTA NAO E O MESMO ID DO BANCO DE DADOS!
function renderDepartments(filter = '') {
    departmentList.innerHTML = ''; // Limpa a lista antes de renderizar

    const filteredDepartments = departments.filter(department =>
        department.nome.toLowerCase().includes(filter.toLowerCase()) 
    );

    //Pegando um departamento por vez
    filteredDepartments.forEach((department, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${index + 1}</td>
            <td>${department.nome}</td>
            <td class="text-center">
                <button class="btn btn-warning btn-sm" onclick="showEditModal(${index})">Editar</button>
                <button class="btn btn-danger btn-sm" onclick="confirmRemoveDepartment(${index})">Remover</button>
            </td>
        `;
        departmentList.appendChild(row);
    });
}

// Salvar o departamento com JSON
saveDepartmentBtn.onclick = function () {
    const departmentName = document.getElementById('departmentName').value;

    if (!departmentName) {
        alert('Por favor, insira o nome do departamento.');
        return;
    }

    const confirmation = confirm(`Você tem certeza que deseja adicionar o departamento "${departmentName}"?`);

    if (confirmation) {
        //JSON para mandar pro back-end
        const department = { nome: departmentName };

        // Criar novo departamento
        postRequest(department); // Chama a função para criar (POST)
        alert('Departamento adicionado com sucesso!');
        
        
        $('#addDepartmentModal').modal('hide'); // Fecha o modal
        departmentForm.reset(); // Reseta o formulário
        currentEditIndex = null; // Reseta o índice após edição
        
    }
};

// Editar o departamento com JSON (funcionando)
saveEditDepartmentBtn.onclick = function () {
    const departmentName = document.getElementById('editDepartmentName').value;

    if (!departmentName) {
        alert('Por favor, insira o nome do departamento.');
        return;
    }

    const confirmation = confirm(`Você tem certeza que deseja 'atualizar' o departamento "${departmentName}"?`);

    if (confirmation) {
        const department = { nome: departmentName };
        
        // Editar departamento existente
        const departmentId = departments[currentEditIndex].id; // Pega o ID do departamento sendo editado
        putRequest(departmentId, department); // Chama a função para editar (PUT)
        
        $('#editDepartmentModal').modal('hide'); // Fecha o modal
        departmentForm.reset(); // Reseta o formulário
        currentEditIndex = null; // Reseta o índice após edição
        
    }

}

// Função para confirmar a remoção de um departamento
function confirmRemoveDepartment(index) {
    const department = departments[index]; // Pegando o departamento correto
    const confirmation = confirm(`Você tem certeza que deseja deletar o departamento "${department.nome}"?`);
    
    if (confirmation) {
        removeDepartment(department.id, index); // Passa o ID e o índice do array local
    }
}

// Função para remover efetivamente o departamento
async function removeDepartment(id, index) {
    try {
        await deleteRequest(id); // Faz o DELETE no back-end
        alert('Departamento removido com sucesso!');
        
        // Remove localmente da lista de departamentos
        departments.splice(index, 1);
        renderDepartments(); // Atualiza a lista na tela
    } catch (error) {
        alert(error.message);
    }
}

// Filtra os departamentos com base na entrada de pesquisa
function filterDepartments() {
    const filterValue = searchInput.value; //o que foi digitado na tela do Front
    //end-point
    fetch(`http://localhost:8080/departamentos/search?nome=${filterValue}`)
        .then(response => response.json())
        .then(data => {
            departments = data; // Atualiza o array de departamentos
            renderDepartments(); // Renderiza a lista filtrada
        })
        .catch(error => {
            console.error('Erro ao buscar departamentos:', error);
        });
}


// Função GET para buscar todos os departamentos do backend
const getRequest = async () => {
    try {
        let response = await fetch(url); // Pega os dados da URL
        if (!response.ok) {
            throw new Error('Erro ao buscar departamentos');
        }
        let departamentos = await response.json(); // Converte a resposta para JSON
        departments = departamentos; // Atualiza a variável global 'departments'
        renderDepartments(); // Renderiza a lista de departamentos
    } catch (error) {
        console.error('Erro:', error.message);
        alert('Erro ao carregar departamentos');
    }
}

// Função para fazer POST de um departamento
const postRequest = async (department) => {
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(department)
        });

        if (!response.ok) {
            throw new Error('Erro ao adicionar o departamento');
        }

        const data = await response.json();
        await getRequest(); // Puxa a lista atualizada de departamentos do backend
        renderDepartments(); // Renderiza a nova lista na tela
        return data; // Retorna os dados adicionados
    } catch (error) {
        throw new Error(error.message);
    }
};

//Fazendo o DELETE
const deleteRequest = async (id) => {
    try {
        const response = await fetch(`${url}/${id}`, {
            method: 'DELETE',
        });

        if (!response.ok) {
            throw new Error('Erro ao deletar o departamento');
        }

        return true; // Deleção foi bem-sucedida
    } catch (error) {
        throw new Error(error.message);
    }
};

//Fazendo o PUT
const putRequest = async (id, updatedDepartment) => {
    const response = await fetch(`${url}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedDepartment),
    });

    if (response.ok) {
        const data = await response.json();
        alert('Departamento atualizado com sucesso!');
        await getRequest(); // Puxa a lista atualizada do banco de dados
    } else {
        alert('Erro ao atualizar o departamento');
    }
};