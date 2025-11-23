/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package katalog_produk_toko; // Sesuaikan dengan nama project Anda

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main { // Class Main untuk menjalankan program
    
    // List untuk menyimpan objek Produk
    private static List<Produk> daftarProduk = new ArrayList<>();
    // List untuk menyimpan objek Transaksi (Relasi Class)
    private static List<Transaksi> daftarTransaksi = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        // Inisialisasi data dummy untuk pengujian
        daftarProduk.add(new ProdukElektronik("E001", "Laptop Gaming", 15000000, "Asus", 12));
        daftarProduk.add(new ProdukPakaian("P001", "Kaos Polos", 75000, "L", "Katun"));
        daftarProduk.add(new ProdukPakaian("P002", "Celana Jeans", 300000, "M", "Denim"));

        int pilihan = 0;
        
        // Loop utama menu
        do {
            tampilkanMenu();
            System.out.print("Masukkan pilihan: ");
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                prosesPilihan(pilihan);
            } catch (java.util.InputMismatchException e) {
                System.out.println("\n[ERROR] Masukkan harus berupa angka.");
                scanner.nextLine(); // Clear buffer
                pilihan = 0;
            }
        } while (pilihan != 7);
    }
    
    // Method untuk menampilkan menu
    private static void tampilkanMenu() {
        System.out.println("\n==================================");
        System.out.println("Aplikasi Katalog Produk Toko (OOP)");
        System.out.println("==================================");
        System.out.println("1. Tambah Data Produk"); // Tambah data
        System.out.println("2. Tampilkan Semua Produk"); // Tampilkan data
        System.out.println("3. Cari Produk Berdasarkan Kode"); // Cari data
        System.out.println("4. Ubah Harga Produk"); // Ubah data
        System.out.println("5. Cek Status Produk"); // Cek status data
        System.out.println("6. Buat Transaksi Pembelian"); // Relasi Class
        System.out.println("7. Keluar dari Program"); // Keluar dari program
        System.out.println("----------------------------------");
    }
    
    // Method untuk memproses pilihan menu
    private static void prosesPilihan(int pilihan) {
        switch (pilihan) {
            case 1:
                tambahDataProduk();
                break;
            case 2:
                tampilkanDataProduk();
                break;
            case 3:
                cariProduk();
                break;
            case 4:
                ubahHargaProduk();
                break;
            case 5:
                cekStatusProduk();
                break;
            case 6:
                buatTransaksi();
                break;
            case 7:
                System.out.println("Terima kasih. Program selesai.");
                break;
            default:
                System.out.println("[ERROR] Pilihan tidak valid.");
        }
    }
    
    // Implementasi Tambah Data Produk (Case 1)
    private static void tambahDataProduk() {
        System.out.println("\n--- Tambah Data Produk ---");
        System.out.print("Masukkan Kode Produk: ");
        String kode = scanner.nextLine();
        
        // Cek duplikasi kode
        if (cariProdukByKode(kode) != null) {
            System.out.println("[WARNING] Produk dengan kode " + kode + " sudah ada.");
            return;
        }

        System.out.print("Masukkan Nama Produk: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer
        
        System.out.print("Tipe Produk (1: Elektronik, 2: Pakaian): ");
        int tipe = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        if (tipe == 1) {
            System.out.print("Masukkan Merek Elektronik: ");
            String merek = scanner.nextLine();
            System.out.print("Masukkan Garansi (bulan): ");
            int garansi = scanner.nextInt();
            scanner.nextLine();
            
            // OBJEK ProdukElektronik dibuat
            ProdukElektronik pe = new ProdukElektronik(kode, nama, harga, merek, garansi);
            daftarProduk.add(pe);
            System.out.println("[INFO] Produk Elektronik berhasil ditambahkan!");
        } else if (tipe == 2) {
            System.out.print("Masukkan Ukuran Pakaian (S/M/L): ");
            String ukuran = scanner.nextLine();
            System.out.print("Masukkan Bahan Pakaian: ");
            String bahan = scanner.nextLine();
            
            // OBJEK ProdukPakaian dibuat
            ProdukPakaian pp = new ProdukPakaian(kode, nama, harga, ukuran, bahan);
            daftarProduk.add(pp);
            System.out.println("[INFO] Produk Pakaian berhasil ditambahkan!");
        } else {
            System.out.println("[ERROR] Tipe produk tidak valid.");
        }
    }
    
    // Implementasi Tampilkan Data (Case 2)
    private static void tampilkanDataProduk() {
        if (daftarProduk.isEmpty()) {
            System.out.println("[INFO] Katalog produk kosong.");
            return;
        }
        System.out.println("\n--- Tampilkan Semua Produk (" + daftarProduk.size() + " item) ---");
        int count = 1;
        for (Produk p : daftarProduk) {
            System.out.println("No. " + count++);
            // POLIMORFISME terjadi di sini:
            // Method displayInfo yang dipanggil akan disesuaikan
            // dengan tipe objek sebenarnya (Elektronik atau Pakaian)
            p.displayInfo(); 
            System.out.println("-------------------------");
        }
        
        System.out.println("\n--- Daftar Transaksi ---");
        if(daftarTransaksi.isEmpty()){
            System.out.println("[INFO] Belum ada transaksi tercatat.");
        } else {
            for(Transaksi t : daftarTransaksi){
                System.out.println("ID Transaksi: " + t.getIdTransaksi() + ", Pelanggan: " + t.getNamaPelanggan() + ", Total: Rp" + t.getTotalHarga());
            }
        }
    }
    
    // Method utilitas untuk mencari produk berdasarkan kode
    private static Produk cariProdukByKode(String kode) {
        for (Produk p : daftarProduk) {
            if (p.getKodeProduk().equalsIgnoreCase(kode)) {
                return p;
            }
        }
        return null;
    }
    
    // Implementasi Cari Data (Case 3)
    private static void cariProduk() {
        System.out.print("\nMasukkan Kode Produk yang dicari: ");
        String kode = scanner.nextLine();
        Produk produkDitemukan = cariProdukByKode(kode);
        
        if (produkDitemukan != null) {
            System.out.println("\n[INFO] Produk ditemukan:");
            produkDitemukan.displayInfo();
        } else {
            System.out.println("[WARNING] Produk dengan kode " + kode + " tidak ditemukan.");
        }
    }
    
    // Implementasi Ubah Data (Case 4)
    private static void ubahHargaProduk() {
        System.out.print("\nMasukkan Kode Produk yang akan diubah harganya: ");
        String kode = scanner.nextLine();
        Produk produkDitemukan = cariProdukByKode(kode);
        
        if (produkDitemukan != null) {
            System.out.println("[INFO] Harga saat ini: Rp" + produkDitemukan.getHarga());
            System.out.print("Masukkan Harga Baru: ");
            double hargaBaru = scanner.nextDouble();
            scanner.nextLine(); 
            
            // Menggunakan Setter untuk mengubah data (Enkapsulasi)
            produkDitemukan.setHarga(hargaBaru);
            System.out.println("[INFO] Harga produk " + produkDitemukan.getNamaProduk() + " berhasil diubah menjadi Rp" + produkDitemukan.getHarga());
        } else {
            System.out.println("[WARNING] Produk dengan kode " + kode + " tidak ditemukan.");
        }
    }

    // Implementasi Cek Status Data (Case 5)
    private static void cekStatusProduk() {
        System.out.print("\nMasukkan Kode Produk untuk cek status: ");
        String kode = scanner.nextLine();
        Produk produkDitemukan = cariProdukByKode(kode);
        
        if (produkDitemukan != null) {
            // Memanggil method khusus (Cek Status Data)
            System.out.println("[INFO] Status ketersediaan " + produkDitemukan.getNamaProduk() + ": " + produkDitemukan.cekStatus());
        } else {
            System.out.println("[WARNING] Produk dengan kode " + kode + " tidak ditemukan.");
        }
    }
    
    // Implementasi Relasi Antar Kelas (Case 6)
    private static void buatTransaksi() {
        System.out.println("\n--- Buat Transaksi Baru ---");
        System.out.print("Masukkan ID Transaksi: ");
        String idT = scanner.nextLine();
        System.out.print("Masukkan Nama Pelanggan: ");
        String namaP = scanner.nextLine();

        // 1. Buat Objek Transaksi (Composition)
        Transaksi transaksiBaru = new Transaksi(idT, namaP);
        
        String tambahLagi;
        do {
            System.out.print("\nMasukkan Kode Produk yang ingin dibeli: ");
            String kode = scanner.nextLine();
            Produk produk = cariProdukByKode(kode);
            
            if (produk != null) {
                // 2. Tambahkan Produk ke dalam Transaksi (Composition)
                transaksiBaru.tambahProduk(produk);
                System.out.println("[INFO] Produk " + produk.getNamaProduk() + " berhasil ditambahkan ke transaksi.");
            } else {
                System.out.println("[WARNING] Produk tidak ditemukan.");
            }
            
            System.out.print("Tambah produk lain? (y/t): ");
            tambahLagi = scanner.nextLine();
        } while (tambahLagi.equalsIgnoreCase("y"));
        
        // Simpan Transaksi dan tampilkan detailnya
        daftarTransaksi.add(transaksiBaru);
        transaksiBaru.displayDetailTransaksi();
        System.out.println("[SUCCESS] Transaksi berhasil dicatat!");
    }
}
