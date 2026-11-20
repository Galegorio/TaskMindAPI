"""
Parte 1 - Programação: get_pending_tasks

Retorna os IDs das tarefas pendentes, ordenados da maior para a menor
prioridade, preservando a ordem original em caso de empate.
"""


def get_pending_tasks(tasks):
    """Solução principal: filtro + sorted() estável. Tempo O(n log n), espaço O(n)."""
    pending = [t for t in tasks if not t["completed"]]
    pending_sorted = sorted(pending, key=lambda t: t["priority"], reverse=True)
    return [t["id"] for t in pending_sorted]


def get_pending_tasks_bucket(tasks):
    """Alternativa com bucket sort (prioridades de 1 a 5). Tempo O(n), espaço O(n)."""
    buckets = {p: [] for p in range(5, 0, -1)}  # 5, 4, 3, 2, 1
    for t in tasks:
        if not t["completed"]:
            buckets[t["priority"]].append(t["id"])
    return [task_id for p in buckets for task_id in buckets[p]]


if __name__ == "__main__":
    tasks = [
        {"id": 101, "title": "Estudar Python",     "priority": 3, "completed": False},
        {"id": 102, "title": "Enviar currículo",   "priority": 5, "completed": False},
        {"id": 103, "title": "Fazer almoço",       "priority": 2, "completed": True},
        {"id": 104, "title": "Estudar algoritmos", "priority": 5, "completed": False},
        {"id": 105, "title": "Revisar código",     "priority": 1, "completed": False},
    ]
    original = [dict(t) for t in tasks]  # cópia para checar que não houve mutação

    for func in (get_pending_tasks, get_pending_tasks_bucket):
        # Exemplo do enunciado
        assert func(tasks) == [102, 104, 101, 105]

        # Lista vazia
        assert func([]) == []

        # Todas concluídas
        assert func([{"id": 1, "title": "a", "priority": 3, "completed": True}]) == []

        # Empates preservam a ordem original
        empate = [
            {"id": 1, "title": "a", "priority": 4, "completed": False},
            {"id": 2, "title": "b", "priority": 4, "completed": False},
            {"id": 3, "title": "c", "priority": 4, "completed": False},
        ]
        assert func(empate) == [1, 2, 3]

        # Lista original não foi modificada
        assert tasks == original

        print(f"{func.__name__}: todos os testes passaram ✔")

    print(get_pending_tasks(tasks))  # [102, 104, 101, 105]
