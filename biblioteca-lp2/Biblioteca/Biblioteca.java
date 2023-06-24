package Biblioteca;

import Emprestimo.Emprestimo;
import Contas.Usuario;
import Contas.Funcionario;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario buscaUsuario(String cpf) {
        for(Usuario usuario : usuarios) {
            if (usuario)
            {
                return usuario;
            }
        }
        return null;
    }

    public ArrayList<Emprestimo> getEmprestimos (String cpf) {
        ArrayList<Emprestimo> emprestimosUsuario = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCpf().equals(cpf)) {
                emprestimosUsuario.add(emprestimo);
            }
        }

        return emprestimosUsuario;
    }

    public ArrayList<Emprestimo> getEmprestimos (String cpf, String id) {
        ArrayList<Emprestimo> emprestimosUsuario = new ArrayList<>();

        for(Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCpf().equals(cpf) && emprestimo.getId().equals(id)) {
                emprestimosUsuario.add(emprestimo);
            }
        }

        return emprestimosUsuario;
    }

    public void adicionarEmprestimo(String cpf, String itemId) {
        Emprestimo emprestimo = new Emprestimo(cpf, itemId);
        emprestimos.add(emprestimo);
    }

    public void excluirEmprestimo(String cpf, String itemId) {
        emprestimos.removeIf(emprestimo -> emprestimo.getCpf().equals(cpf) && emprestimo.getItemId().equals(itemId));
    }

    public void adicionarUsuario(String nome, String senha, String cpf) {
        Usuario usuario = new Usuario(nome, senha, cpf);
        usuarios.add(usuario);
    }

    public void adicionarFuncionario(String nome, String senha, String cpf) {
        Funcionario funcionario = new Funcionario(nome, senha, cpf);
        funcionarios.add(funcionario);
    }
}
