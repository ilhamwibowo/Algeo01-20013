import java.util.Scanner;
import java.util;

class matrixlib {

//SWAP ROW I DENGAN ROW J
static void swapRow(float matrix[][],int i,int j) {
    int k;
    float temp;
    for (k = 0; k < matrix[0].length ; k++) {
        temp = matrix[i][k];
        matrix[i][k] = matrix[j][k];
        matrix[j][k] = temp;
    }
}


//MENERIMA INPUT MATRIKS DARI USER
static void readMatrix (float[][] matrix) {
    int i,j;
    Scanner scan = new Scanner(System.in);
    for(i = 0;i<matrix.length;i++) {
        for(j = 0; j<matrix[0].length;j++){
            matrix[i][j] = scan.nextFloat();
        }
    }
}

//MENGECEK APAKAH MATRIKS PERSEGI ATAU BUKAN
static boolean isSquare(float[][] matrix) {
    return (matrix.length == matrix[0].length);
}


//MENAMPILKAN MATRIKS KE LAYAR
static void displayMatrix(float[][] matrix) {
    int i,j;
    for (i = 0;i< matrix.length;i++) {
        for (j = 0;j<matrix[0].length;j++) {
            System.out.print(matrix[i][j] + " ");
        }
        System.out.println();
    } 
}


//PROSEDUR MENENTUKAN MATRIKS ESELON BARIS
static void rowEsMatrix (float[][] matrix) {
    int i,j,k;
    float x;
    for (k = 0;k < matrix.length ; k++) {

        //cek apakah ada baris yang depannya nol
        if (matrix[k][k] == 0 && k < matrix.length-1) {
            swapRow(matrix, k, k+1);
        }


        for(i = k+1; i < matrix.length;i++) {
            x = matrix[i][k]/matrix[k][k];
            for(j = 0; j < matrix[0].length;j++) {
                matrix[i][j] = matrix[i][j] - matrix[k][j]*x;
            }
            x = 0;
        }
    }
}

//PROSEDUR MENENTUKAN MATRIKS ESELON BARIS TEREDUKSI
static void reducedRowMatrix (float[][] matrix) {
    int i,j;
    float val = 1;
    rowEsMatrix(matrix);
    for(i = 0; i < matrix.length;i++) {
        for (j = 0;j < matrix[0].length;j++) {
            if (matrix[i][j] == 0){
                continue;
            } else {
                val = matrix[i][j];
                break;
            }
        }

        for (j = 0; j < matrix[0].length;j++) {
            matrix[i][j] = matrix[i][j]/val;
            if (matrix[i][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
}

//MENGHITUNG DETERMINAN MENGGUNAKAN MATRIKS ESELON BARIS
static float countDetRow (float[][] matrix) { 
    int i,j;
    float val = 1;
    float det = 1;
    rowEsMatrix(matrix); // prekondisi : isquare bernilai true
    for(i = 0; i < matrix.length;i++) {
        for (j = 0;j < matrix[0].length;j++) {
            if (matrix[i][j] == 0 && j == matrix[0].length-1){ //terdapat baris yang semua nilainya nol
                val = 0; 
                break;   
            }     
            else if (matrix[i][j] == 0){
                continue;
            } 
            else {
                val = matrix[i][j];
                break;
                }
        }
        if (i == matrix.length-1){
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
static float countDetCof (float[][] matrix) {
    int j;
    float det = 0;
    if (matrix.length == 1) {
        return matrix[0][0];
    }
    //basis rekursi
    else if (matrix.length == 2 ) { 
        return (matrix[0][0]*matrix[1][1] - matrix[1][0]*matrix[0][1]); 
    }
    //rekursi
    else {
        for (j = 0;j < matrix[0].length;j++){
            float[][] matMinor  = minorMat(matrix, 0, j);
            if(j%2 == 0) {
                det = det + matrix[0][j] * countDetCof(matMinor);
            } 
            else {
                det = det - matrix[0][j] * countDetCof(matMinor);
            }
        }
    }
    return det;
}


//MENENTUKAN MATRIKS MINOR DARI MATRIX[I][J]
static float[][] minorMat (float[][] matrix, int i, int j) {
    int ii,jj;
    int rowIdx = 0;
    int colIdx = 0;
    float[][] matMinor = new float[matrix.length-1][matrix[0].length-1];

    for (ii = 0; ii < matrix.length; ii++) {
        for (jj = 0; jj < matrix[0].length;jj++) {
            if (ii == i || jj == j) {
                continue;
            } else {
                matMinor[rowIdx][colIdx] = matrix[ii][jj];
                colIdx += 1; 
                if (colIdx == matMinor[0].length) {
                    colIdx = 0;
                }
            }
        }
        if (ii != i) {
            rowIdx += 1;
            if (rowIdx == matMinor.length) {
                rowIdx = 0;
            } 
        }

    }
    return matMinor;
}


//FUNGSI MENCARI MATRIKS MINOR
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


//MENCARI MATRIKS KOFAKTOR
static float[][] cofacMat(float[][] matrix) {
    int i,j;
    float[][] cofacMat = minorMatrix(matrix);
    for (i = 0; i < matrix.length; i++) {
        for (j = 0; j < matrix[0].length;j++) {
            if (i % 2 == 0) {
                if (j % 2 == 1) {
                    cofacMat[i][j] *= -1;
                }
            } else {
                if (j % 2 == 0) {
                    cofacMat[i][j] *= -1;
                }
            }
        }
    }
    return cofacMat;
}


//FUNGSI TRANSPOSE MATRIKS 
static float[][] transpose (float[][] matrix) {
    int i,j;
    float[][] transMat = new float[matrix[0].length][matrix.length];
    for(i = 0; i < transMat.length; i++) {
        for (j = 0; j < transMat[0].length;j++ ) {
            transMat[i][j] = matrix[j][i];
        }
    }
    return transMat;
}

//MENCARI ADJOINT MATRIKS
static float[][] adjoint(float[][] matrix) {
    float[][] adjMat = transpose(cofacMat(matrix));
    return adjMat;
}

}