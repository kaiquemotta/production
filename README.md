# Microserviço de Production Orders

Este microserviço é responsável pela gestão de pedidos de produção. Ele é desenvolvido em Java utilizando o Spring Boot e expõe uma API REST para a criação e consulta de pedidos de produção. O microserviço se integra com o RabbitMQ para a troca de mensagens, especificamente para a fila de leiteira de uma filial.

## Requisitos

- Java 21 ou superior
- RabbitMQ em execução (para a integração com a fila de leiteira)
- Maven (para compilar e executar o projeto)
  
## Configuração

### Porta
Este microserviço será executado na porta `8080` por padrão.

### Perfil Local
O microserviço deve ser executado com o perfil `local`. Isso pode ser configurado ao iniciar o aplicativo com o seguinte comando:

```bash
mvn spring-boot:run -Dspring.profiles.active=local

## Evidencias.
https://github.com/kaiquemotta/production/issues/7#issue-2861927898
