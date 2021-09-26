import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int choice = 0;
        while (choice != 6) {
        System.out.println("MENU");        
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi Linier Berganda");
        System.out.println("6. Keluar");
        Scanner scan = new Scanner(System.in);
        choice = scan.nextInt();
        if (choice == 1) {
            System.out.println("Sistem masih dalam pengembangan");
        }
        else if (choice == 2) {
            System.out.println("1. Metode Eliminasi Gauss");
            System.out.println("2. Metode Ekspansi Kofaktor");
            int x = scan.nextInt();
            if (x == 1) {
                System.out.println("Masukkan ukuran matriks (matriks harus berbentuk persegi NxN): ");
                int N = scan.nextInt();
                float[][] mat = new float[N][N];
                System.out.println("Masukkan elemen matriks : ");
                matrixlib.readMatrix(mat);
                System.out.println("Matriks eselon barisnya adalah : ");
                matrixlib.rowEsMatrix(mat);
                matrixlib.displayMatrix(mat);
                System.out.println("Determinannya adalah : ");
                System.out.print(matrixlib.countDetRow(mat) + "\n"); 
                
                

                float[][] mat2 = matrixlib.readFileMatrix();
                matrixlib.displayMatrix(mat2);
            }             
        } 
        else if (choice == 3) {
            System.out.println("Sistem masih dalam pengembangan");            
        }
        else if (choice == 4) {
            System.out.println("Sistem masih dalam pengembangan");
        }
        else if (choice == 5) {
            System.out.println("Sistem masih dalam pengembangan");
        }
        else if (choice == 6) {
            System.out.println("Selamat tinggal");
        }
        else {
            System.out.println("Input tidak valid! Ulangi lagi!");
        }
    }



    }
}
