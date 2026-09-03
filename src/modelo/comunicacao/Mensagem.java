package modelo.comunicacao;

public class Mensagem {
    private Entidade entidade;
    private Operacao operacao;
    private Object dados;

    public Mensagem(Entidade entidade, Operacao operacao, Object dados) {
        this.entidade = entidade;
        this.operacao = operacao;
        this.dados = dados;
    }

    // MENSAGEM PARA REQUISIÇÃO DE LISTAGEM
    public Mensagem(Entidade entidade, Operacao operacao) {
        this.entidade = entidade;
        this.operacao = operacao;
        this.dados = null;
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
