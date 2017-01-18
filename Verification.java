public interface Verification
{
    public boolean isValidEmail(String email);
    public boolean isValidPassword(String password);
}

class Verify implements Verification
{

    public boolean isValidEmail(String email)
    {
        return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
    
    public boolean isValidPassword(String password)
    {
        //return password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{5,10}");
        return password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,10}");
    }
}
