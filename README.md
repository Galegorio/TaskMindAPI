# TaskMind API

API REST de tarefas (Java 17, Spring Boot 3, Maven). Cadastra tarefas e devolve a fila de pendências: os IDs das
tarefas **não concluídas**, da **maior para a menor prioridade**, com empates na ordem em que foram cadastradas.

Cada tarefa tem `id` (inteiro), `title`, `priority` (1 a 5) e `completed`.

## Como executar (Windows, Linux ou macOS)

Pré-requisitos: JDK 17+ e Maven 3.9+ (`java -version` e `mvn -version` para conferir).

```
cd TaskMindAPI
mvn test                  # testes
mvn spring-boot:run       # sobe em http://localhost:8080
```

Para mudar a porta: `set PORT=9090` (cmd) ou `export PORT=9090` (Linux/macOS) antes de subir.
Para gerar o JAR: `mvn package` e depois `java -jar target/taskmind-api-1.0.0.jar`.

## Exemplo de uso (cmd do Windows)

```
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d "{\"id\":102,\"title\":\"Enviar curriculo\",\"priority\":5}"
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d "{\"id\":101,\"title\":\"Estudar Python\",\"priority\":3}"
curl http://localhost:8080/api/tasks/pending
```

Mais detalhes (endpoints, códigos HTTP e formato de erro) em [`docs/API.md`](docs/API.md).

## Estrutura

```
src/main/java/com/taskmind/api/
├── domain/    Task, PendingTasks (a regra de negócio), DuplicateTaskException   (sem Spring)
├── service/   TaskService: armazenamento em memória e consultas
└── web/       TaskController, TaskRequest (validação), ApiExceptionHandler
src/test/java/                testes de domínio, serviço e controller (MockMvc)
docs/referencia-python/       implementação original do exercício, em Python
```

## Decisões

- **Regra isolada do framework:** `PendingTasks.ids` é uma função pura, testável sem subir o Spring.
- **Estabilidade nos empates:** `Stream.sorted()` é estável em streams ordenados, e o comparador invertido não inverte empates.
  Complexidade O(n log n) em tempo e O(n) em espaço.
- **Armazenamento em memória:** simples e suficiente para o exercício; o `TaskService` é o ponto a trocar por JPA/banco.
- **Erros previsíveis em JSON:** 422 para validação, 409 para ID duplicado (mesmo estilo do TaskMind Logistics).
