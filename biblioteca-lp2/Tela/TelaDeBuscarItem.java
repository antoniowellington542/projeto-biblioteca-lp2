package Tela;

import javax.swing.*;

public class TelaDeBuscarItem extends JPanel {
  
  private Item buscarPorId(String id)
  {    
     item = Biblioteca.biblioteca.getItemPorId(id);
     if(item != null)
     {
       JOptionPane.showMessageDialog(null, "Item encontrado", "Sucesso", JOptionPane.WARNING_MESSAGE);
       return item;
     }
       
    else
    {
       JOptionPane.showMessageDialog(null, "O item buscado não encontra-se no estoque", "Alerta", JOptionPane.WARNING_MESSAGE);     
    }
  }

  private Item buscarPorNome(String nome)
  {    
     item = Biblioteca.biblioteca.getItemPorNome(nome);
     if(item != null)
     {
       JOptionPane.showMessageDialog(null, "Item encontrado", "Sucesso", JOptionPane.WARNING_MESSAGE);
       return item;
     }
       
    else
    {
       JOptionPane.showMessageDialog(null, "O item buscado não encontra-se no estoque", "Alerta", JOptionPane.WARNING_MESSAGE);     
    }
  }  
}
