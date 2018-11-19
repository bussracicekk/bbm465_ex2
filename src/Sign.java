import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
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
	
	public void signHashValue(String hashValue,PrivateKey priKey,PrintWriter registryFile,String filePath) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException,IOException, NoSuchAlgorithmException{
        byte[] data = hashValue.getBytes("UTF8");
        System.out.println(hashValue);
        Signature sig = Signature.getInstance("SHA1WithRSA");
      sig.initSign(priKey);
       sig.update(data);
        byte[] signatureBytes = sig.sign();
       

        
       String signedData = new String(Base64.getEncoder().encode(signatureBytes));
       System.out.println("signeddata"+signedData);
       registryFile.write("##signature: "+signedData);
       

      

        //System.out.println("***"+sig.verify(signatureBytes));
	}
	
	/*public void signHashValueForVerification(String hashValue,PublicKey pubKey,String registryString) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException{
        byte[] data = hashValue.getBytes("UTF8");
        //data = Base64.getDecoder().decode(hashValue);
        Signature sig = Signature.getInstance("SHA1WithRSA");
        //bunu anlamadim
        sig.initVerify(pubKey);
        sig.update(data);
       byte[] signatureBytes = sig.sign();
       
       
       
       
       String signedData = new String(Base64.getEncoder().encode(signatureBytes));
       registryString = registryString + ("##signature: "+signedData);
       System.out.println("***"+sig.verify(signatureBytes));
	}*/
	
	
}
