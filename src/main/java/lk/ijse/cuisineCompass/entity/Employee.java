package lk.ijse.cuisineCompass.entity;

import lombok.*;

@Data
//@NoArgsConstructor
//@AllArgsConstructor

public class Employee {

    private String id;
    private String name;
    private String jobRole;
    private String email;
    private String contact;

    public Employee(){

    }

    public Employee(String id, String name, String jobRole, String email, String contact) {
        this.id = id;
        this.name = name;
        this.jobRole = jobRole;
        this.email = email;
        this.contact = contact;
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
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", jobRole='" + jobRole + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
