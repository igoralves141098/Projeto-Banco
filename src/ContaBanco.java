public class ContaBanco {
    private static int totalContasCriadas = 0;
    private TipoConta tipoConta;
    private int numeroConta;
    private String dono;
    private double saldo;
    private boolean status;

    public ContaBanco(String dono) {
        this.dono = dono;
        saldo = 0.0;
        status = false;
        totalContasCriadas++;
        this.numeroConta = totalContasCriadas;
    }

    public void abrirConta(TipoConta tipo){
        if(this.status == true) {
            throw new IllegalArgumentException("Erro: está conta já está aberta!");
        }
        this.tipoConta = tipo;

        if(tipo == TipoConta.CORRENTE) {
            this.saldo = 50.0;
        } else if(tipo == TipoConta.POUPANCA) {
            this.saldo = 150.0;
        }
        this.status = true;
    }

    public void fecharConta() {
        if(this.saldo > 0 || this.saldo < 0) {
            throw new IllegalArgumentException("A conta possui saldo ou está em debito e não pode ser fechada!");
        }
        this.status = false;
    }

    public void depositar(double valor) {
        if(this.status == false) {
            throw new IllegalArgumentException("A conta está fechada");
        } else if(valor <= 0) {
            throw new IllegalArgumentException("Valor de deposito invalido");
        }
        this.saldo+=valor;
    }

    public void sacar(double valor) {
        if(this.status == false) {
            throw new IllegalArgumentException("A conta está fechada");
        } else if(valor > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        }
        this.saldo = this.saldo - valor;
    }

    public void pagarMensalidade() {
        if(this.status == false) {
            throw new IllegalArgumentException("A conta está fechada, não é possivél a cobrança!");
        }
        if(this.tipoConta == TipoConta.CORRENTE) {
            this.saldo = this.saldo - 12.0;
        } else if(this.tipoConta == TipoConta.POUPANCA) {
            this.saldo = this.saldo - 20.0;
        }
    }

    public static int getTotalContasCriadas() {
        return totalContasCriadas;
    }

    public static void setTotalContasCriadas(int totalContasCriadas) {
        ContaBanco.totalContasCriadas = totalContasCriadas;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
