package Item;

import Biblioteca.Biblioteca;
import Estoque.Estoque;
import Utilidades.UtilitarioDeData;

public class Item {
    private String nome;
    private Long id;
    private int quantidade;

    public Item(String nome, int quantidade) {
        this.id = (long) (Biblioteca.biblioteca.estoque.items.size() + 1);
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
