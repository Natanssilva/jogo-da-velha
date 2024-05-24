package jogo_da_velha;

public class View {
    public void mostrarTabuleiro(char[][] tabuleiro) {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                System.out.print(" " + tabuleiro[linha][coluna] + " ");
                if (coluna < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (linha < 2) {
                System.out.println("-----------");
            }
        }
        System.out.println("\n");
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
