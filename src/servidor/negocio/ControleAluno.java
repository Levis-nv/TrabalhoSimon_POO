package servidor.negocio;

import modelo.entidade.Aluno;
import modelo.entidade.Classificacao;
import servidor.repositorio.RepositorioAluno;

import java.util.ArrayList;

public class ControleAluno {
    private RepositorioAluno repositorio;

    public ControleAluno() {
        this.repositorio = RepositorioAluno.getInstancia();
    }

    public void CadastrarAluno(Aluno aluno) throws Exception {
        if (aluno.getPeso() <= 0 || aluno.getAltura() <= 0) {
            throw new Exception("Peso e altura devem ser maiores que zero!");
        }
        if (aluno.getIdade() < 0) {
            throw new Exception("A idade não pode ser negativa!");
        }

        // calculo imc
        double imc = aluno.getPeso() / (aluno.getAltura() * aluno.getAltura());
        aluno.setImc(imc);

        // cklassif
        if (imc < 18.5) {
            aluno.setClassificacao(Classificacao.MAGREZA);

        } else if (imc < 25) {
            aluno.setClassificacao(Classificacao.NORMAL);

        } else if (imc < 30) {
            aluno.setClassificacao(Classificacao.SOBREPESO);

        } else if (imc < 40) {
            aluno.setClassificacao(Classificacao.OBESIDADE);
        }

        repositorio.adicionarAluno(aluno);
    }

    public ArrayList<Aluno> listarAlunos() {
        return repositorio.listarTodos();
    }

    public Aluno buscarAluno(int id) {
        return repositorio.buscarPorId(id);
    }

    public void removerAluno(int id) {
        repositorio.remover(id);
    }
}