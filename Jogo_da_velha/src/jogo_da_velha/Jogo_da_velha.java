package jogo_da_velha;

public class Jogo_da_velha {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.iniciarJogo();
    }
}
