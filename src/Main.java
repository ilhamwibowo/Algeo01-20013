package src;

import java.util.Scanner;



import java.util.*;


public class Main {

    public static void main(String[] args) {
        outputCapturer capture = new outputCapturer();
        Load ambil =  new Load();
        String captured;
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
            int baris, kolom;
            matriksParametrik m;
            System.out.println("1. Metode eliminasi Gauss");
            System.out.println("2. Metode eliminasi Gauss-Jordan");
            System.out.println("3. Metode matriks balikan");
            System.out.println("4. Kaidah Cramer");
            int splPilih = scan.nextInt();
            
            if (splPilih == 1){
                ambil.cekSimpan();
                if(ambil.simpanStatus()){
                    ambil.namaExternal();
                }
                ambil.cekLoad();
                if(ambil.loadStatus()){
                    m = ambil.bacaMatrixFile("../test/inputFile.txt"); 
                }else{
                    System.out.print("Banyak persamaan : ");
                    baris = scan.nextInt();
                    System.out.print("Banyak variable : ");
                    kolom = scan.nextInt();
                    System.out.println("Masukkan persamaan : ");
                    m = new matriksParametrik(baris, kolom + 1);
                }
                m.readMatrix();
                capture.start();
                m.displayMatrix();
                System.out.println("Memulai algoritma");
                    try{
                        m.rowEsMatrix();
                        m.displayMatrix();
                        System.out.println("^ Matrix hasil eliminasi Gauss ^");
                        m.genStatus();
                        m.parametrikGauss();
                    } catch(noSolution n){
                        System.out.println("Tidak ada solusi");
                        }
            captured = capture.stop(); 
            }
                
            else if(splPilih == 2){
                ambil.cekSimpan();
                if(ambil.simpanStatus()){
                    ambil.namaExternal();
                }
                ambil.cekLoad();
                if(ambil.loadStatus()){
                     m = ambil.bacaMatrixFile("../test/inputFile.txt"); 
                }else{
                    System.out.print("Banyak persamaan : ");
                    baris = scan.nextInt();
                    System.out.print("Banyak variable : ");
                    kolom = scan.nextInt();
                    System.out.println("Masukkan persamaan : ");
                    m = new matriksParametrik(baris, kolom + 1);
                }
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
            captured = capture.stop();
            }
            else if(splPilih == 3){

            }
            else if(splPilih == 4){

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
                m.rowEsMatrix();
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

