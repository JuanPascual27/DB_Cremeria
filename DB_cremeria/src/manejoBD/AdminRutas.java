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
public class AdminRutas {
    Connection cn;
    Conexion con=new Conexion();
    private String IdRuta;
    private String IdVendedor;
    private String DetalleRuta;
    
     public AdminRutas(){
        
        cn= con.conectar();
    }
     
    public void LLenarDatos(DefaultTableModel modelo){
        try {
            String sql="select * from Rutas";
            CallableStatement cmd=cn.prepareCall(sql);
            ResultSet rs= cmd.executeQuery();
           
            while(rs.next()){
                Object[] datos=new Object[3];
              
                for(int i=0;i<3;i++){
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
    
    public int AgregarRuta (String IdR, String IdV, String DR){
        int band =0;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("INSERT INTO Rutas(IdRuta, IdVendedor, DetalleRuta) VALUES(?, ?, ?)");
            prep.setString(1, IdR);
            prep.setString(2, IdV);
            prep.setString(3, DR);
            band=1;
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    
    public int EliminarRuta(String IdR){
        int band =0;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("Delete from Rutas where IdRuta=?");
            prep.setString(1, IdR);
            band=1;
            
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    public int EditarRuta(String IdR, String IdV, String DR){
        int band =0;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("update Rutas set IdVendedor=?, DetalleRuta=? where IdRuta=?");
            
            prep.setString(1, IdV);
            prep.setString(2, DR);
            prep.setString(3, IdR);
            band=1;
           
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    
    public Object[] Buscar(String IdR){
        IdRuta = IdR;
        Object[] datos = new Object[3];
        datos[0] = "";
         try {
            cn = con.conectar();
            //Sentencia con parámetros
             String sql="select * from Rutas where IdRuta = ?";
             PreparedStatement sentencia= cn.prepareStatement(sql);
             sentencia.setString(1,IdRuta);
             ResultSet rs = sentencia.executeQuery();//Guarda el resultado de la consulta
             while(rs.next())
             {
               // datos=new Object[4];
              
                for(int i=0;i<3;i++){
                    datos[i]=rs.getString(i+1);                  
                }
              } 
         }catch (Exception e) {
            System.out.println(e.getMessage());
        }
      return datos;
    }
}
