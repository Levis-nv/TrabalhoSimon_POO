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
        if (aluno == null){
            return false;
        }

        if (aluno.getPeso() <= 0 || aluno.getAltura() <= 0 || aluno.getIdade() < 0) {
            return false;
        }

        // calculo imc
        double imc = aluno.getPeso() / (aluno.getAltura() * aluno.getAltura());
        aluno.setImc(imc);

        aluno.setClassificacao(Classificacao.gerarClassificacao(aluno.getImc()));

        return repositorio.adicionarAluno(aluno);
    }

    public boolean alterarAluno(Aluno aluno) {
        if (aluno == null) {
            return false;
        }

        if (aluno.getPeso() <= 0 || aluno.getAltura() <= 0 || aluno.getIdade() < 0) {
            return false;
        }

        double imc = aluno.getPeso() / (aluno.getAltura() * aluno.getAltura());
        aluno.setImc(imc);

        aluno.setClassificacao(Classificacao.gerarClassificacao(aluno.getImc()));


        return repositorio.alterar(aluno);
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

    public boolean excluirAluno(int idAluno) {
        if (idAluno < 0) {
            return false;
        }

        return repositorio.excluir(idAluno);
    }
}