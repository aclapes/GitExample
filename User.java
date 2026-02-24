import java.util.UUID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class User {
  private UUID id;
  private String username;
  private byte[] salt;
  private byte[] passwordHash;
  public boolean premium;

  public User(String username, String password) {
    this.id = UUID.randomUUID();
    this.username = username;
    this.salt = generateSalt();
    this.passwordHash = hashPassword(password, this.salt);
  }

  public String getUsername() {
    return username;
  }

  private static byte[] generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return salt;
  }

  static byte[] hashPassword(String password, byte[] salt) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      digest.update(salt);
      return digest.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("SHA-256 not available", e);
    }
  }

  public boolean verifyPassword(String password) {
    byte[] candidate = hashPassword(password, this.salt);
    return MessageDigest.isEqual(candidate, this.passwordHash);
  }
}
