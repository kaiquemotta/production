Feature: Obter todas as ordens de produção

  Scenario: Buscar todas as ordens de produção com sucesso
    Given que o sistema possui ordens de produção cadastradas
    When uma requisição GET é feita para "/production-orders"
    Then a resposta deve ter o status 200
    And a resposta deve conter a lista de ordens de produção
