package negocio;

import modelo.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GeradorRelatorio {

    private static GeradorRelatorio instancia;

    public GeradorRelatorio() {
    }

    public static GeradorRelatorio getInstance(){
        if (instancia == null) {
            instancia = new GeradorRelatorio();
        }

        return instancia;
    }

    public void gerarRelatorioAlunos(ArrayList<Aluno> alunos)
            throws IOException {

        File pasta = new File("src/relatorios");
        pasta.mkdirs();

        FileWriter arquivo = new FileWriter("src/relatorios/relatorio_alunos.txt");
        PrintWriter writer = new PrintWriter(arquivo);

        writer.println("===== RELATÓRIO DE ALUNOS =====");
        writer.println();

        for (Aluno aluno : alunos) {

            writer.println("ID: " + aluno.getId());
            writer.println("Nome: " + aluno.getNome());
            writer.println("Escola: " + aluno.getEscola().getNome());
            writer.println("Sexo: " + aluno.getSexo());
            writer.println("Altura: " + aluno.getAltura());
            writer.println("Peso: " + aluno.getPeso());
            writer.println("IMC: " + aluno.getImc());
            writer.println("Classificação: " + aluno.getClassificacao());

            writer.println("----------------------------");
        }

        writer.close();
    }

    public void gerarRelatorioEscola(Escola escola, ArrayList<Aluno> alunos) throws IOException {

        File pasta = new File("src/relatorios");
        pasta.mkdirs();

        PrintWriter writer = new PrintWriter(
                new FileWriter(
                        "src/relatorios/escola_" + escola.getNome() + ".txt"
                )
        );

        int total = alunos.size();

        writer.println("===== RELATÓRIO DA ESCOLA =====");
        writer.println("Escola: " + escola.getNome());
        writer.println("Total de alunos: " + total);
        writer.println();

        for (Classificacao c : Classificacao.values()) {

            int quantidade =
                    contarClassificacao(alunos, c);

            double porcentagem =
                    calcularPorcentagem(quantidade, total);

            writer.printf(
                    "%-20s %3d alunos - %.2f%%%n",
                    c,
                    quantidade,
                    porcentagem
            );
        }

        writer.close();
    }

    public void gerarRelatorioGeral(ArrayList<Aluno> alunos) throws IOException {

        File pasta = new File("src/relatorios");
        pasta.mkdirs();

        PrintWriter writer = new PrintWriter(
                new FileWriter(
                        "src/relatorios/relatorio_geral.txt"
                )
        );

        int total = alunos.size();

        writer.println("===== RELATÓRIO GERAL =====");
        writer.println("Total de alunos: " + total);
        writer.println();

        for (Classificacao c : Classificacao.values()) {

            int quantidade = contarClassificacao(alunos, c);

            double porcentagem =
                    calcularPorcentagem(quantidade, total);

            writer.printf(
                    "%-20s %3d alunos - %.2f%%%n",
                    c,
                    quantidade,
                    porcentagem
            );
        }

        writer.close();
    }

    private int contarClassificacao(ArrayList<Aluno> alunos, Classificacao c) {
        int quant = 0;
        for (Aluno a: alunos) {
            if (a.getClassificacao() == c){
                quant++;
            }
        }

        return quant;
    }

    private double calcularPorcentagem(int quantidade, int total) {
        return (double) quantidade /total*100;
    }
}
