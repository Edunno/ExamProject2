package DBAccess;

import FogExceptions.FogLoginException;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 The purpose of UserMapper is to...

 @author kasper
 */
public class UserMapper {
    public static void createUser( User user ) throws FogLoginException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO User (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setString( 1, user.getEmail() );
            ps.setString( 2, user.getPassword() );
            ps.setString( 3, user.getRole() );
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt( 1 );
            user.setId( id );
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new FogLoginException( ex.getMessage() );
        }
    }

    public static User login( String email, String password ) throws FogLoginException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT uID, Role FROM User "
                    + "WHERE Email=? AND Password=?";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setString( 1, email );
            ps.setString( 2, password );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                String role = rs.getString( "Role" );
                int id = rs.getInt( "uID" );
                User user = new User( email, password, role );
                user.setId( id );
                return user;
            } else {
                throw new FogLoginException( "Could not validate user" );
            }
        } catch ( ClassNotFoundException | SQLException ex ) {
            throw new FogLoginException(ex.getMessage());
        }
    }

}
