/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistent;

import exceptions.UserExistsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 *
 * @author felipe
 */
public class UserDAO<T extends User> implements IDAO<T>
{
    
    private User user;
        
    public UserDAO(User user)
    {        
        this.user = user;
    }
    @Override
    public void inserir() throws Exception {
        // sql
        String sql = "INSERT INTO users VALUES(NULL, ?, ?, ?)";
        // Connection
        try(Connection conn = new ConnectionDB().getMysqlConnection())
        {
            PreparedStatement stm = conn.prepareStatement(sql);
            // PARAMS
            stm.setString(1, this.user.getUsername());
            stm.setString(2, this.user.getPassword());
            stm.setString(3, this.user.getRole());
            // EXECUTE
            stm.execute();
            // CLOSE
            stm.close();
            
        }catch(Exception e)
        {
            throw new UserExistsException(e);
        }
    }

    @Override
    public List<T> listar() throws Exception {
        // Users
        List<User> users = new ArrayList<User>();
        // sql
        String sql = "SELECT * FROM users";
         // Connection
        try(Connection conn = new ConnectionDB().getMysqlConnection())
        {
            PreparedStatement stm = conn.prepareStatement(sql);
            // EXECUTE
            stm.execute();
            // RESULTS
            ResultSet rss = stm.executeQuery();
            while(rss.next()){
                User user = new User();
                
                user.setIduser(rss.getInt("iduser"));
                user.setUsername(rss.getString("username"));
                user.setPassword(rss.getString("password"));
                user.setRole(rss.getString("role"));
                
                users.add(user);
            }
            // CLOSE
            stm.close();
            
        }catch(Exception e)
        {
            throw new Exception(e);
        }
        return (List<T>)users;
    }

    @Override
    public void atualizar() throws Exception {
          // sql
        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE iduser = ?";
        // Connection
        try(Connection conn = new ConnectionDB().getMysqlConnection())
        {
            PreparedStatement stm = conn.prepareStatement(sql);
            // PARAMS
            stm.setString(1, this.user.getUsername());
            stm.setString(2, this.user.getPassword());
            stm.setString(3, this.user.getRole());
            stm.setInt(4, this.user.getIduser());
            // EXECUTE
            stm.execute();
            // CLOSE
            stm.close();
            
        }catch(Exception e)
        {
            throw new UserExistsException(e);
        }
    }

    @Override
    public void excluir() throws Exception {
          // sql
        String sql = "DELETE FROM users WHERE iduser = ?";
        // Connection
        try(Connection conn = new ConnectionDB().getMysqlConnection())
        {
            PreparedStatement stm = conn.prepareStatement(sql);
            // PARAMS
            stm.setInt(1, this.user.getIduser());
            // EXECUTE
            stm.execute();
            // CLOSE
            stm.close();
            
        }catch(Exception e)
        {
            throw new Exception(e);
        }
    }
    
    public User login() throws Exception
    {
        // User
        User user = new User();
        // sql
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        // Connection
        try(Connection conn = new ConnectionDB().getMysqlConnection())
        {
            PreparedStatement stm = conn.prepareStatement(sql);
            // PARAMS
            stm.setString(1, this.user.getUsername());
            stm.setString(2, this.user.getPassword());            
            // EXECUTE
            stm.execute();
            // RESULTS
            ResultSet rss = stm.executeQuery();
            
            if(rss.first())
            {
                user.setIduser(rss.getInt("iduser"));
                user.setUsername(rss.getString("username"));
                user.setPassword(rss.getString("password"));
                user.setRole(rss.getString("role"));
                
                return user;
            }else
            {
                user = null;
            }
            // CLOSE
            stm.close();
            
        }catch(Exception e)
        {
            throw new Exception(e);
        }
        return null;
    }
    
   
}
