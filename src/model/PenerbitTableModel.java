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

import entity.PenerbitEntity;
import java.util.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ashary
 */
public class PenerbitTableModel extends AbstractTableModel {
    List<PenerbitEntity> penerbit = new ArrayList<>();
    private final String header[] = {"Kode Penerbit", "Nama Penerbit"};
    
    public PenerbitTableModel(List<PenerbitEntity> kat) {
        this.penerbit = kat;
    }
    

    @Override
    public int getRowCount() {
        return penerbit.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        PenerbitEntity kat = penerbit.get(rowIndex);
        
        switch (colIndex) {
            case 0: return kat.getKode();
            case 1: return kat.getNama();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return header[column];
    }
}
