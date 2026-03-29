package br.edu.ifgoiano.bo;

import br.edu.ifgoiano.dao.EmprestimoDAO;
import br.edu.ifgoiano.dao.LivroDAO;
import br.edu.ifgoiano.model.Emprestimo;
import br.edu.ifgoiano.model.Livro;

public class EmprestimoBO {

    private LivroDAO livroDAO;
    private EmprestimoDAO emprestimoDAO;

    public EmprestimoBO(LivroDAO livroDAO, EmprestimoDAO emprestimoDAO) {
        this.livroDAO = livroDAO;
        this.emprestimoDAO = emprestimoDAO;
    }

    public void realizarEmprestimo(int idLivro, String leitor) throws Exception {
        if (leitor == null || leitor.trim().isEmpty()) {
            throw new Exception("O nome do leitor nao pode ser vazio!");
        }

        Livro livro = livroDAO.buscarPorId(idLivro);

        // REGRA 1: O livro existe?
        if (livro == null) {
            throw new Exception("Livro nao encontrado!");
        }

        // REGRA 2: O livro esta disponivel?
        if (!livro.isDisponivel()) {
            throw new Exception("Este livro ja esta emprestado!");
        }

        // Se passou pelas regras, prossegue:
        livro.setDisponivel(false);
        livroDAO.atualizar(livro);

        Emprestimo e = new Emprestimo(idLivro, leitor.trim());
        emprestimoDAO.salvar(e);
    }

    public void devolverLivro(int idLivro) throws Exception {
        Livro livro = livroDAO.buscarPorId(idLivro);

        if (livro == null) {
            throw new Exception("Livro nao encontrado!");
        }

        if (livro.isDisponivel()) {
            throw new Exception("Este livro nao esta emprestado!");
        }

        // Busca o emprestimo associado ao livro
        Emprestimo emprestimo = emprestimoDAO.buscarPorIdLivro(idLivro);

        if (emprestimo != null) {
            emprestimoDAO.remover(emprestimo.getId());
        }

        livro.setDisponivel(true);
        livroDAO.atualizar(livro);
    }
}
