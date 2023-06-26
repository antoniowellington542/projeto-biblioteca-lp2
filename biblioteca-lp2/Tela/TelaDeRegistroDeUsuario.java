package Tela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaDeRegistroDeUsuario extends JPanel {
    public TelaDeRegistroDeUsuario () {
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

        JLabel labelDeCpf = new JLabel("Cpf");
        constantes.gridx = 0;
        constantes.gridy = 1;
        add(labelDeCpf, constantes);

        JTextField campoDeCpf = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeCpf, constantes);

        JLabel labelDeSenha = new JLabel("Senha");
        constantes.gridx = 0;
        constantes.gridy = 2;
        add(labelDeSenha, constantes);

        JTextField campoDeSenha = new JPasswordField(20);
        constantes.gridx = 1;
        add(campoDeSenha, constantes);

        JButton botaoDeRegistro = new JButton();
        botaoDeRegistro.setText("Registrar");
        constantes.gridx = 0;
        constantes.gridy = 3;
        constantes.anchor = GridBagConstraints.CENTER;
        constantes.gridwidth = 2;
        botaoDeRegistro.addActionListener(action -> {
            this.registrar(campoDeNome.getText(), campoDeCpf.getText(), campoDeSenha.getText());
        });
        add(botaoDeRegistro, constantes);

        JButton botaoDeVoltar = new JButton();
        botaoDeVoltar.setText("Voltar");
        constantes.gridy = 4;
        botaoDeVoltar.addActionListener(this::voltarTela);
        add(botaoDeVoltar, constantes);
    }

    private void voltarTela(ActionEvent actionEvent) {
        GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout().show(GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel(), "login");
    }

    private void registrar (String nome, String cpf, String senha) {
        // Chamar metodo para registrar usuario
    }
}
