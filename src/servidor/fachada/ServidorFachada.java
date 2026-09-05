package servidor.fachada;

import modelo.entidade.Aluno;
import modelo.entidade.Classificacao;
import servidor.negocio.ControleAluno;

import java.util.ArrayList;

public class ServidorFachada {
    private ControleAluno controleAluno;
    private static ServidorFachada instancia;

    private ServidorFachada() {
        this.controleAluno = ControleAluno.getInstance();
    }

    public static ServidorFachada getInstancia() {
        if (instancia == null) {
            instancia = new ServidorFachada();
        }
        return instancia;
    }

    public boolean cadastrarAluno(Aluno aluno) {
        return controleAluno.cadastrarAluno(aluno);
    }

    public boolean alterarAluno(Aluno aluno){
        return controleAluno.alterarAluno(aluno);
    }

    public ArrayList<Aluno> listarAlunos() {
        return controleAluno.listarAlunos();
    }

    public ArrayList<Aluno> listarPorEscola(int idEscola) {
        return controleAluno.listarPorEscola(idEscola);
    }

    public ArrayList<Aluno> listarPorCategoria(Classificacao categoria){
        return controleAluno.listarPorCategoria(categoria);
    }

    public ArrayList<Aluno> listarFaixaDeRisco(){
        return controleAluno.listarFaixaDeRisco();
    }

    public void removerAluno(int id) {
        controleAluno.excluirAluno(id);
    }
}