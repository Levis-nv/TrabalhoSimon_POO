package servidor.repositorio;

import modelo.entidade.Escola;
import java.util.ArrayList;

public class RepositorioEscola {

    private static RepositorioEscola instancia;
    private final ArrayList<Escola> bancoDeEscola;
    private int id;

    public RepositorioEscola(){
        this.bancoDeEscola = new ArrayList<>();
    }

    public static RepositorioEscola getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioEscola();
        }
        return instancia;
    }

    public boolean adicionarEscola(Escola escola) {
        if(escola != null) {
            escola.setId(this.id);

            bancoDeEscola.add(escola);
            this.id++;

            return true;
        }

        return false;
    }

    public boolean alterarEscola(Escola escolaAlterada) {
        int posicao = buscarPosicao(escolaAlterada.getId());

        if(posicao != -1) {
            bancoDeEscola.set(posicao, escolaAlterada);
            return true;
        }

        return false;
    }

    public boolean excluirEscola(int idEscola){
        int posicao = buscarPosicao(idEscola);

        if (posicao != -1) {
            bancoDeEscola.remove(posicao);
        }

        return false;
    }

    public ArrayList<Escola> listarTodos() {
        return new ArrayList<>(bancoDeEscola);
    }

    public int buscarPosicao(int idEscola) {
        for (Escola e : bancoDeEscola) {
            if (e.getId() == idEscola) {
                return bancoDeEscola.indexOf(e);
            }
        }

        return -1;
    }

    public boolean verificarEscola(Escola escola){
        for (Escola e: bancoDeEscola) {
            if(e.getNome().equalsIgnoreCase(escola.getNome())){
                return true;
            }
        }

        return false;
    }

}
