import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

import static org.junit.Assert.assertEquals;

@RunWith(ConcurrentTestRunner.class)
public class ElevatorSystemTest
{
    private ElevatorSystem elevatorSystem;

    @Before
    public void initialCount() {
        elevatorSystem = new ElevatorSystem();
        elevatorSystem.start();
    }

    @Test
    public void test()
    {
        elevatorSystem.addRequest(new Request(0, 45));
        elevatorSystem.addRequest(new Request(0, 35));
        elevatorSystem.addRequest(new Request(4, 44));
    }

    public void after()
    {
        assertEquals(5, elevatorSystem.getAvailableElevatorsNumber());
    }

}
