public class Drone implements Trackable {
    private String name;
    public Drone (String name) {
        this.name = name;
    }
    @Override
    public void updateLocation() { //updates the drone's loaction
        System.out.println(name + " is currently in the sky. Drone footage sent.");
    }
}
