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

    public static double SJF(Process[] processes){
        int currentTime = 0;
        int completed=0;
        boolean[] done = new boolean[processes.length];

        System.out.print("\nSJF Order:");

        while(completed < processes.length){
            int shortest = -1;

            for(int i=0; i<processes.length; i++){
                if(!done[i] && processes[i].arrivalTime <= currentTime){
                    if(shortest == -1 || processes[i].burstTime < processes[shortest].burstTime){
                        shortest = i;
                    }
                }
            }
            if(shortest == -1){
                currentTime++;
                continue;
            }
            currentTime += processes[shortest].burstTime;
            processes[shortest].completionTime = currentTime;
            done[shortest] = true;
            completed++;
            System.out.print("P" + processes[shortest].id + " ");
        }
        return display(processes);
    }

    public static double SRT(Process[] processes){
        int n = processes.length;
        int[] remainingTime = new int[n];

        for (int i=0; i<n; i++){
            remainingTime[i] = processes[i].burstTime;
        }

        int completed = 0;
        int currentTime = 0;
        int previous = -1;

        System.out.print("\nSRT Order:");

        while(completed < n){
            int shortest = -1;

            for(int i=0; i<n; i++){
                if(processes[i].arrivalTime <= currentTime && remainingTime[i] > 0){
                    if(shortest == -1 || remainingTime[i] < remainingTime[shortest]){
                        shortest = i;
                    }
                }
            }

            if(shortest == -1){
                currentTime++;
                previous = -1;
                continue;
            }

            if(previous != shortest){
                System.out.print("P" + processes[shortest].id + " ");
                previous = shortest;
            }

            remainingTime[shortest]--;
            currentTime++;

            if(remainingTime[shortest] == 0){
                processes[shortest].completionTime = currentTime;
                completed++;
            }
        }

        return display(processes);
    }

    public static double display(Process[] processes){
        double total = 0;

        System.out.println("\nProcess\tArrival\tBurst\tCompletion\tTurnaround");

        for (Process process : processes){
            process.turnaroundTime = process.completionTime - process.arrivalTime;
            total += process.turnaroundTime;
            System.out.println("P" + process.id + "\t" + process.arrivalTime + "\t" + process.burstTime + "\t" + process.completionTime + "\t\t" + process.turnaroundTime);
        }
        System.out.println("Average Turnaround Time: " + (total / processes.length));
        return total / processes.length;

    }

    public static void run(Process[] processes){
        double fifo = FIFO(processes);
        double sjf = SJF(processes);
        double srt = SRT(processes);

        System.out.println("\n----Algorithm Comparison----");
        System.out.println("FIFO Average Turnaround Time: " + fifo);
        System.out.println("SJF Average Turnaround Time: " + sjf);
        System.out.println("SRT Average Turnaround Time: " + srt);

        double lower = Math.min(fifo, Math.min(sjf, srt));
        System.out.printf("Lowest Average Turnaround Time:", lower);
        
    }


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);


        Process[] processes = {
            new Process(1,0,8),
            new Process(2,1,4),
            new Process(3,2,9),
            new Process(4,3,5)
        };

        System.out.println("-------Assignment Dataset-------");
        run(processes);

    }

}
