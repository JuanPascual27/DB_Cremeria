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
public class AdminClientes {
    Connection cn;
    Conexion con=new Conexion();
    private String IdCliente;
    private String IdRuta;
    private String NombreCliente;
    private String DireccionCliente;
    private String TelefonoCliente;
    private float Adeudos;
    
    
    public AdminClientes(){
        
        cn= con.conectar();
    }  
    
    public void LLenarDatos(DefaultTableModel modelo){
        try {
            String sql="select * from Clientes";
            CallableStatement cmd=cn.prepareCall(sql);
            ResultSet rs= cmd.executeQuery();
           
            while(rs.next()){
                Object[] datos=new Object[6];
              
                for(int i=0;i<6;i++){
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
    
    public int AgregarCliente(String IdC, String IdR, String Nom, String Dir, String Tel, float Ade){
        int band =0;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("INSERT INTO Clientes(IdCliente, IdRuta, NombreCliente, DireccionCliente, TelefonoCliente, Adeudos) VALUES(?, ?, ?, ?, ?, ?)");
            prep.setString(1, IdC);
            prep.setString(2, IdR);
            prep.setString(3, Nom);
            prep.setString(4, Dir);
            prep.setString(5, Tel);
            prep.setFloat(6, Ade);
            band=1;
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    
    public int EliminarCliente(String IdC){
        int band =0;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("Delete from Clientes where IdCliente=?");
            prep.setString(1, IdC);
            band=1;
            
            prep.executeUpdate();
            cn.close();          
        } catch (Exception e) {
            band=0;
            System.out.println(e.getMessage());
        }  
        return band;
    }
    
    
    public int EditarCliente(String IdC, String IdR, String Nom, String Dir, String Tel, float Ade){
        int band =0;
        try {
            cn = con.conectar();
            PreparedStatement prep = cn.prepareStatement("update Clientes set IdRuta=?, NombreCliente=?, DireccionCliente=?, TelefonoCLiente=?, Adeudos=? where IdCliente=?");
            
            prep.setString(1, IdR);
            prep.setString(2, Nom);
            prep.setString(3, Dir);
            prep.setString(4, Tel);
            prep.setFloat(5, Ade);
            prep.setString(6, IdC);
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
        NombreCliente = nom;
        Object[] datos = new Object[6];
        datos[0] = "";
         try {
            cn = con.conectar();
            //Sentencia con parámetros
             String sql="select * from Clientes where NombreCliente = ?";
             PreparedStatement sentencia= cn.prepareStatement(sql);
             sentencia.setString(1,NombreCliente);
             ResultSet rs = sentencia.executeQuery();//Guarda el resultado de la consulta
             while(rs.next())
             {
               // datos=new Object[4];
              
                for(int i=0;i<6;i++){
                    datos[i]=rs.getString(i+1);                  
                }
              } 
         }catch (Exception e) {
            System.out.println(e.getMessage());
        }
      return datos;
    }
}
