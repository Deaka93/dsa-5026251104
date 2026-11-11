package lw04.unguided;

import java.util.*;

class SortBot {
    int[] shelf;
    int passesDone = 0;

    SortBot(int[] shelf) {
        this.shelf = shelf;
    }

    void run(int passes) {
        while (passesDone < passes) {
            int i = passesDone;
            for (int j = 0; j < shelf.length - 1 - i; j++) {
                if (shelf[j] < shelf[j + 1]) {
                    int temp = shelf[j];
                    shelf[j] = shelf[j + 1];
                    shelf[j + 1] = temp;
                }
            }
            passesDone++;
        }
    }
}

public class Main {
    static int steps;

    static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        steps = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = sc.nextInt();
        int[] parcels = new int[n];
        for (int i = 0; i < n; i++) {
            parcels[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        SortBot bot = new SortBot(parcels);

        bot.run(2);
        int[] saved = new int[n];
        for (int i = 0; i < n; i++) {
            saved[i] = bot.shelf[i];
        }

    
        bot.run(n - 1);

        System.out.println("==== After 2 Passes ====");
        printArray(saved);
        System.out.println("==== Fully Sorted ====");
        printArray(bot.shelf);
        System.out.println("==== Search Results ====");

        for (int i = 0; i < m; i++) {
            int index = binarySearch(bot.shelf, targets[i]);
            if (index >= 0) {
                System.out.println(targets[i] + ": FOUND at index " + index + " (steps: " + steps + ")");
            } else {
                System.out.println(targets[i] + ": NOT FOUND (steps: " + steps + ")");
            }
        }
    }
}