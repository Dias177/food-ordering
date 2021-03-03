package kz.epam.tcfp.foodordering.entity;

import java.sql.Date;
import java.util.Objects;

public class User extends Entity implements Comparable<User> {

    private String roleName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthday;

    public User() {}

    public User(String roleName, String email, String password) {
        this.roleName = roleName;
        this.email = email;
        this.password = password;
    }

    public User(String roleName, String email, String password, String firstName, String lastName,
                String phoneNumber, Date birthday) {
        this.roleName = roleName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return roleName.equals(user.roleName) && email.equals(user.email) && firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) && phoneNumber.equals(user.phoneNumber) &&
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, email, firstName, lastName, phoneNumber, birthday);
    }

    @Override
    public String toString() {
        return "User{" +
                "roleName=" + roleName +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public int compareTo(User o) {
        int res = this.firstName.compareTo(o.getFirstName());
        if (res == 0) {
            res = this.lastName.compareTo(o.getLastName());
            if (res == 0) {
                res = this.birthday.compareTo(o.getBirthday());
                if (res == 0) {
                    res = (int) (this.getId() - o.getId());
                }
            }
        }
        return res;
    }
}