package servidor.fachada;

import modelo.entidade.Aluno;
import servidor.negocio.ControleAluno;
import java.util.List;

public class ServidorFachada {
    private static ServidorFachada instancia;
    private ControleAluno controleAluno;

    private ServidorFachada() {
        this.controleAluno = new ControleAluno();
    }

    public static ServidorFachada getInstancia() {
        if (instancia == null) {
            instancia = new ServidorFachada();
        }
        return instancia;
    }

    public void cadastrarAluno(Aluno aluno) throws Exception {
        controleAluno.CadastrarAluno(aluno);
    }

    public List<Aluno> listarAlunos() {
        return controleAluno.listarAlunos();
    }

    public Aluno buscarAlunoPorId(int id) {
        return controleAluno.buscarAluno(id);
    }

    public void removerAluno(int id) {
        controleAluno.removerAluno(id);
    }
}