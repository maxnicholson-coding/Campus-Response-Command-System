public class Incident {
    //initialize variables
    private String type;
    private String location;
    private double severity;
    private String description;
    private boolean isActive;
    public Incident(String type, String location, double severity, String description) {
        this.type = type;
        this.location = location;
        //sets severity to 5 if it is less than 1 or greater than 10
        if (severity > 10 || severity < 1) {
            this.severity = 5;
        } else {
            this.severity = severity;
        }
        this.description = description;
        isActive = true;
    }
    public String getType() { //returns the type of incident
        return type;
    }
    public boolean getActive() { //returns if an incident is active or not
        return isActive;
    }
    public void solve() { //the incident is no longer active
        isActive = false;
    }
    public void activate() { //the incident is now active again
        isActive = true;
    }
    public String toString() { //displays all information about the incident
        return("Type: " + type + "\nLocation: " + location + "\nSeverity: " + severity
        + "\nDescription: " + description + "\nIs Active: " + isActive);
    }
}
