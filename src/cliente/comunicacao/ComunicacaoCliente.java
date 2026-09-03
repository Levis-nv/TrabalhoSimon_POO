package cliente.comunicacao;

import modelo.comunicacao.Entidade;
import modelo.comunicacao.Mensagem;
import modelo.comunicacao.Operacao;
import modelo.entidade.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ComunicacaoCliente {

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private static ComunicacaoCliente instancia;

    public ComunicacaoCliente(String ip, int porta) throws IOException {
        this.socket = new Socket(ip, porta);

        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.input = new ObjectInputStream(socket.getInputStream());
    }

    public static ComunicacaoCliente getInstance(String ip, int porta) throws IOException {
        if(instancia == null){
            instancia = new ComunicacaoCliente(ip, porta);
        }

        return instancia;
    }

    public String cadastrarAluno(Aluno aluno) throws IOException, ClassNotFoundException {
        Mensagem mensagem = new Mensagem(Entidade.ALUNO, Operacao.CADASTRAR, aluno);

        output.writeObject(mensagem);
        output.flush();

        Mensagem resposta = (Mensagem) input.readObject();
        return (String) resposta.getDados();
    }
}
