package Tela;

import BancoDeDados.BancoDeDados;
import Biblioteca.Biblioteca;
import Contas.Funcionario;
import Contas.Usuario;
import Estoque.Estoque;
import Item.Item;
import Item.Livro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TelaDeCriarItemNoEstoque extends JPanel {
    private final CardLayout cardLayout = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout();
    private final Dialogo dialogo = new Dialogo();
    private final JPanel panel = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel();

    public TelaDeCriarItemNoEstoque() {
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

        JLabel labelDeAutor = new JLabel("Autor");
        constantes.gridx = 0;
        constantes.gridy = 1;
        add(labelDeAutor, constantes);

        JTextField campoDeAutor = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeAutor, constantes);

        JLabel labelDeDataPublicacao = new JLabel("Data de Publicaçao");
        constantes.gridx = 0;
        constantes.gridy = 2;
        add(labelDeDataPublicacao, constantes);

        JTextField campoDeDataPublicacao = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeDataPublicacao, constantes);

        JLabel labelDeQuantidade = new JLabel("Quantidade");
        constantes.gridx = 0;
        constantes.gridy = 3;
        add(labelDeQuantidade, constantes);

        JTextField campoDeQuantidade = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeQuantidade, constantes);

        JButton botaoDeCriarItem = new JButton();
        botaoDeCriarItem.setText("Criar item");
        constantes.gridx = 0;
        constantes.gridy = 4;
        constantes.anchor = GridBagConstraints.CENTER;
        constantes.gridwidth = 2;
        botaoDeCriarItem.addActionListener(action -> {
            this.criarItem(
                    campoDeNome.getText(),
                    campoDeAutor.getText(),
                    new Date(campoDeDataPublicacao.getText()),
                    Integer.parseInt(campoDeQuantidade.getText())
            );
        });
        add(botaoDeCriarItem, constantes);

        JButton botaoDeLogout = new JButton("Voltar a tela principal");
        botaoDeLogout.addActionListener(this::voltarTelaPrincipal);
        constantes.gridy = 5;
        add(botaoDeLogout, constantes);
    }

    private void voltarTelaPrincipal(ActionEvent actionEvent) {
        cardLayout.show(panel, "telaPrincipal");
    }

    private void criarItem(String nome, String autor, Date dataPublicacao, int quantidade) {
        if (!nome.isEmpty() && !autor.isEmpty() && dataPublicacao != null && quantidade > 0) {
            ArrayList<Item> itemsCorrespondentes = Biblioteca.biblioteca.estoque.getItemsPorNome(nome);
            if (itemsCorrespondentes.size() > 0) {
                dialogo.mostrarMensagemDeAlerta("Erro: Um livro com esse nome já existe no estoque");
                System.out.println("Erro: Um livro com esse nome já existe no estoque");
            } else {
                BancoDeDados.cadastrarLivro(nome, autor, dataPublicacao, quantidade);
                Biblioteca.biblioteca.estoque.adicionarNovoLivro(new Livro(nome, autor, dataPublicacao, quantidade));
                dialogo.mostrarMensagemDeInformacao("Livro cadastrado com sucesso!");
            }
        } else {
            dialogo.mostrarMensagemDeAlerta("Erro ao cadastrar Livro, dados incompletos");
            System.out.println("Erro ao cadastrar Livro, dados incompletos");
        }

    }
}
