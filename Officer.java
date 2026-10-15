public class Officer extends Responder implements Reportable, Trackable {
    public Officer (String name, int id, String location) {
        //uses the responder superclass to define itself
        super(name, id, location);
    }
    @Override
    public void respond(Incident incident) { //officer responds to the incident
        String type = incident.getType();
        //decides what to do based on type of incident
        if (type.equals("fire") || type.equals("hazard")) {
            System.out.println(getName() + " (ID: " + getID() + ") is attempting to rescue people.");
        } else if (type.equals("suspicous person")) {
            leaveBuilding();
            System.out.println(getName() + " (ID: " + getID() + ") is investigating.");
        } else {
            System.out.println(getName() + " (ID: " + getID() + ") is staying alert.");
        }
    }
    @Override
    public void generateReport() { //generates a report
        System.out.println("Officer " + getName() + "'s report and body cam footage generated.");
    }
    @Override 
    public void updateLocation() { //updates officer location
        System.out.println(getName()+ " is currently at " + getLocation() + ".");
    }
    @Override
    public String getJob() { //returns job
        return "Officer";
    }
}
