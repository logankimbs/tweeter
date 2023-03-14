package edu.byu.cs.tweeter.model.net.request;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String alias;
    private String password;
    private String imageUrl;

    private RegisterRequest() {}

    public RegisterRequest(String firstName, String lastName, String alias, String password, String imageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAlias() {
        return alias;
    }

    public String getPassword() {
        return password;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
