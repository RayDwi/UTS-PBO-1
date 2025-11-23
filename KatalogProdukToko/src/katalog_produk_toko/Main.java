package katalog_produk_toko;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Produk> daftarProduk = new ArrayList<>();
    private static List<Transaksi> daftarTransaksi = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        daftarProduk.add(new ProdukElektronik("E001", "Laptop Gaming", 15000000, "Asus", 12));
        daftarProduk.add(new ProdukPakaian("P001", "Kaos Polos", 75000, "L", "Katun"));
        daftarProduk.add(new ProdukPakaian("P002", "Celana Jeans", 300000, "M", "Denim"));

        int pilihan = 0;
        do {
            tampilkanMenu();
            System.out.print("Masukkan pilihan: ");
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
                prosesPilihan(pilihan);
            } catch (java.util.InputMismatchException e) {
                System.out.println("\n[ERROR] Masukkan harus berupa angka.");
                scanner.nextLine();
                pilihan = 0;
            }
        } while (pilihan != 7);
    }

    private static void tampilkanMenu() {
        System.out.println("\n==================================");
        System.out.println("Aplikasi Katalog Produk Toko (OOP)");
        System.out.println("==================================");
        System.out.println("1. Tambah Data Produk");
        System.out.println("2. Tampilkan Semua Produk");
        System.out.println("3. Cari Produk Berdasarkan Kode");
        System.out.println("4. Ubah Harga Produk");
        System.out.println("5. Cek Status Produk");
        System.out.println("6. Buat Transaksi Pembelian");
        System.out.println("7. Keluar dari Program");
        System.out.println("----------------------------------");
    }

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

    private static void tambahDataProduk() {
        System.out.println("\n--- Tambah Data Produk ---");
        System.out.print("Masukkan Kode Produk: ");
        String kode = scanner.nextLine();
        
        if (cariProdukByKode(kode) != null) {
            System.out.println("[WARNING] Produk dengan kode " + kode + " sudah ada.");
            return;
        }

        System.out.print("Masukkan Nama Produk: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Tipe Produk (1: Elektronik, 2: Pakaian): ");
        int tipe = scanner.nextInt();
        scanner.nextLine();
        
        if (tipe == 1) {
            System.out.print("Masukkan Merek Elektronik: ");
            String merek = scanner.nextLine();
            System.out.print("Masukkan Garansi (bulan): ");
            int garansi = scanner.nextInt();
            scanner.nextLine();

            ProdukElektronik pe = new ProdukElektronik(kode, nama, harga, merek, garansi);
            daftarProduk.add(pe);
            System.out.println("[INFO] Produk Elektronik berhasil ditambahkan!");
        } else if (tipe == 2) {
            System.out.print("Masukkan Ukuran Pakaian (S/M/L): ");
            String ukuran = scanner.nextLine();
            System.out.print("Masukkan Bahan Pakaian: ");
            String bahan = scanner.nextLine();

            ProdukPakaian pp = new ProdukPakaian(kode, nama, harga, ukuran, bahan);
            daftarProduk.add(pp);
            System.out.println("[INFO] Produk Pakaian berhasil ditambahkan!");
        } else {
            System.out.println("[ERROR] Tipe produk tidak valid.");
        }
    }

    private static void tampilkanDataProduk() {
        if (daftarProduk.isEmpty()) {
            System.out.println("[INFO] Katalog produk kosong.");
            return;
        }
        System.out.println("\n--- Tampilkan Semua Produk (" + daftarProduk.size() + " item) ---");
        int count = 1;
        for (Produk p : daftarProduk) {
            System.out.println("No. " + count++);
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

    private static Produk cariProdukByKode(String kode) {
        for (Produk p : daftarProduk) {
            if (p.getKodeProduk().equalsIgnoreCase(kode)) {
                return p;
            }
        }
        return null;
    }

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

    private static void ubahHargaProduk() {
        System.out.print("\nMasukkan Kode Produk yang akan diubah harganya: ");
        String kode = scanner.nextLine();
        Produk produkDitemukan = cariProdukByKode(kode);
        
        if (produkDitemukan != null) {
            System.out.println("[INFO] Harga saat ini: Rp" + produkDitemukan.getHarga());
            System.out.print("Masukkan Harga Baru: ");
            double hargaBaru = scanner.nextDouble();
            scanner.nextLine(); 

            produkDitemukan.setHarga(hargaBaru);
            System.out.println("[INFO] Harga produk " + produkDitemukan.getNamaProduk() + " berhasil diubah menjadi Rp" + produkDitemukan.getHarga());
        } else {
            System.out.println("[WARNING] Produk dengan kode " + kode + " tidak ditemukan.");
        }
    }

    private static void cekStatusProduk() {
        System.out.print("\nMasukkan Kode Produk untuk cek status: ");
        String kode = scanner.nextLine();
        Produk produkDitemukan = cariProdukByKode(kode);
        
        if (produkDitemukan != null) {
            System.out.println("[INFO] Status ketersediaan " + produkDitemukan.getNamaProduk() + ": " + produkDitemukan.cekStatus());
        } else {
            System.out.println("[WARNING] Produk dengan kode " + kode + " tidak ditemukan.");
        }
    }

    private static void buatTransaksi() {
        System.out.println("\n--- Buat Transaksi Baru ---");
        System.out.print("Masukkan ID Transaksi: ");
        String idT = scanner.nextLine();
        System.out.print("Masukkan Nama Pelanggan: ");
        String namaP = scanner.nextLine();
        
        Transaksi transaksiBaru = new Transaksi(idT, namaP);
        
        String tambahLagi;
        do {
            System.out.print("\nMasukkan Kode Produk yang ingin dibeli: ");
            String kode = scanner.nextLine();
            Produk produk = cariProdukByKode(kode);
            
            if (produk != null) {
                transaksiBaru.tambahProduk(produk);
                System.out.println("[INFO] Produk " + produk.getNamaProduk() + " berhasil ditambahkan ke transaksi.");
            } else {
                System.out.println("[WARNING] Produk tidak ditemukan.");
            }
            
            System.out.print("Tambah produk lain? (y/t): ");
            tambahLagi = scanner.nextLine();
        } while (tambahLagi.equalsIgnoreCase("y"));

        daftarTransaksi.add(transaksiBaru);
        transaksiBaru.displayDetailTransaksi();
        System.out.println("[SUCCESS] Transaksi berhasil dicatat!");
    }
}
