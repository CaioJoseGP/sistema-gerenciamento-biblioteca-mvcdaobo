package br.edu.ifgoiano.dao;

import br.edu.ifgoiano.model.Livro;
import java.util.List;

public interface LivroDAO {

    void salvar(Livro livro);
    Livro buscarPorId(int id);
    List<Livro> listarTodos();
    void atualizar(Livro livro);
    void remover(int id);
}
