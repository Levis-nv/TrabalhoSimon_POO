package servidor.negocio;

import modelo.entidade.Nutricionista;
import servidor.repositorio.RepositorioNutricionista;

import java.util.ArrayList;

public class ControleNutricionista {

    private RepositorioNutricionista repositorio;
    private static ControleNutricionista instancia;

    public ControleNutricionista() {
        this.repositorio = RepositorioNutricionista.getInstance();
    }

    public static ControleNutricionista getInstance() {
        if(instancia == null) {
            instancia = new ControleNutricionista();
        }

        return instancia;
    }

    public boolean cadastrarNutricionista(Nutricionista nutricionista) {
        if (validacao(nutricionista)) {
            return repositorio.adicionarNuricionista(nutricionista);
        }

        return false;
    }

    public boolean alterarNutricionista(Nutricionista nutricionista) {
        if (nutricionista != null) {
            return repositorio.alterarNutricionista(nutricionista);
        }

        return false;
    }

    public boolean excluirNutricionista(int idNutricionista) {
        if (idNutricionista < 0) {
            return false;
        }

        return repositorio.excluirNutricionista(idNutricionista);
    }

    public ArrayList<Nutricionista> listarNutricionista() {
        return repositorio.listarTodos();
    }

    private boolean validacao(Nutricionista nutricionista) {
        if (nutricionista == null){
            return false;
        }
        return !repositorio.verificarNutricionista(nutricionista);
    }
}