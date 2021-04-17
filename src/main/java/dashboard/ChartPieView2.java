/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Conexion;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author wmolina
 */
@Named(value = "chartPieView2")
@SessionScoped
public class ChartPieView2 implements Serializable {

    private final String SQL_GETFEMALE = "SELECT genero,Count(*) total FROM usuario where genero='F'";
    private final String SQL_GETMALE = "SELECT genero,Count(*) total FROM usuario where genero='M'";
    private PieChartModel pieModel2;

    @PostConstruct
    public void init() {
        createPieModels();
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    private void createPieModels() {
        createPieModel2();
    }

    private void createPieModel2() {
        pieModel2 = new PieChartModel();

        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETFEMALE);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                pieModel2.set(rs.getString("genero"), rs.getInt("total"));
            }
        } catch (SQLException ex) {

        }

        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETMALE);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                pieModel2.set(rs.getString("genero"), rs.getInt("total"));
            }
        } catch (SQLException ex) {

        }

        pieModel2.setTitle("Custom Pie");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
        pieModel2.setShadow(false);
    }
}
