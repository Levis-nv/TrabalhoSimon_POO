package cliente.ui;

import cliente.fachada.FachadaCliente;
import modelo.entidade.Escola;
import modelo.entidade.Nutricionista;

import java.io.IOException;
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
            System.out.println("\n--- Nutricionista ---");
            System.out.println("1 - Alterar escola");
            System.out.println("2 - Excluir escola");
            System.out.println("3 - Listar escolas");
            System.out.println("0 - Voltar");

            op = scan.nextInt();

            switch (op) {

                case 1:
                    alterar();
                    break;

                case 2:
                    excluir();
                    break;

                case 3:
                    try {
                        listarNutricionista(fachada.listarNutricionista());
                    } catch (IOException e) {
                        System.out.println("Erro na comunicação com o servidor");

                    } catch (ClassNotFoundException e) {
                        System.out.println("Erro ao receber a resposta do servidor");

                    }
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

        try {
            String resposta = fachada.cadastrarNutricionista(nutricionista);

            System.out.println(resposta);
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");

        }

    }

    public void alterar(){
        try {
            listarNutricionista(fachada.listarNutricionista());
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

        }

        Nutricionista nutricionistaAlterado = null;
        try {
            while (nutricionistaAlterado == null) {
                System.out.println("Escolha um nutricionista");
                int cod = scan.nextInt();
                nutricionistaAlterado = fachada.buscarNutricionista(cod);
                if (nutricionistaAlterado == null) {
                    System.out.println("O nutricionista não foi encontrado!");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

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

        try {
            String resposta = fachada.alterarNutricionista(nutricionistaAlterado);
            System.out.println(resposta);

        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");

        }

    }

    public void excluir(){
        try {
            listarNutricionista(fachada.listarNutricionista());
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

        }

        Nutricionista nutricionista = null;
        try {
            while (nutricionista == null) {
                System.out.println("Escolha um nutricionista");
                int cod = scan.nextInt();
                nutricionista = fachada.buscarNutricionista(cod);
                if (nutricionista == null) {
                    System.out.println("O nutricionista não foi encontrado!");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

        }

        try {
            String resposta = fachada.excluirNutricionista(nutricionista.getId());

            System.out.println(resposta);
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");

        }
    }

    public void listarNutricionista(ArrayList<Nutricionista> arr){
        System.out.printf("%-8s %-15s %-15s\n",
                "Código", "Nome", "Usuário");
        for (Nutricionista n: arr) {
            System.out.printf("%-8d %-15s %-15s\n", n.getId(), n.getNome());
        }
    }
}
