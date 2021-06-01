/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoBD;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;/**
 *
 * @author joant
 */
public class AdminVentas {
    Connection cn;
    Conexion con=new Conexion();
    private String IdVenta;
    private String IdCliente;
    private String FechaV;
    private float Total;
    
    public AdminVentas(){
        
        cn= con.conectar();
    }
    
    public void LLenarDatos(DefaultTableModel modelo){
        try {
            String sql="select * from Ventas";
            CallableStatement cmd=cn.prepareCall(sql);
            ResultSet rs= cmd.executeQuery();
           
            while(rs.next()){
                Object[] datos=new Object[4];
              
                for(int i=0;i<4;i++){
                    datos[i]=rs.getString(i+1);                  
                }
                modelo.addRow(datos);
            }
            cmd.close();
            cn.close();
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int AgregarVenta (String id, String idC){
        int band =0;
        String fechaV;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        fechaV = dateFormat.format(date);
        System.out.println(fechaV);
        double total = 0;
        

        try {
            cn = con.conectar();
            //String sql="{call sp_alta_usuario (?,?,?,?)}";
            PreparedStatement prep = cn.prepareStatement("INSERT INTO Ventas(IdVenta, IdCliente, FechaV, Total) VALUES(?, ?, ?, ?)");
            prep.setString(1, id);
            prep.setString(2, idC);
            prep.setString(3, fechaV);
            prep.setDouble(4, total);
            band=1;
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    public int EliminarVenta(String idV){
        int band =0;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("Delete from Ventas where idVenta=?");
            prep.setString(1, idV);
            band=1;
            
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    public int EditarVenta(String idV, String idC){
        int band =0;
        String fechaV;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        fechaV = dateFormat.format(date);
        System.out.println(fechaV);
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("update Ventas set idCliente=?, FechaV=?, where IdVenta=?");
            
            prep.setString(2, idC);
            prep.setString(3, fechaV);
            prep.setString(4, idV);
            band=1;
           
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    public Object[] Buscar(String IdV){
        IdVenta = IdV;
        Object[] datos = new Object[4];
        datos[0] = "";
         try {
            cn = con.conectar();
            //Sentencia con parámetros
             String sql="select * from Ventas where IdVenta = ?";
             PreparedStatement sentencia= cn.prepareStatement(sql);
             sentencia.setString(1,IdVenta);
             ResultSet rs = sentencia.executeQuery();//Guarda el resultado de la consulta
             while(rs.next())
             {
               // datos=new Object[4];
              
                for(int i=0;i<4;i++){
                    datos[i]=rs.getString(i+1);                  
                }
              } 
         }catch (Exception e) {
            System.out.println(e.getMessage());
        }
      return datos;
    }
}

