/*
    Daniel Palmer
    K_Means_Clustering.java
    8/9/2023
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class K_Means_Clustering {
    public static void main(String[] args) {
        ArrayList<Record> records = new ArrayList<>();
        ArrayList<Cluster> clusters = new ArrayList<>(); // keeps track of the 6 clusters

        /* Read and store data from input file */
        try{
            File inf = new File("synthetic_control_data.txt");
            Scanner fin = new Scanner(inf);
            int count = 0;
            while(fin.hasNextDouble()){
                ArrayList<Record> tempList = new ArrayList<>();
                double[] temp = new double[60];
                for(int i = 0; i < 60; i++){
                    temp[i] = fin.nextDouble();
                }
                records.add(new Record(temp));
                /* initializes the x00th value as starting point for cluster */
                if((count + 1) % 100 == 0){
                    tempList.add(records.get(count));
                    clusters.add(new Cluster(tempList));
                }
                count++;
            }
            records.trimToSize();
            clusters.trimToSize();
            fin.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Input file not found");
            return;
        }

        /* while loop to compute the k-means clustering */
        boolean changed = true;
        while(changed){
           changed = K_Means.calcClusters(records, clusters);
        }// stops when none of the clusters change

        /* prints the results of the 6 clusters into 6 text files */
        try {
            int count = 1;
            for (Cluster cluster : clusters) {
                File fout = new File("cluster" + count + ".txt");
                PrintStream output = new PrintStream(fout);
                System.setOut(output);

                ArrayList<Record> tempList = cluster.getMembers();
                for (Record result : tempList) {
                    System.out.println(result.toString());
                }
                count++;
                output.close();
            }
        }
        catch(FileNotFoundException e){System.out.println("Error in writing output text files");}
    }
}
