package model.admin;

public class Admin {
    private String username;
    private String passwd;

    public Admin() {
    }

    public Admin(String username, String passwd) {
        this.username = username;
        this.passwd = passwd;
    }
    
    public static Admin empty()
    {
        return new Admin();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
