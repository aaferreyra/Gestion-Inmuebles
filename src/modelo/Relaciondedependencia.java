package modelo;
// Generated 26/11/2021 13:09:34 by Hibernate Tools 4.3.1


// Generated 26/11/2021 13:09:34 by Hibernate Tools 4.3.1



/**
 * Relaciondedependecia generated by hbm2java
 */
public class Relaciondedependencia  extends Tipotrabajador implements java.io.Serializable {

     private byte[] recibo;
     private String url1;
    public Relaciondedependencia() {
    }

	
   
    public Relaciondedependencia(long id, byte[] recibo, int tipo, String url1) {
       super(id, tipo);
       this.recibo = recibo;
       this.url1 = url1;
    }

    public byte[] getRecibo() {
        return recibo;
    }

    public void setRecibo(byte[] recibo) {
        this.recibo = recibo;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }




}


