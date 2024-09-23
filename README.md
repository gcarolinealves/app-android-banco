# Projeto Android

## Banco

A aplicação Banco está parcialmente implementada no momento. Existem vários métodos e trechos de códigos com anotações lembrando que faltam implementar alguns detalhes da aplicação.

1. Na classe `ContasActivity`, já há um `RecyclerView` que usa um `Adapter` para mostrar uma lista de contas, mas os dados ainda não estão sendo recuperados do banco de dados. Dica: use o atributo `contas` (com tipo `LiveData<List<Conta>>`) de `ContaViewModel` para fazer isto;

2. Na classe `ContaViewHolder`, a imagem que é mostrada na lista de contas não é alterada caso o saldo esteja negativo. Inclua o código correspondente na função `bindTo`. 

3. Na classe `ContaViewHolder`, ajuste o código do listener dos botões de editar conta e remover conta para que a funcionalidade seja implementada;

4. Na classe `ContaViewHolder`, o `Intent` criado para enviar o usuário para a tela `EditarContaActivity`, não inclui o número da conta, informação essencial para recuperar os dados da conta na tela a ser aberta;

5. Na classe `AdicionarContaActivity`, inclua a funcionalidade de validar as informações digitadas (ex.: nenhum campo em branco, saldo é um número) antes de criar um objeto `Conta` no banco de dados. Implemente também o código que usa `ContaViewModel` para armazenar o objeto no banco de dados;

6. Na classe `ContaDAO`, inclua métodos para atualizar e remover contas no banco de dados, além de três métodos para buscar (1) pelo número da conta, (2) pelo nome do cliente e (3) pelo CPF do cliente;

7. Na classe `ContaRepository`, implemente o corpo dos métodos para atualizar e remover contas no banco de dados, além dos métodos para buscar pelo número da conta, pelo nome do cliente e pelo CPF do cliente. Estes métodos devem usar os métodos criados na classe `ContaDAO` no passo anterior;

8. Na classe `ContaViewModel`, inclua métodos para atualizar e remover contas no banco de dados, além de um método para buscar pelo número da conta. Estes métodos devem usar os métodos criados na classe `ContaRepository` no passo anterior;

9. Na classe `EditarContaActivity`, inclua a funcionalidade de recuperar as informações da conta de acordo com o número passado pelo `Intent` recebido pela `Activity`. Atualize os campos do formulário de acordo;

10. Na classe `EditarContaActivity`, inclua a funcionalidade de validar as informações digitadas (ex.: nenhum campo em branco, saldo é um número) antes de atualizar a Conta no banco de dados. Implemente também o código que usa `ContaViewModel` para armazenar o objeto atualizado no banco de dados;

11. Na classe `EditarContaActivity`, implemente o código que usa `ContaViewModel` para remover o objeto do banco de dados;

12. Na classe `BancoViewModel`, inclua métodos para realizar as operações de transferir, creditar, e debitar, bem como métodos para buscar pelo número da conta, pelo nome do cliente e pelo CPF do cliente. Estes métodos devem usar os métodos de `ContaRepository` criados em passos anteriores;

13. Nas classes `DebitarActivity`, `CreditarActivity`, e `TransferirActivity`, implementar validação dos números das contas e do valor da operação, antes de efetuar a operação correspondente à tela. Você é livre para usar outro widget se preferir, como `Spinner` ou `AutoCompleteTextView`, por exemplo;

14. Na classe `PesquisarActivity`, implementar o código que faz busca no banco de dados de acordo com o tipo de busca escolhido pelo usuário (ver `RadioGroup` `tipoPesquisa`);

15. Na classe `PesquisarActivity`, ao realizar uma busca, atualizar o `RecyclerView` com os resultados da busca na medida que encontrar algo;

16. Na classe `MainActivity`, mostrar o valor total de dinheiro armazenado no banco na tela principal. Este valor deve ser a soma de todos os saldos das contas armazenadas no banco de dados. Atenção para a possibilidade das contas terem saldo negativo (ou não, dependendo de sua escolha).

17. Na classe `TransacaoViewHolder`, o valor da transação está sempre sendo exibido em azul. Altere o código para que o valor da transação seja exibido em vermelho, no caso de transações de débito.

18. Na classe `TransacaoDAO`, inclua métodos para buscar transações pelo (1) número da conta, (2) pela data, filtrando pelo tipo da transação (crédito, débito, ou todas);

19. Na classe `TransacaoRepository`, implemente o corpo dos métodos para buscar transações pelo (1) número da conta, (2) pela data, filtrando pelo tipo da transação (crédito, débito, ou todas). Estes métodos devem usar os métodos criados na classe `TransacaoDAO` no passo anterior;

20. Na classe `TransacaoViewModel`, inclua métodos para realizar as buscas, usando os métodos criados na classe `ContaRepository` no passo anterior;

21. Na classe `TransacoesActivity`, implementar o código que faz busca no banco de dados de acordo com o tipo de busca escolhido pelo usuário (ver `RadioGroup` `tipoPesquisa`) e exibe (atualiza) a lista na tela. O `RecyclerView` deve mostrar todas as transações inicialmente;


## Daqui em diante - Opcional

1. Incluir as telas para gerenciamento de Clientes e ajustar implementação do BD para refletir relacionamento em que toda `Conta` tem apenas um `Cliente` associado, mas um `Cliente` pode ter mais de uma `Conta` - neste caso, ao adicionar uma conta tem que verificar se o `Cliente` é válido (se é um `Cliente` já existente no banco);

2. Extrair as strings e traduzir a aplicação para outra língua;

3. Fazer melhorias de UI na aplicação.


## ATENÇÃO


- Entregue o arquivo `.zip` com a pasta do projeto, após dar um "_Clean Project_" na IDE (para diminuir o tamanho);
- Inclua comentários no código explicando o que faz cada método criado por você na implementação da aplicação;
- Escreva também um arquivo `README.md` ou um Google Docs explicando a sua implementação, informando quais os passos que você completou (vide numeração acima para identificar);
- Se não for implementar a parte Opcional 1, não vai precisar mexer em nada que tem no pacote `br.ufpe.cin.residencia.banco.cliente`;
- Faça um vídeo da sua aplicação rodando, demonstrando o que você conseguiu implementar.
