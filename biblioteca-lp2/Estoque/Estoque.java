package Estoque;

import Item.Item;
import Item.Livro;

import java.util.ArrayList;
import java.util.Iterator;

public class Estoque {
    public ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Livro> livros = new ArrayList<Livro>();

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros.addAll(livros);
    }

    ;

    public Item getItemPorId(Long id) {
        Item itemEncontrado = null;

        for (Item item : items) {
            if (item.getId().equals(id)) {
                itemEncontrado = item;
                break;
            }
        }

        return itemEncontrado;
    }

    public ArrayList<Item> getItemsPorNome(String nome) {
        //O nome pode ser só o começo
        ArrayList<Item> itensCorrespondentes = new ArrayList<>();

        for (Item item : items) {
            if (item.getNome().startsWith(nome)) {
                itensCorrespondentes.add(item);
            }
        }

        return itensCorrespondentes;
    }

    public void adicionarItems(ArrayList<Item> items) {
        this.items.addAll(items);
    }

    public void excluirItemPorId(Long id) {
        Item itemExiste = this.getItemPorId(id);

        if (itemExiste != null) {
            this.items.remove(itemExiste);
            System.out.println("Item excluido com sucesso!");
        } else {
            System.out.println("Item nao encontrado no estoque");
        }
    }

    public void diminuirEstoque(Long id, int quantidade) {
        boolean possoDiminuirEstoque = this.possoTirarDoEstoque(id, quantidade);

        if (possoDiminuirEstoque) {
            Item itemExiste = this.getItemPorId(id);

            if (itemExiste != null) {
                Iterator<Item> iterator = this.items.iterator();
                while (iterator.hasNext()) {
                    Item item = iterator.next();
                    if (item.getId().equals(id)) {
                        iterator.remove();
                        break;
                    }
                }
            }

            System.out.println("Item retirado com sucesso!");
        } else {
            System.out.println("Quantidade maior do que tem no estoque!");
        }
    }

    public void adicionarEstoque(Long id, int quantidade) {
        Item itemExiste = this.getItemPorId(id);

        if (itemExiste != null) {
            for (Item item : this.items) {
                if (item.getId().equals(id)) {
                    int novaQuantidade = item.getQuantidade() + quantidade;
                    item.setQuantidade(novaQuantidade);
                    break;
                }
            }

            System.out.println("Item adicionado ao estoque");
        } else {
            System.out.println("Item nao encontrado");
        }
    }

    private boolean possoTirarDoEstoque(Long id, int quantidade) {
        Item item = this.getItemPorId(id);

        return item.getQuantidade() >= quantidade;
    }

    public void adicionarNovoLivro(Livro livro) {
        items.add(livro);
    }
}
