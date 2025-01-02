/*
    Daniel Palmer
    Cluster.java
    8/9/2023
 */
import java.util.ArrayList;

public class Cluster {
    private ArrayList<Record> members;
    private double[] centroid;

    public Cluster(ArrayList<Record> members){
        this.members = members;
        calcCentroid(this.members);
    }

    public double[] getCentroid() {
        return centroid;
    }

    public void setMembers(ArrayList<Record> members){
        this.members = members;
        calcCentroid(this.members);
    }

    public void calcCentroid(ArrayList<Record> members){
        double[] centroid = new double[60];
        for(int i = 0; i < centroid.length; i++){
            double sum = 0;
            for (Record records: members) {
                double[] temp = records.arr();
                sum += temp[i];
            }
            centroid[i] = sum / members.size();
        }
        this.centroid = centroid;
    }

    public ArrayList<Record> getMembers() {
        return members;
    }
}
