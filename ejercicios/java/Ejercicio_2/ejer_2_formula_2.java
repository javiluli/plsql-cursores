package ejercicios.java.Ejercicio_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionOracle;
import exceptions.DAOException;
import recursos.DBQuery;

public class ejer_2_formula_2 {

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
        st = con.prepareStatement(DBQuery.getSelectEjercicio2b());
        // ejecutar la orden y recuperamos los datos
        datos = st.executeQuery();
        // leo para ver si hay datos
        hayDatos = datos.next();

        // variables auxiliares
        String nomdeaux; // auxiliar con el nombre del departamento
        int contador; // contaro de empleados

        while (hayDatos) {
          contador = 0;
          nomdeaux = datos.getString("DEPARTAMENTO");

          while (hayDatos && nomdeaux.equals(datos.getString("DEPARTAMENTO"))) {
            contador += 1;
            hayDatos = datos.next();
          }
          System.out.println("Departamento: " + nomdeaux 
                              + " | Empleados: " + contador);

        }

        // se cierra el "cursor"
        datos.close();
        // cerramos el objeto
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