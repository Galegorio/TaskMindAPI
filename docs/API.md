# Referência da API

Base: `http://localhost:8080/api`. Corpo e respostas em JSON.

| Método | Caminho | Descrição | Sucesso |
|---|---|---|---|
| GET | `/health` | Verifica se a API responde | 200 `{"status":"ok"}` |
| POST | `/tasks` | Cadastra uma tarefa | 201 com a tarefa |
| GET | `/tasks` | Lista as tarefas na ordem de cadastro | 200 |
| GET | `/tasks/pending` | IDs das pendentes, por prioridade decrescente | 200 `[102,104,101,105]` |

## POST /tasks

```json
{"id": 101, "title": "Estudar Python", "priority": 3, "completed": false}
```

- `id`: obrigatório, inteiro, único.
- `title`: obrigatório, não vazio.
- `priority`: obrigatório, inteiro de 1 a 5.
- `completed`: opcional, padrão `false`.

## Erros

| Situação | HTTP | Corpo |
|---|---|---|
| Campo ausente ou inválido | 422 | `{"error":"ValidationError","messages":["priority: deve ser menor ou igual a 5"]}` |
| ID já cadastrado | 409 | `{"error":"DuplicateTaskException","message":"já existe uma tarefa com id 101"}` |
| JSON malformado ou tipo errado (ex.: `"priority":"abc"`) | 400 | resposta padrão do Spring |

## Exemplo completo

Cadastrando as tarefas do enunciado (101 a 105, sendo 103 concluída) e consultando `/tasks/pending`,
o resultado é `[102,104,101,105]`.
