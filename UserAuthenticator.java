public class UserAuthenticator {
  private java.util.Map<String, User> registeredUsers;

  public UserAuthenticator() {
    this.registeredUsers = new java.util.HashMap<>();
  }

  public boolean register(User user) {
    if (user == null || user.getUsername() == null) {
      return false;
    }
    if (registeredUsers.containsKey(user.getUsername())) {
      return false;
    }
    registeredUsers.put(user.getUsername(), user);
    return true;
  }

  public User authenticate(String username, String password) {
    if (username == null || password == null) {
      return null;
    }
    User user = registeredUsers.get(username);
    if (user != null && user.verifyPassword(password)) {
      return user;
    }
    return null;
  }
}