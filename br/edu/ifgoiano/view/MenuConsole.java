package br.edu.ifgoiano.view;

import br.edu.ifgoiano.bo.EmprestimoBO;
import br.edu.ifgoiano.bo.LivroBO;
import br.edu.ifgoiano.controller.BibliotecaController;
import br.edu.ifgoiano.dao.EmprestimoDAO;
import br.edu.ifgoiano.dao.EmprestimoMemoryDAO;
import br.edu.ifgoiano.dao.LivroDAO;
import br.edu.ifgoiano.dao.LivroMemoryDAO;
import br.edu.ifgoiano.model.Livro;

import java.util.List;
import java.util.Scanner;

public class MenuConsole {

    public static void main(String[] args) {
        // Monta as dependencias
        LivroDAO livroDAO = new LivroMemoryDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoMemoryDAO();

        LivroBO livroBO = new LivroBO(livroDAO);
        EmprestimoBO emprestimoBO = new EmprestimoBO(livroDAO, emprestimoDAO);

        BibliotecaController controller = new BibliotecaController(livroBO, emprestimoBO);

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("========================================");
        System.out.println("   SISTEMA DE GERENCIAMENTO DE BIBLIOTECA");
        System.out.println("========================================");

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros Disponiveis");
            System.out.println("3. Realizar Emprestimo");
            System.out.println("4. Devolver Livro");
            System.out.println("5. Excluir Livro");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    System.out.println("\n--- CADASTRAR LIVRO ---");
                    System.out.print("Titulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.println(controller.cadastrarLivro(titulo, autor));
                    break;

                case 2:
                    System.out.println("\n--- LIVROS DISPONIVEIS ---");
                    List<Livro> disponiveis = controller.listarDisponiveis();
                    if (disponiveis.isEmpty()) {
                        System.out.println("Nenhum livro disponivel.");
                    } else {
                        System.out.printf("%-5s %-30s %-20s%n", "ID", "TITULO", "AUTOR");
                        System.out.println("------------------------------------------------------");
                        for (Livro l : disponiveis) {
                            System.out.printf("%-5d %-30s %-20s%n", l.getId(), l.getTitulo(), l.getAutor());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- REALIZAR EMPRESTIMO ---");
                    // Mostra livros disponiveis para facilitar
                    List<Livro> livrosDisp = controller.listarDisponiveis();
                    if (livrosDisp.isEmpty()) {
                        System.out.println("Nenhum livro disponivel para emprestimo.");
                        break;
                    }
                    System.out.printf("%-5s %-30s%n", "ID", "TITULO");
                    for (Livro l : livrosDisp) {
                        System.out.printf("%-5d %-30s%n", l.getId(), l.getTitulo());
                    }
                    System.out.print("ID do Livro: ");
                    int idEmprestimo = lerInteiro(scanner);
                    System.out.print("Nome do Leitor: ");
                    String leitor = scanner.nextLine();
                    System.out.println(controller.realizarEmprestimo(idEmprestimo, leitor));
                    break;

                case 4:
                    System.out.println("\n--- DEVOLVER LIVRO ---");
                    // Mostra todos os livros para o usuario identificar o emprestado
                    List<Livro> todos = controller.listarTodos();
                    if (todos.isEmpty()) {
                        System.out.println("Nenhum livro cadastrado.");
                        break;
                    }
                    System.out.printf("%-5s %-30s %-12s%n", "ID", "TITULO", "STATUS");
                    for (Livro l : todos) {
                        String status = l.isDisponivel() ? "Disponivel" : "Emprestado";
                        System.out.printf("%-5d %-30s %-12s%n", l.getId(), l.getTitulo(), status);
                    }
                    System.out.print("ID do Livro a devolver: ");
                    int idDevolucao = lerInteiro(scanner);
                    System.out.println(controller.devolverLivro(idDevolucao));
                    break;

                case 5:
                    System.out.println("\n--- EXCLUIR LIVRO ---");
                    List<Livro> todosExc = controller.listarTodos();
                    if (todosExc.isEmpty()) {
                        System.out.println("Nenhum livro cadastrado.");
                        break;
                    }
                    System.out.printf("%-5s %-30s %-12s%n", "ID", "TITULO", "STATUS");
                    for (Livro l : todosExc) {
                        String status = l.isDisponivel() ? "Disponivel" : "Emprestado";
                        System.out.printf("%-5d %-30s %-12s%n", l.getId(), l.getTitulo(), status);
                    }
                    System.out.print("ID do Livro a excluir: ");
                    int idExclusao = lerInteiro(scanner);
                    System.out.println(controller.excluirLivro(idExclusao));
                    break;

                case 6:
                    System.out.println("\nSistema encerrado. Ate logo!");
                    break;

                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }

        } while (opcao != 6);

        scanner.close();
    }

    /**
     * Le um numero inteiro do scanner, tratando entrada invalida.
     */
    private static int lerInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada invalida. Digite um numero: ");
            scanner.nextLine();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpa o buffer
        return valor;
    }
}
