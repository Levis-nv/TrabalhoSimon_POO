package servidor.negocio;

import simon.modelo.entidade.Aluno;
import simon.modelo.entidade.Classificacao;
import servidor.repositorio.AlunoRepositorio;

import java.util.ArrayList;
import java.util.List;

public class AlunoNegocio {
    private AlunoRepositorio repositorio;

    public AlunoNegocio() {
        this.repositorio = AlunoRepositorio.getInstancia();
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
        } else {}

        repositorio.adicionar(aluno);
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