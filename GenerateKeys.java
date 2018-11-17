import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GenerateKeys {

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

	public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fileoutputstream = new FileOutputStream(f);
		fileoutputstream.write(key);
		fileoutputstream.flush();
		fileoutputstream.close();
	}
	public static void createFolder() throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
		
		generatekeys = new GenerateKeys(1024);
		generatekeys.createKeys();
		generatekeys.writeToFile("KeyFolder/publicKey", generatekeys.getPublicKey().getEncoded());
		generatekeys.writeToFile("KeyFolder/privateKey", generatekeys.getPrivateKey().getEncoded());
		
	}

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
		createFolder();

	}

}