
package com.pucmm.web2.Entity;

import com.pucmm.web2.Enums.AccountStatus;
import com.pucmm.web2.Enums.Permission;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable{
    // Attributes
    @Id
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String shippingAddress;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String password;
    @NotNull
    private Permission role;
    @NotNull
    private AccountStatus status;
    @Column(columnDefinition = "bytea")
    private Byte[] photo;

    private String department;

    private String id;
    private boolean rnc;


    // Constructors
    public User(){

    }

    public User(String email, String firstName, String lastName, String shippingAddress, String country, String city, String password, Permission role, String departament){
        this.setEmail(email.toLowerCase());
        this.setFirstName(firstName.toLowerCase());
        this.setLastName(lastName.toUpperCase());
        this.setShippingAddress(shippingAddress);
        this.setCountry(country);
        this.setCity(city);
        this.setPassword(password);
        this.setRole(role);
        this.setDepartment(departament);
        this.setStatus(AccountStatus.ACTIVE); // Changes once receive confirmation email
    }

    public User(String email, String firstName, String lastName, String shippingAddress, String country, String city, String password, Permission role, String department, String id, boolean rnc) {
        this.setEmail(email.toLowerCase());
        this.setFirstName(firstName.toLowerCase());
        this.setLastName(lastName.toUpperCase());
        this.setShippingAddress(shippingAddress);
        this.setCountry(country);
        this.setCity(city);
        this.setPassword(password);
        this.setRole(role);
        this.setStatus(AccountStatus.ACTIVE); // Changes once receive confirmation email
        this.setId(id);
        this.setRnc(rnc);
        this.setDepartment(department);
    }

    //Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFullName() { return firstName.toUpperCase() + " " + lastName.toUpperCase(); }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permission getRole() {
        return role;
    }

    public void setRole(Permission role) {
        this.role = role;
    }

    public String getPhoto() {
        if(this.photo == null)
            return null;

        byte[] imgBytesAsBase64 = Base64.encodeBase64(toPrimitives(this.photo));
        return new String(imgBytesAsBase64);
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    // Auxiliary Function
    private byte[] toPrimitives(Byte[] buffer) {

        byte[] bytes = new byte[buffer.length];
        for(int i = 0; i < buffer.length; i++){
            bytes[i] = buffer[i];
        }
        return bytes;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRnc() {
        return rnc;
    }

    public void setRnc(boolean rnc) {
        this.rnc = rnc;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
