package databaseAccessLayer;

import interfaces.IPublisher;
import interfaces.IUser;
import models.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    stmt.executeUpdate("insert into USER values('" +user.getEmailAddress()+"','"+ user.getFirstName() + "','" + user.getLastName() + "','" + user.getPassword() + "','" + user.getPhoneNumber() + "','" + user.getShippingAddress() + "','" + user.getUserName() +"')");
                if(type!=CUSTOMER) {
                    stmt.executeUpdate("insert into MANAGER values('" + user.getEmailAddress()+"')");
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
                rs = stmt.executeQuery("select exists(select * from USER where email_address = '" + emailAddress + "')");

            } else {
                rs = stmt.executeQuery("select exists(select * from MANAGER where email_address = '" + emailAddress + "')");
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
                ResultSet rs = stmt.executeQuery("Select * from USER where email_address = '" + emailAddress + "'");
                rs.next();
                String pass = rs.getString(4);
                if(pass.equals(password)) {
                    IUser user = new IUser(rs.getString(1), rs.getString(2),rs.getString(3), pass,
                            rs.getString(5), rs.getString(6),rs.getString(7));
                    // Loading user data ----------------
                    user.setCustomer(type);
                    return user;
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int editProfile(String oldUserEmail , String newUserFirstName , String newUserLastName , String newUserPassword
    ,String newUserEmail , String newUserPhoneNum , String newUserShippingAddress) throws SQLException {
        //sql query update;
        Statement stmt = connection.createStatement();
        int res = stmt.executeUpdate("UPDATE  USER SET first_name ='"+newUserFirstName +"' , last_name = '"
                +newUserLastName +"' , password = '"+newUserPassword+"' , email_address = '"+newUserEmail+"' , phone_number = '"
                +newUserPhoneNum +"' , shipping_address = '" + newUserShippingAddress + "' WHERE email_address = '" + oldUserEmail+"'");
        return res;

    }
    public void logOut(String email) throws SQLException {
        Statement stmt = connection.createStatement();
        int res = stmt.executeUpdate("delete  Cart Where user_email = '"+ email +"'");

    }

}
