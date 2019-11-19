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
public class BukuEntity {
    private String kode;
    private String judul;
    private String kd_kategori;
    private String kd_penerbit;
    private String isbn;
    private String pengarang;
    private String halaman;
    private String jumlah;
    private String th_terbit;
    
    public String getKode() {
        return this.kode;
    }
    
    public String getJudul() {
        return this.judul;
    }
    
    public String getKategori() {
        return this.kd_kategori;
    }
    
    public String getPenerbit() {
        return this.kd_penerbit;
    }
    
    public String getIsbn() {
        return this.isbn;
    }
    
    public String getPengarang() {
        return this.pengarang;
    }
    
    public String getHalaman() {
        return this.halaman;
    }
    
    public String getJumlah() {
        return this.jumlah;
    }
    
    public String getThTerbit() {
        return this.th_terbit;
    }
    
    public void setKode(String kode) {
        this.kode = kode;
    }
    
    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public void setKategori(String kd_kategori) {
        this.kd_kategori = kd_kategori;
    }
    
    public void setPenerbit(String kd_penerbit) {
        this.kd_penerbit = kd_penerbit;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    
    public void setHalaman(String halaman) {
        this.halaman = halaman;
    }
    
    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
    
    public void setThTerbit(String th_terbit) {
        this.th_terbit = th_terbit;
    }
    
}
