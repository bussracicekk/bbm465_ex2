import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Sign {

	//private static BufferedReader br;
	public Sign() {
		// TODO Auto-generated constructor stub
	}

	/*public String readRegistryFile(String registryFilePath) throws IOException{
		String sCurrentLine;
		BufferedReader br = new BufferedReader(new FileReader(registryFilePath));
		String lastLine = "";
		while ((sCurrentLine = br.readLine()) != null) 
		{
			// System.out.println(sCurrentLine);
			lastLine = sCurrentLine;
		}
		String parts[] = lastLine.split(" ");
		return parts[1];
	}*/
	
	public PrivateKey readPrivateKeyFile(String privateKeyFilePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException{
		 byte[] keyBytes = Files.readAllBytes(new File(privateKeyFilePath).toPath());
		 PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		 KeyFactory kf = KeyFactory.getInstance("RSA");
		 return kf.generatePrivate(spec);
	}
	
	public PublicKey readPublicKeyFile(String publicKeyFilePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException{
		byte[] keyBytes = Files.readAllBytes(new File(publicKeyFilePath).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes); 
		 KeyFactory kf = KeyFactory.getInstance("RSA");
		 return kf.generatePublic(spec);
	}
	
	public void signHashValue(String hashValue,PrivateKey priKey,PublicKey pubKey,String registryFilePath,PrintWriter registryFile) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException{
        byte[] data = hashValue.getBytes("UTF8");

        Signature sig = Signature.getInstance("SHA1WithRSA");
        sig.initSign(priKey);
        sig.update(data);
        byte[] signatureBytes = sig.sign();
       
       String signedData = new String(Base64.getEncoder().encode(signatureBytes));
       registryFile.write("##signature: "+signedData);

       //bunu anlamadim
        sig.initVerify(pubKey);
        sig.update(data);

        System.out.println("***"+sig.verify(signatureBytes));
	}
	
	
}
