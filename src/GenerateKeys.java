import java.io.File;
<<<<<<< HEAD
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
=======
import java.io.FileOutputStream;
import java.io.IOException;
>>>>>>> keys
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

<<<<<<< HEAD
import javax.sound.sampled.AudioFormat.Encoding;

public class GenerateKeys {

=======
public class GenerateKeys {
>>>>>>> keys
	private KeyPairGenerator keyPairGen;
	private KeyPair keyPair;
	private PrivateKey privateKey;
	private PublicKey publicKey;
	static GenerateKeys generatekeys;

	public GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyPairGen = KeyPairGenerator.getInstance("RSA");
		this.keyPairGen.initialize(keylength);
	}

	public void createKeys() {
		this.keyPair = this.keyPairGen.generateKeyPair();
		this.privateKey = keyPair.getPrivate();
		this.publicKey = keyPair.getPublic();
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

<<<<<<< HEAD
	public void writeToFile(PrintWriter pKey,String path,byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();
		String s = key.toString();

		pKey = new PrintWriter(path);
		pKey.write(s);
		pKey.flush();
		pKey.close();
		
	}
	/*public void createPriKey(PrintWriter privateKey) throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
		
		generatekeys = new GenerateKeys(1024);
		generatekeys.createKeys();
		generatekeys.writeToFile(privateKey, main.privateKey, generatekeys.getPublicKey().getEncoded());
		//generatekeys.writeToFile(publicKey,main.publicKey, generatekeys.getPrivateKey().getEncoded());
		
	}*/

	/*public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
		createFolder();

	}*/

=======
	public void writeToFile(FileOutputStream fs,String path,byte[] key) throws IOException {
		File f = new File(path);
		//f.getParentFile().mkdirs();
		//String s = key.toString();

		fs = new FileOutputStream(f);
		fs.write(key);
		fs.flush();
		fs.close();
		
	}
>>>>>>> keys
}