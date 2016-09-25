package fr.sabai.cefisi.onlinebids.domain;


public class User1 {

    // C'est occupé ici. Les annotations vont ailleurs
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

    User1() {
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

    // un probleme ? Vérifier l'@Access
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
