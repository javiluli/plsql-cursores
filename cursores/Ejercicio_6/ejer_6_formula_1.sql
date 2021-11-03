-- 6.1.- VISUALIZAR EL NUMDE DE TODOS LOS EMPLEADOS QUE PERCIBEN LOS 3 MENORES SALARIOS (3 min salar) DE CADA NUMDE.

SET SERVEROUTPUT ON;
DECLARE
  CURSOR C1 IS SELECT NOMEM, NUMDE, SALAR FROM TEMPLE ORDER BY NUMDE, SALAR;
  LINEA_C1 C1%ROWTYPE;
  CONTADORsalarios NUMBER:=0;
  AUX TEMPLE.NUMDE%TYPE;
  salario_aux TEMPLE.SALAR%TYPE;
  
BEGIN
  OPEN C1;
  FETCH C1 INTO LINEA_C1;
  WHILE C1%FOUND LOOP -- haya filas
     AUX:= LINEA_C1.NUMDE;
     WHILE C1%FOUND AND AUX=LINEA_C1.NUMDE LOOP -- haya filas y mismo departamento
         contadorsalarios:=0;
         WHILE C1%FOUND AND AUX=LINEA_C1.NUMDE AND contadorsalarios < 3  LOOP -- haya filas,mismo depto
            salario_aux:= LINEA_C1.salar;                                         -- menos de 3  salarios
           WHILE C1%FOUND AND AUX=LINEA_C1.NUMDE and salario_aux= LINEA_C1.salar  loop
               DBMS_OUTPUT.PUT_LINE(LINEA_C1.NOMEM||'  '||AUX||'  '||LINEA_C1.salar);
               FETCH C1 INTO LINEA_C1;
               end loop;
          -- fin mismo salario del mismo departamento .o distinto departamento, o ho hay datos    
            contadorsalarios:=contadorsalarios+1;   
         end loop;
         -- forzar cambio de departamento o final de datos si se han tratado tres salarios
         WHILE C1%FOUND AND AUX=LINEA_C1.NUMDE  loop
         FETCH C1 INTO LINEA_C1;
         end loop;
     END LOOP;
        -- cambio de departamento o fin de datos
  END LOOP;
   --  fin de datos
  CLOSE C1;
END;
