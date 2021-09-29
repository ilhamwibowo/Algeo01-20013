package src;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;


public class matriks {
    public int baris;
    public int kolom;
    public double [][] data;
    
    // KONSTRUKTOR MATRIKS
    public matriks(int nb, int nk){
        this.baris = nb;
        this.kolom = nk;
        data = new double[baris][kolom];
    }

    public matriks(double[][] dat, int nb, int nk){
        this.baris = nb;
        this.kolom = nk;
        this.data = dat;
    }

    public int getIdxBaris(){
        return this.baris;
    }

    public int getIdxKolom(){
        return this.kolom;
    }

    public void readMatrix () {
        Scanner scan = new Scanner(System.in);
        for(int i = 0;i<this.baris;i++) {
            for(int j = 0; j < this.kolom ;j++){
                this.data[i][j] = scan.nextDouble();
            }
        }
    }
    public void displayMatrix() {
        int i,j;
        for (i = 0;i< this.baris;i++) {
            for (j = 0;j< this.kolom;j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        } 
    }
    public void swapRow(int r1, int r2) {
        int k;
        if (r1 < 0 || r1 >= baris){
            System.out.println("Row 1 tidak valid.");
        }
        else if (r2 < 0 || r2 >= kolom){
            System.out.println("Row 2 tidak valid.");
        } else {
            for (k = 0; k < data[0].length ; k++) {
                double temp = this.data[r1][k];
                this.data[r1][k] = this.data[r2][k];
                this.data[r1][k] = temp;
            }

        }

    }
    
    public boolean isSquare() {
        return (this.kolom == this.baris);
    }

    public void kaliBaris(int r, int val){
        if(r < 0 || r >= baris){
            System.out.println("Tidak valid.");
        }
        else {
            for(int i = 0; i < this.kolom; i++){
                this.data[r1][i] *= a;
            }
        }
    }

    public void plusBaris(int r1, double val, int r2) {
        //Membuat r1 = r1 + a * r2
        if (r1 < 0 || r1 >= baris) {
            System.out.println("R1 tidak valid.");
        } else if (r2 < 0 || r2 >= baris) {
            System.out.println("R2 tidak valid.");
        } else {
            for (int i = 0; i < this.kolom; i++) {
                this.data[r1][i] += (val * this.data[r2][i]);
            }
        }
    }
    public boolean isRowZero(int r) {
        //Return true jika baris r semuanya 0
        int i = 0;
        while ((this.data[r][i]==0) && i < this.kolom - 1) {
            i++;
        }
        if (this.data[r][i]==0) {
            return true;
        } else {
            return false;
        }
    }

    public int getFirstCoef(int r) {
        //return index leading point, jika tidak ketemu return nkol
        boolean flag = false;
        int i = 0;
        while ((i < this.kolom) && !flag) {
            if (this.data[r][i]!=0) {
                flag = true;
            } else {
                i++;
            }
        }
        if (flag) {
            return i;
        } else {
            return this.kolom;
        }
    }

    public void sortMatrix() {
        //Selection sort, dari kecil ke besar
        int i, j;
        if (this.baris > 1) {
            for (i = 0; i < this.baris - 1; i++) {
                int bMax = i;
                for (j = i + 1; j < this.baris; j++) {
                    int sike = this.getFirstCoef(j);
                    if (sike < this.getFirstCoef(bMax)) {
                        bMax = j;
                    }
                }
                this.swapRow(i, bMax);
            }
        }
    }
    public void rowEsMatrix () {
        int i,j,k;
        float x;
        this.sortMatrix();
        for (k = 0;k < baris - 1 ; k++) {
            //cek apakah ada baris yang depannya nol
            if (!this.isRowZero(k)) {
                int idxFirstCoef = this.getFirstCoef(k);
                double firstCoef = this.data[k][idxFirstCoef];
                for(j=k+1; j < baris; j++){
                    if (!this.isRowZero(j)){
                        double val = -1 * this.data[j][idxFirstCoef] / firstCoef;
                        this.plusBaris(j, val, k)
                    }
                }
            }
        }
        for (i = 0; i < this.baris; i++){
            if (!this.isRowZero(i)){
                int idxFirstCoef = this.getFirstCoef(i);
                double firstCoef = this.data[i][idxFirstCoef];
                this.kaliBaris(i, 1/firstCoef);
            }
        } 
    }

    public void reducedRowMatrix () {
        int i,j;
        this.rowEsMatrix();
        for(i = baris-1 ; i > 0 ; i--) {
            if (!this.isRowZero(i)){
                int idxFirstCoef = this. getFirstCoef(i);
                for (j = i - 1; j >= 0; j--){
                    if (!this.isRowZero(j){
                        double val = -1 * this.data[j][idxFirstCoef];
                        this.plusBaris(j, pengali, i);
                    }
                }
            }
        }
    }
}
