# language:pt

Funcionalidade: Manter Clientes
  Ator: cadastrador.
  Deve manter o cadastro dos clientes.

  Cenario: Testar pessoa com nome nulo
    Dado que eu tenha uma pessoa
    Quando eu informo um nome nulo
    Entao  o sistema lança exceção

