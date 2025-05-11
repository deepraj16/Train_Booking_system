package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class MovieMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Configuration cfg = new Configuration()
                .configure() // looks for hibernate.cfg.xml
                .addAnnotatedClass(Movie.class);
        SessionFactory factory = cfg.buildSessionFactory();

        while (true) {
            System.out.println("\n--- Movie Database Menu ---");
            System.out.println("1. Insert Movie");
            System.out.println("2. View Movie");
            System.out.println("3. Update Movie");
            System.out.println("4. Delete Movie");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    Session insertSession = factory.openSession();
                    Transaction insertTx = insertSession.beginTransaction();

                    Movie movie = new Movie();
                    System.out.print("Enter Movie ID: ");
                    movie.setMovieId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    movie.setTitle(sc.nextLine());
                    System.out.print("Enter Director: ");
                    movie.setDirector(sc.nextLine());
                    System.out.print("Enter Genre: ");
                    movie.setGenre(sc.nextLine());
                    System.out.print("Enter Release Year: ");
                    movie.setReleaseYear(sc.nextInt());
                    System.out.print("Enter Rating: ");
                    movie.setRating(sc.nextDouble());

                    insertSession.persist(movie);
                    insertTx.commit();
                    insertSession.close();
                    System.out.println("✅ Movie Inserted.");
                    break;

                case 2:
                    System.out.print("Enter Movie ID to View: ");
                    int viewId = sc.nextInt();
                    Session viewSession = factory.openSession();
                    Movie viewMovie = viewSession.get(Movie.class, viewId);
                    if (viewMovie != null) {
                        System.out.println(viewMovie);
                    } else {
                        System.out.println("❌ Movie not found.");
                    }
                    viewSession.close();
                    break;

                case 3:
                    System.out.print("Enter Movie ID to Update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    Session updateSession = factory.openSession();
                    Transaction updateTx = updateSession.beginTransaction();

                    Movie updateMovie = updateSession.get(Movie.class, updateId);
                    if (updateMovie != null) {
                        System.out.print("Enter New Title: ");
                        updateMovie.setTitle(sc.nextLine());
                        System.out.print("Enter New Rating: ");
                        updateMovie.setRating(sc.nextDouble());

                        updateSession.merge(updateMovie);
                        updateTx.commit();
                        System.out.println("✅ Movie Updated.");
                    } else {
                        System.out.println("❌ Movie not found.");
                        updateTx.rollback();
                    }
                    updateSession.close();
                    break;

                case 4:
                    System.out.print("Enter Movie ID to Delete: ");
                    int deleteId = sc.nextInt();
                    Session deleteSession = factory.openSession();
                    Transaction deleteTx = deleteSession.beginTransaction();

                    Movie deleteMovie = deleteSession.get(Movie.class, deleteId);
                    if (deleteMovie != null) {
                        deleteSession.remove(deleteMovie);
                        deleteTx.commit();
                        System.out.println("✅ Movie Deleted.");
                    } else {
                        System.out.println("❌ Movie not found.");
                        deleteTx.rollback();
                    }
                    deleteSession.close();
                    break;

                case 5:
                    factory.close();
                    System.out.println("👋 Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid Choice.");
            }
        }
    }
}
