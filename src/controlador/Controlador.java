/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import DAO.*;
import MysqlDAO.*;
import controlador.*;
import static controlador.TPFINAL.conectar;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import modelo.*;
import vista.*;
import org.hibernate.SessionFactory;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Aaron
 */
public class Controlador {
    	private final LocadorDAO LocadorDAO;
        private final LocatarioDAO LocatarioDAO;
	private final RelacionDeDependenciaDAO RelacionDAO;
        private final TrabajadorIndependienteDAO TrabajadorDAO;
        private final GaranteDAO GaranteDAO;
        private final InmuebleDAO InmuebleDAO;
        private final MySQLLocalComercialDAO LocalComercialDAO;
        private final MySQLDepartamentoDAO DepartamentoDAO;
        private final MySQLCasaDAO CasaDAO;
        private final MySQLTerrenoDAO TerrenoDAO;
        private final MySQLContratoDAO ContratoDAO;
        private final MySQLArancelDAO ArancelDAO;
        private final MySQLServicioDAO ServicioDAO;
        private final MySQLVentaDAO VentaDAO;
        private final MySQLAlquilerDAO AlquilerDAO;
        private final MySQLPersonaDAO PersonaDAO;
	public Controlador(SessionFactory sessionFactory) {
		this.LocadorDAO = new MySQLLocadorDAO(sessionFactory);
                this.LocatarioDAO = new MySQLLocatarioDAO(sessionFactory);
                this.RelacionDAO = new MySQLRelacionDeDependenciaDAO(sessionFactory);
                this.TrabajadorDAO = new MySQLTrabajadorIndependienteDAO(sessionFactory);
                this.GaranteDAO = new MySQLGaranteDAO(sessionFactory);
                this.InmuebleDAO = new MySQLInmuebleDAO(sessionFactory);
                this.LocalComercialDAO = new MySQLLocalComercialDAO(sessionFactory);
                this.DepartamentoDAO = new MySQLDepartamentoDAO(sessionFactory);
                this.CasaDAO = new MySQLCasaDAO(sessionFactory);
                this.TerrenoDAO = new MySQLTerrenoDAO(sessionFactory);
                this.ContratoDAO = new MySQLContratoDAO(sessionFactory);
                this.ArancelDAO = new MySQLArancelDAO(sessionFactory);
                this.ServicioDAO = new MySQLServicioDAO(sessionFactory);
                this.VentaDAO = new MySQLVentaDAO(sessionFactory);
                this.AlquilerDAO = new MySQLAlquilerDAO(sessionFactory);
                this.PersonaDAO = new MySQLPersonaDAO(sessionFactory);
        }
        public Controlador(){
             SessionFactory sessionFactory = conectar();
             this.LocadorDAO = new MySQLLocadorDAO(sessionFactory);
                this.LocatarioDAO = new MySQLLocatarioDAO(sessionFactory);
                this.RelacionDAO = new MySQLRelacionDeDependenciaDAO(sessionFactory);
                this.TrabajadorDAO = new MySQLTrabajadorIndependienteDAO(sessionFactory);
                this.GaranteDAO = new MySQLGaranteDAO(sessionFactory);
                this.InmuebleDAO = new MySQLInmuebleDAO(sessionFactory);
                this.LocalComercialDAO = new MySQLLocalComercialDAO(sessionFactory);
                this.DepartamentoDAO = new MySQLDepartamentoDAO(sessionFactory);
                this.CasaDAO = new MySQLCasaDAO(sessionFactory);
                this.TerrenoDAO = new MySQLTerrenoDAO(sessionFactory);
                this.ContratoDAO = new MySQLContratoDAO(sessionFactory);
                this.ArancelDAO = new MySQLArancelDAO(sessionFactory);
                this.ServicioDAO = new MySQLServicioDAO(sessionFactory);
                this.VentaDAO = new MySQLVentaDAO(sessionFactory);
                this.AlquilerDAO = new MySQLAlquilerDAO(sessionFactory);
                this.PersonaDAO = new MySQLPersonaDAO(sessionFactory);
        }
        /////WEB
        public void loggin(HttpServletResponse response, String usuario, String pass){
            PrintWriter out = null;
            int error=1;
                try {
                    response.setContentType("text/html;charset=UTF-8");
                    out = response.getWriter();
                    List <Locatario> locatario = ObtenerTLocatario();
                    Locatario locatario1 = new Locatario();
                    for(Locatario l: locatario){
                        if(l.getUsuario().equals(usuario) && l.getContrasena().equals(pass)){
                            error=0;
                            response.sendRedirect("menu.jsp?id="+l.getDni());
                        }
                    }
                    if(error==1){
                        System.out.println("noanda");
                        response.sendRedirect("errorlog.jsp");
                    }
                } catch (IOException ex) {
                        
                } finally {
                    out.close();
                    
                }
                
        }
        public List<Arancel> Ordenar(List<Arancel> aranceles){
            Collections.sort(aranceles, new Comparator<Arancel>() {
                @Override
                public int compare(Arancel o1, Arancel o2) {
                    return new Integer(Math.toIntExact(o1.getIdarancel())).compareTo(new Integer(Math.toIntExact(o2.getIdarancel())));
                }
            });
            return aranceles;
        } 
        public Object [][] reporteArancel(String idarancel, String idlocatario){
            List <Arancel> aranceles = VerArancel(idlocatario);
            List <Contrato> contratos = ObtenerTContrato();
            Object [][] arancel= new Object[1][7];
            int i=0;
            for(Arancel a: aranceles){
                if(a.getIdarancel()==Long.parseLong(idarancel) && a.getPagado().equals(false)){
                    arancel[i][0]= a.getIdarancel();
                    for(Contrato c: contratos){
                        if(c.getId().equals(a.getIdcontrato())){
                            Locatario locatario = ObtenerLocatario(c.getDnilocatario());
                            arancel[i][1]= locatario.getNombre();
                            arancel[i][2]= locatario.getDni();
                        }
                    }
                    arancel[i][3]=a.getPrimerfecha();
                    arancel[i][4]=a.getSegundafecha();
                    arancel[i][5]=a.getMontoarancel();
                    arancel[i][6]=a.getMontosegunda();
                    i++;
                    System.out.println("ESTE ES EL ARANCEL: "+a);
                }
            }
            return arancel;
        }
        /////CONTRATO
        public String[][] CrearMatrizArancelVista(List<Arancel> aranceles){
            String matriz [][]=new String [aranceles.size()][6];
            int i=0;
            for(Arancel a : aranceles){
                matriz[i][0]= String.valueOf(a.getIdarancel());
                if(a.getPagado()){
                    matriz[i][1]= "Pagado";
                }else{
                    matriz[i][1]= "Adeuda";
                }
                matriz[i][2]= String.valueOf(a.getMontoarancel());
                matriz[i][3]= String.valueOf(a.getMontosegunda());
                DateFormat fechacompleta = new SimpleDateFormat("dd/MM/yyyy");
                String primerFechaText = fechacompleta.format(a.getPrimerfecha().getTime());
                String segundaFechaText = fechacompleta.format(a.getSegundafecha().getTime());
                matriz[i][4]= primerFechaText;
                matriz[i][5]= segundaFechaText;   
                i++;
            }
            return matriz;
        }
        public String obtenerFecha(){
        Date fecha = new Date();
        DateFormat fecha1 = new SimpleDateFormat("dd/MM/yyyy");
        String fecha2 = fecha1.format(fecha);
            return fecha2;
        }
        public Date obtenerFechaDate() throws ParseException{
        Date fecha = new Date();
        DateFormat fecha1 = new SimpleDateFormat("dd/MM/yyyy");
        String fecha2 = fecha1.format(fecha);
        Date fecha3 = fecha1.parse(fecha2);
            return fecha3;
        }
        public String SumarMes(int a, String inicio) throws ParseException{
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        Date convertido = fecha.parse(inicio);
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(convertido);
        calendario.add(Calendar.MONTH, a);
        String fecha1 = fecha.format(calendario.getTime());
            return fecha1;
        }
        public List<Venta> ObtenerTVenta(){
            return VentaDAO.obtenerTodos();
        }
        public List<Alquiler> ObtenerTAlquiler(){
            return AlquilerDAO.obtenerTodos();
        }
        public List<Contrato> ObtenerTContrato(){
            return ContratoDAO.obtenerTodos();
        }
        public void InsertarContrato(Contrato a) {
		ContratoDAO.insertar(a);
	}
        public void ActualizarContrato(Contrato a){
            ContratoDAO.modificar(a);
        }
        public void EliminarContrato(Contrato a){
            ContratoDAO.eliminar(a);
        }
        public Contrato ObtenerContrato(Long a){
        Contrato lectura = ContratoDAO.obtener(a);
        return lectura;
        }
        public long idContrato(){
            long c = 1;
            List<Contrato> contrato = ObtenerTContrato();
            for (Contrato a: contrato){
                c++;
            }
            return c;
        }
        public int daysBetween(Date d1, Date d2){
             return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
        }
        public void ObtenerListaArancelActu(String idlocatariotext) throws ParseException{
            Long idLocatario = Long.parseLong(idlocatariotext);
            Locatario locatario = ObtenerLocatario(idLocatario);
            List <Contrato> contrato = ObtenerTContrato();
            List <Arancel> aranceles = ObtenerTArancel();
            if(contrato!=null){
                List <Arancel> arancele = new ArrayList<Arancel>();
                for(Contrato c: contrato){
                    if(c.getDnilocatario()==idLocatario){
                        for(Arancel a: aranceles){
                            if(a.getIdcontrato()==c.getId()){
                                arancele.add(a);
                            }
                        }
                    }
                }
                Calendar cal1 = new GregorianCalendar();
                Calendar cal2 = new GregorianCalendar();
                String fecha2 = obtenerFecha();
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                cal2.setTime(sdf.parse(fecha2));
                int diferencia;
                for(Arancel ar: arancele){
                    String fecha1 = sdf.format(ar.getSegundafecha());
                    cal2.setTime(sdf.parse(fecha1));
                    diferencia = daysBetween(cal1.getTime(),cal2.getTime());
                    System.out.println(diferencia);
                    if(diferencia>0){
                        Contrato con = ObtenerContrato(ar.getIdcontrato());
                        ar.setDiasmora(diferencia);
                        Double monto = ar.getMontosegunda();
                        if(con.getValorbaserecargo()!=null){
                            System.out.println(diferencia*con.getValorbaserecargo());
                            ar.setMontosegunda(monto+(diferencia*con.getValorbaserecargo()));
                            ar.setSegundafecha(sdf.parse(fecha2));
                        ActualizarArancel(ar);
                        }
                    }
                    diferencia=0;
                }
           
            }else{
                System.out.println("null");
            }
        }
        public boolean saberFecha(Date fecha) throws ParseException{
            int cont=0;
            Calendar cal1 = new GregorianCalendar();
                Calendar cal2 = new GregorianCalendar();
                String fecha2 = obtenerFecha();
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                cal2.setTime(sdf.parse(fecha2));
                int diferencia;
                    Date fecha1 = sdf.parse(String.valueOf(fecha));
                    cal2.setTime(fecha1);
                    diferencia = daysBetween(cal1.getTime(),cal2.getTime());
                    if(diferencia>0){
                        return true;
                    }else{
                        return false;
                    }
                
        }
        public String[][] ObtenerMatrizArancel(String idlocatariotext){
            Long idLocatario = Long.parseLong(idlocatariotext);
            Locatario locatario = ObtenerLocatario(idLocatario);
            List <Contrato> contrato = ObtenerTContrato();
            List <Arancel> aranceles = ObtenerTArancel();
            if(contrato!=null){
                List <Arancel> arancele = new ArrayList<Arancel>();
                for(Contrato c: contrato){
                    if(c.getDnilocatario()==idLocatario){
                        for(Arancel a: aranceles){
                            if(a.getIdcontrato()==c.getId()){
                                arancele.add(a);
                            }
                        }
                    }
                }
                String matrizpa[][] = CrearMatrizPagarArancel(arancele);
                for(Contrato c: contrato){
                    for(int i=0;arancele.size()>i;i++){
                        Arancel ara = ObtenerArancel(Long.parseLong(matrizpa[i][0]));
                        if(c.getId()==ara.getIdcontrato()){
                            if(c.getTipocontrato()==1){
                                matrizpa[i][6]="Alquiler";
                            }
                            if(c.getTipocontrato()==2){
                                matrizpa[i][6]="Venta";
                            }
                        }
                    }
                }
            return matrizpa;
            }else{
                System.out.println("null");
            }

            return null;
        }
        public String[][] ObtenerMatrizArancelT(String idcontratotext){
            Long idcontrato = Long.parseLong(idcontratotext);
            Venta venta = ObtenerVenta(idcontrato);
            List <Arancel> aranceles = ObtenerTArancel();
            List <Arancel> arancel = new ArrayList<Arancel>();
            for(Arancel a: aranceles){
                if(a.getIdcontrato()==venta.getId()){
                    arancel.add(a);
                }
            }
            int i=0;
            String matrizpa[][] = new String[arancel.size()][6];
            for(Arancel a : arancel){
                matrizpa[i][0]= String.valueOf(a.getIdarancel());
                if(a.getPagado()){
                    matrizpa[i][1]= "Pagado";
                }else{
                    matrizpa[i][1]= "Adeuda";
                }
                matrizpa[i][2]= String.valueOf(a.getMontoarancel());
                matrizpa[i][3]= String.valueOf(a.getMontosegunda());
                DateFormat fechacompleta = new SimpleDateFormat("dd/MM/yyyy");
                String primerFechaText = fechacompleta.format(a.getPrimerfecha().getTime());
                String segundaFechaText = fechacompleta.format(a.getSegundafecha().getTime());
                matrizpa[i][4]= primerFechaText;
                matrizpa[i][5]= segundaFechaText;   
                i++;
                System.out.println(a);
            }
            return matrizpa;
        }
        public String[][] ObtenerMatrizArancelTA(String idcontratotext){
            Long idcontrato = Long.parseLong(idcontratotext);
            Alquiler alquiler = ObtenerAlquiler(idcontrato);
            List <Arancel> aranceles = ObtenerTArancel();
            List <Arancel> arancel = new ArrayList<Arancel>();
            for(Arancel a: aranceles){
                if(a.getIdcontrato()==alquiler.getId()){
                    arancel.add(a);
                }
            }
            int i=0;
            String matrizpa[][] = new String[arancel.size()][6];
            for(Arancel a : arancel){
                matrizpa[i][0]= String.valueOf(a.getIdarancel());
                if(a.getPagado()){
                    matrizpa[i][1]= "Pagado";
                }else{
                    matrizpa[i][1]= "Adeuda";
                }
                matrizpa[i][2]= String.valueOf(a.getMontoarancel());
                matrizpa[i][3]= String.valueOf(a.getMontosegunda());
                DateFormat fechacompleta = new SimpleDateFormat("dd/MM/yyyy");
                String primerFechaText = fechacompleta.format(a.getPrimerfecha().getTime());
                String segundaFechaText = fechacompleta.format(a.getSegundafecha().getTime());
                matrizpa[i][4]= primerFechaText;
                matrizpa[i][5]= segundaFechaText;   
                i++;
                System.out.println(a);
            }
            return matrizpa;
        }
        public String[][] CrearMatrizContrato(){
            Venta venta = new Venta();
            Alquiler alquiler = new Alquiler();
            List<Contrato> contratos = ObtenerTContrato();
            String matriz [][]=new String [contratos.size()][11];
            int i=0;
            for(Contrato a : contratos){
                if(a.getTipocontrato()==1){
                    alquiler = ObtenerAlquiler(a.getId());
                    matriz[i][0]= String.valueOf(alquiler.getId());
                    matriz[i][1]= String.valueOf("Alquiler");
                    matriz[i][2]= String.valueOf(alquiler.getIniciocontrato()); 
                    matriz[i][3]= String.valueOf(alquiler.getFincontrato());
                    matriz[i][4]= String.valueOf(alquiler.getIdinmueble());
                    matriz[i][5]= String.valueOf(alquiler.getDnilocador()); 
                    matriz[i][6]= String.valueOf(alquiler.getDnilocatario());
                    matriz[i][7]= String.valueOf(alquiler.getDnigarante());
                    matriz[i][8]= String.valueOf(alquiler.getRecargofijo());
                    matriz[i][9]= String.valueOf(alquiler.getValorbaserecargo()); 
                    matriz[i][10]= String.valueOf(alquiler.getMontocomision());
                }
                if(a.getTipocontrato()==2){
                    venta = ObtenerVenta(a.getId());
                    matriz[i][0]= String.valueOf(venta.getId());
                    matriz[i][1]= String.valueOf("Venta");
                    matriz[i][2]= String.valueOf(venta.getIniciocontrato()); 
                    matriz[i][3]= String.valueOf(venta.getFincontrato());
                    matriz[i][4]= String.valueOf(venta.getIdinmueble());
                    matriz[i][5]= String.valueOf(venta.getDnilocador()); 
                    matriz[i][6]= String.valueOf(venta.getDnilocatario());
                    matriz[i][7]= String.valueOf("NO TIENE");
                    matriz[i][8]= String.valueOf(venta.getRecargofijo());
                    matriz[i][9]= String.valueOf(venta.getValorbaserecargo()); 
                    matriz[i][10]= String.valueOf(venta.getMontocomision());
                }
                i++;
            }
            return matriz;
        }
        //////ARANCEL
        public List<Arancel> ObtenerTArancel(){
            return ArancelDAO.obtenerTodos();
        }
        public Arancel ObtenerArancel(Long a){
        Arancel lectura = ArancelDAO.obtener(a);
        return lectura;
        }
        public long idArancel(){
            long c = 1;
            List<Arancel> arancel = ObtenerTArancel();
            for (Arancel a: arancel){
                c++;
            }
            return c;
        }
        public void InsertarArancel(Arancel a){
            ArancelDAO.insertar(a);
        }
        public void ActualizarArancel(Arancel a){
            ArancelDAO.modificar(a);
        }
        public void EliminarArancel(Arancel a){
            ArancelDAO.eliminar(a);
        }
        
        public List<Arancel> CrearAranceles(int Mes, String fecha, String Monto, String IdContrato,String Recargo, String Comisiontext, int banderacomision){
                try {
                    DateFormat fechacompleta = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat fechames = new SimpleDateFormat("MM/yyyy");
                    Date convertido = fechacompleta.parse(fecha);
                    Calendar calendario = Calendar.getInstance();
                    calendario.setTime(convertido);
                    calendario.add(Calendar.MONTH, 1);
                    String fecha1 = fechames.format(calendario.getTime());
                    String primerFechaText = "5/"+fecha1;
                    String segundaFechaText = "10/"+fecha1;
                    Date primerFecha = fechacompleta.parse(primerFechaText);
                    Date segundaFecha = fechacompleta.parse(segundaFechaText);
                    Calendar primerFechaM = Calendar.getInstance();
                    primerFechaM.setTime(primerFecha);
                    Calendar segundaFechaM = Calendar.getInstance();
                    segundaFechaM.setTime(segundaFecha);
                    List<Arancel> arancel = new ArrayList<Arancel>();
                    Long id = idArancel();
                    Long idc = Long.parseLong(IdContrato);
                    Double comision = Double.parseDouble(Comisiontext);
                    for(int i=1;i<=Mes;i++){
                        if(banderacomision==3){
                            Double montoarancel = Double.parseDouble(Monto);
                            Double montosegunda = montoarancel+Double.parseDouble(Recargo);
                            Arancel arancelItem = new Arancel(id,idc,null,false,montoarancel,montosegunda,primerFecha,segundaFecha);
                            arancel.add(arancelItem);
                            id++;
                            primerFechaM.add(Calendar.MONTH, 1);
                            segundaFechaM.add(Calendar.MONTH, 1);
                            primerFechaText = fechacompleta.format(primerFechaM.getTime());
                            segundaFechaText = fechacompleta.format(segundaFechaM.getTime());
                            primerFecha = fechacompleta.parse(primerFechaText);
                            segundaFecha = fechacompleta.parse(segundaFechaText);
                        }
                        if(banderacomision==1){
                            Double montoarancel = Double.parseDouble(Monto) + comision;
                            Double montosegunda = montoarancel+Double.parseDouble(Recargo);
                            Arancel arancelItem = new Arancel(id,idc,null,false,montoarancel,montosegunda,primerFecha,segundaFecha);
                            arancel.add(arancelItem);
                            id++;
                            primerFechaM.add(Calendar.MONTH, 1);
                            segundaFechaM.add(Calendar.MONTH, 1);
                            primerFechaText = fechacompleta.format(primerFechaM.getTime());
                            segundaFechaText = fechacompleta.format(segundaFechaM.getTime());
                            primerFecha = fechacompleta.parse(primerFechaText);
                            segundaFecha = fechacompleta.parse(segundaFechaText);
                            banderacomision=3;
                        }
                        if(banderacomision==2){
                            Double montoarancel = Double.parseDouble(Monto) + (comision/Mes);
                            Double montosegunda = montoarancel+Double.parseDouble(Recargo);
                            Arancel arancelItem = new Arancel(id,idc,null,false,montoarancel,montosegunda,primerFecha,segundaFecha);
                            arancel.add(arancelItem);
                            id++;
                            primerFechaM.add(Calendar.MONTH, 1);
                            segundaFechaM.add(Calendar.MONTH, 1);
                            primerFechaText = fechacompleta.format(primerFechaM.getTime());
                            segundaFechaText = fechacompleta.format(segundaFechaM.getTime());
                            primerFecha = fechacompleta.parse(primerFechaText);
                            segundaFecha = fechacompleta.parse(segundaFechaText);
                        }
                    }
                    System.out.println(arancel);
                    return arancel;
                    
                } catch (ParseException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            return null;
        }
        public String[][] CrearMatrizArancelAlquiler(List<Arancel> aranceles){
            String matriz [][]=new String [aranceles.size()][5];
            int i=0;
            for(Arancel a : aranceles){
                matriz[i][0]= String.valueOf(a.getIdarancel());
                matriz[i][1]= String.valueOf(a.getMontoarancel());
                DateFormat fechacompleta = new SimpleDateFormat("dd/MM/yyyy");
                String primerFechaText = fechacompleta.format(a.getPrimerfecha().getTime());
                String segundaFechaText = fechacompleta.format(a.getSegundafecha().getTime());
                matriz[i][2]= String.valueOf(a.getMontosegunda());
                matriz[i][3]= primerFechaText;
                matriz[i][4]= segundaFechaText;
                i++;
            }
            return matriz;
        }
        public String[][] CrearMatrizArancel(List<Arancel> aranceles){
            String matriz [][]=new String [aranceles.size()][4];
            int i=0;
            for(Arancel a : aranceles){
                matriz[i][0]= String.valueOf(a.getIdarancel());
                matriz[i][1]= String.valueOf(a.getMontoarancel());
                DateFormat fechacompleta = new SimpleDateFormat("dd/MM/yyyy");
                String primerFechaText = fechacompleta.format(a.getPrimerfecha().getTime());
                String segundaFechaText = fechacompleta.format(a.getSegundafecha().getTime());
                matriz[i][2]= primerFechaText;
                matriz[i][3]= segundaFechaText;
                i++;
            }
            return matriz;
        }
        public String[][] CrearMatrizPagarArancel(List<Arancel> aranceles){
            String matriz [][]=new String [aranceles.size()][7];
            int i=0;
            for(Arancel a : aranceles){
                matriz[i][0]= String.valueOf(a.getIdarancel());
                if(a.getPagado()){
                    matriz[i][1]= "Pagado";
                }else{
                    matriz[i][1]= "Adeuda";
                }
                matriz[i][2]= String.valueOf(a.getMontoarancel());
                matriz[i][3]= String.valueOf(a.getMontosegunda());
                DateFormat fechacompleta = new SimpleDateFormat("dd/MM/yyyy");
                String primerFechaText = fechacompleta.format(a.getPrimerfecha().getTime());
                String segundaFechaText = fechacompleta.format(a.getSegundafecha().getTime());
                matriz[i][4]= primerFechaText;
                matriz[i][5]= segundaFechaText;   
                i++;
            }
            return matriz;
        }
        public List <Arancel> VerArancel(String idlocatario){
            Locatario locatario = ObtenerLocatario(Long.parseLong(idlocatario));
            List<Contrato> contrato = new ArrayList <Contrato>();
            List <Contrato> contratos = ObtenerTContrato();
            List <Arancel> arancel = new ArrayList<Arancel>();
            List <Arancel> aranceles = ObtenerTArancel();
            for(Contrato c: contratos){
                if(c.getDnilocatario().equals(locatario.getDni())){
                    contrato.add(c);
                }
            }
            for(Arancel a: aranceles){
                for(Contrato co: contrato){
                    if(co.getId().equals(a.getIdcontrato())){
                        arancel.add(a);
                    }
                }
            }
            return arancel;
        }
        public String NombreLocatario(String idlocatario){
            Locatario locatario = ObtenerLocatario(Long.parseLong(idlocatario));
            return locatario.getNombre();
        }
        public byte[] CrearJR(String usuario,  String aplicacion, String id, SessionFactory sessionFactory) {
            
            Map parametro = new HashMap();
            parametro.put("usuario2",usuario);
            if(id!=null){
                parametro.put("id",id);
            }
            File reportFile = new File(aplicacion);
            byte[] bytes = null;
            return bytes;
    }
        /////SERVICIO
        public void InsertarServicio(Servicio a){
            ServicioDAO.insertar(a);
        }
        public List<Servicio> ObtenerTServicio(){
            return ServicioDAO.obtenerTodos();
        }
        public void ActualizarServicio(Servicio a) {
                ServicioDAO.modificar(a);
	}
        public void EliminarServicio(Servicio a) {
                ServicioDAO.eliminar(a);
	}
        public Servicio ObtenerServicio(Long a){
        Servicio lectura = ServicioDAO.obtener(a);
        return lectura;
        }
        public String[][] CrearMatrizServicio(){
            List<Servicio> servicios = ObtenerTServicio();
            String matriz [][]=new String [servicios.size()][3];
            int i=0;
            for(Servicio s : servicios){
                matriz[i][0]= String.valueOf(s.getId());
                matriz[i][1]= s.getTiposervicio();
                matriz[i][2]= String.valueOf(s.getMonto()); 
                i++;
            }
            return matriz;
        }
        public String[][] ObtenerMatrizServicio(List<Servicio> servicios){
            String matriz [][]=new String [servicios.size()][3];
            int i=0;
            for(Servicio s : servicios){
                matriz[i][0]= String.valueOf(s.getId());
                matriz[i][1]= s.getTiposervicio();
                matriz[i][2]= String.valueOf(s.getMonto()); 
                i++;
            }
            return matriz;
        }
        public String[][] ObtenerMatrizServiciosAl(String idcontratotext){
            Long idcontrato = Long.parseLong(idcontratotext);
            Alquiler alquiler = ObtenerAlquiler(idcontrato);
            List <Servicio> servicios = alquiler.getServicios();
            String matriz[][]=ObtenerMatrizServicio(servicios);
            return matriz;
        }
        public long idServicio(){
            long c = 1;
            List<Servicio> servicios = ObtenerTServicio();
            for (Servicio s: servicios){
                c++;
            }
            return c;
        }
        /////VENTA
        public void InsertarVenta(Venta a){
            VentaDAO.insertar(a);
        }
        public Venta ObtenerVenta(Long a){
        Venta lectura = VentaDAO.obtener(a);
        return lectura;
        }
        public void ActualizarVenta(Venta a){
            VentaDAO.modificar(a);
        }
        public void EliminarVenta(Venta a){
            VentaDAO.eliminar(a);
        }
        /////ALQUILER
        public void InsertarAlquiler(Alquiler a){
            AlquilerDAO.insertar(a);
        }
        public void ActualizarAlquiler(Alquiler a){
            AlquilerDAO.modificar(a);
        }
        public void EliminarAlquiler(Alquiler a){
            AlquilerDAO.eliminar(a);
        }
        public Alquiler ObtenerAlquiler(Long a){
        Alquiler lectura = AlquilerDAO.obtener(a);
        return lectura;
        }
	/////LOCADOR
	public void InsertarLocador(Locador a) {
		LocadorDAO.insertar(a);
	}
        
        public void ActualizarLocador(Locador a) {
                LocadorDAO.modificar(a);
	}
        public void EliminarLocador(Locador a) {
                LocadorDAO.eliminar(a);
	}
        public Locador ObtenerLocador(Long a){
        Locador lectura = LocadorDAO.obtener(a);
        return lectura;
        }
        public List<Locador> ObtenerTLocador(){
            return LocadorDAO.obtenerTodos();
        }
        public String[][] CrearMatrizLocador(){
            List<Locador> locador = ObtenerTLocador();
            String matriz [][]=new String [locador.size()][6];
            int i=0;
            for(Locador a : locador){
                matriz[i][0]= String.valueOf(a.getDni());
                matriz[i][1]= String.valueOf(a.getNombre());
                matriz[i][2]= String.valueOf(a.getEstadocivil());
                matriz[i][3]= String.valueOf(a.getDomicilio());
                matriz[i][4]= String.valueOf(a.getTelefono());
                matriz[i][5]= String.valueOf(a.getMail());  
                i++;
            }
            return matriz;
        }
        ////LOCATARIO
        public void InsertarLocatario(Locatario a) {
            LocatarioDAO.insertar(a);
        }
        public void ActualizarLocatario(Locatario a) {
            LocatarioDAO.modificar(a);
        }
        public Locatario ObtenerLocatario(long a) {
            Locatario lectura = LocatarioDAO.obtener(a);
            return lectura;
        }
        public void EliminarLocatario(Locatario a) {
                LocatarioDAO.eliminar(a);
	}
        public List<Locatario> ObtenerTLocatario(){
            return LocatarioDAO.obtenerTodos();
        }
        public String[][] CrearMatrizLocatario(){
            List<Locatario> locatario = ObtenerTLocatario();
            String matriz [][]=new String [locatario.size()][10];
            int i=0;
            for(Locatario a : locatario){
                matriz[i][0]= String.valueOf(a.getDni());
                matriz[i][1]= String.valueOf(a.getNombre());
                matriz[i][2]= String.valueOf(a.getEstadocivil());
                matriz[i][3]= String.valueOf(a.getDomicilio());
                matriz[i][4]= String.valueOf(a.getTelefono());
                matriz[i][5]= String.valueOf(a.getMail());  
                matriz[i][8]= String.valueOf(a.getUsuario());
                matriz[i][9]= String.valueOf(a.getContrasena());
                if(a.getEstudiante()){
                    matriz[i][6]= String.valueOf("Estudiante");
                    matriz[i][7]= String.valueOf("No tiene");
                    
                }else{
                    matriz[i][6]= String.valueOf(a.getActividaddedicada());
                    matriz[i][7]= String.valueOf(a.getIngresos());
                }
                i++;
            }
            return matriz;
        }
         ////GARANTE
        public void InsertarGarante(Garante a) {
            GaranteDAO.insertar(a);
        }
        public void ActualizarGarante(Garante a) {
            GaranteDAO.modificar(a);
        }
        public Garante ObtenerGarante(long a) {
            Garante lectura = GaranteDAO.obtener(a);
            return lectura;
        }
        public void EliminarGarante(Garante a) {
                GaranteDAO.eliminar(a);
	}
        public List<Garante> ObtenerTGarante(){
            return GaranteDAO.obtenerTodos();
        }
        public String[][] CrearMatrizGarante(){
            List<Garante> garante = ObtenerTGarante();
            String matriz [][]=new String [garante.size()][8];
            int i=0;
            for(Garante a : garante){
                matriz[i][0]= String.valueOf(a.getDni());
                matriz[i][1]= String.valueOf(a.getNombre());
                matriz[i][2]= String.valueOf(a.getEstadocivil());
                matriz[i][3]= String.valueOf(a.getDomicilio());
                matriz[i][4]= String.valueOf(a.getTelefono());
                matriz[i][5]= String.valueOf(a.getMail());  
                matriz[i][6]= String.valueOf(a.getActividaddedicada());
                matriz[i][7]= String.valueOf(a.getIngresos());
                i++;
            }
            return matriz;
        }
        
        ///RELACION DE DEPENDENCIA
        public void InsertarRelacion(Relaciondedependencia a) {
                RelacionDAO.insertar(a);
        }
        public Relaciondedependencia ObtenerRelacion(long a) {
            Relaciondedependencia lectura = RelacionDAO.obtener(a);
            return lectura;
        }
        public void ActualizarRelacion(Relaciondedependencia a) {
             RelacionDAO.modificar(a);
        }
        public void EliminarRelacion(Relaciondedependencia a) {
             RelacionDAO.eliminar(a);
        }
        public List<Relaciondedependencia> ObtenerTRelacion(){
            return RelacionDAO.obtenerTodos();
        }
        public String[][] CrearMatrizRelacion(){
            Persona persona = new Persona();
            List<Relaciondedependencia> relacion = ObtenerTRelacion();
            String matriz [][]=new String [relacion.size()][3];
            int i=0;
            for(Relaciondedependencia a : relacion){
                persona = ObtenerPersona(a.getId());
                matriz[i][0]= String.valueOf(persona.getDni());
                matriz[i][1]= String.valueOf(persona.getNombre()); 
                if(persona.getTipo()==2){
                    matriz[i][2]= String.valueOf("Locatario");
                }
                if(persona.getTipo()==3){
                    matriz[i][2]= String.valueOf("Garante");
                }
                i++;
            }
            return matriz;
        }
        ////Trabajador Independiente
        public void InsertarTrabajador(Trabajadorindependiente a) {
                TrabajadorDAO.insertar(a);
        }
        public Trabajadorindependiente ObtenerTrabajador(long a) {
            Trabajadorindependiente lectura = TrabajadorDAO.obtener(a);
            return lectura;
        }
        public void ActualizarTrabajador(Trabajadorindependiente a) {
             TrabajadorDAO.modificar(a);
        }
        public void EliminarTrabajador(Trabajadorindependiente a) {
             TrabajadorDAO.eliminar(a);
        }
        public List<Trabajadorindependiente> ObtenerTTrabajador(){
            return TrabajadorDAO.obtenerTodos();
        }
        public String[][] CrearMatrizTrabajador(){
            Persona persona = new Persona();
            List<Trabajadorindependiente> trabajador = ObtenerTTrabajador();
            String matriz [][]=new String [trabajador.size()][3];
            int i=0;
            for(Trabajadorindependiente a : trabajador){
                persona = ObtenerPersona(a.getId());
                matriz[i][0]= String.valueOf(persona.getDni());
                matriz[i][1]= String.valueOf(persona.getNombre()); 
                if(persona.getTipo()==2){
                    matriz[i][2]= String.valueOf("Locatario");
                }
                if(persona.getTipo()==3){
                    matriz[i][2]= String.valueOf("Garante");
                }
                i++;
            }
            return matriz;
        }
        //INMUEBLE
        public List<Inmueble> ObtenerTInmueble(){
            return InmuebleDAO.obtenerTodos();
        }
        public Inmueble BuscarInmueble(String idtext){
            long id = Long.parseLong(idtext);
            Inmueble inmueble = InmuebleDAO.obtener(id);
            return inmueble;
        }
        public long idInmueble(){
            long b = 1;
            List<Inmueble> inmueble = ObtenerTInmueble();
            for (Inmueble a: inmueble){
                b++;
            }
            return b;
        }
        
        ///LOCAL COMERCIAL
        public void InsertarLocal(Localcomercial a) {
		LocalComercialDAO.insertar(a);
	}
        
        public void ActualizarLocal(Localcomercial a) {
                LocalComercialDAO.modificar(a);
	}
        public void EliminarLocal(Localcomercial a) {
                LocalComercialDAO.eliminar(a);
	}
        public Localcomercial ObtenerLocal(Long a){
        Localcomercial lectura = LocalComercialDAO.obtener(a);
        return lectura;
        }
        public List<Localcomercial> ObtenerTLocal(){
            return LocalComercialDAO.obtenerTodos();
        }
        public String[][] CrearMatrizLocal(){
            Localcomercial local = new Localcomercial();
            List<Localcomercial> locales = ObtenerTLocal();
            String matriz [][]=new String [locales.size()][7];
            int i=0;
            for(Localcomercial a : locales){
                local = ObtenerLocal(a.getId());
                matriz[i][0]= String.valueOf(local.getId());
                matriz[i][1]= String.valueOf(local.getUbicacion());
                matriz[i][2]= String.valueOf(local.getTamano()); 
                matriz[i][3]= String.valueOf(local.getLocalidad());
                matriz[i][4]= String.valueOf(local.getCantidadbanos());
                matriz[i][5]= String.valueOf(local.getCantidadhabitaciones());
                if(local.getCochera()){
                    matriz[i][6]= "Si";
                }else{
                    matriz[i][6]= "No";
                }
                
                i++;
            }
            return matriz;
        }
        ///DEPARTAMENTO
        public void InsertarDepartamento(Departamento a) {
		DepartamentoDAO.insertar(a);
	}
        
        public void ActualizarDepartamento(Departamento a) {
                DepartamentoDAO.modificar(a);
	}
        public void EliminarDepartamento(Departamento a) {
                DepartamentoDAO.eliminar(a);
	}
        public Departamento ObtenerDepartamento(Long a){
        Departamento lectura = DepartamentoDAO.obtener(a);
        return lectura;
        }
        public List<Departamento> ObtenerTDepartamento(){
            return DepartamentoDAO.obtenerTodos();
        }
        public String[][] CrearMatrizDepartamento(){
            Departamento departamento = new Departamento();
            List<Departamento> departamentos = ObtenerTDepartamento();
            String matriz [][]=new String [departamentos.size()][7];
            int i=0;
            for(Departamento a : departamentos){
                departamento = ObtenerDepartamento(a.getId());
                matriz[i][0]= String.valueOf(departamento.getId());
                matriz[i][1]= String.valueOf(departamento.getUbicacion());
                matriz[i][2]= String.valueOf(departamento.getTamano()); 
                matriz[i][3]= String.valueOf(departamento.getLocalidad());
                matriz[i][4]= String.valueOf(departamento.getCantidadbanos());
                matriz[i][5]= String.valueOf(departamento.getCantidadhabitaciones());
                if(departamento.getCochera()){
                    matriz[i][6]= "Si";
                }else{
                    matriz[i][6]= "No";
                }
                i++;
            }
            return matriz;
        }
        ///CASA
        public void InsertarCasa(Casa a) {
		CasaDAO.insertar(a);
	}
        
        public void ActualizarCasa(Casa a) {
                CasaDAO.modificar(a);
	}
        public void EliminarCasa(Casa a) {
                CasaDAO.eliminar(a);
	}
        public Casa ObtenerCasa(Long a){
        Casa lectura = CasaDAO.obtener(a);
        return lectura;
        }
        public List<Casa> ObtenerTCasa(){
            return CasaDAO.obtenerTodos();
        }
        public String[][] CrearMatrizCasa(){
            Casa casa = new Casa();
            List<Casa> casas = ObtenerTCasa();
            String matriz [][]=new String [casas.size()][7];
            int i=0;
            for(Casa a : casas){
                casa = ObtenerCasa(a.getId());
                matriz[i][0]= String.valueOf(casa.getId());
                matriz[i][1]= String.valueOf(casa.getUbicacion());
                matriz[i][2]= String.valueOf(casa.getTamano()); 
                matriz[i][3]= String.valueOf(casa.getLocalidad());
                matriz[i][4]= String.valueOf(casa.getCantidadbanos());
                matriz[i][5]= String.valueOf(casa.getCantidadhabitaciones());
                if(casa.getCochera()){
                    matriz[i][6]= "Si";
                }else{
                    matriz[i][6]= "No";
                }
                i++;
            }
            return matriz;
        }
        ///TERRENO
        public void InsertarTerreno(Terreno a) {
		TerrenoDAO.insertar(a);
	}
        
        public void ActualizarTerreno(Terreno a) {
                TerrenoDAO.modificar(a);
	}
        public void EliminarTerreno(Terreno a) {
                TerrenoDAO.eliminar(a);
	}
        public Terreno ObtenerTerreno(Long a){
        Terreno lectura = TerrenoDAO.obtener(a);
        return lectura;
        }
          public List<Terreno> ObtenerTTerreno(){
            return TerrenoDAO.obtenerTodos();
        }
          public String[][] CrearMatrizTerreno(){
            Terreno terreno = new Terreno();
            List<Terreno> terrenos = ObtenerTTerreno();
            String matriz [][]=new String [terrenos.size()][4];
            int i=0;
            for(Terreno a : terrenos){
                terreno = ObtenerTerreno(a.getId());
                matriz[i][0]= String.valueOf(terreno.getId());
                matriz[i][1]= String.valueOf(terreno.getUbicacion());
                matriz[i][2]= String.valueOf(terreno.getTamano()); 
                matriz[i][3]= String.valueOf(terreno.getLocalidad());
                i++;
            }
            return matriz;
        }
        public Persona ObtenerPersona(Long a){
        Persona lectura = PersonaDAO.obtener(a);
        return lectura;
        }
        public void ajustartabla(JTable table) {
        //Se obtiene el modelo de la columna
        TableColumnModel columnModel = table.getColumnModel();
        //Se obtiene el total de las columnas
        for (int column = 0; column < table.getColumnCount(); column++) {
            //Establecemos un valor minimo para el ancho de la columna
            int width = 100; //Min Width
            //Obtenemos el numero de filas de la tabla
            for (int row = 0; row < table.getRowCount(); row++) {
                //Obtenemos el renderizador de la tabla
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                //Creamos un objeto para preparar el renderer
                Component comp = table.prepareRenderer(renderer, row, column);
                //Establecemos el width segun el valor maximo del ancho de la columna
                width = Math.max(comp.getPreferredSize().width + 1, width);

            }
            //Se establece una condicion para no sobrepasar el valor de 300
            //Esto es Opcional
            if (width > 250) {
                width = 250;
            }
            //Se establece el ancho de la columna
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}

    

