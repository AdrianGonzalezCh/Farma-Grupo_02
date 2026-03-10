-- ============================================
-- AJUSTES PARA JAVA - RECETA Y DISPENSACION
-- ============================================

-- Insertar receta y devolver el ID generado.
CREATE OR REPLACE PROCEDURE SP_INSERTAR_RECETA_CON_ID (
    P_ID_PACIENTE      IN NUMBER,
    P_ID_MEDICO        IN NUMBER,
    P_OBSERVACIONES    IN VARCHAR2,
    P_ID_RECETA_OUT    OUT NUMBER
)
AS
BEGIN
    INSERT INTO RECETA (
        ID_PACIENTE,
        ID_MEDICO,
        FECHA_EMISION,
        ESTADO,
        OBSERVACIONES
    ) VALUES (
        P_ID_PACIENTE,
        P_ID_MEDICO,
        SYSDATE,
        'ACTIVA',
        P_OBSERVACIONES
    )
    RETURNING ID_RECETA INTO P_ID_RECETA_OUT;
END;
/

-- Insertar dispensacion y devolver el ID generado.
CREATE OR REPLACE PROCEDURE SP_INSERTAR_DISPENSACION_CON_ID (
    P_ID_RECETA            IN NUMBER,
    P_ID_USUARIO           IN NUMBER,
    P_OBSERVACIONES        IN VARCHAR2,
    P_ID_DISPENSACION_OUT  OUT NUMBER
)
AS
BEGIN
    INSERT INTO DISPENSACION (
        ID_RECETA,
        ID_USUARIO,
        FECHA_DISPENSACION,
        ESTADO,
        OBSERVACIONES
    ) VALUES (
        P_ID_RECETA,
        P_ID_USUARIO,
        SYSDATE,
        'ACTIVA',
        P_OBSERVACIONES
    )
    RETURNING ID_DISPENSACION INTO P_ID_DISPENSACION_OUT;
END;
/