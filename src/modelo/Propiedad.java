package modelo;
// Generated 26/11/2021 13:09:34 by Hibernate Tools 4.3.1


// Generated 26/11/2021 13:09:34 by Hibernate Tools 4.3.1



/**
 * Propiedad generated by hbm2java
 */
public class Propiedad  extends Inmueble implements java.io.Serializable {
     private Integer cantidadhabitaciones;
     private Integer cantidadbanos;
     private Boolean cochera;

    public Propiedad() {
    }

    public Propiedad(long id, String ubicacion, String tamano, String localidad, Integer cantidadhabitaciones, Integer cantidadbanos, Boolean cochera, int tipo) {
       super(id, ubicacion, tamano, localidad, tipo);
       this.cantidadhabitaciones = cantidadhabitaciones;
       this.cantidadbanos = cantidadbanos;
       this.cochera = cochera;
    }
   
    public Integer getCantidadhabitaciones() {
        return this.cantidadhabitaciones;
    }
    
    public void setCantidadhabitaciones(Integer cantidadhabitaciones) {
        this.cantidadhabitaciones = cantidadhabitaciones;
    }
    public Integer getCantidadbanos() {
        return this.cantidadbanos;
    }
    
    public void setCantidadbanos(Integer cantidadbanos) {
        this.cantidadbanos = cantidadbanos;
    }
    public Boolean getCochera() {
        return this.cochera;
    }
    
    public void setCochera(Boolean cochera) {
        this.cochera = cochera;
    }




}

