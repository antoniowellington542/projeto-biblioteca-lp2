package Contas;

import java.util.ArrayList;

import Item.Item;

interface Conta {
    String getNome();
    String getCpf();
    String getSenha();
    ArrayList<Item> pesquisaLivro(String nomeLivro);
}