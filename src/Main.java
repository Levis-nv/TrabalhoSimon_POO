import negocio.Fachada;
import modelo.*;
import ui.UILogin;
import ui.UIMain;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    static void main(String[] args) throws IOException {
        Fachada fachada = Fachada.getInstancia();

        init(fachada);

        UILogin uiLogin = new UILogin(fachada);

        uiLogin.exibir();
    }


    public static void init(Fachada fachada) {

        Escola escola1 = new Escola("Escola Central");
        Escola escola2 = new Escola("Escola Simon");

        fachada.cadastrarEscola(escola1);
        fachada.cadastrarEscola(escola2);

        // Depois cria os alunos
        Aluno aluno1 = new Aluno(
                "João Silva",
                LocalDate.of(2009, 5, 12),
                Sexo.MASCULINO,
                68.0,
                1.75,
                escola1
        );

        Aluno aluno2 = new Aluno(
                "Maria Souza",
                LocalDate.of(2008, 8, 20),
                Sexo.FEMININO,
                55.0,
                1.62,
                escola1
        );

        Aluno aluno3 = new Aluno(
                "Pedro Santos",
                LocalDate.of(2009, 2, 10),
                Sexo.MASCULINO,
                82.0,
                1.80,
                escola2
        );

        fachada.cadastrarAluno(aluno1);
        fachada.cadastrarAluno(aluno2);
        fachada.cadastrarAluno(aluno3);
    }

}
