package Biblioteca;

import Emprestimo.Emprestimo;
import Contas.Usuario;
import Contas.Funcionario;
import Estoque.Estoque;
import Item.Item;

import java.util.ArrayList;

public class Biblioteca {
    public ArrayList<Usuario> usuarios = new ArrayList<>();
    public ArrayList<Funcionario> funcionarios = new ArrayList<>();
    public ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    public Estoque estoque;
    public static Biblioteca biblioteca = new Biblioteca();

    public Usuario buscaUsuario(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        System.out.println("Usuário não encontrado encontrado.");
        return null;
    }

    public Funcionario buscaFuncionario(String cpf) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                return funcionario;
            }
        }
        System.out.println("Usuário não encontrado encontrado.");
        return null;
    }

    public ArrayList<Emprestimo> getEmprestimos(String cpf) {
        ArrayList<Emprestimo> emprestimosUsuario = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCpf().equals(cpf)) {
                emprestimosUsuario.add(emprestimo);
            }
        }

        return emprestimosUsuario;
    }

    public ArrayList<Emprestimo> getEmprestimos(String cpf, String id) {
        ArrayList<Emprestimo> emprestimosUsuario = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {
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
        Usuario usuario = new Usuario(nome, senha, cpf, false);
        usuarios.add(usuario);
    }

    public void adicionarFuncionario(String nome, String senha, String cpf) {
        Funcionario funcionario = new Funcionario(nome, senha, cpf, true);
        funcionarios.add(funcionario);
    }

    public Usuario loginUsuario(String cpf, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public Funcionario loginFuncionario(String cpf, String senha) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf) && funcionario.getSenha().equals(senha)) {
                return funcionario;
            }
        }
        return null;
    }
}
