package src;

import java.util.Scanner;
import src.matriks;

public class determinan extends matriks {

    //MENGHITUNG DETERMINAN MENGGUNAKAN MATRIKS ESELON BARIS
    public static double determinan_gauss (matriks matrix) { 
        int i,j;
        double val = 1;
        double det = 1;
        rowEsMatrix(); // prekondisi : isquare bernilai true
        for(i = 0; i < matrix.baris;i++) {
            for (j = 0;j < matrix.kolom;j++) {
                if (matrix.data[i][j] == 0 && j == matrix.kolom-1){ //terdapat baris yang semua nilainya nol
                    val = 0; 
                    break;   
                }     
                else if (matrix.data[i][j] == 0){
                    continue;
                } 
                else {
                    val = matrix.data[i][j];
                    break;
                    }
            }
            if (i == matrix.baris-1){
                System.out.print(val + " = ");  
            } 
            else {
                System.out.print(val + " x ");  
            }
    
            det = det * val;
            } 
        return det;
}


// MENENTUKAN DETERMINAN MENGGUNAKAN EKSPANSI KOFAKTOR
// PREKONDISI : MATRIKS BERBENTUK PERSEGI
public static double determinan_kofaktor (matriks matrix) {
    int j;
    double det = 0;
    if (matrix.baris == 1) {
        return matrix.data[0][0];
    }
    //basis rekursi
    else if (matrix.baris == 2 ) { 
        return (matrix.data[0][0]*matrix.data[1][1] - matrix.data[1][0]*matrix.data[0][1]); 
    }
    //rekursi
    else {
        for (j = 0;j < matrix.kolom;j++){
            matriks matMinor  = matriks.minorMat(matrix, 0, j);
            if(j%2 == 0) {
                det = det + matrix.data[0][j] * determinan_kofaktor(matMinor);
            } 
            else {
                det = det - matrix.data[0][j] * determinan_kofaktor(matMinor);
            }
        }
    }
    return det;
}

}