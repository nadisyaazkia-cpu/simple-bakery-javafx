package kelas.controller;

import java.io.IOException;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class DaftarController {
    @FXML private TableView<Produk> tabel;
    @FXML private TableColumn<Produk, String> kodeCol;
    @FXML private TableColumn<Produk, String> namaCol;
    @FXML private TableColumn<Produk, String> kategoriCol;
    @FXML private TableColumn<Produk, Double> hargaCol;
    @FXML private TextField cariField;
    @FXML private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;
    private final ObservableList<Produk> dataTampilan = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        kodeCol.setCellValueFactory(new PropertyValueFactory<>("kode"));
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kategoriCol.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("harga"));

        tabel.setItems(dataTampilan);
        tabel.setPlaceholder(new Label("Belum ada data produk."));
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
        cari();
    }

    @FXML
    private void cari() {
        if (data == null) return;
        dataTampilan.clear();
        String kata = cariField.getText().trim().toLowerCase(Locale.ROOT);

        for (Produk p : data.getDaftar()) {
            if (p.getNama().toLowerCase(Locale.ROOT).contains(kata)) {
                dataTampilan.add(p);
            }
        }
        pesanLabel.setText("Jumlah produk ditemukan: " + dataTampilan.size());
    }

    @FXML
    private void tambah() {
        try {
            navigator.bukaForm();
        } catch (IOException e) {
            pesanLabel.setText("Form gagal dibuka.");
        }
    }

    @FXML
    private void detail() {
        Produk terpilih = tabel.getSelectionModel().getSelectedItem();
        if (terpilih == null) {
            pesanLabel.setText("Pilih satu produk pada tabel terlebih dahulu.");
            return;
        }
        try {
            navigator.bukaDetail(terpilih);
        } catch (IOException e) {
            pesanLabel.setText("Detail gagal dibuka.");
        }
    }
}