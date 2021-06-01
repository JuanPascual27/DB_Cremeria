/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoBD;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tulre
 */
public class metodosBD {
    Connection con;
    Conexion cn = new Conexion();
    
    public void llenarTabla(String sql, DefaultTableModel modelo, int col) {
        try {
            con = cn.conectar();
            CallableStatement cmd = con.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                Object[] datos = new Object[col];
                for(int i = 0; i < col; i++){
                    datos[i] = rs.getString(i+1);                  
                }
                modelo.addRow(datos);
            }
            cmd.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void llenarTablaD(String sql, DefaultTableModel modelo, int col) {
        try {
            con = cn.conectar();
            CallableStatement cmd = con.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            while(rs.next()){
                Object[] datos = new Object[col];
                for(int i = 0; i < col; i++){
                    datos[i] = rs.getString(i+2);              
                }
                modelo.addRow(datos);
            }
            cmd.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int agregar(String sql) {
        int band = 0;
        try {
            con = cn.conectar();
            CallableStatement cmd = con.prepareCall(sql);
            band = cmd.executeUpdate();
            cmd.close();
            con.close();          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return band;
    }
    
    public Object[] buscar(String sql, int col) {
        Object[] datos = new Object[col];
        for(int i = 0; i < col; i++){
            datos[i] = "";  
        }
        try {
            con = cn.conectar();
            PreparedStatement cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            while(rs.next()) {
                for(int i = 0; i < col; i++){
                    datos[i] = rs.getString(i+1);  
                }
            }
            cmd.close();
            con.close();          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return datos;
    }
    
    public Object buscarA(String sql, String col) {
        Object dato = "";
        try {
            con = cn.conectar();
            PreparedStatement cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            while(rs.next()) {
                dato = rs.getString(col);
            }
            cmd.close();
            con.close();          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dato;
    }
    
    public Object[] buscarD(String sql, int col) {
        Object[] datos = new Object[col];
        for(int i = 0; i < col; i++){
            datos[i] = "";  
        }
        try {
            con = cn.conectar();
            PreparedStatement cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            while(rs.next()) {
                for(int i = 0; i < col; i++){
                    datos[i] = rs.getString(i+2);  
                }
            }
            cmd.close();
            con.close();          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return datos;
    }
    
    public int eliminar(String sql) {
        int band = 0;
        try {
            con = cn.conectar();
            CallableStatement cmd = con.prepareCall(sql);
            band = cmd.executeUpdate();
            cmd.close();
            con.close();          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return band;
    }
    
    public int modificar(String sql) {
        int band = 0;
        try {
            con = cn.conectar();
            CallableStatement cmd = con.prepareCall(sql);
            band = cmd.executeUpdate();
            cmd.close();
            con.close();          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return band;
    }
    /*
    //metodo 1 para validar con parametros (tipo de dato)
    public boolean validar(Object dato, String tipo, boolean negativos) {
        
    }
    
    //metodo 2 para validar con parametros (tipo de dato, tamaño)
    public boolean validar(Object dato, String tipo, int tamaño) {
        String d = dato.toString();
        int num;
        boolean b = false;
        if(tipo.equals("int")) {
            try {
                num = Integer.parseInt(d);
                if
            }catch(NumberFormatException e) {
                
            }
        }
        return b;
    }
    
    //metodo 3 para validar con parametros (tipo de dato, formato)
    public boolean validar(Object dato, String tipo, String formato) {
        
    }*/
}
