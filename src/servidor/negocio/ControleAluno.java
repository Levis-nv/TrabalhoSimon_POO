package servidor.negocio;

import modelo.entidade.Aluno;
import modelo.entidade.Classificacao;
import servidor.repositorio.RepositorioAluno;

import java.util.ArrayList;

public class ControleAluno {
    private RepositorioAluno repositorio;
    private static ControleAluno instancia;

    public ControleAluno() {
        this.repositorio = RepositorioAluno.getInstancia();
    }

    public static ControleAluno getInstance() {
        if(instancia == null) {
            instancia = new ControleAluno();
        }

        return instancia;
    }

    public boolean cadastrarAluno(Aluno aluno) {

        if (validacao(aluno)) {
            aluno.setImc(calcularIMC(aluno.getAltura(), aluno.getPeso()));

            aluno.setClassificacao(Classificacao.gerarClassificacao(aluno.getImc()));

            return repositorio.adicionarAluno(aluno);
        }

        return false;
    }

    public boolean alterarAluno(Aluno aluno) {

        if (validacao(aluno)) {
            aluno.setImc(calcularIMC(aluno.getAltura(), aluno.getPeso()));

            aluno.setClassificacao(Classificacao.gerarClassificacao(aluno.getImc()));

            return repositorio.alterarAluno(aluno);
        }

        return false;
    }

    public boolean excluirAluno(int idAluno) {
        if (idAluno < 0) {
            return false;
        }

        return repositorio.excluirAluno(idAluno);
    }

    public ArrayList<Aluno> listarAlunos() {
        return repositorio.listarTodos();
    }

    public ArrayList<Aluno> listarPorEscola(int idEscola) {
        if (idEscola < 0){
            return null;
        }

        return repositorio.listarPorEscola(idEscola);
    }

    public ArrayList<Aluno> listarPorCategoria(Classificacao categoria) {
        if (categoria == null) {
            return null;
        }

        return repositorio.listarPorCategoria(categoria);
    }

    public ArrayList<Aluno> listarFaixaDeRisco() {
        return repositorio.listarFaixaDeRisco();
    }

    private boolean validacao(Aluno aluno) {
        if (aluno == null){
            return false;
        }

        if (repositorio.verificarAluno(aluno)){
            return false;
        }

        return aluno.getEscola() != null && aluno.getAltura() > 0 && aluno.getPeso() > 0
                && aluno.getSexo() != null;
    }

    private double calcularIMC(double altura, double peso){
        return peso/(altura * altura);
    }
}