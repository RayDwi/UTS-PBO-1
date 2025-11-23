package katalog_produk_toko;

public class ProdukElektronik extends Produk {
    private String merek;
    private int garansiBulan;
    
    public ProdukElektronik(String kodeProduk, String namaProduk, double harga, String merek, int garansiBulan) {
        super(kodeProduk, namaProduk, harga);
        this.merek = merek;
        this.garansiBulan = garansiBulan;
    }

    public String getMerek() {
        return merek;
    }

    public int getGaransiBulan() {
        return garansiBulan;
    }

    public double hitungBiayaAsuransi() {
        return getHarga() * 0.01;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Merek: " + merek);
        System.out.println("Garansi: " + garansiBulan + " bulan");
        System.out.println("Biaya Asuransi: Rp" + hitungBiayaAsuransi());
    }
}
