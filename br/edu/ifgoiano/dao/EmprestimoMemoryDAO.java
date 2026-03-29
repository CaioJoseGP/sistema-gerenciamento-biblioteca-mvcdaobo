package br.edu.ifgoiano.dao;

import br.edu.ifgoiano.model.Emprestimo;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoMemoryDAO implements EmprestimoDAO {

    private static List<Emprestimo> dados = new ArrayList<>();
    private static int proximoId = 1;

    @Override
    public void salvar(Emprestimo emprestimo) {
        emprestimo.setId(proximoId++);
        dados.add(emprestimo);
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return dados;
    }

    @Override
    public Emprestimo buscarPorIdLivro(int idLivro) {
        for (Emprestimo e : dados) {
            if (e.getIdLivro() == idLivro) {
                return e;
            }
        }
        return null;
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
