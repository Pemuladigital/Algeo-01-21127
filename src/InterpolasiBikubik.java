import java.util.Scanner;
import java.io.File;
import java.lang.Math;
public class InterpolasiBikubik extends Matrix {
    public InterpolasiBikubik(int m, int n){
        super(m, n);
    }
    public InterpolasiBikubik(double [][]mat, int m, int n) {
        super(mat, m, n);
    }

    // Punya bikubik
    public Matrix inputfxy(){
        int i, j;
        Matrix matrixSolusi = new Matrix(this.baris*this.kolom, 1);
        for (j = 0; j < this.kolom; j++){
            for (i = 0; i < this.baris; i++){
                matrixSolusi.matrix[4*j+i][0] = this.matrix[i][j];
            }
        }
        return matrixSolusi;
    }
    public Matrix persamaanKoefisien (){
        int i, j, x, y;
        int awal = 0;
        Matrix exp = new Matrix(this.baris*this.baris, this.kolom*this.kolom);
        /* System.out.println("Matriks input: ");
        for (i = 0; i < this.baris; i++){
            for (j = 0; j < this.kolom; j++){
                System.out.printf("%.3f   ", this.matrix[i][j]);
            }
            System.out.println("");
        }*/
        x = 0;
        y = 0;
        for (i = 0; i < exp.baris; i++){
            int k = 0; // inisialisasi indeks matriks a(ij) sebagai i
            int l = 0; // inisialisasi indeks matriks a(ij) sebagai j
            for (j = 0; j < exp.kolom; j++){
                double xi = Math.pow((x-1), k % (this.baris));
                double yj = Math.pow((y-1), l % (this.kolom));
                exp.matrix[i][j] = xi*yj;
                if (k < this.baris){
                    k++;
                }
                if (k == this.baris){
                    k = awal;
                    l++;
                }
            }
            if (x < this.baris){
                x++;
            }
            if (x == this.kolom){
                x = awal;
                y++;
            }

        }
        // Menggabungkan nilai setiap f(x,y) dalam kolom terakhir, kolom ke 17 (indeks 16)
       /* System.out.println("Matriks y = Xa");
        for (i = 0; i < exp.baris; i++){
            for (j = 0; j < exp.kolom; j++){
                System.out.printf("%.1f ", exp.matrix[i][j]);
            }
            System.out.println("");
        }*/
        return exp;
    }
    // Punya bikubik
    public InterpolasiBikubik inverseX(){
        // int i, j;
        this.inverseOBE();
        /*System.out.println("Matrikx a = x^-1 y");
        for (i = 0; i < this.baris; i++){
            for (j = 0; j < this.kolom; j++){
                System.out.printf("%.2f ", this.matrix[i][j]);
            }
            System.out.println("");
        }*/
        return this;
    }

    public Matrix koefisien(){
        int i, j;
        Matrix persK = this.persamaanKoefisien();
        InterpolasiBikubik m1 = new InterpolasiBikubik(persK.matrix, persK.baris, persK.kolom);
        Matrix m2 = this.inputfxy();
        InterpolasiBikubik inv = m1.inverseX();
        Matrix koef =  perkalianMatriks(inv , m2);
        for (j = 0; j < 4; j++){
            for (i = 0; i < 4; i++){

            }
        }
        return koef;
    }
    public double fxy(int pilihan){
        // Input merupakan suatu matrix.koefisien();
        Scanner input = new Scanner(System.in);
        InterpolasiBikubik titik = new InterpolasiBikubik(1, 2);
        if (pilihan == 1){
            System.out.println("Nilai x yang diuji: ");
            titik.matrix[0][0] = input.nextDouble(); // x
            System.out.println("Nilai y yang diuji: ");
            titik.matrix[0][1] = input.nextDouble(); // y
        }
        else if (pilihan == 2){
            System.out.println("Masukan nama file: ");
            String NamaFile = input.next();
            this.ReadBicubicFile(NamaFile);
            titik.ReadBicubicArrayFile(NamaFile);
        }
        Matrix koef = this.koefisien();
        int i, j;
        double hasil = 0;
        for (j = 0; j < 4; j++){
            for (i = 0; i < 4; i++){
                double xi = Math.pow(titik.matrix[0][0], i);
                double yj = Math.pow(titik.matrix[0][1], j);
                hasil += koef.matrix[4*j+i][0]*xi*yj;
            }
        }
        System.out.printf("f(%f, %f) = %f\n", titik.matrix[0][0], titik.matrix[0][1], hasil);
        return hasil;
    }
    public void ReadBicubicFile (String NamaFile){
        int i,j;
        try {
            i = 0;
            j = 0;
            File newFile = new File("../test/"+NamaFile);
            Scanner scan2 = new Scanner(newFile);
            while (scan2.hasNextDouble()&& i<4){
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
            System.out.println(e);
        }
    }

    public void ReadBicubicArrayFile (String NamaFile){
        int i,j;
        try {
            i = 0;
            j = 0;
            File newFile = new File("../test/"+NamaFile);
            Scanner scan2 = new Scanner(newFile);
            scan2.nextLine();
            scan2.nextLine();
            scan2.nextLine();
            scan2.nextLine();
            while (scan2.hasNextDouble()&& i<this.baris+4){
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
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner input = new Scanner(System.in);
        InterpolasiBikubik mat = new InterpolasiBikubik(4, 4);
        System.out.println("1 untuk keyboard, 2 buat ngga ya");
        int pilihan = input.nextInt();
        int i, j;
        if (pilihan == 1){
            for (i = 0; i < 4; i++){
                for (j = 0;j < 4; j++){
                    mat.matrix[i][j] = input.nextDouble();
                }
            }
        }

        if (pilihan == 2){
        }
        mat.fxy(pilihan);
        
        /*Matrix inp = new Matrix(bar, kol);
        for (i = 0; i < bar; i++){
            for (j = 0; j < kol; j++){
                inp.matrix[i][j] = input.nextDouble();
            }
        }*/

    }
}
