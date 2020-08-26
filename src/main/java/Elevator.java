import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Elevator
{
    private final String elevatorId;
    private int currentFloor;

    public Elevator()
    {
        this.elevatorId = UUID.randomUUID().toString();
        this.currentFloor = 0;
    }


    public String getElevatorId()
    {
        return elevatorId;
    }

    public int getCurrentFloor()
    {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor)
    {
        this.currentFloor = currentFloor;
    }

    @Override
    public String toString() {
        return "Elevator "+ elevatorId + "is currently at the "
                + currentFloor + ". floor. ";
    }
}