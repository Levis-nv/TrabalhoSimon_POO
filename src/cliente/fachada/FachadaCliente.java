package cliente.fachada;

import modelo.entidade.*;
import cliente.comunicacao.ComunicacaoCliente;

import java.io.IOException;
import java.util.ArrayList;

public class FachadaCliente {

    private ComunicacaoCliente comunicacao;
    private static FachadaCliente instancia;

    private FachadaCliente(ComunicacaoCliente comunicacao) {
        this.comunicacao = comunicacao;
    }

    public static FachadaCliente getInstance(ComunicacaoCliente comunicacao) {
        if (instancia == null) {
            instancia = new FachadaCliente(comunicacao);
        }

        return instancia;
    }

    public Boolean cadastrarAluno(Aluno aluno) throws IOException, ClassNotFoundException {
        return comunicacao.cadastrarAluno(aluno);
    }

    public Boolean alterarAluno(Aluno aluno) throws IOException, ClassNotFoundException {
        return comunicacao.alterarAluno(aluno);
    }

    public Boolean excluirAluno(int idAluno) throws IOException, ClassNotFoundException {
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

    public Aluno buscarAluno(int codigo) throws IOException, ClassNotFoundException {
        return comunicacao.buscarAluno(codigo);
    }

    // ESCOLA

    public Boolean cadastrarEscola(Escola escola) throws IOException, ClassNotFoundException {
        return comunicacao.cadastrarEscola(escola);
    }

    public Boolean alterarEscola(Escola escola) throws IOException, ClassNotFoundException {
        return comunicacao.alterarEscola(escola);
    }

    public Boolean excluirEscola(int idEscola) throws IOException, ClassNotFoundException {
        return comunicacao.excluirEscola(idEscola);
    }

    public ArrayList<Escola> listarEscola()  throws IOException, ClassNotFoundException {
        return comunicacao.listarEscola();
    }

    public Escola buscarEscola(int codigo) throws IOException, ClassNotFoundException {
        return comunicacao.buscarEscola(codigo);
    }

    public Boolean cadastrarNutricionista(Nutricionista nutricionista) throws IOException, ClassNotFoundException {
        return comunicacao.cadastrarNutricionista(nutricionista);
    }

    public Boolean alterarNutricionista(Nutricionista nutricionista) throws IOException, ClassNotFoundException {
        return comunicacao.alterarNutricionista(nutricionista);
    }

    public Boolean excluirNutricionista(int idNutricionista) throws IOException, ClassNotFoundException {
        return comunicacao.excluirNutricionista(idNutricionista);
    }

    public ArrayList<Nutricionista> listarNutricionista()  throws IOException, ClassNotFoundException {
        return comunicacao.listarNutricionista();
    }

    public Nutricionista buscarNutricionista(int codigo) throws IOException, ClassNotFoundException {
        return comunicacao.buscarNutricionista(codigo);
    }
}
