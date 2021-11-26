package ejercicios.java.Ejercicio_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionOracle;
import exceptions.DAOException;
import recursos.DBQuery;

public class ejer_2_formula_3 {

  public static void main(String[] args) {

    ConexionOracle c = null;
    Connection con = null;
    PreparedStatement stTdepto = null;
    PreparedStatement stTemple = null;
    ResultSet datosTdepto = null;
    ResultSet datosTemple = null;

    try {
      c = new ConexionOracle();
      con = c.getConexion();

      try {
        // preparacion
        stTdepto = con.prepareStatement(DBQuery.getSelectEjercicio2cTdepto());
        stTemple = con.prepareStatement(DBQuery.getSelectEjercicio2cTemple());

        // ejecutar la orden y recuperamos los datos
        datosTdepto = stTdepto.executeQuery();
        // leo para ver si hay datos
        boolean hayDatosTdepto = datosTdepto.next();

        while (hayDatosTdepto) {
          System.out.print(datosTdepto.getString("nomde") + " -> ");
          // preparamos "stTemple" con los parametros correspondientes
          stTemple.setInt(1, datosTdepto.getInt("numde"));
          // ejecutamos "stTemple" con los valores de su parametro y obtenemos
          // "datosTemple"
          datosTemple = stTemple.executeQuery();
          // se lee si hay datos en "datosTemple"
          boolean hayDatosTemple = datosTemple.next();

          while (hayDatosTemple) {
            System.out.println(datosTemple.getInt("empleados"));
            hayDatosTemple = datosTemple.next();
          }

          // cerramos "datosTemple"
          datosTemple.close();
          // se lee si hay datos en "datosTdepto"
          hayDatosTdepto = datosTdepto.next();
        }

        // se cierra "datosTdepto"
        datosTdepto.close();

        // cerramos los objetos
        stTdepto.close();
        stTemple.close();

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
