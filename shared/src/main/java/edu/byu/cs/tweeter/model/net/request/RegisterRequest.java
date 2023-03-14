package edu.byu.cs.tweeter.model.net.request;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String imageUrl;

    private RegisterRequest() {}

    public RegisterRequest(String firstName, String lastName, String username, String password, String imageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String setFirstName(String firstName) {
        return this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String setLastName(String lastName) {
        return this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        return this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        return this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String setImageUrl(String imageUrl) {
        return this.imageUrl = imageUrl;
    }
}
