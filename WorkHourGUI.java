import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import javax.swing.filechooser.*;

public class WorkHourGUI extends JPanel 
{
    private JLabel firstNameLabel, lastNameLabel, emailLabel, passwordLabel, dateLabel, cellLabel, picLabel, tempPicLabel, oldPasswordLabel, mainPagePic;
    private JPanel accountPanel, accountPanel2, menu, external, operation, signUpForm, editUserForm, editAccountForm, logInForm, jobForm, changePasswordForm, totalsPanel;
    private JPanel jobButtons, jobInfoPanel, newHours, dateOfBirth, cellPanel, window, lowerSignUpPanel, picPanel, changePicPanel = new JPanel();
    private JPanel reportsPanel, reportButtonsPanel, formBoxPanel;;
    private JButton signUp, logIn, browse, personalInfoEdit;
    private JButton pictureEdit = new JButton("Change Picture");
    private JTextArea descriptions[], jnameOP, reportDescription = new JTextArea();; 
    private JTextField email;
    private JPasswordField password;
    private String passwordRules, image = "icon.png", tempImage = "";
    private JScrollPane windowPane;
    private Color buttonColor = new Color(204, 217, 255), fadedButtonColor = new Color(230, 230, 255);
    private JRadioButton showPasswordButton;
    private ImageIcon imageIcon;
    private JFileChooser fileChooser;
    private File selectedFile;
    private Random r;
    private Verification verification;
    private GridLayout horLayout = new GridLayout(1,0), vertLayout = new GridLayout(0,1), totalsLayout = new GridLayout(1,1);
    private ButtonListener blisten = new ButtonListener();//Instantiates button listener
    private RadioListener rlisten = new RadioListener();//Instantiates radio button listener
    //private ClickListener clisten = new ClickListener();//Instantiates click listener       
    private SubmitOnEnter klisten = new SubmitOnEnter();//Instantiates key listener

    public WorkHourGUI()
    {           
       passwordRules = "Password must:\n*Be at least 8 characters long\n*Contain an uppercase letter\n*Contain a lowercase letter\n*Conatain at least one numerical digit\n*Contain no spaces";

        window = new JPanel();
        window.setPreferredSize(new Dimension(840,565));
        window.setBackground(new Color(0, 57, 230));

        //Creates scroll pane for window panel
        windowPane = new JScrollPane(window,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        windowPane.setPreferredSize(new Dimension(850,575));
        windowPane.setBorder(BorderFactory.createRaisedBevelBorder());               
        
        UIManager.put("Button.disabledText", new Color(0,0, 0));//Makes all disabled buttons have a black text
        repaint();

        //Operation Panel
        operation = new JPanel(); 
        operation.setBackground(Color.white);
        operation.setPreferredSize(new Dimension(850,555));
        
        browse = new JButton("Browse...");
        browse.addActionListener(blisten);//Browse button for changing profile pic
        
        makeSignUpForm();
        makeLogInForm();
        createForm();

        signUp = new JButton("Sign Up");
        signUp.addActionListener(blisten);

        logIn = new JButton("Log In");
        logIn.addActionListener(blisten);

        picLabel = new JLabel();

        picPanel = new JPanel();
        picPanel.add(picLabel);
        picPanel.setOpaque(true);
        picPanel.setBackground(Color.WHITE);
        setImage(image,picLabel,550,550);

        pictureEdit.addActionListener(blisten);
        
        operation.add(signUp);
        operation.add(logIn);
        operation.add(pictureEdit);
        operation.add(picPanel);
        window.add(operation);
        
        add(windowPane);

        setVisible(true);
    }

    public void makeSignUpForm(){
        signUpForm = new JPanel(new GridLayout(0,2));
        signUpForm.setPreferredSize(new Dimension(400,70));
        signUpForm.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    }

    public void makeLogInForm(){
        logInForm = new JPanel(new GridLayout(0,2));
        logInForm.setPreferredSize(new Dimension(400,70));
        logInForm.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    }

    public void createForm(){
        formBoxPanel = new JPanel();
        formBoxPanel.setLayout(new BoxLayout(formBoxPanel,BoxLayout.PAGE_AXIS));
        
        emailLabel = new JLabel("Email:",SwingConstants.CENTER);
        emailLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emailLabel.setOpaque(true);
        emailLabel.setBackground(fadedButtonColor);
        
        passwordLabel = new JLabel("Password:",SwingConstants.CENTER);
        passwordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passwordLabel.setOpaque(true);
        passwordLabel.setBackground(Color.WHITE);
        
        email = new JTextField("");
        email.setPreferredSize(new Dimension(200,20));
        email.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        email.addKeyListener(klisten);
        
        password = new JPasswordField("");
        password.setPreferredSize(new Dimension(200,20));
        password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        password.addKeyListener(klisten);

        showPasswordButton = new JRadioButton("Show Password");
        showPasswordButton.setBackground(Color.WHITE);
        showPasswordButton.addActionListener(rlisten);
        
        lowerSignUpPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Creates panel for submit and cancel below sign up form
        lowerSignUpPanel.setPreferredSize(new Dimension(337,30));
        lowerSignUpPanel.setOpaque(true);
        lowerSignUpPanel.setBackground(Color.WHITE);
    }

    //Returns new colour
    public Color makeColor(int r, int g, int b){
        return new Color(r,g,b);
    }
    
    //Yes or no dialog box
    public int confirmationBox(String message)
    {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        
        return JOptionPane.showConfirmDialog(null, message, "Confirm", dialogButton);
    }
    
    //User input dialog box
    public int inputBox(String message, JPanel panel)
    {
        return JOptionPane.showConfirmDialog(null, panel, message, JOptionPane.OK_CANCEL_OPTION);
    }

    //Creates sign up box
    public void signUpBox(JPanel signUpForm)
    {
        if(inputBox("Sign Up", signUpForm) == JOptionPane.OK_OPTION)
        {
            submitSignUpForm();
        }   
        signUp.setEnabled(true);

        email.setText("");
        password.setText("");
    }
    
    //Creates log in box
    public void logInBox(JPanel logInForm)
    {
        if(inputBox("Log In", logInForm) == JOptionPane.OK_OPTION)
        {
            submitLogInForm();
        }
        logIn.setEnabled(true);

        email.setText("");
        password.setText("");
    }
    
    //Creates box for changing picture
    public void changePicBox(JPanel changePicPanel)
    {
        if(inputBox("Change Picture", changePicPanel) == JOptionPane.OK_OPTION)
        {
            JOptionPane.showMessageDialog(null, "Picture Updated");
            
            setImage(tempImage,picLabel,550,550);
            
            operation.repaint();
            operation.revalidate();
        }
        pictureEdit.setEnabled(true);
    }

    //Concatenates a job name and location
    private String concatenate(String word1, String word2)
    {
        return word1 + " - " + word2;
    }
    
    //Separates the name from the concatenated form of a jobs name and location from a selected button's text value
    private String separateName(String button)
    {
        String sep[] = button.split(" - ");
        return sep[0];
    }
    
    //Separates the location from the concatenated form of a jobs name and location from a selected button's text value
    private String separateLocation(String button)
    {
        String sep[] = button.split(" - ");
        return sep[1];
    }
    
    //Removes all components from the screen excluding account and menu buttons
    private void clearScreen()
    {
        password.setText("");
        operation.removeAll();
    }
    
    //Clears fields in sign up form
    public void clearForm()
    {               
        email.setText("");
        password.setText("");
        
        password.setEchoChar((char)0);
        showPasswordButton.setSelected(false);
    }
    
    //Checks if sign up form is empty and returns boolean value
    public boolean isFormEmpty()
    {
        if(email.getText().trim().length() == 0 || String.valueOf(password.getPassword()).trim().length() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Changes image of designated image label
    public void setImage(String imgName, JLabel imageLabel, int hor, int ver)
    {   
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        if((new File(imgName)).exists())
        {
            //imageIcon = new ImageIcon(getClass().getResource(imgName.replace("\\","\\\\")));   
            imageIcon = new ImageIcon(getClass().getResource(imgName));      
            Image img = imageIcon.getImage().getScaledInstance(hor,ver,java.awt.Image.SCALE_SMOOTH);
            imageLabel.setText("");
            //imageLabel.setPreferredSize(new Dimension(800,ver));
            imageIcon = new ImageIcon(img);
            imageLabel.setIcon(imageIcon);
        }
        else
        {
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            imageLabel.setIcon(null);
            imageLabel.setText("Image not found");
            imageLabel.setPreferredSize(new Dimension(550,550));
            operation.revalidate();
            operation.repaint();
        }
    }
    
    //Submit Sign Up Form
    public void submitSignUpForm()
    {
        if(isFormEmpty())
        {
            JOptionPane.showMessageDialog(null,"Fill out all fields.");  
            signUpBox(signUpForm);
        }
        else
        {
            if(!verification.isValidEmail(email.getText()))
            {
                JOptionPane.showMessageDialog(null,"Email is invalid.");  
                signUpBox(signUpForm);
            }
            else
            {
                if(!verification.isValidPassword(String.valueOf(password.getPassword())))
                {
                    JOptionPane.showMessageDialog(null,passwordRules); 
                    signUpBox(signUpForm);
                }
                else
                {

                    JOptionPane.showMessageDialog(null, "New user has been added.");
                }                       
            }
        }
    }
    
    //Submit Login Form
    public void submitLogInForm()
    {  
        if(email.getText().trim().length() == 0 || String.valueOf(password.getPassword()).trim().length() == 0)
        {
            JOptionPane.showMessageDialog(null,"Fill out all fields.");  
            logInBox(logInForm);
        }
        else
        {
            if(!verification.isValidEmail(email.getText()))
            {
                JOptionPane.showMessageDialog(null,"Email is invalid."); 
                logInBox(logInForm);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Login Successful"); 
            }
        }
    }
    
    //Key listener to implement submit on pressing enter in forms
    private class SubmitOnEnter implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent event)
        {
            if(event.getKeyCode() == KeyEvent.VK_ENTER) //If Enter key is pressed
            {
                if((email.isFocusOwner() || password.isFocusOwner()) && !logIn.isEnabled())//In login form
                {
                    submitLogInForm();
                }
                else
                {
                    if((email.isFocusOwner() || password.isFocusOwner()) && !signUp.isEnabled())//In login form
                    {
                        submitLogInForm();
                    }
                }          
            }
        }
        
        @Override
        public void keyReleased(KeyEvent arg0) {

        }
    
        @Override
        public void keyTyped(KeyEvent arg0) {
    
        }
    }
    
    //Radio Listener Actions
    private class RadioListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if (showPasswordButton.isSelected())//Allows password to be visible
            {
                password.setEchoChar((char)0);
            }
            
            if (!showPasswordButton.isSelected())//Masks password with given echo character
            {
                password.setEchoChar((char)'*');
            }
        }
    }

    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent event)
        {       
            //Sign Up User Form
            if (event.getSource() == signUp)
            {
                signUp.setEnabled(false);

                signUpForm.add(emailLabel);
                signUpForm.add(email);
                signUpForm.add(passwordLabel);
                signUpForm.add(password);
                
                password.setEchoChar((char)'*');
                
                formBoxPanel.add(signUpForm);
                formBoxPanel.add(showPasswordButton);
                
                signUpBox(formBoxPanel);
            }
            
            //Log in user form
            if(event.getSource() == logIn)
            {
                logIn.setEnabled(false);

                password.setEchoChar((char)'*');
                
                logInForm.add(emailLabel);
                logInForm.add(email);
                logInForm.add(passwordLabel);
                logInForm.add(password);
                               
                logInBox(logInForm);
            }
                        
            //Change profile picture
            if (event.getSource() == pictureEdit)
            {
                pictureEdit.setEnabled(false);

                changePicPanel.removeAll();
                
                tempPicLabel = new JLabel();
                tempPicLabel.setPreferredSize(new Dimension(300,300));
                tempPicLabel.setHorizontalAlignment(JLabel.CENTER);  
                setImage(image,tempPicLabel,300,300);
                
                changePicPanel.add(tempPicLabel);
                changePicPanel.add(browse);
                
                changePicBox(changePicPanel);
            }
            
            //Browse button to change profile picture
            if (event.getSource() == browse)
            {
                try
                {
                    fileChooser = new JFileChooser(new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()));
                    //fileChooser = new JFileChooser("Pictures");
                    //defaults to designated file path
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }   
                
                //Allows only files with jpg and png extensions shown
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files (^.jpg, ^.png)","jpg","png"));
                
                int returnValue = fileChooser.showOpenDialog(null);
                
                if (returnValue == JFileChooser.APPROVE_OPTION) 
                {
                    selectedFile = fileChooser.getSelectedFile();
                    //setImage("Pictures\\\\" + selectedFile.getName(), picLabel, 200, 200);
                    setImage(selectedFile.getName(), tempPicLabel, 300, 300);
                    tempImage = selectedFile.getName();
                    //setImage(selectedFile.getAbsolutePath(), picLabel);
                }
            }
            
        }
    }
}