/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoBD;

import java.sql.*;

/**
 *
 * @author tulre
 */
public class Conexion {
    Statement stmt = null; //Permite ejecutar las sentencias sql sin parametros
    Connection conect = null; //Conexion a una base de datos. Una aplicacion puede tener multiples conexiones con varias bases de datos

    public static Connection sql;

    public static Connection conectar(){
        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;database=Cremeria_Toscano;user=sa; password = 1234;";
            sql = DriverManager.getConnection(connectionUrl); //Gestiona los controladores
            System.out.println("Conectado.");
        } 
        catch (SQLException ex) {
            System.out.println("Error." + ex);
        }
        return sql;
    }

    public static void main(String args[]){
        conectar();
    }
}
