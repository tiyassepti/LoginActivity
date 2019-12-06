package com.example.loginactivity.model;

public class Angsuran {
    String kodeAngsuran;
    String kodePinjaman;
    String tanggal;
    Integer angsuranKe;
    Integer jumlah;
    String buktiAngsuran;

    public Angsuran() {
    }

    public Angsuran(String kodeAngsuran, String kodePinjaman, String tanggal, Integer angsuranKe, Integer jumlah, String buktiAngsuran) {
        this.kodeAngsuran = kodeAngsuran;
        this.kodePinjaman = kodePinjaman;
        this.tanggal = tanggal;
        this.angsuranKe = angsuranKe;
        this.jumlah = jumlah;
        this.buktiAngsuran = buktiAngsuran;
    }

    public String getKodeAngsuran() {
        return kodeAngsuran;
    }

    public void setKodeAngsuran(String kodeAngsuran) {
        this.kodeAngsuran = kodeAngsuran;
    }

    public String getKodePinjaman() {
        return kodePinjaman;
    }

    public void setKodePinjaman(String kodePinjaman) {
        this.kodePinjaman = kodePinjaman;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getAngsuranKe() {
        return angsuranKe;
    }

    public void setAngsuranKe(Integer angsuranKe) {
        this.angsuranKe = angsuranKe;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public String getBuktiAngsuran() {
        return buktiAngsuran;
    }

    public void setBuktiAngsuran(String buktiAngsuran) {
        this.buktiAngsuran = buktiAngsuran;
    }
}
