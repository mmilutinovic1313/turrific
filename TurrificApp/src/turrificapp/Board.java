/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package turrificapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author markymark1346
 */
public class Board extends JFrame implements ActionListener {
    
    //TODO: We need Desk and Enemy baefore we can do Board
    // GridLayout? 
    // Push
    private Desk desks[][];
    private Enemy enemies[];
    private User user;
    private JButton startWave;
    private JButton weaponSelect[]; // need to create 3 default weapons in the class...
    private JLabel userInformation;
    private StartMenu mainMenu;
    private StopMenu pauseMenu;
    private JButton pauseMenuButton;
    
    
    public Board(){
        
        super("Board JFrame");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        
        mainMenu = new StartMenu();
        mainMenu.setVisible(false);
        
        pauseMenu = new StopMenu();
        pauseMenu.setVisible(false);
        
        pauseMenuButton = new JButton("Pause");
        desks = new Desk [10][10];
        enemies = new Enemy[10]; // verify that this is correct...
        user = new User();
        startWave = new JButton("Start Wave");
        weaponSelect = new JButton[0]; // Weapon 0 is the default? 
        userInformation = new JLabel("User Information");
        
        JPanel gameboardPanel = new JPanel();
        JPanel sidebarPanel = new JPanel();
        
        gameboardPanel.setSize(600, 600);
        gameboardPanel.setBackground(Color.red); // This will be changed to the image...
        
        sidebarPanel.setSize(200, 800);
        sidebarPanel.setBackground(Color.gray);
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.PAGE_AXIS));
        sidebarPanel.add(startWave);
        sidebarPanel.add(pauseMenuButton);
        
        this.add(gameboardPanel);
        this.add(sidebarPanel);

        this.setVisible(true);

        display();
    }
    
    public Board(Desk newDesks[][], Enemy newEnemies[], JLabel newUserInformation){
        //
        // Populates the passed in values to the fields
        //
        desks = newDesks;
        enemies = newEnemies;
        userInformation = newUserInformation;
        
        display();
    }
    
    public void actionPerformed(ActionEvent evt){
        Object obj = evt.getSource();
        if(obj == pauseMenuButton){
            pauseMenu.setVisible(true);
        }
        //
        // Updates the current map when an action is performed...
        //
        update();
    }
    
    public void display(){
        //
        // This initialized the display...this occurs on start and whenever the game updates.
        // This will depend on get and set methods from things...
        // If not path then desk...?
        //
        
        
    }
    
    public void update(){
        
    }
    
}
