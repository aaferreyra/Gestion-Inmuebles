package modelo;
// Generated 26/11/2021 13:09:34 by Hibernate Tools 4.3.1


// Generated 26/11/2021 13:09:34 by Hibernate Tools 4.3.1



/**
 * Trabajadorindependiente generated by hbm2java
 */
public class Trabajadorindependiente  extends Tipotrabajador implements java.io.Serializable {


     private byte[] comprobante1;
     private byte[] comprobante2;
     private byte[] comprobante3;
     private String url1;
     private String url2;
     private String url3;

    public Trabajadorindependiente() {
    }

	
    
    public Trabajadorindependiente(long id, byte[] comprobante1, byte[] comprobante2, byte[] comprobante3, int tipo, String url1, String url2, String url3) {
       super(id, tipo);
       this.comprobante1 = comprobante1;
       this.comprobante2 = comprobante2;
       this.comprobante3 = comprobante3;
       this.url1 = url1;
       this.url2 = url2;
       this.url3 = url3;
    }
   
    
    public byte[] getComprobante1() {
        return this.comprobante1;
    }
    
    public void setComprobante1(byte[] comprobante1) {
        this.comprobante1 = comprobante1;
    }
    public byte[] getComprobante2() {
        return this.comprobante2;
    }
    
    public void setComprobante2(byte[] comprobante2) {
        this.comprobante2 = comprobante2;
    }
    public byte[] getComprobante3() {
        return this.comprobante3;
    }
    
    public void setComprobante3(byte[] comprobante3) {
        this.comprobante3 = comprobante3;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }




}

