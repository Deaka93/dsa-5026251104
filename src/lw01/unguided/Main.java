package lw01.unguided;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main (String[] args) {
        List<WashService> services = new ArrayList<>();
        File file = new File("washes.txt");
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNext()){
            int input = scanner.nextInt();
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("MOTORCYCLE")) {
                services.add(new MotorcycleWash(id, days, units));
            } 
            //jika tipenya colour, maka buat objek colourprint dan masukkan ke daftar
            else if (type.equals("CAR")) {
                services.add(new CarWash(id, days, units));
            }
        }
        scanner.close();

        for (WashService service : services) {
            System.out.println(service.summary());
        }

    }
}
