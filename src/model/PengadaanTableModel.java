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

/**
 *
 * @author ashary
 */
import entity.PengadaanEntity;
import java.util.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ashary
 */
public class PengadaanTableModel extends AbstractTableModel {
    List<PengadaanEntity> buku = new ArrayList<>();
    private final String header[] = {"No Pengadaan", "Tanggal", "Kode Buku", "Asal Buku", "Jumlah", "Keterangan"};
    
    public PengadaanTableModel(List<PengadaanEntity> kat) {
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
        PengadaanEntity kat = buku.get(rowIndex);
        
        switch (colIndex) {
            case 0: return kat.getNoPengadaan();
            case 1: return kat.getTglPengadaan();
            case 2: return kat.getKdBuku();
            case 3: return kat.getAsalBuku();
            case 4: return kat.getJumlah();
            case 5: return kat.getKeterangan();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return header[column];
    }
}
