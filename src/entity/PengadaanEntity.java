/*
 * Copyright (C) 2019 ashary
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package entity;

/**
 *
 * @author ashary
 */
public class PengadaanEntity {
    private String noPengadaan;
    private String tglPengadaan;
    private String kdBuku;
    private String asalBuku;
    private String jumlah;
    private String keterangan;
    
    public String getNoPengadaan() {
        return this.noPengadaan;
    }
    
    public String getTglPengadaan() {
        return this.tglPengadaan;
    }
    
    public String getKdBuku() {
        return this.kdBuku;
    }
    
    public String getAsalBuku() {
        return this.asalBuku;
    }
    
    public String getJumlah() {
        return this.jumlah;
    }
    
    public String getKeterangan() {
        return this.keterangan;
    }
    
    public void setNoPengadaan(String noPengadaan) {
        this.noPengadaan = noPengadaan;
    }
    
    public void setTglPengadaan(String tglPengadaan) {
        this.tglPengadaan = tglPengadaan;
    }
    
    public void setKdBuku(String kdBuku) {
        this.kdBuku = kdBuku;
    }
    
    public void setAsalBuku(String asalBuku) {
        this.asalBuku = asalBuku;
    }
    
    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
}