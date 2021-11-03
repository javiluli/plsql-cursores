-- 2.2.- HACER UN PROGRAMA PL/SQL PARA VISUALIZAR EL NOMBRE Y EL NÚMERO DE EMPLEADOS DE CADA NUMDE (sin group by en SQL y con ruptura de control).

SET SERVEROUTPUT ON;
DECLARE
  CURSOR cdep IS SELECT nomde FROM temple e, tdepto d WHERE e.numde = d.numde ORDER BY nomde;

  fcdep    cdep%rowtype;
  nomdeaux tdepto.nomde%TYPE;
  contador NUMBER;
  
BEGIN
  OPEN cdep;
  FETCH cdep INTO fcdep;
  WHILE cdep%found LOOP
     --inicio de ruptura
    contador := 0;
    nomdeaux := fcdep.nomde;
    WHILE cdep%found AND nomdeaux = fcdep.nomde LOOP
      contador := contador + 1;
    FETCH cdep INTO fcdep;
    END LOOP;
    
    --ruptura
    dbms_output.put_line(nomdeaux || ' ' || contador);
  END LOOP;

  CLOSE cdep;
END;
