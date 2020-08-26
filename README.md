# elevatorchallenge
My solution to the IBM DC Tower Challenge. My solution uses threads to manage all the requests.

## How are the requests managed?
My solution (in detail the ElevatorSystem-class) uses thread safe lists (CopyOnWriteArrayLists). One that contains seven elevators at default, which shows the available elevators and one that contains all the active requests. 
Since I just have to simulate the movement and did not have to implement the movement in detail, I worked with a permanent method call on new threads. For that I worked with
the ExecutorService-class. At the execute method of the executor service, the first element in the availableElevators-list and the first element in the requests-list are removed and stored
in a variable. Afterwards the time for the movement gets calculated. The thread on which the elevator follows the request sleeps as long as the calculated result. Afterwards the
elevator gets added to the availableElevators-list again.
For more details, check the inline documentation of the code.

## What happens at the permanent method call and what is called?
The start-method of the ElevatorSystem-class calls the execute-method of the executor service on a new thread. If there are no available elevators nothing happens. If there are
no requests made nothing happens. If there are available elevators and requests in their respective lists, then the movement gets simulated.

## How is the movement simulated?
First the time is calculated, that one elevator needs to transport someone from the current floor to the destination floor. This consists of the time the elevator needs to the
current floor of the request and to transport someone from there to the destination floor of the request. I assumed, that the elevator needs probably 2 seconds to pass 1 floor. 
So I calculated the difference between the current floor of the available elevator and the current floor of the request. The result gets multiplied with 2.
The time between the current floor of the request and the destination floor gets calculated the same way. Afterrwards I added 6 seconds for the time the requester needs to get in 
and out of the elevator. The thread sleeps as long as the result in milliseonds. For more information, check the inline documentation of the simulateMovement()-method.

## Testing
For the testing I used the main-method and the console, in which I checked if there were no exceptions as well as no timing errors and I used junit together with the ConcurrentTestRunner.class. 
