package lk.ijse.veggieSystem.to;

public class User {
    private String userId;
    private String userName;
    private String password;
    private String name;
    private String userAddress;
    private String userMobile;
    private String userEmail;

    public User(String userId, String userName, String password, String name, String userAddress, String userMobile, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.userAddress = userAddress;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}