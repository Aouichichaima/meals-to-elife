package models;

public class User {
    private String cin;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String typeOfUser;
    private int id;
    private int isAuthorized;

    
    public User(){}
    public int getIsAuthorized() {
        return isAuthorized;
    }
    public void setIsAuthorized(int isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public User(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    public User(String cin,String firstName, String lastName, String phone, String email, String pwd, String type) {
        this.cin=cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password=pwd;
        this.typeOfUser=type;
    }
    public User(String cin, String firstName, String lastName, String phone, String email, String typeOfUser, int isAutho) {
        this.cin = cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.isAuthorized=isAutho;
        this.email = email;
        this.typeOfUser = typeOfUser;
    }


    public User(int id, String firstName, String lastName, String phone, String email, String typeOfUser) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.typeOfUser = typeOfUser;
    }


    public User(String cin, String lastName, String firstName, String email, String phone, String typeOfUser) {
        this.cin = cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.typeOfUser = typeOfUser;
    }

    

    public User(String cin, int id, String firstName, String lastName, String phone, String email, String password) {
        this.cin = cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.password = password;
    }
    
    public User(String cin, String firstName, String lastName, String email, String phone) {
        this.cin = cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    @Override
    public String toString() {
        return "User [cin=" + cin + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
                + ", password=" + password + ", phone=" + phone + ", typeOfUser=" + typeOfUser + "]";
    }

}
