package src;

import java.util.Scanner;

import jdk.internal.jshell.tool.resources.l10n;

import java.util.*;


public class Main {
<<<<<<< HEAD
    public static void main (String[] args) {
=======
    public static void main(String[] args) {
        outputCapturer capture = new outputCapturer();
>>>>>>> 59cb9a867f42d5a7df05c80be9c73e8c42a86aa1
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
<<<<<<< HEAD
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
            
=======
            int baris, kolom;
            matriks m;
            System.out.println("1. Metode eliminasi Gauss");
            System.out.println("2. Metode eliminasi Gauss-Jordan");
            System.out.println("3. Metode matriks balikan");
            System.out.println("4. Kaidah Cramer");
            int splPilih = scan.nextInt();
            if (splPilih == 1){
                if(/*external.loadStatus*/baris == 1){
                    // M=external.bacaMatrixFile("../test/data_input/file_externalmatrix.txt"); 
                }else{
                    System.out.print("Banyak persamaan : ");
                    baris = scan.nextInt();
                    System.out.print("Banyak variable : ");
                    kolom = scan.nextInt();
                    System.out.println("Masukkan persamaan : ");
                    m = new matriksParametrik(baris, kolom + 1);
                    m.readMatrix();
                    capture.start();
                    m.displayMatrix();
                    try{
                        m.rowEsMatrix();
                        m.displayMatrix();
                        System.out.println("^ Matrix hasil eliminasi Gauss ^");
                        .genStatus();
                        m.parametrikGauss();
                    } catch(noSolution n){
                        System.out.println("Tidak ada solusi");
                        }
                    }
                }
            else if(splPilih == 2){
                if(/*external.loadStatus*/baris == 1){
                    // M=external.bacaMatrixFile("../test/data_input/file_externalmatrix.txt"); 
                }else{
                    System.out.print("Banyak persamaan : ");
                    baris = scan.nextInt();
                    System.out.print("Banyak variable : ");
                    kolom = scan.nextInt();
                    System.out.println("Masukkan persamaan : ");
                    m = new matriksParametrik(baris, kolom + 1);
                    m.readMatrix();
                    capture.start();
                    m.displayMatrix();
                    try{
                        m.reducedRowMatrix();
                        m.displayMatrix();
                        System.out.println("^ Matrix hasil eliminasi Gauss ^");
                        m.genStatus();
                        m.parametrikGJ();
                    } catch(noSolution n){
                        System.out.println("Tidak ada solusi");
                        }

            }
            else if(splPilih == 3){

            }
            else if(splPilih == 4){

            }
>>>>>>> 59cb9a867f42d5a7df05c80be9c73e8c42a86aa1
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

