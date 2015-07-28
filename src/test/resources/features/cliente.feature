# language:pt

Funcionalidade: Manter Clientes
  Ator: Atendente.
  Deve manter o cadastro dos clientes.

  Cenario: Cliente com nome vazio
    Dado que tenha um cliente
    Quando e informado o nome vazio
    Entao  o sistema lança exceção com a mensagem :O valor do campo Nome não foi informado.

  Cenario: Cliente com nome pequeno
    Dado que tenha um cliente
    Quando e informado um nome menor que 10 caracteres
    Entao  o sistema lança exceção com a mensagem :O valor do campo Nome esta inválido, deverá ter no mínimo 10 caracteres.

  Cenario: Cliente com nome grande
    Dado que tenha um cliente
    Quando e informado um nome maior que 80 caracteres
    Entao o sistema lança exceção com a mensagem :O valor do campo Nome esta inválido, deverá ter no máximo 80 caracteres.

  Cenario: Excluir cliente com débitos
    Dado que tenha um cliente
    Quando o mesmo possui débitos
    Entao o sistema lança a exceção com a mensagem :Operação não permitida, não e possível excluir um cliente com débitos.

