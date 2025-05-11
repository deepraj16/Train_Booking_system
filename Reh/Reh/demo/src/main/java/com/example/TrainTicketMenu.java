package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class TrainTicketMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Configuration cfg = new Configuration()
                .configure() // looks for hibernate.cfg.xml
                .addAnnotatedClass(TrainTicket.class);
        SessionFactory factory = cfg.buildSessionFactory();

        while (true) {
            System.out.println("\n--- Train Ticket Menu ---");
            System.out.println("1. Insert Ticket");
            System.out.println("2. View Ticket");
            System.out.println("3. Update Ticket");
            System.out.println("4. Delete Ticket");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    Session insertSession = factory.openSession();
                    Transaction insertTx = insertSession.beginTransaction();

                    TrainTicket ticket = new TrainTicket();
                    System.out.print("Enter Ticket ID: ");
                    ticket.setTicketId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Enter Passenger Name: ");
                    ticket.setPassengerName(sc.nextLine());
                    System.out.print("Enter Train Number: ");
                    ticket.setTrainNumber(sc.nextLine());
                    System.out.print("Enter Source: ");
                    ticket.setSource(sc.nextLine());
                    System.out.print("Enter Destination: ");
                    ticket.setDestination(sc.nextLine());
                    System.out.print("Enter Price: ");
                    ticket.setPrice(sc.nextDouble());

                    insertSession.persist(ticket);
                    insertTx.commit();
                    insertSession.close();
                    System.out.println("✅ Ticket Inserted.");
                    break;

                case 2:
                    System.out.print("Enter Ticket ID to View: ");
                    int viewId = sc.nextInt();
                    Session viewSession = factory.openSession();
                    TrainTicket viewTicket = viewSession.get(TrainTicket.class, viewId);
                    if (viewTicket != null) {
                        System.out.println(viewTicket);
                    } else {
                        System.out.println("❌ Ticket not found.");
                    }
                    viewSession.close();
                    break;

                case 3:
                    System.out.print("Enter Ticket ID to Update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    Session updateSession = factory.openSession();
                    Transaction updateTx = updateSession.beginTransaction();

                    TrainTicket updateTicket = updateSession.get(TrainTicket.class, updateId);
                    if (updateTicket != null) {
                        System.out.print("Enter New Passenger Name: ");
                        updateTicket.setPassengerName(sc.nextLine());
                        System.out.print("Enter New Price: ");
                        updateTicket.setPrice(sc.nextDouble());

                        updateSession.merge(updateTicket); // updated safely
                        updateTx.commit();
                        System.out.println("✅ Ticket Updated.");
                    } else {
                        System.out.println("❌ Ticket not found.");
                        updateTx.rollback();
                    }
                    updateSession.close();
                    break;

                case 4:
                    System.out.print("Enter Ticket ID to Delete: ");
                    int deleteId = sc.nextInt();
                    Session deleteSession = factory.openSession();
                    Transaction deleteTx = deleteSession.beginTransaction();

                    TrainTicket deleteTicket = deleteSession.get(TrainTicket.class, deleteId);
                    if (deleteTicket != null) {
                        deleteSession.remove(deleteTicket);
                        deleteTx.commit();
                        System.out.println("✅ Ticket Deleted.");
                    } else {
                        System.out.println("❌ Ticket not found.");
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
