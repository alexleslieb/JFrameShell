public class Alternatives
{
    public void signupForm()
    {
        enableAllMenuButtons();
                
        clearScreen();
        
        clearLogInForm();
        
        operation.add(opHeader);
        operation.add(response);
        opHeader.setText("SIGN UP");
        response.setText("Enter user information below.");
        
        signUpForm.add(firstNameLabel);
        signUpForm.add(firstName);
        signUpForm.add(lastNameLabel);
        signUpForm.add(lastName);
        signUpForm.add(emailLabel);
        signUpForm.add(email);
        signUpForm.add(cellLabel);
        signUpForm.add(cellPanel);
        signUpForm.add(dateLabel);
        signUpForm.add(dateOfBirth);
        signUpForm.add(passwordLabel);
        signUpForm.add(password);

        lowerSignUpPanel.add(submit);
        lowerSignUpPanel.add(clear);
        
        operation.add(signUpForm);     
        operation.add(lowerSignUpPanel);
        operation.add(showPasswordButton);

        setAccountOpsStates(0);
                      
        operation.revalidate();
        operation.repaint();
    }
    
    public void loginForm()
    {
        enableAllMenuButtons();
                
        clearScreen();
        
        clearSignUpForm();
        
        password.setEchoChar((char)'•');
        
        operation.add(opHeader);
        operation.add(response);
        opHeader.setText("LOG IN ");
        response.setText("Enter user information below.");
        
        logInForm.add(emailLabel);
        logInForm.add(email);
        logInForm.add(passwordLabel);
        logInForm.add(password);
        
        operation.add(logInForm);
        operation.add(submit);
        operation.add(clear);
        
        setAccountOpsStates(1);
                      
        operation.revalidate();
        operation.repaint();
    }
    
    public void changePic()
    {
        clearScreen();
                
        pictureEdit.setEnabled(false);
        
        operation.add(opHeader);
        operation.add(response);
        operation.add(pictureEdit);

        response.setText("Click Browse to select a new profile picture");
        
        operation.add(profilePicLabel);
        operation.add(browse);
        operation.add(cancel);
        
        operation.revalidate();
        operation.repaint();
    }
    
    /*
     
    //Sumbit Sign Up Form
    if (event.getSource() == submit && !accountOps[0].isEnabled())
    {
        submitSignUpForm();
    }
    
    //Submit Log In Form
    if (event.getSource() == submit && !accountOps[1].isEnabled())
    {
        submitLogInForm();
    }
    
    if(event.getSource() == save && !pictureEdit.isEnabled())
    {
        loggedUser.setProfilePic(selectedFile.getName());
        
        work.changeProfilePic(loggedUser);
        
        JOptionPane.showMessageDialog(null, "Profile picture has been updated");
        
        clearScreen();
        
        pictureEdit.setEnabled(true);
        
        accountPage();
    }
    
    //Save new profile pic for user
    if(event.getSource() == save && !pictureEdit.isEnabled())
    {
        loggedUser.setProfilePic(selectedFile.getName());
        
        work.changeProfilePic(loggedUser);
        
        JOptionPane.showMessageDialog(null, "Profile picture has been updated");
        
        clearScreen();
        
        pictureEdit.setEnabled(true);
        
        accountPage();
    }
    
    //Cancel uploading profile pic
    if(event.getSource() == cancel && !pictureEdit.isEnabled())
    {
        clearScreen();
        
        pictureEdit.setEnabled(true);
        
        accountPage();
    }
    */
}