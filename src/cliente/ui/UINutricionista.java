package cliente.ui;

import cliente.fachada.FachadaCliente;
import modelo.entidade.Nutricionista;

import java.util.ArrayList;
import java.util.Scanner;

public class UINutricionista {
    private Scanner scan;
    private FachadaCliente fachada;

    public UINutricionista(FachadaCliente fachada) {
        this.scan = new Scanner(System.in);
        this.fachada = fachada;
    }

    public void exibir() {
        int op;
        do {
            System.out.println("\n--- MENU NUTRICIONISTA ---");
            System.out.println("1 - Cadastrar Nutricionista");
            System.out.println("2 - Alterar Nutricionista");
            System.out.println("3 - Excluir Nutricionista");
            System.out.println("4 - Listar Nutricionistas");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            op = scan.nextInt();
            scan.nextLine(); // limpa

            switch (op) {
                case 1: add(); break;
                case 2: alterar(); break;
                case 3: excluir(); break;
                case 4: listarTodos(); break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    private void add() {
        System.out.print("Nome: ");
        String nome = scan.nextLine();

        System.out.print("Nome de Usuário (Login): ");
        String usuario = scan.nextLine();

        System.out.print("Senha: ");
        String senha = scan.nextLine();

        try {
            Nutricionista n = new Nutricionista(nome, usuario, senha);
            System.out.println(fachada.cadastrarNutricionista(n));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void alterar() {
        listarTodos();
        System.out.print("ID do Nutricionista para alterar: ");
        int id = scan.nextInt();
        scan.nextLine();

        try {
            Nutricionista n = fachada.buscarNutricionista(id);
            if (n == null) {
                System.out.println("Nutricionista não encontrado!");
                return;
            }

            System.out.println("1- Alterar Nome | 2- Alterar Usuário | 3- Alterar Senha");
            int op = scan.nextInt();
            scan.nextLine();

            switch(op) {
                case 1:
                    System.out.print("Novo Nome: ");
                    n.setNome(scan.nextLine());
                    break;
                case 2:
                    System.out.print("Novo Usuário: ");
                    n.setNomeUsuario(scan.nextLine());
                    break;
                case 3:
                    System.out.print("Nova Senha: ");
                    n.setSenha(scan.nextLine());
                    break;
            }

            System.out.println(fachada.alterarNutricionista(n));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void excluir() {
        listarTodos();
        System.out.print("ID do Nutricionista para excluir: ");
        int id = scan.nextInt();
        try {
            System.out.println(fachada.excluirNutricionista(id));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarTodos() {
        try {
            ArrayList<Nutricionista> arr = fachada.listarNutricionista();
            if(arr == null || arr.isEmpty()) {
                System.out.println("Nenhum nutricionista cadastrado.");
                return;
            }
            System.out.printf("%-5s %-20s %-15s\n", "ID", "Nome", "Usuário");
            for (Nutricionista n : arr) {
                System.out.printf("%-5d %-20s %-15s\n", n.getId(), n.getNome(), n.getNomeUsuario());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }
}