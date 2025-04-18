# 📊 Spring Batch - Processamento de Lotes com Banco de Dados H2

Projeto desenvolvido com foco em demonstrar o uso do **Spring Batch** para o processamento em lote de dados e a persistência desses dados em um banco de dados relacional **H2 em memória**. Ideal para estudos, provas de conceito (PoCs) ou como base para sistemas de ETL em produção.

---

## 🧩 Tecnologias e Ferramentas

- [Java 23](https://openjdk.org/projects/jdk/23/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Batch](https://spring.io/projects/spring-batch)
- [Spring JDBC](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#jdbc)
- [H2 Database](https://www.h2database.com/)
- [Maven](https://maven.apache.org/)

---

## 🧠 Visão Geral

Este projeto simula um fluxo de importação de dados utilizando Spring Batch. Os dados são carregados (por exemplo, de um mock ou outro `ItemReader`), mapeados para a entidade `PersonWriterModel` e persistidos na tabela `pessoa` por meio de `JdbcBatchItemWriter`.

---

## 📁 Estrutura da Tabela `pessoa`

```sql
CREATE TABLE IF NOT EXISTS pessoa (
    id SERIAL PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    gender CHAR(1) NOT NULL
);
```

---

## 🧾 Modelo de Dados

```java
public record PersonWriterModel(
    Long id,
    String name,
    String email,
    int age,
    String cpf,
    String gender
) {}
```
---

## ▶️ Como Executar o Projeto

1. **Clone o repositório:**

```bash
git clone [https://github.com/seu-usuario/project-spring-batch.git](https://github.com/angelo7890/Spring-Batch-Project.git)
cd project-spring-batch
```

2. **Execute com Maven:**

```bash
./mvnw spring-boot:run
```

3. **Acesse o Console H2:**

- URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuário: `sa`
- Senha: 'password'

4. **Verifique os dados inseridos:**

```sql
SELECT * FROM pessoa;
```

---

## 📈 Logs de Execução

No log da aplicação, você deverá visualizar uma saída similar a:

```
Job: [SimpleJob: [name=job]] completed with the following status: [COMPLETED]
```

Isso indica que o processamento foi realizado com sucesso e os dados foram inseridos na tabela `pessoa`.

---

## 📌 Considerações

- O banco H2 está em **modo memória**, ou seja, os dados são descartados ao desligar a aplicação.
- O Spring Batch pode ser estendido para ler dados de arquivos `.csv`, bancos externos, ou até filas (ex: RabbitMQ).

---


## 👨‍💻 Autor

Desenvolvido por Angelo  
[🔗 GitHub](https://github.com/angelo7890) • [💼 LinkedIn](https://www.linkedin.com/in/angelo-rodrigues-762364286)
