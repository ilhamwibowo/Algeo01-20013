package src;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;


public class matriks {
    public int baris;
    public int kolom;
    public double [][] data;
    
    // KONSTRUKTOR matriks
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

    
    
    /* ********** SELEKTOR ********** */
    public int getIdxbaris(){
        return this.baris;
    }
    
    public int getIdxkolom(){
        return this.kolom;
    }
    
    public int getFirstIdxBrs(matriks M) {
        return 0;
    }

    public int getFirstIdxKol(matriks M) {
        return 0;
    }

    public int getLastIdxBrs(matriks M) {
        return M.baris - 1;
    }

    public int getLastIdxKol(matriks M) {
        return M.baris - 1;
    }

    public int ELMT(matriks M) {
        return (M.baris * M.kolom);
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

    public static boolean IsIdentitas(matriks M) {
        boolean out = true;
        for (int i = 0; i < M.baris; i++) {
            for (int j = 0; j < M.kolom; j++) {
                if (!(((i == j) && M.data[i][j] == 1) || ((i != j) && M.data[i][j] == 0))) {
                    out = false;
                }
            }
        }
        return out;
    }


    public static matriks matrixIdentitas(int N) {
        matriks I = new matriks(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    public static matriks matriksHilbert(int N) {
        matriks H = new matriks(N, N + 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                H.data[i][j] = 1.0 / (i + j + 1);
            }
        }
        return H;
    }

    public void kalibaris(int r, double val){
        if(r < 0 || r >= baris){
            System.out.println("Tidak valid.");
        }
        else {
            for(int i = 0; i < this.kolom; i++){
                this.data[r][i] *= val;
            }
        }
    }
    public static matriks kaliMatriks(matriks M, double k) {
        matriks out = new matriks(M.baris, M.kolom);
        for (int i = 0; i < M.baris; i++) {
            for (int j = 0; j < M.kolom; j++) {
                out.data[i][j] = M.data[i][j] * k;
            }
        }
        return out;
    }

    public void kaliMatriks(double x) {
        this.data = kaliMatriks(this, x).data;
    }

    public static matriks kaliMatriks(matriks M, matriks N) {
        matriks out = new matriks(M.baris, N.kolom);
        for (int i = 0; i<out.baris; i++) {
            for (int j = 0; j < out.kolom; j++) {
                out.data[i][j] = 0;
                for (int k = 0; k < M.kolom; k++) {
                    out.data[i][j] += M.data[i][k] * N.data[k][j];
                }
            }
        }

        return out;
    }

    static matriks cofacMat(double[][] matrix) {
        int i,j;
        matriks cofacMat = minorMatrix(matrix);
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length;j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 1) {
                        data[i][j] *= -1;
                    }
                } else {
                    if (j % 2 == 0) {
                        data[i][j] *= -1;
                    }
                }
            }
        }
        return cofacMat;
    }
    
    static float[][] minorMatrix (float[][] matrix) {
        int i,j;
        float[][] minorMatrix = new float[matrix.length][matrix[0].length];
        for (i = 0; i < matrix.length; i++ ) {
            for (j = 0; j < matrix[0].length; j++ ) {
                minorMatrix[i][j] = countDetCof(minorMat(matrix, i, j));
            }
        }
        return minorMatrix;
    }
    
    //FUNGSI TRANSPOSE matriks 
    static float[][] transpose(float[][] matrix) {
        int i,j;
        float[][] transMat = new float[matrix[0].length][matrix.length];
        for(i = 0; i < transMat.length; i++) {
            for (j = 0; j < transMat[0].length;j++ ) {
                transMat[i][j] = matrix[j][i];
            }
        }
        return transMat;
    }
    
    //MENCARI ADJOINT matriks
    static float[][] adjoint(double[][] matrix) {
        float[][] adjMat = transpose(cofacMat(matrix));
        return adjMat;
    }
    
    }

    public void plusbaris(int r1, double val, int r2) {
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

    public static void copyMatrix(matriks m2, matriks m1) {

        m1.baris = m2.baris;
        m1.kolom = m2.kolom;
        m1.data = new double[m2.baris][m2.kolom];

        for (int i = 0; i < m1.baris; i++) {
            for (int j = 0; j < m1.kolom; j++) {
                m1.data[i][j] = m2.data[i][j];
            }
        }
    }

    // Varian fungsi dari Copy matriks diatas
    public static matriks copyMatrix(matriks m2) {
        matriks m1 = new matriks(1, 1);
        copyMatrix(m2, m1);
        return m1;
    }
    public static matriks sambungbarisM(matriks M, matriks N) {
    // menyambungkan m dan n dalam baris yang sama
        matriks out = new matriks(M.baris, M.kolom + N.kolom);
        for (int i = 0; i < out.baris; i++) {
            for (int j = 0; j < out.kolom; j++) {
                if (j < M.kolom) {
                    out.data[i][j] = M.data[i][j];
                } else {
                    out.data[i][j] = N.data[i][j - M.kolom];
                }
            }
        }

        return out;
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
                        this.plusbaris(j, val, k);
                    }
                }
            }
        }
        for (i = 0; i < this.baris; i++){
            if (!this.isRowZero(i)){
                int idxFirstCoef = this.getFirstCoef(i);
                double firstCoef = this.data[i][idxFirstCoef];
                this.kalibaris(i, 1/firstCoef);
            }
        } 
    }

    public void reducedRowMatrix () {
        int i,j;
        this.rowEsMatrix();
        for(i = baris-1 ; i > 0 ; i--) {
            if (!this.isRowZero(i)){
                int idxFirstCoef = this.getFirstCoef(i);
                for (j = i - 1; j >= 0; j--){
                    if (!this.isRowZero(j)) {
                        double val = -1 * this.data[j][idxFirstCoef];
                        this.plusbaris(j, val, i);
                    }
                }
            }
        }
    }
    public static boolean balikanGJ(matriks m1, matriks minv) {
        // jika gagal, matriks tetap sama
        matriks M = copyMatrix(m1);
    
        M = sambungbarisM(M, matrixIdentitas(M.baris));
        M.reducedRowMatrix();
    
        matriks N = new matriks(m1.baris, m1.kolom);
        for (int i = 0; i < N.baris; i++) {
            for (int j = 0; j < N.kolom; j++) {
                N.data[i][j] = M.data[i][j];
            }
        }
    
        if (IsIdentitas(N)) {
            copyMatrix(m1, minv);
    
            for (int i = 0; i < minv.baris; i++) {
                for (int j = 0; j < minv.kolom; j++) {
                    minv.data[i][j] = M.data[i][j + minv.kolom];
                }
            }
            return true;
        } 
        else {
            copyMatrix(m1, minv);
            return false;
        }
    }
    public boolean balikanAdjoin(matriks m1, matriks m2) {
        copyMatrix(m1, m2);
        double det = cofacMat(m2);
        if (det != 0) {
            m2.adjoint(m2);
            m2.kaliMatriks(1/det);
            return true;
        } else {
            return false;
        }
    }
    
}
