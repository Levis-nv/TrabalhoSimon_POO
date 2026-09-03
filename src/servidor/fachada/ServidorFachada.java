package servidor.fachada;

import simon.modelo.entidade.Aluno;
import servidor.negocio.AlunoNegocio;
import java.util.List;

public class ServidorFachada {
    private static ServidorFachada instancia;
    private AlunoNegocio alunoNegocio;

    private ServidorFachada() {
        this.alunoNegocio = new AlunoNegocio();
    }

    public static ServidorFachada getInstancia() {
        if (instancia == null) {
            instancia = new ServidorFachada();
        }
        return instancia;
    }

    public void cadastrarAluno(Aluno aluno) throws Exception {
        alunoNegocio.CadastrarAluno(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoNegocio.listarAlunos();
    }

    public Aluno buscarAlunoPorId(int id) {
        return alunoNegocio.buscarAluno(id);
    }

    public void removerAluno(int id) {
        alunoNegocio.removerAluno(id);
    }
}