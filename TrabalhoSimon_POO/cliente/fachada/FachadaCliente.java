package simon.cliente.fachada;

import simon.modelo.entidade.*;
import simon.cliente.comunicacao.ComunicacaoCliente;

import java.io.IOException;

public class FachadaCliente {

    private ComunicacaoCliente comunicacao;

    public FachadaCliente(ComunicacaoCliente comunicacao) {
        this.comunicacao = comunicacao;
    }

    public String cadastrarAluno(Aluno aluno) throws IOException, ClassNotFoundException {
        return comunicacao.cadastrarAluno(aluno);
    }
}
