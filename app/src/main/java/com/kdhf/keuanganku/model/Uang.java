package com.kdhf.keuanganku.model;

public class Uang {
    private int id;
    private String pemasukan;
    private String pengeluaran;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPemasukan() {
        return pemasukan;
    }

    public void setPemasukan(String pemasukan) {
        this.pemasukan = pemasukan;
    }

    public String getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(String pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public Uang(String pemasukan, String pengeluaran) {
        this.pemasukan = pemasukan;
        this.pengeluaran = pengeluaran;
    }
}
