import java.util.Objects;

public class User {
    private String name;
    private String surname;
    private int departnmentID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getDepartnmentID() {
        return departnmentID;
    }

    public void setDepartnmentID(int departnmentID) {
        this.departnmentID = departnmentID;
    }

    public User(String name, String surname, int departnmentID) {
        this.name = name;
        this.surname = surname;
        this.departnmentID = departnmentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return departnmentID == user.departnmentID &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, departnmentID);
    }
}
