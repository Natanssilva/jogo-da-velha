package jogo_da_velha;

import java.util.Scanner;

public class Controller {
    private Model model; // Referência ao modelo do jogo
    private View view; // Referência à visualização do jogo
    private Scanner scanner; // Scanner para entrada do usuário

    // Construtor da classe Controller
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        scanner = new Scanner(System.in); // Inicializa o scanner para leitura de entrada do usuário
    }

    // Método para iniciar o jogo
    public void iniciarJogo() {
        view.exibirMensagem("Bem-vindo ao Jogo da Velha!");
        view.exibirMensagem("Escolha 'X' ou 'O': ");
        char jogadorEscolhido = escolherJogador(); // Usuário escolhe seu símbolo ('X' ou 'O')
        char jogadorComputador = (jogadorEscolhido == 'X') ? 'O' : 'X'; // Computador fica com o símbolo restante

        while (true) {
            view.mostrarTabuleiro(model.getTabuleiro()); // Exibe o tabuleiro atual
            if (jogadorEscolhido == model.getJogadorAtual()) {
                view.exibirMensagem("Sua vez, jogador " + jogadorEscolhido + ". Informe sua jogada (linha coluna):");

                // Obtém a entrada do usuário para linha e coluna
                int linha = obterEntrada("Linha:");
                int coluna = obterEntrada("Coluna:");

                // Tenta fazer o movimento do jogador
                if (model.fazerMovimento(linha, coluna)) {
                    // Verifica se o jogador venceu
                    if (model.verificarVitoria()) {
                        view.mostrarTabuleiro(model.getTabuleiro());
                        view.exibirMensagem("Parabéns! Jogador " + jogadorEscolhido + " venceu!");
                        break;
                        // Verifica se o tabuleiro está cheio, indicando um empate
                    } else if (model.tabuleiroCheio()) {
                        view.mostrarTabuleiro(model.getTabuleiro());
                        view.exibirMensagem("O jogo terminou em empate!");
                        break;
                    }
                    model.trocarJogador(); // Troca para o próximo jogador
                } else {
                    view.exibirMensagem("Movimento inválido. Tente novamente."); // Movimento inválido
                }
            } else {
                // Computador faz sua jogada
                int[] movimento = calcularMovimento();
                model.fazerMovimento(movimento[0], movimento[1]);
                // Verifica se o computador venceu
                if (model.verificarVitoria()) {
                    view.mostrarTabuleiro(model.getTabuleiro());
                    view.exibirMensagem("Você perdeu! O computador venceu.");
                    break;
                    // Verifica se o tabuleiro está cheio, indicando um empate
                } else if (model.tabuleiroCheio()) {
                    view.mostrarTabuleiro(model.getTabuleiro());
                    view.exibirMensagem("O jogo terminou em empate!");
                    break;
                }
                model.trocarJogador(); // Troca para o próximo jogador
            }
        }
        reiniciarJogo(); // Pergunta se o usuário quer jogar novamente
    }

    // Método para escolher o jogador ('X' ou 'O')
    private char escolherJogador() {
        while (true) {
            String escolha = scanner.nextLine().toUpperCase(); // Lê a escolha do usuário e converte para maiúsculo
            if (escolha.equals("X") || escolha.equals("O")) {
                return escolha.charAt(0); // Retorna a escolha válida
            } else {
                view.exibirMensagem("Escolha inválida. Por favor, escolha 'X' ou 'O'."); // Mensagem de erro
            }
        }
    }

    // Método para obter a entrada do usuário para linha ou coluna
    private int obterEntrada(String prompt) {
        int valor;
        while (true) {
            try {
                view.exibirMensagem(prompt); // Exibe o prompt para o usuário
                valor = Integer.parseInt(scanner.nextLine()); // Lê a entrada do usuário e converte para inteiro
                if (valor >= 0 && valor <= 2) {
                    break; // Entrada válida
                } else {
                    view.exibirMensagem("Por favor, insira um valor entre 0 e 2."); // Mensagem de erro para entrada fora do intervalo
                }
            } catch (NumberFormatException e) {
                view.exibirMensagem("Entrada inválida. Por favor, insira um número."); // Mensagem de erro para entrada não numérica
            }
        }
        return valor; // Retorna a entrada válida
    }

    // Método para calcular o movimento do computador
    private int[] calcularMovimento() {
        char[][] tabuleiro = model.getTabuleiro();
        // Lógica básica da IA para encontrar um movimento válido

        // Verifica se o computador pode vencer em uma linha
        for (int i = 0; i < 3; i++) {
            int linhaProduto = tabuleiro[i][0] + tabuleiro[i][1] + tabuleiro[i][2];
            if (linhaProduto == 2 * model.getJogadorAtual()) {
                for (int j = 0; j < 3; j++) {
                    if (tabuleiro[i][j] == '-') {
                        return new int[]{i, j}; // Movimento para vencer
                    }
                }
            }
        }

        // Verifica se o computador pode vencer em uma coluna
        for (int j = 0; j < 3; j++) {
            int colunaProduto = tabuleiro[0][j] + tabuleiro[1][j] + tabuleiro[2][j];
            if (colunaProduto == 2 * model.getJogadorAtual()) {
                for (int i = 0; i < 3; i++) {
                    if (tabuleiro[i][j] == '-') {
                        return new int[]{i, j}; // Movimento para vencer
                    }
                }
            }
        }

        // Retorna o primeiro movimento válido encontrado
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '-') {
                    return new int[]{i, j}; // Movimento válido
                }
            }
        }

        return new int[]{-1, -1}; // Caso não encontre movimentos válidos
    }

    // Método para reiniciar o jogo
    private void reiniciarJogo() {
        view.exibirMensagem("Deseja jogar novamente? (S/N)"); // Pergunta se o usuário quer jogar novamente
        String resposta = scanner.nextLine().toUpperCase(); // Lê a resposta do usuário e converte para maiúsculo
        if (resposta.equals("S")) {
            model.reiniciarJogo(); // Reinicia o modelo do jogo
            iniciarJogo(); // Inicia um novo jogo
        } else {
            view.exibirMensagem("Obrigado por jogar!"); // Mensagem de despedida
        }
    }
}
