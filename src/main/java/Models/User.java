package Models;

public class User {
    private int id;
    private String username;
    private String saltedHash;
    private String salt;
    private boolean is_admin;

    public User(Integer id, String username, String saltedHash, String salt) {
        this.id = id;
        this.username = username;
        this.saltedHash = saltedHash;
        this.salt = salt;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSaltedHash() {
        return saltedHash;
    }

    public void setSaltedHash(String saltedHash) {
        this.saltedHash = saltedHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void set_admin_status(boolean is_admin){
        this.is_admin = is_admin;
    }

    public boolean get_admin_status(){
        return this.is_admin;
    }
}