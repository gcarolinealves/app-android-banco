## Aplicativo Android - Banco

1. Na classe ContasActivity foi implementado a exibição da lista com todos os objetos do tipo Conta que estão armazenados no banco de dados.

2. Na classe ContaViewHolder foi implementado a exibição de uma imagem identificando que a conta possui saldo negativo; a passagem do número da conta que se deseja editar por intent para a classe EditarContaActivity ao apertar no ícone de edição; a exclusão da conta ao apertar no ícone de exclusão.

3. Na classe AdicionarContaActivity, foi implementado todas as validações antes da criação de um objeto Conta. As validações usadas foram:
    - Nenhum campo pode ser vazio (todos são obrigatórios);
    - O campo nome precisa ter ao menos 5 caracteres;
    - O CPF deve conter necessariamente 11 caracteres, sem traços ou números.
    - O campo número e saldo aceitam apenas números. O regex foi utilizado para garantir que os campos de número da conta e saldo contenham apenas números inteiros e decimais.

4. Na classe ContaDAO foram implementados os métodos para atualizar e remover contas; os métodos para buscar pelo número da conta, pelo nome do cliente, pelo
cpf do cliente; o método para buscar o saldo total do banco somente das contas que possuem saldo positivo.

5. Na classe ContaRepository foi implementado o corpo dos métodos para atualizar e remover contas, buscar pelo número da conta, pelo nome do cliente e pelo cpf do cliente, usando os métodos da classe ContaDAO.

6. Na classe ContaViewModel foram implementados os métodos para atualizar e remover contas, buscar pelo número da conta, pelo nome do cliente e pelo cpf do
cliente, usando os métodos da classe ContaRepository.

7. Na classe EditarContaActivity, o intent enviado por ContaViewHolder é recebido e mostrado no campo de número da conta ao clicar no ícone de editar; o nome, cpf e saldo da conta escolhida para edição são exibidos nos campos do formulário ao clicar no ícone de editar; a validação dos campos antes da edição de um objeto Conta são as mesmas da classe AdicionarContaAcvitity; o método para excluir uma conta foi implementado.

8. Na classe BancoViewModel foram implementados os métodos para buscar pelo número da conta, pelo nome do cliente e pelo cpf do cliente, além de pegar o saldo total do banco, usando os métodos da classe ContaRepository. Na classe BancoViewModel também foram implementados os métodos para buscar transações de crédito por data, transações de crédito por número da conta, transações de débito por data, transações de débito por número da conta, todas as transações por data e todas as transações por número da conta usando os métodos da classe TransacaoRepository.

9. Nas classes DebitarActivity, CreditarAcvitivy e TransferirAcvitity, a data e hora do sistema foram salvos e configurados no formato "dd-MM-yyyy HH:mm:ss" antes de salvar os objetos Transacao no banco de dados. Além disso, foram implementados as seguintes validações antes de concluir uma transação:
    - Nenhum campo pode ser vazio;
    - O campo número da conta aceita apenas números;
    - O valor da transação não pode ser negativo. 

10. Na classe PesquisarActivity foi implementada a validação que não permite que o campo de pesquisa seja vazio. Além disso, o RecyclerView foi atualizado de acordo com a busca escolhida (busca pelo nome, pelo cpf ou pelo número da conta).

11. Na classe MainAcvivity é exibido o saldo total do banco (o banco permite contas com saldo negativo, porém nessa classe é exibido apenas o saldo total
considerando as contas com saldo positivo).

12. Na classe TransacaoViewHolder, a cor do texto é alterada para vermelho caso transações de débito sejam realizadas.

13. Na classe TransacaoDAO foram implementados os métodos para buscar transações de crédito por data, transações de crédito por número da conta, transações de débito por data, transações de débito por número da conta, todas as transações por data e todas as transações por número da conta.

13. Na classe TransacaoRepository foi implementado o corpo dos métodos para buscar transações de crédito por data, transações de crédito por número da conta, transações de débito por data, transações de débito por número da conta, todas as transações por data e todas as transações por número da conta usando os métodos
da classe TransacaoDAO.

14. Na classe TransacaoViewModel, foi implementado o corpo dos métodos para buscar transações de crédito por data, transações de crédito por número da conta, transações de débito por data, transações de débito por número da conta, todas as transações por data e todas as transações por número da conta usando os métodos da classe TransacaoRepository.

15. Na classe TransacoesActivity foi implementada a validação que não permite que o campo de pesquisa seja vazio. Além disso, o RecyclerView foi atualizado de acordo com a busca escolhida (transações de crédito por data, transações de crédito por número da conta, transações de débito por data, transações de débito por número da conta, todas as transações por data e todas as transações por número da conta).

## Melhorias futuras

1. Incluir as telas para gerenciamento de Clientes e ajustar implementação do BD para refletir relacionamento em que toda `Conta` tem apenas um `Cliente` associado, mas um `Cliente` pode ter mais de uma `Conta` - neste caso, ao adicionar uma conta tem que verificar se o `Cliente` é válido (se é um `Cliente` já existente no banco);

2. Extrair as strings e traduzir a aplicação para outra língua;

3. Fazer melhorias de UI na aplicação.

<br>
Projeto final da disciplina Orientação a Objetos com Java e Android da Pós-graduação Lato Sensu - Especialização em Residência em Software com Ênfase em Análise de Testes da Universidade Federal de Pernambuco.
