package cliente.ui;

import cliente.fachada.FachadaCliente;
import modelo.entidade.Escola;

import java.util.ArrayList;
import java.util.Scanner;

public class UIEscola {
    private Scanner scan;
    private FachadaCliente fachada;

    public UIEscola(FachadaCliente fachada) {
        this.scan = new Scanner(System.in);
        this.fachada = fachada;
    }

    public void exibir() {
        int op;
        do {
            System.out.println("\n--- MENU ESCOLA ---");
            System.out.println("1 - Cadastrar escola");
            System.out.println("2 - Alterar escola");
            System.out.println("3 - Excluir escola");
            System.out.println("4 - Listar escolas");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            op = scan.nextInt();
            scan.nextLine(); // limpa

            switch (op) {
                case 1: add(); break;
                case 2: alterar(); break;
                case 3: excluir(); break;
                case 4: listarTodas(); break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    private void add() {
        System.out.print("Nome da escola: ");
        String nome = scan.nextLine();

        try {
            System.out.println(fachada.cadastrarEscola(new Escola(nome)));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void alterar() {
        listarTodas();
        System.out.print("ID da escola para alterar: ");
        int id = scan.nextInt();
        scan.nextLine();

        try {
            Escola escola = fachada.buscarEscola(id);
            if (escola == null) {
                System.out.println("Escola não encontrada!");
                return;
            }

            System.out.print("Novo nome (" + escola.getNome() + "): ");
            escola.setNome(scan.nextLine());
            System.out.println(fachada.alterarEscola(escola));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void excluir() {
        listarTodas();
        System.out.print("ID da escola para excluir: ");
        int id = scan.nextInt();
        try {
            System.out.println(fachada.excluirEscola(id));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarTodas() {
        try {
            ArrayList<Escola> arr = fachada.listarEscola();
            if(arr == null || arr.isEmpty()) {
                System.out.println("Nenhuma escola cadastrada.");
                return;
            }
            System.out.printf("%-5s %-20s\n", "ID", "Nome");
            for (Escola e : arr) {
                System.out.printf("%-5d %-20s\n", e.getId(), e.getNome());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }
}