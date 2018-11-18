import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;
//integrity start -p /Users/Elmas/Documents/hacettepe/bbm/java/ex2/files -r registry.txt -l L -h MD5 -k PriKey PubKey -i #

public class Main {
	private static Scanner sc;
	static String folderPath;
	static String registerFilePath;
	static String logFilePath;
	static String hashAlgorithm;
	static String privateKey;
	static String publicKey;
	static String periodTime;

	//take command from console
	//split command
	//call functions according to command's part
	public static void readConsole() throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, InvalidKeyException, SignatureException{
		Hash hash = new Hash();
		Sign sign = new Sign();
		GenerateKeys gKeys = new GenerateKeys(1024);
		PrintWriter registryFile;
		FileOutputStream privateFile;
		FileOutputStream publicFile;
		sc = new Scanner(System.in);
		String command = sc.nextLine();
		String[] parts = command.split(" ");
		
		//for this command format:
		//"integrity start -p P -r R -l L -h H -k PriKey PubKey -i #"
		if(parts[1].equals("start")){
			registerFilePath = parts[5];
			registryFile = new PrintWriter(registerFilePath, "UTF-8");
			
			privateKey = parts[11];
			privateFile = new FileOutputStream(privateKey);
			gKeys.createKeys();
			gKeys.writeToFile(privateFile,privateKey, gKeys.getPrivateKey().getEncoded());
			
			PrivateKey priKey = sign.readPrivateKeyFile(privateKey);
			
			hashAlgorithm = parts[9];
			
			publicKey = parts[12];
			publicFile = new FileOutputStream(publicKey);
			gKeys.writeToFile(publicFile,publicKey, gKeys.getPublicKey().getEncoded());
			
			PublicKey pubKey = sign.readPublicKeyFile(publicKey);
			
			
			folderPath = parts[3];
			
			hash.createFilePathList(folderPath,registryFile,registerFilePath,hashAlgorithm);
			String hashValueEndOfRegistryFile = hash.getHashValueRegistry(registerFilePath, registryFile, hashAlgorithm);
			sign.signHashValue(hashValueEndOfRegistryFile,priKey,pubKey,registerFilePath,registryFile);
				
			logFilePath = parts[7];
								
			periodTime = parts[14];

			registryFile.close();
			privateFile.close();
			publicFile.close();
		}
		else if(parts[1].equals("stop")){
			System.out.println(command);
		}
		else{
			
		}
	}
	
public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException, SignatureException {
		readConsole();
		}
}