package lk.ijse.cuisineCompass.view.tdm;

public class SupplierTM implements Comparable<SupplierTM>{

    private String id;
    private String name;
    private String email;
    private String contact;

    public SupplierTM() {

    }

    public SupplierTM(String id, String name, String email, String contact) {
        this.id = id;
        this.name = name;
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
        return "Supplier{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    @Override
    public int compareTo(SupplierTM o) {
        return id.compareTo(o.getId());
    }
}
