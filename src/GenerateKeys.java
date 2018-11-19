import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

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
	/*public void createKeys() throws NoSuchAlgorithmException, InvalidKeySpecException{
		// Anahtarımızı byte dizisine çevirelim.
		byte[] modulusBytes = Base64.getDecoder().decode("AJmvQm09j0UQEvHgZVt6KFjF8SI6Tj1M4xCpU1/Tof14UrUQycR238bFbbIVYuoyXGCsKi/kEqCJyZ0GrDeShXLaVoD5ErPZ7Yw6KMXsoprYlRo77h7zqb6Bep+UUhRtPR/PIhUoaLzDX3UaqKuEiaLlQcILqhqePIFtfrRZafT/zLIDGtyMOGK9E0API7ADddLPBd3jVqDUBA4mSXFqBOVIi+nHxvvEDi5IEdisEVYvKYv7xeAklsPRKmeQ0PsbO16z2fq4iXhus6Wn9Kcf/I4sz/AsF0puTlDczVScPdzRtVRM9lPg68MWFM10WaFmmJ4alNGmn0pDlWD9jsXLk2k=");
		byte[] exponentBytes = Base64.getDecoder().decode("AmWHRajWGR+dDASR0BFhm7DKh9wc6DvQtqvNTws/1XIQ6B3w541rA/CEO2i1+Cz8380Pg1utJq+/YyF9gghY6GebPbuknQVi/PLTO/LqvjtuZ9BTcEwgc4YVYXOwq/zgHSTfxTCyIW9yh6L4ymPwuYeVtO71oiChlOseNLXIPzfPyw51pbn4TBlAEkDY+ppNy2F+x6YBEe37V315BrZ3u6Xq0co/Ut9ldZyuIPnsGk80WbCrDqtz2Klx1NgRcLCe+sRYR/5naRNNI+Ut+ilDR4RlGYlGkPluP/o6QSD2EtatJCmpddXt9I2tmneEauRo+AF/SYHjoPUJQm1IKFz24Q==");

		// Algoritme BigInteger objesi istiyor. Aynı bizim bu anahtarları ilk ürettiğimzde bize verdiği gibi.
		BigInteger modulusBigInteger = new BigInteger(1, modulusBytes);
		BigInteger exponentBigInteger = new BigInteger(1, exponentBytes);

		// Anahtarımızla şifreleme için kullanacağımız PublicKey objemizi hazırlayalım.
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulusBigInteger, exponentBigInteger);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		
		// Anahtarımızı byte dizisine çevirelim.
		byte[] modulusBytes2 = Base64.getDecoder().decode("AJmvQm09j0UQEvHgZVt6KFjF8SI6Tj1M4xCpU1/Tof14UrUQycR238bFbbIVYuoyXGCsKi/kEqCJyZ0GrDeShXLaVoD5ErPZ7Yw6KMXsoprYlRo77h7zqb6Bep+UUhRtPR/PIhUoaLzDX3UaqKuEiaLlQcILqhqePIFtfrRZafT/zLIDGtyMOGK9E0API7ADddLPBd3jVqDUBA4mSXFqBOVIi+nHxvvEDi5IEdisEVYvKYv7xeAklsPRKmeQ0PsbO16z2fq4iXhus6Wn9Kcf/I4sz/AsF0puTlDczVScPdzRtVRM9lPg68MWFM10WaFmmJ4alNGmn0pDlWD9jsXLk2k=");
		byte[] exponentBytes2 = Base64.getDecoder().decode("AQAB");

		// Algoritme BigInteger objesi istiyor. Aynı bizim bu anahtarları ilk ürettiğimzde bize verdiği gibi.
		BigInteger modulusBigInteger2 = new BigInteger(1, modulusBytes2);
		BigInteger exponentBigInteger2 = new BigInteger(1, exponentBytes2);

		// Anahtarımızla şifreleme için kullanacağımız PublicKey objemizi hazırlayalım.
		KeyFactory keyFactory2 = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec keySpec2 = new RSAPublicKeySpec(modulusBigInteger2, exponentBigInteger2);
		PublicKey publicKey = keyFactory2.generatePublic(keySpec2);
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}*/

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	public void writeToFile(FileOutputStream fs,String path,byte[] key) throws IOException {
		File f = new File(path);
		//f.getParentFile().mkdirs();
		//String s = key.toString();

		fs = new FileOutputStream(f);
		fs.write(key);
		fs.flush();
		fs.close();
		
	}
}