import java.util.Scanner;

class Menu {
    public static int menuUtama() {
        Scanner scan = new Scanner(System.in);

        System.out.println("=============== MENU UTAMA ===============");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi Linear Berganda");
        System.out.println("7. Keluar");
        System.out.println("Input pilihan dengan angka dari 1-7: ");
        System.out.print("=> ");
        int result = scan.nextInt();
        System.out.println();

        return result;
    }

    public static int menuSPL() {
        Scanner scan = new Scanner(System.in);

        System.out.println("========= MENU SPL =========");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("Input pilihan dengan angka dari 1-4: ");
        System.out.print("=> ");
        int result = scan.nextInt();
        System.out.println();
        return result;

    }

    public static int menuDeterminan() {
        Scanner scan = new Scanner(System.in);

        System.out.println("========= MENU DETERMINAN =========");
        System.out.println("1. Metode Operasi Baris Elementer");
        System.out.println("2. Metode Matriks Kofaktor");
        System.out.println("Input pilihan dengan angka dari 1-2: ");
        System.out.print("=> ");
        int result = scan.nextInt();
        System.out.println();

        return result;
    }

    public static int menuInput() {
        Scanner scan = new Scanner(System.in);

        System.out.println("========= MENU INPUT =========");
        System.out.println("1. Masukan berupa input dari keyboard");
        System.out.println("2. Masukan berupa file text");
        System.out.println("Input pilihan dengan angka dari 1-2: ");
        System.out.print("=> ");
        int result = scan.nextInt();
        System.out.println();

        return result;
    }

    public static int menuInvers() {
        Scanner scan = new Scanner(System.in);

        System.out.println("========= MENU INVERS =========");
        System.out.println("1. Metode Adjoin dan Kofaktor");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("Input pilihan dengan angka dari 1-2: ");
        System.out.print("=> ");
        int result = scan.nextInt();
        System.out.println();

        return result;
    }

    public static void runMenu() {
        Scanner scan = new Scanner(System.in);
        boolean masihrun = true;
        int menu, spl, determinan, invers;

        while (masihrun) {
            menu = Menu.menuUtama();
            if (menu == 1) {
                spl = Menu.menuSPL();
                if (spl == 1) {
                    int carainput = menuInput();
                    if (carainput == 2) {
                        System.out.println("Masukkan nama file yang akan dibaca: ");
                        String NamaFile = scan.next();
                        Matrix mat = new Matrix(Matrix.FileBaris(NamaFile), Matrix.FileKolom(NamaFile));
                        mat.ReadMatrixFile(NamaFile);
                        mat.gauss();
                        mat.pembagiGauss();
                        String solusi = mat.printSolusi();
                        mat.konfirmasiOutputFile(1,0,solusi);

                    }
                    else if (carainput == 1){
                        System.out.print("Masukkan baris: ");
                        int m = scan.nextInt();
                        System.out.print("Masukkan kolom: ");
                        int n = scan.nextInt();
                        Matrix mat = new Matrix(m,n);
                        mat.readMatrix();
                        mat.gauss();
                        mat.pembagiGauss();
                        String solusi = mat.printSolusi();
                        mat.konfirmasiOutputFile(1,0,solusi);
                    }
                    else {
                        System.out.println("Masukan pilihan tidak valid!");
                    }
                }
                else if (spl == 2) {
                    int carainput = menuInput();
                    if (carainput == 2) {
                        System.out.println("Masukkan nama file yang akan dibaca: ");
                        String NamaFile = scan.next();
                        Matrix mat = new Matrix(Matrix.FileBaris(NamaFile), Matrix.FileKolom(NamaFile));
                        mat.ReadMatrixFile(NamaFile);
                        mat.gaussJordan();
                        String solusi = mat.printSolusi();
                        mat.konfirmasiOutputFile(1,0,solusi);
                    }
                    else if (carainput == 1) {
                        System.out.print("Masukkan baris: ");
                        int m = scan.nextInt();
                        System.out.print("Masukkan kolom: ");
                        int n = scan.nextInt();
                        Matrix mat = new Matrix(m,n);
                        mat.readMatrix();
                        mat.gaussJordan();
                        String solusi = mat.printSolusi();
                        mat.konfirmasiOutputFile(1,0,solusi);
                    }
                    else {
                        System.out.println("Masukan pilihan tidak valid!");
                    }
                }
                else if (spl == 3) {
                    int carainput = menuInput();
                    if (carainput == 2) {
                        System.out.println("Masukkan nama file yang akan dibaca: ");
                        String NamaFile = scan.next();
                        Matrix mat = new Matrix(Matrix.FileBaris(NamaFile), Matrix.FileKolom(NamaFile));
                        mat.ReadMatrixFile(NamaFile);
                        Matrix inverse = mat.SPLinversOBE();
                        String line = mat.SPLinversOBELine();
                        inverse.konfirmasiOutputFile(1,0,line);
                    }
                    else if (carainput == 1){
                        System.out.print("Masukkan baris: ");
                        int m = scan.nextInt();
                        System.out.print("Masukkan kolom: ");
                        int n = scan.nextInt();
                        Matrix mat = new Matrix(m,n);
                        mat.readMatrix();
                        Matrix inverse = mat.SPLinversOBE();
                        String line = mat.SPLinversOBELine();
                        inverse.konfirmasiOutputFile(1,0,line);
                    }
                    else {
                        System.out.println("Masukan pilihan tidak valid!");
                    }
                }
                else if (spl == 4) {
                    int carainput = menuInput();
                    if (carainput == 2) {
                        System.out.println("Masukkan nama file yang akan dibaca: ");
                        String NamaFile = scan.next();
                        Matrix mat = new Matrix(Matrix.FileBaris(NamaFile), Matrix.FileKolom(NamaFile));
                        mat.ReadMatrixFile(NamaFile);
                        String solusi = mat.kaidahCramer();
                        mat.konfirmasiOutputFile(1,0,solusi);
                    }
                    else if (carainput == 1){
                        System.out.println("Untuk SPL dengan metode kaidah cramer, Mohon input baris dan kolom dengan kolom = baris+1");
                        System.out.print("Masukkan baris: ");
                        int m = scan.nextInt();
                        System.out.print("Masukkan kolom: ");
                        int n = scan.nextInt();
                        Matrix mat = new Matrix(m,n);
                        mat.readMatrix();
                        String solusi = mat.kaidahCramer();
                        mat.konfirmasiOutputFile(1,0,solusi);
                    }
                    else {
                        System.out.println("Masukan pilihan tidak valid!");
                    }
                }
            }
            else if (menu == 2) {
                int det = Menu.menuDeterminan();
                if (det == 1) {
                    int carainput = menuInput();
                    if (carainput == 2) {
                        System.out.println("Masukkan nama file yang akan dibaca: ");
                        String NamaFile = scan.next();
                        Matrix mat = new Matrix(Matrix.FileBaris(NamaFile), Matrix.FileKolom(NamaFile));
                        mat.ReadMatrixFile(NamaFile);
                        double hasil = mat.determinan();
                        System.out.format("Nilai Determinan dari Matrix tersebut adalah %.3f\n", hasil);
                        System.out.print("\n");
                        mat.konfirmasiOutputFile(2,hasil,null);
                    }
                    else if (carainput == 1) {
                        System.out.print("Masukkan baris: ");
                        int m = scan.nextInt();
                        System.out.print("Masukkan kolom: ");
                        int n = scan.nextInt();
                        Matrix mat = new Matrix(m,n);
                        mat.readMatrix();
                        double hasil = mat.determinan();
                        System.out.format("Nilai Determinan dari Matrix tersebut adalah %.3f\n", hasil);
                        System.out.print("\n");
                        mat.konfirmasiOutputFile(2,hasil,null);
                    }
                    else {
                        System.out.println("Masukan pilihan tidak valid!");
                    }
                }
                else if (det == 2) {
                    int carainput = menuInput();
                    if (carainput == 2) {
                        System.out.println("Masukkan nama file yang akan dibaca: ");
                        String NamaFile = scan.next();
                        Matrix mat = new Matrix(Matrix.FileBaris(NamaFile), Matrix.FileKolom(NamaFile));
                        mat.ReadMatrixFile(NamaFile);
                        double hasil = mat.determinanKofaktor();
                        System.out.format("Nilai Determinan dari Matrix tersebut adalah %.3f", hasil);
                        System.out.print("\n");
                        mat.konfirmasiOutputFile(2,hasil,null);
                    }
                    else if (carainput == 1) {
                        System.out.print("Masukkan baris: ");
                        int m = scan.nextInt();
                        System.out.print("Masukkan kolom: ");
                        int n = scan.nextInt();
                        Matrix mat = new Matrix(m,n);
                        mat.readMatrix();
                        double hasil = mat.determinanKofaktor();
                        System.out.format("Nilai Determinan dari Matrix tersebut adalah %.3f", hasil);
                        System.out.print("\n");
                        mat.konfirmasiOutputFile(2,hasil,null);
                    }
                    else {
                        System.out.println("Masukan pilihan tidak valid!");
                    }
                }
                else {
                    System.out.println("Masukan pilihan tidak valid!");
                }
            }
            else if (menu == 3) {
                invers = Menu.menuInvers();
                if (invers == 2) {
                    int carainput = menuInput();
                    if (carainput == 2) {
                        System.out.println("Masukkan nama file yang akan dibaca: ");
                        String NamaFile = scan.next();
                        Matrix mat = new Matrix(Matrix.FileBaris(NamaFile), Matrix.FileKolom(NamaFile));
                        mat.ReadMatrixFile(NamaFile);
                        mat.inverseOBE();
                        mat.printMatrix();
                        mat.konfirmasiOutputFile(3,0,null);
                    }
                    else if (carainput == 1) {
                        System.out.print("Masukkan baris: ");
                        int m = scan.nextInt();
                        System.out.print("Masukkan kolom: ");
                        int n = scan.nextInt();
                        Matrix mat = new Matrix(m,n);
                        mat.readMatrix();
                        mat.inverseOBE();
                        mat.printMatrix();
                        mat.konfirmasiOutputFile(3,0,null);
                    }
                    else {
                        System.out.println("Masukan pilihan tidak valid!");
                    }
                }
                else if (invers == 1){
                    int carainput = menuInput();
                    if (carainput == 2) {
                        System.out.println("Masukkan nama file yang akan dibaca: ");
                        String NamaFile = scan.next();
                        Matrix mat = new Matrix(Matrix.FileBaris(NamaFile), Matrix.FileKolom(NamaFile));
                        mat.ReadMatrixFile(NamaFile);
                        mat.inverseKofaktor();
                        mat.konfirmasiOutputFile(3,0,null);

                    }
                    else if (carainput == 1){
                        System.out.print("Masukkan baris: ");
                        int m = scan.nextInt();
                        System.out.print("Masukkan kolom: ");
                        int n = scan.nextInt();
                        Matrix mat = new Matrix(m,n);
                        mat.readMatrix();
                        mat.inverseKofaktor();
                        mat.konfirmasiOutputFile(3,0,null);
                    }
                    else {
                        System.out.println("Masukan pilihan tidak valid!");
                    }
                }
                else {
                    System.out.println("Masukan pilihan tidak valid!");
                }
            }
            else if (menu == 4) {
                int carainput = menuInput();
                if (carainput == 2) {
                    System.out.println("Masukkan nama file yang akan dibaca: ");
                    String NamaFile = scan.next();
                    Interpolasi inter = new Interpolasi(Interpolasi.FileBarisInterpolasi(NamaFile), Interpolasi.FileKolomInterpolasi(NamaFile));
                    Interpolasi interTitik = new Interpolasi(1,1);
                    inter.ReadInterpolasiFile(NamaFile);
                    interTitik.ReadInterpolasiTitikFile(NamaFile);
                    String solusi = inter.interpolasiline(interTitik, carainput);
                    inter.konfirmasiOutputFile(1,0,solusi);
                }
                else if (carainput == 1) {
                    System.out.print("Banyak titik: ");
                    int i, j;
                    int titik = scan.nextInt();
                    Interpolasi inter = new Interpolasi(titik, 2);
                    Interpolasi dummy = new Interpolasi(1,1);
                    dummy.matrix[0][0] = 1;
                
                    for (i=0;i<inter.baris;i++){
                        for (j=0; j<inter.kolom; j++) {
                            inter.matrix[i][j] = scan.nextDouble();
                        }
                    }
                    String solusi = inter.interpolasiline(dummy, carainput);
                    inter.konfirmasiOutputFile(1,0,solusi);
                }
                else {
                    System.out.println("Masukan pilihan tidak valid!");
                }
            }
            else if (menu == 5) {
                int carainput = menuInput();
                Scanner input = new Scanner(System.in);
                InterpolasiBikubik mat = new InterpolasiBikubik(4, 4);
                if (carainput == 2) {
                    double hasil = mat.fxy(carainput);
                    mat.konfirmasiOutputFile(carainput, hasil, null);
                    
                }
                else if (carainput == 1){
                    int i,j;
                    System.out.println("Masukan nilai matriks 4x4: ");
                    for (i = 0; i < 4; i++){
                        for (j = 0;j < 4; j++){
                            mat.matrix[i][j] = input.nextDouble();
                        }
                    }
                    double hasil = mat.fxy(carainput);
                    mat.konfirmasiOutputFile(carainput, hasil, null);

                }
                else {
                    System.out.println("Masukan pilihan tidak valid!");
                }
            }
            else if (menu == 6) {
                int carainput =  menuInput();
                if (carainput == 2) {
                    System.out.println("Masukkan nama file yang akan dibaca: ");
                    String NamaFile = scan.next();
                    RegresiLinear regresi = new RegresiLinear(RegresiLinear.FileBarisRegresi(NamaFile), RegresiLinear.FileKolomRegresi(NamaFile));
                    RegresiLinear pengubah = new RegresiLinear(1,RegresiLinear.FileKolomPengubah(NamaFile));
                    regresi.ReadRegresiFile(NamaFile);
                    pengubah.ReadPengubahFile(NamaFile);
                    String solution = regresi.regresiBerganda(pengubah,2);
                    regresi.konfirmasiOutputFile(6,0,solution);

                }
                else if (carainput == 1) {
                    System.out.print("Masukkan jumlah peubah: ");
                    int m = scan.nextInt();
                    System.out.print("Masukkan jumlah sampel: ");
                    int n = scan.nextInt();
                    RegresiLinear m1 = new RegresiLinear(n, m+1);
                    RegresiLinear dummy = new RegresiLinear(1, 1);
                    dummy.matrix[0][0] = 0;
                    System.out.println("Masukkan nilai nilai sampel: ");
                    m1.readMatrix();
                    String solution = m1.regresiBerganda(dummy,1);
                    m1.konfirmasiOutputFile(6,0,solution);
                }
                else {
                    System.out.println("Masukan pilihan tidak valid!");
                }
            }
            else if (menu == 7) {
                System.out.println("Terima kasih telah menggunakan program kami (:");
                break;
            }
            else {
                System.out.println();
                System.out.println("Pilihan tidak valid");
            }

            System.out.println();
            System.out.println("Apakah ingin mengulang operasi lain? (y/n)");
            char ulang = scan.next().charAt(0);
            if (ulang != 'y' && ulang != 'Y') {
                masihrun = false;
                System.out.println("Terima kasih telah menggunakan program kami (:");
            }
        }
    }
}
