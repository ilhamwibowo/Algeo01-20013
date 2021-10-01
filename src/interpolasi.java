package src;

import java.util.Scanner;

public class interpolasi {

    //bentuk matriks augmented dari masukan titik  
    public static void solusi(int N) {
    	Scanner scan = new Scanner(System.in);
        int i,j,k;
        double num = 1;
        matriks point = new matriks(N, 2);
        matriks m = new matriks(N, N+1); //N adalah jumlah titik
        System.out.println("Masukkan data titik yang diinginkan (contoh x1 y1) : ");
        point.readMatrix(); 
        for (i = 0; i < m.baris; i++) {
        	num = 1;
            for (j = 0; j < m.kolom; j++) {
                if (j == m.kolom - 1) {
                    m.data[i][j] = point.data[i][1];
                }
                else {
                    m.data[i][j] = num;
                    num = num * point.data[i][0];
                }
        }
            

    }
        m.displayMatrix();
        matriks solusi = new matriks(N,1);
        solusi = solusiSPL(m,"x");
		boolean same = matriks.isEqual(m, solusi);
		if (same) {
			System.out.println();
		} else {
			System.out.println("Masukkan nilai yang akan diestimasi : ");
	        double x = scan.nextDouble();
			printHasil(solusi,x);
		}
        
    }

    public static matriks solusiSPL(matriks m1, String val) {
        int n = m1.baris;

        // Membuat split matriks

        matriks A = new matriks(n, n);
        for (int i = 0; i <= A.getLastIdxBrs(A); i++) {
            for (int j = 0; j < A.kolom; j++) {
                A.data[i][j] = m1.data[i][j];
            }
        }

        matriks B = new matriks(n, 1);
        for (int i = 0; i < B.baris; i++) {
            B.data[i][0] = m1.data[i][n];
        }


        matriks inverted = new matriks(n, n);
        if (matriks.balikanGJ(A, inverted)) {
            matriks X = matriks.kaliMatriks(inverted, B); 
            // output
            for (int i = 0; i <= X.getLastIdxBrs(X); i++) {
                System.out.println(val + (i + 1) + " = " + X.data[i][0]);
            }
            return X;
        } 
        else {
            System.out.println("SPL tersebut tidak memiliki solusi" + "\n");
            return m1;
        }
        

    }

    public static void printHasil (matriks m, double x) {
    	int i,j;
    	double result = 0;
    	double num = 1;
    	for (i = 0;i < m.baris; i++) {
    		for (j = 0; j < i;j++) {
    			num *= x;
    		}
    		result += m.data[i][0] * num;
    		num = 1;
    	}
    	System.out.print("Hasil estimasi dari " + x + " adalah " + result + "\n");
    }
    

}
