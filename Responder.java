public abstract class Responder {
    //initialize variables
    private String name;
    private int id;
    private String location;
    private String designation;
    public Responder(String name, int id, String location) {
        this.name = name;
        this.id = id;
        this.location = location;
        designation = this.location;
    }
    public String getName() { //returns responder's name
        return name;
    }
    public int getID() { //returns responder's id
        return id;
    }
    public String getLocation() { //returns responer's location
        return location;
    }
    public void leaveBuilding() { //the responder leaves the building
        location = "Outside";
    }
    public void goToBuilding() { //the responder returns to the building
        location = designation;
    }
    public String toString() { //displays all information from the Responder class
        return ("Name: " + name + "\nID: " + id + "\nCurrent Location: " + location + "\nDesignation: " + designation);
    }
    //all responders have their own respond method and getJob method
    public abstract void respond(Incident incident);
    public abstract String getJob();
}
