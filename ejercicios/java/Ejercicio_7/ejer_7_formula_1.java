package ejercicios.java.Ejercicio_7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionOracle;
import exceptions.DAOException;
import recursos.DBQuery;

public class ejer_7_formula_1 {

  public static void main(String[] args) {

    ConexionOracle c = null;
    Connection con = null;
    PreparedStatement st = null;
    ResultSet datos = null;
    boolean hayDatos;

    try {
      c = new ConexionOracle();
      con = c.getConexion();

      // codigo para consultar
      try {
        // preparacion
        st = con.prepareStatement(DBQuery.getSelectEjercicio7());
        // ejecutar la orden y recuperamos los datos
        datos = st.executeQuery();
        // leo para ver si hay datos
        hayDatos = datos.next();

        // variables auxiliares
        int contadorsSalarios, contadorMismoSalario, auxNumde;
        double auxSalario;

        while (hayDatos) {
          auxNumde = datos.getInt("numde");

          // haya filas y mismo departamento
          while (hayDatos && auxNumde == datos.getInt("numde")) {
            contadorsSalarios = 0;

            while (hayDatos && auxNumde == datos.getInt("numde") && contadorsSalarios < 3) {
              contadorMismoSalario = 0;
              auxSalario = datos.getDouble("salar");

              while (hayDatos && auxNumde == datos.getInt("numde") && auxSalario == datos.getDouble("salar")) {
                if (contadorMismoSalario < 2) {
                  System.out.println(datos.getString("nomem") + " // " + auxNumde + " // " + datos.getDouble("salar"));
                }
                hayDatos = datos.next();

              }
              contadorsSalarios += 1;

            }
            while (hayDatos && auxNumde == datos.getInt("numde")) {
              hayDatos = datos.next();
            }
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