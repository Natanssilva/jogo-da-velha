package jogo_da_velha;

public class Model {
    private char[][] tabuleiro; // Representa o tabuleiro do jogo, uma matriz 3x3 de caracteres
    private char jogadorAtual; // Representa o jogador atual, 'X' ou 'O'

    // Construtor da classe Model
    public Model() {
        tabuleiro = new char[3][3]; // Inicializa o tabuleiro com uma matriz 3x3
        jogadorAtual = 'X'; // O jogo começa com o jogador 'X'
        reiniciarJogo(); // Chama o método para reiniciar o jogo, preenchendo o tabuleiro
    }

    // Método para reiniciar o jogo, preenchendo o tabuleiro com '-'
    public void reiniciarJogo() {
        for (int i = 0; i < 3; i++) { // Percorre as linhas
            for (int j = 0; j < 3; j++) { // Percorre as colunas
                tabuleiro[i][j] = '-'; // Preenche cada posição do tabuleiro com '-'
            }
        }
    }

    // Método para fazer um movimento no tabuleiro
    public boolean fazerMovimento(int linha, int coluna) {
        // Verifica se a posição é válida e está vazia
        if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == '-') {
            tabuleiro[linha][coluna] = jogadorAtual; // Preenche a posição com o jogador atual
            return true; // Movimento foi bem-sucedido
        } else {
            return false; // Movimento inválido
        }
    }

    // Método para verificar se o jogador atual venceu o jogo
    public boolean verificarVitoria() {
        // Verifica linhas para uma vitória
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
                return true; // Vitória na linha i
            }
        }

        // Verifica colunas para uma vitória
        for (int j = 0; j < 3; j++) {
            if (tabuleiro[0][j] == jogadorAtual && tabuleiro[1][j] == jogadorAtual && tabuleiro[2][j] == jogadorAtual) {
                return true; // Vitória na coluna j
            }
        }

        // Verifica diagonais para uma vitória
        if ((tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) ||
                (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual)) {
            return true; // Vitória em uma das diagonais
        }
        return false; // Nenhuma vitória encontrada
    }

    // Método para verificar se o tabuleiro está cheio
    public boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) { // Percorre as linhas
            for (int j = 0; j < 3; j++) { // Percorre as colunas
                if (tabuleiro[i][j] == '-') {
                    return false; // Encontrou uma posição vazia, o tabuleiro não está cheio
                }
            }
        }
        return true; // Todas as posições estão preenchidas
    }

    // Método para obter o estado atual do tabuleiro
    public char[][] getTabuleiro() {
        return tabuleiro;
    }

    // Método para obter o jogador atual
    public char getJogadorAtual() {
        return jogadorAtual;
    }

    // Método para trocar o jogador atual
    public void trocarJogador() {
        // Alterna entre 'X' e 'O'
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
    }
}
