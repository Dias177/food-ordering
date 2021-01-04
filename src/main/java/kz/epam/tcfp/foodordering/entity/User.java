package kz.epam.tcfp.foodordering.entity;

import java.util.Date;
import java.util.Objects;

public class User {

    private int id;
    private int roleId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthday;

    public User() {}

    public User(int roleId, String email, String password) {
        this.roleId = roleId;
        this.email = email;
        this.password = password;
    }

    public User(int id, int roleId, String email, String password, String firstName, String lastName,
                String phoneNumber, Date birthday) {
        this.id = id;
        this.roleId = roleId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public User(User user) {
        this.id = user.id;
        this.roleId = user.roleId;
        this.email = user.email;
        this.password = user.password;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.phoneNumber = user.phoneNumber;
        this.birthday = user.birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
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

    public User copy() {
        return new User(this);
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
}