package katalog_produk_toko;

import java.util.ArrayList;
import java.util.List;

public class Transaksi {
    private String idTransaksi;
    private String namaPelanggan;
    private List<Produk> daftarProdukDibeli;
    private double totalHarga;

    public Transaksi(String idTransaksi, String namaPelanggan) {
        this.idTransaksi = idTransaksi;
        this.namaPelanggan = namaPelanggan;
        this.daftarProdukDibeli = new ArrayList<>();
        this.totalHarga = 0.0;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public List<Produk> getDaftarProdukDibeli() {
        return daftarProdukDibeli;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void tambahProduk(Produk produk) {
        daftarProdukDibeli.add(produk);
        totalHarga += produk.getHarga();
    }

    public void displayDetailTransaksi() {
        System.out.println("\n--- Detail Transaksi ID: " + idTransaksi + " ---");
        System.out.println("Pelanggan: " + namaPelanggan);
        System.out.println("Total Barang: " + daftarProdukDibeli.size());
        System.out.println("Daftar Produk:");
        for (Produk p : daftarProdukDibeli) {
            System.out.print("- ");
            p.displayInfo(); 
            System.out.println("---");
        }
        System.out.println("TOTAL HARGA: Rp" + totalHarga);
        System.out.println("----------------------------------------");
    }
}
