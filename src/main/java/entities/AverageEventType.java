package main.java.entities;

public class AverageEventType {
    public AverageEventType(final String eventType, final Double avg, final Double minAvg, final Double maxAvg) {
        this.eventType = eventType;
        this.avg = avg;
        this.minAvg = minAvg;
        this.maxAvg = maxAvg;
    }

    // getters
    public Double getAvg() { return avg; }
    public String getEventType() { return eventType; }
    public Double getMaxAvg() { return maxAvg; }
    public Double getMinAvg() { return minAvg; }

    final String eventType;
    final Double avg;
    final Double minAvg;
    final Double maxAvg;
}
