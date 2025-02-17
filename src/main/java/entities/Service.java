package main.java.entities;

public class Service {
    public Service(String id, String serviceName) {
        this.id = id;
        this.serviceName = serviceName;
    }

    public String getId() { return id; }
    public String getServiceName() { return serviceName; }

    private String id;
    private String serviceName; 
}
