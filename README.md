# CineControl
# 🎬 Cine Control

Cine Control é um projeto Java para gerenciamento de um cinema, seguindo o padrão de arquitetura **MVC (Model–View–Controller)** e utilizando **DAO** para acesso a dados. O sistema organiza filmes, salas e sessões, servindo como base para aplicações acadêmicas ou projetos de estudo em Java.

---

## 👥 Autoria

Este projeto foi desenvolvido **em dupla**, como parte de um trabalho acadêmico/estudo prático em Java.

> *Maria Luisa Sanches e Pedro Prado*

---

## 📌 Funcionalidades

* Cadastro e gerenciamento de **Filmes**
* Cadastro e gerenciamento de **Salas**
* Cadastro e gerenciamento de **Sessões**
* Separação clara de responsabilidades usando MVC
* Camada DAO para acesso e persistência de dados

---

## 🧱 Estrutura do Projeto

A estrutura do projeto está organizada da seguinte forma:

```
Cinema
├── controller
│   ├── FilmeController.java
│   ├── PrincipalController.java
│   ├── SalaController.java
│   └── SessaoController.java
│
├── model
│   ├── Filme.java
│   ├── Sala.java
│   └── Sessao.java
│
├── model.dao
│   ├── ConnFactory.java
│   ├── DaoFactory.java
│   ├── InterfaceDao.java
│   ├── FilmeDao.java
│   ├── SalaDao.java
│   └── SessaoDao.java
│
├── start
│   └── App.java
│
└── module-info.java
```

---

## ▶️ Execução do Projeto

1. Abra o projeto em uma IDE Java (NetBeans, IntelliJ ou Eclipse)
2. Certifique-se de que o JDK esteja configurado corretamente
3. Execute a classe:

   ```
   start.App
   ```

---

## 🛠 Tecnologias Utilizadas

* Java
* JDBC (para acesso a dados)
* Padrões de Projeto: MVC e DAO

---

## 📚 Objetivo do Projeto

O Cine Control tem como objetivo praticar:

* Organização de projetos Java
* Aplicação de padrões de projeto
* Separação de camadas (Model, Controller e DAO)
* Boas práticas de programação orientada a objetos

---

## 📄 Licença

Projeto desenvolvido para fins educacionais.
