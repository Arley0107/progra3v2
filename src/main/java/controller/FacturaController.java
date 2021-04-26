package controller;

import gestion.ClienteGestion;
import gestion.DetalleFacturaGestion;
import gestion.FacturaGestion;
import gestion.ProductoGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.DetalleFactura;
import model.Facturas;
import model.Productos;

@Named(value = "facturaController")
@SessionScoped
public class FacturaController extends Facturas implements Serializable {

    //Toma la fecha actual
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    String fechaActual = dtf.format(now);
    Date fecha;
    
    public FacturaController() {
    }

    public List<Facturas> getFacturas() {
        return FacturaGestion.getFacturas();
    }

    public String insertaFactura(ArrayList<Productos> listaProductos) {  
        //almacena la fecha actual
        try {       
            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaActual);
        } catch (ParseException ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Facturas plantillaFactura = new Facturas(
                (int) (Math.random() * 1000),
                ClienteGestion.getCliente(getIdCliente()).getIdCliente(),
                fecha,
                calculaTotal());
        FacturaGestion.insertFactura(plantillaFactura);
        Facturas factura = FacturaGestion.getUltimaFactura();
        if (factura != null) {
            for (Productos producto : listaProductos) {
                producto.setIdFacturaDetalleFactura(factura.getIdFactura());
                insertaDetalleFactura(producto.getDetalleFactura());
            }
            return "principal.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el usuario se encuentre duplicado.");
            FacesContext.getCurrentInstance().addMessage("menuForm:cantidad", msg);
            return "menu.xhtml";
        }
    }

    private String insertaDetalleFactura(DetalleFactura detalleFactura) {
        if (DetalleFacturaGestion.insertDetalleFactura(detalleFactura)) {
            return "principal.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente el usuario se encuentre duplicado.");
            FacesContext.getCurrentInstance().addMessage("menuForm:cantidad", msg);
            return "menu.xhtml";
        }
    }

    private Double calculaTotal() {
        Double total = 0.0;
        for (Productos producto : ProductoGestion.getProductos()) {
            total += producto.getSubtotalDetalleFactura();
        }
        return total;
    }
}
