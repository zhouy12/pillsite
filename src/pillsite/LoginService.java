package pillsite;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

import pillsite.DatabaseConnectionService;

public class LoginService {
	private static final Random RANDOM = new SecureRandom();
	private static final Base64.Encoder enc = Base64.getEncoder();
	private static final Base64.Decoder dec = Base64.getDecoder();
	private DatabaseConnectionService dbService = null;

	public LoginService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean useApplicationLogins() {
		return true;
	}
	
	public boolean login(String username, String password) {
		try {
			Connection connection = this.dbService.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT PasswordSalt, PasswordHash FROM [User] WHERE Username = ?");
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String saltString = rs.getString("PasswordSalt");
				String hash = rs.getString("PasswordHash");
				String hash1 = hashPassword(getBytesFromString(saltString), password);
				if(hash1.equals(hash)) {
					return true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Login Failed");
		return false;
	}

	public boolean register(String username, String password) {
		byte[] salt = getNewSalt();
		String hash = hashPassword(salt, password);
		CallableStatement cs = null;
		try {
			Connection connection = this.dbService.getConnection();
			cs = connection.prepareCall("{? = call Register(?, ?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, username);
			cs.setString(3, getStringFromBytes(salt));
			cs.setString(4, hash);
			cs.execute();	
			if(cs.getInt(1)!=0){
				throw new SQLException();
			}
			return true;
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Registration Failed");
			try {
				int retVal = cs.getInt(1);
				if (retVal == 1) {
					JOptionPane.showMessageDialog(null, "Registration Failed");
					return false;
				} else if (retVal == 2) {
					JOptionPane.showMessageDialog(null, "Registration Failed");
					return false;
				} else if (retVal == 3) {
					JOptionPane.showMessageDialog(null, "Registration Failed");
					return false;
				} else if (retVal == 4) {
					JOptionPane.showMessageDialog(null, "Registration Failed");
					return false;
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Registration Failed");
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	
	public byte[] getNewSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}
	
	public String getStringFromBytes(byte[] data) {
		return enc.encodeToString(data);
	}
	
	public byte[] getBytesFromString(String str) {
		return dec.decode(str);
	}

	public String hashPassword(byte[] salt, String password) {

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash = null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		}
		return getStringFromBytes(hash);
	}

}
