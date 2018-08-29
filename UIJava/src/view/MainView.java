/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import model.User;
import persistent.UserDAO;


/**
 *
 * @author felipe
 */
public class MainView extends JFrame 
{
    private User user;
    
    JToolBar toolBar;
    JButton btnAdd, btnRemove, btnUpdate, btnList;
    JLabel lblUsername, lblRole, lblImage;
    ImageIcon imgAdd, imgRemove, imgUpdate, imgList, imgUser;
    JTable tblUsers;
    DefaultTableModel model;
    JScrollPane scroll;
    
    public MainView(User user)
    {        
        // Title
        super("MVC COM JAVA");
        // User
        this.user = user;
        // Container        
        Container screen = getContentPane();
        setLayout(null);
        // Tool Bar Buttons
        // Configure Images
        imgAdd = new ImageIcon(getClass().getResource("/res/add.png"));
        imgAdd.setImage(imgAdd.getImage().getScaledInstance(40, 40, 100));
        imgRemove = new ImageIcon(getClass().getResource("/res/remove.png"));
        imgRemove.setImage(imgRemove.getImage().getScaledInstance(40, 40, 100));
        imgUpdate = new ImageIcon(getClass().getResource("/res/update.png"));
        imgUpdate.setImage(imgUpdate.getImage().getScaledInstance(40, 40, 100));
        imgList = new ImageIcon(getClass().getResource("/res/list.png"));
        imgList.setImage(imgList.getImage().getScaledInstance(40, 40, 100));
        // Add image
        btnAdd = new JButton(imgAdd);
        btnRemove = new JButton(imgRemove);
        btnUpdate = new JButton(imgUpdate);
        btnList = new JButton(imgList);
        // Tool Bar
        toolBar = new JToolBar("CRUD Basico");
        toolBar.add(btnAdd);
        toolBar.add(btnRemove);
        toolBar.add(btnUpdate);
        toolBar.add(btnList);
        // Tool bar pos
        toolBar.setBounds(100, 200, 260, 50);
        // Tool Bar Buttons
        btnAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add User
                AddUserView addUser = new AddUserView(null, "Novo Usuario", true);
            }
        
        });
        btnRemove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove User
                 if(tblUsers.getSelectedRow() < 0)
                {
                    JOptionPane.showMessageDialog(rootPane, "Selecione um Registro para remover");
                }
                 else if(tblUsers.getSelectedRow() == 0)
                {
                    JOptionPane.showMessageDialog(rootPane, "Você não pode Excluir todos os registros de usuario");
                }
                 else
                {
                    int rowNum = tblUsers.getSelectedRow();
                    
                    int id = (int) model.getValueAt(rowNum, 0);
                    
                    User u = new User();
                    u.setIduser(id);
                    
                    int confirm = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir esse registro?",
                                                        "Excluir registro", JOptionPane.YES_NO_OPTION);
                
                    if(confirm == JOptionPane.YES_OPTION)
                    {
                        removeUser(u);
                    }
                    
                }
            }
        
        });
        btnUpdate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update User
                if(tblUsers.getSelectedRow() < 0)
                {
                    JOptionPane.showMessageDialog(rootPane, "Selecione um Registro para alterar");
                }
                else
                {
                    int rowNum = tblUsers.getSelectedRow();
                    
                    int id = (int) model.getValueAt(rowNum, 0);
                    String username = (String) model.getValueAt(rowNum, 1);
                    String role = (String) model.getValueAt(rowNum, 2);
                    
                    User u = new User();
                    u.setIduser(id);
                    u.setUsername(username);
                    u.setRole(role);
                    
                    UpdateUserView upUser = new UpdateUserView(null, "Alterar Usuario", true, u);
                }
            }
        
        });
        btnList.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // List User
                listarUsers(model);
            }
        
        });
        // Labels
        // Image User
        imgUser = new ImageIcon(getClass().getResource("/res/cut.png"));
        imgUser.setImage(imgUser.getImage().getScaledInstance(40, 40, 100));
        lblImage = new JLabel(imgUser);
        lblImage.setBounds(1, 1, 150, 150);
        // Username
        lblUsername = new JLabel("Usuario: "+user.getUsername());
        lblUsername.setBounds(155, 1, 160, 20);        
        // Role
        lblRole = new JLabel("Permissão: "+user.getRole());
        lblRole.setBounds(155, 40, 160, 20);
        // Table
        setTable();
        scroll = new JScrollPane(tblUsers);
        scroll.setBounds(50, 250, 350, 200);
        // Add elements
        screen.add(lblImage);
        screen.add(lblUsername);
        screen.add(lblRole);        
        screen.add(toolBar);
        screen.add(scroll);
        // Tamanho e localização da Janela
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        // Sair
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void setTable()
    {
        model = new DefaultTableModel();        
        tblUsers = new JTable(model);
        
        // Column Headers
        String header[] = {"ID", "Nome de Usuario", "Permissão"};
        
        for (int i = 0; i < header.length; i++) {
            model.addColumn(header[i]);
        }
        listarUsers(model);
    }
    
    private void listarUsers(DefaultTableModel model)
    {
        try {
            List<User> users = new ArrayList<User>();
            
            model.setNumRows(0);
            
            UserDAO ud = new UserDAO(user);
            
            users =  ud.listar();
            
            for(User u : users){
                model.addRow(new Object[]{u.getIduser(), u.getUsername(), u.getRole()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Um erro ocorreu!");
        }       
    }
    
    private void removeUser(User u)
    {
        try{
            UserDAO ud = new UserDAO(u);
            
            ud.excluir();
            
            JOptionPane.showMessageDialog(rootPane, "Usuario Removido!");
            listarUsers(model);
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Um erro ocorreu");
        }
    }
}
