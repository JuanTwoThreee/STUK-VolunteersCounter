package stukVolonteer;

import java.sql.Date;

public class Entry {
    private workType work;
    private Date date;

    private class workType {
        private String work;
        private int points;

        private workType(String w, int p){
            this.work = w;
            this.points = p;
        }
    }

    public Entry(workType wT, Date d) {
        this.work = wT;
        this.date = d;
    }

    public Date getDate(){
        return this.date;
    }

    public workType getWork(){
        return this.work;
    }
}
