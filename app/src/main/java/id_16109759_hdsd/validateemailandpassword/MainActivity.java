package id_16109759_hdsd.validateemailandpassword;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
{
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputUserNameEmail;
    private TextInputLayout textInputPassword;

    //Pattern used to set the Username to include at least
    // 1 upper & 1 lower character, 8 - 15 characters long,
    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    ".{8,15}" +             //at least 8 characters long, no more than 15
                    "$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[?@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,12}" +               //at least 8 characters no more than 12
                    "$");



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputEmail = findViewById(R.id.text_Input_Email);
        textInputPassword = findViewById(R.id.text_Input_Password);
        textInputUserNameEmail = findViewById(R.id.text_Input_UserName);
    }

    //Validation Methods
    //Validate Input of Email address (not checking email address is correct)
    private boolean validateEmail(){
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if(emailInput.isEmpty()) {
            textInputEmail.setError("Email Address required");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            textInputEmail.setError("Valid email address required");
            return false;
        } else {
            textInputEmail.setError(null);
            textInputEmail.setErrorEnabled(false); //space allowed for error message is removed
            return true;

        }
    }

    //Validate User Name is entered (Only)
    private boolean validateUserName() {
        String usernameInput = textInputUserNameEmail.getEditText().getText().toString().trim();

        //Check if usernameInput is empty
        if (usernameInput.isEmpty())
        {
            textInputUserNameEmail.setError("Username required");
            return false;
        } else if(!USERNAME_PATTERN.matcher(usernameInput).matches())
        {
            textInputUserNameEmail.setError("Username is not valid");
            return false;

//            if (usernameInput.length() < 8 ) {
//                textInputUserNameEmail.setError("Username is too short");
//                return false;
//            } else if (usernameInput.length() > 15) {
//                textInputUserNameEmail.setError("Username is too long");
//                return false;
//            } else {
//               // textInputUserNameEmail.setError("No special characters allowed");
//                return false;
//            }
        } else {
            textInputUserNameEmail.setError(null);
            textInputUserNameEmail.setErrorEnabled(false); //space allowed for error message is removed
            return true;
        }
    }

    //Validate password entered is entered no more than 10 characters long
    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if(passwordInput.isEmpty()) {
            textInputPassword.setError("Enter a password");
            return false;
        } else if(!PASSWORD_PATTERN.matcher(passwordInput).matches())
        {
            if (passwordInput.length() < 8) {
                textInputPassword.setError("Password is too short");
                return false;
            } else if (passwordInput.length() > 12) {
                textInputPassword.setError("Password is too long");
                return false;
            } else {
                textInputPassword.setError("Password is too weak");
                return false;
            }
        }
        else {
            textInputPassword.setError(null);
            textInputUserNameEmail.setErrorEnabled(false); //space allowed for error message is removed
            return true;
        }
    }

    //Note: using single vertical bar calls ALL methods, unlike double vertical bars which exits once a method fails.
    public void confirmInput(View v){
        if(!validateEmail() | validateUserName() | !validatePassword()) {
            return;
        }
        String input = "Email: " + textInputEmail.getEditText().getText().toString();
        input += "\n";
        input += "Username: " + textInputPassword.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + textInputPassword.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}
