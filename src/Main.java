package src;

import java.util.Scanner;


public class Main {
    public static void main (String[] args) {
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
        	System.out.println("1. Metode eliminasi Gauss");
        	System.out.println("2. Metode eliminasi Gauss-Jordan");
        	System.out.println("3. Metode matriks balikan");
        	System.out.println("4. Metode kaidah Cramer");
            int x = scan.nextInt();
            if (x == 1) {
                System.out.println("Sistem masih dalam pengembangan");      
            } 
            else if ( x == 2) {
                System.out.println("Sistem masih dalam pengembangan");
            }
            else if (x == 3) {
                System.out.println("Sistem masih dalam pengembangan");
            }
            else if (x == 4) {
                System.out.println("Sistem masih dalam pengembangan");
            } 
            else {
                System.out.println("Input tidak valid!");
            }
            
        }
        else if (choice == 2) {
            System.out.println("1. Metode Eliminasi Gauss");
            System.out.println("2. Metode Ekspansi Kofaktor");
            int x = scan.nextInt();
            if (x == 1) {
                System.out.println("Masukkan ukuran matriks (matriks harus berbentuk persegi NxN): ");
                int N = scan.nextInt();
                System.out.println("Masukkan elemen matriks : ");
                matriks m = new matriks(N, N);
                m.readMatrix();
                System.out.println("Matriks eselon barisnya adalah : ");
                m.rowEsMatrix2();
                m.displayMatrix();
                System.out.println("Determinannya adalah : " );
                System.out.print(matriks.determinan_gauss(m) + "\n");
            } 
            else if ( x == 2) {
                System.out.println("Masukkan ukuran matriks (matriks harus berbentuk persegi NxN): ");
                int N = scan.nextInt();
                System.out.println("Masukkan elemen matriks : ");
                matriks m = new matriks(N, N);
                m.readMatrix();
                System.out.println("Determinannya adalah : " );
                System.out.print(matriks.determinan_kofaktor(m));
                
            }
        } 
        else if (choice == 3) {
            System.out.println("Sistem masih dalam pengembangan");            
        }
        else if (choice == 4) {
            System.out.println("Masukkan jumlah titik yang diinginkan : ");
            int N = scan.nextInt();
            interpolasi.solusi(N);
        }
        else if (choice == 5) {
            System.out.println("Masukkan jumlah data yang diinginkan : ");
            int Ndat = scan.nextInt();
            System.out.println("Masukkan jumlah variabel yang diinginkan : ");
            int Nvar = scan.nextInt();
            regresi.solusiRegresi(Ndat,Nvar);
        }
        else if (choice == 6) {
            System.out.println("Selamat tinggal!");
        }
        else {
            System.out.println("Input tidak valid! Ulangi lagi!");
            scan.close();
        }
    }



    }
}

