package com.ngopidevteam.pranadana.metime.Model;

public class ModelData {
    String jamMulai, jamSelesai;

    public ModelData() {
    }

    public ModelData(String jamMulai, String jamSelesai) {
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }
}
