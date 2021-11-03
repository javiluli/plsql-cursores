-- 2.1.- HACER UN PROGRAMA  PL/SQL PARA VISUALIZAR EL NOMBRE Y EL NUMERO DE EMPLEADOS DE CADA NUMDE (CON group by SQL).

SET SERVEROUTPUT ON;
DECLARE
  total NUMBER := 0;
  CURSOR ctemple IS SELECT d.nomde departamento, COUNT(*) cont FROM tdepto d, temple e WHERE d.numde = e.numde GROUP BY d.nomde;

  linea ctemple%rowtype;
BEGIN
  OPEN ctemple;
  FETCH ctemple INTO linea;
  WHILE ctemple%found LOOP
    dbms_output.put_line('Nombre dedepartamento ' || linea.nomde);
    dbms_output.put_line('Numero de empleados ' || linea.cont);
    FETCH ctemple INTO linea;
  END LOOP;

  CLOSE ctemple;
END;
