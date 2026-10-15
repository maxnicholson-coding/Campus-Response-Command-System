public class Technician extends Responder implements Reportable, Trackable {
    public Technician (String name, int id, String location) {
        //uses the responder superclass to define itself
        super(name, id, location);
    }
    @Override
    public void respond(Incident incident) { //technician responds to the incident
        String type = incident.getType();
        //decides what to do based on type of incident
        if (type.equals("fire") || type.equals("hazard")) {
            leaveBuilding();
            System.out.println(getName() + " (ID: " + getID() + ") successfully evacuated.");
        } else if (type.equals("cyberattack")) {
            System.out.println(getName() + " (ID: " + getID() + ") is working hard to prevent the attack.");
        } else {
            System.out.println(getName() + " (ID: " + getID() + ") is waiting patiently.");
        }
    }
    @Override
    public void generateReport() { //generates a report
        System.out.println("Technician " + getName() + "'s breakdown and patches generated.");
    }
    @Override 
    public void updateLocation() { //updates technician location
        System.out.println(getName()+ " is currently at " + getLocation() + ".  School systems are secure.");
    }
    @Override
    public String getJob() { //returns job
        return "Technician";
    }
}
