import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class KMeans {
    private static final int N = 1000;
    private static final int M = 8;

    private static Point[] shmeia = new Point[N];
    private static Point[] centers = new Point[M];
    private static boolean flag;
    private static float[] shmeiaX1 = new float[M];
    private static float[] shmeiaX2 = new float[M];
    private static int[] teamNumber = new int[M];
    private static float sfalma = 0.0f;

    private static final String PARADEIGMATA_FILENAME  = "Paradeigmata.txt";
    private static final String CENTERS_FILENAME  = "Centers.txt";

    static class Point {
        float x1;
        float x2;
        int team;
        int previousTeam;

        public Point(float x1, float x2) {
            this.x1 = x1;
            this.x2 = x2;
            this.team = -1;
            this.previousTeam = -2;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFile();
        float minError = Float.MAX_VALUE;
        float totalError = 0.0f;
        Point[] finalCenters = null;
        int[] finalTeams = new int[N];
        System.out.println("M = " + M);
        for (int run = 1; run <= 20; run++) {
            randomCenters();
            int times = 0;
            while (true) {
                Point[] oldCenters = copyCenters(centers);
                makeTeams();
                setNewCenters();
                times++;

                if (!hasCentersChanged(oldCenters, centers)) {
                    break;
                }
                //System.out.printf("Changed Centers %d times%n", times);
            }
            calculateError();
            float currentError = sfalma / N;
            totalError += currentError;
            System.out.printf("Run #%d, Centers changed %d times, Clustering Error = %f%n",
                              run, times, currentError);
            if (currentError < minError) {
                minError = currentError;
                finalCenters = copyCenters(centers);
                for (int i = 0; i < N; i++) {
                    finalTeams[i] = shmeia[i].team;
                }
            }
        }
        float meanError = totalError / 20;
        System.out.printf("Mean Clustering Error over 20 runs = %f%n", meanError);
        System.out.printf("Minimum Error = %f%n", minError);
        writeCentersToFile(finalCenters);
    }

    private static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(PARADEIGMATA_FILENAME));
        for (int i = 0; i < N; i++) {
            float x1 = sc.nextFloat();
            float x2 = sc.nextFloat();
            shmeia[i] = new Point(x1, x2);
        }
        sc.close();
    }

    private static void randomCenters() {
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < M; i++) {
            int randomPoint = rand.nextInt(N);
            centers[i] = new Point(shmeia[randomPoint].x1, shmeia[randomPoint].x2);
        }
    }

    private static void makeTeams() {
        for (int i = 0; i < N; i++) {
            float dx = centers[0].x1 - shmeia[i].x1;
            float dy = centers[0].x2 - shmeia[i].x2;
            float minDist = (float) Math.sqrt(dx * dx + dy * dy);  
            int bestTeam = 0;  
    
            for (int j = 1; j < M; j++) {
                dx = centers[j].x1 - shmeia[i].x1;
                dy = centers[j].x2 - shmeia[i].x2;
                float distance = (float) Math.sqrt(dx * dx + dy * dy);  
    
                if (distance < minDist) { 
                    minDist = distance;
                    bestTeam = j;
                }
            }
    
            if (shmeia[i].previousTeam != bestTeam) {
                flag = true;
            }
            shmeia[i].team = bestTeam;
            shmeia[i].previousTeam = bestTeam;
        }
    }
    

    private static void setNewCenters() {
        resetCoordinates();
        sumPointsByTeam();
        calculateNewCenters();
    }
    
    private static void resetCoordinates() {
        for (int i = 0; i < M; i++) {
            shmeiaX1[i] = 0.0f;
            shmeiaX2[i] = 0.0f;
            teamNumber[i] = 0;
        }
    }
    
    private static void sumPointsByTeam() {
        for (int i = 0; i < N; i++) {
            int team = shmeia[i].team;
            teamNumber[team]++;
            shmeiaX1[team] += shmeia[i].x1;
            shmeiaX2[team] += shmeia[i].x2;
        }
    }
    
    private static void calculateNewCenters() {
        for (int i = 0; i < M; i++) {
            if (teamNumber[i] != 0) {
                centers[i].x1 = shmeiaX1[i] / teamNumber[i];
                centers[i].x2 = shmeiaX2[i] / teamNumber[i];
            }
        }
    }
    

   
    private static Point[] copyCenters(Point[] point) {
        Point[] copy = new Point[point.length];
        for (int i = 0; i < point.length; i++) {
            copy[i] = new Point(point[i].x1, point[i].x2);
        }
        return copy;
    }

    private static void writeCentersToFile(Point[] centers) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(CENTERS_FILENAME);
        for (int i = 0; i < M; i++) {
            pw.printf("%f %f%n", centers[i].x1, centers[i].x2);
        }
        pw.close();
    }

    private static boolean hasCentersChanged(Point[] oldCenters, Point[] newCenters) {
        for (int i = 0; i < oldCenters.length; i++) {
            float dx = oldCenters[i].x1 - newCenters[i].x1;
            float dy = oldCenters[i].x2 - newCenters[i].x2;
            float distanceSquared = dx * dx + dy * dy;  
            if (distanceSquared != 0) {
                return true;
            }
        }
        return false;
    }

    private static void calculateError() {
        sfalma = 0.0f;
        for (int i = 0; i < N; i++) {
            float dx = centers[shmeia[i].team].x1 - shmeia[i].x1;
            float dy = centers[shmeia[i].team].x2 - shmeia[i].x2;
            sfalma += (float) Math.sqrt(dx * dx + dy * dy);
        }
    }
    
}
