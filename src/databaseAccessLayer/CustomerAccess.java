package databaseAccessLayer;

import interfaces.IUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class CustomerAccess extends Access {
    public static final boolean CUSTOMER = true;
    public static final boolean MANAGER = false;

    Connection connection;

    public CustomerAccess() {
        connection = getConnection();
    }

    public boolean addUser(IUser user, boolean type) {
        try {
            if(!checkEmailAddress(user.getEmailAddress(),type)) {
                Statement stmt = connection.createStatement();
                if(type==CUSTOMER) {
                    stmt.executeUpdate("insert into customers values('" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getPassword() + "','" + user.getPassword() + "','" + user.getEmailAddress() + "','" + user.getPhoneNumber() + "','" + user.getShippingAddress() + "')");
                } else {
                    stmt.executeUpdate("insert into managers values('" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getPassword() + "','" + user.getPassword() + "','" + user.getEmailAddress() + "','" + user.getPhoneNumber() + "','" + user.getShippingAddress() + "')");
                }
                return true;
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkEmailAddress(String emailAddress, boolean type) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs;
            if (type == CUSTOMER) {
                rs = stmt.executeQuery("select exists(select * from customers where emailAddress = '" + emailAddress + "')");

            } else {
                rs = stmt.executeQuery("select exists(select * from managers where emailAddress = '" + emailAddress + "')");
            }
            rs.next();
            String s = rs.getString(1);
            return s.equals("1");
        }catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public IUser signIn(String emailAddress, String password, boolean type) {
        try {
            Boolean check = checkEmailAddress(emailAddress, type);
            if(check) {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from users where emailAddress = '" + emailAddress + "'");
                rs.next();
                int logged = rs.getInt(5);
                if(logged != 1) {
                    String pass = rs.getString(3);
                    if(pass.equals(password)) {
                        IUser user = new IUser(rs.getString(1), rs.getString(2), pass,
                                rs.getString(4), rs.getString(5), rs.getString(6));
                        // Loading user data ----------------



                        return user;
                    }
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int editProfile(String oldUserEmail , String newUserFirstName , String newUserLastName , String newUserPassword
    ,String newUserEmail , String newUserPhoneNum , String newUserShippingAddress){
        //sql query update;
        return -1;
    }

}
