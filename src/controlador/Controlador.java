
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Estudiante;
import modelo.TransaccionesBD;
import vista.VistaEstudiante;


public class Controlador implements ActionListener {
    VistaEstudiante vista;
    TransaccionesBD modelo;
    Estudiante estudiante;

    public Controlador(VistaEstudiante vista, TransaccionesBD modelo, Estudiante estudiante) {
        this.vista = vista;
        this.modelo = modelo;
        this.estudiante = estudiante;
        vista.btnInsertar.addActionListener(this);
        
    }
    public void iniciar()
    {
        vista.setTitle("Estudiante");
        vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnInsertar)
        {
            estudiante.setNombre(vista.txtNombre.getText());
            estudiante.setNota(Double.parseDouble(vista.txtNota.getText()));
            estudiante.setGenero(vista.cbGenero.getSelectedItem().toString());
            estudiante.setMateria(vista.cbMateria.getSelectedItem().toString());
            if(modelo.insertar(estudiante)){
                JOptionPane.showMessageDialog(null,"Registro insertado correctamente");
            }
            else{
                JOptionPane.showMessageDialog(null,"No se inserto el registro");
            }
        }
    }
    
}
