package br.edu.ifgoiano.dao;

import br.edu.ifgoiano.model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroMemoryDAO implements LivroDAO {

    private static List<Livro> dados = new ArrayList<>();
    private static int proximoId = 1;

    @Override
    public void salvar(Livro livro) {
        livro.setId(proximoId++);
        dados.add(livro);
    }

    @Override
    public Livro buscarPorId(int id) {
        for (Livro livro : dados) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }

    @Override
    public List<Livro> listarTodos() {
        return dados;
    }

    @Override
    public void atualizar(Livro livro) {
        for (int i = 0; i < dados.size(); i++) {
            if (dados.get(i).getId() == livro.getId()) {
                dados.set(i, livro);
                return;
            }
        }
    }

    @Override
    public void remover(int id) {
        for (int i = 0; i < dados.size(); i++) {
            if (dados.get(i).getId() == id) {
                dados.remove(i);
                return;
            }
        }
    }
}
