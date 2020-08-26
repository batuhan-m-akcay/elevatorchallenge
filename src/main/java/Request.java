import exception.InvalidFloorValueException;

import java.util.UUID;

public class Request
{
    private final String requestId;
    private int currentFloor;
    private int destinationFloor;

    public Request(int currentFloor, int destinationFloor)
    {
        this.requestId = UUID.randomUUID().toString();
        try {
              setCurrentFloor(currentFloor);
              setDestinationFloor(destinationFloor);
        }
        catch (InvalidFloorValueException e)
        {
            e.getMessage();
        }

    }

    public String getRequestId()
    {
        return requestId;
    }

    public int getCurrentFloor()
    {
        return currentFloor;
    }

    public int getDestinationFloor()
    {
        return destinationFloor;
    }

    public void setCurrentFloor(int currentFloor) throws InvalidFloorValueException
    {
        if(currentFloor > 0 && currentFloor < 55)
        {
            this.currentFloor = currentFloor;
        }
        else
        {
            throw new InvalidFloorValueException("902: ");
        }
    }

    public void setDestinationFloor(int destinationFloor) throws InvalidFloorValueException
    {
        if(destinationFloor > 0 && destinationFloor < 55)
        {
            this.destinationFloor = destinationFloor;
        }
        else
        {
            throw new InvalidFloorValueException("902: ");
        }
    }

    public String toString()
    {
        return "ElevatorRequest: "+ requestId
                + " wants the elevator to move from the "
                + currentFloor + ". floor to the "
                +destinationFloor+". floor.";
    }
}
