package com.com.com.task;

import java.util.Objects;

public class Model {
    // Pariwisata
    private String namaP;
    private String alamatP;
    private String detailP;
    private String gambarP;

    public Model() {
    }

    public Model(String namaP, String alamatP, String detailP, String gambarP) {
        this.namaP = namaP;
        this.alamatP = alamatP;
        this.detailP = detailP;
        this.gambarP = gambarP;
    }

    public String getNamaP() {
        return namaP;
    }

    public void setNamaP(String namaP) {
        this.namaP = namaP;
    }

    public String getAlamatP() {
        return alamatP;
    }

    public void setAlamatP(String alamatP) {
        this.alamatP = alamatP;
    }

    public String getDetailP() {
        return detailP;
    }

    public void setDetailP(String detailP) {
        this.detailP = detailP;
    }

    public String getGambarP() {
        return gambarP;
    }

    public void setGambarP(String gambarP) {
        this.gambarP = gambarP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(namaP, model.namaP) &&
                Objects.equals(alamatP, model.alamatP) &&
                Objects.equals(detailP, model.detailP) &&
                Objects.equals(gambarP, model.gambarP);
    }

    @Override
    public int hashCode() {

        return Objects.hash(namaP, alamatP, detailP, gambarP);
    }
}
