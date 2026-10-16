import java.util.*;

class Process {
    int id;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnaroundTime;

    public Process(int id, int arrivalTime, int burstTime){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class CPUscheduling {

    public static double FIFO(Process[] processes){ //FIFO
        Process []p = processes.clone();
        Arrays.sort(p, Comparator.comparingInt(a->a.arrivalTime));

        int currentTime = 0;
        System.out.print("\nFIFO Order:");

        for(Process process : p){
            currentTime = Math.max(currentTime, process.arrivalTime);
            currentTime += process.burstTime;
            process.completionTime = currentTime;
            System.out.print("P" + process.id + " ");
        }

        return display(p);

    }
}
