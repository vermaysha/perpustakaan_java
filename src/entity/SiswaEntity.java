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
public class SiswaEntity {
    private String kode;
    private String nama;
    private String nisn;
    private String kelamin;
    private String agama;
    private String tempat_lahir;
    private String tanggal_lahir;
    private String alamat;
    private String no_telp;
    
    public String getKode() {
        return this.kode;
    }
    
    public String getNama() {
        return this.nama;
    }
    
    public String getNisn() {
        return this.nisn;
    }
    
    public String getKelamin() {
        return this.kelamin;
    }
    
    public String getAgama() {
        return this.agama;
    }
    
    public String getTempatLahir() {
        return this.tempat_lahir;
    }
    
    public String getTanggalLahir() {
        return this.tanggal_lahir;
    }
    
    public String getAlamat() {
        return this.alamat;
    }
    
    public String getTelp() {
        return this.no_telp;
    }
    
    public void setKode(String kode) {
        this.kode = kode;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setNisn(String nisn) {
        this.nisn = nisn;
    }
    
    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }
    
    public void setAgama(String agama) {
        this.agama = agama;
    }
    
    public void setTempatLahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }
    
    public void setTanggalLahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public void setTelp(String telp) {
        this.no_telp = telp;
    }
    
}
