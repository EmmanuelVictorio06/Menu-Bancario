public class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome.toUpperCase();
        this.cpf = cpf.toUpperCase();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome.toLowerCase();
    }

    public void setCpf(String cpf) {
        this.cpf = cpf.toLowerCase();
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", CPF: " + cpf;
    }
}
