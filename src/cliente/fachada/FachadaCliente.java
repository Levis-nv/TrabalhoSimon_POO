package cliente.fachada;

import modelo.entidade.*;
import cliente.comunicacao.ComunicacaoCliente;

import java.io.IOException;
import java.util.ArrayList;

public class FachadaCliente {

    private ComunicacaoCliente comunicacao;
    private static FachadaCliente instancia;

    public FachadaCliente(ComunicacaoCliente comunicacao) {
        this.comunicacao = comunicacao;
    }

    public static FachadaCliente getInstance(ComunicacaoCliente comunicacao) {
        if (instancia == null) {
            instancia = new FachadaCliente(comunicacao);
        }

        return instancia;
    }

    public String cadastrarAluno(Aluno aluno) throws IOException, ClassNotFoundException {
        return comunicacao.cadastrarAluno(aluno);
    }

    public String alterarAluno(Aluno aluno) throws IOException, ClassNotFoundException {
        return comunicacao.alterarAluno(aluno);
    }

    public String excluirAluno(int idAluno) throws IOException, ClassNotFoundException {
        return comunicacao.excluirAluno(idAluno);
    }

    public ArrayList<Aluno> listarAluno() throws IOException, ClassNotFoundException {
        return comunicacao.listarAluno();
    }

    public ArrayList<Aluno> listarAlunoCategoria(Classificacao categoria) throws IOException, ClassNotFoundException {
        return comunicacao.listarAlunoCategoria(categoria);
    }

    public ArrayList<Aluno> listarAlunoEscola(int idEscola) throws IOException, ClassNotFoundException {
        return comunicacao.listarAlunoEscola(idEscola);
    }

    public ArrayList<Aluno> listarAlunoRisco() throws IOException, ClassNotFoundException {
        return comunicacao.listarAlunoRisco();
    }

    // ESCOLA

    public String cadastrarEscola(Escola escola) throws IOException, ClassNotFoundException {
        return comunicacao.cadastrarEscola(escola);
    }

    public String alterarEscola(Escola escola) throws IOException, ClassNotFoundException {
        return comunicacao.alterarEscola(escola);
    }

    public String excluirEscola(int idEscola) throws IOException, ClassNotFoundException {
        return comunicacao.excluirEscola(idEscola);
    }

    public ArrayList<Escola> listarEscola()  throws IOException, ClassNotFoundException {
        return comunicacao.listarEscola();
    }
}
