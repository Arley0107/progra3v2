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
import org.primefaces.model.chart.PieChartModel;
import model.Conexion;

/**
 *
 * @author wmolina
 */
@Named(value = "chartPieView1")
@SessionScoped
public class ChartPieView1 implements Serializable {

    private final String SQL_GETFEMALE = "SELECT genero,Count(*) total FROM personas where genero='F'";
    private final String SQL_GETMALE = "SELECT genero,Count(*) total FROM personas where genero='M'";
    private final String SQL_GETOTHER = "SELECT genero,Count(*) total FROM personas where genero='O'";

    private PieChartModel pieModel1;

    @PostConstruct
    public void init() {
        createPieModels();
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    private void createPieModels() {
        createPieModel1();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETFEMALE);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                pieModel1.set("Femenino", rs.getInt("total"));
            }
        } catch (SQLException ex) {

        }

        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETMALE);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                pieModel1.set("Masculino", rs.getInt("total"));
            }
        } catch (SQLException ex) {

        }
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_GETOTHER);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                pieModel1.set("Otro", rs.getInt("total"));
            }
        } catch (SQLException ex) {

        }

        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
    }

}
