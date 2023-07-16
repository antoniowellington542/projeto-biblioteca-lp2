package Tela;

import BancoDeDados.BancoDeDados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaDeAtualizarQuantidadeDeItemNoEstoque extends JPanel {
    private final CardLayout cardLayout = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout();
    private final Dialogo dialogo = new Dialogo();
    private final JPanel panel = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel();

    public TelaDeAtualizarQuantidadeDeItemNoEstoque() {
        setLayout(new GridBagLayout());

        GridBagConstraints constantes = new GridBagConstraints();
        constantes.insets = new Insets(5, 5, 5, 5);

        JLabel labelDeIdDoItem = new JLabel("Id do Item");
        constantes.gridx = 0;
        constantes.gridy = 0;
        add(labelDeIdDoItem, constantes);

        JTextField campoDeIdDoItem = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeIdDoItem, constantes);

        JLabel labelDeQuantidade = new JLabel("Quantidade");
        constantes.gridx = 0;
        constantes.gridy = 1;
        add(labelDeQuantidade, constantes);

        JTextField campoDeQuantidade = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeQuantidade, constantes);

        JButton botaoDeAumentar = new JButton();
        botaoDeAumentar.setText("Aumentar");
        constantes.gridx = 0;
        constantes.gridy = 2;
        constantes.anchor = GridBagConstraints.CENTER;
        constantes.gridwidth = 2;
        botaoDeAumentar.addActionListener(action -> {
            this.aumentar(
                    campoDeIdDoItem.getText(),
                    Integer.parseInt(campoDeQuantidade.getText())
            );
        });
        add(botaoDeAumentar, constantes);

        JButton botaoDeDiminuir = new JButton();
        botaoDeDiminuir.setText("Diminuir");
        constantes.gridy = 3;
        botaoDeDiminuir.addActionListener(action -> {
            this.diminuir(
                    campoDeIdDoItem.getText(),
                    Integer.parseInt(campoDeQuantidade.getText())
            );
        });
        add(botaoDeDiminuir, constantes);

        JButton botaoDeVoltarTela = new JButton();
        botaoDeVoltarTela.setText("Diminuir");
        constantes.gridy = 3;
        botaoDeVoltarTela.addActionListener(this::voltarTela);
        add(botaoDeVoltarTela, constantes);
    }

    private void voltarTela(ActionEvent actionEvent) {
        cardLayout.show(panel, "estoque");
    }

    private void aumentar(String itemId, int quantidade) {
        try {
            BancoDeDados.MudarQuantidadeLivro(new Long(itemId), quantidade, true);
        } catch (Exception e) {
            dialogo.mostrarMensagemDeAlerta("Erro ao atualizar quantidade.");
            e.printStackTrace();
        }
    }

    private void diminuir(String itemId, int quantidade) {
        try {
            BancoDeDados.MudarQuantidadeLivro(new Long(itemId), quantidade, false);
        } catch (Exception e) {
            dialogo.mostrarMensagemDeAlerta("Erro ao atualizar quantidade.");
            e.printStackTrace();
        }
    }
}
