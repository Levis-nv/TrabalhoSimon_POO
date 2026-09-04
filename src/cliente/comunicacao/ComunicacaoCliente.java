package cliente.comunicacao;

import modelo.comunicacao.Entidade;
import modelo.comunicacao.Mensagem;
import modelo.comunicacao.Operacao;
import modelo.entidade.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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

    public String alterarAluno(Aluno aluno) throws IOException, ClassNotFoundException {
        Mensagem mensagem = new Mensagem(Entidade.ALUNO, Operacao.ALTERAR, aluno);

        output.writeObject(mensagem);
        output.flush();

        Mensagem resposta = (Mensagem) input.readObject();
        return (String) resposta.getDados();
    }

    public String excluirAluno(int idAluno) throws IOException, ClassNotFoundException {
        Mensagem mensagem = new Mensagem(Entidade.ALUNO, Operacao.EXCLUIR, idAluno);

        output.writeObject(mensagem);
        output.flush();

        Mensagem resposta = (Mensagem) input.readObject();
        return (String) resposta.getDados();
    }

    public ArrayList<Aluno> listarAluno() throws IOException, ClassNotFoundException {
        Mensagem mensagem = new Mensagem(Entidade.ALUNO, Operacao.LISTAR);

        output.writeObject(mensagem);
        output.flush();

        Mensagem resposta = (Mensagem) input.readObject();
        return (ArrayList<Aluno>) resposta.getDados();
    }

    public ArrayList<Aluno> listarAlunoCategoria() throws IOException, ClassNotFoundException {
        Mensagem mensagem = new Mensagem(Entidade.ALUNO, Operacao.LISTAR_CATEGORIA);

        output.writeObject(mensagem);
        output.flush();

        Mensagem resposta = (Mensagem) input.readObject();
        return (ArrayList<Aluno>) resposta.getDados();
    }

    public ArrayList<Aluno> listarAlunoEscola(int idEscola) throws IOException, ClassNotFoundException {
        Mensagem mensagem = new Mensagem(Entidade.ALUNO, Operacao.LISTAR_ESCOLA, idEscola);

        output.writeObject(mensagem);
        output.flush();

        Mensagem resposta = (Mensagem) input.readObject();
        return (ArrayList<Aluno>) resposta.getDados();
    }

    public ArrayList<Aluno> listarAlunoRisco() throws IOException, ClassNotFoundException {
        Mensagem mensagem = new Mensagem(Entidade.ALUNO, Operacao.LISTAR_RISCO);

        output.writeObject(mensagem);
        output.flush();

        Mensagem resposta = (Mensagem) input.readObject();
        return (ArrayList<Aluno>) resposta.getDados();
    }

    public String cadastrarEscola(Escola escola) throws IOException, ClassNotFoundException {
        Mensagem mensagem = new Mensagem(Entidade.ESCOLA, Operacao.CADASTRAR, escola);

        output.writeObject(mensagem);
        output.flush();

        Mensagem resposta = (Mensagem) input.readObject();
        return (String) resposta.getDados();
    }

    public String alterarEscola(Escola escola) throws IOException, ClassNotFoundException {
        Mensagem mensagem = new Mensagem(Entidade.ESCOLA, Operacao.ALTERAR, escola);

        output.writeObject(mensagem);
        output.flush();

        Mensagem resposta = (Mensagem) input.readObject();
        return (String) resposta.getDados();
    }

    public String excluirEscola(int idEscola) throws IOException, ClassNotFoundException {
        Mensagem mensagem = new Mensagem(Entidade.ESCOLA, Operacao.EXCLUIR, idEscola);

        output.writeObject(mensagem);
        output.flush();

        Mensagem resposta = (Mensagem) input.readObject();
        return (String) resposta.getDados();
    }

    public ArrayList<Escola> listarEscola() throws IOException, ClassNotFoundException {
        Mensagem mensagem = new Mensagem(Entidade.ESCOLA, Operacao.LISTAR);

        output.writeObject(mensagem);
        output.flush();

        Mensagem resposta = (Mensagem) input.readObject();
        return (ArrayList<Escola>) resposta.getDados();
    }
}
