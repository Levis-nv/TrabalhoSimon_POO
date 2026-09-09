package ui;

import negocio.Fachada;
import modelo.Nutricionista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UINutricionista {
    private Scanner scan;
    private Fachada fachada;

    public UINutricionista(Fachada fachada) {
        this.scan = new Scanner(System.in);
        this.fachada = fachada;
    }

    public void exibir() {

        int op;

        do {
            System.out.println("\n--- Nutricionista ---");
            System.out.println("1 - Alterar nutricionista");
            System.out.println("2 - Excluir nutricionista");
            System.out.println("3 - Listar nutricionistas");
            System.out.println("0 - Voltar");

            op = scan.nextInt();
            scan.nextLine();

            switch (op) {

                case 1:
                    alterar();
                    break;

                case 2:
                    excluir();
                    break;

                case 3:

                        listarNutricionista(fachada.listarNutricionista());
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
        System.out.println("Escreva o seu nome");
        String nome = scan.nextLine();

        System.out.println("Escreva o seu nome de usuário");
        String nomeUsuario = scan.nextLine();

        System.out.println("Escreva sua senha");
        String senha = scan.nextLine();

        Nutricionista nutricionista = new Nutricionista(nome, nomeUsuario, senha);


            if (fachada.cadastrarNutricionista(nutricionista)){
                System.out.println("Cadastro realizado com sucesso");
            } else {
                System.out.println("Erro ao cadastrar");
            }

    }

    public void alterar(){

            listarNutricionista(fachada.listarNutricionista());

        Nutricionista nutricionistaAlterado = null;

            while (nutricionistaAlterado == null) {
                System.out.println("Escolha um nutricionista");
                int cod = scan.nextInt();
                nutricionistaAlterado = fachada.buscarNutricionista(cod);
                if (nutricionistaAlterado == null) {
                    System.out.println("O nutricionista não foi encontrado!");
                }
            }

        System.out.println("Escolha o que você deseja alterar");
        System.out.println("1- Nome");
        System.out.println("2- Nome de usuário");
        System.out.println("3- Senha");

        int op;
        do {
            op = scan.nextInt();
            scan.nextLine();
            switch (op) {
                case 1:
                    System.out.println("Escreva o nome");
                    nutricionistaAlterado.setNome(scan.nextLine());
                    break;

                case 2:
                    System.out.println("Escreva o nome de usuário");
                    nutricionistaAlterado.setNomeUsuario(scan.nextLine());
                    break;

                case 3:
                    System.out.println("Escreva a senha");
                    nutricionistaAlterado.setSenha(scan.nextLine());
                    break;

                default:
                    System.out.println("Escreva um valor válido");
            }
        } while (op < 1 || op > 4);


            if (fachada.alterarNutricionista(nutricionistaAlterado)){
                System.out.println("Nutricionista alterado com sucesso");
            } else {
                System.out.println("Erro ao alterar");
            }

    }

    public void excluir(){

            listarNutricionista(fachada.listarNutricionista());

        Nutricionista nutricionista = null;

            while (nutricionista == null) {
                System.out.println("Escolha um nutricionista");
                int cod = scan.nextInt();
                nutricionista = fachada.buscarNutricionista(cod);
                if (nutricionista == null) {
                    System.out.println("O nutricionista não foi encontrado!");
                }
            }

            if (!fachada.excluirNutricionista(nutricionista.getId())) {
                System.out.println("Nutricionista excluido com sucesso");
            } else {
                System.out.println("Erro ao excluir");
            }
    }

    public void listarNutricionista(ArrayList<Nutricionista> arr){
        if (arr == null || arr.isEmpty()) {
            System.out.println("Não existem Nutricionistas cadastrados");
            return;
        }

        System.out.printf("%-8s %-15s %-15s\n",
                "Código", "Nome", "Usuário");
        for (Nutricionista n: arr) {
            System.out.printf("%-8d %-15s %-15s\n", n.getId(), n.getNome(), n.getNomeUsuario());
        }
    }
}
