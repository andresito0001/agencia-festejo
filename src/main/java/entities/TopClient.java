package main.java.entities;

public class TopClient {
    public TopClient(final String ci, final String name, final String lname, Integer score) {
        this.ci = ci;
        this.name = name;
        this.lname = lname;
        this.score = score;
    }

    // getters
    public String getCi() { return ci; }
    public String getLname() { return lname; }
    public String getName() { return name; }
    public Integer getScore() { return score; }
    
    private String ci;
    private String name;
    private String lname;
    private Integer score;
}
