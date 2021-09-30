package src;

import javax.swing.*;

public class parametrikSPL extends matriks {
    //Variable
    private double[][] hasilParametrik;
    /*
            Bentuk array hasilParametrik adalah seperti berikut
            x0  =   0*x0 + b*x1 + c*x2 + ... + x
            x1  =   d*x0 + 0*x1 + f*x2 + ... + y
            x2  =   g*x0 + h*x1 + 0*x2 + ... + z

            Untuk variable bebas, nilai matrix pada baris tersebut adalah
            x5 = 0*x0 + 0*x1 + 0*x2 + 0*x3 + 0*x4 + 0*x5 + 0*x6 + ... + 0   (Contoh) && status = 0

            Untuk variable terikat, nilai matrix pada baris tersebut adalah
            x5 = 1*x0 + 2*x1 + 0*x2 + 0*x3 + 0*x4 + 0*x5 + 3*x6 + ... + 30  (Contoh) && status = 1

            Untuk variable tentu, nilai matrix pada baris tersebut adalah
            x5 = 0*x0 + 0*x1 + 0*x2 + 0*x3 + 0*x4 + 0*x5 + 0*x6 + ... + 20  (Contoh) && status = 2

            Yang membedakan variable bebas dengan tentu adalah nilai statusnya
    */

    private int[] status;
    private int banyakVariable;

    //Konstruktor
    public  void MatrixParametrik(int nb, int nk) {
        super(nb, nk);
        this.banyakVariable = this.kolom - 1;
        this.hasilParametrik = new double[banyakVariable][banyakVariable + 1];
        this.status = new int[banyakVariable];
    }

    public void  MatrixParametrik(JTable tabel, int nb, int nk) {
        super(nb, nk);
        this.banyakVariable = this.kolom - 1;
        this.hasilParametrik = new double[banyakVariable][banyakVariable + 1];
        this.status = new int[banyakVariable];
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                this.data[i][j] = Double.valueOf(tabel.getModel().getValueAt(i, j).toString());
            }
        }

    }

    public void genStatus() {
        for (int i = this.baris - 1; i >= 0; i--) {
            if (!this.isRowZero(i)) {
                /*
                    Asumsi matrix sudah di gauss / gauss-jordan
                    Cek apakah lead coefnya ada di paling belakang, jika iya maka ada baris berbentuk
                        0 0 0 0 0...0 0 0 | a   dengan a != 0, maka itu tidak ada solusi
                    Jika lead coef tidak di paling belakang, cek lagi status angka di belakang lead coef
                        Jika 0 semua maka variable tentu
                        Jika tidak, maka variable terikat / parametrik

                    Pada awalnya semua variable diasumsikan bebas, makanya nilai awal array status adalah 0 semua

                    0 = bebas
                    1 = terikat
                    2 = tentu

                 */
                int idxLead = this.getFirstCoef(i);
                //Cek letak lead coef
                if (idxLead == this.kolom - 1) {
                    //Tidak ada solusi, lempar NoSolution Exception
                    throw new noSolution("Tidak ada solusi");
                } else {
                    //Aman
                    boolean tentu = true;
                    //Cek semua variable di belakang lead coef
                    for (int j = idxLead + 1; j < banyakVariable; j++) {
                        //Syarat tentu adalah, semua variable dikanannya koefnya 0 atau tentu semua
                        //Maka dia tidak tentu jika ada salah satu yang tidak 0 dan tidak tentu
                        if (this.data[i][j] != 0 && this.status[j] != 2) {
                            tentu = false;
                            break;
                        }
                    }
                    if (tentu) {
                        this.status[idxLead] = 2;
                    } else {
                        this.status[idxLead] = 1;
                    }
                }
            }
        }
    }

    //Metode Gauss
    public void solveParametrikGauss() {
        //I.S : src.Matrix awal sudah di gauss dan status sudah di generate
        /*
            Asumsi matrix sudah dilakukan operasi gauss dan sekarang sedang dalam bentuk row echelon form
            Iter dari bawah, lakukan algoritma berikut

            Algoritma :
                Untuk setiap baris, cari leading koef nya, lalu untuk setiap kolom disamping lead koef, sebut saja kolom k
                lakukan :
                    jika status[k] == 0 maka ->
                        hasil[leadKoef][k] += -data[i][k]
                    jika status[k] == 1 maka ->
                        hasil[leadKoef] += -data[i][k]*hasil[k]
                    jika status[k] == 2 maka ->
                        hasil[leadKoef][banyakVariable] += -hasil[k][banyakVariable]
         */
        for (int i = this.baris - 1; i >= 0; i--) {
            if (!isRowZero(i)) {
                int idxLead = this.getFirstCoef(i);
                for (int k = idxLead + 1; k < this.banyakVariable; k++) {
                    if (this.status[k] == 0) { //bebas
                        this.hasilParametrik[idxLead][k] += (-1 * this.data[i][k])+0.0;
                    } else if (this.status[k] == 1) { //terikat
                        for (int j = 0; j < this.banyakVariable; j++) {
                            this.hasilParametrik[idxLead][j] += (-1 * this.data[i][k] * this.hasilParametrik[k][j])+0.0;
                        }
                        this.hasilParametrik[idxLead][banyakVariable] += (-1 * this.data[i][k] * this.hasilParametrik[k][banyakVariable])+0.0;
                    } else if (this.status[k] == 2) { //tentu
                        this.hasilParametrik[idxLead][banyakVariable] += (-1 * this.data[i][k] * this.hasilParametrik[k][banyakVariable])+0.0;
                    }
                }
                this.hasilParametrik[idxLead][banyakVariable] += this.data[i][this.kolom - 1]+0.0;
            }
        }
    }

    //Metode Gauss-Jordan
    public void solveParametrikGaussJordan() {
        for (int i = this.baris - 1; i >= 0; i--) {
            if (!isRowZero(i)) {
                int idxLead = this.getFirstCoef(i);
                if (status[idxLead] == 1) {
                    for (int k = idxLead + 1; k < banyakVariable; k++) {
                        this.hasilParametrik[idxLead][k] += (-1 * this.data[i][k])+0.0;
                    }
                    this.hasilParametrik[idxLead][banyakVariable] += this.data[i][this.kolom - 1]+0.0;
                } else if (this.status[idxLead] == 2) {
                    this.hasilParametrik[idxLead][banyakVariable] = this.data[i][this.kolom - 1]+0.0;
                }
            }
        }

    }

// digunakan setelah parametrikGauss()/parametrikGJ()
    public String printHasilParametrik() {
        String hasil = "";
        for (int i = 0; i < this.banyakVariable; i++) {
            // tipe variabel
            
            if (status[i] == 0) {
                // Bebas
                hasil += String.format("x%d = bebas", i);
                hasil += "\n";
            } else if (status[i] == 1) {
                // Terikat
                hasil += String.format("x%d = ", i);
                boolean pertama = true;
                //Print ax^n + bx^(n-1) + cx^(n-2) ... + dx
                for (int j = 0; j < this.banyakVariable; j++) {
                    double nilai = this.hasilParametrik[i][j];
                    if (nilai != 0) {
                        if (nilai > 0) {
                            //positif
                            if (pertama) {
                                hasil += String.format("(%.2f * x%d)", nilai, j);
                                pertama = false;
                            } else {
                                hasil += String.format(" + (%.2f * x%d)", nilai, j);
                            }
                        } else {
                            //negatif
                            if (pertama) {
                                hasil += String.format("(-%.2f * x%d)", -1 * nilai, j);
                                pertama = false;
                            } else {
                                hasil += String.format(" - (-%.2f * x%d)", -1 * nilai, j);
                            }
                        }
                    }
                }
                //Print koefisien fungsi
                double koefHasil = this.hasilParametrik[i][banyakVariable];
                if (koefHasil == 0) {
                    if (pertama) {
                        hasil += "0.00";
                        pertama = false;
                    } else {
                        hasil += " + 0.00";
                    }
                }else if(koefHasil > 0){
                    //positif
                    if (pertama) {
                        hasil += String.format("%.2f", koefHasil);
                        pertama = false;
                    } else {
                        hasil += String.format(" + %.2f", koefHasil);
                    }
                } else {
                    //negatif
                    if (pertama) {
                        hasil += String.format("-%.2f", -1 * koefHasil);
                        pertama = false;
                    } else {
                        hasil += String.format(" - %.2f", -1 * koefHasil);
                    }
                }
                hasil += "\n";
            } else if (status[i] == 2) {
                //tentu
                hasil += String.format("x%d = %.2f", i, this.hasilParametrik[i][banyakVariable]);
                hasil += "\n";
            }
        }
        return hasil;
    }

}