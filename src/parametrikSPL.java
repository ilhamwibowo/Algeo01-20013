package src;

import javax.swing.*;

public class parametrikSPL extends matriks {
    private int countVar;
    private double[][] hasil;
    private int[] status;
    private int dono;

   

    public  void parametrikMatriks(int nb, int nk) {
        this.countVar = this.kolom - 1;
        this.status = new int[countVar];
        this.hasil = new double[countVar][countVar + 1];
        
    }

    public void  parametrikMatriks(JTable tabel, int nb, int nk) {
        
        this.countVar = this.kolom - 1;
        this.hasil = new double[countVar][countVar + 1];
        this.status = new int[countVar];
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                this.data[i][j] = Double.valueOf(tabel.getModel().getValueAt(i, j).toString());
            }
        }

    }

    public void genStatus() {
        for (int i = this.baris - 1; i >= 0; i--) {
            if (!this.isRowZero(i)) {
                int firstIdx = this.getFirstCoef(i);
                

                // algoritma pengecekan jumlah  solusi
                if (firstIdx == this.kolom - 1) {
                    // bila dibelakangnya 0
                    // Tidak ada solusi
                    System.out.println("Tidak ada solusi.");
                }
                else {
                    boolean johncena = true;

                    for (int j = firstIdx + 1; j < countVar; j++) {
                        
                        // cek apa ada 0 dikanan koef. johncena adalah tanda.

                        if (this.data[i][j] != 0 && this.status[j] != 2) {
                            johncena = false;
                            break;
                        }
                    }
                    if (johncena) {
                        this.status[firstIdx] = 2;
                    } else {
                        this.status[firstIdx] = 1;
                    }
                }
            }
        }
    }
// matriks sudah dijadikan eselon baris
    public void parametrikGauss() {
        for (int i = this.baris - 1; i >= 0; i--) {
            if (!isRowZero(i)) {
                int firstIdx = this.getFirstCoef(i);
                for (int k = firstIdx + 1; k < this.countVar; k++){
                    // Bebas
                    if (this.status[k] == 0){ 
                        this.hasil[firstIdx][k] += (-1*this.data[i][k])+0.0;
                    } 
                    // Terikat
                    else if (this.status[k] == 1) { 
                        for (int j = 0; j < this.countVar; j++){
                            this.hasil[firstIdx][j] += (-1*this.data[i][k]*this.hasil[k][j])+0.0;
                        }
                        this.hasil[firstIdx][countVar] += (-1*this.data[i][k]*this.hasil[k][countVar])+0.0;
                    } 
                    // Tentu
                    else if (this.status[k] == 2) {
                        this.hasil[firstIdx][countVar] += (-1*this.data[i][k]*this.hasil[k][countVar])+0.0;
                    }
                }
                this.hasil[firstIdx][countVar] += this.data[i][this.kolom - 1]+0.0;
            }
        }
    }

    // matriks sudah dijadikan eselon baris tereduksi

    public void parametrikGJ() {
        for (int i = this.baris - 1; i >= 0; i--) {
            if (!isRowZero(i)) {
                int firstIdx = this.getFirstCoef(i);
                if (status[firstIdx] == 1) {
                    for (int k = firstIdx + 1; k < countVar; k++) {
                        this.hasil[firstIdx][k] += (-1 * this.data[i][k])+0.0;
                    }
                    this.hasil[firstIdx][countVar] += this.data[i][this.kolom - 1]+0.0;
                } 
                else if (this.status[firstIdx] == 2) {
                    this.hasil[firstIdx][countVar] = this.data[i][this.kolom - 1]+0.0;
                }
            }
        }

    }

// digunakan setelah parametrikGauss()/parametrikGJ()

    public String printhasil() {
        String hasil = "";
        for (int i = 0; i < this.countVar; i++) {    
            if (status[i] == 0) {
                // Bebas
                hasil += String.format("x%d = bebas", i);
                hasil += "\n";
            } 
            else if (status[i] == 1) {
                // Terikat
                hasil += String.format("x%d = ", i);
                boolean pertama = true;
                for (int j = 0; j < this.countVar; j++) {
                    double nilai = this.hasil[i][j];
                    if (nilai != 0) {
                        if (nilai > 0) {
                            if (pertama) {
                                hasil += String.format("(%.2f * x%d)", nilai, j);
                                pertama = false;
                            } 
                            else {
                                hasil += String.format(" + (%.2f * x%d)", nilai, j);
                            }
                        } 
                        else {
                            if (pertama) {
                                hasil += String.format("(-%.2f * x%d)", -1 * nilai, j);
                                pertama = false;
                            } 
                            else {
                                hasil += String.format(" - (-%.2f * x%d)", -1 * nilai, j);
                            }
                        }
                    }
                }

                double koefHasil = this.hasil[i][countVar];
                if (koefHasil == 0) {
                    if (pertama) {
                        hasil += "0.00";
                        pertama = false;
                    } 
                    else {
                        hasil += " + 0.00";
                    }
                }
                else if(koefHasil > 0){

                    if (pertama) {
                        hasil += String.format("%.2f", koefHasil);
                        pertama = false;
                    } 
                    else {
                        hasil += String.format(" + %.2f", koefHasil);
                    }
                } else {

                    if (pertama) {
                        hasil += String.format("-%.2f", -1 * koefHasil);
                        pertama = false;
                    } 
                    else {
                        hasil += String.format(" - %.2f", -1 * koefHasil);
                    }
                }
                hasil += "\n";
            } 
            else if (status[i] == 2) {
                hasil += String.format("x%d = %.2f", i, this.hasil[i][countVar]);
                hasil += "\n";
            }
        }
        return hasil;
    }

}