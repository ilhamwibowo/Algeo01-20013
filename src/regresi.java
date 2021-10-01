package src;

import java.util.Scanner;

import matriks;

public class regresi {
	public static void solusiRegresi (int Ndat, int Nvar, double x) {
		int i,j,k;
		double num = 0;
		int kol = 0;
		Scanner scan = new Scanner(System.in);
		matriks data = new matriks(Ndat, Nvar+1);
		System.out.println("Masukkan data yang ingin dilakukan regresi (contoh : x1 x2 x3 y1): ");
		for (i = 0;i < Ndat; i++ ) {
			for (j = 0;j <= Nvar; j++) {
				data.data[i][j] = scan.nextDouble();
			}
		}
		
		matriks reg = new matriks(Nvar+1,Nvar+2);
		for (i = 0;i <= Nvar; i++) {
			for (j = 0; j <= Nvar+1;j++) {
				if (i == 0 && j == 0) {
					reg.data[0][0] = Ndat;
				}
				else if (i == 0 && j > 0) {
					for (k = 0; k <data.kolom;k++) {
						reg.data[i][j] += data.data[k][j-1];
				}
				
				}
				else {
					if (j == 0) {
						reg.data[i][j] = num;
					}
					else {
						reg.data[i][j] = num * reg.data[0][j];
					}
				}
			}
			kol += 1;
			num = reg.data[0][kol];
			
			
		}
		
		System.out.println("Menggunakan normal estimation equation for multiple linear regression diperoleh : ");
		reg.displayMatrix();
		
		matriks solusi = new matriks(Nvar+1,1);
		solusi = solusiSPL(reg,"x");
		solusi.displayMatrix();
		boolean same = matriks.isEqual(reg, solusi);
		if (same) {
			System.out.println();
		} else {
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
            System.out.println("SPL tersebut tidak memiliki solusi");
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
    	System.out.print("Hasil estimasi dari " + x + " adalah " + result);
    }
    
    public static boolean isEqual
}