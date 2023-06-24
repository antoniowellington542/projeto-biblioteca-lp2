package Estoque;

import Item.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class Estoque {
    private static ArrayList<Item> items;

    public Item getItemPorId (String id) {
        Item itemEncontrado = null;

        for (Item item : Estoque.items) {
            if (item.getId().equals(id)) {
                itemEncontrado = item;
                break;
            }
        }

        return itemEncontrado;
    }

    public static ArrayList<Item> getItemPorNome(String nome) {
        //O nome pode ser só o começo
        ArrayList<Item> itensCorrespondentes = new ArrayList<>();

        for (Item item : items) {
            if (item.getNome().startsWith(nome)) {
                itensCorrespondentes.add(item);
            }
        }
        
        return itensCorrespondentes;
    }


    public void adicionarItems (ArrayList<Item> items) {
        Estoque.items.addAll(items);
    }

    public void excluirItemPorId (String id) {
        Item itemExiste = this.getItemPorId(id);

        if (itemExiste != null) {
            Estoque.items.remove(itemExiste);
            System.out.println("Item excluido com sucesso!");
        } else {
            System.out.println("Item nao encontrado no estoque");
        }
    }

    public void diminuirEstoque (String id, int quantidade) {
        boolean possoDiminuirEstoque = this.possoTirarDoEstoque(id, quantidade);

        if (possoDiminuirEstoque) {
            Item itemExiste = this.getItemPorId(id);

            if (itemExiste != null) {
                Iterator<Item> iterator = Estoque.items.iterator();
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

    public void adicionarEstoque (String id, int quantidade) {
        Item itemExiste = this.getItemPorId(id);

        if (itemExiste != null) {
            for (Item item : Estoque.items) {
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

    private boolean possoTirarDoEstoque (String id, int quantidade) {
        Item item = this.getItemPorId(id);

        return item.getQuantidade() >= quantidade;
    }
}
