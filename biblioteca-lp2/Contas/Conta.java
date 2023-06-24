package Contas;

import java.util.ArrayList;

import Item.Item;

interface Conta {
    String getNome();
    String getCpf();
    ArrayList<Item> pesquisaLivro(String nomeLivro);
}