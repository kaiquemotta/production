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

![image](https://github.com/user-attachments/assets/e6148a78-45b5-482b-be03-e6fddb91fc25)
![image](https://github.com/user-attachments/assets/86570dc2-d622-4349-95f9-93fd76fe0027)
![image](https://github.com/user-attachments/assets/1b3d9b09-ecb8-460f-8041-9587443668a9)
