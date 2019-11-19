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

import entity.UserEntity;
import java.util.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ashary
 */
public class UserTableModel extends AbstractTableModel {
    List<UserEntity> user = new ArrayList<>();
    private final String header[] = {"Kode User", "Nama User", "Username", "Level"};
    
    public UserTableModel(List<UserEntity> kat) {
        this.user = kat;
    }
    

    @Override
    public int getRowCount() {
        return user.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        UserEntity kat = user.get(rowIndex);
        
        switch (colIndex) {
            case 0: return kat.getKode();
            case 1: return kat.getNama();
            case 2: return kat.getUsername();
            case 3: return kat.getLevel();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return header[column];
    }
}
