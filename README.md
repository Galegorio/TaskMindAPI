# TaskMind API

**Uma API REST para gerenciamento de tarefas, desenvolvida com Java 17 e Spring Boot 3.**

O TaskMind API é um projeto focado na construção de uma API backend para gerenciamento e consulta de tarefas. Seu objetivo é aplicar conceitos de desenvolvimento de software, arquitetura em camadas, encapsulamento das regras de negócio, validação de dados e testes automatizados.

O projeto prioriza uma separação clara de responsabilidades, mantendo as regras de negócio independentes do framework utilizado.

---

## Funcionalidades

- **Cadastro de tarefas:** criação de tarefas com identificador, título e prioridade.
- **Controle de conclusão:** identificação de tarefas concluídas e pendentes.
- **Priorização:** organização das tarefas por prioridade, da maior para a menor.
- **Ordenação estável:** tarefas com a mesma prioridade preservam a ordem de cadastro.
- **Validação de dados:** verificação dos campos e das prioridades permitidas.
- **Tratamento de erros:** respostas padronizadas para situações como dados inválidos e identificadores duplicados.

## Tecnologias

| Tecnologia | Aplicação |
|---|---|
| Java 17 | Linguagem principal |
| Spring Boot 3 | Framework backend |
| Maven | Gerenciamento de dependências e build |
| Spring Web | Construção da API REST |
| Bean Validation | Validação de entradas |
| JUnit / MockMvc | Testes automatizados |

## Arquitetura

O projeto utiliza uma organização em camadas para separar responsabilidades e facilitar a manutenção.

```text
src/
├── main/
│   └── java/com/taskmind/api/
│       ├── domain/
│       │   ├── Task
│       │   ├── PendingTasks
│       │   └── DuplicateTaskException
│       │
│       ├── service/
│       │   └── TaskService
│       │
│       └── web/
│           ├── TaskController
│           ├── TaskRequest
│           └── ApiExceptionHandler
│
└── test/
    └── java/
        └── testes de domínio, serviço e controller
```

### Separação de responsabilidades

- **Domain:** concentra os modelos e as regras de negócio, sem depender do Spring.
- **Service:** gerencia o armazenamento em memória e as operações sobre as tarefas.
- **Web:** disponibiliza os endpoints REST, valida as requisições e trata as exceções.

## Regra de priorização

Uma das regras centrais do projeto é a construção da fila de tarefas pendentes.

A consulta considera somente tarefas não concluídas e as organiza por prioridade decrescente.

Em caso de empate, a ordem original de cadastro é preservada.

**Exemplo:**

| ID | Tarefa | Prioridade | Concluída |
|---|---|---:|---|
| 102 | Enviar currículo | 5 | Não |
| 101 | Estudar Python | 3 | Não |
| 103 | Revisar código | 5 | Não |
| 104 | Ler documentação | 2 | Sim |

Resultado da consulta de pendências:

```json
[102, 103, 101]
```

A tarefa 104 não aparece porque já foi concluída.

**Complexidade:** O(n log n) em tempo e O(n) em espaço para a ordenação.

## Executando o projeto

### Pré-requisitos

- JDK 17 ou superior.
- Maven 3.9 ou superior.

Verifique as instalações:

```bash
java -version
mvn -version
```

### Clonando o repositório

```bash
git clone https://github.com/Galegorio/TaskMindAPI.git
cd TaskMindAPI
```

### Executando os testes

```bash
mvn test
```

### Iniciando a aplicação

```bash
mvn spring-boot:run
```

Por padrão, a API estará disponível em:

```text
http://localhost:8080
```

### Gerando o JAR

```bash
mvn package
```

Executando o artefato gerado:

```bash
java -jar target/taskmind-api-1.0.0.jar
```

### Alterando a porta

A aplicação permite configurar a porta por meio da variável de ambiente `PORT`.

**Windows (CMD):**

```cmd
set PORT=9090
mvn spring-boot:run
```

**Linux / macOS:**

```bash
export PORT=9090
mvn spring-boot:run
```

## Exemplo de utilização

Criando uma tarefa:

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"id":102,"title":"Enviar currículo","priority":5}'
```

Consultando as tarefas pendentes:

```bash
curl http://localhost:8080/api/tasks/pending
```

Para consultar todos os endpoints, parâmetros, códigos HTTP e formatos de erro, acesse a [documentação da API](docs/API.md).

## Decisões de engenharia

Algumas decisões foram tomadas para priorizar simplicidade, previsibilidade e facilidade de manutenção.

- **Regras de negócio independentes:** a lógica de priorização é implementada no domínio, permitindo testes sem inicializar o Spring.
- **Ordenação estável:** o algoritmo preserva a ordem de cadastro em tarefas com prioridades iguais.
- **Armazenamento em memória:** adotado para manter o escopo inicial simples. O `TaskService` concentra as operações, permitindo uma futura substituição por persistência em banco de dados.
- **Tratamento consistente de erros:** respostas JSON com códigos HTTP específicos, incluindo `422` para validações e `409` para identificadores duplicados.

## Testes

O projeto possui testes automatizados distribuídos entre as principais camadas:

- Testes de domínio.
- Testes de serviço.
- Testes dos endpoints utilizando MockMvc.

A execução pode ser realizada com:

```bash
mvn test
```

## Limitações atuais

- Os dados são armazenados em memória e não persistem após o encerramento da aplicação.
- O projeto possui um escopo inicial focado no gerenciamento e na priorização de tarefas.
- Ainda não possui integração com banco de dados ou autenticação de usuários.

## Projeto relacionado

O TaskMind faz parte de um conjunto de projetos voltados ao desenvolvimento de soluções backend.

- **TaskMind Logistics:** projeto independente desenvolvido em Python, com foco em gerenciamento e distribuição de entregas, algoritmos de grafos e otimização logística.

[Conheça o TaskMind Logistics](https://github.com/Galegorio/TaskMindLogistics)

---

**Desenvolvido por [Galegorio](https://github.com/Galegorio).**

Projeto desenvolvido para estudo, prática e aplicação de conceitos de Engenharia de Software e desenvolvimento backend.
