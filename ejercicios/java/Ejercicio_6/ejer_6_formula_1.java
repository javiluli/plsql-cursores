package ejercicios.java.Ejercicio_6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionOracle;
import exceptions.DAOException;
import recursos.DBQuery;

public class ejer_6_formula_1 {

  public static void main(String[] args) {

    ConexionOracle c = null;
    Connection con = null;
    PreparedStatement st = null;
    ResultSet datos = null;
    boolean hayDatos;

    try {
      c = new ConexionOracle();
      con = c.getConexion();

      try {
        // preparacion
        st = con.prepareStatement(DBQuery.getSelectEjercicio6a());
        // ejecutar la orden y recuperamos los datos
        datos = st.executeQuery();
        // leo para ver si hay datos
        hayDatos = datos.next();

        // variables auxiliares
        int auxNumde; // numero de departamento
        int contEmpleados; // contador de empleados
        double auxSalar; // salario

        while (hayDatos) {
          auxNumde = datos.getInt("numde");

          // haya filas y mismo departamento
          while (hayDatos && auxNumde == datos.getInt("numde")) {
            contEmpleados = 0;
            System.out.println("\nNº. Departamento: " + datos.getInt("numde"));

            // haya filas, mismo depto, menos de 3 empleados
            while (hayDatos && auxNumde == datos.getInt("numde") && contEmpleados < 3) {
              auxSalar = datos.getDouble("salar");

              // haya filas, mismo depto, menos de 3 empleados y mismo salario
              while (hayDatos && auxNumde == datos.getInt("numde") && auxSalar == datos.getDouble("salar")) {
                System.out.println("Empleado: " + datos.getString("nomem") 
                                    + " | Salario " + datos.getDouble("salar"));
                // vulevo a leer para ver si hay datos
                hayDatos = datos.next();
              }

              // fin mismo salario del mismo departamento, o distinto departamento, o ho hay datos
              contEmpleados += 1;
            }

            // forzar cambio de departamento o final de datos si se han tratado tres salarios
            while (hayDatos && auxNumde == datos.getInt("numde")) {
              // vulevo a leer para ver si hay datos
              hayDatos = datos.next();
            }

          }
          // cambio de departamento o fin de datos
        }

        // se cierra el "ResultSet" (equivalente a un Cursor de PL/SQL)
        datos.close();
        // cerramos el objeto "PreparedStatement"
        st.close();

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