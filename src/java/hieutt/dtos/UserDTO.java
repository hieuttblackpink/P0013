
package hieutt.dtos;

import java.io.Serializable;


public class UserDTO implements Serializable
{
    private String userID;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String phone;
    private String address;

    public UserDTO(String userID, String firstName, String lastName, String role) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
    
    public UserDTO(String userID, String password, String firstName, String lastName, String role) {
        this.userID = userID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public UserDTO(String userID, String phone, String address) {
        this.userID = userID;
        this.phone = phone;
        this.address = address;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
