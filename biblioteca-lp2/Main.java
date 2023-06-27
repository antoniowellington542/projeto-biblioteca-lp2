import BancoDeDados.BancoDeDados;
import Tela.GerenciadorDeTelas;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        BancoDeDados.carregarUsuarios();

        GerenciadorDeTelas.getGerenciadorDeTelas().tela();
    }
}
