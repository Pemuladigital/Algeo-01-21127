# Library dan Aplikasi Interpolasi Polinom, Interpolasi Bikubik, dan Regresi Linier Berganda
Program ini adalah sebuah perpustakaan aljabar geometri yang berisi metode eliminasi Gauss, Gauss Jordan, SPL, Ekspansi Kofaktor, Interpolasi Polinom, Interpolasi Bikubik, dan Regresi Linier


# Instalasi Pemrograman
Program yang telah kami buat menggunakan bahasa java sehingga minimal dibutuhkan compiler Java SE versi 8
Compiler dapat diunduh melalui:
- https://www.oracle.com/java/technologies/downloads/#java8
- https://www.java.com/en/download/
# Cara Kompilasi Kode Program
Untuk mengompilasi kode program, buka terminal pada komputer dan pergi ke directory dan

# Cara Menjalankan Program
Untuk menjalankan program, pertama kita compile dan run program file main.java untuk menjalankan menu utama program.


# Cara Menggunakan Program 
Setelah program di run dalam file main.javanya, akan muncul menu utama. Di dalam menu utama, 
terdapat 7 pilihan. Masukkan input pilihan sesuai dengan pilihan yang diinginkan untuk dicari solusinya.

# Pilihan input
Saat memilih salah satu pilihan dalam menu utama, terdapat bagian pilihan input setelah pengguna menentukan pilihan mana beserta pilihan metode yang digunakan.
Masukkan input terdiri dari 2 jenis yaitu keyboard, dan berupa file txt yang berisi data input yang ingin dimasukkan. Berikut contoh input menggunakan keyboard dan menggunakan file.


# 1. Sistem Persamaan Linier
Ketika memasukkan input 1 dalam menu utama, pengguna akan masuk ke bagian sistem persamaan linier.
Sistem persamaan linier dapat memberikan solusi berupa nilai variabel-variabel dari beberapa persamaan linier koefisien variabel tersebut dengan metode eliminasi
Gauss,Gauss Jordan, Matriks balikan, dan kaidah cramer. Input yang dimasukkan ke dalam sistem persamaan linier ini berupa 


![SPL](https://user-images.githubusercontent.com/110378747/193562585-c726b96b-dee7-4540-b21e-b5e1ef7af3dc.PNG)


# 2. Determinan
Pilihan 2 dalam menu utama akan menghasilkan nilai determinan dari suatu input yang berupa matriks. Matriks yang dimasukkan dalam input ini juga sudah memiliki
prekondisi berbentuk kotak, yaitu baris dan kolomnya berjumlah sama. Setelah memilih pilihan 2, terdapat sub-menu untuk metode yang digunakan apakah menggunakan ekspansi kofaktor atau menggunakan metode operasi baris elementer. Berikut adalah contoh sebuah input matriks yang ingin dicari determinannya.


# 3. Matriks balikan
Pilihan 3 akan menghasilkan matriks balikan atau inverse dari suatu input matriks. Setelah memilih pilihan ini, pengguna akan ditunjukkan sub-menu untuk menentukan metode yang ingin digunakan dalam mencari matriks balikan berupa metode Adjoin dan Kofaktor atau Eliminasi Gauss-Jordan. Berikut contoh sebuah input matriks yang ingin dicari matriks balikannya.

# 4. Interpolasi Polinom
Untuk interpolasi polinom, pertama - tama input jumlah titik yang akan dimasukkan, lalu masukkan sample titiknya, dan setelah didapatkan rumusnya, masukkan x yang ingin diuji, dan x tersebut pun akan dimasukkan ke rumus yang sudah ada secara otomatis dan akan menghasilkan hasil.
![1664800443079](https://user-images.githubusercontent.com/92111319/193578081-d70965e2-6424-4a59-9529-b278a87932fe.jpg)


# 5. Interpolasi Bicubic
Untuk interpolasi bikubik pertama inputkan sebuah matriks 4x4 yang setiap elemennya berisi nilai f(x,y) dari x dan y bernilai -1 hingga 2. Kemudian setelah itu akan
dirumuskan hasil interpolasi f(x,y)nya dan diminta input nilai x dan y yang ingin diuji dari f(x,y) yang telah dibentuk. Berikut adalah contoh sebuah input untuk mencari hasil interpolasi bikubiknya pada sebuah titik.

# 6. Regresi linier berganda
Untuk Regresi Linear Berganda pertama-tama kita input terlebih dahulu jumlah peubah pada data yang kita punya, kemudian kita input jumlah sampel data yang kita punya, barulah kita menginput sampel-sampel data yang kita miliki. Terakhir setelah menginput sampel-sampel data yang kita punya, kita input nilai-nilai peubah yang ingin kita ketahui hasil regresinya. 

![RegLin](https://user-images.githubusercontent.com/88817627/193574665-3e00d314-7a54-491e-ac31-0d1998453582.png)


# 7. Keluar
Apabila pengguna ingin keluar dari program, masukan input pilihan 7 dalam menu utama untuk mengeksekusi exit atau keluar dari program.

# Penyimpanan data
Dari ketujuh pilihan dalam menu utama selain pilihan keluar, setelah program menunjukkan hasil output dari suatu input yang pengguna berikan, hasil tersebut 
dapat disimpan dalam sebuah file txt. Program akan menanyakan apakah hasil output dari input pengguna ingin disimpan ke dalam file atau tidak. Jika iya, maka pengguna diminta memberikan nama file (tanpa .txt nya) sebagai nama file yang menyimpan hasil output dari suatu matriks input yang telah diberikan pengguna tersebut.
# Kontributor
Program ini dibuat untuk memenuhi tugas besar materi kuliah Aljabar Geometri Teknik Informatika ITB. Berikut pembuat dari program ini:
- Marcel Ryan         - 13521127
- Raynard Tanadi      - 13521143
- Kenneth Dave Bahana - 13521145
