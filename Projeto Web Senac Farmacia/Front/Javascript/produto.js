window.onload = function () {
    getRequest() // Carrega os produtos assim que a página abre
    loadBrands() // Carrega as marcas para o select ao carregar a página
    loadCategories() // Carrega as categorias para o select ao carregar a página
}

// Elementos do formulário
const productForm = document.getElementById('productForm')
const productList = document.getElementById('productList')
const searchInput = document.getElementById('searchInput')
const saveProductBtn = document.getElementById('ProductBtn')
const saveEditProductBtn = document.getElementById('saveEditProductBtn')

// Selects para marcas e categorias
const brandSelect = document.getElementById('brandList')
const categorySelect = document.getElementById('categoryList')
const editBrandSelect = document.getElementById('editBrandList')
const editCategorySelect = document.getElementById('editCategoryList')

let products = []
let brands = []
let categories = []
let currentEditIndex = null

// URL do back-end
const url = 'http://localhost:8080/produtos'
const brandUrl = 'http://localhost:8080/marcas'
const categoryUrl = 'http://localhost:8080/categorias'

// Funções que mostram os modais
function showAddModal() {
    $('#addProductModal').modal('show') // Abre o modal para adicionar produto
}

function showEditModal(index) {
    currentEditIndex = index
    const product = products[index]
    document.getElementById('editProductName').value = product.nome
    document.getElementById('editProductDescription').value = product.descricao
    document.getElementById('editProductPrice').value = product.preco
    document.getElementById('editProductQuantity').value = product.quantidade

    // Carregar as marcas e categorias para o modal de edição
    loadBrands(true)
    loadCategories(true)

    $('#editProductModal').modal('show') // Abre o modal para editar produto
}

document.getElementById('addProductBtn').onclick = function () {
    showAddModal()
}

// Carregar marcas
const loadBrands = async (isEdit = false) => {
    try {
        let response = await fetch(brandUrl)
        if (!response.ok) {
            throw new Error('Erro ao buscar marcas')
        }
        let brandData = await response.json()
        brands = brandData

        // Limpar selects
        brandSelect.innerHTML = '<option value="">Selecione uma marca</option>'
        editBrandSelect.innerHTML = '<option value="">Selecione uma marca</option>'

        // Preencher selects com as marcas carregadas
        brands.forEach(brand => {
            const option = `<option value="${brand.id}">${brand.nome}</option>`
            brandSelect.innerHTML += option
            editBrandSelect.innerHTML += option
        })
    } catch (error) {
        console.error('Erro ao carregar marcas:', error)
        alert('Erro ao carregar marcas')
    }
}

// Carregar categorias
const loadCategories = async (isEdit = false) => {
    try {
        let response = await fetch(categoryUrl)
        if (!response.ok) {
            throw new Error('Erro ao buscar categorias')
        }
        let categoryData = await response.json()
        categories = categoryData

        // Limpar selects
        categorySelect.innerHTML =
            '<option value="">Selecione uma categoria</option>'
        editCategorySelect.innerHTML =
            '<option value="">Selecione uma categoria</option>'

        // Preencher selects com as categorias carregadas
        categories.forEach(category => {
            const option = `<option value="${category.id}">${category.nome}</option>`
            categorySelect.innerHTML += option
            editCategorySelect.innerHTML += option
        })
    } catch (error) {
        console.error('Erro ao carregar categorias:', error)
        alert('Erro ao carregar categorias')
    }
}

//Tratando os campos de números
document.getElementById('productPrice').addEventListener('keypress', filterInput);
document.getElementById('productQuantity').addEventListener('keypress', filterInput);

// Função para renderizar os produtos
function renderProducts(filter = '') {
    productList.innerHTML = '' // Limpa a lista de produtos antes de renderizar

    const filteredProducts = products.filter(product =>
        product.nome.toLowerCase().includes(filter.toLowerCase())
    )

    filteredProducts.forEach((product, index) => {
        const row = `
            <tr>
                <td>${index + 1}</td>
                <td>${product.nome}</td>
                <td class="description">
                    <span class="read-more" onclick="showFullDescription('${product.descricao}')">Leia Mais</span>
                </td>
                <td>R$ ${product.preco.toFixed(2).replace(".", ",")}</td>
                <td>${product.quantidade}</td>
                <td>${product.marca.nome}</td>
                <td>${product.categoria.nome}</td>
                <td>${product.categoria.departamento.nome}</td>
                <td class="text-center">
                    <button class="btn btn-primary btn-sm" onclick="showEditModal(${index})">Editar</button>
                    <button class="btn btn-danger btn-sm" onclick="removeProduct(${index})">Remover</button>
                </td>
            </tr>
        `
        productList.innerHTML += row
    })
}

// Função para buscar e carregar os produtos do back-end
const getRequest = async () => {
    try {
        let response = await fetch(url)
        if (!response.ok) {
            throw new Error('Erro ao buscar produtos')
        }
        let productData = await response.json()
        products = productData
        renderProducts() // Renderiza a lista de produtos
    } catch (error) {
        console.error('Erro ao carregar produtos:', error)
        alert('Erro ao carregar produtos')
    }
}

// Função para adicionar um novo produto
const addProduct = async () => {
    const newProduct = {
        nome: document.getElementById('productName').value,
        descricao: document.getElementById('productDescription').value,
        preco: parseFloat(document.getElementById('productPrice').value),
        quantidade: parseInt(document.getElementById('productQuantity').value),
        marcaDTO: { id: brandSelect.value },
        categoriaDTO: { id: categorySelect.value }
    }

    if (
        !newProduct.nome ||
        !newProduct.descricao ||
        isNaN(newProduct.preco) ||
        isNaN(newProduct.quantidade) ||
        !newProduct.marcaDTO.id ||
        !newProduct.categoriaDTO.id
    ) {
        alert('Por favor, preencha todos os campos!')
        return
    }


    const confirmation = confirm(
        `Você tem certeza que deseja adicionar o produto "${newProduct.nome}"?`
    )

    if (confirmation) {
        // Verifica se todos os campos estão preenchidos

        try {
            let response = await fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(newProduct)
            })

            if (!response.ok) {
                throw new Error('Erro ao adicionar produto')
            }
            alert('Produto adicionado com sucesso!')
            $('#addProductModal').modal('hide')
            const data = await response.json()
            await getRequest() // Puxa a lista atualizada de produtos do backend
            renderProducts() // Renderiza a nova lista na tela
            $('#editProductModal').modal('hide')
            productForm.reset()
            currentEditIndex = null
        } catch (error) {
            console.error('Erro ao adicionar produto:', error)
            alert('Erro ao adicionar produto')
        }
    }
}

// Função para remover produto
const removeProduct = async index => {
    
    if(confirm("Tem certeza que quer remover o produto?")) {
        const productId = products[index].id

        try {
            let response = await fetch(`${url}/${productId}`, { method: 'DELETE' })
            if (!response.ok) {
                throw new Error('Erro ao remover produto')
            }
            alert("Produto removido com sucesso!")
            getRequest() // Atualiza a lista de produtos
        } catch (error) {
            console.error('Erro ao remover produto:', error)
            alert('Erro ao remover produto')
        }
    }

}

// Filtrar produtos com base no input de busca
function filterProducts() {
    const searchTerm = searchInput.value
    renderProducts(searchTerm)
}

// Função para salvar alterações em um produto
const saveEditProduct = async () => {
    const newProduct = {
        nome: document.getElementById('editProductName').value,
        descricao: document.getElementById('editProductDescription').value,
        preco: parseFloat(document.getElementById('editProductPrice').value),
        quantidade: parseInt(document.getElementById('editProductQuantity').value),
        //Pesquisar pra vir já preenchido
        marcaDTO: { id: editBrandSelect.value },
        categoriaDTO: { id: editCategorySelect.value }
    }

    if (
        !newProduct.nome ||
        !newProduct.descricao ||
        isNaN(newProduct.preco) ||
        isNaN(newProduct.quantidade) ||
        !newProduct.marcaDTO.id ||
        !newProduct.categoriaDTO.id
    ) {
        alert('Por favor, preencha todos os campos!')
        return
    }

    const confirmation = confirm(
        `Você tem certeza que deseja editar o produto "${newProduct.nome}"?`
    )


    if (confirmation) {

        try {
            const productId = products[currentEditIndex].id
            // const productId = newProduct.id;
            let response = await fetch(`${url}/${productId}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(newProduct)
            })

            if (!response.ok) {
                throw new Error('Erro ao editar produto')
            }
            alert('Produto editado com sucesso!')
            $('#editProductModal').modal('hide')
            const data = await response.json()
            await getRequest() // Puxa a lista atualizada de produtos do backend
            renderProducts() // Renderiza a nova lista na tela
            productForm.reset()
            currentEditIndex = null
            return data // Retorna os dados adicionados
        } catch (error) {
            console.error('Erro ao editar produto:', error)
            alert('Erro ao editar produto')
        }
    }
}

// Event listeners para botões de salvar
saveProductBtn.addEventListener('click', addProduct)
saveEditProductBtn.addEventListener('click', saveEditProduct)

// Função para prevenir que o usuário digite +, - e "e".
function filterInput(event) {
    const key = event.key;
    const value = event.target.value;

    
    if (['e', '+', '-'].includes(key)) {
        event.preventDefault();
    }

    // Apenas duas casas decimais
    if (event.target.id === 'productPrice') {
        const decimalIndex = value.indexOf('.');
        if (decimalIndex >= 0 && value.length - decimalIndex > 2 && key !== 'Backspace') {
            event.preventDefault();
        }
    }
    //Sem casa decimal pra quantidade
    if (event.target.id === 'productQuantity' && (key === '.' || key === ',')) {
        event.preventDefault();
    
}
}

// Cria e mostra o modal com a descrição completa
function showFullDescription(description) {
    
    const modal = document.createElement('div');
    modal.className = 'description-modal';
    modal.innerHTML = `
        <div class="description-modal-content">
        <p>${description}</p>
            <button class="description-close" onclick="closeDescriptionModal()"><img src="../Assets/close-image.png" width="20px"></button>
        </div>`;
    document.body.appendChild(modal);

        // Se o usuário clicar fora do modal ou ESC, fecha o modal
        modal.addEventListener('click', (event) => {
            if (event.target === modal) closeDescriptionModal();
        });
        document.addEventListener('keydown', handleKeyPress);




}

function closeDescriptionModal() {
    const modal = document.querySelector('.description-modal').remove();
    if (modal) modal.remove();
    document.removeEventListener('keydown', handleKeyPress);
}


function handleKeyPress(event) {
    if (event.key === 'Escape') closeDescriptionModal();
}
