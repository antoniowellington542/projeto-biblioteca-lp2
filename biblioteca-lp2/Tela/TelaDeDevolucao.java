package Tela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaDeDevolucao extends JPanel {
    private final CardLayout cardLayout = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDelayout();
    private final Dialogo dialogo = new Dialogo();
    private final JPanel panel = GerenciadorDeTelas.getGerenciadorDeTelas().getGerenciadorDePainel();

    public TelaDeDevolucao () {
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

        JLabel labelDeEmprestiId = new JLabel("EmprestimoId");
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
                    campoDeEmprestimoId.getText()
            );
        });
        add(botaoDeCriarItem, constantes);

        JButton botaoDeLogout = new JButton("Voltar a tela principal");
        botaoDeLogout.addActionListener(this::voltarTelaPrincipal);
        constantes.gridy = 3;
        add(botaoDeLogout, constantes);
    }

    private void devolver(String cpf, String emprestimoId) {
        
        Biblioteca lib = Biblioteca.getBiblioteca();
        
        for(Emprestimo emprestimo : lib.emprestimos.getEmprestimos(cpf))
        {
            if(emprestimo.id == emprestimoId)
            {
                lib.excluirEmprestimo(cpf,emprestimo.itemId);
                dialogo.mostrarMensagemDeInformacao("Devolução realizada com sucesso!");
                return;
            }          
        } 
        dialogo.mostrarMensagemDeAlerta("Erro: esse item já foi devolvido");
        System.out.println("Erro: esse item já foi devolvido");
        
    }

    private void voltarTelaPrincipal(ActionEvent actionEvent) {
        cardLayout.show(panel, "telaPrincipal");
    }
}
