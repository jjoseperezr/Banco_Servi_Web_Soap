/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Juan Jose
 */
@WebService(serviceName = "consultar_solicitudes")
public class solicitudes_ws {

    private int id;
    private String nombre;
    private String correo;
    private String fechanacimiento;
    private String estadosolicitud;
    private String origentramite;
    private String cedula;
    
    @WebMethod
 
    public String insertarSolicitud(@WebParam(name="nombre")String nombre,@WebParam(name="correo")String correo,@WebParam(name="fechanacimiento")String fechanacimiento,
                                    @WebParam(name="estadosolicitud")String estadosolicitud,@WebParam(name="origensolicitud")String origentramite,@WebParam(name="cedula")String cedula){
  	
        String conexionBD="jdbc:mysql://ec2-184-72-146-193.compute-1.amazonaws.com:3306/user";
        Connection conexion=null;
        String funciono="No se inserto la solicitud";
        String con;
        try{

            Class.forName("com.mysql.jdbc.Driver");//el driver de mysql
            conexion=DriverManager.getConnection(conexionBD, "root","UniRemington2020");//conexion a la base de datos
            Statement s = conexion.createStatement();
          
            con = "INSERT INTO solicitudes (id, nombre, correo, fechanacimiento, estadosolicitud, origentramite, cedula) VALUES (NULL,'"+ nombre +"','"+ correo +"','" + fechanacimiento +"','"+ estadosolicitud +"','"+ origentramite +"','"+ cedula +"')"; 
            s.executeUpdate(con);
            funciono="Se inserto el usuario correctamente";
        }

        catch(Exception e){
            System.out.println("No se ha completado la petición..."+e);
        }
    return funciono;
    }
    
    @WebMethod
 
    public String buscarSolicitud(@WebParam(name="Id")int id){

        String conexionBD="jdbc:mysql://ec2-184-72-146-193.compute-1.amazonaws.com:3306/user";
        Connection conexion=null;
        String funciono="No se encontro el usuario";
        String con;
        ResultSet rs;

        try{

            Class.forName("com.mysql.jdbc.Driver");//el driver de mysql
            conexion=DriverManager.getConnection(conexionBD, "root","UniRemington2020");//conexion a la base de datos
            Statement s = conexion.createStatement();

            con="SELECT * FROM solicitudes where id = '" + id + "'" ;

            rs = s.executeQuery (con); {

                while (rs.next()) {
                    nombre=rs.getString("nombre");
                    correo=rs.getString("correo");
                    fechanacimiento=rs.getString("fechanacimiento");
                    estadosolicitud=rs.getString("estadosolicitud");
                    origentramite=rs.getString("origentramite");
                    cedula=rs.getString("cedula");

                    funciono="Se encontraron los siguientes datos: nombre="+nombre+",correo="+correo+",fecha de nacimiento="+fechanacimiento+",estado solicitud="+estadosolicitud+",origen tramite="+origentramite+", cedula="+cedula;
                    break;
                }
            }
        }

        catch(Exception e){
            System.out.println("No se ha completado la petición..."+e);
        }

        return funciono;
    }

@WebMethod

    public String actualizarSolicitud(@WebParam(name="Id")int id, @WebParam(name="estadosolicitud")String estadosolicitud){

        String conexionBD="jdbc:mysql://ec2-184-72-146-193.compute-1.amazonaws.com:3306/user";
        Connection conexion=null;
        String funciono="No se pudo actualizar";
        String con;

        try {

            Class.forName("com.mysql.jdbc.Driver");//el driver de mysql
            conexion=DriverManager.getConnection(conexionBD, "root","UniRemington2020");//conexion a la base de datos
            Statement s = conexion.createStatement();
            
            con= "update solicitudes set estadosolicitud='"+estadosolicitud+"' where id='"+id+"'";
            s.executeUpdate(con);
            funciono="Se actualizo correctamente la solicitud con el id "+id;
        }

        catch(Exception e){

            System.out.println("No se ha completado la petición..."+e);
        }
        return funciono;
    }

@WebMethod
 
    public String eliminarSolicitud(@WebParam(name="cedula")String cedula){

        String conexionBD="jdbc:mysql://ec2-184-72-146-193.compute-1.amazonaws.com:3306/user";
        Connection conexion=null;
        String funciono="No se pudo eliminar";
        String con;

        try{

            // JOptionPane.showMessageDialog(null,"entro");
            Class.forName("com.mysql.jdbc.Driver");//el driver de mysql
            conexion=DriverManager.getConnection(conexionBD, "root","UniRemington2020");//conexion a la base de datos
            Statement s = conexion.createStatement();

            // JOptionPane.showMessageDialog(null,no+" "+ap);
            con= "delete from solicitudes where cedula='"+cedula+"'";
            s.executeUpdate(con);
            funciono="Se elimino la solicitud "+cedula;
        }
        catch(Exception e){
            System.out.println("No se ha completado la petición..."+e);
        }
        return funciono;
    }
}
