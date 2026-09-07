package servidor.fachada;

import modelo.entidade.Aluno;
import modelo.entidade.Escola;
import modelo.entidade.Classificacao;
import modelo.entidade.Nutricionista;
import servidor.negocio.ControleNutricionista;

import servidor.negocio.ControleAluno;
import servidor.negocio.ControleEscola;

import java.util.ArrayList;

public class ServidorFachada {
    private ControleAluno controleAluno;
    private ControleEscola controleEscola;
    private static ServidorFachada instancia;
    private ControleNutricionista controleNutricionista;

    private ServidorFachada() {
        this.controleAluno = ControleAluno.getInstance();
        this.controleEscola = ControleEscola.getInstance();
        this.controleNutricionista = ControleNutricionista.getInstance();
    }

    public static ServidorFachada getInstancia() {
        if (instancia == null) {
            instancia = new ServidorFachada();
        }
        return instancia;
    }

    // ALUNO

    public boolean cadastrarAluno(Aluno aluno) {
        return controleAluno.cadastrarAluno(aluno);
    }

    public boolean alterarAluno(Aluno aluno){
        return controleAluno.alterarAluno(aluno);
    }

    public boolean removerAluno(Aluno aluno) {
        return controleAluno.excluirAluno(aluno.getId());
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

    // ESCOLA

    public boolean cadastrarEscola(Escola escola){
        return controleEscola.cadastrarEscola(escola);
    }

    public boolean alterarEscola(Escola escola) {
        return controleEscola.alterarEscola(escola);
    }

    public boolean removerEscola(Escola escola){
        return controleEscola.excluirEscola(escola.getId());
    }

    public ArrayList<Escola> listarEscola() {
        return controleEscola.listarEscolas();
    }

    // NUTRICIONISTA
    public boolean cadastrarNutricionista(Nutricionista nutricionista) {
        return controleNutricionista.cadastrarNutricionista(nutricionista);
    }

    public boolean alterarNutricionista(Nutricionista nutricionista) {
        return controleNutricionista.alterarNutricionista(nutricionista);
    }

    public boolean removerNutricionista(Nutricionista nutricionista) {
        return controleNutricionista.excluirNutricionista(nutricionista.getId());
    }

    public ArrayList<Nutricionista> listarNutricionista() {
        return controleNutricionista.listarNutricionista();
    }
}