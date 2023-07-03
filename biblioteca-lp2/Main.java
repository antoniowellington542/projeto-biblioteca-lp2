import BancoDeDados.BancoDeDados;
import Tela.GerenciadorDeTelas;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        BancoDeDados.carregarUsuarios();
        BancoDeDados.carregarLivros();
        BancoDeDados.carregarEmprestimos();
        GerenciadorDeTelas.getGerenciadorDeTelas().tela();
    }
}
