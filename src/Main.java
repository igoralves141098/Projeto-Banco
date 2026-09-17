public class Main {
    public static void main(String[] args) {

        System.out.println("==== ABERTURA DE CONTA=====");

        try {
            ContaBanco conta1 = new ContaBanco("Igor");

            conta1.abrirConta(TipoConta.CORRENTE);
            conta1.depositar(100);
            conta1.pagarMensalidade();
            conta1.sacar(15.75);
            conta1.fecharConta();

            ContaBanco conta2 = new ContaBanco("Pedro");

            conta2.abrirConta(TipoConta.POUPANCA);
            conta2.depositar(150);
            conta2.pagarMensalidade();
            conta2.sacar(35);
            conta2.fecharConta();



        } catch (Exception erro) {
            System.out.println("Algo deu errado na abertura de conta" + erro.getMessage());
        }

    }
}
