/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import controlador.Controlador;
import java.util.Arrays;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.hibernate.SessionFactory;
/**
 *
 * @author Aaron
 */
public class Aranceles implements JRDataSource{
    private final Object[][] arancels;
    private int i;
    public Aranceles(Controlador control, String idarancel, String idlocatario){
        i=-1;
        arancels = control.reporteArancel(idarancel, idlocatario);
        System.out.println(Arrays.toString(arancels));
    }
    @Override
    public boolean next() throws JRException {
        i++;
        return (i < arancels.length) ;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        String nombre = jrf.getName();
        switch(nombre){
            case "idarancel":
                valor=arancels[i][0];
            break;  
            case "nombre":
                valor=arancels[i][1];
            break;
            case "dni":
                valor=arancels[i][2];
            break;
            case "primerfecha":
                valor=arancels[i][3];
            break;
            case "segundafecha":
                valor=arancels[i][4];
            break;
            case "montoarancel":
                valor=arancels[i][5];
            break;
            case "montosegunda":
                valor=arancels[i][6];
            break;
        }
        return valor;
    }

    public static JRDataSource getDataSource(Controlador control, String idarancel, String idlocatario) {
        return new Aranceles(control, idarancel, idlocatario);
    }

    public void setI(int i) {
        this.i = i;
    }
    
}
