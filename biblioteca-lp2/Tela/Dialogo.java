package Tela;

import javax.swing.*;

public class Dialogo extends JOptionPane {
    public void mostrarMensagemDeInformacao (String texto) {
        showMessageDialog(
                null,
                texto,
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void mostrarMensagemDeAlerta (String texto) {
        showMessageDialog(
                null,
                texto,
                "Alerta",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
