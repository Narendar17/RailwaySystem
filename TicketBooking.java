package DemoProjects.RailwayApplication;

import java.util.*;
public class TicketBooking {
    static int availableLower = 1;
    static int availableMiddle = 1;
    static int availableUpper = 1;
    static int availableRAC = 1;
    static int availableWaitingList = 1;

    static  Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList = new LinkedList<>();
    static List<Integer> bookedList = new ArrayList<>();

    static List<Integer> lowerBirthPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleBirthPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperBirthPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> racPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitingListPosition = new ArrayList<>(Arrays.asList(1));
    static Map<Integer, PassangerDetails> passanger = new HashMap<>();
    public void bookTicker(PassangerDetails p, int berthInfo, String allocatedBerth){
        p.number = berthInfo;
        p.alloted = allocatedBerth;
        passanger.put(p.PassangerId, p);
        bookedList.add(p.PassangerId);
        System.out.println("-----Booked Successfully-----");
    }
    public void addToRAC(PassangerDetails p, int berthInfo, String allocatedRAC)
    {
        p.number = berthInfo;
        p.alloted = allocatedRAC;
        passanger.put(p.PassangerId, p);
        racList.add(p.PassangerId);
        availableRAC--;
        racPosition.remove(0);
        System.out.println("-----RAC ticket booked-----");
    }
    public void addToWaitingList(PassangerDetails p, int berthInfo, String allocatedRAC)
    {
        p.number = berthInfo;
        p.alloted = allocatedRAC;
        passanger.put(p.PassangerId, p);
        waitingList.add(p.PassangerId);
        availableWaitingList--;
        waitingListPosition.remove(0);
        System.out.println("-----Waiting List ticket booked-----");
    }
    public void cancelTicket(int passangerId)
    {
       PassangerDetails p = passanger.get(passangerId);
       passanger.remove(passangerId);
       bookedList.remove(passangerId);
       int positionBooked = p.number;
       System.out.println("------cancelled successfully-------");
       if(p.alloted.equals("L"))
       {
           availableLower++;
           lowerBirthPosition.add(positionBooked);
       } else if (p.alloted.equals("M"))
       {
           availableMiddle++;
           middleBirthPosition.add(positionBooked);
       } else if (p.alloted.equals("U")) {
           availableUpper++;
           upperBirthPosition.add(positionBooked);
       }
       if(racList.size() > 0)
       {
         PassangerDetails passangerFromRAC = passanger.get(racList.poll());
         int positionRAC = passangerFromRAC.number;
         racPosition.add(positionRAC);
         racList.remove(passangerFromRAC.PassangerId);
         availableRAC++;

         if(waitingList.size() > 0){
             PassangerDetails passangerFromWL = passanger.get(waitingList.poll());
             int positionWL = passangerFromWL.number;
             waitingListPosition.add(positionWL);
             waitingList.remove(passangerFromWL.PassangerId);

             passangerFromWL.number = racPosition.get(0);
             passangerFromWL.alloted= "RAC";
             racPosition.remove(0);
             racList.add(passangerFromWL.PassangerId);

             availableWaitingList++;
             availableRAC--;
         }
         Main.bookticket(passangerFromRAC);
       }

    }
    public void printAvailable()
    {
        System.out.println("Available lower berths" + availableLower);
        System.out.println("Available middle berths" + availableMiddle);
        System.out.println("Available upper berths" + availableUpper);
        System.out.println("Available RAC" + availableRAC);
        System.out.println("Available Waiting List" + availableWaitingList);
        System.out.println("-----------------------------------");
    }
    public void printPassanger()
    {
        if(passanger.size() == 0){
            System.out.println("no details of passanger");
            return;
        }
        for (PassangerDetails p : passanger.values()){
            System.out.println("Passanger ID " + p.PassangerId);
            System.out.println("Name " + p.name);
            System.out.println("Age" + p.age);
            System.out.println("status " + p.number + p.alloted);
            System.out.println("-----------------");
        }
    }
}
