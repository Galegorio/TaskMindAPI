TaskMind API

API inteligente para gerenciamento de tarefas, eventos e operações através de linguagem natural.

Sobre o projeto

O TaskMind é um projeto de API desenvolvido com o objetivo de criar uma plataforma de gerenciamento inteligente capaz de interpretar comandos em linguagem natural e transformá-los em ações estruturadas.

A ideia é permitir que o usuário interaja com o sistema de maneira mais natural, sem precisar realizar manualmente todas as etapas de criação, organização e atualização de tarefas e compromissos.

Por exemplo:

"Amanhã às 9h chega o caminhão da empresa X com a mercadoria do pedido 39482."

O sistema deverá ser capaz de interpretar as informações relevantes dessa frase e transformá-las em uma operação estruturada dentro da plataforma.

Objetivo

O objetivo principal do TaskMind é unir:

API REST
Inteligência artificial
Gerenciamento de tarefas
Eventos e compromissos
Operações logísticas
Integrações com serviços externos
Interação através de linguagem natural

A API será desenvolvida de forma independente de uma aplicação específica, permitindo que diferentes clientes possam utilizá-la.

Um aplicativo web será desenvolvido posteriormente como uma das interfaces de demonstração da API.

Conceito

O TaskMind não pretende ser apenas uma lista de tarefas.

A proposta é criar uma plataforma capaz de representar diferentes tipos de atividades e operações do cotidiano, como:

Tarefas
Reuniões
Visitas
Compromissos
Entregas
Recebimento de mercadorias
Retiradas
Transferências
Outras operações

A inteligência artificial será responsável por interpretar comandos em linguagem natural e convertê-los em ações estruturadas.

Exemplo

Entrada:

Marca uma reunião com João para sexta às 15h.

Interpretação esperada:

{
  "intent": "CREATE_EVENT",
  "event_type": "MEETING",
  "title": "Reunião com João",
  "date": "2026-09-25",
  "start_time": "15:00"
}

A API então será responsável por validar essas informações e executar a operação solicitada.

Arquitetura planejada

A arquitetura inicial seguirá o conceito de API-first:

             ┌──────────────────┐
             │    Web App       │
             │    Mobile App    │
             │  Outros Clientes │
             └────────┬─────────┘
                      │
                      ▼
             ┌──────────────────┐
             │   TaskMind API   │
             │                  │
             │ Autenticação     │
             │ Tarefas          │
             │ Eventos          │
             │ Operações        │
             │ Inteligência     │
             │ Integrações      │
             └────────┬─────────┘
                      │
          ┌───────────┼───────────┐
          ▼           ▼           ▼
     PostgreSQL       IA      Integrações
                              externas

A aplicação cliente não será responsável pela lógica principal do sistema. Ela consumirá a API através de requisições HTTP.

Inteligência artificial

Um dos principais conceitos do TaskMind será separar a interpretação feita pela inteligência artificial da execução das ações.

O fluxo planejado é:

Linguagem natural
       ↓
Interpretação
       ↓
Intenção estruturada
       ↓
Validação
       ↓
Execução

Dessa forma, a inteligência artificial não terá controle direto sobre o banco de dados ou sobre as regras do sistema.

A API será responsável por validar e executar as ações solicitadas.

Tecnologias planejadas
Backend
Java
Spring Boot
Spring Data JPA
Hibernate
Maven
Banco de dados
PostgreSQL
API
REST
JSON
OpenAPI / Swagger
Autenticação
JWT
OAuth 2.0
Inteligência artificial

A integração com modelos de inteligência artificial será feita através de uma camada de abstração, permitindo que diferentes provedores ou modelos possam ser utilizados no futuro.

Integrações planejadas
Google Tasks
Google Calendar
Outros serviços externos futuramente
Funcionalidades planejadas

O projeto ainda está em desenvolvimento. Entre as funcionalidades planejadas estão:

Autenticação de usuários

Gerenciamento de tarefas

Gerenciamento de eventos

Gerenciamento de operações

Pessoas e contatos relacionados

Locais

Integração com Google Tasks

Integração com Google Calendar

Interpretação de linguagem natural

Criação e alteração de tarefas através de comandos

Detecção de conflitos de agenda

Sugestão de horários

Resumos da agenda

Sistema de notificações

Histórico de ações da IA

Interface web de demonstração

Entrada por áudio

Integração com outros serviços

Exemplo de uso futuro

O usuário poderá enviar algo como:

Amanhã às 8 chega um caminhão da empresa X
com 300 caixas. O descarregamento deve levar
aproximadamente 40 minutos.

O TaskMind deverá ser capaz de identificar informações como:

Tipo da operação
Data
Horário
Empresa
Quantidade
Tipo de mercadoria
Duração estimada

E transformar essas informações em uma operação estruturada dentro do sistema.

Status

🚧 Em desenvolvimento

O projeto encontra-se em fase inicial de planejamento e desenvolvimento.

As funcionalidades e a arquitetura descritas neste documento poderão ser alteradas conforme o projeto evoluir.

Objetivos do projeto

Além de desenvolver uma aplicação funcional, o TaskMind tem como objetivo servir como projeto de estudo e portfólio, explorando conceitos de:

Desenvolvimento de APIs
Arquitetura de software
Desenvolvimento backend
Bancos de dados
Autenticação e autorização
Integração com APIs externas
Inteligência artificial
Processamento de linguagem natural
Sistemas distribuídos e integrações
Boas práticas de desenvolvimento
Licença

Este projeto está sob a licença MIT.
