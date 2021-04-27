package controller;

import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Named(value = "reporteController")
@SessionScoped
public class ReporteController implements Serializable {

    public ReporteController() {
    }

    public void verPDFPersonas() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/reporte/PersonasReport.jasper"));

            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descargarPDFPersonas() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/reporte/PersonasReport.jasper"));

            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.addHeader("Content-disposition", "attachment; filename=PersonasReport.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    public void verPDFEmpleados() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/reporte/EmpleadosReport.jasper"));

            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descargarPDFEmpleados() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/reporte/EmpleadosReport.jasper"));

            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.addHeader("Content-disposition", "attachment; filename=PersonasReport.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    public void verPDFClientes() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/reporte/ClientesReport.jasper"));

            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descargarPDFClientes() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/reporte/ClientesReport.jasper"));

            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.addHeader("Content-disposition", "attachment; filename=PersonasReport.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    public void verPDFFacturas() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/reporte/FacturasReport.jasper"));

            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descargarPDFFacturas() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/reporte/FacturasReport.jasper"));

            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.addHeader("Content-disposition", "attachment; filename=PersonasReport.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    public void verPDFInventarioXProductos() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/reporte/ProductosXInventarioReport.jasper"));

            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descargarPDFInventarioXProductos() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().
                    getExternalContext().getRealPath("/reporte/ProductosXInventarioReport.jasper"));

            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.addHeader("Content-disposition", "attachment; filename=PersonasReport.pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    /** 
    public void buscaUsuario(Empleados empleado) {
        Map<String, Object> parametrosReporte = new HashMap<>();
        parametrosReporte.put("idUsuario", empleado.getIdUsuario());
        parametrosReporte.put("nombre", empleado.getNombreCompleto());
        try {
            File jasper = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/usuarios/buscaUsuario.jasper"));

            JasperPrint reporteJasper = JasperFillManager.
                    fillReport(jasper.getPath(), parametrosReporte, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");

            ServletOutputStream flujo = respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    **/
    
    
}
