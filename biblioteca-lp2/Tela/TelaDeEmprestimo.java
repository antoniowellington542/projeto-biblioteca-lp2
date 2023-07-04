package Tela;

import BancoDeDados.BancoDeDados;
import Biblioteca.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaDeEmprestimo extends JPanel {
    private final CardLayout cardLayout = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout();
    private final Dialogo dialogo = new Dialogo();
    private final JPanel panel = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel();
    private final Biblioteca biblioteca = Biblioteca.getBiblioteca();

    public TelaDeEmprestimo() {
        setLayout(new GridBagLayout());

        GridBagConstraints constantes = new GridBagConstraints();
        constantes.insets = new Insets(5, 5, 5, 5);

        JLabel labelDeCpf = new JLabel("Cpf");
        constantes.gridx = 0;
        constantes.gridy = 0;
        add(labelDeCpf, constantes);

        JTextField campoDeCpf = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeCpf, constantes);

        JLabel labelDeItemId = new JLabel("ItemId");
        constantes.gridx = 0;
        constantes.gridy = 1;
        add(labelDeItemId, constantes);

        JTextField campoDeItemId = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeItemId, constantes);

        JButton botaoDeEmprestar = new JButton();
        botaoDeEmprestar.setText("Emprestar");
        constantes.gridx = 0;
        constantes.gridy = 2;
        constantes.anchor = GridBagConstraints.CENTER;
        constantes.gridwidth = 2;
        botaoDeEmprestar.addActionListener(action -> {
            this.emprestar(
                    campoDeCpf.getText(),
                    campoDeItemId.getText()
            );
        });
        add(botaoDeEmprestar, constantes);

        JButton botaoDeLogout = new JButton("Voltar a tela principal");
        botaoDeLogout.addActionListener(this::voltarTelaPrincipal);
        constantes.gridy = 3;
        add(botaoDeLogout, constantes);
    }

    private void voltarTelaPrincipal(ActionEvent actionEvent) {
        cardLayout.show(panel, "telaPrincipal");
    }

    private void emprestar(String cpf, String itemId) {
        try {
            biblioteca.adicionarEmprestimo(cpf, itemId);
            Date dataAtual = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String dataFormatada = formato.format(dataAtual);
            BancoDeDados.cadastrarEmprestimo(cpf, Long.parseLong(itemId), dataFormatada);

            dialogo.mostrarMensagemDeInformacao("Emprestimo feito com sucesso");
        } catch (Exception e) {
            dialogo.mostrarMensagemDeAlerta("Erro ao cadastrar Empréstimo");
            e.printStackTrace();
        }
    }
}
