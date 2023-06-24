package Item;

import Utilidades.UtilitarioDeData;

public class Item {
    private String nome;
    private String id;
    private int quantidade;

    public Item (String nome, int quantidade) {
        this.id = String.valueOf(new UtilitarioDeData().getDataAtualEmMS());
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getId() {
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
