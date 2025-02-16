package main.java.entities;

public class EventRank {
    public EventRank(final String eventType, final String peekSeason, final String lowSeaon) {
        this.enevntType = eventType;
        this.peekSeason = peekSeason;
        this.lowSeason = lowSeaon;
    }

    // getters
    public String getEnevntType() { return enevntType; }
    public String getLowSeason() { return lowSeason; }
    public String getPeekSeason() { return peekSeason; }

    final String enevntType;
    final String peekSeason;
    final String lowSeason;
}
