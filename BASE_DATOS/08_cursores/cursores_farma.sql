-- ============================================
-- CURSORES DEL PROYECTO FARMA
-- ============================================

-------------------------------------------------
-- 1. Cursor de pacientes activos
-------------------------------------------------
DECLARE
    CURSOR C_PACIENTES IS
        SELECT ID_PACIENTE, NOMBRE, APELLIDO1
        FROM PACIENTE
        WHERE ESTADO = 'ACTIVO';
BEGIN
    FOR R IN C_PACIENTES LOOP
        DBMS_OUTPUT.PUT_LINE('PACIENTE: ' || R.ID_PACIENTE || ' - ' || R.NOMBRE || ' ' || R.APELLIDO1);
    END LOOP;
END;
/

-------------------------------------------------
-- 2. Cursor de medicos activos
-------------------------------------------------
DECLARE
    CURSOR C_MEDICOS IS
        SELECT ID_MEDICO, NOMBRE, APELLIDO1
        FROM MEDICO
        WHERE ESTADO = 'ACTIVO';
BEGIN
    FOR R IN C_MEDICOS LOOP
        DBMS_OUTPUT.PUT_LINE('MEDICO: ' || R.ID_MEDICO || ' - ' || R.NOMBRE || ' ' || R.APELLIDO1);
    END LOOP;
END;
/

-------------------------------------------------
-- 3. Cursor de medicamentos activos
-------------------------------------------------
DECLARE
    CURSOR C_MEDICAMENTOS IS
        SELECT ID_MEDICAMENTO, NOMBRE, STOCK_ACTUAL
        FROM MEDICAMENTO
        WHERE ESTADO = 'ACTIVO';
BEGIN
    FOR R IN C_MEDICAMENTOS LOOP
        DBMS_OUTPUT.PUT_LINE('MEDICAMENTO: ' || R.ID_MEDICAMENTO || ' - ' || R.NOMBRE || ' STOCK=' || R.STOCK_ACTUAL);
    END LOOP;
END;
/

-------------------------------------------------
-- 4. Cursor de usuarios por rol
-------------------------------------------------
DECLARE
    CURSOR C_USUARIOS IS
        SELECT U.ID_USUARIO, U.NOMBRE, U.APELLIDO1, R.NOMBRE AS ROL
        FROM USUARIO U
        INNER JOIN ROL R ON U.ID_ROL = R.ID_ROL;
BEGIN
    FOR R IN C_USUARIOS LOOP
        DBMS_OUTPUT.PUT_LINE('USUARIO: ' || R.ID_USUARIO || ' - ' || R.NOMBRE || ' ' || R.APELLIDO1 || ' - ' || R.ROL);
    END LOOP;
END;
/

-------------------------------------------------
-- 5. Cursor de recetas activas
-------------------------------------------------
DECLARE
    CURSOR C_RECETAS IS
        SELECT ID_RECETA, ESTADO, FECHA_EMISION
        FROM RECETA
        WHERE ESTADO = 'ACTIVA';
BEGIN
    FOR R IN C_RECETAS LOOP
        DBMS_OUTPUT.PUT_LINE('RECETA: ' || R.ID_RECETA || ' - ' || R.ESTADO || ' - ' || TO_CHAR(R.FECHA_EMISION, 'DD/MM/YYYY'));
    END LOOP;
END;
/

-------------------------------------------------
-- 6. Cursor de detalle de receta
-------------------------------------------------
DECLARE
    CURSOR C_DET_RECETA IS
        SELECT ID_RECETA_DETALLE, ID_RECETA, CANTIDAD_AUTORIZADA, CANTIDAD_DISPENSADA
        FROM RECETA_DETALLE;
BEGIN
    FOR R IN C_DET_RECETA LOOP
        DBMS_OUTPUT.PUT_LINE('DETALLE: ' || R.ID_RECETA_DETALLE || ' RECETA=' || R.ID_RECETA ||
                             ' AUT=' || R.CANTIDAD_AUTORIZADA || ' DISP=' || R.CANTIDAD_DISPENSADA);
    END LOOP;
END;
/

-------------------------------------------------
-- 7. Cursor de recetas pendientes
-------------------------------------------------
DECLARE
    CURSOR C_RECETAS_PEND IS
        SELECT ID_RECETA_DETALLE, ID_RECETA, (CANTIDAD_AUTORIZADA - CANTIDAD_DISPENSADA) AS PENDIENTE
        FROM RECETA_DETALLE
        WHERE CANTIDAD_DISPENSADA < CANTIDAD_AUTORIZADA;
BEGIN
    FOR R IN C_RECETAS_PEND LOOP
        DBMS_OUTPUT.PUT_LINE('DETALLE PENDIENTE: ' || R.ID_RECETA_DETALLE || ' RECETA=' || R.ID_RECETA ||
                             ' PENDIENTE=' || R.PENDIENTE);
    END LOOP;
END;
/

-------------------------------------------------
-- 8. Cursor de medicamentos con stock bajo
-------------------------------------------------
DECLARE
    CURSOR C_STOCK_BAJO IS
        SELECT ID_MEDICAMENTO, NOMBRE, STOCK_ACTUAL, STOCK_MINIMO
        FROM MEDICAMENTO
        WHERE STOCK_ACTUAL <= STOCK_MINIMO;
BEGIN
    FOR R IN C_STOCK_BAJO LOOP
        DBMS_OUTPUT.PUT_LINE('STOCK BAJO: ' || R.NOMBRE || ' ACTUAL=' || R.STOCK_ACTUAL || ' MIN=' || R.STOCK_MINIMO);
    END LOOP;
END;
/

-------------------------------------------------
-- 9. Cursor de dispensaciones activas
-------------------------------------------------
DECLARE
    CURSOR C_DISP IS
        SELECT ID_DISPENSACION, ID_RECETA, FECHA_DISPENSACION
        FROM DISPENSACION
        WHERE ESTADO = 'ACTIVA';
BEGIN
    FOR R IN C_DISP LOOP
        DBMS_OUTPUT.PUT_LINE('DISPENSACION: ' || R.ID_DISPENSACION || ' RECETA=' || R.ID_RECETA);
    END LOOP;
END;
/

-------------------------------------------------
-- 10. Cursor de detalle de dispensacion
-------------------------------------------------
DECLARE
    CURSOR C_DET_DISP IS
        SELECT ID_DISPENSACION_DETALLE, ID_DISPENSACION, ID_MEDICAMENTO, CANTIDAD_DISPENSADA
        FROM DISPENSACION_DETALLE;
BEGIN
    FOR R IN C_DET_DISP LOOP
        DBMS_OUTPUT.PUT_LINE('DETALLE DISP: ' || R.ID_DISPENSACION_DETALLE || ' DISP=' || R.ID_DISPENSACION ||
                             ' MED=' || R.ID_MEDICAMENTO || ' CANT=' || R.CANTIDAD_DISPENSADA);
    END LOOP;
END;
/

-------------------------------------------------
-- 11. Cursor de bitacora
-------------------------------------------------
DECLARE
    CURSOR C_BITACORA IS
        SELECT ID_BITACORA, ACCION, ENTIDAD_AFECTADA, FECHA_HORA
        FROM BITACORA
        ORDER BY FECHA_HORA DESC;
BEGIN
    FOR R IN C_BITACORA LOOP
        DBMS_OUTPUT.PUT_LINE('BITACORA: ' || R.ID_BITACORA || ' ' || R.ACCION || ' ' || R.ENTIDAD_AFECTADA);
    END LOOP;
END;
/

-------------------------------------------------
-- 12. Cursor de movimientos de inventario
-------------------------------------------------
DECLARE
    CURSOR C_MOVIMIENTOS IS
        SELECT ID_MOVIMIENTO, ID_MEDICAMENTO, TIPO_MOVIMIENTO, CANTIDAD
        FROM MOVIMIENTO_INVENTARIO;
BEGIN
    FOR R IN C_MOVIMIENTOS LOOP
        DBMS_OUTPUT.PUT_LINE('MOVIMIENTO: ' || R.ID_MOVIMIENTO || ' MED=' || R.ID_MEDICAMENTO ||
                             ' TIPO=' || R.TIPO_MOVIMIENTO || ' CANT=' || R.CANTIDAD);
    END LOOP;
END;
/

-------------------------------------------------
-- 13. Cursor de pacientes con recetas
-------------------------------------------------
DECLARE
    CURSOR C_PACIENTES_RECETA IS
        SELECT DISTINCT P.ID_PACIENTE, P.NOMBRE, P.APELLIDO1
        FROM PACIENTE P
        INNER JOIN RECETA R ON P.ID_PACIENTE = R.ID_PACIENTE;
BEGIN
    FOR R IN C_PACIENTES_RECETA LOOP
        DBMS_OUTPUT.PUT_LINE('PACIENTE CON RECETA: ' || R.ID_PACIENTE || ' - ' || R.NOMBRE || ' ' || R.APELLIDO1);
    END LOOP;
END;
/

-------------------------------------------------
-- 14. Cursor de medicos con recetas emitidas
-------------------------------------------------
DECLARE
    CURSOR C_MEDICOS_RECETA IS
        SELECT DISTINCT M.ID_MEDICO, M.NOMBRE, M.APELLIDO1
        FROM MEDICO M
        INNER JOIN RECETA R ON M.ID_MEDICO = R.ID_MEDICO;
BEGIN
    FOR R IN C_MEDICOS_RECETA LOOP
        DBMS_OUTPUT.PUT_LINE('MEDICO CON RECETA: ' || R.ID_MEDICO || ' - ' || R.NOMBRE || ' ' || R.APELLIDO1);
    END LOOP;
END;
/

-------------------------------------------------
-- 15. Cursor de medicamentos que requieren receta
-------------------------------------------------
DECLARE
    CURSOR C_MEDS_RECETA IS
        SELECT ID_MEDICAMENTO, NOMBRE
        FROM MEDICAMENTO
        WHERE REQUIERE_RECETA = 'SI';
BEGIN
    FOR R IN C_MEDS_RECETA LOOP
        DBMS_OUTPUT.PUT_LINE('MEDICAMENTO CON RECETA: ' || R.ID_MEDICAMENTO || ' - ' || R.NOMBRE);
    END LOOP;
END;
/