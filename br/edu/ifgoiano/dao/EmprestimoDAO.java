package br.edu.ifgoiano.dao;

import br.edu.ifgoiano.model.Emprestimo;
import java.util.List;

public interface EmprestimoDAO {

    void salvar(Emprestimo emprestimo);
    List<Emprestimo> listarTodos();
    Emprestimo buscarPorIdLivro(int idLivro);
    void remover(int id);
}
