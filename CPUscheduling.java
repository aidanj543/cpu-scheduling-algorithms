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
    public static void main(String[] args){
        Process [] processes = {
            new Process(1, 0, 8),
            new Process(2, 1, 4),
            new Process(3, 2, 9),
            new Process(4, 3, 5)
        };
    }
}
