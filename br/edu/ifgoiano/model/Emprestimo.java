package br.edu.ifgoiano.model;

import java.time.LocalDate;

public class Emprestimo {

    private int id;
    private int idLivro;
    private String nomeLeitor;
    private LocalDate dataEmprestimo;

    public Emprestimo(int idLivro, String nomeLeitor) {
        this.idLivro = idLivro;
        this.nomeLeitor = nomeLeitor;
        this.dataEmprestimo = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getNomeLeitor() {
        return nomeLeitor;
    }

    public void setNomeLeitor(String nomeLeitor) {
        this.nomeLeitor = nomeLeitor;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
}
