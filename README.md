# Chamados Condomínio

O seguinte projeto foi desenvolvido com o objetivo de gerenciar chamados em um ambiente de condomínio, permitindo o controle de usuários, unidades, tipos de manutenção e fluxo de atendimento.

---

## 📌 Visão Geral

O sistema permite que moradores registrem chamados relacionados a problemas em suas unidades, enquanto colaboradores e administradores gerenciam o atendimento dessas solicitações.

A aplicação foi projetada considerando cenários reais de condomínios, incluindo controle de acesso, escopo de atuação e rastreabilidade das interações.

---

## 🧱 Estrutura do Sistema

O sistema é composto pelas seguintes entidades principais:

* **User**
* **Block**
* **Unit**
* **Call**
* **Annex**
* **Status**
* **CallType**
* **Comment**

Essas entidades representam os elementos essenciais para o funcionamento do fluxo de chamados.

---

## 👥 Perfis de Usuário

O sistema possui três perfis de usuário:

### 🔹 Resident (Morador)

* Pode visualizar apenas chamados:

    * criados por ele
    * ou relacionados à unidade em que reside
* Pode:

    * Criar novos chamados
    * Anexar arquivos (opcional)
    * Comentar em seus próprios chamados
    * Visualizar detalhes dos chamados

---

### 🔹 Collaborator (Colaborador)

* Possui um **escopo de atuação** (ex: Elétrica, Hidráulica)
* Pode visualizar apenas chamados relacionados ao seu escopo
* Pode:

    * Visualizar detalhes
    * Comentar nos chamados do seu escopo
    * Filtrar chamados por status
    * Alterar status (dentro do seu escopo)

---

### 🔹 Admin (Administrador)

Possui controle total do sistema:

* Gerenciamento de usuários (CRUD)
* Gerenciamento de blocos e unidades
* Criação de:

    * Tipos de chamado
    * Status
* Visualização completa de todos os chamados
* Pode comentar e alterar qualquer chamado

---

## 🏢 Gestão de Estrutura do Condomínio

Administradores podem criar blocos informando:

* Identificação (ex: A, B, Sul)
* Quantidade de andares
* Número de unidades por bloco

A partir disso, o sistema gera automaticamente as unidades.

### 📐 Regra de geração de unidades

Exemplo:

* 3 andares
* 5 unidades por andar

Resultado:

* Térreo: 001, 002, 003...
* 1º andar: 101, 102, 103...
* 2º andar: 201, 202, 203...

---

## 📞 Fluxo de Chamados

### 🔹 Criação

Ao criar um chamado, o usuário informa:

* Título
* Unidade
* Tipo de chamado
* Descrição
* Anexos (opcional)

O chamado inicia com status padrão: **Open**

---

### 🔹 Tipos de Chamado (CallType)

* Definidos por administradores
* Possuem:

    * Título
    * Deadline (SLA em horas)

Exemplo:

* SLA = 4.5 → 4h30min

O sistema calcula automaticamente o prazo do chamado.

---

### 🔹 Status

* Criados por administradores
* Possuem flag de **status final**

Quando um chamado atinge um status final:

* É encerrado automaticamente
* Recebe data de finalização
* Não pode mais ser alterado

---

### 🔹 Comentários

* Funcionam como histórico de interações
* Regras:

    * Resident: apenas nos próprios chamados
    * Collaborator: apenas no seu escopo
    * Admin: em todos

---

## 🔐 Controle de Acesso

Implementado com **Spring Security**, baseado em roles:

* ADMIN
* COLLABORATOR
* RESIDENT

Regras principais:

* Rotas protegidas por perfil
* Redirecionamento para login quando não autenticado
* Redirecionamento para página 403 quando sem permissão

---

## 🧠 Decisões Arquiteturais

O sistema segue princípios de **Clean Architecture**, com separação clara de responsabilidades:

### 🔹 Camadas

* **Domain**

    * Entidades e regras de negócio

* **Application**

    * Use cases
    * Gateways (interfaces)
    * Exceções de negócio

* **Infrastructure**

    * Controllers (View e API)
    * Implementações de repositório
    * Mapeamentos (Entity ↔ Domain)
    * Segurança (Spring Security)
    * Persistência (JPA)

* **Main**

    * Configuração de beans
    * Integração com Spring

---

### 🔹 Interface

* Utilização de **JSP (Java Server Pages)** para renderização
* Views localizadas em:

```plaintext
/WEB-INF/views
```

---

### 🔹 API e Controllers

* Controllers divididos em:

    * **View Controllers** → páginas JSP
    * **API Controllers** → prefixo `/api`

---

### 🔹 Persistência

* Banco relacional
* Controle de versão com **Flyway**
* Migrations responsáveis por:

    * Criação das tabelas
    * Inserção de dados iniciais

---

### 🔹 Usuário Inicial

Criado via migration:

* Email: `ryan@gmail.com`
* Senha: `ryan123`
* Perfil: ADMIN

---

## 🗄️ Diagrama do Banco de Dados

*(Inserir imagem aqui)*

---

## 🏗️ Arquitetura do Sistema

*(Inserir diagrama aqui)*

---

## 🚀 Como Executar o Projeto

### 🔹 Pré-requisitos

* Docker
* Docker Compose

---

### 🔹 Execução

```bash
docker compose up
```

---

### 🔹 Acesso

```plaintext
http://localhost:8080
```

---

### 🔹 Fluxo de Acesso

* Usuário não autenticado → redirecionado para login
* Usuário sem permissão → página 403

---

## ⚠️ Observações Importantes

* Migrations são imutáveis após execução
* Controle de acesso é centralizado
* Sistema considera cenários reais de uso em condomínio

---

## 📌 Considerações Finais

O sistema foi projetado com foco em:

* Organização
* Segurança
* Escalabilidade
* Aproximação com cenários reais

As decisões arquiteturais visam facilitar manutenção, testes e evolução futura da aplicação.
