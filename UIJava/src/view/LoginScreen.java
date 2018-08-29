/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import exceptions.UserExistsException;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.User;
import persistent.UserDAO;

/**
 *
 * @author felipe
 */
public class LoginScreen extends JFrame 
{
    // Labels
    JLabel lblHeader, lblUser, lblPass;
    // Text Field
    JTextField txtUser;
    // Pass TextField
    JPasswordField txtPass;
    // Button
    JButton btnLogin;
    public LoginScreen()
    {
        // Titulo
        super("MVC com Java");
        // Conteiner
        Container screen = getContentPane();
        setLayout(null);
        // Icone
        ImageIcon icon = new ImageIcon("../res/img/icone.jpg");
        setIconImage(icon.getImage());
        // Labels Text
        lblHeader = new JLabel("MVC com JAVA");
        lblUser = new JLabel("Usuario");
        lblPass = new JLabel("Senha");
        // Labels Style
        // Bounds
        lblHeader.setBounds(100, 20, 200, 40);
        lblUser.setBounds(100, 170, 100, 40);
        lblPass.setBounds(100, 220, 100, 40);
        // Font Type
        lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
        lblUser.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPass.setFont(new Font("Arial", Font.PLAIN, 16));
        // Text Fields
        txtUser = new JTextField(32);
        txtPass = new JPasswordField(10);
        // Text Styles
        txtUser.setBounds(200, 180, 100, 20);
        txtUser.setFont(new Font("Arial", Font.PLAIN, 16));
        txtUser.requestFocus();
        txtPass.setBounds(200, 230, 100, 20);
        txtPass.setFont(new Font("Arial", Font.PLAIN, 16));
        // Button
        btnLogin = new JButton("Entrar");
        // Button Style
        btnLogin.setBounds(100, 270, 100, 40);
        // Button Event
        getRootPane().setDefaultButton(btnLogin);
        btnLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){    
                // Conteudo de Usuario e senha
                login(txtUser.getText(), txtPass.getText());
            }           
        });
        // Set elements on screen
        // Labels
        screen.add(lblHeader);
        screen.add(lblUser);
        screen.add(lblPass);
        // Text Fields
        screen.add(txtUser);
        screen.add(txtPass);
        // Button
        screen.add(btnLogin);
        // Tamanho e localização da Janela
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        // Sair
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void login(String username, String password)
    {
        User u = new User();
     
        u.setUsername(username);
        u.setPassword(password);
            
        UserDAO ud = new UserDAO(u);
                      
        try{
            if(ud.login() != null)
            {
                JOptionPane.showMessageDialog(rootPane, "Login efetuado com sucesso");
                // Dados do usuario logado
                u = ud.login();                
                // Main Menu
                MainView main = new MainView(u);
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, "Usuario ou Senha invalido");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Um erro ocorreu! Tente mais tarde");
        }
        
    }
}
