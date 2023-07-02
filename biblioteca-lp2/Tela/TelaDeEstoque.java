package Tela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaDeEstoque extends JPanel {
    private final CardLayout cardLayout = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout();
    private final Dialogo dialogo = new Dialogo();
    private final JPanel panel = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel();
    public TelaDeEstoque () {
        setLayout(new GridLayout(7, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton botaoDeEstoque = new JButton("Devolver item ao Estoque");
        botaoDeEstoque.addActionListener(this::diminuirEstoque);
        add(botaoDeEstoque);

        JButton botaoDeEmprestimo = new JButton("Aumentar Estoque de item");
        botaoDeEmprestimo.addActionListener(this::aumentarEstoque);
        add(botaoDeEmprestimo);

        JButton botaoDeDevolucao = new JButton("Criar item no estoque");
        botaoDeDevolucao.addActionListener(this::criarItemNoEstoque);
        add(botaoDeDevolucao);

        JButton botaoDeBuscarItem = new JButton("Excluir item no estoque");
        botaoDeBuscarItem.addActionListener(this::excluirItemNoEstoque);
        add(botaoDeBuscarItem);

        JButton botaoDeLogout = new JButton("Voltar a tela principal");
        botaoDeLogout.addActionListener(this::voltarTelaPrincipal);
        add(botaoDeLogout);
    }

    private void diminuirEstoque(ActionEvent actionEvent) {
    }

    private void aumentarEstoque(ActionEvent actionEvent) {
    }

    private void criarItemNoEstoque(ActionEvent actionEvent) {
    }

    private void excluirItemNoEstoque(ActionEvent actionEvent) {
    }

    private void voltarTelaPrincipal(ActionEvent actionEvent) {
        cardLayout.show(panel, "telaPrincipal");
    }
}
