package br.edu.ifgoiano.controller;

import br.edu.ifgoiano.bo.EmprestimoBO;
import br.edu.ifgoiano.bo.LivroBO;
import br.edu.ifgoiano.model.Livro;
import java.util.List;

public class BibliotecaController {

    private LivroBO livroBO;
    private EmprestimoBO emprestimoBO;

    public BibliotecaController(LivroBO livroBO, EmprestimoBO emprestimoBO) {
        this.livroBO = livroBO;
        this.emprestimoBO = emprestimoBO;
    }

    public String cadastrarLivro(String titulo, String autor) {
        try {
            livroBO.cadastrar(titulo, autor);
            return "Livro cadastrado com sucesso!";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    public List<Livro> listarDisponiveis() {
        return livroBO.listarDisponiveis();
    }

    public List<Livro> listarTodos() {
        return livroBO.listarTodos();
    }

    public String realizarEmprestimo(int idLivro, String nomeLeitor) {
        try {
            emprestimoBO.realizarEmprestimo(idLivro, nomeLeitor);
            return "Emprestimo realizado com sucesso!";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    public String devolverLivro(int idLivro) {
        try {
            emprestimoBO.devolverLivro(idLivro);
            return "Livro devolvido com sucesso!";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    public String excluirLivro(int id) {
        try {
            livroBO.excluir(id);
            return "Livro excluido com sucesso!";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }
}
