package recursos;

public class DBQuery {

  private final static String SELECT_EJERCICIO_1 = "SELECT nomem, fecin, ROUND(salar/3) TERCIOSALAR"
      + "                                           FROM temple"
      + "                                           WHERE comis IS NOT NULL"
      + "                                           ORDER BY nomem";

  private final static String SELECT_EJERCICIO_2A = "SELECT d.nomde DEPARTAMENTO, COUNT(*) EMPLEADOS"
      + "                                           FROM tdepto d, temple e"
      + "                                           WHERE d.numde = e.numde"
      + "                                           GROUP BY d.nomde";

  private final static String SELECT_EJERCICIO_2B = "SELECT nomde DEPARTAMENTO"
      + "                                            FROM temple e, tdepto d"
      + "                                            WHERE e.numde = d.numde"
      + "                                            ORDER BY nomde";

  private final static String SELECT_EJERCICIO_2C_TDEPTO = "SELECT nomde, numde FROM tdepto";
  private final static String SELECT_EJERCICIO_2C_TEMPLE = "SELECT COUNT(*) empleados FROM temple WHERE numde = ?";

  private final static String SELECT_EJERCICIO_3 = "SELECT nomem, numde"
      + "                                            FROM temple"
      + "                                            ORDER BY numde";

  private final static String SELECT_EJERCICIO_4 = "SELECT nomem, numde, salar"
      + "                                           FROM temple"
      + "                                           ORDER BY numde, salar";

  private final static String SELECT_EJERCICIO_5 = "SELECT nomem, numde, salar"
      + "                                           FROM temple"
      + "                                           ORDER BY numde, salar";

  private final static String SELECT_EJERCICIO_6A = "SELECT nomem, numde, salar"
      + "                                           FROM temple"
      + "                                           ORDER BY numde, salar";

  private final static String SELECT_EJERCICIO_7 = "SELECT nomem, numde, salar"
      + "                                           FROM temple"
      + "                                           ORDER BY numde, salar";

  public static String getSelectEjercicio1() {
    return SELECT_EJERCICIO_1;
  }

  public static String getSelectEjercicio2a() {
    return SELECT_EJERCICIO_2A;
  }

  public static String getSelectEjercicio2b() {
    return SELECT_EJERCICIO_2B;
  }

  public static String getSelectEjercicio2cTdepto() {
    return SELECT_EJERCICIO_2C_TDEPTO;
  }

  public static String getSelectEjercicio2cTemple() {
    return SELECT_EJERCICIO_2C_TEMPLE;
  }

  public static String getSelectEjercicio3() {
    return SELECT_EJERCICIO_3;
  }

  public static String getSelectEjercicio4() {
    return SELECT_EJERCICIO_4;
  }

  public static String getSelectEjercicio5() {
    return SELECT_EJERCICIO_5;
  }

  public static String getSelectEjercicio6a() {
    return SELECT_EJERCICIO_6A;
  }

  public static String getSelectEjercicio7() {
    return SELECT_EJERCICIO_7;
  }

}
