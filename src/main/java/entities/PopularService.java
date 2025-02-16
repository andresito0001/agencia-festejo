package main.java.entities;

public class PopularService {
    public PopularService(final String tipoEvento, final String servicio, final Integer contrataciones) {
        this.tipoEvento = tipoEvento;
        this.servicio = servicio;
        this.contrataciones = contrataciones;
    }

    // getters
    public Integer getContrataciones() { return contrataciones; }
    public String getServicio() { return servicio; }
    public String getTipoEvento() { return tipoEvento; }

    //setters
    public void setContrataciones(Integer contrataciones) { this.contrataciones = contrataciones; }
    public void setServicio(String servicio) { this.servicio = servicio; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }
    
    private String tipoEvento;
    private String servicio;
    private Integer contrataciones;
}
