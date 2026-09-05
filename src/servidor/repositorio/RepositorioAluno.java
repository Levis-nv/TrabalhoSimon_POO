package servidor.repositorio;

import modelo.entidade.Aluno;
import modelo.entidade.Classificacao;
import java.util.ArrayList;

public class RepositorioAluno {

    private static RepositorioAluno instancia;
    private ArrayList<Aluno> bancoDeAlunos;
    private int id = 0;

    private RepositorioAluno() {
        this.bancoDeAlunos = new ArrayList<Aluno>();
    }

    public static RepositorioAluno getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioAluno();
        }
        return instancia;
    }

    public boolean adicionarAluno(Aluno aluno) {
        if(aluno != null) {
            aluno.setId(this.id);

            bancoDeAlunos.add(aluno);
            this.id++;

            return true;
        }

        return false;
    }

    public boolean alterar(Aluno alunoAlterado) {
        int posicao = buscarPosicao(alunoAlterado.getId());

        if(posicao != -1) {
            bancoDeAlunos.set(posicao, alunoAlterado);
            return true;
        }

        return false;
    }

    public boolean excluir(int idAluno){
        int posicao = buscarPosicao(idAluno);

        if (posicao != -1) {
            bancoDeAlunos.remove(posicao);
        }

        return false;
    }

    public ArrayList<Aluno> listarTodos() {
        return bancoDeAlunos;
    }

    public ArrayList<Aluno> listarPorEscola(int idEscola){
        ArrayList<Aluno> lista = new ArrayList<Aluno>();

        for (Aluno a: bancoDeAlunos){
            if (a.getEscola().getId() == idEscola){
                lista.add(a);
            }
        }

        return lista;
    }

    public ArrayList<Aluno> listarPorCategoria(Classificacao categoria){
        ArrayList<Aluno> lista = new ArrayList<Aluno>();

        for (Aluno a : bancoDeAlunos) {
            if (a.getClassificacao() == categoria){
                lista.add(a);
            }
        }

        return lista;
    }

    public ArrayList<Aluno> listarFaixaDeRisco(){
        ArrayList<Aluno> lista = new ArrayList<Aluno>();

        for (Aluno a : bancoDeAlunos) {
            if (a.getImc() < 18 || a.getImc() >= 30){
                lista.add(a);
            }
        }

        return lista;
    }

    public int buscarPosicao(int idAluno) {
        for (Aluno a : bancoDeAlunos) {
            if (a.getId() == idAluno) {
                return bancoDeAlunos.indexOf(a);
            }
        }

        return -1;
    }

}