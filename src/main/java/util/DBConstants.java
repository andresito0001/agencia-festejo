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

    public static final List<String> SERVICIOS_COLUMS = List.of (
        "id_servicio",
        "nombre",
        "descripcion",
        "categoria",
        "precio",
        "unidades_servicio"
    );

    public static final List<String> SERVICIOS_TYPES = List.of (
        "serial primary key",
        "text",
        "text",
        "text",
        "double precision",
        "text"
    );

    public static final List<String> ENVENTOS_COLUMS = List.of (
        "id_evento",
        "nombre_evento",
        "fecha_evento",
        "hora_evento",
        "lugar_evento",
        "cliente",
        "id_servicio"
    );

    public static final List<String> ENVENTOS_TYPES = List.of (
        "serial primary key",
        "text",
        "date",
        "time",
        "text",
        "text references clientes(cedula)",
        "integer references servicios(id_servicio)"
    );

    public static final List<String> CLIENTES_COLUMS = List.of (
        "cedula",
        "nombre",
        "apellido",
        "direccion",
        "telefono",
        "correo_electronico"
    );

    public static final List<String> CLIENTES_TYPES = List.of (
        "text primary key",
        "text",
        "text",
        "text",
        "text",
        "text"
    );

}
