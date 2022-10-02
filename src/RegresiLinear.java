import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class RegresiLinear extends Matrix {

    public RegresiLinear(int m, int n) {
        super(m, n);
    }

    public static int FileBarisRegresi (String NamaFile){
        int baris = -9999;
        try {
            Scanner scanfile = new Scanner (new File(NamaFile));
            baris = 0;
            while (scanfile.hasNextLine()){
                scanfile.nextLine();
                baris+=1;
            }
            scanfile.close ();
        }
        catch (Exception e){
            System.out.println("File tidak ada");
        }
        return baris-3;
    }

    public static int FileKolomRegresi (String NamaFile){
        int kolom = -9999;
        try {
            Scanner scan2 = new Scanner (new File(NamaFile));
            kolom = 1;
            scan2.nextLine();
            scan2.nextLine();
            String baris1 = scan2.nextLine();
            for (int i=0;i<baris1.length();i++){
                if (baris1.charAt(i) == ' '){
                    kolom+=1;
                }
            }
            scan2.close ();
        }
        catch (Exception e){
            System.out.println("error1");
        }
        return kolom;
    }

    public static int FileKolomPengubah (String NamaFile){
        int kolom = -9999;
        int k;
        try {
            Scanner scan2 = new Scanner (new File(NamaFile));
            kolom = 1;
            for (k=0;k<FileBarisRegresi(NamaFile)+2;k++){
                scan2.nextLine();
            }
            String baris1 = scan2.nextLine();
            for (int i=0;i<baris1.length();i++){
                if (baris1.charAt(i) == ' '){
                    kolom+=1;
                }
            }
            scan2.close ();
        }
        catch (Exception e){
            System.out.println("error1");
        }
        return kolom;
    }

    public void ReadRegresiFile (String NamaFile){
        int i,j;
        try {
            i = 0;
            j = 0;
            File newFile = new File(NamaFile);
            Scanner scan2 = new Scanner(newFile);
            scan2.nextLine();
            scan2.nextLine();
            while (scan2.hasNextDouble() && i<this.baris){
                this.matrix[i][j] = scan2.nextDouble();
                if (j<this.kolom-1){
                    j+=1;
                }
                else {
                    j=0;
                    i+=1;
                }
            }
            scan2.close();
        }
        catch (Exception e){
            System.out.println("error3");
        }
    }

    public void ReadPengubahFile (String NamaFile){
        int k,j;
        try {
            File newFile = new File(NamaFile);
            Scanner scan2 = new Scanner(newFile);
            for (k=0;k<FileBarisRegresi(NamaFile)+2;k++){
                scan2.nextLine();
            }
            j=0;
            while (scan2.hasNextDouble()){
                this.matrix[0][j] = scan2.nextDouble();
                if (j<this.kolom-1){
                    j+=1;
                }
                else {
                    j=0;
                }
            }
            scan2.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static double penjumlahanKolom(Matrix mat, int n) {
        int i;
        double sum;
        sum = 0;
        for (i=0; i<mat.baris; i++) {
            sum += mat.matrix[i][n-1];
        }
        return sum;
    }

    public static Matrix perkalianKolom(Matrix mat, int n1, int n2) {
        Matrix matbaru = new Matrix(mat.baris, 1);
        int i;
        for (i = 0; i<mat.baris; i++) {
            matbaru.matrix[i][0] = mat.matrix[i][n1-1] * mat.matrix[i][n2-1]; 
        }
        return matbaru;
    }

    public static Matrix kaliKolomBedaMatrix(Matrix mat1, Matrix mat2, int n1, int n2){
        Matrix matbaru = new Matrix(mat1.baris, 1);
        int i;
        for (i=0; i<mat1.baris; i++) {
            matbaru.matrix[i][0] = mat1.matrix[i][n1-1] * mat2.matrix[i][n2-1];
        }
        return matbaru;
    }

    public void printSPL() {
        int i,j;
        System.out.println("Berikut adalah persamaan SPL yang diperoleh dari data-data mengggunakan Multiple Linear Regression : ");
        for (i=0; i<this.baris; i++) {
            for (j=0; j<this.kolom; j++) {
                if (j == this.kolom-2) {
                    System.out.format("%.3fb%d = ", this.matrix[i][j], j);
                }
                else if (j != this.kolom-1) {
                    System.out.format("%.3fb%d + ", this.matrix[i][j], j);
                }
                else {
                    System.out.format("%.3f", this.matrix[i][j]);
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public String printPersamaan() {
        String solusi = "Berikut adalah persamaan yang didapat untuk menentukan nilai Y:\n ";
        solusi += "f(x) = ";
        System.out.println("Berikut adalah persamaan yang didapat untuk menentukan nilai Y: ");
        System.out.print("f(x) = ");
        for (int i = 0; i<this.baris; i++) {
            if (i == 0) {
                System.out.format("%.3f ", this.matrix[i][this.kolom-1]);
                solusi += Double.toString(this.matrix[i][this.kolom-1]) + " ";
            }
            else {
                if (this.matrix[i][this.kolom-1] < 0) {
                    System.out.format("- %.3fx%d ", (-1)*this.matrix[i][this.kolom-1], i);
                    solusi += "- " + Double.toString((-1)*this.matrix[i][this.kolom-1]) + "x" + Integer.toString(i) + " ";
                }
                else if (this.matrix[i][this.kolom-1] > 0) {
                    System.out.format("+ %.3fx%d ", this.matrix[i][this.kolom-1], i);
                    solusi += "+ " + Double.toString(this.matrix[i][this.kolom-1]) + "x" + Integer.toString(i) + " ";
                }
            }
        }
        System.out.println("");
        solusi += "\n";
        return solusi;
    }


    public String regresiBerganda(RegresiLinear reg, int pilihan) {
        int i,j, k,l, index;
        Matrix matY = new Matrix(this.baris, 1);
        for (i=0; i<this.baris; i++) {
            matY.matrix[i][0] = this.matrix[i][0];
        }
        RegresiLinear matSol = new RegresiLinear(this.kolom, this.kolom+1);
        RegresiLinear pertanyaan = new RegresiLinear(1, this.kolom-1);
        if (pilihan == 1) {
            System.out.println("Masukkan nilai-nilai x di pertanyaan: ");
            pertanyaan.readMatrix();
        }
        else if (pilihan == 2) {
            pertanyaan = reg;
        }
        Matrix sumKolom = new Matrix(1, this.kolom+1);
        index = 2;
        for (i=0; i<sumKolom.kolom; i++) {
            double sum = penjumlahanKolom(this, index);
            if (i == 0) {
                sumKolom.matrix[0][i] = matY.baris;
            }
            else if (i == sumKolom.kolom-1) {
                sum = penjumlahanKolom(matY, 1);
                sumKolom.matrix[0][i] = sum;
            }
            else {
                sumKolom.matrix[0][i] = sum;
                if (index < this.kolom) {
                    index++;
                }
            }
        }
        Matrix hasilkuadratKolom = new Matrix (this.baris, 1);
        Matrix kuadratKolom = new Matrix(1, this.kolom-1);

        k=0;
        for (i=1; i<this.kolom; i++) {
            hasilkuadratKolom = perkalianKolom(this, i+1, i+1);
            double sum = penjumlahanKolom(hasilkuadratKolom, 1);
            kuadratKolom.matrix[0][k] = sum;
            k++;

        }
        Matrix sumPerkalianKolom = new Matrix(this.kolom-1, this.kolom-1);
        Matrix hasilKaliKolom = new Matrix(this.baris,1);
        k = 0;
        for (i=1; i<sumPerkalianKolom.kolom+2; i++) {
            l = 0;
            for (j=1; j<sumPerkalianKolom.kolom+2; j++) {
                if (j == sumPerkalianKolom.kolom+1 && i < sumPerkalianKolom.kolom+1)  {
                    hasilKaliKolom = kaliKolomBedaMatrix(this,matY, i+1, 1);
                    double sum = penjumlahanKolom(hasilKaliKolom, 1);
                    sumPerkalianKolom.matrix[k][l] = sum;
                }
                else if (i != j && i < sumPerkalianKolom.kolom+1){
                    hasilKaliKolom = perkalianKolom(this, i+1, j+1);
                    double sum = penjumlahanKolom(hasilKaliKolom, 1);
                    sumPerkalianKolom.matrix[k][l] = sum;
                    l++;
                }
            }
            k++;
        }
        k = 0;
        for (i = 0; i<matSol.baris; i++) {
            l = 0;
            for (j=0; j<matSol.kolom; j++) {
                if (i == 0) {
                    matSol.matrix[i][j] = sumKolom.matrix[0][j];
                }
                else if (i == j & i != 0) {
                    matSol.matrix[i][j] = kuadratKolom.matrix[0][j-1];
                }
                else if (i != 0 && j == 0) {
                    matSol.matrix[i][j] = sumKolom.matrix[0][i];
                }
                else {
                    matSol.matrix[i][j] = sumPerkalianKolom.matrix[k][l];
                    l++;
                }
            }
            if (i >=1 && i < sumPerkalianKolom.baris){
                k++;
            }
        }
        System.out.println("");
        matSol.printSPL();
        matSol.gaussJordan();
        String solution = matSol.printPersamaan();
        solution += "\n";
        System.out.println("");
        RegresiLinear nilaibeta = new RegresiLinear(1, matSol.baris);
        for (i=0; i<matSol.baris; i++) {
            nilaibeta.matrix[0][i] = matSol.matrix[i][matSol.kolom-1];
        }

        float hasilY = 0;
        for (i=0; i<nilaibeta.kolom; i++) {
            if (i == 0) {
                hasilY += nilaibeta.matrix[0][i];
            }
            else {
                hasilY += nilaibeta.matrix[0][i] * pertanyaan.matrix[0][i-1];
            }
        }

        System.out.format("Dari Regresi Linear Berganda, nilai Y yang didapat adalah %.3f", hasilY);
        solution += "Dari Regresi Linear Berganda, nilai Y yang didapat adalah " + Double.toString(hasilY);
        System.out.println();
        return solution;
    }
}
