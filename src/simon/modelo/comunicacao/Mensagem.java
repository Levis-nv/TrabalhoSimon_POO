package simon.modelo.comunicacao;

public class Mensagem {
    private Entidade entidade;
    private Operacao operacao;
    private Object dados;

    public Mensagem(Entidade entidade, Operacao operacao, Object dados){
        this.entidade = entidade;
        this.operacao = operacao;
        this.dados = dados;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public Object getDados() {
        return dados;
    }
}
