# Projeto Integrador - Farmácia <br> Aplicação Web

![Language](https://img.shields.io/badge/java-black?label=language&labelColor=grey&color=%234caf50)
![Project Version](https://img.shields.io/badge/1.0-black?label=project%20version&labelColor=grey&color=%2365a535)
![Last Commit](https://img.shields.io/github/last-commit/lucas-lentz/pi-farmacia?color=%2364973d)
![Repository Size](https://img.shields.io/github/repo-size/lucas-lentz/pi-farmacia?color=%2364a432)
![License](https://img.shields.io/github/license/lucas-lentz/pi-farmacia?color=%234caf50)

## Índice

- [Objetivo](#-objetivo)
- [Funcionalidades](#-funcionalidades)
- [Utilização](#-utilização)
- [Fluxogramas](#-fluxogramas)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Autores](#-autores)

<br>

## Estrutura/Tree

```
├── LICENSE
├── Projeto Web Senac Farmacia
    ├── .vscode
    │   └── settings.json
    ├── Back
    │   └── farmacia
    │   │   ├── .idea
    │   │       ├── .gitignore
    │   │       ├── RestfulApiTool-Environment-Cache.xml
    │   │       ├── compiler.xml
    │   │       ├── encodings.xml
    │   │       ├── farmacia.iml
    │   │       ├── git_toolbox_blame.xml
    │   │       ├── jarRepositories.xml
    │   │       ├── jpa-buddy.xml
    │   │       ├── misc.xml
    │   │       └── modules.xml
    │   │   └── farmacia
    │   │       ├── .gitignore
    │   │       ├── .mvn
    │   │           └── wrapper
    │   │           │   └── maven-wrapper.properties
    │   │       ├── mvnw
    │   │       ├── mvnw.cmd
    │   │       ├── pom.xml
    │   │       └── src
    │   │           ├── main
    │   │               ├── java
    │   │               │   └── com
    │   │               │   │   └── farmacia
    │   │               │   │       └── farmacia
    │   │               │   │           ├── FarmaciaApplication.java
    │   │               │   │           ├── controller
    │   │               │   │               ├── CategoriaController.java
    │   │               │   │               ├── ControllerStatusApplication.java
    │   │               │   │               ├── DepartamentoController.java
    │   │               │   │               ├── LoginController.java
    │   │               │   │               ├── MarcaController.java
    │   │               │   │               ├── ProdutoController.java
    │   │               │   │               └── ViewController.java
    │   │               │   │           ├── dto
    │   │               │   │               ├── CategoriaDTO.java
    │   │               │   │               ├── DepartamentoDTO.java
    │   │               │   │               ├── MarcaDTO.java
    │   │               │   │               └── ProdutoDTO.java
    │   │               │   │           ├── entities
    │   │               │   │               ├── Categoria.java
    │   │               │   │               ├── Departamento.java
    │   │               │   │               ├── Marca.java
    │   │               │   │               └── Produto.java
    │   │               │   │           ├── repository
    │   │               │   │               ├── CategoriaRepository.java
    │   │               │   │               ├── DepartamentoRepository.java
    │   │               │   │               ├── MarcaRepository.java
    │   │               │   │               └── ProdutoRepository.java
    │   │               │   │           ├── security
    │   │               │   │               └── SecurityConfig.java
    │   │               │   │           ├── service
    │   │               │   │               ├── CategoriaService.java
    │   │               │   │               ├── DepartamentoService.java
    │   │               │   │               ├── MarcaService.java
    │   │               │   │               └── ProdutoService.java
    │   │               │   │           └── util
    │   │               │   │               ├── CategoriaMapper.java
    │   │               │   │               ├── DepartamentoMapper.java
    │   │               │   │               ├── MarcaMapper.java
    │   │               │   │               ├── ProdutoMapper.java
    │   │               │   │               └── WebConfig.java
    │   │               └── resources
    │   │               │   ├── application.properties
    │   │               │   ├── static
    │   │               │       ├── Assets
    │   │               │       │   ├── categorias.png
    │   │               │       │   ├── close-image.png
    │   │               │       │   ├── departamento.png
    │   │               │       │   ├── marcas.jpg
    │   │               │       │   └── produtos.jpeg
    │   │               │       ├── CSS
    │   │               │       │   ├── crudStyles.css
    │   │               │       │   ├── globalStyles.css
    │   │               │       │   ├── index.css
    │   │               │       │   ├── login.css
    │   │               │       │   └── produto.css
    │   │               │       └── Javascript
    │   │               │       │   ├── categoria.js
    │   │               │       │   ├── departamento.js
    │   │               │       │   ├── marca.js
    │   │               │       │   └── produto.js
    │   │               │   └── templates
    │   │               │       ├── categoria.html
    │   │               │       ├── departamento.html
    │   │               │       ├── index.html
    │   │               │       ├── login.html
    │   │               │       ├── marca.html
    │   │               │       └── produto.html
    │   │           └── test
    │   │               └── java
    │   │                   └── com
    │   │                       └── farmacia
    │   │                           └── farmacia
    │   │                               └── FarmaciaApplicationTests.java
    ├── Docs
    │   └── Assets do Projeto
    │   │   ├── Adicionando um produto ao sistema.png
    │   │   ├── Fluxograma Front.jpg
    │   │   ├── Fluxograma de adição de um produto.jpg
    │   │   └── Pagina Principal.png
    ├── Front
    │   ├── Assets
    │   │   ├── categorias.png
    │   │   ├── close-image.png
    │   │   ├── departamento.png
    │   │   ├── main-background.png
    │   │   ├── marcas.jpg
    │   │   └── produtos.jpeg
    │   ├── CSS
    │   │   ├── crudStyles.css
    │   │   ├── globalStyles.css
    │   │   ├── index.css
    │   │   └── produto.css
    │   ├── HTML
    │   │   ├── categoria.html
    │   │   ├── departamento.html
    │   │   ├── index.html
    │   │   ├── marca.html
    │   │   └── produto.html
    │   ├── Javascript
    │   │   ├── categoria.js
    │   │   ├── departamento.js
    │   │   ├── marca.js
    │   │   └── produto.js
    │   └── Readme.txt
    ├── Modelagem do BD
    │   ├── Modelagem Web.mwb
    │   ├── Modelagem Web.mwb.bak
    │   ├── modelagem-bd-071124.PNG
    │   └── script-db.sql
    └── README Files
    │   └── Programa Rodando (BOTAR NO MUDO).mp4
└── README.md
```

<br>

## 🎯 Objetivo

Desenvolver um sistema de gerenciamento de estoque para farmácias, permitindo o cadastro, edição, exclusão e consulta de produtos. A aplicação facilita o controle de itens, organizando produtos, categorias, marcas e departamentos, e inclui uma busca dinâmica para rápida localização de itens nas listas. Futuramente, será possível adicionar imagens aos produtos e criar contas de usuários para maior segurança e personalização de acesso.<br>

<br>

## 🧰 Funcionalidades

## 1. Sistema de Login Único

<strong>Login Fixo:</strong> Um login único com usuário e senha pré-definidos no sistema. Apenas quem conhece as credenciais pode acessar o sistema de cadastro e controle de estoque.<br>

Validação de Login: Sistema de validação de login com mensagens de erro, caso o usuário tente acessar com credenciais incorretas.<br>

<br>

## 2. Cadastro de Itens

<strong>Adicionar Produto:</strong> Permite ao usuário cadastrar um produto no sistema, inserindo informações como:
- Nome do produto
- Descrição
- Preço
- Quantidade em estoque
- Categoria
- Marca

<strong>Edição de Produto:</strong> Opção para editar as informações de um produto já cadastrado, alterando qualquer um dos campos, incluindo a quantidade em estoque ou até mesmo mudar a marca ou categoria do mesmo.<br>

<strong>Exclusão de Produto:</strong> Permite ao usuário excluir um produto do sistema. Será solicitado uma confirmação antes de excluir permanentemente.<br>

<strong>Listagem de Produtos:</strong> Exibe todos os produtos cadastrados, organizados por categorias e marcas.<br>

<br>

## 3. Pesquisa Dinâmica de Produtos

<strong>Busca por Nome:</strong> O usuário pode realizar uma pesquisa dinâmica pelo nome, que inclui produtos, categorias, marcas e departamentos. A lista de resultados deve ser atualizada automaticamente à medida que o usuário digita, facilitando a localização rápida do item desejado.<br>

<br>

## 4. Gerenciamento de Categorias, Marcas e Departamentos

<strong>Cadastro de Categorias:</strong> Permite adicionar novas categorias de produtos, cada uma associada a um departamento específico.<br>

<strong>Cadastro de Marcas:</strong> Permite adicionar novas marcas para os produtos.<br>

<strong>Departamentos:</strong> Cada categoria pertence a um departamento, então é necessário definir quais departamentos existem no sistema.<br>

<br>

## 5. Aprimoramentos Futuros
### 5.1. Imagens de Produtos
<strong>Upload de Imagens:</strong> Futuramente, o sistema terá a capacidade de permitir que o usuário faça upload de imagens dos produtos.<br>

<strong>Visualização de Imagens:</strong> Cada produto poderá exibir sua imagem na listagem, facilitando a identificação visual.<br>

<strong>Sistema de Cadastro de Usuários:</strong> Implementação de um sistema de registro, onde cada usuário poderá criar sua própria conta. Isso permitirá acesso individualizado, segurança aprimorada e personalização dos dados de acesso.<br>

<br>

### 5.2. Implementação de testes unitários
<strong>Criação de Classes de Testes:</strong> Futuramente, o sistema terá arquivos de testes para facilitar a manutenção do mesmo.<br>

<br>

### 5.3. Implementação de segurança com Token
<strong>Utilização do OAuth2RestTemplate:</strong> Futuramente, o sistema será capaz de ser utilizado fora do ambiente de testes, validando apenas as ações de usuários autenticados por meio de chave token, como fazer inserções ou exclusões do sistema.<br>

<br>

## 🔀 Fluxogramas

## Front-end

<strong>Fluxograma de funcionamento do front-end</strong><br>

![IMG-20241114-WA0004](https://github.com/user-attachments/assets/1d9fd6ea-9f46-492a-9f62-ccf187ed3b09)<br>

<br>

## Back-end

<strong>Fluxograma do funcionamento da adição de um produto no Banco de Dados</strong><br>

![5077a988-3f04-4221-8f3f-e4d7a3d4aac4](https://github.com/user-attachments/assets/3282b12e-ae44-4fe6-9b5c-e8bd9df721ed)<br>

<br>

## 💻 Utilização

### Como utilizar o programa
<strong>1) Criação do Schema no Banco de Dados:</strong> Criar a Database farmacia no gerenciador de banco de dados de sua escolha.<br>

<strong>2) Rodar a aplicação Spring Boot FarmaciaApplication:</strong> Numa IDE ou compilador de código (IntelliJ IDE, Eclipse IDE, Visual Studio Code), rodar a aplicação.<br>

<strong>3) Usando a aplicação localmente:</strong> Com a aplicação rodando, o usuário recebe, pelo terminal da IDE ou compilador de código, a senha a ser colocada no Login na seguinte frase "Using generated security password:". Utilizando o usuário user e senha gerada pelo Spring security no end-point http://localhost:8080/, o usuário pode acessar a aplicação. Caso o user e/ou senha estejam errados, o login não é validado, conforme o fluxograma de funcionamento do front-end abaixo.<br>

<strong>Nota:</strong> Caso queira ver um exemplo da aplicação funcionando, você pode baixar o vídeo clicando no link abaixo:<br>
<a href="https://raw.githubusercontent.com/lucas-lentz/pi-farmacia/main/Projeto%20Web%20Senac%20Farmacia/README%20Files/Programa%20Rodando%20(BOTAR%20NO%20MUDO).mp4">Clique aqui</a><br>

<br>

### Exemplos de Utilização
<strong>Entrando no sistema com Login do Spring Security </strong><br><br>
![Login Security](https://github.com/user-attachments/assets/5e64ecbd-a9ce-449b-9725-c1e35ad37905)<br>


<br>

<strong>Tela Inicial do Sistema </strong><br><br>
![Pagina Principal](https://github.com/user-attachments/assets/88d3c7bc-fa91-420f-89ad-aec87774fccb)<br>


<br>

<strong>Responsividade da Tela Inicial </strong><br><br>
![Responsivity](https://github.com/user-attachments/assets/ba8ed43c-9b96-468d-b7e7-1247a0865abe)


<br>

<strong>Pesquisa Dinamica de Categorias</strong><br><br>
![Pesquisa Dinamica](https://github.com/user-attachments/assets/ea95a9c9-cbf5-4ed3-af5d-c46762f4c106)<br>


<br>

<strong>Responsividade da pagina de Produtos</strong><br><br>
![Responsivity Product](https://github.com/user-attachments/assets/95ec9600-b1a9-4e9d-b204-5e2a39d30c24)


<br>

<strong>Modal para adição de um produto</strong><br><br>
![Adicionando um produto ao sistema](https://github.com/user-attachments/assets/5c7c04c4-f093-435e-b67f-faa966adae06)<br>


<br>

<strong>POST de um Produto</strong><br><br>
![POST](https://github.com/user-attachments/assets/d9d6bcd7-ce44-4db9-9131-8413a3105a39)<br>


<br>

<strong>PUT de um Produto</strong><br><br>
![PUT](https://github.com/user-attachments/assets/be24c46f-2b7f-40c7-b27b-a97904e41c17)


<br>
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
      <img width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/bootstrap/bootstrap-original-wordmark.svg" />
    </td>
  </tr>
  <tr>
    <th>Bibliotecas e Dependências</th>
    <td width="500px">
      <img alt="Static Badge" src="https://img.shields.io/badge/spring%20data%20jpa-black?style=for-the-badge">
      <img alt="Static Badge" src="https://img.shields.io/badge/spring%20web-black?style=for-the-badge">
      <img alt="Static Badge" src="https://img.shields.io/badge/spring%20boot%20devtools-black?style=for-the-badge">
      <img alt="Static Badge" src="https://img.shields.io/badge/mysql%20driver-black?style=for-the-badge">
      <img alt="Static Badge" src="https://img.shields.io/badge/lombok-black?style=for-the-badge">
      <img alt="Static Badge" src="https://img.shields.io/badge/thymeleaf-black?style=for-the-badge">
      <img alt="Static Badge" src="https://img.shields.io/badge/spring%20security-black?style=for-the-badge">
      <img alt="Static Badge" src="https://img.shields.io/badge/validation-black?style=for-the-badge">
    </td>
  </tr>
  <tr>
    <th>Ferramentas e Outros</th>
    <td>
      <img width="60px" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postman/postman-original.svg"/>
    </td>
  </tr>
</table><br>

<br>

Todas as bibliotecas mencionadas na tabela são do Spring framework. Abaixo está uma breve explicação de cada uma: <br>
- <strong>Spring Data JPA</strong>: Facilita o trabalho com bancos de dados relacionais através de mapeamento objeto-relacional (ORM). Permite executar consultas e gerenciar entidades de forma simplificada;
- <strong>Spring Web</strong>: Oferece suporte para criar APIs REST e aplicações web. Simplifica a criação de endpoints HTTP, permitindo lidar facilmente com requisições e respostas;
- <strong>Spring Boot Devtools</strong>: Uma ferramenta de desenvolvimento que permite atualização automática (hot-reload) da aplicação quando alterações são feitas no código;
- <strong>MySQL Driver</strong>: Driver JDBC utilizado para conectar a aplicação ao banco de dados MySQL;
- <strong>Lombok</strong>: Reduz o código repetitivo em classes Java, gerando automaticamente métodos como getters, setters e construtores através de anotações;
- <strong>Thymeleaf</strong>: Um motor de templates que permite criar interfaces web dinâmicas utilizando HTML com lógica embutida;
- <strong>Spring Security</strong>: Fornece suporte para autenticação e autorização, garantindo que apenas usuários autorizados possam acessar os recursos da aplicação;
- <strong>Validation</strong>: Oferece suporte para validação de dados, permitindo verificar entradas de usuários e dados antes de salvar ou processar.

<br>

## 📂 Estrutura do Projeto
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
    <th>Security</th>
    <td>Contém a configuração de segurança da aplicação, incluindo autenticação e autorização de usuários, proteção contra ataques como CSRF, CORS, e gerenciamento de permissões de acesso às APIs. Geralmente, envolve a configuração do Spring Security, integração com JWT (JSON Web Tokens) ou outras formas de autenticação, além de filtros personalizados de segurança.</td>
  </tr>
  <tr>
    <th>Templates</th>
    <td>Contém os arquivos HTML que definem a estrutura e a aparência das páginas da aplicação. Esses templates são processados por um motor de templates (ex.: Thymeleaf, FreeMarker ou Mustache), permitindo a inserção de dados dinâmicos vindos do back-end por meio de placeholders.</td>
  </tr>
  <tr>
    <th>Static</th>
    <td>Armazena os arquivos estáticos da aplicação, como CSS, imagens, ícones e scripts JavaScript. Esses recursos são enviados diretamente para o navegador sem processamento adicional pelo servidor, sendo essenciais para o estilo, comportamento e elementos visuais da aplicação.</td>
  </tr>
</table>

<br>

## 👥 Autores

| [<img loading="lazy" src="https://avatars.githubusercontent.com/u/168394448?v=4" width=115><br><sub>Lorenzo Rover</sub>](https://github.com/lorenzorover) | [<img loading="lazy" src="https://avatars.githubusercontent.com/u/168482892?v=4" width=115><br><sub>Lucas Lentz</sub>](https://github.com/lucas-lentz) | [<img loading="lazy" src="https://avatars.githubusercontent.com/u/159506794?v=4" width=115><br><sub>Kaio Gredilha Pinheiro</sub>](https://github.com/Kaio-pinheiro) | [<img loading="lazy" src="https://avatars.githubusercontent.com/u/114513409?v=4" width=115><br><sub>João Ribeiro Scharmann</sub>](https://github.com/JRScharmann) | [<img loading="lazy" src="" width=115><br><sub>Pedro Dalsenter</sub>]() |
| :---: | :---: | :---: | :---: | :---: |

<br>

### 📆 Release date: <br>
```nov/2024```
