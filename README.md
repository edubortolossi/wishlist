# Projeto de Lista de Desejos

O objetivo é desenvolver um serviço HTTP resolvendo a
funcionalidade de Wishlist do cliente. Esse serviço deve atender
os seguintes requisitos:
- Adicionar um produto na Wishlist do cliente;
- Remover um produto da Wishlist do cliente;
- Consultar todos os produtos da Wishlist do cliente;
- Consultar se um determinado produto está na Wishlist do
  cliente;

#Como executar

Executar o projeto Spring a classe WishlistApplication.java

#O que foi Utilizado?

Utilizei o Spring Framework, o banco de dados utilizei o MongoDB, utilizei o banco embarcado tanto para aplicação quanto para os testes unitarios.

#O que foi feito

Foi criado em padrão REST, 4 endpoints (POST, DELET, GET, GET) para o exercicio, adicionei um campo userIdentification como header para caso mais de um cliente criasse uma lista de desejos pode ser considerado o id do usuario ou CPF.
Foi criado testes unitarios considerando cobertura de código e possiveis cenarios de utilização.


