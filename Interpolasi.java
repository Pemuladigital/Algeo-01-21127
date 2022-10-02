import java.util.Scanner;
import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Interpolasi extends Matrix {
    public Interpolasi(int m, int n){
        super(m,n);
    }
    public Interpolasi(double[][] mat, int m, int n){
        super(mat, m, n);
    }

    public double interpolasi(Interpolasi arraytitik, int pilihan){
        int i,j;
        Scanner scan = new Scanner(System.in);

        Matrix koef = new Matrix(this.baris, this.baris+1);
        for (i=0;i<this.baris;i++){
            for (j=0;j<this.baris+1;j++){
                if (j==0){
                    koef.matrix[i][j]=1;
                }
                if (j!=0 && j!= (this.baris+1)-1){
                    koef.matrix[i][j]=Math.pow(this.matrix[i][0],j);
                }
                if (j==(this.baris+1)-1){
                    koef.matrix[i][j]=this.matrix[i][1];
                }
            }
        }

        koef.gauss();
        koef.pembagiGauss();
        Matrix solusi = koef.solusiGauss();

        String line = "";
        System.out.print("P2(x) = ");
        line +="f(x) = ";
        for (i=0;i<this.baris;i++){
            System.out.print(solusi.matrix[i][0]);
            line += Double.toString(solusi.matrix[i][0]);
            if (i!=0){
                System.out.print("x^");
                line +="x^";
                System.out.print(i);
                line +=String.valueOf(i);
            }
            if (i!=this.baris-1){
                System.out.print(" + ");
                line +=" + ";
            }
        }
        System.out.print("\n");
        line +="\n";
        line +="\n";
        double x1 = 0;
        if (pilihan == 1){
            System.out.println("Masukkan nilai x : ");
            x1 = scan.nextDouble(); 
        }
        if (pilihan == 2){
            x1 = arraytitik.matrix[0][0];
        }
        double hasil;
        hasil = 0;
        for (i=0;i<this.baris;i++){
            hasil += solusi.matrix[i][0]*Math.pow(x1,i);
        }
        System.out.printf("Hasil interpolasi adalah : %.3f\n", hasil);
        line +="Hasil interpolasi adalah : ";
        line += Double.toString(hasil);

        return hasil;
    }

    public String interpolasiline(Interpolasi arraytitik, int pilihan){
        int i,j;
        Scanner scan = new Scanner(System.in);

        Matrix koef = new Matrix(this.baris, this.baris+1);
        for (i=0;i<this.baris;i++){
            for (j=0;j<this.baris+1;j++){
                if (j==0){
                    koef.matrix[i][j]=1;
                }
                if (j!=0 && j!= (this.baris+1)-1){
                    koef.matrix[i][j]=Math.pow(this.matrix[i][0],j);
                }
                if (j==(this.baris+1)-1){
                    koef.matrix[i][j]=this.matrix[i][1];
                }
            }
        }

        koef.gauss();
        koef.pembagiGauss();
        Matrix solusi = koef.solusiGauss();

        String line = "";
        System.out.print("P2(x) = ");
        line +="f(x) = ";
        for (i=0;i<this.baris;i++){
            System.out.print(solusi.matrix[i][0]);
            line += Double.toString(solusi.matrix[i][0]);
            if (i!=0){
                System.out.print("x^");
                line +="x^";
                System.out.print(i);
                line +=String.valueOf(i);
            }
            if (i!=this.baris-1){
                System.out.print(" + ");
                line +=" + ";
            }
        }
        System.out.print("\n");
        line +="\n";
        line +="\n";
        double x1 = 0;
        if (pilihan == 1){
            System.out.println("Masukkan nilai x : ");
            x1 = scan.nextDouble(); 
        }
        if (pilihan == 2){
            x1 = arraytitik.matrix[0][0];
        }
        double hasil;
        hasil = 0;
        for (i=0;i<this.baris;i++){
            hasil += solusi.matrix[i][0]*Math.pow(x1,i);
        }
        System.out.printf("Hasil interpolasi adalah : %.3f\n", hasil);
        line +="Hasil interpolasi adalah : ";
        line += Double.toString(hasil);

        return line;
    }

    public static int FileBarisInterpolasi (String NamaFile){
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
        return baris-2;
    }

    public static int FileKolomInterpolasi (String NamaFile){
        int kolom = -9999;
        try {
            Scanner scan2 = new Scanner (new File(NamaFile));
            kolom = 1;
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

    public void ReadInterpolasiTitikFile (String NamaFile){
        int k;
        try {
            File newFile = new File(NamaFile);
            Scanner scan2 = new Scanner(newFile);
            for (k=0;k<FileBarisInterpolasi(NamaFile)+1;k++){
                scan2.nextLine();
            }
            this.matrix[0][0] = scan2.nextDouble();
            scan2.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void ReadInterpolasiFile (String NamaFile){
        int i,j;
        try {
            i = 0;
            j = 0;
            File newFile = new File(NamaFile);
            Scanner scan2 = new Scanner(newFile);
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
}
