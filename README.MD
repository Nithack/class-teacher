# Class Teacher API

## Sobre

A Class Teacher API é um serviço criado para permitir que professores monitorem as informações de sala de aula. Esta API
foi desenvolvida usando Spring Boot 3.1, Redis para cache dos dados, MongoDB como banco de dados, e Docker e Docker
Compose para contenização e orquestração do ambiente.

## Endpoints

### Autenticação

* **POST /auth/register**: Registra um novo professor.
* **POST /auth/login**: Faz o login com email e senha.

### Professores

* **GET /teacher/classroom**: Obtém todas as salas de aula de um professor.

### Secretário

* **GET /secretary/teacher/unapproved**: Obtém todos os professores que ainda não foram aprovados.
* **POST /secretary/teacher/approve/{id}**: Aprova um professor por id.
* **POST /secretary/classroom**: Cria uma nova sala de aula.
* **PUT /secretary/classroom/{id}**: Atualiza uma sala de aula existente por id.

## Instalação

Requisitos:

* Docker e Docker Compose
* Java 17
* Gradle

Para rodar a aplicação:

1. Clone o repositório do Github:

```bash
git clone https://github.com/Nithack/class-teacher-api.git
```

2. Entre na pasta do projeto:

```bash
cd class-teacher-api
```

3. Crie um arquivo `.env` na raiz do projeto com todas as variáveis de ambiente necessárias.

4. Inicie o ambiente Docker:

```bash
docker-compose up -d
```

5. Rode a aplicação com o Gradle:

```bash
./gradlew bootRun
```

## Contato

Para mais informações, por favor entre em contato com [Nithack](mailto:andreynithack@gmail.com) ou visite
o [repositório do projeto no Github](https://github.com/Nithack).

## Licença

Esta API está sob a licença MIT. Para mais detalhes, veja
a [licença](https://github.com/Nithack/class-teacher-api/blob/main/LICENSE) no repositório do projeto.
