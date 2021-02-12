package kz.epam.tcfp.foodordering.entity;

import java.sql.Date;
import java.util.Objects;

public class User extends Entity implements Comparable<User> {

    private long roleId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthday;

    public User() {}

    public User(long roleId, String email, String password) {
        this.roleId = roleId;
        this.email = email;
        this.password = password;
    }

    public User(long roleId, String email, String password, String firstName, String lastName,
                String phoneNumber, Date birthday) {
        this.roleId = roleId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
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
        return roleId == user.roleId && email.equals(user.email) && firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) && phoneNumber.equals(user.phoneNumber) &&
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, email, firstName, lastName, phoneNumber, birthday);
    }

    @Override
    public String toString() {
        return "User{" +
                "roleId=" + roleId +
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