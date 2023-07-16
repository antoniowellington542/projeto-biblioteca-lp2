package Tela;

import BancoDeDados.BancoDeDados;
import Biblioteca.Biblioteca;
import Emprestimo.Emprestimo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;

public class TelaDeDevolucao extends JPanel {
    private final CardLayout cardLayout = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout();
    private final Dialogo dialogo = new Dialogo();
    private final JPanel panel = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel();

    public TelaDeDevolucao() {
        setLayout(new GridBagLayout());

        GridBagConstraints constantes = new GridBagConstraints();
        constantes.insets = new Insets(5, 5, 5, 5);

        JLabel labelDeCpf = new JLabel("CPF");
        constantes.gridx = 0;
        constantes.gridy = 0;
        add(labelDeCpf, constantes);

        JTextField campoDeCpf = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeCpf, constantes);

        JLabel labelDeEmprestiId = new JLabel("ID Livro");
        constantes.gridx = 0;
        constantes.gridy = 1;
        add(labelDeEmprestiId, constantes);

        JTextField campoDeEmprestimoId = new JTextField(20);
        constantes.gridx = 1;
        add(campoDeEmprestimoId, constantes);

        JButton botaoDeCriarItem = new JButton();
        botaoDeCriarItem.setText("Devolver");
        constantes.gridx = 0;
        constantes.gridy = 2;
        constantes.anchor = GridBagConstraints.CENTER;
        constantes.gridwidth = 2;
        botaoDeCriarItem.addActionListener(action -> {
            this.devolver(
                    campoDeCpf.getText(),
                    Long.parseLong(campoDeEmprestimoId.getText())
            );
        });
        add(botaoDeCriarItem, constantes);

        JButton botaoDeLogout = new JButton("Voltar a tela principal");
        botaoDeLogout.addActionListener(this::voltarTelaPrincipal);
        constantes.gridy = 3;
        add(botaoDeLogout, constantes);
    }

    private void devolver(String cpf, Long livroId) {
        try {
            Biblioteca lib = Biblioteca.getBiblioteca();

            for (Emprestimo emprestimo : lib.getEmprestimos(cpf)) {
                if (emprestimo.getId().equals(livroId)) {
                    lib.excluirEmprestimo(cpf, emprestimo.getItemId());
                    BancoDeDados.removerEmprestimo(cpf, livroId);
                    BancoDeDados.MudarQuantidadeLivro(livroId, 1, true);
                    dialogo.mostrarMensagemDeInformacao("Devolução realizada com sucesso!");
                    return;
                }
            }
            dialogo.mostrarMensagemDeAlerta("Não foi possível encontrar a devolução");
            System.out.println("Erro: esse item já foi devolvido");
        } catch (Exception e) {
            dialogo.mostrarMensagemDeAlerta("Erro ao realizar devolução");
            e.printStackTrace();
        }
    }

    private void voltarTelaPrincipal(ActionEvent actionEvent) {
        cardLayout.show(panel, "telaPrincipal");
    }
}
