public class LogInActivity {
  private UserAuthenticator authenticator;

  public LogInActivity(UserAuthenticator authenticator) {
    this.authenticator = authenticator;
  }

  public User logIn(String username, String password) {
    User user = authenticator.authenticate(username, password);
    if (user != null) {
      System.out.println("Successful login");
    } else {
      System.out.println("Invalid username or password");
    }
    return user;
  }
}