import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
// import javax.swing.UIDefaults.ProxyLazyValue;

public class Matrix {
    public double [][] matrix;
    public double [] arrSol;
    protected int baris, kolom, count;
    
    public Matrix(int m, int n) {
        this.baris = m;
        this.kolom = n;
        matrix = new double[m][n];
        arrSol = new double[m];
    }

    public Matrix (double [][]mat, int m, int n) {
        this.matrix = mat;
        this.baris = m;
        this.kolom = n;
        this.count = 0;
    }

    public void readMatrix() {
        int i,j;
        Scanner scan = new Scanner(System.in);
        for (i=0; i<this.baris; i++) {
            for (j=0; j<this.kolom; j++) {
                this.matrix[i][j] = scan.nextDouble();
            }
        }
    }

    public void printMatrix() {
        int i,j;
        for (i=0; i<this.baris; i++) {
            for (j=0; j<this.kolom; j++) {
                if (j == this.kolom-1) {
                    System.out.printf("%.3f   ", this.matrix[i][j]);
                }
                else {
                    System.out.printf("%.3f ", this.matrix[i][j]);
                }
            }
            System.out.println("");
        }
    }
    
    public void minusBaris(double isi, int m, int n) {
        // Prekondisi: Asumsikan baris yang diinput selalu valid yaitu m,n > 0 dan m,n <= this.baris
        // Digunakan untuk membantu pencarian matriks gauss
        int i;
        for (i=0; i<this.kolom; i++) {
            this.matrix[m][i] -= (isi * matrix[n][i]);
        }
    }

    public void bagiMatriks(int m, double k) {
        // Membagi suatu baris pada matriks dengan suatu konstanta
        int i;
        for (i=0; i<this.kolom; i++) {
            this.matrix[m][i] /= k;
        }
    }

    public boolean baris0Semua(int m) {
        // Memberikan return berupa true apabila 1 baris berisi 0 semua
        int i;
        boolean allzero = true;
        for (i=0; i<this.kolom; i++) {
            if (this.matrix[m][i] != 0) {
                allzero = false;
            }
        }
        return allzero;
    }

    public boolean kolom0Semua(int m) {
        // Memberikan return berupa true apabila 1 baris berisi 0 semua
        int i;
        boolean allzero = true;
        for (i=0; i<this.baris; i++) {
            if (this.matrix[i][m] != 0) {
                allzero = false;
            }
        }
        return allzero;
    }

    public int isiBukan0(int m) {
        // Mengambil indeks pertama yang bukan 0 di suatu baris
        // Fungsi ini digunakan untuk membantu perhitungan matriks gauss
        int i=0;
        while (this.matrix[m][i] == 0 && i < this.kolom) {
            i++;
        }
        return i;
    }

    public void tukarBaris(int m1, int m2) {
        // Prekondisi : nilai m1 dan m2 valid dan ada di dalam matriks
        double []temp = this.matrix[m1];
        this.matrix[m1] = this.matrix[m2];
        this.matrix[m2] = temp;
    }


    public void susunMatrix() {
        // Menyusun barisan matriks agar baris dengan 0 terbanyak berada di paling bawah dan 0 paling sedikit berada di paling atas
        int i,j, baristeratas;
        baristeratas = 0;
        if (this.baris > 1) {
            for (i=0; i<this.baris; i++) {
                baristeratas = i;
                for (j=i+1; j<this.baris; j++) {
                    int idxbukan0 = this.isiBukan0(j);
                    if (idxbukan0 < this.isiBukan0(baristeratas)) { // apabila indeks yang berisi nilai bukan 0 pada baris j lebih kecil daripada baris i
                        baristeratas = j;
                    }
                }
                if (baristeratas != i) {
                    this.count += 1;
                }
                this.tukarBaris(i, baristeratas); // Menukarkan baris tiap looping baris apabila indeks yang berisi nilai lebih kecil daripada baris selanjutnya
             }
        }
    }


    public void gauss() {
        int i,j;
        susunMatrix();
        for (i=0; i<this.baris-1; i++) {
            if (!this.baris0Semua(i)) {
                int idxbukan0 = this.isiBukan0(i);
                double nilaibukan0 = this.matrix[i][idxbukan0];
                for (j=i+1; j<this.baris; j++) {
                    if (!this.baris0Semua(j)) {
                        double pengurang = this.matrix[j][idxbukan0]/nilaibukan0;
                        this.minusBaris(pengurang, j, i);
                    }
                    this.matrix[j][idxbukan0] = 0;
                }
            }
        }

    }

    public void pembagiGauss() {
        int i,j;
        for (i=0; i<this.baris; i++) {
            if (!baris0Semua(i)) {
                int idxpembagi = this.isiBukan0(i);
                double pembagi = this.matrix[i][idxpembagi];
                if (pembagi != 0) {
                    this.bagiMatriks(i, pembagi);
                }
            }
        }
    }

    public void gaussJordan() {
        this.gauss();
        this.pembagiGauss();
        int i,j;
        for (i=this.baris-1; i>=1; i--) {
            if (!baris0Semua(i)) {
                int idxbukan0terbawah = this.isiBukan0(i);
                double nilaibukan0terbawah = this.matrix[i][idxbukan0terbawah];
                for (j=i-1; j>=0; j--) {
                    if (!baris0Semua(j)) {
                        double pengurang = this.matrix[j][idxbukan0terbawah]/nilaibukan0terbawah;
                        this.minusBaris(pengurang, j, i);
                        // this.printMatrix();
                        // System.out.println("");

                    }
                }
            }
        }
    }

    /*public double determinanCramer(double[][] mat) {
        // Prekondisi: belum dilakukan metode Gauss pada matriks dan matriks berukuran sama
        // Determinan untuk menghitung matriks selain matriks asli
        int i;
        double hasil = 1;
        this.gauss();
        for(i = 0; i<this.kolom; i++) {
            hasil *= mat[i][i];
        }
        hasil *= Math.pow(-1, this.count);
        this.count = 0;
        return hasil;
    }*/

    public double determinan() {
        // Prekondisi: belum dilakukan metode Gauss pada matriks dan matriks berukuran sama
        int i,j;
        double hasil = 1;
        this.gauss();
        for(i = 0; i<this.kolom; i++) {
            hasil *= this.matrix[i][i];
        }
        hasil *= Math.pow(-1, this.count);
        this.count = 0;
        return hasil;
    }

    public Matrix copyMatrix(Matrix  mbaru, Matrix matriks) {
        int i,j;
        for (i=0; i<this.baris; i++) {
            for (j=0; j<this.kolom; j++) {
                mbaru.matrix[i][j] = matriks.matrix[i][j]; 
            }
        }
        return mbaru;
        
    }
    public boolean isSquare (int rowEff, int colEff) {
        return (rowEff==colEff);
    }
    public void inverseOBE() {
        int i,j,count,invers1;
        Matrix arr = new Matrix(this.baris, this.kolom*2);
        invers1=0;
        
        if (isSquare(this.baris,this.kolom)){
            
            for (i=0;i<this.baris;i++){
                for (j=0;j<this.kolom;j++){
                    arr.matrix[i][j]=this.matrix[i][j];
                }
            }

            j=this.kolom;

            for (i=0;i<this.baris;i++){
                arr.matrix[i][j] = 1;
                j++;
            }

            for (i=0;i<this.baris;i++){
                for(j=this.kolom;j<(this.kolom*2);j++){
                    if (arr.matrix[i][j]!=1){
                        arr.matrix[i][j]=0;
                    }
                }
            }

            arr.gaussJordan();

            for (i=0;i<this.baris;i++){
                count = 0;
                for(j=0;j<this.kolom;j++){
                    if (arr.matrix[i][j]==0){
                        count+=1;
                    }
                }
                if (count==this.kolom){
                    System.out.println("Matriks tidak memiliki Balikan");
                    invers1+=1;
                    break;
                }
            }

            if (invers1==0){

                for (i=0;i<this.baris;i++){
                    for (j=0;j<this.kolom;j++){
                        this.matrix[i][j] = arr.matrix[i][j+this.kolom];
                    }
                }
            } 
        }
        else {
            System.out.println("Matriks tidak memiliki Balikan");
        }
    }

    public String kaidahCramer() {
    
        Matrix matpersamaan = new Matrix(this.baris, this.kolom-1);
        int i,j;
        double hasil = 0;
        Matrix matrixdummy = new Matrix(matpersamaan.baris, matpersamaan.kolom);
        Matrix matrixdummy2 = new Matrix(matpersamaan.baris, matpersamaan.kolom);
        double [] arrDet = new double[this.baris];
        double []arrSol = new double[this.baris];
        String solusi = "";
        for (i=0; i<this.baris; i++) {
            for (j=0; j<this.kolom-1; j++){
                matrixdummy.matrix[i][j] = this.matrix[i][j]; 
            }
        }

        for (i=0; i<this.baris; i++) {
            arrSol[i] = this.matrix[i][this.kolom-1];
        }
        double detAsli = matrixdummy.determinan();

        for (i=0; i<this.baris; i++) {
            for (j=0; j<this.kolom-1; j++){
                matrixdummy.matrix[i][j] = this.matrix[i][j]; 
            }
        }

        for (i=0; i<matrixdummy2.baris; i++) {
            matrixdummy2 = matrixdummy2.copyMatrix(matrixdummy2, matrixdummy);
            
            for (j=0; j<matrixdummy2.kolom; j++) {
                matrixdummy2.matrix[j][i] = arrSol[j];
            }
            hasil = matrixdummy2.determinan();
            arrDet[i] = hasil;
        }

        if (detAsli == 0) {
            solusi += "Metode ini tidak bisa digunakan untuk persamaan matrix tersebut.";
            System.out.println(solusi);
        }
        else {
            solusi += "Berikut solusi yang didapat dari kaidah cramer :\n ";
            for (i=0; i<this.baris; i++) {
                if (i != this.baris-1) {
                    solusi += "x" + Integer.toString(i+1) + " = " + Double.toString(arrDet[i]/detAsli) + ", ";
                }
                else {
                    solusi += "x" + Integer.toString(i+1) + " = " + Double.toString(arrDet[i]/detAsli);
                }
            }
            System.out.println(solusi);
        }
        return solusi;
    }

    
    public Matrix inverseKofaktor (){
        // mulai dari kondisi awal, kofaktor, transpose, adjoin, baru 1/det dikali adjoin.
        int i, j;
        // ALGORITMA
        double det = this.determinanKofaktor();
        Matrix adj = this.adjoin();
        if(det != 0){
            for (i = 0; i < this.baris; i++){
                for (j = 0; j < this.kolom; j++){
                    this.matrix[i][j] = adj.matrix[i][j] / det;
                }
            }
            for (i = 0; i < this.baris; i++){
                for (j = 0; j < this.kolom; j++){
                    System.out.printf("%.3f   ", this.matrix[i][j]);
                }
            }
        }
        if (det == 0){
            System.out.println("Matriks tidak memiliki balikan.");
        }
        
        return this;
    }
    public Matrix adjoin (){
        // kondisi awal harus di kofaktor dulu, baru di transpose, jadilah adjoin.
        Matrix kofac = this.minorkofaktor();
        return kofac.transpose();
    }
    public Matrix transpose (){
        int i, j;
        Matrix tpose = new Matrix(this.baris, this.kolom);
        for (i = 0; i < this.baris; i++){
            for (j = 0; j < this.kolom; j++){
                tpose.matrix[i][j] = this.matrix[j][i];
            }
        }
        return tpose;
    }

    //
    // 

    public double determinanKofaktor (){
        int j;
        double det = 0;

        if (this.baris > 2 && this.kolom > 2){
            for (j = 0; j < this.kolom; j++){
                double exp = Math.pow(-1, j);
                Matrix deter = this.ekspansi(0, j);
                det += exp*(this.matrix[0][j])*deter.determinanKofaktor();
            }
        }
        else {
            det += this.matrix[0][0]*this.matrix[1][1] - (this.matrix[0][1]*this.matrix[1][0]);
        }
        return det;

    }
    //  
    //
    public Matrix minorkofaktor (){
        int i, j;
        Matrix hasil = new Matrix(this.baris, this.kolom);
        for (i = 0; i < this.baris; i++){
            for (j = 0; j < this.kolom; j++){
                double exp = Math.pow(-1, i+j);
                Matrix deter = this.ekspansi(i, j);
                hasil.matrix[i][j] = exp*deter.determinanKofaktor();
            }
        }
        return hasil;
    }

    //
    //

    public Matrix ekspansi (int tbar, int tkol){
        int i, j;
        int ii = 0;
        int jj = 0;
        Matrix rec = new Matrix(this.baris-1, this.kolom-1);
        if (this.baris > 2 && this.kolom > 2){
            for (i = 0; i < this.baris; i++){
                if (i == tbar){
                    ii--;
                }
                jj = 0;
                for (j = 0; j < this.kolom; j++){
                    if (i != tbar){
                        if (j == tkol){
                            jj--;
                        }
                        else{
                           // System.out.printf("i = %d, j = %d, ii = %d, jj = %d\n", i, j, ii, jj);
                            rec.matrix[ii][jj] = this.matrix[i][j];
                        }
                    }
                    jj++;
                }
                ii++;
            }
        }
        else{
            return this;
        }
        return rec;
    }

    public int[] ambilKol1Utama(Matrix mat) {
        int i,j;
        boolean [] kol1UtamaBol = new boolean[this.kolom];
        for (j=0; j<this.kolom; j++) {
            kol1UtamaBol[j] = false;
        }

        int count = 0; /* Menghitung kolom yang memiliki 1 utama */
        for (i=0; i<this.baris; i++) {
            for (j=0; j<this.kolom; j++) {
                if (mat.matrix[i][j] == 1) {
                    kol1UtamaBol[j] = true;
                    count++;
                    break;
                }
            }
        }
        int [] kol1Utama = new int[count];
        int k =0;
        for (j=0; j<this.kolom; j++) {
            if (kol1UtamaBol[j]) {
                kol1Utama[k] = j;
                k++;
            }
        }
        return kol1Utama;
    }
    public boolean isKol1Utama(int[] kol, int panjang, int x) {
        int j;
        for (j=0; j<panjang; j++) {
            if (kol[j] == x) {
                return true;
            }
        }
        return false;
    }

    public String printSolusi() {
        int i,j, count;
        String solusi;

        boolean noSolusi = false;
        for (i=this.baris-1; i>=0; i--) {
            count = 0; // 
            for (j=0; j<this.kolom; j++) {
                if (this.matrix[i][j] == 0) {
                    count++;
                }
            }
            if (count == this.kolom-1 && this.matrix[i][this.kolom-1] != 0) {
                noSolusi = true;
                break;
            }
        }
        if (noSolusi) {
            solusi = "";
            solusi += "Tidak ada solusi untuk matrix ini";
            System.out.println("Tidak ada solusi untuk matrix ini");
        }
        else {
            boolean parametrik = false;
            for (i=this.baris-1; i>0; i--) {
                count = 0;
                for (j=0; j<this.kolom; j++) {
                    if (this.matrix[i][j] == 0) {
                        count++;
                    }
                }
                if (count >= this.baris+1 || this.kolom > this.baris+1) {
                    parametrik = true;
                    break;
                }
            }
            if (parametrik) {
                for (i=this.baris-1; i>0; i--) {
                    for(j=0; j<this.kolom-1; j++) {
                        if (this.matrix[i][j] == 1) {
                            for (int k = i-1; k>=0; k--) {
                                double nilaix = this.matrix[k][j];
                                this.matrix[k][j] = 0;
                                this.matrix[k][this.kolom-1] -= nilaix * this.matrix[i][this.kolom-1];
                            }
                            i = 0;
                            break;
                        }
                    }
                }
            }
            if (parametrik) {
                double [][] arrSol = new double[this.kolom][];
                String [][] charParam = new String[this.kolom][];
                String[][] constChar = new String[9][] ;
                constChar[0] = new String[] {"s"};
                constChar[1] = new String[] {"t"};
                constChar[2] = new String[] {"u"};
                constChar[3] = new String[] {"v"};
                constChar[4] = new String[] {"w"};
                constChar[5] = new String[] {"x"};
                constChar[6] = new String[] {"y"};
                constChar[7] = new String[] {"z"};
                constChar[8] = new String[] {"a"};
                int [] kol1Utama = ambilKol1Utama(this);
                int panjangkol1Utama = kol1Utama.length;
                count = 0;
                for (i=this.baris-1; i>=0; i--) {
                    for (j=0; j<this.kolom; j++) {
                        if (!isKol1Utama(kol1Utama, panjangkol1Utama, j) && !kolom0Semua(j)) {
                            charParam[j] = constChar[j];
                            arrSol[j] = new double[] {0,1};
                        }
                        else if (!isKol1Utama(kol1Utama, panjangkol1Utama, j) && kolom0Semua(j)) {
                            arrSol[j] = new double[] {0,0};
                            charParam[j] = new String[] {};
                        }
                        else {
                            if (count == 0 && this.matrix[i][j] == 1 && j == this.kolom-2) {
                                arrSol[j] = new double[] {this.matrix[i][j+1]};
                                count++;
                                charParam[j] = new String[] {};
                            }
                            else if (this.matrix[i][j] == 1 && j<this.kolom) {
                                arrSol[j] = new double[this.kolom];
                                charParam[j] = new String[this.kolom];
                                for (int k = j+1; k<this.kolom; k++) {
                                    arrSol[j][k] = (-1)*this.matrix[i][k];
                                    if (k == this.kolom-1) {
                                        arrSol[j][k] = this.matrix[i][k];
                                    }
                                    else if (arrSol[j][k] != 0 && k != this.kolom-1) {
                                        charParam[j][k] = constChar[k][0];
                                    }
                                }
                                if (i-1 >= 0) {
                                    if (this.matrix[i-1][j] != 0)  {
                                        double pengali = this.matrix[i-1][j];
                                        this.matrix[i-1][j] = 0;
                                        for (int k = j+1; k<this.kolom; k++) {
                                            this.matrix[i-1][k] = -1*(pengali * this.matrix[i][k] - this.matrix[i-1][k]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                double arrSolBaru[][] = new double[this.kolom-1][];
                String charParamBaru[][] = new String[this.kolom-1][];
                int countSol;
                int countParam;
                for (i=0; i<this.kolom-1; i++) {
                    countSol = 0;
                    for (j=0; j<arrSol[i].length; j++) {
                        if (arrSol[i][j] != 0 ) {
                            countSol++;
                        }
                        else if (arrSol[i][j] == 0 && j == arrSol[i].length-1) {
                            countSol++;
                        }
                    }
                    arrSolBaru[i] = new double[countSol+1];
                }

                for (i=0; i<this.kolom-1; i++) {
                    countParam = 0;
                    for (j=0; j<charParam[i].length; j++) {
                        if (charParam[i][j] != null ) {
                            countParam++;
                        }
                    }
                    charParamBaru[i] = new String[countParam];
                }

                for (i=0; i<this.kolom-1; i++) {
                    countSol = 0;
                    for (j=0; j<arrSol[i].length; j++) {
                        if (j==0) {
                            arrSolBaru[i][countSol] = 0;
                            countSol++;
                        }
                        if (arrSol[i].length == 1 && arrSol[i][0] != 0) {
                            arrSolBaru[i][countSol] = arrSol[i][0];
                            countSol++;
                        }
                        else {
                            if (arrSol[i][j] != 0) {
                                arrSolBaru[i][countSol] = arrSol[i][j];
                                countSol++;
                            }
                            else if (arrSol[i][j] == 0 && j == arrSol[i].length-1) {
                                arrSolBaru[i][countSol] = 0;
                                countSol++;
                            }
                        }
                    }
                }

                for (i=0; i<this.kolom-1; i++) {
                    countParam = 0;
                    for (j=0; j<charParam[i].length; j++) {
                        if (charParam[i][j] != null) {
                            charParamBaru[i][countParam] = charParam[i][j];
                            countParam++;
                        }
                        else if (i==this.kolom-2 && j == 0) {
                            charParamBaru[i] = new String[] {};
                        }
                    }
                }
                solusi = "";
                for (i=0; i<this.kolom-1; i++) {
                    countParam = charParamBaru[i].length-1;
                    for (j=arrSolBaru[i].length-1; j>0; j--) {
                        if (arrSolBaru[i].length == 2 && arrSolBaru[i][1] == 0) {
                            solusi +="";
                        }
                        else if (arrSolBaru[i].length == 2 && arrSolBaru[i][1] != 0 && charParamBaru[i].length != 0) {
                            solusi += "x" + Integer.toString(i+1) + " = " + charParamBaru[i][countParam];
                        }
                        else if (arrSolBaru[i].length == 2 && arrSolBaru[i][1] != 0  && charParamBaru[i].length == 0) {
                            solusi += "x" + Integer.toString(i+1) + " = " + Double.toString(arrSolBaru[i][1]);
                        }
                        else if (arrSolBaru[i][j] != 0 && j == arrSolBaru[i].length-1) {
                            solusi += "x" + Integer.toString(i+1) + " = " + Double.toString(arrSolBaru[i][j]);
                        }
                        else if (arrSolBaru[i][j] == 0 && j == arrSolBaru[i].length-1) {
                            solusi += "x" + Integer.toString(i+1) + " = ";
                        }
                        else if (j != arrSolBaru[i].length-1 && arrSolBaru[i][j] > 0 && arrSolBaru[i][j] == 1) {
                            solusi += " + " + charParamBaru[i][countParam];
                            countParam--;
                        }
                        else if (j != arrSolBaru[i].length-1 && arrSolBaru[i][j] < 0 && (-1)*arrSolBaru[i][j] == 1 ) {
                            solusi += " - " + charParamBaru[i][countParam];
                            countParam--;
                        }
                        else if (j != arrSolBaru[i].length-1 && arrSolBaru[i][j] > 0 && arrSolBaru[i][j] != 1) {
                            solusi += " + " + Double.toString(arrSolBaru[i][j]) + charParamBaru[i][countParam];
                            countParam--;
                        }
                        else if (j != arrSolBaru[i].length-1 && arrSolBaru[i][j] < 0 && arrSolBaru[i][j] != 1) {
                            solusi += " - " + Double.toString(-1*arrSolBaru[i][j]) + charParamBaru[i][countParam];
                            countParam--;
                        }
                    }
                    solusi += "\n";
                }
                System.out.println("Solusi yang didapat dari persamaan adalah: ");
                System.out.println(solusi);
            }
            else {
                Matrix x = new Matrix(this.kolom-1, 1);
                for (i = 0; i < this.kolom-1; i++){
                    x.matrix[i][0] = 0;
                }

                for (i = this.baris-1; i >=0; i--){
                    for (j = this.kolom-2; j >= 0; j--){
                        if (i != j){
                            x.matrix[i][0] -= x.matrix[j][0]*this.matrix[i][j];
                        }
                    }
                    x.matrix[i][0] += this.matrix[i][this.kolom-1];
                    x.matrix[i][0] /= this.matrix[i][i];
                }
                solusi = "Berikut solusi yang didapatkan dari persamaan matrix diatas :\n";
                for (i = 0; i < this.kolom-1; i++){
                    solusi += "Nilai x" + Integer.toString(i+1) + " = " + Double.toString(x.matrix[i][0]) + "\n";
                }
                System.out.println(solusi);
            }
        }
        return solusi;
    }


    static Matrix perkalianMatriks(Matrix m1, Matrix m2) {
        int i, j, k;
        float sum;
        if (m1.kolom == m2.baris) {
            Matrix mbaru = new Matrix(m1.baris, m2.kolom);
            for (i=0; i<mbaru.baris; i++) {
                for (j=0; j<mbaru.kolom; j++) {
                    sum = 0;
                    for (k=0; k<m1.kolom; k++) {
                        sum += m1.matrix[i][k] * m2.matrix[k][j];
                    }
                    mbaru.matrix[i][j] = sum;
                }

            }
            return mbaru;
        }
        
        else if (m1.baris == m2.kolom) {
            Matrix mbaru = new Matrix(m2.baris, m1.kolom);
            for (i=0; i<mbaru.baris; i++) {
                for (j=0; j<mbaru.kolom; j++) {
                    sum = 0;
                    for (k=0; k<m2.kolom; k++) {
                        sum += m2.matrix[i][k] * m1.matrix[k][j];
                    }
                    mbaru.matrix[i][j] = sum;
                }
            }
            return mbaru;
        }
        else {
            return m1;
        }
    }
    // Punya ray

    public String SPLinversOBELine(){
        int i,j,tidak;
        String line="";
        tidak = 0;
        Matrix inversepembantu = new Matrix(this.baris, this.kolom-1);
        Matrix awal = new Matrix(this.baris, this.kolom-1);
        Matrix hasilmatrix = new Matrix(this.baris, 1);
        Matrix hasilsplinvers = new Matrix(this.baris,1);
        
        for (i=0;i<this.baris;i++){
            for (j=0;j<this.kolom-1;j++){
                inversepembantu.matrix[i][j] = this.matrix[i][j];
            }
        }

        for (i=0;i<this.baris;i++){
            for (j=0;j<this.kolom-1;j++){
                awal.matrix[i][j] = this.matrix[i][j];
            }
        }


        for (i=0;i<this.baris;i++){
            hasilmatrix.matrix[i][0] = this.matrix [i][this.kolom-1];
        }

        inversepembantu.inverseOBE();
        for (i=0;i<this.baris;i++){
            for (j=0;j<this.kolom-1;j++){
                if(inversepembantu.matrix[i][j] == this.matrix[i][j]){
                    tidak +=1;
                }
            }
        }

        if (tidak == (this.baris *(this.kolom-1)))
        {
            line = "Matriks tidak memiliki balikan sehingga tidak bisa menggunakan metode ini";
            return line;
        }
        else
        {
            hasilsplinvers = perkalianMatriks(inversepembantu, hasilmatrix);
            for (i = 0; i<this.baris;i++){
                int idx = i+1;
                line += "X";
                line += idx;
                line += " = ";
                line += Double.toString(hasilsplinvers.matrix[i][0]);
                line +="\n";
            }
            return line;
        }
    }

    public Matrix SPLinversOBE(){
        int i,j,tidak;
        tidak = 0;
        Matrix inversepembantu = new Matrix(this.baris, this.kolom-1);
        Matrix awal = new Matrix(this.baris, this.kolom-1);
        Matrix hasilmatrix = new Matrix(this.baris, 1);
        Matrix hasilsplinvers = new Matrix(this.baris,1);
        
        for (i=0;i<this.baris;i++){
            for (j=0;j<this.kolom-1;j++){
                inversepembantu.matrix[i][j] = this.matrix[i][j];
            }
        }

        for (i=0;i<this.baris;i++){
            for (j=0;j<this.kolom-1;j++){
                awal.matrix[i][j] = this.matrix[i][j];
            }
        }


        for (i=0;i<this.baris;i++){
            hasilmatrix.matrix[i][0] = this.matrix [i][this.kolom-1];
        }

        inversepembantu.inverseOBE();
        for (i=0;i<this.baris;i++){
            for (j=0;j<this.kolom-1;j++){
                if(inversepembantu.matrix[i][j] == this.matrix[i][j]){
                    tidak +=1;
                }
            }
        }

        if (tidak == (this.baris *(this.kolom-1)))
        {
            System.out.println("Matriks tidak memiliki balikan sehingga tidak bisa menggunakan metode ini");
            return awal;
        }
        else
        {
            hasilsplinvers = perkalianMatriks(inversepembantu, hasilmatrix);
            System.out.println("Hasil SPL adalah : ");
            String line = "";
            for (i = 0; i<this.baris;i++){
                int idx = i+1;
                System.out.printf("X[%d] = %.3f\n", idx, hasilsplinvers.matrix[i][0]);
                line += "X";
                line += idx;
                line += " = ";
                line += Double.toString(hasilsplinvers.matrix[i][0]);
                line +="\n";
            }
            return hasilsplinvers;
        }
    }
    public static int FileBaris (String NamaFile){
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
        return baris;
    }

    public static int FileKolom (String NamaFile){
        int kolom = -9999;
        try {
            Scanner scan2 = new Scanner (new File(NamaFile));
            kolom = 1;
            String baris1 = scan2.nextLine();
            for (int i=0;i<baris1.length();i++){
                if (baris1.charAt(i) == ' '){
                    kolom+=1;
                }
            }
            scan2.close ();
        }
        catch (Exception e){
            System.out.println("File tidak ada");
        }
        return kolom;
    }

    public void ReadMatrixFile (String NamaFile){
        int i,j;
        try {
            i = 0;
            j = 0;
            File newFile = new File(NamaFile);
            Scanner scan2 = new Scanner(newFile);
            while (scan2.hasNextDouble()){
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
            System.out.println("error2");
        }
    }


    public void outputFileMatriks (){
        System.out.println("Masukkan nama file tanpa txt dan tanpa spasi");
        Scanner scan2 = new Scanner(System.in);
        String NamaFile = scan2.next();
        try {
            FileWriter writer = new FileWriter(NamaFile+".txt");
            writer.write("Matrix balikan : \n");
            for (int i = 0;i<this.baris;i++){
                for (int j=0;j<this.kolom;j++){
                    writer.write(Double.toString(this.matrix[i][j]));
                    if (j != this.kolom -1){
                        writer.write (" ");
                    }
                }
                writer.write("\n");
            }
            System.out.println("File disimpan di " + NamaFile + ".txt");
            writer.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    public void outputFileLine (String line){
        System.out.println("Masukkan nama file tanpa txt dan tanpa spasi");
        Scanner scan2 = new Scanner(System.in);
        String NamaFile = scan2.next();
        try {
            FileWriter writer = new FileWriter(NamaFile+".txt");
            writer.write(line);
            System.out.println("File disimpan di " + NamaFile + ".txt");
            writer.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public void outputFileNumber (double det){
        System.out.println("Masukkan nama file tanpa txt dan tanpa spasi");
        Scanner scan2 = new Scanner(System.in);
        String NamaFile = scan2.next();
        try {
            FileWriter writer = new FileWriter(NamaFile+".txt");
            writer.write("Hasil adalah " + Double.toString(det));
            System.out.println("File disimpan di " + NamaFile + ".txt");
            writer.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    public void konfirmasiOutputFile (int tipe, double det, String line){
        char yesorno;
        Scanner scan2 = new Scanner(System.in);
        System.out.println("Apakah hasil ingin disimpan ke sebuah file? (y/n)");
        yesorno = scan2.next().charAt(0);
        if (yesorno != 'n' && yesorno != 'y'){
            System.out.println("Input salah");
        }
        while (yesorno != 'n' && yesorno != 'y'){
            System.out.println("Apakah hasil ingin disimpan ke sebuah file? (y/n)");
            yesorno = scan2.next().charAt(0);
            if (yesorno != 'n' && yesorno != 'y'){
                System.out.println("Input salah");
            }
        }
        if (yesorno == 'y' && (tipe == 1||tipe == 4||tipe == 5||tipe == 6)){
            this.outputFileLine(line);
        }
        else if ( yesorno == 'y' && tipe ==2 ){
            this.outputFileNumber(det);
        }
        else if (yesorno == 'y' && tipe ==3){
            this.outputFileMatriks();
        }
    }
    public Matrix solusiGauss(){
        int i, j;
        Matrix x = new Matrix(this.kolom-1, 1);
        for (i = 0; i < this.kolom-1; i++){
            x.matrix[i][0] = 0;
        }

        for (i = this.baris-1; i >=0; i--){
            for (j = this.kolom-2; j >= 0; j--){
                if (i != j){
                    x.matrix[i][0] -= x.matrix[j][0]*this.matrix[i][j];
                }
            }
            x.matrix[i][0] += this.matrix[i][this.kolom-1];
            x.matrix[i][0] /= this.matrix[i][i];
        }
        for (i = 0; i < this.kolom-1; i++){
            System.out.printf("Nilai x[%d] = %.4f\n", i, x.matrix[i][0]);
        }
        return x;
        
    }
    

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner input = new Scanner(System.in);
        Matrix mat = new Matrix(4, 4);
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
    }
}
