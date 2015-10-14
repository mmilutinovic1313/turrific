/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package turrificapp;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author markymark1346
 */
public class Board extends JFrame implements ActionListener {
    
    //TODO: We need Desk and Enemy baefore we can do Board
    // GridLayout? 
    // Push
    private JLabel map;
    private Desk desk;
    private Enemy enemies[];
    private User user;
    private JButton startWaveButton;
    private JButton weaponSelectButton[]; // need to create 3 default weapons in the class...
    private JTextArea userInformation;
    private StartMenu mainMenu;
    private StopMenu pauseMenu;
    private JButton pauseMenuButton;
    private JLabel pencilWeaponImage; // better name? 
    private JLabel staplerWeaponImage; // better name? 
    private JLabel rubberbandWeaponImage; // better name? 
    int boardSize = 10;
    
    // Someone fix this spacing -- I'm moving onto other things. This should be simple in just  layout change.
    public Board() {
        
        super("Board JFrame");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        JPanel gameboardPanel = new JPanel();
        JPanel sidebarPanel = new JPanel();
        
        mainMenu = new StartMenu(this);
        mainMenu.setVisible(false);
        
        pauseMenu = new StopMenu(this);
        pauseMenu.setVisible(false);
        
        pauseMenuButton = new JButton("Pause");
        pauseMenuButton.addActionListener(this);
        pauseMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        startWaveButton = new JButton("Start Wave");
        startWaveButton.addActionListener(this);
        startWaveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        weaponSelectButton = new JButton[0]; // Weapon 0 is the default? 
        //weaponSelectButton.addActionListener(this); Figure this shit out...
        enemies = new Enemy[10]; // verify that this is correct... base this number off of difficulty
        user = new User();
       
        //user information accessible through user.txt file, not sure if this is how you want to
        //run it for the constantly updated userInfo on sidebarPanel. If not it can easily be changed
        //to make it work just for saving and loading user profiles
        userInformation = new JTextArea();//new JLabel("User Information");
        userInformation.setSize(200, 100);
        userInformation.setVisible(true);
        userInformation.setEditable(false);
        userInformation.setAlignmentX(Component.CENTER_ALIGNMENT);
        try{
            FileReader fr = new FileReader("images/user.txt");
            BufferedReader br = new BufferedReader(fr);
            userInformation.read(br, "userInformation");
        }
        catch(IOException ioe){
            //error handling
        }

        map = new JLabel(new ImageIcon("images/board.png"));
        map.setLayout(new GridLayout(10,10));
        pencilWeaponImage = new JLabel(new ImageIcon("images/pencil.png"));
        pencilWeaponImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        staplerWeaponImage = new JLabel(new ImageIcon("images/stapler.png"));
        staplerWeaponImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        rubberbandWeaponImage = new JLabel(new ImageIcon("images/rubberband.png"));
        rubberbandWeaponImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        gameboardPanel.setLayout(new BorderLayout());
        gameboardPanel.setSize(600, 600);
        gameboardPanel.add(pauseMenu, BorderLayout.CENTER);
        gameboardPanel.add(mainMenu, BorderLayout.CENTER);
        gameboardPanel.add(map, BorderLayout.CENTER);

        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.PAGE_AXIS));
        sidebarPanel.setSize(200, 800);
        sidebarPanel.setBackground(Color.gray);
        sidebarPanel.add(startWaveButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0,10)));
        sidebarPanel.add(pauseMenuButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0,10)));
        sidebarPanel.add(userInformation);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0,30)));
        sidebarPanel.add(pencilWeaponImage);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0,50)));
        sidebarPanel.add(staplerWeaponImage);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0,50)));
        sidebarPanel.add(rubberbandWeaponImage);

        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                if((i == 3 && j == 3) || (i == 3 && j == 4)|| (i == 3 && j == 5)|| (i == 3 && j == 6)|| (i == 3 && j == 7)|| (i == 3 && j == 8)|| (i == 3 && j == 9)|| (i == 3 && j == 10)|| (i == 4 && j == 3)|| (i == 5 && j == 3)|| (i == 5 && j == 4)|| (i == 5 && j == 5)|| (i == 5 && j == 6)|| (i == 6 && j == 6)|| (i == 7 && j == 3)|| (i == 7 && j == 4)|| (i == 7 && j == 5)|| (i == 7 && j == 6)|| (i == 8 && j == 3)|| (i == 9 && j == 3)|| (i == 10 && j == 3)){
                    // Do nothing and sob because this if block is ugly
                    map.add(new JLabel()); // We add a fake JLabel so it skips a space...lol GridLayout
                    
                }
                else{
                    desk = new Desk(i,j); 
                    JLabel deskImage = desk.deskImageSetup();
                    JButton test = new JButton();
                    test.addActionListener(this);
                    map.add(test);
                    test.add(deskImage);
                }
            }
        }
        
        this.add(gameboardPanel, BorderLayout.CENTER);
        this.add(sidebarPanel, BorderLayout.LINE_END);
        
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public Board(Desk newDesk, Enemy newEnemies[], JTextArea newUserInformation){
        //
        // Populates the passed in values to the fields
        //
        desk = newDesk;
        enemies = newEnemies;
        userInformation = newUserInformation;
        
        display();
    }
    
    public StartMenu getStartMenu(){ // add sets?
        return mainMenu;
    }
    
    public StopMenu getStopMenu(){ // add sets?
        return pauseMenu;
    }
    
    public void actionPerformed(ActionEvent evt){
        Object obj = evt.getSource();
        if(obj == pauseMenuButton){
            pauseMenu.setVisible(true);
            
        }
        else{
            System.out.println("ham");
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
