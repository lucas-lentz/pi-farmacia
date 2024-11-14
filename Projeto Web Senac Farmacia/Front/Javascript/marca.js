window.onload = function () {
    getRequest(); // Carrega as marcas assim que a página abre
};

const brandForm = document.getElementById('brandForm');
const brandList = document.getElementById('brandList');
const searchInput = document.getElementById('searchInput');
const saveBrandBtn = document.getElementById('saveBrandBtn');
const saveEditBrandBtn = document.getElementById('saveEditBrandBtn');
let brands = [];
let currentEditIndex = null;

//url do Front
const url = 'http://localhost:8080/marcas';

// Funções que mostram os modais

function showAddModal() {
    $('#addBrandModal').modal('show'); // Abre o modal para adição
}

function showEditModal(index) {
    currentEditIndex = index;
    const brand = brands[index];
    document.getElementById('editBrandName').value = brand.nome;
    $('#editBrandModal').modal('show'); // Abre o modal para edição
}

document.getElementById('addBrandBtn').onclick = function () {
   showAddModal()
};

// Função para renderizar as marcas
// O ID DESSA LISTA NAO E O MESMO ID DO BANCO DE DADOS!
function renderBrands(filter = '') {
    brandList.innerHTML = ''; // Limpa a lista antes de renderizar

    const filteredBrands = brands.filter(brand =>
        brand.nome.toLowerCase().includes(filter.toLowerCase()) 
    );

    //Pegando uma marca por vez
    filteredBrands.forEach((brand, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${index + 1}</td>
            <td>${brand.nome}</td>
            <td class="text-center">
                <button class="btn btn-primary btn-sm" onclick="showEditModal(${index})">Editar</button>
                <button class="btn btn-danger btn-sm" onclick="confirmRemoveBrand(${index})">Remover</button>
            </td>
        `;
        brandList.appendChild(row);
    });
}

// Salvar a marca com JSON
saveBrandBtn.onclick = function () {
    const brandName = document.getElementById('brandName').value;

    if (!brandName) {
        alert('Por favor, insira o nome da marca!');
        return;
    }

    const confirmation = confirm(`Você tem certeza que deseja adicionar a marca "${brandName}"?`);

    if (confirmation) {
        //JSON para mandar pro back-end
        const brand = { nome: brandName };

        // Criar nova marca
        postRequest(brand); // Chama a função para criar (POST)
        alert('Marca adicionada com sucesso!');
        
        
        $('#addBrandModal').modal('hide'); // Fecha o modal
        brandForm.reset(); // Reseta o formulário
        currentEditIndex = null; // Reseta o índice após edição
        
    }
};

// Editar a marca com JSON 
saveEditBrandBtn.onclick = function () {
    const brandName = document.getElementById('editBrandName').value;

    if (!brandName) {
        alert('Por favor, insira o nome da marca.');
        return;
    }

    const confirmation = confirm(`Você tem certeza que deseja 'atualizar' a marca "${brandName}"?`);

    if (confirmation) {
        const brand = { nome: brandName };
        
        // Editar marca existente
        const brandId = brands[currentEditIndex].id; // Pega o ID da marca sendo editada
        putRequest(brandId, brand); // Chama a função para editar (PUT)
        
        $('#editBrandModal').modal('hide'); // Fecha o modal
        brandForm.reset(); // Reseta o formulário
        currentEditIndex = null; // Reseta o índice após edição
        
    }

}

// Função para confirmar a remoção de uma marca
function confirmRemoveBrand(index) {
    const brand = brands[index]; // Pegando a marca correta
    const confirmation = confirm(`Você tem certeza que deseja deletar a marca "${brand.nome}"?`);
    
    if (confirmation) {
        removeBrand(brand.id, index); // Passa o ID e o índice do array local
    }
}

// Função para remover efetivamente a marca
async function removeBrand(id, index) {
    try {
        await deleteRequest(id); // Faz o DELETE no back-end
        alert('Marca removida com sucesso!');
        
        // Remove localmente da lista de marcas
        brands.splice(index, 1);
        renderBrands(); // Atualiza a lista na tela
    } catch (error) {
        alert(error.message);
    }
}

// Filtra as marcas com base na entrada de pesquisa
function filterBrands() {
    const filterValue = searchInput.value; //o que foi digitado na tela do Front
    //end-point
    fetch(`http://localhost:8080/marcas/search?nome=${filterValue}`)
        .then(response => response.json())
        .then(data => {
            brands = data; // Atualiza o array de marcas
            renderBrands(); // Renderiza a lista filtrada
        })
        .catch(error => {
            console.error('Erro ao buscar marcas:', error);
        });
}


// Função GET para buscar todos as marcas do backend
const getRequest = async () => {
    try {
        let response = await fetch(url); // Pega os dados da URL
        if (!response.ok) {
            throw new Error('Erro ao buscar marcas');
        }
        let marcas = await response.json(); // Converte a resposta para JSON
        brands = marcas; // Atualiza a variável global 'brands'
        renderBrands(); // Renderiza a lista de marcas
    } catch (error) {
        console.error('Erro:', error.message);
        alert('Erro ao carregar marcas');
    }
}

// Função para fazer POST de uma marca
const postRequest = async (brand) => {
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(brand)
        });

        if (!response.ok) {
            throw new Error('Erro ao adicionar a marca');
        }

        const data = await response.json();
        await getRequest(); // Puxa a lista atualizada de marcas do backend
        renderBrands(); // Renderiza a nova lista na tela
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
            throw new Error('Erro ao deletar a marca!');
        }

        return true; // Deleção foi bem-sucedida
    } catch (error) {
        throw new Error(error.message);
    }
};

//Fazendo o PUT
const putRequest = async (id, updatedBrand) => {
    const response = await fetch(`${url}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedBrand),
    });

    if (response.ok) {
        const data = await response.json();
        alert('Marca atualizada com sucesso!');
        await getRequest(); // Puxa a lista atualizada do banco de dados
    } else {
        alert('Erro ao atualizar a marca');
    }
};