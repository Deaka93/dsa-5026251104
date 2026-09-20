package lw01.prelab;

import java.io.File;
import java .io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        List<PrintJob> jobs = new ArrayList<>();

        //membuat objek file yang mengarah pada jobs.txt
        File file = new File("jobs.txt");

        //membuka file jobs.txt melalui objek scaner
        Scanner scanner = new Scanner(file);

        //perulangan yang mengecek apakah ada kata yang tersisa dalam file
        while (scanner.hasNext()){
            String type = scanner.next();
            
            //membaca kata kedua dan menyimpan ke variabel id
            String id = scanner.next();

            //membaca kata ketiga yang berisi jumlah halaman (int) dan menyimpan ke variabel pages
            int pages = scanner.nextInt();

            //mengecek tipe
            //jika tipe nya mono, maka buat objek baru monoprint dan masukkan ke dalam daftar
            if (type.equals("MONO")) {
                jobs.add(new MonoPrint(id, pages));
            } 
            //jika tipenya colour, maka buat objek colourprint dan masukkan ke daftar
            else if (type.equals("COLOUR")) {
                jobs.add(new ColourPrint(id, pages));
            }
        }
        scanner.close();

        /** perulangan untuk mengambil tiap objek job yang tersimpan dalam daftar jobs satu persatu, 
         * lalu mencetak ringkasnya melalui job.summary() **/
        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}
