package repositorio;

import modelo.Aluno;
import modelo.Nutricionista;
import java.util.ArrayList;

public class RepositorioNutricionista {

    private static RepositorioNutricionista instancia;
    private final ArrayList<Nutricionista> bancoDeNutricionista;
    private int id;

    private RepositorioNutricionista() {
        this.bancoDeNutricionista = new ArrayList<>();
    }

    public static RepositorioNutricionista getInstance() {
        if (instancia == null) {
            instancia = new RepositorioNutricionista();
        }
        return instancia;
    }

    public boolean adicionarNuricionista(Nutricionista nutricionista) {
        if(nutricionista != null) {
            nutricionista.setId(this.id);

            bancoDeNutricionista.add(nutricionista);
            this.id++;

            return true;
        }

        return false;
    }

    public boolean alterarNutricionista(Nutricionista nutricionistaAlterada) {
        int posicao = buscarPosicao(nutricionistaAlterada.getId());

        if(posicao != -1) {
            bancoDeNutricionista.set(posicao, nutricionistaAlterada);
            return true;
        }

        return false;
    }

    public boolean excluirNutricionista(int idNutricionista){
        int posicao = buscarPosicao(idNutricionista);

        if (posicao != -1) {
            bancoDeNutricionista.remove(posicao);
        }

        return false;
    }

    public ArrayList<Nutricionista> listarTodos() {
        return new ArrayList<>(bancoDeNutricionista);
    }

    public int buscarPosicao(int idNutricionista) {
        for (Nutricionista e : bancoDeNutricionista) {
            if (e.getId() == idNutricionista) {
                return bancoDeNutricionista.indexOf(e);
            }
        }

        return -1;
    }

    public boolean verificarNutricionista(Nutricionista nutricionista){
        for (Nutricionista n: bancoDeNutricionista) {
            if(n.getNomeUsuario().equalsIgnoreCase(nutricionista.getNomeUsuario())){
                return true;
            }
        }

        return false;
    }

    public Nutricionista buscarNutricionista(int idNutricionista) {
        int posicao = buscarPosicao(idNutricionista);
        if (posicao == -1) {
            return null;
        }

        return bancoDeNutricionista.get(posicao);
    }

    public boolean login(String user, String senha) {
        for (Nutricionista n: bancoDeNutricionista) {
            if (n.getNomeUsuario().equalsIgnoreCase(user) && n.getSenha().equalsIgnoreCase(senha)) {
                return true;
            }
        }

        return false;
    }
}
