package cliente.ui;

import modelo.entidade.Aluno;
import modelo.entidade.Classificacao;
import modelo.entidade.Escola;
import modelo.entidade.Sexo;
import cliente.fachada.FachadaCliente;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UIAluno {

    private Scanner scan;
    private FachadaCliente fachada;

    public UIAluno(FachadaCliente fachada) {
        this.scan = new Scanner(System.in);
        this.fachada = fachada;
    }

    public void exibir() {
        int op;
        do {
            System.out.println("\n--- MENU ALUNOS ---");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Alterar aluno");
            System.out.println("3 - Excluir aluno");
            System.out.println("4 - Listar todos os alunos");
            System.out.println("5 - Listar alunos por categoria (IMC)");
            System.out.println("6 - Listar alunos por escola");
            System.out.println("7 - Listar alunos na faixa de risco");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            op = scan.nextInt();
            scan.nextLine(); // Limpar buffer

            switch (op) {
                case 1: add(); break;
                case 2: alterar(); break;
                case 3: excluir(); break;
                case 4: listarTodos(); break;
                case 5: listarPorCategoria(); break;
                case 6: listarPorEscola(); break;
                case 7: listarPorRisco(); break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    private void add() {
        try {
            System.out.print("Nome do aluno: ");
            String nome = scan.nextLine();

            System.out.println("Data de nascimento:");
            System.out.print("Dia: "); int dia = scan.nextInt();
            System.out.print("Mês: "); int mes = scan.nextInt();
            System.out.print("Ano: "); int ano = scan.nextInt();
            LocalDate data = LocalDate.of(ano, mes, dia);

            System.out.println("Sexo (1- Masculino | 2- Feminino): ");
            int codSexo = scan.nextInt();
            Sexo sexo = Sexo.selecionar(codSexo);

            System.out.print("Altura (ex: 1,70): ");
            double altura = scan.nextDouble();

            System.out.print("Peso (ex: 60,5): ");
            double peso = scan.nextDouble();

            listarEscolasDisponiveis();
            System.out.print("Digite o código da escola do aluno: ");
            int idEscola = scan.nextInt();
            Escola escola = fachada.buscarEscola(idEscola);

            if (escola == null) {
                System.out.println("Escola não encontrada. Cancelando cadastro.");
                return;
            }

            Aluno aluno = new Aluno(nome, data, sexo, altura, peso, escola);
            System.out.println(fachada.cadastrarAluno(aluno));

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    private void alterar() {
        try {
            listarTodos();
            System.out.print("Digite o ID do aluno que deseja alterar: ");
            int id = scan.nextInt();
            scan.nextLine();

            Aluno aluno = fachada.buscarAluno(id);
            if (aluno == null) {
                System.out.println("Aluno não encontrado.");
                return;
            }

            System.out.println("O que deseja alterar?");
            System.out.println("1- Nome | 2- Escola | 3- Altura | 4- Peso");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Novo nome: ");
                    aluno.setNome(scan.nextLine());
                    break;
                case 2:
                    listarEscolasDisponiveis();
                    System.out.print("Novo ID de Escola: ");
                    Escola esc = fachada.buscarEscola(scan.nextInt());
                    if (esc != null) aluno.setEscola(esc);
                    break;
                case 3:
                    System.out.print("Nova altura: ");
                    aluno.setAltura(scan.nextDouble());
                    break;
                case 4:
                    System.out.print("Novo peso: ");
                    aluno.setPeso(scan.nextDouble());
                    break;
            }

            System.out.println(fachada.alterarAluno(aluno));
        } catch (Exception e) {
            System.out.println("Erro ao alterar: " + e.getMessage());
        }
    }

    private void excluir() {
        try {
            listarTodos();
            System.out.print("Digite o ID do aluno a excluir: ");
            int id = scan.nextInt();
            System.out.println(fachada.excluirAluno(id));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarTodos() {
        try {
            imprimirTabelaAlunos(fachada.listarAluno());
        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

    private void listarPorCategoria() {
        System.out.println("Categorias: 1- Magreza | 2- Normal | 3- Sobrepeso | 4- Obesidade");
        int op = scan.nextInt();
        Classificacao c = switch(op) {
            case 1 -> Classificacao.MAGREZA;
            case 2 -> Classificacao.NORMAL;
            case 3 -> Classificacao.SOBREPESO;
            case 4 -> Classificacao.OBESIDADE;
            default -> Classificacao.NORMAL;
        };
        try {
            imprimirTabelaAlunos(fachada.listarAlunoCategoria(c));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarPorEscola() {
        try {
            listarEscolasDisponiveis();
            System.out.print("Digite o ID da Escola: ");
            int id = scan.nextInt();
            imprimirTabelaAlunos(fachada.listarAlunoEscola(id));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarPorRisco() {
        try {
            imprimirTabelaAlunos(fachada.listarAlunoRisco());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarEscolasDisponiveis() throws Exception {
        System.out.println("Escolas disponíveis:");
        for (Escola e : fachada.listarEscola()) {
            System.out.println("ID: " + e.getId() + " - " + e.getNome());
        }
    }

    private void imprimirTabelaAlunos(ArrayList<Aluno> arr) {
        if (arr == null || arr.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
            return;
        }
        System.out.printf("%-5s %-15s %-15s %-10s %-10s %-15s\n", "ID", "Nome", "Escola", "Altura", "Peso", "Classificação");
        for (Aluno a : arr) {
            String nomeEscola = a.getEscola() != null ? a.getEscola().getNome() : "Sem escola";
            System.out.printf("%-5d %-15s %-15s %-10.2f %-10.2f %-15s\n",
                    a.getId(), a.getNome(), nomeEscola, a.getAltura(), a.getPeso(), a.getClassificacao());
        }
    }
}