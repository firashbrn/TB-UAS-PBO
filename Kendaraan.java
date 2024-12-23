package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import Database.DatabaseConnection;

// Kelas induk Kendaraan
public class Kendaraan {
    private int idParkir;
    private String platNomor;
    private String jenisKendaraan;
    private Date waktuMasuk;

    // Constructor
    public Kendaraan(int idParkir, String platNomor, String jenisKendaraan, Date waktuMasuk) {
        this.idParkir = idParkir;
        this.platNomor = platNomor;
        this.jenisKendaraan = jenisKendaraan;
        this.waktuMasuk = waktuMasuk;
    }

    // Getters and Setters
    public int getIdParkir() {
        return idParkir;
    }

    public void setIdParkir(int idParkir) {
        this.idParkir = idParkir;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public Date getWaktuMasuk() {
        return waktuMasuk;
    }

    public void setWaktuMasuk(Date waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }

    // Implementasikan atau hapus metode yang tidak diperlukan
    // Jika Anda tidak membutuhkan metode tambahKendaraan dan tampilkanKendaraan, bisa dihapus atau diimplementasikan sesuai kebutuhan
    public void tambahKendaraan(Kendaraan kendaraanBaru) {
        // Implementasikan logika untuk menambah kendaraan baru jika diperlukan
    }

    public Object tampilkanKendaraan() {
        // Implementasikan logika untuk menampilkan data kendaraan
        return null; // Atau return data yang sesuai
    }

    // Metode updateKendaraan untuk memperbarui data kendaraan di database
    public void updateKendaraan() {
        String sql = "UPDATE parkir SET platNomor = ?, jenisKendaraan = ?, waktuMasuk = ? WHERE idParkir = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set parameter untuk query
            pstmt.setString(1, getPlatNomor());
            pstmt.setString(2, getJenisKendaraan());
            pstmt.setTimestamp(3, new Timestamp(getWaktuMasuk().getTime()));
            pstmt.setInt(4, getIdParkir());

            // Eksekusi query
            int rowsUpdated = pstmt.executeUpdate();

            // Beri konfirmasi apakah data berhasil diupdate
            if (rowsUpdated > 0) {
                System.out.println("Kendaraan berhasil diperbarui: " + getPlatNomor());
            } else {
                System.out.println("Gagal memperbarui kendaraan. ID tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Error saat memperbarui kendaraan: " + e.getMessage());
        }
    }
}
