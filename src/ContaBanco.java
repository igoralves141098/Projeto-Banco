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
    }

    public void abrirConta(TipoConta tipo){
        if(tipo == TipoConta.CORRENTE) {
            this.saldo = 50.0;
        }
        else if(tipo == TipoConta.POUPANCA) {
            this.saldo = 150.0;
        }
        status = true;
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
