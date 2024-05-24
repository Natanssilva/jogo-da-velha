package jogo_da_velha;

public class View {
    // Método para exibir o tabuleiro do jogo na tela
    public void mostrarTabuleiro(char[][] tabuleiro) {
        // Percorre cada linha do tabuleiro
        for (int linha = 0; linha < 3; linha++) {
            // Percorre cada coluna da linha atual
            for (int coluna = 0; coluna < 3; coluna++) {
                // Imprime o conteúdo da posição atual do tabuleiro com espaço ao redor
                System.out.print(" " + tabuleiro[linha][coluna] + " ");
                // Imprime um separador vertical entre as colunas, exceto após a última coluna
                if (coluna < 2) {
                    System.out.print("|");
                }
            }
            System.out.println(); // Nova linha após cada linha do tabuleiro
            // Imprime um separador horizontal entre as linhas, exceto após a última linha
            if (linha < 2) {
                System.out.println("-----------");
            }
        }
        System.out.println("\n"); // Nova linha para separar o tabuleiro de futuras saídas
    }

    // Método para exibir uma mensagem na tela
    public void exibirMensagem(String mensagem) {
        // Imprime a mensagem recebida como parâmetro
        System.out.println(mensagem);
    }
}
