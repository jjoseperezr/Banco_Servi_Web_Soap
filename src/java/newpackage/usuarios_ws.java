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


@WebService(serviceName = "consultar_usuario")
public class usuarios_ws {
    
    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String address;
    
    @WebMethod
 
    public String insertarUsuario(@WebParam(name="nombre")String name,@WebParam(name="correo")String email,@WebParam(name="contraseña")String password,
                               @WebParam(name="genero")String gender,@WebParam(name="direccion")String address){
 
        String conexionBD="jdbc:mysql://ec2-184-72-146-193.compute-1.amazonaws.com:3306/user";
        Connection conexion=null;
        String funciono="No se inserto el usuario";
        String con;
        try{

            Class.forName("com.mysql.jdbc.Driver");//el driver de mysql
            conexion=DriverManager.getConnection(conexionBD, "root","UniRemington2020");//conexion a la base de datos
            Statement s = conexion.createStatement();

            con = "INSERT INTO users (id, name, email, password, gender, address) VALUES (NULL,'"+ name +"','"+ email +"','" + password +"','"+ gender +"','"+ address +"')"; 
            s.executeUpdate(con);
            funciono="Se inserto el usuario correctamente";
        }

        catch(Exception e){
            System.out.println("No se ha completado la petición..."+e);
        }
    return funciono;
    }
    
    @WebMethod
 
    public String buscarUsuario(@WebParam(name="nombre")String name){

        String conexionBD="jdbc:mysql://ec2-184-72-146-193.compute-1.amazonaws.com:3306/user";
        Connection conexion=null;
        String funciono="No se encontro el usuario";
        String con;
        ResultSet rs;

        try{

            Class.forName("com.mysql.jdbc.Driver");//el driver de mysql
            conexion=DriverManager.getConnection(conexionBD, "root","UniRemington2020");//conexion a la base de datos
            Statement s = conexion.createStatement();

            con="SELECT * FROM users where name = '" + name + "'" ;

            rs = s.executeQuery (con); {

                while (rs.next()) {
                    name=rs.getString("name");
                    email=rs.getString("email");
                    password=rs.getString("password");
                    gender=rs.getString("gender");
                    address=rs.getString("address");

                    funciono="Se encontraron los siguientes datos: nombre="+name+",correo="+email+",contraseña="+password+",genero="+gender+",direccion="+address;

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
 				 	
    public String actualizarUsuario(@WebParam(name="nombre")String name, @WebParam(name="correo")String email, @WebParam(name="contraseña")String password, 
                                    @WebParam(name="genero")String gender, @WebParam(name="direccion")String address){

        String conexionBD="jdbc:mysql://ec2-184-72-146-193.compute-1.amazonaws.com:3306/user";
        Connection conexion=null;
        String funciono="No se pudo actualizar";
        String con;

        try {

            Class.forName("com.mysql.jdbc.Driver");//el driver de mysql
            conexion=DriverManager.getConnection(conexionBD, "root","UniRemington2020");//conexion a la base de datos
            Statement s = conexion.createStatement();

            con= "update users set name='"+name+"',email='"+email+"',password='"+password+"',gender='"+gender+"',address='"+address+"' where name='"+name+"'";
            s.executeUpdate(con);
            funciono="Se actualizo correctamente el usuario "+name;
        }

        catch(Exception e){

            System.out.println("No se ha completado la petición..."+e);
        }
        return funciono;
    }

@WebMethod
 
    public String eliminarUsuario(@WebParam(name="nombre")String name){

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
            con= "delete from users where name='"+name+"'";
            s.executeUpdate(con);
            funciono="Se elimino el usuario "+name;
        }
        catch(Exception e){
            System.out.println("No se ha completado la petición..."+e);
        }
        return funciono;
    }
    
    
    
}
