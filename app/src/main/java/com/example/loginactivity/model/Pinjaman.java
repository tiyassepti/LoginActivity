package com.example.loginactivity.model;

public class Pinjaman {
    String id;

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    String tglPinjam,alasan;
    Double besarPinjam;
    int lamaPinjam;
    String status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNamarek() {
        return namarek;
    }

    public void setNamarek(String namarek) {
        this.namarek = namarek;
    }

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    Double totalPinjam;
    String email,namarek,norek;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(String tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    public Double getBesarPinjam() {
        return besarPinjam;
    }

    public void setBesarPinjam(Double besarPinjam) {
        this.besarPinjam = besarPinjam;
    }



    public void setTotalPinjam(Double totalPinjam) {
        this.totalPinjam = totalPinjam;
    }

    public Pinjaman(String id, String tglPinjam, String alasan, Double besarPinjam, int lamaPinjam, String status, Double totalPinjam, String email, String namarek, String norek) {
        this.id = id;
        this.tglPinjam = tglPinjam;
        this.alasan = alasan;
        this.besarPinjam = besarPinjam;
        this.lamaPinjam = lamaPinjam;
        this.status = status;
        this.totalPinjam = totalPinjam;
        this.email = email;
        this.namarek = namarek;
        this.norek = norek;
    }

    public int getLamaPinjam() {
        return lamaPinjam;
    }

    public void setLamaPinjam(int lamaPinjam) {
        this.lamaPinjam = lamaPinjam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPinjam() {
        return totalPinjam;
    }

    public Pinjaman() {
    }


}