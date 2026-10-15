import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class CampusResponseApp
{
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Incident> allIncidents = new ArrayList<Incident>();
    public static void main(String[] args)
    {
        System.out.println("Welcome to your Campus Response App.\n");
        //creates campus staff, cameras, and drone
        Teacher t1 = new Teacher("Mrs. Smith", 295710, "Room 210");
        Teacher t2 = new Teacher("Mrs. Gonzales", 318442, "Room 153");
        Officer o1 = new Officer("Terry", 814922, "Cafeteria");
        Officer o2 = new Officer("Zachary", 605593, "Outside");
        Technician tech = new Technician("Wilson", 166281, "Storage Unit B");
        SecurityCamera c1 = new SecurityCamera("Cam 4");
        SecurityCamera c2 = new SecurityCamera("Cam 9");
        Drone drone = new Drone("D-1");
        //creates a list of all responders on campus
        Responder[] responders = {t1, t2, o1, o2, tech};
        //creates a list of all reporters on campus
        Reportable[] reporters = {t1, t2, o1, o2, tech, c1, c2};
        //creates a list of all trackable units on campus
        Trackable[] trackables = {t1, t2, o1, o2, tech, drone};
        //starts menu loop
        while (true) {
            //takes user input and uses the correct method associated with it
            System.out.println("What would you like to do?");
            String command = input.nextLine();
            if (command.equals("help")) {
                System.out.println("Commands: help, incident, view, staff, dispatch, report, track, reactivate, return, sign out");
            } else if (command.equals("incident")) {
                createIncident();
            } else if (command.equals("view")) {
                viewIncidents();
            } else if (command.equals("staff")) {
                viewStaff(responders);
            } else if (command.equals("dispatch")) {
                findAndDispatch(responders);
            } else if (command.equals("report")) {
                makeReports(reporters);
            } else if (command.equals("track")) {
                findLocations(trackables);
            } else if (command.equals("reactivate")) {
                reactivateIncident();
            } else if (command.equals("return")) {
                returnPeople(responders);
            } else if (command.equals("sign out")) {
                System.out.println("Goodbye!");
                break;
            } else { //if command is not a valid input, refers the user to "help"
                System.out.println("Invalid input. To see all commands, type \"help\"");
            }
            System.out.println();
        }
    }
    public static void createIncident() { //creates an incident object
        //gets user input on all details
        System.out.println("What type of incident is it?\n1. fire\n2. suspicous person\n3. weather\n4. hazard\n5. cyberattack");
        String incidentType;
        while (true) { //loops until the user inputs a valid incident
            incidentType = (input.nextLine()).toLowerCase();
            if (incidentType.equals("fire") || incidentType.equals("suspicous person") || incidentType.equals("weather") 
            || incidentType.equals("hazard") || incidentType.equals("cyberattack")) {
                break;
            } else {
                System.out.println("Please input an incident exactly as shown above.");
            }
        }
        System.out.println("Where is it located?");
        String incidentLocation = input.nextLine();
        System.out.println("On a scale of 1-10, how severe is it?");
        double incidentSeverity = safeInputDouble();
        //notes if severity is between 1 and 10
        if (incidentSeverity > 10 || incidentSeverity < 1) {
            System.out.println("Invalid severity. It is being set to 5.");
        }
        System.out.println("Write a short description of it.");
        String incidentDescription = input.nextLine();
        //creates incident object and adds it to the list
        Incident incident = new Incident(incidentType, incidentLocation, incidentSeverity, incidentDescription);
        allIncidents.add(incident);
        System.out.println("Incident succesfully logged. This incident has an ID of " + (allIncidents.size() - 1) + ".");
    }
    public static void viewIncidents() { //prints all recorded incidents
        //interates through all incidents and prints them
        if (allIncidents.size() > 0) {
            for (int i = 0; i < allIncidents.size(); i++) {
                System.out.println();
                System.out.println(allIncidents.get(i));
            }
        } else { //notes if there are no incidents
            System.out.println("There are currently no incidents.");
        }
    }
    public static void viewStaff(Responder[] staff) { //prints all staff
        for (Responder r : staff) {
            System.out.println();
            System.out.println("--- " + r.getJob() + " ---");
            System.out.println(r);
        }
    }
    public static void findAndDispatch(Responder[] team) { //finds an incident of inputted id and dispatches people from it
        //checks if there are any incidents
        if (allIncidents.size() == 0) {
            System.out.println("There are currently no incidents.");
            return;
        }
        //asks for input of incident id
        System.out.println("Which incident needs dispatching from? (Input its ID)");
        int id = safeInputInt();
        //makes sure that is a valid id
        if (id <= (allIncidents.size()-1) && id >= 0){
            Incident currentIncident = allIncidents.get(id);
            //dispatches responder team if the incident if active
            if (currentIncident.getActive()) {
                dispatch(team, currentIncident);
            } else {
                System.out.println("That incident is not active.");
            }
        } else {
            System.out.println("Invalid input.");
        }
    }
    public static void dispatch(Responder[] team, Incident incident) { //sends a group of responders to deal with an incident
        //all responders respond
        for (Responder r : team) {
            r.respond(incident);
        }
        //asks if the incident has been remedied
        System.out.println("\nHas the incident been remedied? (Y or N)");
        while (true) { //loops until a valid input is made
            String remedy = input.nextLine();
            if (remedy.equals("Y") || remedy.equals("y")) {
                //makes the incident inactive if so
                incident.solve();
                System.out.println("The incident has been resolved!");
                break;
            } else if (remedy.equals("N") || remedy.equals("n")) {
                System.out.println("The incident has not been resolved.");
                break;
            } else {
                System.out.println("Input invaild.");
            }
        }
    }
    public static void makeReports(Reportable[] team) { //all reporters issue their reports
        for (Reportable r : team) {
            r.generateReport();
        }
    }
    public static void findLocations(Trackable[] team) { //displays location fo all trackable things
        for (Trackable t : team) {
            t.updateLocation();
        }
    }
    public static void reactivateIncident() { //reactivates an inactive incident
        //checks if there are any inactive incidents
        boolean hasInactiveIncident = false;
        for (int i = 0; i < allIncidents.size(); i++) {
            Incident incident = allIncidents.get(i);
            if (!incident.getActive()) {
                hasInactiveIncident = true;
            }
        }
        //takes user input if there was an inactive incident
        if (hasInactiveIncident) {
            Incident currentIncident;
            System.out.println("What incident has reactivated? (Input its ID)");
            int id = safeInputInt();
            try { //stops method if inputted incident is outside range
                currentIncident = allIncidents.get(id);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Incident not found.");
                return;
            }
            if (currentIncident.getActive()) { //does nothing is it is already active
                System.out.println("That incident is already active.");
            } else { //sets incident to be active if it is inactive
                currentIncident.activate();
                System.out.println("The incident has been reactivated.");
            }
        } else { //does nothing if no inactive incidents
            System.out.println("There are no currently inactive incidents.");
        }
    }
    public static void returnPeople(Responder[] team) { //returns pepole to their designation
        //takes inputted ID
        System.out.println("What is the ID of the person who should return to their post?");
        System.out.println("If everyone should return, type \"-1\".");
        int personReturning = safeInputInt();
        if (personReturning == -1) { //if -1 is entered, everyone returns to their post
            for (Responder r : team) {
                r.goToBuilding();
            }
            System.out.println("Everyone has returned to their posts.");
            return;
        } else {
            for (Responder r : team) { //finds the person with the inputted id and sends them back
                if (r.getID() == personReturning) {
                    r.goToBuilding();
                    System.out.println(r.getName() + " has returned to " + r.getLocation() + ".");
                    return;
                }
            }
        }
        //if the inputted id is not present, nothing happens
        System.out.println("ID not found.");
    }
    public static int safeInputInt() { //takes an inputted integer without erroring at non-ints
        while (true) {
            try {
                int userInput = input.nextInt();
                input.nextLine();
                return userInput;
            } catch (InputMismatchException e) { //catch if input is not an int and continues the loop
                System.out.println("Invalid");
                input.nextLine();
            }
        }
    }
    public static double safeInputDouble() { //takes an inputted double without erroring at non-doubles
        while (true) {
            try {
                double userInput = input.nextDouble();
                input.nextLine();
                return userInput;
            } catch (InputMismatchException e) { //catch if input is not an double and continues the loop
                System.out.println("Invalid");
                input.nextLine();
            }
        }
    }
}
