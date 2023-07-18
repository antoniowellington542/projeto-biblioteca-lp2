package Tela;

import BancoDeDados.BancoDeDados;
import Biblioteca.Biblioteca;
import Item.Item;
import Item.Livro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

public class TelaDeExcluirItemDoEstoque extends JPanel {
    private final CardLayout cardLayout = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout();
    private final Dialogo dialogo = new Dialogo();
    private final JPanel panel = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel();

    public TelaDeExcluirItemDoEstoque() {
        setLayout(new GridBagLayout());

        GridBagConstraints constantes = new GridBagConstraints();
        constantes.insets = new Insets(5, 5, 5, 5);

        JLabel labelDeNome = new JLabel("Nome");
        constantes.gridx = 0;
        constantes.gridy = 0;
        add(labelDeNome, constantes);

        JTextField campoDeNome = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeNome, constantes);

        JButton botaoDeExcluirItem = new JButton();
        botaoDeExcluirItem.setText("Excluir item");
        constantes.gridx = 0;
        constantes.gridy = 2;
        constantes.anchor = GridBagConstraints.CENTER;
        constantes.gridwidth = 2;
        botaoDeExcluirItem.addActionListener(action -> {
            this.excluirItem(campoDeNome.getText());
        });
        add(botaoDeExcluirItem, constantes);

        JButton botaoDeVoltarTela = new JButton("Voltar a tela anterior");
        botaoDeVoltarTela.addActionListener(this::voltarTela);
        constantes.gridy = 3;
        add(botaoDeVoltarTela, constantes);
    }

    private void voltarTela(ActionEvent actionEvent) {
        cardLayout.show(panel, "estoque");
    }
    private void excluirItem(String nome) {
        try {
            ArrayList<Item> livros = Biblioteca.biblioteca.estoque.getItemsPorNome(nome);
            if (livros.size() > 1) {
                dialogo.mostrarMensagemDeAlerta("Erro: Há mais de um livro para ser removido");
                System.out.println("Erro: Há mais de um livro para ser removido");
            } else if (livros.size() == 1) {
                BancoDeDados.removerLivro(nome);
                Biblioteca.biblioteca.estoque.excluirItemPorId(livros.get(0).getId());
                dialogo.mostrarMensagemDeAlerta("Livro excluido com sucesso");
            } else {
                dialogo.mostrarMensagemDeAlerta("Livro não encontrado no estoque");
                System.out.println("Livro não encontrado no estoque");
            }
        } catch (Exception e) {
            dialogo.mostrarMensagemDeAlerta("Erro não foi possível remover o livro do estoque");
            System.out.println("Erro não foi possível remover o livro do estoque");
        }
    }
}
