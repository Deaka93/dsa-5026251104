package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactionsList = new LinkedList<>();
        LinkedList<String[]> customersList = new LinkedList<>();

        // Membaca file transaksi
        try {
            File file = new File("transactions.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" ");
                String name = parts[0];
                String type = parts[1];
                String amount = parts[2];

                transactionsList.add(new String[]{name, type, amount});

                // Mengecek apakah nasabah sudah terdaftar
                boolean exists = false;
                for (String[] customer : customersList) {
                    if (customer[0].equals(name)) {
                        exists = true;
                        break;
                    }
                }

                // Menambahkan nasabah baru dengan saldo awal 0
                if (!exists) {
                    customersList.add(new String[]{name, "0"});
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt tidak ditemukan.");
            return;
        }

        // Memindahkan transaksi ke Queue (FIFO)
        Queue<String[]> transactionQueue = new LinkedList<>();
        while (!transactionsList.isEmpty()) {
            transactionQueue.add(transactionsList.poll());
        }

        Stack<String[]> failedTransactionsStack = new Stack<>();

        // Memproses transaksi
        while (!transactionQueue.isEmpty()) {
            String[] currentTx = transactionQueue.poll();
            String name = currentTx[0];
            String type = currentTx[1];
            int amount = Integer.parseInt(currentTx[2]);

            for (String[] customer : customersList) {
                if (customer[0].equals(name)) {
                    int currentBalance = Integer.parseInt(customer[1]);

                    // Memproses DEPOSIT dan WITHDRAW secara terpisah
                    if (type.equals("DEPOSIT")) {
                        customer[1] = String.valueOf(currentBalance + amount);
                    } else if (type.equals("WITHDRAW")) {
                        if (amount > currentBalance) {
                            failedTransactionsStack.push(currentTx);
                        } else {
                            customer[1] = String.valueOf(currentBalance - amount);
                        }
                    }
                    break; // Keluar dari loop pencarian nasabah setelah ketemu
                }
            }
        }

        // Menampilkan hasil di luar perulangan transaksi
        System.out.println("=== Final Balances ===");
        for (String[] customer : customersList) {
            System.out.println(customer[0] + ": " + customer[1]);
        }

        System.out.println("\n=== Failed Transactions ===");
        while (!failedTransactionsStack.isEmpty()) {
            String[] failedTx = failedTransactionsStack.pop();
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}