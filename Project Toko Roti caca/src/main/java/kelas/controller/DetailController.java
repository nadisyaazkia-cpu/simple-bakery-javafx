package kelas.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kelas.Navigator;
import kelas.model.Produk;

public class DetailController {
    @FXML private Label kodeLabel;
    @FXML private Label namaLabel;
    @FXML private Label kategoriLabel;
    @FXML private Label hargaLabel;
    @FXML private ImageView gambarView;

    private Navigator navigator;

    public void setData(Navigator navigator, Produk produk) {
        this.navigator = navigator;
        kodeLabel.setText(produk.getKode());
        namaLabel.setText(produk.getNama());
        kategoriLabel.setText(produk.getKategori());

        NumberFormat rupiah = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"));
        hargaLabel.setText(rupiah.format(produk.getHarga()));

        if (produk.getGambarUri() != null && !produk.getGambarUri().isBlank()) {
    Image img = new Image(produk.getGambarUri(), 320, 220, true, true, true); // true terakhir = background loading
    img.exceptionProperty().addListener((obs, oldEx, newEx) -> {
        if (newEx != null) {
            System.out.println("Gagal memuat gambar dari URI: " + produk.getGambarUri());
            newEx.printStackTrace();
        }
    });
    gambarView.setImage(img);
} else {
    gambarView.setImage(null);
        }
    }

    @FXML
    private void bukaDaftar() {
        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}