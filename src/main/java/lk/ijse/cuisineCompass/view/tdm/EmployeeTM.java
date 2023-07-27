package lk.ijse.cuisineCompass.view.tdm;

public class EmployeeTM implements Comparable<EmployeeTM> {

    private String id;
    private String name;
    private String jobRole;
    private String email;
    private String contact;

    public EmployeeTM() {
    }

    public EmployeeTM(String id, String name, String jobRole, String email, String contact) {
        this.id = id;
        this.name = name;
        this.jobRole = jobRole;
        this.email = email;
        this.contact = contact;
    }

    public EmployeeTM(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", jobRole='" + jobRole + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    @Override
    public int compareTo(EmployeeTM o) {
        return id.compareTo(o.getId());
    }

}
