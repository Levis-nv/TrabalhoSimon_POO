package ui;

import negocio.Fachada;
import modelo.Escola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UIEscola {
    private Scanner scan;
    private Fachada fachada;

    public UIEscola(Fachada fachada) {
        this.scan = new Scanner(System.in);
        this.fachada = fachada;
    }

    public void exibir() {

        int op;

        do {
            System.out.println("\n--- Escola ---");
            System.out.println("1 - Cadastrar escola");
            System.out.println("2 - Alterar escola");
            System.out.println("3 - Excluir escola");
            System.out.println("4 - Listar escolas");
            System.out.println("0 - Voltar");

            op = scan.nextInt();

            switch (op) {

                case 1:
                    add();
                    break;

                case 2:
                    alterar();
                    break;

                case 3:
                    excluir();
                    break;

                case 4:
                    listarEscola(fachada.listarEscola());
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }

    public void add(){
        System.out.println("Escreva o nome da escola:");
        String nome = scan.next();

        Escola escola = new Escola(nome);
        if (fachada.cadastrarEscola(escola)){
            System.out.println("Cadastro realizado com sucesso");
        } else {
            System.out.println("Erro ao cadastrar");
        }

    }

    public void alterar(){
        listarEscola(fachada.listarEscola());

        Escola escolaAlterada = null;
        while (escolaAlterada == null) {
            System.out.println("Escolha uma escola");
            int cod = scan.nextInt();
            escolaAlterada = fachada.buscarEscola(cod);
            if (escolaAlterada == null) {
                System.out.println("A escola não foi encontrada!");
            }
        }

        System.out.println("Escreva o novo nome");
        escolaAlterada.setNome(scan.next());

        if (fachada.alterarEscola(escolaAlterada)) {
            System.out.println("Alteração realizada com sucesso");
        } else {
            System.out.println("Erro ao alterar");
        }

    }

    public void excluir(){
        listarEscola(fachada.listarEscola());

        Escola escola = null;
        while (escola == null) {
            System.out.println("Escolha uma escola");
            int cod = scan.nextInt();
            escola = fachada.buscarEscola(cod);
            if (escola == null) {
                System.out.println("A escola não foi encontrada!");
            }
        }

        if (fachada.removerEscola(escola)) {
            System.out.println("Escola excluida com sucesso");
        } else {
            System.out.println("Erro ao excluir");
        }
    }

    public void listarEscola(ArrayList<Escola> arr){
        if (arr == null || arr.isEmpty()) {
            System.out.println("Não existem alunos cadastrados");
            return;
        }

        System.out.printf("%-8s %-15s\n",
                "Código", "Nome");
        for (Escola e: arr) {
            System.out.printf("%-8d %-15s\n", e.getId(), e.getNome());
        }
    }
}
