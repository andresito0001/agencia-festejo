package main.java.util;

import java.util.List;

public class DBConstants {
    public static final List<String> ADMINISTRADORES_CoLUMN = List.of (
        "id",
        "nombre_usuario",
        "password"
    );

    public static final List<String> ADMINISTRADORES_TYPES = List.of (
        "serial primary key",
        "text",
        "text"
    );

    public static final List<String> EMPLEADOS_COLUMNS = List.of (
        "id_empleado",
        "nombre",
        "apellido",
        "cargo",
        "salario"
    );

    public static final List<String> EMPLEADOS_TYPES = List.of (
        "serial primary key",
        "text",
        "text",
        "text",
        "double precision"
    );

}
