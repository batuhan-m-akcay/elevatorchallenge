public class Main
{
    public static void main(String[] args) {
        ElevatorSystem elevatorSystem = new ElevatorSystem();
        elevatorSystem.start();

        try {
            elevatorSystem.addRequest(new Request(0, 2));
            elevatorSystem.addRequest(new Request(4, 2));
            elevatorSystem.addRequest(new Request(0, 1));
            elevatorSystem.addRequest(new Request(0, 4));
            elevatorSystem.addRequest(new Request(0, 2));
            elevatorSystem.addRequest(new Request(4, 2));
            elevatorSystem.addRequest(new Request(0, 1));
            elevatorSystem.addRequest(new Request(1, 0));
            elevatorSystem.addRequest(new Request(2, 1));
            elevatorSystem.addRequest(new Request(2, 1));
            elevatorSystem.addRequest(new Request(0, 2));
            elevatorSystem.addRequest(new Request(4, 2));
            elevatorSystem.addRequest(new Request(0, 1));
            elevatorSystem.addRequest(new Request(0, 2));
            elevatorSystem.addRequest(new Request(4, 2));
            elevatorSystem.addRequest(new Request(0, 1));

            Thread.sleep(4000);
            elevatorSystem.addRequest(new Request(0, 2));
            elevatorSystem.addRequest(new Request(4, 2));
            elevatorSystem.addRequest(new Request(0, 1));
            elevatorSystem.addRequest(new Request(0, 2));
            elevatorSystem.addRequest(new Request(4, 2));
            elevatorSystem.addRequest(new Request(0, 1));
            Thread.sleep(2000);

            elevatorSystem.addRequest(new Request(0, 2));
            elevatorSystem.addRequest(new Request(4, 2));
            elevatorSystem.addRequest(new Request(0, 1));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
