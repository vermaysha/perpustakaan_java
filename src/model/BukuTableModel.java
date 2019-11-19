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
package model;

import entity.BukuEntity;
import java.util.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ashary
 */
public class BukuTableModel extends AbstractTableModel {
    List<BukuEntity> buku = new ArrayList<>();
    private final String header[] = {"Kode Buku", "Judul", "Kategori", "Penerbit", "ISBN", "Pengarang", "Halaman", "Jumlah", "Tahun Terbit"};
    
    public BukuTableModel(List<BukuEntity> kat) {
        this.buku = kat;
    }
    

    @Override
    public int getRowCount() {
        return buku.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        BukuEntity kat = buku.get(rowIndex);
        
        switch (colIndex) {
            case 0: return kat.getKode();
            case 1: return kat.getJudul();
            case 2: return kat.getKategori();
            case 3: return kat.getPenerbit();
            case 4: return kat.getIsbn();
            case 5: return kat.getPengarang();
            case 6: return kat.getHalaman();
            case 7: return kat.getJumlah();
            case 8: return kat.getThTerbit();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return header[column];
    }
}
