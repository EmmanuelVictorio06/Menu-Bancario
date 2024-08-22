public class Conta {
    private Cliente cliente;
    private double saldo;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void depositar(double valor) {
        if (valor > 0) saldo += valor;
        System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) saldo -= valor;
        System.out.println("Saque de R$" + valor + " realizado com sucesso.");
    }

    public void pagarConta(double valor) {
        if (valor > 0 && valor <= saldo) saldo -= valor;
        System.out.println("Pagamento de conta no valor de R$" + valor + " realizado com sucesso.");
    }

    @Override
    public String toString() {
        return cliente.toString() + ", Saldo: R$" + saldo;
    }
}
