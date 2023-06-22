
package com.mycompany.peluqueriacanina.logica;

import com.mycompany.peluqueriacanina.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void guardar(String nombreMasco, String raza, String color, String observaciones, 
            String nombreDuenio, String celDuenio, String alergico, String atencionEspecial) {
        
        //Creamos el dueño y asignamos sus valores
        Duenio duenio = new Duenio ();
        duenio.setNombre(nombreDuenio);
        duenio.setCelDuenio(celDuenio);
        
        //Creamos la mascota y asignamos sus valores
        Mascota masco = new Mascota();
        masco.setNombre_mascota(nombreMasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setObservaciones(observaciones);
        masco.setAlergico(alergico);
        masco.setAtencionEspecial(atencionEspecial);
        masco.setUnDuenio(duenio); //automaticamente nos reconoce el dueño porque ya esta seteado arriba y esta mapeado
        //id num cliente no lo ponemos porque elegimos que se coloquen automaticamente
        
        //Llamamos a la persistencia de la logica y que se encargue a partir de estos dos objetos (masco y dueño) de crear a ambos en la BD y asociarlos
        controlPersis.guardar(duenio,masco);
        
    }

    public List<Mascota> traerMascotas() {
        
        /*lo que retornamos en la controladora de la logica lo va a retornar aca, y este lo va a retornar a quien este
        llamando este metodo, que en este caso es ver datos, y este ver datos lo va a guardar en la lista de mascotas
        */
        return controlPersis.traerMascotas();
        
    }

    public void borrarMascota(int num_cliente) {
        controlPersis.borrarMascota(num_cliente);
    }

    public Mascota traerMascota(int num_cliente) {
       return controlPersis.traerMascota(num_cliente);
    }

    public void modificarMascota(Mascota masco, String nombreMasco, String raza, String color,
            String observaciones, String nombreDuenio, String celDuenio, String alergico, String atencionEspecial) {
        
        //traigo valores de mascota
        masco.setNombre_mascota(nombreMasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setObservaciones(observaciones);
        masco.setAlergico(alergico);
        masco.setAtencionEspecial(atencionEspecial);
        
        //modifico mascota
        controlPersis.modificarMascota(masco);
        
        //identifico al dueño y seteo los valores del dueño
        Duenio dueno = this.buscarDuenio(masco.getUnDuenio().getId_duenio());
        dueno.setNombre(nombreDuenio);
        dueno.setCelDuenio(celDuenio); 
        
        //llamar a modificar dueño
        this.modificarDuenio(dueno);
        
    }

    private Duenio buscarDuenio(int id_duenio) {
        return controlPersis.traerDuenio(id_duenio);
    }

    private void modificarDuenio(Duenio dueno) {
        controlPersis.modificarDuenio(dueno);
    }
    
}
