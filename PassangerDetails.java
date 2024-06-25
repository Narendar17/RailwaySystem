package DemoProjects.RailwayApplication;

public class PassangerDetails {
    static int id = 1;
    String name;
    int age;
    String BerthPrefernece;
    int PassangerId;
    String alloted;
    int number; // seat number

    public PassangerDetails(String name, int age, String  BerthPrefernce){
        this.name = name;
        this.age = age;
        this.BerthPrefernece = BerthPrefernce;
        this.PassangerId = id;
        id++;
        alloted = "";
        number = -1;
    }
}
