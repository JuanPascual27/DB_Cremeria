/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoBD;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class AdminProductos {
    Connection cn;
    Conexion con=new Conexion();
    private String IdProducto;
    private String NombreProducto;
    private float Precio;
    private float Cantidad;
    private float stock;
    private String FechaCaducidad;
      
    public AdminProductos(){
        
        cn= con.conectar();
    }  

    public void LLenarDatos(DefaultTableModel modelo){
        try {
            String sql="select * from Productos";
            CallableStatement cmd=cn.prepareCall(sql);
            ResultSet rs= cmd.executeQuery();
           
            while(rs.next()){
                Object[] datos=new Object[6];
              
                for(int i=0;i<6;i++){
                    if (i >= 4)
                    {
                        datos[i]=rs.getString(i+2);
                    }
                    else
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

    public int AgregarProd (String id, String nom, float can, float prec, float stock, String fechaC){
        int band =0;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("INSERT INTO Productos(IdProducto, NombreProducto, CantidadP, Precio, Detalles, Stock, FechaCaducidad) VALUES(?, ?, ?, ?, ?, ?, ?)");
            prep.setString(1, id);
            prep.setString(2, nom);
            prep.setFloat(3, can);
            prep.setDouble(4, prec);
            prep.setString(5, " ");
            prep.setFloat(6, stock);
            prep.setString(7, fechaC);
            band=1;
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    public int EliminarProducto(String nom){
        int band =0;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("Delete from Productos where NombreProducto=?");
            prep.setString(1, nom);
            band=1;
            
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    public int EditarProducto(String id, String nom, float can, float prec, float stock, String fechaC){
        int band =0;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("update Productos set NombreProducto=?, CantidadP=?, Precio=?, Detalles=?, Stock=?, FechaCaducidad=? where IdProducto=?");
            
            prep.setString(1, nom);
            prep.setFloat(2, can);
            prep.setDouble(3, prec);
            prep.setString(4, " ");
            prep.setFloat(5, stock);
            prep.setString(6, fechaC);
            prep.setString(7, id);
            band=1;
           
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
  
    public Object[] Buscar(String nom){
        NombreProducto = nom;
        Object[] datos = new Object[5];
        datos[0] = "";
         try {
            cn = con.conectar();
            //Sentencia con parámetros
             String sql="select * from productos where NombreProducto = ?";
             PreparedStatement sentencia= cn.prepareStatement(sql);
             sentencia.setString(1,NombreProducto);
             ResultSet rs = sentencia.executeQuery();//Guarda el resultado de la consulta
             while(rs.next())
             {
               // datos=new Object[4];
              
                for(int i=0;i<6;i++){
                    if(i>=4){
                        datos[i]=rs.getString(i+2);
                    }
                    else
                    datos[i]=rs.getString(i+1);                  
                }
              } 
         }catch (Exception e) {
            System.out.println(e.getMessage());
        }
      return datos;
    }
}