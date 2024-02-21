# Java Spring Boot RESTful API - Junior Case
### Tecnologias: Java, MySql, Docker, Swagger e JaCoCo.
<br>

# Banco de Dados
### Foi utilizado o Flyway como ferramenta de IaC para provisionar a Infraestrutura do Banco de Dados.
<br>

# Endpoints de Carros Cadastrados

![image](https://github.com/AL3ZIN/Junior-Case-Spring-Boot-Restful-Api/blob/main/Java-Spring-Boot-RESTful-Api/assets/Screenshot_1.png)

### GET - /api/carro/{id} | Consultar um cadastro.
### GET - /api/carro | Listar todos os cadastros.
### POST - /api/carro | Efetuar um cadastramento.
### PUT - /api/carro | Atualizar um dos dados do cadastro (status).
### DELETE - /api/carro/{id} |  Efetuar a exclusão logica do cadastro.
<br>


# Docker
![image](https://github.com/AL3ZIN/Junior-Case-Spring-Boot-Restful-Api/assets/93688391/)

Tanto a API quanto o Banco de Dados MySql foram Dockerizados durante o desenvolvimento e utiliza uma Stack do <b>Docker Compose</b> para provisionamento automático.
<br><br>
Para testes e melhor Documentação da API, adicionei o Swagger.
<br>
<b>Link do Swagger quando a aplicação estiver executando: http://localhost/swagger-ui/index.html#/</b>
<br><br>

# Execução
Para rodar o projeto, após clonado o repositório, no diretório onde se encontra o arquivo <b>pom.xml</b> execute o seguinte comando:
<br>
<b>mvn clean package -DskipTests</b>
<br>
Em seguida, vá ao diretório onde contém o arquivo <b>docker-compose.yml</b> e execute o seguinte comando:
<br>
<b>docker compose up -d --build</b>
<br>
Dessa forma, a aplicação já estará funcionando e pode ser executado os testes automatizados da maneira como preferir :).
