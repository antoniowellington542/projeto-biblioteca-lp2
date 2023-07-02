package Item;

import java.util.Date;

public class Livro extends Item {
    private String autor;
    private Date dataPublicacao;

    public Livro(String nome, String autor, Date dataPublicacao, int quantidade) {
        super(nome, quantidade);
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }
}
