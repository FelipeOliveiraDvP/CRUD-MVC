/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import model.User;
import persistent.UserDAO;

/**
 *
 * @author felipe
 */
public class AddUserView extends JDialog
{

    private JButton btnAdd, btnCancel;
    private JLabel lblUsername, lblPassword, lblRole;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox cbRole;
    
    public AddUserView(Frame owner, String title, boolean modal)
    {
        super(owner, title, modal);       
        // Container
        Container screen = getContentPane();
        screen.setLayout(null);
        // Labels
        lblUsername = new JLabel("Usuario");
        lblPassword = new JLabel("Senha");
        lblRole = new JLabel("Permissão");
        lblUsername.setBounds(20, 50, 100, 20);
        lblPassword.setBounds(20, 80, 100, 20);
        lblRole.setBounds(20, 110, 100, 20);
        // Text Box
        txtUsername = new JTextField(30);
        txtPassword = new JPasswordField(10);
        txtUsername.setBounds(120, 50, 100, 20);
        txtPassword.setBounds(120, 80, 100, 20);
        // Roles
        String roles[] = {"A", "G", "C"};
        cbRole = new JComboBox(roles);        
        cbRole.setMaximumRowCount(4);
        cbRole.setEditable(false);
        cbRole.setBounds(120, 110, 100, 20);
        // Buttons
        btnAdd = new JButton("Adicionar");
        btnCancel = new JButton("Cancelar");
        btnAdd.setBounds(20, 140, 100, 30);
        btnCancel.setBounds(140, 140, 100, 30);
        // Buttons Actions
        btnAdd.addActionListener(new ActionListener(){            
            @Override
            public void actionPerformed(ActionEvent e) {             
                
                addUser();
            }            
        });
        
        btnCancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit = JOptionPane.showConfirmDialog(rootPane, "Deseja cancelar a operação?",
                                                        "Cancelar", JOptionPane.YES_NO_OPTION);
                
                if(exit == JOptionPane.YES_OPTION)
                {
                    dispose();
                }
            }            
        });
        // Add elements
        screen.add(lblUsername);
        screen.add(lblPassword);
        screen.add(lblRole);
        screen.add(txtUsername);
        screen.add(txtPassword);
        screen.add(cbRole);
        screen.add(btnAdd);
        screen.add(btnCancel);
        // Settings
        setLocationRelativeTo(null);
        setSize(300, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void addUser()
    {
        try{
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            String role = cbRole.getSelectedItem().toString();
            
            if(username.equals("") || password.equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Preencha os campos");
                return;
            }
            
            User u = new User();           
            u.setUsername(username);
            u.setPassword(password);
            u.setRole(role);
            
            UserDAO ud = new UserDAO(u);
                       
            ud.inserir();
            JOptionPane.showMessageDialog(rootPane, "Usuario cadastrado com sucesso!");
            txtUsername.setText("");
            txtPassword.setText("");                      
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro ao inserir o usuario");
        }
    }  
  
    
}
