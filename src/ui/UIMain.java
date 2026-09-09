package ui;

import negocio.Fachada;

import java.io.IOException;
import java.util.Scanner;

public class UIMain {

    private Scanner scan;
    private Fachada fachada;

    public UIMain(Fachada fachada) {
        this.scan = new Scanner(System.in);
        this.fachada = fachada;
    }

    public void exibir() throws IOException {

        int op;

        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1 - Alunos");
            System.out.println("2 - Escolas");
            System.out.println("3 - Nutricionistas");
            System.out.println("4 - Relatórios");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            op = scan.nextInt();

            switch (op) {

                case 1:
                    new UIAluno(fachada).exibir();
                    break;

                case 2:
                    new UIEscola(fachada).exibir();
                    break;

                case 3:
                    new UINutricionista(fachada).exibir();
                    break;

                case 4:
                    new UIRelatorio(fachada).exibir();
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }
}
