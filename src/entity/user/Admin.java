package entity.user;

public class Admin extends User{
    private static Admin admin;

    private Admin() {
        super("Cristina", "Ionita", "admin", "adminpa55");
    }

    public static Admin getAdminInstance(){
        if(admin == null){
            admin = new Admin();
        }
        return admin;
    }

    public String getUserType(){
        return "Admin";
    }

    @Override
    public String toString() {
        return "The admin is " + firstName + " " + lastName + "\n Email address of the admin: " + emailAddress;
    }

    @Override
    public String getPassword(){
        return "adminpa55";
    }

    @Override
    public String getEmailAddress() {
        return "admin";
    }

    @Override
    public void setPassword(String password) {
        return;
    }

    @Override
    public void setEmailAddress(String emailAddress) {
        return;
    }
}
