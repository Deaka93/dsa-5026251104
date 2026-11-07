package lw04.prelab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        //membaca baris pertama: jumlah cabang (N)
        int n = scanner.nextInt();

        //membuat array int dengan ukuran N untuk menampung nilai stabilitas 
        int[] branches = new int[n];

        //melakukan perulangan untuk mengisi setiap elemen arrau dari file
        for (int i = 0; i < n; i++) {
            branches[i] = scanner.nextInt();
        }

        //membaca baris selanjutnya: jumlah target yang dicari (M)
        int m = scanner.nextInt();
        int[] targets = new int[m];

        //melakukan perulangan untuk mengisi nilai target dari file
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }

        scanner.close();

        //insertion sort descending 3 passes
        int passes = 3;
        for (int i = 1; i <= passes; i++) {
            int key = branches[i];
            int j = i - 1;
            //geser elemen di kiri yang lebih kecil dari key ke kanan
            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }
            branches[j + 1] = key;
}

        //mencetak array yang telah melewati 3 passes
        for (int i = 0; i < n; i++){
            System.out.print(branches[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        //linear search
        for (int i = 0; i < m; i++){
            int target = targets[i];
            int foundIndex = -1; //menyimpan index dengan nilai awal -1 sebagai tanda bahwa target dianggap belum ditemukan.
            //melakukan penarian
            for (int j = 0; j < n; j++){
                if (branches[j] == target){
                    foundIndex = j; //menyimpan index jika target ditemukan
                    break; //menghentikan pencarian karena target sudah ditemukan
                }
            }
            if (foundIndex != -1){
                System.out.println(target + ": FOUND at index " + foundIndex);
            } else {
                System.out.println(target + ": NOT FOUND");
            } 
        }
    }
}
