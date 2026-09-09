package ui;

import modelo.Nutricionista;
import negocio.Fachada;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class UILogin {
    private Scanner scan;
    private Fachada fachada;

    public UILogin(Fachada fachada) {
        this.scan = new Scanner(System.in);
        this.fachada = fachada;
    }

    public void exibir() throws IOException {
        int op;

        do {
            System.out.println("\n --- MENU LOGIN ---");
            System.out.println("1- Login");
            System.out.println("2- Cadastrar");
            System.out.println("0- Sair");

            op = scan.nextInt();
            scan.nextLine();

            switch (op) {

                case 1:
                    if (login()) {
                        new UIMain(fachada).exibir();
                    }
                    break;

                case 2:
                    if (cadastrar()) {
                        new UIMain(fachada).exibir();
                    };
                    break;

                case 0:
                    System.out.println("Encerrando...");

                default:
                    System.out.println("Opção inválida");
            }
        } while (op != 0);
    }

    public boolean login(){
        if (fachada.listarNutricionista().isEmpty()) {
            System.out.println("Não existem nutricionistas cadastrados");
            return false;
        }

        System.out.println("Escreva o nome de usuário");
        String user = scan.nextLine();

        System.out.println("Escreva a senha");
        String senha = scan.nextLine();

        if (fachada.login(user, senha)){
            System.out.println("Login realizado!");
            return true;
        } else {
            System.out.println("Usuário ou senha incorretos");
            return false;
        }
    }

    public boolean cadastrar(){

        System.out.println("Escreva o seu nome: ");
        String nome = scan.nextLine();
        while (nome.isEmpty()){
            System.out.println("Escreva um nome válido!");
            nome = scan.nextLine();
        }

        System.out.println("Escreva o seu nome de usuário");
        String user = scan.nextLine();
        while (user.isEmpty()){
            System.out.println("Escreva um nome válido!");
            user = scan.nextLine();
        }

        System.out.println("Escreva a sua senha");
        String senha = scan.nextLine();
        while (senha.isEmpty()){
            System.out.println("Escreva uma senha válida!");
            senha = scan.nextLine();
        }

        Nutricionista nutricionista = new Nutricionista(nome, user, senha);

        if (fachada.cadastrarNutricionista(nutricionista)){
            System.out.println("Cadastro realizado com sucesso");
            return true;
        } else {
            System.out.println("Erro ao cadastrar!");
            return false;
        }

    }

}
