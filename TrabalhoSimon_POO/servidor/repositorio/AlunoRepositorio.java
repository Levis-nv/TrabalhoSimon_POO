package simon.servidor.repositorio;

import simon.modelo.entidade.Aluno;
import java.util.ArrayList;

public class AlunoRepositorio {

    private static AlunoRepositorio instancia;
    private ArrayList<Aluno> bancoDeAlunos;

    private AlunoRepositorio() {
        this.bancoDeAlunos = new ArrayList<>();
    }

    public static AlunoRepositorio getInstancia() {
        if (instancia == null) {
            instancia = new AlunoRepositorio();
        }
        return instancia;
    }

    public void adicionar(Aluno aluno) {
        bancoDeAlunos.add(aluno);
    }

    public ArrayList<Aluno> listarTodos() {
        return bancoDeAlunos;
    }

    public Aluno buscarPorId(int id) {
        for (Aluno a : bancoDeAlunos) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public void atualizar(Aluno alunoAtualizado) {
        for (int i = 0; i < bancoDeAlunos.size(); i++) {
            if (bancoDeAlunos.get(i).getId() == alunoAtualizado.getId()) {
                bancoDeAlunos.set(i, alunoAtualizado);
                break;
            }
        }
    }

    public void remover(int id) {
        bancoDeAlunos.removeIf(a -> a.getId() == id);
    }
}