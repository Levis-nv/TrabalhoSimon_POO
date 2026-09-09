package negocio;

import modelo.Escola;
import repositorio.RepositorioEscola;

import java.util.ArrayList;

public class ControleEscola {

    private RepositorioEscola repositorio;
    private static ControleEscola instancia;

    private ControleEscola() {
        this.repositorio = RepositorioEscola.getInstancia();
    }

    public static ControleEscola getInstance() {
        if(instancia == null) {
            instancia = new ControleEscola();
        }

        return instancia;
    }

    public boolean cadastrarEscola(Escola escola) {

        if (!validacao(escola)) {

            return repositorio.adicionarEscola(escola);
        }

        return false;
    }

    public boolean alterarEscola(Escola escola) {

        if (validacao(escola)) {

            return repositorio.alterarEscola(escola);
        }

        return false;
    }

    public boolean excluirEscola(int idEscola) {
        if (idEscola < 0) {
            return false;
        }

        return repositorio.excluirEscola(idEscola);
    }

    public ArrayList<Escola> listarEscolas() {
        return repositorio.listarTodos();
    }

    private boolean validacao(Escola escola) {
        if (escola == null){
            return false;
        }

        return repositorio.verificarEscola(escola);
    }

    public Escola buscarEscola(int idEscola) {
        return repositorio.buscarEscola(idEscola);
    }
}
