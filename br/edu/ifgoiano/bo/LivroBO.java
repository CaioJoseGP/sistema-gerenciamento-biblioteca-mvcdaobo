package br.edu.ifgoiano.bo;

import br.edu.ifgoiano.dao.LivroDAO;
import br.edu.ifgoiano.model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroBO {

    private LivroDAO livroDAO;

    public LivroBO(LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

    public void cadastrar(String titulo, String autor) throws Exception {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("O titulo do livro nao pode ser vazio!");
        }
        if (autor == null || autor.trim().isEmpty()) {
            throw new Exception("O autor do livro nao pode ser vazio!");
        }

        Livro livro = new Livro(titulo.trim(), autor.trim());
        livroDAO.salvar(livro);
    }

    public List<Livro> listarDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        for (Livro livro : livroDAO.listarTodos()) {
            if (livro.isDisponivel()) {
                disponiveis.add(livro);
            }
        }
        return disponiveis;
    }

    public List<Livro> listarTodos() {
        return livroDAO.listarTodos();
    }

    public void excluir(int id) throws Exception {
        Livro livro = livroDAO.buscarPorId(id);

        if (livro == null) {
            throw new Exception("Livro nao encontrado!");
        }

        // REGRA DO DESAFIO: nao pode excluir livro emprestado
        if (!livro.isDisponivel()) {
            throw new Exception("Nao e possivel excluir um livro que esta emprestado!");
        }

        livroDAO.remover(id);
    }
}
