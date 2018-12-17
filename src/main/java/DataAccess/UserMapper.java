package DataAccess;

import FunctionLayer.FogExceptions.FogCreateUserException;
import FunctionLayer.FogExceptions.FogLoginException;
import FunctionLayer.DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The purpose of UserMapper is to be able to put and pull User data from the database
 * 
 *
 * @author kasper
 */
public class UserMapper {
    
    /**
     * This method creates a User object and stores it in the DB
     * 
     * @param user the user to store
     * @throws FogCreateUserException
     * @throws ClassNotFoundException 
     */

    public static void createUser(User user) throws FogCreateUserException, ClassNotFoundException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO User (Email, Password, Role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            user.setId(id);
        } catch (SQLException ex ) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
                throw new FogCreateUserException(ex.getMessage(), ex);
            }
    }
    
    /**
     * This method checks if the username and password given matches anything in the DB
     * 
     * @param email the username string
     * @param password the password string
     * @return User object
     * @throws FogLoginException 
     */

    public static User login(String email, String password) throws FogLoginException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT uID, Role FROM User "
                    + "WHERE Email=? AND Password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("Role");
                int id = rs.getInt("uID");
                User user = new User(email, password, role);
                user.setId(id);
                return user;
            } else {
                Exception ex = null;
                throw new FogLoginException("Could not validate user", ex);
            }
        } catch (FogLoginException | ClassNotFoundException | SQLException ex) {
            throw new FogLoginException(ex.getMessage(), ex);
        }
    }

}
