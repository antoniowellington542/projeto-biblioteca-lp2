package Contas;

import java.util.ArrayList;

import Biblioteca.Biblioteca;
import Item.Item;

public class Funcionario implements Conta {
    private String login;
    private String senha;
    private String nome;
    private String cpf;
    private boolean admin;
    public Biblioteca biblioteca;

    public Funcionario(String cpf, String senha, String nome, boolean admin) {
        this.login = cpf;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.admin = admin;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public String getSenha() {
        return senha;
    }
}