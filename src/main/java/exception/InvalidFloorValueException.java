package exception;

public class InvalidFloorValueException extends Exception
{
    public InvalidFloorValueException(String errorMessage)
    {
        super(errorMessage + "Context: The value is invalid for a floor. There is no such floor, Solution: Change the current floor or the destination floor in your request");
    }

}
