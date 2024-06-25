package DemoProjects.RailwayApplication;

import java.util.Scanner;

public class Main {
    public static void bookticket(PassangerDetails p){
        TicketBooking ticket = new TicketBooking();
        if(TicketBooking.availableWaitingList == 0){
            System.out.println("Regret booking");
            return;
        }
        if((p.BerthPrefernece.equals("L") && TicketBooking.availableLower > 0) ||
                (p.BerthPrefernece.equals("M") && TicketBooking.availableMiddle > 0) ||
                (p.BerthPrefernece.equals("U") && TicketBooking.availableUpper > 0))
        {
            System.out.println("Preferred Berth Available");
            if(p.BerthPrefernece.equals("L"))
            {
                System.out.println("Lower Berth Given");
                ticket.bookTicker(p, (TicketBooking.lowerBirthPosition.get(0)),"L");
                TicketBooking.lowerBirthPosition.remove(0);
                TicketBooking.availableLower--;
            } else if (p.BerthPrefernece.equals("M")) {
                System.out.println("Middle Berth Given");
                ticket.bookTicker(p, (TicketBooking.middleBirthPosition.get(0)), "M");
                TicketBooking.middleBirthPosition.remove(0);
                TicketBooking.availableMiddle--;
            }
            else if(p.BerthPrefernece.equals("U"))
            {
                System.out.println("Upper Berth Given");
                ticket.bookTicker(p, (TicketBooking.upperBirthPosition.get(0)), "U");
                TicketBooking.middleBirthPosition.remove(0);
                TicketBooking.availableUpper--;
            }
            else if (TicketBooking.availableLower > 0)
            {
                System.out.println("Lower Berth Given");
                ticket.bookTicker(p, (TicketBooking.lowerBirthPosition.get(0)),"L");
                TicketBooking.lowerBirthPosition.remove(0);
                TicketBooking.availableLower--;
           }
            else if (TicketBooking.availableMiddle > 0)
            {
                System.out.println("Middle Berth Given");
                ticket.bookTicker(p, (TicketBooking.middleBirthPosition.get(0)), "M");
                TicketBooking.middleBirthPosition.remove(0);
                TicketBooking.availableMiddle--;
            }
            else if (TicketBooking.availableUpper > 0)
            {
                System.out.println("Upper Berth Given");
                ticket.bookTicker(p, (TicketBooking.upperBirthPosition.get(0)), "U");
                TicketBooking.middleBirthPosition.remove(0);
                TicketBooking.availableUpper--;
            }
            else if(TicketBooking.availableRAC > 0)
            {
                System.out.println("RAC available");
                ticket.addToRAC(p, (TicketBooking.racPosition.get(0)), "RAC");
            }
            else if(TicketBooking.availableWaitingList > 0)
            {
                System.out.println("WL available");
                ticket.addToWaitingList(p, (TicketBooking.waitingListPosition.get(0)), "WL");
            }
        }
    }
    public static void cancelTicket(int id){
        TicketBooking ticket =new TicketBooking();
        if(!ticket.passanger.containsKey(id)){
            System.out.println("Passanger detail is invalid");
        } else {
            
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("1 - Book Ticket \n 2 - Cancel Ticket \n 3 - Available Ticket \n 4 - Booked Tickets \n 5 - Exit");
            int choice = sc.nextInt();
            switch (choice){
                case  1:
                {
                    System.out.println("Enter passange name, age and berth preference");
                    String name = sc.next();
                    int age = sc.nextInt();
                    String berth = sc.next();
                    PassangerDetails passanger = new PassangerDetails(name, age, berth);
                    bookticket(passanger);
                }
                break;
                case 2:
                {
                    System.out.println("Enter Passanger Id to cancel");
                    int id = sc.nextInt();
                    cancelTicket(id);
                }
                break;
                case 3:
                {
                    TicketBooking booker = new TicketBooking();
                    booker.printAvailable();;
                }
                break;
                case 4:
                {
                    TicketBooking booker = new TicketBooking();
                    booker.printPassanger();
                }
                break;
                case 5:
                {
                    loop = false;
                }
                break;
                default:
                    break;
            }
        }
    }
}
