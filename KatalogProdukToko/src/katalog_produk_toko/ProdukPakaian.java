package katalog_produk_toko;

public class ProdukPakaian extends Produk {
    private String ukuran;
    private String bahan;

    public ProdukPakaian(String kodeProduk, String namaProduk, double harga, String ukuran, String bahan) {
        super(kodeProduk, namaProduk, harga);
        this.ukuran = ukuran;
        this.bahan = bahan;
    }

    public String getUkuran() {
        return ukuran;
    }

    public String getBahan() {
        return bahan;
    }

    public String cekKategoriUkuran() {
        if (ukuran.equalsIgnoreCase("S") || ukuran.equalsIgnoreCase("M") || ukuran.equalsIgnoreCase("L")) {
            return "Dewasa";
        } else {
            return "Anak-anak";
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Ukuran: " + ukuran);
        System.out.println("Bahan: " + bahan);
        System.out.println("Kategori: " + cekKategoriUkuran());
    }
}
