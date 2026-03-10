-- ============================================
-- TRIGGERS DEL PROYECTO FARMA
-- ============================================

-------------------------------------------------
-- 1. Registrar bitacora al crear receta
-------------------------------------------------
CREATE OR REPLACE TRIGGER TRG_BITACORA_INSERT_RECETA
AFTER INSERT ON RECETA
FOR EACH ROW
BEGIN
    INSERT INTO BITACORA (
        ID_USUARIO,
        ACCION,
        ENTIDAD_AFECTADA,
        ID_REFERENCIA,
        FECHA_HORA,
        DETALLE
    ) VALUES (
        1,
        'INSERT',
        'RECETA',
        :NEW.ID_RECETA,
        SYSDATE,
        'Se registro una nueva receta'
    );
END;
/

-------------------------------------------------
-- 2. Registrar bitacora al crear dispensacion
-------------------------------------------------
CREATE OR REPLACE TRIGGER TRG_BITACORA_INSERT_DISPENSACION
AFTER INSERT ON DISPENSACION
FOR EACH ROW
BEGIN
    INSERT INTO BITACORA (
        ID_USUARIO,
        ACCION,
        ENTIDAD_AFECTADA,
        ID_REFERENCIA,
        FECHA_HORA,
        DETALLE
    ) VALUES (
        :NEW.ID_USUARIO,
        'INSERT',
        'DISPENSACION',
        :NEW.ID_DISPENSACION,
        SYSDATE,
        'Se registro una nueva dispensacion'
    );
END;
/

-------------------------------------------------
-- 3. Registrar movimiento de inventario al dispensar
-------------------------------------------------
CREATE OR REPLACE TRIGGER TRG_MOVIMIENTO_DISPENSACION_DET
AFTER INSERT ON DISPENSACION_DETALLE
FOR EACH ROW
BEGIN
    INSERT INTO MOVIMIENTO_INVENTARIO (
        ID_MEDICAMENTO,
        TIPO_MOVIMIENTO,
        CANTIDAD,
        MOTIVO,
        FECHA_MOVIMIENTO,
        ID_USUARIO
    ) VALUES (
        :NEW.ID_MEDICAMENTO,
        'SALIDA',
        :NEW.CANTIDAD_DISPENSADA,
        'Salida por dispensacion',
        SYSDATE,
        1
    );
END;
/

-------------------------------------------------
-- 4. Completar receta automaticamente si ya no hay pendiente
-------------------------------------------------
CREATE OR REPLACE TRIGGER TRG_COMPLETAR_RECETA
AFTER UPDATE OF CANTIDAD_DISPENSADA ON RECETA_DETALLE
FOR EACH ROW
DECLARE
    V_ID_RECETA    NUMBER;
    V_PENDIENTES   NUMBER;
BEGIN
    V_ID_RECETA := :NEW.ID_RECETA;

    SELECT COUNT(*)
    INTO V_PENDIENTES
    FROM RECETA_DETALLE
    WHERE ID_RECETA = V_ID_RECETA
      AND CANTIDAD_DISPENSADA < CANTIDAD_AUTORIZADA;

    IF V_PENDIENTES = 0 THEN
        UPDATE RECETA
        SET ESTADO = 'COMPLETADA'
        WHERE ID_RECETA = V_ID_RECETA;
    END IF;
END;
/

-------------------------------------------------
-- 5. Reabrir receta si un detalle vuelve a quedar pendiente
-------------------------------------------------
CREATE OR REPLACE TRIGGER TRG_REABRIR_RECETA
AFTER UPDATE OF CANTIDAD_DISPENSADA ON RECETA_DETALLE
FOR EACH ROW
DECLARE
    V_ID_RECETA  NUMBER;
BEGIN
    V_ID_RECETA := :NEW.ID_RECETA;

    IF :NEW.CANTIDAD_DISPENSADA < :NEW.CANTIDAD_AUTORIZADA THEN
        UPDATE RECETA
        SET ESTADO = 'ACTIVA'
        WHERE ID_RECETA = V_ID_RECETA;
    END IF;
END;
/