-- Ajuste de sesión para permitir la creación del usuario en el entorno actual.
ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;

-- Creación del usuario que será utilizado para el proyecto.
CREATE USER FARMA IDENTIFIED BY Farma123;

-- Asignación de roles básicos al usuario.
GRANT CONNECT, RESOURCE TO FARMA;

-- Asignación de privilegios necesarios para trabajar con objetos de base de datos.
GRANT CREATE SESSION TO FARMA;
GRANT CREATE TABLE TO FARMA;
GRANT CREATE VIEW TO FARMA;
GRANT CREATE PROCEDURE TO FARMA;
GRANT CREATE SEQUENCE TO FARMA;
GRANT CREATE TRIGGER TO FARMA;
GRANT CREATE TYPE TO FARMA;

-- Asignación de cuota ilimitada sobre el tablespace de usuarios por defecto en Oracle.
ALTER USER FARMA QUOTA UNLIMITED ON USERS;