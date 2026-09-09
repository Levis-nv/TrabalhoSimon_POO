package ui;

import modelo.Aluno;
import modelo.Escola;
import negocio.Fachada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UIRelatorio {

    private Scanner scan;
    private Fachada fachada;

    public UIRelatorio(Fachada fachada) {
        this.scan = new Scanner(System.in);;
        this.fachada = fachada;
    }

    public void exibir() throws IOException {

        int op;

        do {
            System.out.println("\n--- RELATÓRIO ---");
            System.out.println("1- Gerar relatório dos alunos");
            System.out.println("2- Gerar relatório da escola");
            System.out.println("3- Gerar relatório geral");
            System.out.println("0- Sair");

            op = scan.nextInt();
            scan.nextLine();

            switch (op){
                case 1:
                    fachada.gerarRelatorioAlunos(fachada.listarAlunos());
                    break;

                case 2:
                    Escola escola = null;

                    listarEscola(fachada.listarEscola());

                    while (escola == null) {
                        System.out.println("Escolha uma escola");
                        int cod = scan.nextInt();
                        escola = fachada.buscarEscola(cod);
                        if (escola == null) {
                            System.out.println("A escola não foi encontrada!");
                        }
                    }

                    fachada.gerarRelatorioEscola(escola, fachada.listarAlunos());
                    break;

                case 3:
                    fachada.gerarRelatorioGeral(fachada.listarAlunos());
                    break;

                default:
                    System.out.println("Escreva uma opção válida");
            }
        } while (op != 0);
    }

    public void listarAluno(ArrayList<Aluno> arr){
        if (arr == null || arr.isEmpty()) {
            System.out.println("Não existem alunos cadastrados");
            return;
        }

        System.out.printf("%-8s %-15s %-20s %-15s %-10s %-10s %-10s %-15s\n",
                "Código", "Nome", "Escola", "Sexo","Altura", "Peso", "IMC", "Classificação");
        for (Aluno a: arr) {
            System.out.printf("%-8d %-15s %-20s %-15s %-6s %-6s %-10s %-15s\n", a.getId(), a.getNome(), a.getEscola().getNome(),
                    a.getNome() ,a.getAltura(), a.getPeso(), a.getImc(), a.getClassificacao());
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
