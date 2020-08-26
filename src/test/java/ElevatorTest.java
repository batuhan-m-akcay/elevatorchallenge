import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElevatorTest
{

    @Test
    public void testDefaultStorey()
    {
        Elevator elevator = new Elevator();
        assertEquals(0, elevator.getCurrentFloor());
    }

}
