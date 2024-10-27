# Projeto Integrador - Farmácia <br> Aplicação Web

## Índice
- [Objetivo](#objetivo)
- [Funcionalidades](#funcionalidades)
- [Utilização](#utilização)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Autores](#autores)

## 🎯 Objetivo



<br>

## 🧰 Funcionalidades
### 1. Sistema de Login Único

<strong>Login Fixo:</strong> Um login único com usuário e senha pré-definidos no sistema. Apenas quem conhece as credenciais pode acessar o sistema de cadastro e controle de estoque.<br>

Validação de Login: Sistema de validação de login com mensagens de erro, caso o usuário tente acessar com credenciais incorretas.<br><br>


### 2. Cadastro de Itens

<strong>Adicionar Produto:</strong> Permite ao usuário cadastrar um novo produto no sistema, inserindo informações como:
- Nome do produto
- Descrição
- Quantidade em estoque
- Preço
- Categoria
- Marca

<strong>Edição de Produto:</strong> Opção para editar as informações de um produto já cadastrado, alterando qualquer um dos campos, incluindo a quantidade em estoque ou até mesmo mudar a marca ou categoria do mesmo.<br>

<strong>Exclusão de Produto:</strong> Permite ao usuário excluir um produto do sistema. Será solicitado uma confirmação antes de excluir permanentemente.<br>

<strong>Listagem de Produtos:</strong> Exibe todos os produtos cadastrados, organizados por categorias e marcas.<br><br>


### 3. Pesquisa Dinâmica de Produtos

<strong>Busca por Nome:</strong> O usuário pode buscar produtos dinamicamente pelo nome. A pesquisa deve atualizar a lista de produtos conforme o usuário digita, permitindo encontrar o item rapidamente.<br><br>


### 4. Gerenciamento de Categorias, Marcas e Departamentos

<strong>Cadastro de Categorias:</strong> Permite adicionar novas categorias de produtos, cada uma associada a um departamento específico.<br>

<strong>Cadastro de Marcas:</strong> Permite adicionar novas marcas para os produtos.<br>

<strong>Departamentos:</strong> Cada categoria pertence a um departamento, então é necessário definir quais departamentos existem no sistema.<br><br>


### 5. Integração Futura: Imagens de Produtos
<strong>Upload de Imagens:</strong> Futuramente, o sistema terá a capacidade de permitir que o usuário faça upload de imagens dos produtos.<br>
<strong>Visualização de Imagens:</strong> Cada produto poderá exibir sua imagem na listagem, facilitando a identificação visual.<br>
<strong>Sistema de Cadastro de Usuários:</strong> Implementação de um sistema de registro, onde cada usuário poderá criar sua própria conta. Isso permitirá acesso individualizado, segurança aprimorada e personalização dos dados de acesso.<br><br>


<br>

## 💻 Utilização

### Como utilizar o programa

<br>

### Exemplos de Utilização



<br>

## 📚 Tecnologias Utilizadas

<table>
  <tr>
    <th>Linguagens de programação</th>
    <td>
      <img width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg"/>
      <img width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/javascript/javascript-original.svg"/>
      <img width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/azuresqldatabase/azuresqldatabase-original.svg"/>
    </td>
  </tr>
  <tr>
    <th>Linguagens de marcação e estilização</th>
    <td>
      <img width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/html5/html5-original-wordmark.svg"/>
      <img width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/css3/css3-original-wordmark.svg"/>
    </td>
  </tr>
  <tr>
    <th>Framework</th>
    <td>
      <img width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original-wordmark.svg" />
    </td>
  </tr>
  <tr>
    <th>Bibliotecas e Dependências</th>
    <td>
      <img alt="Static Badge" src="https://img.shields.io/badge/spring%20data%20jpa-black?style=for-the-badge">
      <img alt="Static Badge" src="https://img.shields.io/badge/spring%20web-black?style=for-the-badge">
      <img alt="Static Badge" src="https://img.shields.io/badge/spring%20boot%20devtools-black?style=for-the-badge">
      <br>
      <img alt="Static Badge" src="https://img.shields.io/badge/mysql%20driver-black?style=for-the-badge">
      <img alt="Static Badge" src="https://img.shields.io/badge/lombok-black?style=for-the-badge">
    </td>
  </tr>
  <tr>
    <th>Ferramentas e Outros</th>
    <td>
      <img width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postman/postman-original.svg"/>
    </td>
  </tr>
</table>

<br>

## 📂 Estrutura do Projeto
### Back-end
<table>
  <tr>
    <th>Pacotes</th>
    <th width="500px">Funcionalidade(s)</th>
  </tr>
  <tr>
    <th>Entities</th>
    <td>Define as classes que representam as tabelas do banco de dados, incluindo anotações JPA para mapeamento e relacionamentos.</td>
  </tr>
  <tr>
    <th>DTO</th>
    <td>DTO ou Data Transfer Object, utiliza classes que transferem dados entre a camada de apresentação e a camada de serviço, evitando o uso direto das entidades.</td>
  </tr>
  <tr>
    <th>Service</th>
    <td>Contém a lógica de negócio da aplicação. Interage com os repositórios para realizar operações sobre as entidades.</td>
  </tr>
  <tr>
    <th>Repository</th>
    <td>Define interfaces que estendem `JpaRepository` ou `CrudRepository`, facilitando as operações de acesso a dados, como salvar, buscar, atualizar e deletar.</td>
  </tr>
  <tr>
    <th>Controller</th>
    <td>Gerencia as requisições HTTP e as respostas. Mapeia URLs para métodos que manipulam os dados e interagem com os serviços.</td>
  </tr>
  <tr>
    <th>Util</th>
    <td>Contém classes utilitárias que oferecem funcionalidades auxiliares, como manipulação de data, formatação, ou constantes utilizadas em toda a aplicação.</td>
  </tr>
</table>

<br>

## 👥 Autores

| [<img loading="lazy" src="https://avatars.githubusercontent.com/u/168394448?v=4" width=115><br><sub>Lorenzo Rover</sub>](https://github.com/lorenzorover) | [<img loading="lazy" src="https://avatars.githubusercontent.com/u/168482892?v=4" width=115><br><sub>Lucas Lentz</sub>](https://github.com/lucas-lentz) | [<img loading="lazy" src="https://avatars.githubusercontent.com/u/159506794?v=4" width=115><br><sub>Kaio Gredilha Pinheiro</sub>](https://github.com/Kaio-pinheiro) | [<img loading="lazy" src="https://avatars.githubusercontent.com/u/114513409?v=4" width=115><br><sub>João Ribeiro Scharmann</sub>](https://github.com/JRScharmann) |
| :---: | :---: | :---: | :---: |

<br>

### 📆 Release date: <br>
nov/2024
