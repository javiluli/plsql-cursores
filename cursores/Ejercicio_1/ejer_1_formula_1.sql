-- 1.- PROGRAMAR UN BLOQUE ANONIMO QUE VISUALICE EL NOMEM, FECIN Y UN TERCIO DEL SALAR, DE TODOS LOS EMPLEADOS QUE TIENEN COMISIÓN, ORDENADOS POR NOMEM.

SET SERVEROUTPUT ON;
DECLARE
	-- definir el cursor y su sentencia select
   CURSOR C1 IS SELECT NOMEM, FECIN, ROUND(SALAR/3,2) TERCIOSALAR FROM TEMPLE ORDER BY NOMEM;
  
  -- definimos una variable del mismo tipo del cursor
  LINEA_C1 C1%ROWTYPE;
BEGIN
-- abrimos el cursor
   OPEN C1;
   
   -- leemos la primera fila
   FETCH C1 INTO LINEA_C1;
   
   -- minetras el FECTH encuentre filas
   WHILE C1%FOUND LOOP
       DBMS_OUTPUT.PUT_LINE('Empleado: ' || LINEA_C1.NOMEM || ' Fecha de alta: ' || LINEA_C1.FECIN || ' SALARIO/3: ' || LINEA_C1.TERCIOSALAR);
   -- se lee la siguiente fila
   FETCH C1 INTO LINEA_C1;
   END LOOP;
   
   -- cierra el cursor y limpia memoria
   CLOSE C1;
END; 
