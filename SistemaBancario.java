import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SistemaBancario {

    private static Conta conta;
    private static Set<String> nomesCadastrados = new HashSet<>();
    private static Set<String> cpfsCadastrados = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===== Menu Bancário =====");
            System.out.println("1. Cadastrar");
            System.out.println("2. Sacar");
            System.out.println("3. Depositar");
            System.out.println("4. Pagar conta");
            System.out.println("5. Verificar saldo");
            System.out.println("6. Alterar informações");
            System.out.println("7. Emitir fatura");
            System.out.println("0. Sair");
            System.out.print("Digite a opção desejada: ");

            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1 -> cadastrar(scanner);
                case 2 -> sacar(scanner);
                case 3 -> depositar(scanner);
                case 4 -> pagarConta(scanner);
                case 5 -> verificarSaldo();
                case 6 -> alterarInformacoes(scanner);
                case 7 -> emitirFatura();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrar(Scanner scanner) {
        String nome = lerString(scanner, "Digite o nome do cliente: ");
        String cpf = lerString(scanner, "Digite o CPF do cliente: ");

        if (nomesCadastrados.contains(nome)) {
            System.out.println("Nome já cadastrado. Por favor, insira um nome diferente.");
            return;
        }

        if (cpfsCadastrados.contains(cpf)) {
            System.out.println("CPF já cadastrado. Por favor, insira um CPF diferente.");
            return;
        }

        Cliente cliente = new Cliente(nome, cpf);
        conta = new Conta(cliente);
        nomesCadastrados.add(nome);
        cpfsCadastrados.add(cpf);

        System.out.println("Cadastro realizado com sucesso!");
    }

    private static void sacar(Scanner scanner) {
        if (conta == null) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        double valor = lerDouble(scanner, "Digite o valor a ser sacado: ");
        conta.sacar(valor);
    }

    private static void depositar(Scanner scanner) {
        if (conta == null) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        double valor = lerDouble(scanner, "Digite o valor a ser depositado: ");
        conta.depositar(valor);
    }

    private static void pagarConta(Scanner scanner) {
        if (conta == null) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        double valor = lerDouble(scanner, "Digite o valor da conta a ser paga: ");
        conta.pagarConta(valor);
    }

    private static void verificarSaldo() {
        if (conta == null) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("Saldo atual: R$" + conta.getSaldo());
    }

    private static void alterarInformacoes(Scanner scanner) {
        if (conta == null) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        String novoNome = lerString(scanner, "Digite o novo nome do cliente: ");
        String novoCpf = lerString(scanner, "Digite o novo CPF do cliente: ");

        if (nomesCadastrados.contains(novoNome) && !novoNome.equals(conta.getCliente().getNome())) {
            System.out.println("Nome já cadastrado. Por favor, insira um nome diferente.");
            return;
        }

        if (cpfsCadastrados.contains(novoCpf) && !novoCpf.equals(conta.getCliente().getCpf())) {
            System.out.println("CPF já cadastrado. Por favor, insira um CPF diferente.");
            return;
        }

        nomesCadastrados.remove(conta.getCliente().getNome());
        cpfsCadastrados.remove(conta.getCliente().getCpf());

        conta.getCliente().setNome(novoNome);
        conta.getCliente().setCpf(novoCpf);

        nomesCadastrados.add(novoNome);
        cpfsCadastrados.add(novoCpf);

        System.out.println("Informações alteradas com sucesso!");
    }

    private static void emitirFatura() {
        if (conta == null) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("===== Fatura =====");
        System.out.println(conta);
    }

    private static int lerInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.next(); // Limpar o scanner
        }
        return scanner.nextInt();
    }

    private static double lerDouble(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida. Por favor, insira um valor numérico.");
            scanner.next(); // Limpar o scanner
        }
        return scanner.nextDouble();
    }

    private static String lerString(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().toLowerCase();
    }
}
