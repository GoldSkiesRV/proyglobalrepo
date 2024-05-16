CREATE OR REPLACE PROCEDURE sp_insertAndListProducts (
    idProducto IN NUMBER,
    nombre IN VARCHAR2,
    fec_registro IN DATE,
    cursorProductos OUT SYS_REFCURSOR,
    codigoRespuesta OUT NUMBER,
    mensajeRespuesta OUT VARCHAR2
)
AS
BEGIN
    DECLARE
        v_error_code NUMBER;
        v_error_msg VARCHAR2(200);
    BEGIN
        INSERT INTO Productos (id, nombre, fec_registro)
        VALUES (idProducto, nombre, fec_registro);
        
        OPEN cursorProductos FOR
        SELECT id, nombre, fec_registro
        FROM Productos
        WHERE TRUNC(fec_registro) = TRUNC(SYSDATE);

        codigoRespuesta := 0;
        mensajeRespuesta := 'Ejecucion con Exito';
    EXCEPTION
        WHEN OTHERS THEN
            -- Manejo de errores
            v_error_code := SQLCODE;
            v_error_msg := SUBSTR(SQLERRM, 1, 200);

            codigoRespuesta := v_error_code;
            mensajeRespuesta := 'Error: ' || v_error_msg;
    END;
END;
