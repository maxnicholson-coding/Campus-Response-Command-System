public class SecurityCamera implements Reportable {
    private String name;
    public SecurityCamera (String name) {
        this.name = name;
    }
    @Override
    public void generateReport() { //generates a report
        System.out.println("Camera footage from " + name + " generated.");
    }
}
