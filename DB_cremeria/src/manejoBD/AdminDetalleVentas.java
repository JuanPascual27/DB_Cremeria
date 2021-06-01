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
 * @author joant
 */
public class AdminDetalleVentas {
    Connection cn;
    Conexion con=new Conexion();
    private String IdVenta;
    private String IdProducto;
    private float CantidadPV;
    private float subTotalV;
    private float CostoV;
    static String Id = "0";
   
    public AdminDetalleVentas(String IdV){
        Id = IdV;
        cn= con.conectar();
    } 
    
    public void LLenarDatos(String sq, DefaultTableModel modelo, int rows){
        try {
            //String sql="select * from DetallesVentas ";//where IdVenta = idV
            String sql=sq;
            CallableStatement cmd=cn.prepareCall(sql);
            ResultSet rs= cmd.executeQuery();
           
            while(rs.next()){
                Object[] datos=new Object[5];
              
                for(int i=0;i<rows;i++){
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
    public int AgregarDVenta (String idV, String idP, float can, float cost){
        int band =0;
        float subT = 0;
        subT = can * cost;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("INSERT INTO DetallesVentas(IdVenta, IdProducto, CantidadPV, CostoV, SubTotalV) VALUES(?, ?, ?, ?, ?)");
            prep.setString(1, idV);
            prep.setString(2, idP);
            prep.setDouble(3, can);
            prep.setDouble(4, cost);
            prep.setDouble(5, subT);
            band=1;
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    public int EliminarDVenta(String V, String P){
        int band =0;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("Delete from DetallesVentas where IdVenta=? AND IdProducto =?");
            prep.setString(1, V);
            prep.setString(2, P);
            band=1;
            
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    public int EditarDVenta(String idV, String idP, float can, float cost){
        int band =0;
        float subT = 0;
        subT = can * cost;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("update Productos set CantidadPV=?, CostoV=?,SubTotalV=? where IdVenta=? and IdProducto=?");
            
            prep.setDouble(1, can);
            prep.setDouble(2, cost);
            prep.setString(3, idV);
            prep.setString(4, idP);
            prep.setDouble(5, subT);
            band=1;
           
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    public Object[] Buscar(String idP){
        IdProducto = idP;
        Object[] datos = new Object[5];
        datos[0] = "";
         try {
            cn = con.conectar();
            //Sentencia con parámetros
             String sql="select * from DetallesVentas where IdProductos = ?";
             PreparedStatement sentencia= cn.prepareStatement(sql);
             sentencia.setString(1,IdProducto);
             ResultSet rs = sentencia.executeQuery();//Guarda el resultado de la consulta
             while(rs.next())
             {
               // datos=new Object[4];
              
                for(int i=0;i<5;i++){
                    datos[i]=rs.getString(i+1);                  
                }
              } 
         }catch (Exception e) {
            System.out.println(e.getMessage());
        }
      return datos;
    }
    
    
}
