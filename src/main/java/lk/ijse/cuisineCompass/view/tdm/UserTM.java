package lk.ijse.cuisineCompass.view.tdm;

public class UserTM implements Comparable<UserTM>{

    private String id;
    private String jobRole;
    private String userName;
    private String password;

    public UserTM() {

    }

    public UserTM(String id, String jobRole, String userName, String password) {
        this.id = id;
        this.jobRole = jobRole;
        this.userName = userName;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTM{" +
                "id='" + id + '\'' +
                ", jobRole='" + jobRole + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int compareTo(UserTM o) {
        return id.compareTo(o.getId());
    }
}
