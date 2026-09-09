package negocio;

import modelo.*;

import java.io.IOException;
import java.util.ArrayList;

public class Fachada {
    private ControleAluno controleAluno;
    private ControleEscola controleEscola;
    private static Fachada instancia;
    private ControleNutricionista controleNutricionista;
    private GeradorRelatorio geradorRelatorio;

    private Fachada() {
        this.controleAluno = ControleAluno.getInstance();
        this.controleEscola = ControleEscola.getInstance();
        this.controleNutricionista = ControleNutricionista.getInstance();
        this.geradorRelatorio = GeradorRelatorio.getInstance();
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
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

    public Aluno buscarAluno(int idAluno) {
        return controleAluno.buscarAluno(idAluno);
    }

    public boolean avaliacao(Avaliacao avaliacao) {
        return controleAluno.avaliacao(avaliacao);
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

    public Escola buscarEscola(int idEscola) {
        return controleEscola.buscarEscola(idEscola);
    }

    // NUTRICIONISTA

    public Boolean cadastrarNutricionista(Nutricionista nutricionista) {
        return controleNutricionista.cadastrarNutricionista(nutricionista);
    }

    public Boolean alterarNutricionista(Nutricionista nutricionista) {
        return controleNutricionista.alterarNutricionista(nutricionista);
    }

    public Boolean excluirNutricionista(int idNutricionista) {
        return controleNutricionista.excluirNutricionista(idNutricionista);
    }

    public ArrayList<Nutricionista> listarNutricionista() {
        return controleNutricionista.listarNutricionista();
    }

    public Nutricionista buscarNutricionista(int idNutricionista) {
        return controleNutricionista.buscarNutricionista(idNutricionista);
    }

    public boolean login(String user, String senha) {
        return controleNutricionista.login(user, senha);
    }

    // RELATORIOS

    public void gerarRelatorioAlunos(ArrayList<Aluno> alunos) throws IOException {
        geradorRelatorio.gerarRelatorioAlunos(alunos);
    }

    public void gerarRelatorioEscola(Escola escola, ArrayList<Aluno> alunos) throws IOException {
        geradorRelatorio.gerarRelatorioEscola(escola, alunos);
    }

    public void gerarRelatorioGeral(ArrayList<Aluno> alunos) throws IOException {
        geradorRelatorio.gerarRelatorioGeral(alunos);
    }

}