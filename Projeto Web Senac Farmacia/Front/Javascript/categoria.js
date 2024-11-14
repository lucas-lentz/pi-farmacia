window.onload = function () {
    getRequest(); // Carrega as categorias assim que a página abre
    loadDepartments(); // Carrega os departamentos para o select ao carregar a página
};

const categoryForm = document.getElementById('categoryForm');
const categoryList = document.getElementById('categoryList');
const searchInput = document.getElementById('searchInput');
const saveCategoryBtn = document.getElementById('saveCategoryBtn');
const saveEditCategoryBtn = document.getElementById('saveEditCategoryBtn');
//Referente ao departamento
const departmentSelect = document.getElementById('departmentList'); // Select de departamentos no modal de adicionar
const editDepartmentSelect = document.getElementById('editDepartmentList'); // Select de departamentos no modal de edição


let categories = [];
let departments = []; // Armazena os departamentos obtidos via GET
let currentEditIndex = null;

//url do Front
const url = 'http://localhost:8080/categorias';
const departmentUrl = 'http://localhost:8080/categorias/departamentos';

// Funções que mostram os modais

function showAddModal() {
    $('#addCategoryModal').modal('show'); // Abre o modal para adição
}

function showEditModal(index) {
    currentEditIndex = index;
    const category = categories[index];
    document.getElementById('editCategoryName').value = category.nome;
    loadDepartments(true); // Carrega os departamentos antes de abrir o modal de edição
    $('#editCategoryModal').modal('show'); // Abre o modal para edição
}

document.getElementById('addCategoryBtn').onclick = function () {
   showAddModal()
};

// Pegando os departamentos
const loadDepartments = async (isEdit = false) => {
    try {
        let response = await fetch(departmentUrl);
        console.log(response); // Verifica a resposta
        if (!response.ok) {
            throw new Error('Erro ao buscar departamentos');
        }
        let departmentData = await response.json();
        departments = departmentData; // Atualiza o array com os departamentos

        // Limpa os selects
        departmentSelect.innerHTML = '<option value="">Selecione um departamento</option>';
        editDepartmentSelect.innerHTML = '<option value="">Selecione um departamento</option>';

        // Preenche os selects com os departamentos carregados
        departments.forEach(department => {
            const option = `<option value="${department.id}">${department.nome}</option>`;
            departmentSelect.innerHTML += option;
            editDepartmentSelect.innerHTML += option;
        });

    } catch (error) {
        console.error('Erro ao carregar departamentos:', error);
        alert('Erro ao carregar departamentos');
    }
};


// O ID DESSA LISTA NAO E O MESMO ID DO BANCO DE DADOS!
// Função para renderizar as categorias
function renderCategories(filter = '') {
    categoryList.innerHTML = ''; // Limpa a lista antes de renderizar

    const filteredCategories = categories.filter(category =>
        category.nome.toLowerCase().includes(filter.toLowerCase()) 
    );

    console.log("Rendering categories:", categories);

    // Pegando uma categoria por vez
    filteredCategories.forEach((category, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${index + 1}</td>
            <td>${category.nome}</td>
            <td>${category.departamento ? category.departamento.nome : 'N/A'}</td>
            <td class="text-center">
                <button class="btn btn-primary btn-sm" onclick="showEditModal(${index})">Editar</button>
                <button class="btn btn-danger btn-sm" onclick="confirmRemoveCategory(${index})">Remover</button>
            </td>
        `;
        categoryList.appendChild(row);
    });
}


// Função para salvar uma nova categoria com JSON
saveCategoryBtn.onclick = function () {
    const categoryName = document.getElementById('categoryName').value;
    const departmentId = document.getElementById('departmentList').value; // ID do departamento selecionado

    if (!categoryName || !departmentId) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    const confirmation = confirm(`Você tem certeza que deseja adicionar a categoria "${categoryName}"?`);

    if (confirmation) {
        const category = { nome: categoryName, departamentoDTO: { id: departmentId } }; // Certo para adicionar/editar


        postRequest(category); // Chama a função para criar (POST)
        alert('Categoria adicionada com sucesso!');

        $('#addCategoryModal').modal('hide'); // Fecha o modal
        categoryForm.reset(); // Reseta o formulário
        currentEditIndex = null; // Reseta o índice após edição
    }
};


// Função para editar uma categoria existente
saveEditCategoryBtn.onclick = function () {
    const categoryName = document.getElementById('editCategoryName').value;
    const departmentId = document.getElementById('editDepartmentList').value; // ID do departamento selecionado no modal de edição

    if (!categoryName || !departmentId) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    const confirmation = confirm(`Você tem certeza que deseja atualizar a categoria "${categoryName}"?`);

    if (confirmation) {
        const category = { nome: categoryName, departamentoDTO: { id: departmentId } }; // Certo para adicionar/editar

        const categoryId = categories[currentEditIndex].id; // Pega o ID da categoria sendo editada
        putRequest(categoryId, category); // Chama a função para editar (PUT)

        $('#editCategoryModal').modal('hide'); // Fecha o modal
        categoryForm.reset(); // Reseta o formulário
        currentEditIndex = null; // Reseta o índice após edição
    }
};

// Função para confirmar a remoção de uma categoria
function confirmRemoveCategory(index) {
    const category = categories[index]; // Pegando a categoria correta
    /* Mexer */
    const confirmation = confirm(`Você tem certeza que deseja deletar a categoria "${category.nome}"?`);
    
    if (confirmation) {
        removeCategory(category.id, index); // Passa o ID e o índice do array local
    }
}

// Função para remover efetivamente o departamento
async function removeCategory(id, index) {
    try {
        await deleteRequest(id); // Faz o DELETE no back-end
        alert('Categoria removido com sucesso!');
        
        // Remove localmente da lista de categorias
        categories.splice(index, 1);
        renderCategories(); // Atualiza a lista na tela
    } catch (error) {
        alert(error.message);
    }
}

// Filtra os departamentos com base na entrada de pesquisa
function filterCategories() {
    const filterValue = searchInput.value; //o que foi digitado na tela do Front
    
    //end-point
    fetch(`http://localhost:8080/categorias/search?nome=${filterValue}`)
        .then(response => response.json())
        .then(data => {
            categories = data; // Atualiza o array de departamentos
            // O fetch ta recebendo os dados como departamentoDTO, mas para renderizar a tabela precisa estar 
            // Como "departamento"
            const normalizedCategories = categories.map(category => {
                if (category.departamentoDTO) {
                    category.departamento = category.departamentoDTO; // Converte 'departamentoDTO' para 'departamento'
                    delete category.departamentoDTO;
                }
                return category;
            });

            renderCategories(); // Renderiza a lista filtrada
        })
        .catch(error => {
            console.error('Erro ao buscar categorias:', error);
        });
}

// Função GET para buscar todas as categorias do backend
const getRequest = async () => {
    try {
        let response = await fetch(url); // Pega os dados da URL
        if (!response.ok) {
            throw new Error('Erro ao buscar categorias');
        }
        let categorias = await response.json(); // Converte a resposta para JSON
        categories = categorias; // Atualiza a variável global 'categories'
        renderCategories(); // Renderiza a lista de categorias
    } catch (error) {
        console.error('Erro:', error.message);
        alert('Erro ao carregar categorias');
    }
}

// Função para fazer POST de um departamento
const postRequest = async (category) => {
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(category)
        });

        if (!response.ok) {
            throw new Error('Erro ao adicionar a categoria!');
        }

        const data = await response.json();
        await getRequest(); // Puxa a lista atualizada de categorias do backend
        renderCategories(); // Renderiza a nova lista na tela
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
            throw new Error('Erro ao deletar a categoria');
        }

        return true; // Deleção foi bem-sucedida
    } catch (error) {
        throw new Error(error.message);
    }
};

//Fazendo o PUT
const putRequest = async (id, updatedCategory) => {
    const response = await fetch(`${url}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedCategory),
    });

    if (response.ok) {
        const data = await response.json();
        alert('Categoria atualizada com sucesso!');
        await getRequest(); // Puxa a lista atualizada do banco de dados
    } else {
        alert('Erro ao atualizar a categoria');
    }
};