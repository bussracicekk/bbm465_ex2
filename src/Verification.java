import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class Verification {

	public Verification() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean verification(PublicKey publicKey,String hashData,String sign) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException{
		byte[] dateBytes = hashData.getBytes("UTF-8");
		byte[] signatureBytes = Base64.getDecoder().decode(sign);
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initVerify(publicKey);
		signature.update(dateBytes);
		boolean verified = signature.verify(signatureBytes);
		return verified;
	}

}


