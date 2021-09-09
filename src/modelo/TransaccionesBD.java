
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class TransaccionesBD extends Conexion {
    PreparedStatement ps;
    ResultSet rs;
    Estudiante estudiante;
    
    public boolean insertar(Estudiante estudiante)
    {
       try{
        Connection conexion = getConnection();
        ps = conexion.prepareStatement("insert into estudiante (nombreE,nota,sexo,materia) values(?,?,?,?)"); 
        ps.setString(1, estudiante.getNombre());
        ps.setDouble(2, estudiante.getNota());
        ps.setString(3, estudiante.getGenero());
        ps.setString(4, estudiante.getMateria());
        int resultado = ps.executeUpdate();
        if(resultado>0){
            return true;
        }
        else{
            return false;
        }
       }catch(Exception ex){
           System.err.println("Error revisar "+ex);
           return false;
       }
                     
        
    }
    public boolean modificar(Estudiante estudiante)
    {
        return true;
    }
    public boolean eliminar(Estudiante estudiante)
    {
        return true;
    }
    public boolean siguiente(Estudiante estudiante)
    {
        return true;
    }
    public boolean anterior(Estudiante estudiante)
    {
        return true;
    }
}
