package controller;

import gestion.EmpleadoGestion;
import gestion.PersonaGestion;
import gestion.ClienteGestion;
import gestion.DetalleFacturaGestion;
import gestion.FacturaGestion;
import gestion.InventarioGestion;
import gestion.InventarioXProductosGestion;
import gestion.ProductoGestion;
import gestion.ProvinciasGestion;
import gestion.RolesGestion;
import gestion.SucursalesGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Named(value = "respaldoController")
@SessionScoped
public class RespaldoController implements Serializable {

    public RespaldoController() {
    }

    public void respaldo() {
        ZipOutputStream out = null;
        try {

            String json = "Personas:\n\n" + PersonaGestion.generarJson()
                    + "\n\nRoles:\n\n" + RolesGestion.generarJson()
                    + "\n\nEmpleados:\n\n" + EmpleadoGestion.generarJson()
                    + "\n\nClientes:\n\n" + ClienteGestion.generarJson()
                    + "\n\nProductos:\n\n" + ProductoGestion.generarJson()
                    + "\n\nFacturas:\n\n" + FacturaGestion.generarJson()
                    + "\n\nDetalleFactura:\n\n" + DetalleFacturaGestion.generarJson()
                    + "\n\nInventarios:\n\n" + InventarioGestion.generarJson()
                    + "\n\nInventariosXProductos:\n\n" + InventarioXProductosGestion.generarJson()
                    + "\n\nProvincias:\n\n" + ProvinciasGestion.generarJson()
                    + "\n\nSucursales:\n\n" + SucursalesGestion.generarJson();

            File f = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "respaldo.zip");
            out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry("respaldo.json");
            out.putNextEntry(e);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();

            File zipPath = new File(FacesContext
                    .getCurrentInstance().
                    getExternalContext()
                    .getRealPath("/respaldo") + "respaldo.zip");

            byte[] zip = Files.readAllBytes(zipPath.toPath());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            ServletOutputStream flujo = respuesta.getOutputStream();

            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-disposition", "attachment; filename=respaldo.zip");

            flujo.write(zip);
            flujo.flush();
            FacesContext.getCurrentInstance().responseComplete();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RespaldoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RespaldoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RespaldoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
