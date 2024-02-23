# Java Spring Boot RESTful API - Junior Case
### Tecnologias Principais: Java, MySql, Docker, Swagger e JaCoCo.
<br>

# Banco de Dados
### Foi utilizado o Flyway como ferramenta de IaC para provisionar a Infraestrutura do Banco de Dados.
<br>

# JaCoCo
![image](https://github.com/AL3ZIN/Junior-Case-Spring-Boot-Restful-Api/blob/main/Java-Spring-Boot-RESTful-Api/assets/jaCoCo.png)

Foi utilizado JaCoCo no projeto para mapear a cobertura de testes, uma vez que o JaCoCo nos mostra visualmente em porcentagem a cobertura de testes do nosso codigo, baseado em quantas linhas ja foram testadas.

# HATEOAS
![image](https://github.com/AL3ZIN/Junior-Case-Spring-Boot-Restful-Api/blob/main/Java-Spring-Boot-RESTful-Api/assets/Hateoas1.png)

Foi utilizado o Hateoas no projeto para trazer uma resposta mais dinâmica as requisições da api, pois o HATEOAS depois de implementado faz com que as requisições voltem com seu link de detalhamento, ou seja, mostra onde pode ser consultado cada objeto retornado na requisição.
<br>

# Endpoints de Carros Cadastrados

![image](https://github.com/AL3ZIN/Junior-Case-Spring-Boot-Restful-Api/blob/main/Java-Spring-Boot-RESTful-Api/assets/requisicoes.png)

### GET - /api/carro/{id} | Consultar um cadastro.
### GET - /api/carro | Listar todos os cadastros.
### POST - /api/carro | Efetuar um cadastramento.
### PUT - /api/carro | Atualizar um dos dados do cadastro (status).
### DELETE - /api/carro/{id} |  Efetuar a exclusão logica do cadastro.
<br>

# Docker
![image](https://github.com/AL3ZIN/Junior-Case-Spring-Boot-Restful-Api/blob/main/Java-Spring-Boot-RESTful-Api/assets/docker.png)

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
Dessa forma, a aplicação já estará funcionando e pode ser executado os testes automatizados da maneira como preferir.

## Considerações Finais

Além das tecnologias principais documentadas anteriormente, este projeto também adota várias metodologias e práticas recomendadas para garantir qualidade, escalabilidade e manutenibilidade do código. Entre elas, destacam-se:

- **SOLID:** A aplicação tende sempre a seguir os princípios SOLID para garantir que nosso código seja facilmente mantido e estendido.
- **DDD (Domain-Driven Design):** Utilizei dos conceitos de Domain-Driven Design para alinhar o design do nosso software com o domínio do negócio, facilitando a comunicação e compreensão.
- **IoC (Inversão de Controle):** Adotei o princípio de Inversão de Controle para reduzir o acoplamento entre os módulos do nosso software.
- **DI (Injeção de Dependência):** Utilizei da Injeção de Dependência para gerenciar as dependências entre os objetos, tornando nosso código mais modular e testável.
- **Padrão Strategy:** Implementei tambem o padrão Strategy para permitir a escalabilidade de validações, tornando nosso software mais flexível.
- **Exception Handlers:** Utilizei de manipuladores de exceções para tratar erros de forma centralizada, melhorando a robustez e a usabilidade do nosso software.
- **Retorno de ResponseEntitys Detalhados:** Garanti que nossas respostas HTTP sejam detalhadas e informativas, melhorando a comunicação com os consumidores da API.

Essas práticas e metodologias são fundamentais para a construção de um software robusto, escalável e fácil de manter. Continuaremos a adotá-las e aperfeiçoá-las à medida que o projeto evolui.

