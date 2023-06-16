package apiTest.day7;

public class NewUserInfoForPost {
  /*  {
        "email": "string",
            "password": "string",
            "name": "string",
            "google": "string",
            "facebook": "string",
            "github": "string"
    }*/
    private String email;
    private String password;
    private String name;
    private String google;
    private String facebook;
    private String github;

    public NewUserInfoForPost()
    {

    }
    public NewUserInfoForPost(String email, String password, String name, String google, String facebook, String github) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.google = google;
        this.facebook = facebook;
        this.github = github;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getGoogle() {
        return google;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getGithub() {
        return github;
    }
}
