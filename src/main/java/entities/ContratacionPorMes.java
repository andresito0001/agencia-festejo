package main.java.entities;

public class ContratacionPorMes {
    public ContratacionPorMes(int mes, String nombreServicio, int totalContrataciones) {
        this.mes = mes;
        this.nombreServicio = nombreServicio;
        this.totalContrataciones = totalContrataciones;
    }

    // Getters
    public int getMes() { return mes; }
    public String getNombreServicio() { return nombreServicio; }
    public int getTotalContrataciones() { return totalContrataciones; }

    public String getMesNombre() {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[mes - 1];
    }

    private int mes;
    private String nombreServicio;
    private int totalContrataciones;
}