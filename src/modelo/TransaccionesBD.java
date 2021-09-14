package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class TransaccionesBD extends Conexion {

    PreparedStatement ps;
    ResultSet rs;
    Estudiante estudiante;
    int indice = -1;
    int[] est = null;

    public boolean insertar(Estudiante estudiante) {
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("insert into estudiante (nombreE,nota,sexo,materia) values(?,?,?,?)");
            ps.setString(1, estudiante.getNombre());
            ps.setDouble(2, estudiante.getNota());
            ps.setString(3, estudiante.getGenero());
            ps.setString(4, estudiante.getMateria());
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }
    }

    public boolean modificar(Estudiante estudiante) {
        try 
        {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("update estudiante set nombreE=?,nota=?,sexo=?,materia=? where codigoE=?");
            ps.setString(1, estudiante.getNombre());
            ps.setDouble(2, estudiante.getNota());
            ps.setString(3, estudiante.getGenero());
            ps.setString(4, estudiante.getMateria());
            ps.setInt(5, estudiante.getCodigo());
            int resultado = ps.executeUpdate();
            if (resultado > 0) 
            {
                return true;

            } else 
            {
                return false;
            }
        } catch (Exception ex) 
        {
            System.out.println("Error revisar " + ex);
            return false;
        }
    }

    public boolean eliminar(Estudiante estudiante) {
        try 
        {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("delete from estudiante where codigoE=?");
            ps.setInt(1, estudiante.getCodigo());
            int resultado = ps.executeUpdate();
            if (resultado > 0) 
            {
                return true;
            } else 
            {
                return false;
            }
        } catch (Exception ex) 
        {
            System.out.println("Error revisar " + ex);
            return false;
        }
    }

    public boolean siguiente(Estudiante estudiante) {
        try 
        {
            int ind = 0;
            int cont = 0; 
            int auxiliar;
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiante ");
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                cont++;
            }
            est = new int[cont];
            ps = conexion.prepareStatement("select * from estudiante ");
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                est[ind] = rs.getInt("codigoE");
                ind++;
            }
            auxiliar = cont - 1;

            if (indice >= auxiliar) 
            {
                JOptionPane.showMessageDialog(null, "Último Estudiante");
                indice = auxiliar - 1;
            }
            indice++;
            ps = conexion.prepareStatement("select * from estudiante where codigoE=?");
            ps.setInt(1, Integer.parseInt(String.valueOf(est[indice])));
            rs = ps.executeQuery();
            if (rs.next()) 
            {
                estudiante.setNombre(rs.getString("nombreE"));
                estudiante.setGenero(rs.getString("sexo"));
                estudiante.setMateria(rs.getString("materia"));
                estudiante.setNota(rs.getDouble("nota"));
                estudiante.setCodigo(est[indice]);
                return true;
            } else 
            {
                return false;
            }
        } catch (Exception ex) 
        {
            System.out.println("Error " + ex);
            return false;
        }
    }

    
    
    public boolean anterior(Estudiante estudiante) {
        try 
        {
            int cont = 0;
            int ind = 0;
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiante ");
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                ind++;
            }
            est = new int[ind];
            ps = conexion.prepareStatement("select * from estudiante ");
            rs = ps.executeQuery();
            while (rs.next()) 
            {
                est[cont] = rs.getInt("codigoE");
                cont++;
            }
            if (indice <= 0) 
            {
                indice = 1;
                JOptionPane.showMessageDialog(null, "Primer Estudiante");
            }
            indice--;

            ps = conexion.prepareStatement("select * from estudiante where codigoE=?");
            ps.setInt(1, Integer.parseInt(String.valueOf(est[indice])));
            rs = ps.executeQuery();
            if (rs.next()) 
            {
                estudiante.setNombre(rs.getString("nombreE"));
                estudiante.setGenero(rs.getString("sexo"));
                estudiante.setMateria(rs.getString("materia"));
                estudiante.setNota(rs.getDouble("nota"));
                estudiante.setCodigo(est[indice]);
                return true;
            } else 
            {
                return false;
            }
        } catch (Exception ex) 
        {
            System.out.println("Error " + ex);
            return false;
        }
    }
}
