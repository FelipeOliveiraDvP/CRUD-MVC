/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author felipe
 */
public class UserExistsException extends Exception
{
    public UserExistsException(Exception e) {
        super("O usuario já esta cadastrado");
    }
    
}
