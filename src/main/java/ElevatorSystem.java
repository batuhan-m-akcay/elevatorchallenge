import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ElevatorSystem
{
    private CopyOnWriteArrayList<Elevator> availableElevators;
    private CopyOnWriteArrayList<Request> requests;
    private final ExecutorService executorService;
    private final SimpleDateFormat simpleDateFormat;


    public ElevatorSystem()
    {
       availableElevators = new CopyOnWriteArrayList<>();
       for(int i=0; i<7; i++)
       {
           availableElevators.add(new Elevator());
       }
       requests = new CopyOnWriteArrayList<>();
       executorService = Executors.newFixedThreadPool(7);
       simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss.SSS");
    }

    /**
     * This method ensures that the execute method of the executor service is called permanently (see method start).
     * If there are no elevators available at the time and there are no requests, then nothing happens. (The if-statement prevents the possible appearance of an IndexOutOfBounds-Exception)
     * If there is a request but not an elevator available at the moment, then nothing happens until there is an elevator available.
     * If there is an elevator available and there are requests, then the first element of the availableElevator-list and
     * the first element of the request-list get removed and are saved as variables.
     * Afterwards, the method simulateMovement() is called and delays the thread. (see simulateMethod())
     * After the thread continues, the current floor of the request is updated and the elevators is added to the list of free elevators.
     */
    public void start()
    {
        new Thread(() -> {
            while (true) {

                executorService.execute(() -> {
                    if(!requests.isEmpty() & !availableElevators.isEmpty()) {
                        Request request = requests.get(0);
                        Elevator elevator = availableElevators.get(0);
                        requests.remove(request);
                        availableElevators.remove(elevator);

                        outputRequest(request, elevator);
                        outputElevatorOrigin(request, elevator);
                        simulateMovement(request, elevator);

                        elevator.setCurrentFloor(request.getDestinationFloor());
                        makeElevatorAvailable(elevator);
                        outputElevatorDestination(request, elevator);
                    }
                });
            }
        }).start();
    }


    /**
     * This method adds the removed elevator to the list of the free elevators.
     * @param elevator the elevator that should be added to the list
     */
    private void makeElevatorAvailable(Elevator elevator) {
        availableElevators.add(elevator);
    }

    /**
     * This method adds an request to the list 'requests'.
     * @param request the request that should be added to the list
     */
    public void addRequest(Request request)
    {
        try
        {
            requests.add(request);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method simulates the time, that an elevator needs to transport someone.
     * I assume, that it needs 2 seconds to pass an floor and it needs 6 seconds to open and close the door.
     * So I calculated the time that an elevator needs to arrive at the source of the request (difference between
     * elevator) and the time that an elevator needs to transport one person to the destination floor of the request.
     * Last but not least I multiply the time with 1000 to get it in milliseconds and let the working thread sleep.
     * @param request the first request from the list of the requests (chronological)
     * @param elevator the first elevator that is available
     */
    private void simulateMovement(Request request, Elevator elevator)
    {
        try
        {
            int elevatorArrivalTime = Math.abs(elevator.getCurrentFloor() - request.getCurrentFloor())*2;
            int transportTime = Math.abs(request.getCurrentFloor() - request.getDestinationFloor())*2;
            long time = elevatorArrivalTime + transportTime + 6;
            Thread.sleep(time*1000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * This method returns the current time in the simpleDateFormat, that was defined in the constructor.
     * @return current time in simpleDateFormat
     */
    private String currentTime() {
         return simpleDateFormat.format(new Date(java.lang.System.currentTimeMillis()));
    }

    /**
     * This method returns the current thread as a string value
     * @return current thread as a string value
     */
    private String currentThread(){
        return ", Working Thread-ID: "+Thread.currentThread();
    }

    /**
     * This method logs to the console which request an elevator was assigned to.
     * It shows the current time, the current thread, the type of output as well as the essential information.
     * Type R = Type "Request" (from which floor to which floor should an elevator move)
     * @param request
     * @param elevator
     */
    private void outputRequest(Request request, Elevator elevator)
    {
        System.out.println(currentTime()+currentThread()+", Type R, Request: "+request.getRequestId()+", Elevator "+elevator.getElevatorId() +" will move from floor "+request.getCurrentFloor()+" to "+request.getDestinationFloor());
    }

    /**
     * This method logs to the console where the origin of an elevator is.
     * It shows the current time, the current thread, the type of output as well as the essential information.
     * Type O = Type "Origin" (the origin of the elevator)
     * @param request
     * @param elevator
     */
    private void outputElevatorOrigin(Request request, Elevator elevator)
    {
        System.out.println(currentTime()+currentThread()+", Type O, Request: "+request.getRequestId()+", Elevator "+elevator.getElevatorId() +" is currently at the "+elevator.getCurrentFloor());
    }

    /**
     * This method logs to the console which request an elevator was assigned to.
     * It shows the current time, the current thread, the type of output as well as the essential information.
     * Type A = Type "Arrival" (on which floor did the elevator arrive?)
     * @param request
     * @param elevator
     */
    private void outputElevatorDestination(Request request, Elevator elevator)
    {
        System.out.println(currentTime()+currentThread()+", Type A, Request: "+request.getRequestId()+", Elevator "+elevator.getElevatorId() +" arrived at the "+elevator.getCurrentFloor());
    }

    /**
     * This method returns the list of available elevators that are contained in the list.
     */
    public CopyOnWriteArrayList<Elevator> checkAvailableElevators()
    {
        return availableElevators;
    }

    /**
     * This method returns the list of available elevators that are contained in the list.
     */
    public long getAvailableElevatorsNumber()
    {
        long i=0;
        for(Elevator a: availableElevators)
        {
            i++;
        }
        return i;
    }

    public long giveSize()
    {
        return availableElevators.size();
    }

}
