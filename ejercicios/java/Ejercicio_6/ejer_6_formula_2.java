package ejercicios.java.Ejercicio_6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionOracle;
import exceptions.DAOException;

public class ejer_6_formula_2 {

  public static void main(String[] args) {

    ConexionOracle c = null;
    Connection con = null;

    PreparedStatement ordenSelectDistinctNumde = null;
    PreparedStatement ordenSelectDistinctSalar = null;
    PreparedStatement ordenSelectEmpleados = null;

    ResultSet datosSelectDistinctNumde = null;
    ResultSet datosSelectDistinctSalar = null;
    ResultSet datosSelectEmpleados = null;

    final String SELECT_DISTINCT_NUMDE = "SELECT DISTINCT numde FROM temple";
    String SELECT_DISTINCT_SALAR = "SELECT DISTINCT salar FROM temple WHERE numde = ? ORDER BY salar";
    String SELECT_EMPLEADOS = "SELECT nomem, numde, salar FROM temple WHERE numde = ? AND salar = ?";

    try {
      c = new ConexionOracle();
      con = c.getConexion();

      // codigo para consultar
      try {
        // preparacion
        ordenSelectDistinctNumde = con.prepareStatement(SELECT_DISTINCT_NUMDE);
        ordenSelectDistinctSalar = con.prepareStatement(SELECT_DISTINCT_SALAR);
        ordenSelectEmpleados = con.prepareStatement(SELECT_EMPLEADOS);

        // ejecutar la select para obtener los distintos NUMDE
        datosSelectDistinctNumde = ordenSelectDistinctNumde.executeQuery();

        // leo para ver si hay datos
        boolean hayDatosSelectDistinctNumde = datosSelectDistinctNumde.next();

        while (hayDatosSelectDistinctNumde) {
          // variables auxiliares
          int contadorSalarios = 0;

          // preparamos la orden para obtener los salarios segun el NUMDE
          ordenSelectDistinctSalar.setInt(1, datosSelectDistinctNumde.getInt("numde"));
          // ejecutamosla orden para obtener los salarios segun el NUMDE
          datosSelectDistinctSalar = ordenSelectDistinctSalar.executeQuery();
          // si hay datos en la seleccion de SALAR segun su NUMDE
          boolean hayDatosSelectDistinctSalar = datosSelectDistinctSalar.next();

          while (hayDatosSelectDistinctSalar && contadorSalarios < 3) {

            // preparamos la orden para obtener los salarios segun el NUMDE
            ordenSelectEmpleados.setInt(1, datosSelectDistinctNumde.getInt("numde"));
            ordenSelectEmpleados.setDouble(2, datosSelectDistinctSalar.getDouble("salar"));
            // ejecutamosla orden para obtener los salarios segun el NUMDE
            datosSelectEmpleados = ordenSelectEmpleados.executeQuery();
            // si hay datos en la seleccion de SALAR segun su NUMDE
            boolean hayDatosSelectEmpleados = datosSelectEmpleados.next();

            while (hayDatosSelectEmpleados) {
              System.out.println("Empleado: " + datosSelectEmpleados.getString("nomem") 
                                  + " | Nº. Departamento: " + datosSelectEmpleados.getInt("numde") 
                                  + " | Salario " + datosSelectEmpleados.getDouble("salar"));

              hayDatosSelectEmpleados = datosSelectEmpleados.next();

            }
            contadorSalarios += 1;
            datosSelectEmpleados.close();
            hayDatosSelectDistinctSalar = datosSelectDistinctSalar.next();
          }
          datosSelectDistinctSalar.close();
          hayDatosSelectDistinctNumde = datosSelectDistinctNumde.next();
        }

        datosSelectDistinctNumde.close();

      } catch (SQLException e) {
        throw new DAOException("Error", e);
      }

    } catch (DAOException e) {
      if (e.getCause() == null)
        System.out.println(e.getMessage());
      else {
        System.out.println("Error interno");
        e.printStackTrace();
      }
    } finally {
      try {
        if (con != null)
          con.close();
      } catch (SQLException e) {
        System.out.println("Error interno");
        e.printStackTrace();
      }
    }

  }
}