# Arquitetura de Microserviços - Projeto Acadêmico

## Visão Geral

Este projeto foi desenvolvido como parte da disciplina de Arquitetura de Software da Universidade Católica do Salvador (UCSAL).

Na primeira unidade da disciplina, o sistema foi implementado utilizando uma **arquitetura monolítica**, onde uma aplicação front-end se comunicava com uma única API backend.

Para a segunda unidade, o objetivo foi **analisar a API monolítica e reestruturá-la em uma arquitetura de microserviços**, identificando domínios independentes e separando-os em serviços autônomos.

A aplicação front-end não sofreu alterações estruturais, sendo necessário apenas atualizar o endpoint da API para se comunicar com a nova arquitetura.

---

## Arquitetura do Sistema

O sistema segue uma **arquitetura baseada em microserviços**, composta por:

- API Gateway  
- Service Discovery  
- Microserviços independentes  
- Banco de dados por serviço  

Todos os serviços foram desenvolvidos utilizando **Java e Spring Boot**.

---

## Microserviços

O projeto é composto pelos seguintes serviços:

### API Gateway
Atua como o **ponto único de entrada** para todas as requisições dos clientes.  
Responsável pelo roteamento, segurança e comunicação com os microserviços.

### Discovery Service
Implementa **Service Discovery utilizando Eureka**, permitindo que os serviços se registrem e se descubram dinamicamente.

### Auth Service
Responsável pela **autenticação e autorização**, realizando a geração e validação de **tokens JWT**.

### Employee Service
Responsável pelo gerenciamento dos **dados de funcionários**.

### Institution Service
Gerencia as **informações das instituições**.

### Classroom Service
Responsável pelo gerenciamento das **salas**.

### Reserve Service
Responsável pelo controle de **reservas de salas**.

---

## Tecnologias Utilizadas

- Java  
- Spring Boot  
- Spring Cloud  
- Spring Cloud Gateway  
- Netflix Eureka  
- JWT (JSON Web Token)  
- PostgreSQL  
- Maven  

---

## Princípios de Arquitetura

O sistema foi desenvolvido seguindo os principais **princípios de microserviços**:

- Baixo Acoplamento  
- Alta Coesão  
- Autonomia dos Serviços  
- Reusabilidade  
- Abstração  
- Serviços Stateless  

Cada microserviço possui uma **responsabilidade única** e seu próprio **banco de dados PostgreSQL**, garantindo independência entre os serviços.

---

## Comunicação entre Serviços

- APIs REST  
- Registro e descoberta de serviços com **Eureka**  
- Roteamento de requisições via **API Gateway**  
- Autenticação baseada em **JWT**  

---

## Banco de Dados

Cada microserviço utiliza seu próprio banco de dados **PostgreSQL**, seguindo o padrão **Database per Service**, garantindo isolamento e escalabilidade.
