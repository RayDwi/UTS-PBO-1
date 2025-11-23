package katalog_produk_toko;

public class Produk {
    private String kodeProduk;
    private String namaProduk;
    private double harga;
    private boolean isTersedia;

    public Produk(String kodeProduk, String namaProduk, double harga) {
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.isTersedia = true;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public boolean isTersedia() {
        return isTersedia;
    }

    public String cekStatus() {
        if (isTersedia) {
            return "Tersedia";
        } else {
            return "Tidak Tersedia";
        }
    }

    public void displayInfo() {
        System.out.println("Kode: " + kodeProduk);
        System.out.println("Nama: " + namaProduk);
        System.out.println("Harga: Rp" + harga);
        System.out.println("Status: " + cekStatus());
    }
}
