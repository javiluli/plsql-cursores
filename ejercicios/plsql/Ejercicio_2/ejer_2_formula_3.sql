-- 2.3.- HACER UN PROGRAMA PL/SQL PARA VISUALIZAR EL NOMBRE Y EL NÚMERO DE EMPLEADOS DE CADA NUMDE CON UN CURSOR CON PARÁMETROS.

SET SERVEROUTPUT ON;
DECLARE
  CURSOR cdep IS SELECT nomde, numde FROM tdepto;
  fcdep   cdep%rowtype;
  CURSOR cemple (pnumde temple.numde%TYPE) IS SELECT COUNT(*) total FROM temple WHERE numde = pnumde;
  fcemple cemple%rowtype;
  
BEGIN
  OPEN cdep;
  FETCH cdep INTO fcdep;
  WHILE cdep%found LOOP
    dbms_output.put_line('Nombre de departamento: ' || fcdep.nomde);
    OPEN cemple(fcdep.numde);
    FETCH cemple INTO fcemple;
    WHILE cemple%found LOOP
      dbms_output.put_line('Numero de empleados:' || fcemple.total);
      FETCH cemple INTO fcemple;
    END LOOP;

    CLOSE cemple;
    FETCH cdep INTO fcdep;
  END LOOP;

  CLOSE cdep;
END;
