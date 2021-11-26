package ejercicios.java.Ejercicio_3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionOracle;
import exceptions.DAOException;
import recursos.DBQuery;

public class ejer_3_formula_1 {

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
        st = con.prepareStatement(DBQuery.getSelectEjercicio3());
        // ejecutar la orden y recuperamos los datos
        datos = st.executeQuery();
        // leo para ver si hay datos
        hayDatos = datos.next();

        // variables auxiliares
        int numdemaux; // numero de departamento
        int contador; // cantidad de empleados por departamento

        while (hayDatos) {
          contador = 0;
          numdemaux = datos.getInt("numde");
          System.out.println("\nNumero de departamento: " + numdemaux);

          while (hayDatos && numdemaux == datos.getInt("numde")) { // hay datos y el numero de departamento es el mismo

            if (contador < 2) { // solo mostrara 2 empleados por departamento
              contador += 1;
              System.out.println("- " + datos.getString("nomem"));
            }

            hayDatos = datos.next();
          }
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
