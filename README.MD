# Class Teacher API

## Sobre

A Class Teacher API é um serviço criado para permitir que professores monitorem as informações da sala de aula. Esta API
foi desenvolvida usando Spring Boot 3.1, MongoDB como banco de dados, e Docker e Docker Compose para a contenização e
orquestração do ambiente.

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

### Swagger UI

Para explorar e testar os endpoints da API, você pode utilizar a Swagger UI através do seguinte link após iniciar a
aplicação:

```
http://localhost:3000/swagger-ui/index.html
```

### Configurações

```bash
JWT_INSURER: class-teacher
JTW_TOKEN_KEY: qwertyuiopasdfghjklzxcvbnm123456
JWT_EXPIRATION: 3600000
MONGO_DB_URI: mongodb://class-teacher:class-teacher@mongodb:27017/class-teacher?authSource=admin
MONGO_DB_DATA_BASE: class-teacher
MANAGER_SECRETARY_USER: test
MANAGER_SECRETARY_PASSWORD: test
APPLICATION_PORT: 3000
```

Para logar como secretário, utilize as seguintes credenciais:

```json
{
  "user": "test",
  "password": "test"
}
```

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

3. Inicie o ambiente Docker:

```bash
docker-compose up -d
```

## Testes

Para executar os testes utilizando o Docker Compose, siga os passos abaixo:

1. Certifique-se que o Docker e Docker Compose estão corretamente instalados no seu ambiente.

2. No terminal, navegue até a pasta do projeto e execute o seguinte comando para iniciar os serviços necessários:

```bash
docker-compose -f docker-compose-test.yml up -d
```

3. Execute os testes utilizando via interface do IntelliJ:

```bash
gradle>>Tasks>>verification>>test
```

3.1 Execute os testes utilizando o Gradle:

```bash
./gradlew test
```

## Contato

Para mais informações, por favor entre em contato com [Nithack](mailto:andreynithack@gmail.com) ou visite
o [repositório do projeto no Github](https://github.com/Nithack).

## Licença

Esta API está sob a licença MIT. Para mais detalhes, veja
a [licença](https://github.com/Nithack/class-teacher-api/blob/main/LICENSE) no repositório do projeto.