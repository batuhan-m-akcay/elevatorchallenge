import exception.InvalidFloorValueException;
import org.junit.Test;

public class RequestTest
{
    @Test(expected= InvalidFloorValueException.class)
    public void test() throws InvalidFloorValueException {
        Request req = new Request(0, 54);
        req.setDestinationFloor(-1);
    }
}
