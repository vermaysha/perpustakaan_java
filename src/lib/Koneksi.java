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
package lib;
import java.sql.*;
import java.util.logging.*;

/**
 *
 * @author ashary
 */
public class Koneksi {
    private Connection _conn;
    
    public Koneksi() {
        String url = "jdbc:mysql://localhost:3306/perpustakaan?serverTimezone=UTC&useUnicode=true";
        String username = "root";
        String password = "123456";
        
        if (this._conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this._conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Koneksi.class.getName())
                        .log(Level.SEVERE, ex.getMessage());
            }
        }
    }
    
    public boolean isConnected() {
        return this._conn != null;
    }
    
    public Connection getCon() {
        return this._conn;
    }
}
