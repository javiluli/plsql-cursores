package ejercicios.java.Ejercicio_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionOracle;
import exceptions.DAOException;
import recursos.DBQuery;

public class ejer_2_formula_1 {

  /**
   * @param args
   */
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
        st = con.prepareStatement(DBQuery.getSelectEjercicio2a());
        // ejecutar la orden y recuperamos los datos
        datos = st.executeQuery();
        // leo para ver si hay datos
        hayDatos = datos.next();

        while (hayDatos) {
          // seleccionando el nombre de la columna (recomendada)
          System.out.println("Departamento: " + datos.getString("DEPARTAMENTO") 
                              + " | Empleados: " + datos.getInt("EMPLEADOS"));
          // vulevo a leer para ver si hay datos
          hayDatos = datos.next();
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