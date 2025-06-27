package br.edu.idp.cc.poo.dvrental;

import br.edu.idp.cc.poo.dvrental.dao.*;
import br.edu.idp.cc.poo.dvrental.model.*;

import java.sql.SQLException;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Inserir novo filme");
                System.out.println("2. Buscar filme por ID");
                System.out.println("3. Buscar filme por nome");
                System.out.println("4. Listar todos os atores");
                System.out.println("5. Atualizar ator");
                System.out.println("6. Remover ator por ID");
                System.out.println("0. Sair");
                System.out.print("Escolha: ");
                int opcao = sc.nextInt();
                sc.nextLine(); 

                try {
                    switch (opcao) {
                        case 1 -> {
                            System.out.print("Título: ");
                            String title = sc.nextLine();
                            System.out.print("Descrição: ");
                            String desc = sc.nextLine();
                            FilmDAO.insert(new Film(title, desc));
                        }
                        case 2 -> {
                            System.out.print("ID do filme: ");
                            int id = sc.nextInt();
                            Film film = FilmDAO.buscaID(id);
                            System.out.println(film != null ? film : "Filme não encontrado.");
                        }
                        case 3 -> {
                            System.out.print("Nome do filme: ");
                            String name = sc.nextLine();
                            Film film = FilmDAO.buscaTitle(name);
                            System.out.println(film != null ? film : "Filme não encontrado.");
                        }
                        case 4 -> {
                            ActorDAO.listAll();
                            System.out.println("\nPressione <ENTER> para voltar.");
                            sc.nextLine();
                        }
                        case 5 -> {
                            System.out.print("ID do ator: ");
                            int actorId = sc.nextInt(); sc.nextLine();
                            System.out.print("Novo primeiro nome: ");
                            String fname = sc.nextLine();
                            System.out.print("Novo sobrenome: ");
                            String lname = sc.nextLine();
                            ActorDAO.updateId(actorId, fname, lname);
                        }
                        case 6 -> {
                            System.out.print("ID do ator: ");
                            int actorId = sc.nextInt();
                            ActorDAO.deleteId(actorId);
                        }
                        case 0 -> {
                            System.out.println("Encerrando...");
                            return;
                        }
                        default -> System.out.println("Opção inválida.");
                    }
                } catch(IOException ioex) {
                    System.err.println("Erro ao ler arquivo de configuração - " + ioex.getMessage());
                    return;
                } catch(SQLException sqlex) {
                    System.err.println("Erro ao obter conexão com o banco de dados - " + sqlex.getMessage());
                    return;
                }
            }
        }
    }
}