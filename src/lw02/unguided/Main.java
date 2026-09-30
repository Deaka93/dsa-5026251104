package lw02.unguided;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foodStock = new LinkedList<>();
        LinkedList<String[]> drinkStock = new LinkedList<>();
        LinkedList<String[]> successOrders = new LinkedList<>();

        Queue<String[]> processedOrders = new LinkedList<>();
        Stack<String[]> failOrders = new Stack<>();

        //menambahkan stok makanan
        foodStock.add(new String[]{"Bakso", "2"});
        foodStock.add(new String[]{"Sate", "1"});
        foodStock.add(new String[]{"Soto", "2"});

        //menambahkan stok minuman
        drinkStock.add(new String[]{"EsTeh", "4"});
        drinkStock.add(new String[]{"EsJeruk", "2"});

        Scanner scanner = new Scanner (Main.class.getResourceAsStream("orders.txt"));
        while (scanner.hasNext()) {
            String[] order = new String[4];
            order[0] = scanner.next();
            order[1] = scanner.next();
            order[2] = scanner.next();
            orders.add(order);
        }
        scanner.close();

        while (!processedOrders.isEmpty()) {
            String[] order = processedOrders.poll();

            String name = order[0];
            String foodName = order[1];
            String drinkName = order[2];
            int qty = Integer.parseInt(order[3]);

            boolean foodAvailable = true;
            String[] foodTarget = null;
            if (!foodName.equals("-")){
                foodAvailable = false;
                for (String[] f : foodStock) {
                    if (f[0].equals(foodName)){
                        foodTarget = f;
                        if (Integer.parseInt(f[1]) > 0){
                            foodAvailable = true;
                        }
                        break;
                    }
                }
            }

            boolean drinkAvailable = true;
            String[] drinkTarget = null;
            if (!drinkName.equals("-")){
                drinkAvailable = false;
                for (String[] d : drinkStock) {
                    if (d[0].equals(drinkName)){
                        drinkTarget = d;
                        if (Integer.parseInt(d[1]) > 0){
                            drinkAvailable = true;
                        }
                        break;
                    }
                }
            }

            if (foodAvailable && drinkAvailable) {

                if (foodTarget != null) {
                    int currentStock = Integer.parseInt(foodTarget[1]);
                    foodTarget[1] = String.valueOf(currentStock - 1);
                }
                if (drinkTarget != null) {
                    int currentStock = Integer.parseInt(drinkTarget[1]);
                    drinkTarget[1] = String.valueOf(currentStock - 1);
                }
                successOrders.add(order);
            } else {

                failOrders.push(order);
            }
            
        }

        System.out.println("===Success Processed Orders===");
        for (String[] s : successOrders) {
            System.out.println(s[0] + " " + s[1] + " " + s[2] +  " " + s[3]);
        }

        System.out.println("===Remaining Food Stock===");
        for (String[] f : foodStock) {
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println("===Remaining Drink Stock===");
        for (String[] d : drinkStock) {
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println("===failed Orders===");
        while (!String[] fa : failOrders) {
            System.out.println(s[0] + " " + s[1] + " " + s[2] +  " " + s[3]);
        }
    }
}
