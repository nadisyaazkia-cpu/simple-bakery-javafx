package kelas.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kelas.model.Produk;

public class DataProduk {
    private final ObservableList<Produk> daftar = FXCollections.observableArrayList();
    private int urutan = 1;

    public DataProduk() {
        // Otomatis membuat 5 katalog produk awal (Point Plus Dosen)
        tambah("Roti Tawar", "Makanan", 15000, "https://www.rumahmesin.com/wp-content/uploads/2017/02/resep-roti-tawar-cara-membuat-roti-tawar-enak-empuk-dan-lembut.jpg");
        tambah("Croissant Cokelat", "Makanan", 22000, "https://i.pinimg.com/originals/cf/9b/af/cf9bafa8f904bb4c2aacf06cebf46331.jpg");
        tambah("Donut Glazed", "Makanan", 12000, "https://i.pinimg.com/originals/71/c7/56/71c7560b78e99cae7ebf88406dad50ab.jpg");
        tambah("Es Kopi Susu", "Minuman", 18000, "https://klikwartaku.com/wp-content/uploads/2025/09/Kopi-Es-gula-Aren.jpg");
        tambah("Matcha Latte", "Minuman", 25000, "https://911recipes.com/wp-content/uploads/2025/05/Creamy-Iced-Matcha-Latte-1.jpg");
    }

    public ObservableList<Produk> getDaftar() {
        return FXCollections.unmodifiableObservableList(daftar);
    }

    public Produk tambah(String nama, String kategori, double harga, String gambarUri) {
        String kode = String.format("PRD%03d", urutan);
        Produk p = new Produk(kode, nama, kategori, harga, gambarUri);
        daftar.add(p);
        urutan++;
        return p;
    }
}