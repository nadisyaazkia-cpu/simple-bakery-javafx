package kelas.controller;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import kelas.Navigator;
import kelas.data.DataProduk;

public class FormController {
    @FXML private TextField namaField;
    @FXML private TextField hargaField;
    @FXML private ComboBox<String> kategoriBox;
    @FXML private Label pesanLabel;
    @FXML private ImageView gambarView;

    private String gambarUri = ""; // Beri nilai awal string kosong agar tidak null
    private Navigator navigator;
    private DataProduk data;

    @FXML
    private void initialize() {
        kategoriBox.getItems().addAll("Makanan", "Minuman");
        kategoriBox.setValue("Makanan");
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
    }

    @FXML
    private void pilihGambar() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Pilih Gambar Produk");
        chooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Gambar PNG/JPEG", "*.png", "*.jpg", "*.jpeg"));
        
        File file = chooser.showOpenDialog(namaField.getScene().getWindow());
        if (file == null) return;

        // Ambil string URI file lokal (file:/...)
        gambarUri = file.toURI().toString();

        try {
            // Load image tanpa memaksakan ukuran di konstruktor agar tidak glitch
            Image image = new Image(gambarUri, true); 
            
            // Set ukuran fisik tampilan lewat ImageView langsung
            gambarView.setFitWidth(280);
            gambarView.setFitHeight(160);
            gambarView.setPreserveRatio(true);
            gambarView.setImage(image);

            pesanLabel.setText("Gambar produk berhasil dipilih.");
        } catch (Exception e) {
            pesanLabel.setText("Gagal memuat file gambar.");
            e.printStackTrace();
        }
    }

    @FXML
    private void simpan() {
        try {
            if (namaField.getText().trim().isEmpty()) {
                pesanLabel.setText("Nama produk tidak boleh kosong.");
                return;
            }

            double harga = Double.parseDouble(hargaField.getText().trim());
            
            // Simpan data produk termasuk gambarUri yang sudah terpilih
            data.tambah(namaField.getText().trim(), kategoriBox.getValue(), harga, gambarUri);
            
            navigator.bukaDaftar();
        } catch (NumberFormatException e) {
            pesanLabel.setText("Harga harus berupa angka yang valid.");
        } catch (IllegalArgumentException e) {
            pesanLabel.setText(e.getMessage());
        } catch (IOException e) {
            pesanLabel.setText("Gagal membuka halaman daftar.");
        }
    }

    @FXML
    private void bukaDaftar() {
        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            pesanLabel.setText("Daftar gagal dibuka.");
        }
    }
}