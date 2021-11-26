package ejercicios.java.Ejercicio_5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.ConexionOracle;
import exceptions.DAOException;
import recursos.DBQuery;

public class ejer_5 {

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
        st = con.prepareStatement(DBQuery.getSelectEjercicio4());
        // ejecutar la orden y recuperamos los datos
        datos = st.executeQuery();
        // leo para ver si hay datos
        hayDatos = datos.next();

        // variables auxiliares
        int auxNumde; // numero de departamento
        int contEmpleados; // contador de empelados
        double auxSalar; // salario

        while (hayDatos) {
          // seleccionando el nombre de la columna (recomendada)
          contEmpleados = 0;
          auxNumde = datos.getInt("numde");
          auxSalar = datos.getDouble("salar");

          while (hayDatos && auxNumde == datos.getInt("numde")) { // hay datos y es el mismo numero de departamento

            if (auxSalar == datos.getDouble("salar")) { // tienen el mismo salario

              if (contEmpleados < 2) { // se muestran solo 2 empelados
                System.out.println("Empleado: " + datos.getString("nomem") 
                                    + "Nº. Departamento: " + datos.getInt("numde") 
                                    + " Salario: " + datos.getDouble("salar"));
              }
              contEmpleados += 1;
            }
            // vulevo a leer para ver si hay datos
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
