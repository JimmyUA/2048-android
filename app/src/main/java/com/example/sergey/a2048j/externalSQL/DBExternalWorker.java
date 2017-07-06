package com.example.sergey.a2048j.externalSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Sergey on 23.06.2017.
 */

public class DBExternalWorker {

    Connection conn = null;
    Statement st = null;

    DBExternalWorker() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection conn = DriverManager
                .getConnection("jdbc:mysql://5.79.97.171:3306/game2048", "root", "Vika_Ruban");
        st = conn.createStatement();

    }
}