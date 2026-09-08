package servidor.comunicacao;

import modelo.comunicacao.*;
import modelo.entidade.Aluno;
import modelo.entidade.Classificacao;
import modelo.entidade.Escola;
import modelo.entidade.Nutricionista;
import servidor.fachada.ServidorFachada;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ComunicacaoServidor {

    private ServerSocket serverSocket;
    private ServidorFachada fachada;

    public ComunicacaoServidor(int porta) throws IOException {
        this.serverSocket = new ServerSocket(porta);
        this.fachada = ServidorFachada.getInstancia();
    }

    public void iniciar() {
        System.out.println("Servidor escutando na porta " + serverSocket.getLocalPort());

        while (true) {
            try {
                Socket clienteSocket = serverSocket.accept();

                ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream());

                Mensagem mensagemRecebida = (Mensagem) in.readObject();
                Mensagem mensagemResposta = processarMensagem(mensagemRecebida);

                out.writeObject(mensagemResposta);
                out.flush();

                clienteSocket.close();
            } catch (Exception e) {
                System.out.println("Erro na conexao: " + e.getMessage());
            }
        }
    }

    private Mensagem processarMensagem(Mensagem msg) {
        Entidade entidade = msg.getEntidade();

        if (entidade == Entidade.ALUNO) {
            return tratarAluno(msg);
        } else if (entidade == Entidade.ESCOLA) {
            return tratarEscola(msg);
        } else if (entidade == Entidade.NUTRICIONISTA) {
            return tratarNutricionista(msg);
        }

        return new Mensagem(entidade, msg.getOperacao(), "Entidade nao encontrada");
    }

    private Mensagem tratarAluno(Mensagem msg) {
        Operacao op = msg.getOperacao();

        switch (msg.getOperacao()) {
            case CADASTRAR:
                if (fachada.cadastrarAluno((Aluno) msg.getDados())) {
                    return new Mensagem(null, null, true);
                } else {
                    return new Mensagem(null, null, false);
                }

            case ALTERAR:
                if (fachada.alterarAluno((Aluno) msg.getDados())) {
                    return new Mensagem(null, null, true);
                } else {
                    return new Mensagem(null, null, false);
                }

            case EXCLUIR:
                if (fachada.removerAluno((Aluno) msg.getDados())) {
                    return new Mensagem(null, null, true);
                } else {
                    return new Mensagem(null, null, false);
                }

            case LISTAR_CATEGORIA:
                return new Mensagem(null, null, fachada.listarPorCategoria((Classificacao) msg.getDados()));

            case LISTAR_ESCOLA:
                return new Mensagem(null, null, fachada.listarPorEscola((int) msg.getDados()));

            case LISTAR_RISCO:
                return new Mensagem(null, null, fachada.listarFaixaDeRisco());

            case BUSCAR:
                return new Mensagem(null, null, fachada.);
        }


        } else if (op == Operacao.BUSCAR) {
            return new Mensagem(msg.getEntidade(), op, "Buscar não implementado");
        }

        return new Mensagem(msg.getEntidade(), op, "Operacao invalida");
    }

    private Mensagem tratarEscola(Mensagem msg) {
        Operacao op = msg.getOperacao();

        if (op == Operacao.CADASTRAR) {
            Escola escola = (Escola) msg.getDados();
            boolean sucesso = fachada.cadastrarEscola(escola);
            return new Mensagem(msg.getEntidade(), op, sucesso ? "Escola cadastrada" : "Erro ao cadastrar escola");

        } else if (op == Operacao.ALTERAR) {
            Escola escola = (Escola) msg.getDados();
            boolean sucesso = fachada.alterarEscola(escola);
            return new Mensagem(msg.getEntidade(), op, sucesso ? "Escola alterada" : "Erro ao alterar escola");

        } else if (op == Operacao.EXCLUIR) {
            int idEscola = (int) msg.getDados();
            Escola escola = new Escola();
            escola.setId(idEscola);
            boolean sucesso = fachada.removerEscola(escola);
            return new Mensagem(msg.getEntidade(), op, sucesso ? "Escola excluida" : "Erro ao excluir escola");

        } else if (op == Operacao.LISTAR) {
            ArrayList<Escola> lista = fachada.listarEscola();
            return new Mensagem(msg.getEntidade(), op, lista);
        }

        return new Mensagem(msg.getEntidade(), op, "Operacao invalida");
    }

    private Mensagem tratarNutricionista(Mensagem msg) {
        Operacao op = msg.getOperacao();

        if (op == Operacao.CADASTRAR) {
            Nutricionista nutri = (Nutricionista) msg.getDados();
            boolean sucesso = fachada.cadastrarNutricionista(nutri);
            return new Mensagem(msg.getEntidade(), op, sucesso ? "Nutricionista cadastrado" : "Erro ao cadastrar");

        } else if (op == Operacao.ALTERAR) {
            Nutricionista nutri = (Nutricionista) msg.getDados();
            boolean sucesso = fachada.alterarNutricionista(nutri);
            return new Mensagem(msg.getEntidade(), op, sucesso ? "Nutricionista alterado" : "Erro ao alterar");

        } else if (op == Operacao.EXCLUIR) {
            int idNutri = (int) msg.getDados();
            Nutricionista nutri = new Nutricionista("", "", "");
            nutri.setId(idNutri);
            boolean sucesso = fachada.removerNutricionista(nutri);
            return new Mensagem(msg.getEntidade(), op, sucesso ? "Nutricionista excluido" : "Erro ao excluir");

        } else if (op == Operacao.LISTAR) {
            ArrayList<Nutricionista> lista = fachada.listarNutricionista();
            return new Mensagem(msg.getEntidade(), op, lista);
        }

        return new Mensagem(msg.getEntidade(), op, "Operacao invalida");
    }
}