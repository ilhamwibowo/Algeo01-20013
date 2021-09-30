package src;

import java.util.*;

public class SPL {
    static Scanner scanner = new Scanner(System.in);

    public static void SPLInvers(String val) {
        System.out.print("Masukan m : ");
        int n = scanner.nextInt();
        matriks M = new matriks(n, n + 1);

        M.readMatrix();

        SPLInvers(M, val);
    }

    public static void SPLInvers(matriks m1, String val) {
        int n = m1.baris;

        // Membuat split matriks

        matriks A = new matriks(n, n);
        for (int i = 0; i <= A.getLastIdxBrs(A); i++) {
            for (int j = 0; j <= A.getLastIdxKol(A); j++) {
                A.data[i][j] = m1.data[i][j];
            }
        }

        matriks B = new matriks(n, 1);
        for (int i = 0; i <= B.getLastIdxBrs(B); i++) {
            B.data[i][0] = m1.data[i][n];
        }

        // OUTPUT
        matriks inverted = new matriks(n, n);
        if (matriks.balikanGJ(A, inverted)) {
            matriks X = matriks.kaliMatriks(inverted, B); // PROSES SEBELUM OUTPUT

            for (int i = 0; i <= X.getLastIdxBrs(X); i++) {
                System.out.println(val + (i + 1) + " = " + X.data[i][0]);
            }
        } 
        else {
            System.out.println("SPL tersebut tidak memiliki solusi");
        }

    }
    public static void SPLCramer(String val) {
        System.out.print("Masukan m : ");
        int n = scanner.nextInt();
        matriks M = new matriks(n, n + 1);

        M.readMatrix();

        SPLCramer(M, val);
    }

    public static void SPLCramer(matriks M, String val) {
        int n = M.baris;

        // SPLIT INPUT
        matriks A = new matriks(n, n);
        for (int i = 0; i <= A.getLastIdxBrs(A); i++) {
            for (int j = 0; j <= A.getLastIdxKol(A); j++) {
                A.data[i][j] = M.data[i][j];
            }
        }

        matriks B = new matriks(n, 1);
        for (int i = 0; i <= B.getLastIdxBrs(B); i++) {
            B.data[i][0] = M.data[i][n];
        }

        // PROSES & OUTPUT
        double detA = cofacMat(A);

        if (detA != 0) {
            double[] x = new double[n];

            for (int j = 0; j <= A.getLastIdxKol(A); j++) {
                matriks beatles = new matriks(n, n);
                matriks.copyMatrix(A, beatles);

                for (int i = 0; i <= A.baris; i++) {
                    beatles.data[i][j] = B.data[i][0];
                }

                x[j] = beatles.cofacMat(beatles) / detA;
            }

            // OUTPUT
            for (int i = 0; i < n; i++) {
                System.out.println(val + (i + 1) + " = " + x[i]);
            }
        } else {
            System.out.println("SPL tersebut tidak memiliki solusi");
        }
    }


}
