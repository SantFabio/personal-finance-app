# Proposta de Arquitetura do Back-end: Personal Finance App

Com base na análise do design fornecido (`design_projeto.pdf`), o aplicativo é um sistema completo de gestão de finanças pessoais. Ele permite aos usuários gerenciar saldos, orçamentos, contas recorrentes (bills), cofrinhos de economia (pots) e transações (receitas e despesas).

## 1. Stack Tecnológico Sugerido
Dado o seu histórico de projetos, recomendo seguir com o stack consolidado:
*   **Linguagem/Framework:** Java com Spring Boot
*   **Banco de Dados:** PostgreSQL
*   **Segurança/Autenticação:** Spring Security com JWT (JSON Web Tokens)
*   **ORM e Migrations:** Spring Data JPA (Hibernate) e Flyway (ou Liquibase) para versionamento do banco.
*   **Utilitários:** Lombok, MapStruct (para mapeamento DTO) e Bean Validation.

## 2. Modelagem de Dados (Entidades Principais)

A estrutura relacional precisará das seguintes entidades:

*   **User (Usuário):**
    *   `id` (UUID), `name`, `email` (unique), `password_hash`, `created_at`.
*   **Transaction (Transação):**
    *   `id`, `user_id` (FK), `recipient_sender` (nome do recebedor/pagador), `amount` (Decimal - valores positivos para entrada, negativos para saída), `transaction_date` (Timestamp), `category` (Enum: *Entertainment, Bills, Groceries, Dining Out, Transportation, Personal Care, General, Lifestyle, Shopping, etc.*), `created_at`.
*   **Budget (Orçamento):**
    *   `id`, `user_id` (FK), `category` (Enum - Único por usuário para cada categoria), `maximum_spend` (Decimal), `color_tag` (Enum/String: Green, Yellow, Cyan, etc.), `created_at`.
*   **Pot (Cofrinho de Economia):**
    *   `id`, `user_id` (FK), `name`, `target_amount` (Decimal), `total_saved` (Decimal), `color_tag` (Enum/String), `created_at`.
*   **Recurring Bill (Conta Recorrente):**
    *   `id`, `user_id` (FK), `title`, `amount` (Decimal), `due_day_of_month` (Integer, 1-31), `created_at`. No front-end, ela precisa de lógica para saber se já foi "paga" no mês vigente (pode ser inferido via junção com Transactions ou flag mensal).

## 3. Regras de Negócio Importantes

1.  **Cálculo de "Current Balance" (Saldo Atual):**
    O saldo disponível é a soma de todas as receitas (Transactions positivas) menos todas as despesas (Transactions negativas).
    *Atenção:* Ao adicionar dinheiro a um "Pot" (Cofrinho), o design afirma explicitamente: *"As soon as you add this money, it will be deducted from your current balance"*. Portanto, transições para um Pot devem gerar uma Transaction negativa de saída categorizada como poupança, ou o Saldo Atual deve ser formulado deduzindo a soma de todos os `total_saved` dos Pots.
2.  **Cálculo de Orçamentos (Budgets):**
    Quando um usuário lista um *Budget* (ex: Dining Out = $75 limite), a API deve somar as despesas das `Transactions` dessa respectiva categoria dentro do mês corrente para retornar o valor gasto (`spent`) e o valor restante (`free`).
3.  **Contas Recorrentes (Recurring Bills):**
    A aba exibe o "Total Bills", "Paid Bills", "Total Upcoming" e "Due Soon". O back-end precisará cruzar a data de vencimento (`due_day_of_month`) com as transações do mês atual para o recebedor específico para determinar se estão *Paid* ou *Upcoming*.

## 4. Estrutura de Endpoints Recomendada

*   **Auth (Acesso Público)**
    *   `POST /api/auth/login` (Gera o Token JWT)
    *   `POST /api/auth/register` (Cria a conta)
*   **Dashboard Overview**
    *   `GET /api/overview`-> Retorna um objeto consolidado contendo `current_balance`, `income`, `expenses`, totais sumarizados dos pots, budgets e recurring bills, além de um mini array com as 5 transações mais recentes. (Ideal para evitar múltiplas chamadas na Home).
*   **Transactions**
    *   `GET /api/transactions` (Aceita paginação, busca por texto `q`, filtro por `category` e ordenação `sort`).
    *   REST padrão (`POST`, `PUT`, `DELETE`).
*   **Budgets**
    *   `GET /api/budgets` (Deve retornar os budgets enriquecidos com o quanto já foi gasto (`spent`) no mês atual e as 3 transações mais recentes de cada orçamento).
    *   REST padrão (`POST`, `PUT`, `DELETE`).
*   **Pots**
    *   `GET /api/pots`
    *   REST padrão (`POST`, `PUT`, `DELETE`).
    *   `POST /api/pots/{id}/add-money` -> Incrementa o `total_saved` e debita do saldo geral (cria transaction de débito).
    *   `POST /api/pots/{id}/withdraw` -> Decrementa o `total_saved` e credita ao saldo geral.
*   **Recurring Bills**
    *   `GET /api/recurring-bills` -> Retorna os totais resumidos (Paid, Upcoming, Due soon) e a lista de bills com status.
