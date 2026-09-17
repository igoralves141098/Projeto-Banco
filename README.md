# Guia de Estudos - Projeto Banco

Este documento contém todo o seu código final, mas **repleto de comentários detalhados**. Leia os comentários como se fossem as minhas explicações durante a nossa conversa. O objetivo é que você possa consultar este arquivo no futuro sempre que bater uma dúvida sobre POO (Programação Orientada a Objetos).

---

## 1. O Enumerador (Enum)
O `Enum` é um tipo especial do Java usado para definir uma lista fechada de opções constantes. Usamos quando sabemos que algo só pode ter um número limitado de valores.

```java
// O enum garante que é impossível alguém criar uma conta do tipo "SALARIO" ou "INVESTIMENTO", 
// pois as únicas duas opções que existem no nosso sistema são as listadas abaixo.
public enum TipoConta {
    CORRENTE,
    POUPANCA
}
```

---

## 2. A Classe de Domínio (`ContaBanco`)
Esta é a sua "Fábrica" ou "Molde". Ela não é uma conta real, ela ensina o Java a como fabricar contas.

```java
public class ContaBanco {
    // ==========================================
    // 1. ATRIBUTOS (As características do objeto)
    // ==========================================
    
    // STATIC: A palavra 'static' significa que esta variável NÃO pertence à conta individual do Igor ou do Pedro.
    // Ela pertence à CLASSE (a fábrica inteira). Todas as contas compartilham essa mesma variável.
    // Se a fábrica fabricar 10 contas, esse número será 10 para todo mundo.
    private static int totalContasCriadas = 0; 

    // PRIVATE (Encapsulamento): Ninguém de fora da fábrica (como a classe Main) 
    // pode alterar esses valores diretamente. Precisam pedir permissão usando os métodos.
    private TipoConta tipoConta;
    private int numeroConta;
    private String dono;
    private double saldo;
    private boolean status;

    // ==========================================
    // 2. CONSTRUTOR (A esteira de montagem)
    // ==========================================
    // O construtor tem EXATAMENTE o mesmo nome da Classe e nunca tem 'void' ou 'int' antes.
    // Ele é ativado uma ÚNICA VEZ, no momento que alguém faz um "new ContaBanco()".
    public ContaBanco(String dono) {
        this.dono = dono; // Pega o nome que foi passado e salva no atributo da conta.
        this.saldo = 0.0; // Toda conta nasce zerada.
        this.status = false; // Toda conta nasce fechada.
        
        // Acessamos a variável global da fábrica e somamos +1.
        totalContasCriadas++; 
        
        // Pegamos esse novo número global e "carimbamos" no número DESTA conta específica.
        this.numeroConta = totalContasCriadas; 
    }

    // ==========================================
    // 3. MÉTODOS / REGRAS DE NEGÓCIO (O que a conta faz)
    // ==========================================
    // O 'this' sempre significa "A MINHA variável", para diferenciar da variável que vem pelo parâmetro.

    public void abrirConta(TipoConta tipo) {
        // Validação (Regra de Ouro): Nunca deixe fazer algo proibido.
        if (this.status == true) {
            // O 'throw new' funciona como uma bomba. Ele explode e para a execução do método na hora, 
            // enviando esse texto vermelho para quem chamou.
            throw new IllegalArgumentException("Erro: esta conta já está aberta!");
        }
        
        // Se a conta não estava aberta, o código continua normalmente.
        this.tipoConta = tipo; // Guarda se é CORRENTE ou POUPANCA

        // Define o bônus inicial com base no tipo escolhido.
        if (tipo == TipoConta.CORRENTE) {
            this.saldo = 50.0;
        } else if (tipo == TipoConta.POUPANCA) {
            this.saldo = 150.0;
        }
        
        // Finaliza alterando o status para verdadeiro (aberta).
        this.status = true;
    }

    public void fecharConta() {
        // Validação: Só pode fechar se o saldo for exatamente cravado em 0.
        // Se for maior que 0 (tem dinheiro) OU menor que 0 (está devendo), jogue a exceção.
        if (this.saldo > 0 || this.saldo < 0) {
            throw new IllegalArgumentException("A conta possui saldo ou está em debito e não pode ser fechada!");
        }
        // Se passou direto pelo IF, significa que saldo é 0. Pode fechar.
        this.status = false;
    }

    public void depositar(double valor) {
        // Validação 1: Não pode depositar em conta fechada.
        if (this.status == false) {
            throw new IllegalArgumentException("A conta está fechada");
        } 
        // Validação 2: Não pode depositar R$ 0,00 ou dinheiro negativo (ex: -100).
        else if (valor <= 0) {
            throw new IllegalArgumentException("Valor de deposito invalido");
        }
        
        // Ação: Pega o saldo atual e SOMA com o valor que chegou. (+= é o atalho mágico)
        this.saldo += valor;
    }

    public void sacar(double valor) {
        if (this.status == false) {
            throw new IllegalArgumentException("A conta está fechada");
        } 
        // Validação 2: Se a pessoa pediu um valor que é MAIOR que o saldo que ela tem, proíba!
        else if (valor > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        }
        
        // Ação: Pega o saldo atual e SUBTRAI o valor que ela quer sacar.
        this.saldo = this.saldo - valor; // Essa é a forma por extenso. O atalho seria this.saldo -= valor;
    }

    public void pagarMensalidade() {
        if (this.status == false) {
            throw new IllegalArgumentException("A conta está fechada, não é possivél a cobrança!");
        }
        
        // Descobre o tipo da conta e debita o valor específico de cada uma.
        if (this.tipoConta == TipoConta.CORRENTE) {
            this.saldo = this.saldo - 12.0;
        } else if (this.tipoConta == TipoConta.POUPANCA) {
            this.saldo = this.saldo - 20.0;
        }
    }

    // ==========================================
    // 4. GETTERS E SETTERS (As portas de acesso)
    // ==========================================
    // Como nossos atributos são "private" (trancados num cofre), criamos esses métodos públicos
    // para que as outras classes possam consultar os valores (Get) ou alterá-los (Set).
    // O professor recomendou apagar os 'setters' de atributos que não fazem sentido serem mudados 
    // manualmente, como o saldo e o status (que devem mudar só via depositar/sacar/abrir). 
    
    public static int getTotalContasCriadas() {
        return totalContasCriadas;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isStatus() {
        return status;
    }
}
```

---

## 3. A Classe de Teste (`Main`)
É aqui que a "brincadeira" acontece de verdade. Aqui nós somos os clientes interagindo com o caixa do banco (usando os objetos gerados a partir do molde `ContaBanco`).

```java
public class Main {
    public static void main(String[] args) {

        System.out.println("==== ABERTURA DE CONTA=====");

        // O BLOCO TRY / CATCH:
        // Como todos os seus métodos na ContaBanco agora possuem 'bombas' (throw new), 
        // precisamos usar o 'try' (tentar). O Java vai tentar rodar todo o código abaixo, 
        // mas se qualquer 'bomba' explodir, ele pula IMEDIATAMENTE para o bloco 'catch' 
        // para capturar a exceção de forma amigável, sem travar (crashar) o sistema do usuário.
        
        try {
            // "new ContaBanco" chama o seu CONSTRUTOR. Aqui nasce a Instância/Objeto conta1.
            ContaBanco conta1 = new ContaBanco("Igor");

            // Começamos a interagir usando os métodos.
            conta1.abrirConta(TipoConta.CORRENTE); // Saldo vai pra 50
            conta1.depositar(100);                 // Saldo vai pra 150
            conta1.pagarMensalidade();             // Saldo vai pra 138
            
            // Para poder fechar a conta do Igor sem a nossa regra lançar um erro,
            // precisamos sacar EXATAMENTE o que sobrou.
            conta1.sacar(138);                     // Saldo vai pra 0
            
            // Agora a conta fecha bonitinho!
            conta1.fecharConta();
            System.out.println("Conta do Igor fechada com sucesso!");

            // -----------------------------------------------------

            // Aqui nasce um NOVO Objeto. A conta2 é 100% independente da conta1.
            ContaBanco conta2 = new ContaBanco("Pedro");

            conta2.abrirConta(TipoConta.POUPANCA); // Saldo vai pra 150
            conta2.depositar(150);                 // Saldo vai pra 300
            conta2.pagarMensalidade();             // Saldo vai pra 280
            
            // Sacamos tudo o que restava do Pedro
            conta2.sacar(280);                     // Saldo vai pra 0
            
            conta2.fecharConta();
            System.out.println("Conta do Pedro fechada com sucesso!");

        } catch (Exception erro) {
            // O 'erro.getMessage()' extrai exatamente o texto vermelho que você 
            // escreveu lá dentro da sua classe ContaBanco.
            System.out.println("Algo deu errado na operacao: " + erro.getMessage());
        }

    }
}
```
