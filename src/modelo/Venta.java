package modelo;
// Generated 26/11/2021 13:09:34 by Hibernate Tools 4.3.1



import java.util.Date;
import java.util.List;


/**
 * Venta generated by hbm2java
 */
public class Venta  extends Contrato implements java.io.Serializable {

     

    public Venta() {
    }

    
    public Venta(Long id, Double recargofijo, Double valorbaserecargo, Date iniciocontrato, Date fincontrato, Long idinmueble, Long dnilocatario, Long dnilocador, Double montocomision, List<Arancel> arancels, Integer cantaranceles, Integer tipocontrato) {
       super(id, recargofijo, valorbaserecargo, iniciocontrato, fincontrato, idinmueble, dnilocatario, dnilocador, montocomision, arancels, cantaranceles, tipocontrato);
    }
   
  


}

