public class Teacher extends Responder implements Reportable, Trackable {
    public Teacher (String name, int id, String location) {
        //uses the responder superclass to define itself
        super(name, id, location);
    }
    @Override
    public void respond(Incident incident) { //teacher responds to the incident
        String type = incident.getType();
        //decides what to do based on type of incident
        if (type.equals("fire") || type.equals("hazard")) {
            if (getLocation().equals("Outside")) {
                System.out.println(getName() + " (ID: " + getID() + ") was outside with the students.");
            } else {
                leaveBuilding();
                System.out.println(getName() + " (ID: " + getID() + ") successfully evacuated with the students.");
            }
        } else {
            if (getLocation().equals("Outside")) {
                goToBuilding();
                System.out.println(getName() + " (ID: " + getID() + ") is going inside.");
            } else {
                System.out.println(getName() + " (ID: " + getID() + ") is staying inside.");
            }
        }
    }
    @Override
    public void generateReport() { //generates a report
        System.out.println(getName() + "'s attendance sheets generated.");
    }
    @Override 
    public void updateLocation() { //updates teacher location
        System.out.println(getName()+ " and students are currently at " + getLocation() + ".");
    }
    @Override
    public String getJob() { //returns job
        return "Teacher";
    }
}
