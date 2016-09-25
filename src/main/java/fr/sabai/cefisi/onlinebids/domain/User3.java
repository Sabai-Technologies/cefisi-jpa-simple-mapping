package fr.sabai.cefisi.onlinebids.domain;


public class User3 {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

    User3() {
    }

    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        // on doit pouvoir retourner autre chose que null !
        return null;
    }

    public void setAddress(Address address) {
        // que faire ici ???
    }
}
