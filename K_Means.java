/*
    Daniel Palmer
    K_Means.java
    8/9/2023
 */
import java.util.ArrayList;

public class K_Means {

    /* Manages all changes to each cluster */
    public static boolean calcClusters(ArrayList<Record> records, ArrayList<Cluster> clusters){
        boolean altered = false;
        // new lists to store results after calculation
        ArrayList<Record> newRecords1 = new ArrayList<>(), newRecords2 = new ArrayList<>(), newRecords3 = new ArrayList<>(),
        newRecords4 = new ArrayList<>(), newRecords5 = new ArrayList<>(), newRecords6 = new ArrayList<>();
        for (Record record: records) {
            double[] arr = record.arr();
            //calculate Euclidean distance to each cluster
            double res1 = calcEDistance(arr, clusters, 0);
            double res2 = calcEDistance(arr, clusters, 1);
            double res3 = calcEDistance(arr, clusters, 2);
            double res4 = calcEDistance(arr, clusters, 3);
            double res5 = calcEDistance(arr, clusters, 4);
            double res6 = calcEDistance(arr, clusters, 5);

            //add record to the closest cluster
            if(res1 < res2 && res1 < res3 && res1 < res4 && res1 < res5 && res1 < res6)
                newRecords1.add(record);
            else if(res2 < res1 && res2 < res3 && res2 < res4 && res2 < res5 && res2 < res6)
                newRecords2.add(record);
            else if(res3 < res1 && res3 < res2 && res3 < res4 && res3 < res5 && res3 < res6)
                newRecords3.add(record);
            else if(res4 < res1 && res4 < res3 && res4 < res2 && res4 < res5 && res4 < res6)
                newRecords4.add(record);
            else if(res5 < res1 && res5 < res3 && res5 < res4 && res5 < res2 && res5 < res6)
                newRecords5.add(record);
            else newRecords6.add(record);
        }

        //compare the new cluster lists to the old cluster lists
        altered = checkLists(newRecords1, clusters, 0);
        if(!altered)
            altered = checkLists(newRecords2, clusters, 1);
        if(!altered)
            altered = checkLists(newRecords3, clusters, 2);
        if(!altered)
            altered = checkLists(newRecords4, clusters, 3);
        if(!altered)
            altered = checkLists(newRecords5, clusters, 4);
        if(!altered)
            altered = checkLists(newRecords6, clusters, 5);

        //updates the list for each cluster if changes made
        if(altered) {
            clusters.get(0).setMembers(newRecords1);
            clusters.get(1).setMembers(newRecords2);
            clusters.get(2).setMembers(newRecords3);
            clusters.get(3).setMembers(newRecords4);
            clusters.get(4).setMembers(newRecords5);
            clusters.get(5).setMembers(newRecords6);
        }

        return altered;
    }

    // Calculates Euclidean Distance
    public static double calcEDistance(double[] arr, ArrayList<Cluster> clusters, int index){
        double res = 0;
        double[] temp = clusters.get(index).getCentroid();
        for(int i = 0; i < arr.length; i++){
            res+= Math.pow((arr[i] - temp[i]), 2);
        }
        return Math.sqrt(res);
    }

    public static boolean checkLists(ArrayList<Record> newList, ArrayList<Cluster> clusters, int index){
        ArrayList<Record> oldList = clusters.get(index).getMembers();
        if(newList.size() != oldList.size()) return true; // number of records in cluster different
        for(int i = 0; i < newList.size(); i++){
            double[] temp = newList.get(i).arr();
            double[] temp2 = oldList.get(i).arr();
            for(int j = 0; j < temp.length; j++){
                if(temp[j] != temp2[j])
                    return true; // differing records
            }
        }
        return false; // points in cluster stayed the same
    }
}
