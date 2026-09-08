package cliente.ui;

import cliente.fachada.FachadaCliente;
import modelo.entidade.Escola;

import java.io.IOException;
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
                    try {
                        listarEscola(fachada.listarEscola());
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
        System.out.println("Escreva o nome da escola:");
        String nome = scan.next();

        Escola escola = new Escola(nome);

        try {
            if (fachada.cadastrarEscola(escola)){
                System.out.println("Cadastro realizado com sucesso");
            } else {
                System.out.println("Erro ao cadastrar");
            }

        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");

        }

    }

    public void alterar(){
        try {
            listarEscola(fachada.listarEscola());
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

        }

        Escola escolaAlterada = null;
        try {
            while (escolaAlterada == null) {
                System.out.println("Escolha uma escola");
                int cod = scan.nextInt();
                escolaAlterada = fachada.buscarEscola(cod);
                if (escolaAlterada == null) {
                    System.out.println("A escola não foi encontrada!");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

        }

        System.out.println("Escreva o novo nome");
        escolaAlterada.setNome(scan.next());

        try {
            if (fachada.alterarEscola(escolaAlterada)) {
                System.out.println("Alteração realizada com sucesso");
            } else {
                System.out.println("Erro ao alterar");
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");

        }

    }

    public void excluir(){
        try {
            listarEscola(fachada.listarEscola());
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");
            return;

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");
            return;

        }

        Escola escola = null;
        try {
            while (escola == null) {
                System.out.println("Escolha uma escola");
                int cod = scan.nextInt();
                escola = fachada.buscarEscola(cod);
                if (escola == null) {
                    System.out.println("A escola não foi encontrada!");
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
            if (fachada.excluirEscola(escola.getId())) {
                System.out.println("Escola excluida com sucesso");
            } else {
                System.out.println("Erro ao excluir");
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber a resposta do servidor");

        }
    }

    public void listarEscola(ArrayList<Escola> arr){
        System.out.printf("%-8s %-15s\n",
                "Código", "Nome");
        for (Escola e: arr) {
            System.out.printf("%-8d %-15s\n", e.getId(), e.getNome());
        }
    }
}
