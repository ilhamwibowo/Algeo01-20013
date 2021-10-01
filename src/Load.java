package src;

import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Load {
    private String fileName;
    private Boolean simpan;
    private Boolean load;
    private int totalbaris;

    public Boolean simpanStatus(){
        return this.simpan;
    }
    public Boolean loadStatus(){
        return this.load;
    }
    public int getTotalBaris(){
        return this.totalbaris;
    }
    
    public matriksParametrik bacaMatrixFile(String namaFile) {
        File fileExternal;
        matriksParametrik M= new matriksParametrik(1,1);;
        int baris = -1, kolom = -1;
        ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();
        try {
            fileExternal = new File(namaFile);
            Scanner scanBaris = new Scanner(fileExternal);
            while (scanBaris.hasNextLine()) {
                baris += 1;
                temp.add(new ArrayList<Double>());
                String s = scanBaris.nextLine();
                Scanner scanDouble = new Scanner(s);
                while (scanDouble.hasNextDouble()) {
                    Double f = scanDouble.nextDouble();
                    temp.get(baris).add(f);
                }
            }
            kolom = temp.get(0).size();
            baris += 1;
            if (baris != -1 && kolom != -1) {
                M = new matriksParametrik(baris,kolom);
                for (int i = 0; i < baris; i++) {
                    for (int j = 0; j < kolom; j++) {
                        M.data[i][j] = temp.get(i).get(j);
                    }
                }
            } else {
                System.out.println("Empty file.");
            }
            
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }finally{
            return M;
        }
        
    }

    public double[][] readMatrix(String namaFile) {
        File fileExternal;
        double[][] tempData =  new double[0][0];
        int baris = -1, kolom = -1;
        ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();
        try {
            fileExternal=new File(namaFile);
            Scanner scanBaris = new Scanner(fileExternal);
            while (scanBaris.hasNextLine()) {
                baris += 1;
                temp.add(new ArrayList<Double>());
                String s = scanBaris.nextLine();
                Scanner scanDouble = new Scanner(s);
                while (scanDouble.hasNextDouble()) {
                    Double f = scanDouble.nextDouble();
                    temp.get(baris).add(f);
                }
            }
            kolom = temp.get(0).size();
            baris += 1;
            this.totalbaris=baris; 
            if (baris != -1 && kolom != -1) {
                tempData = new double[temp.size()][temp.get(0).size()];
                for (int i = 0; i < baris; i++) {
                    for (int j = 0; j < kolom; j++) {
                        tempData[i][j] = temp.get(i).get(j);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        finally{
           return tempData;
        }
        
    }
    public void cekSimpan(){
        Scanner input=new Scanner(System.in);
        boolean stop=false;
        System.out.println("Save?\n<ya/tidak> : ");
        do {
            String s=input.nextLine();
            if(s.equals("ya")||s.equals("tidak")){
                if(s.equals("ya")) {
                    simpan = true;
                } else{
                    simpan=false;
                }
                stop=true;
            }else{
                System.out.println("Ulangi, input salah.");
            }
        }while(!stop);
    }

    public void cekLoad(){
        Scanner input=new Scanner(System.in);
        boolean stop=false;
        System.out.println("Load file? \n<ya/tidak> : ");
        do {
            String s=input.nextLine();
            if(s.equals("ya")||s.equals("tidak")){
                if(s.equals("ya")) {
                    load = true;
                } else{
                    load=false;
                }
                stop=true;
            }else{
                System.out.println("Ulangi, input salah.");
            }
        }while(!stop);
    }

    

    public void namaExternal(){
        Scanner input=new Scanner(System.in);
        System.out.print("Masukkan nama file : ");
        fileName="../test/"+input.nextLine()+".txt";
    }

    public void saveExternal(String judul,matriks M){
        if(simpan) {
            try {
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                pw.println(judul);
                int i, j;
                for (i = 0; i < M.getLastIdxBrs(M); i++) {
                    for (j = 0; j < M.getLastIdxKol(M) - 1; j++) {
                        pw.printf("%.7f\t", M.data[i][j]);
                    }
                    pw.printf("%.7f", M.data[i][j]);
                    pw.println();
                }
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveExternal(String s){
        if(simpan) {
            try {
                File file = new File(fileName);
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                if (!file.exists()) {
                    file.createNewFile();
                }
                pw.println(s);
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

 
}