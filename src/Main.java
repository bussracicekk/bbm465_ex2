import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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
//integrity start -p /Users/Elmas/Documents/hacettepe/bbm/java/ex2/files -r /Users/Elmas/Documents/hacettepe/bbm/java/ex2/registry.txt -l L -h MD5 -k PriKey PubKey -i #
//integrity -p /Users/Elmas/Documents/hacettepe/bbm/java/ex2/files -r registry.txt -l L -h MD5 -k PubKey
public class Main {
	private static Scanner sc;
	static String folderPath;
	static String registerFilePath;
	static String logFilePath;
	static String hashAlgorithm;
	static String privateKeyPath;
	static String publicKey;
	static String periodTime;

	//take command from console
	//split command
	//call functions according to command's part
	public static void callStartingCommand(String parts[]) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, InvalidKeyException, SignatureException{
		Hash hash = new Hash();
		Sign sign = new Sign();
		GenerateKeys gKeys = new GenerateKeys(1024);
		PrintWriter registryFile;
		FileOutputStream privateFile;
		FileOutputStream publicFile;

		//for this command format:
		//"integrity start -p P -r R -l L -h H -k PriKey PubKey -i #"
		if(parts[1].equals("start")){
			registerFilePath = parts[5];
			registryFile = new PrintWriter(new File(registerFilePath));
			
			privateKeyPath = parts[11];
			privateFile = new FileOutputStream(privateKeyPath);
			gKeys.createKeys();
			gKeys.writeToFile(privateFile,privateKeyPath, gKeys.getPrivateKey().getEncoded());
			PrivateKey priKey = sign.readPrivateKeyFile(privateKeyPath);
			
			hashAlgorithm = parts[9];
			
			publicKey = parts[12];
			publicFile = new FileOutputStream(publicKey);
			gKeys.writeToFile(publicFile,publicKey, gKeys.getPublicKey().getEncoded());
			
			folderPath = parts[3];
			hash.createFilePathList(folderPath,registryFile,registerFilePath,hashAlgorithm);

			logFilePath = parts[7];
								
			periodTime = parts[14];

			registryFile.close();
			privateFile.close();
			publicFile.close();
			//registryFile = new PrintWriter(registerFilePath, "UTF8",true);
			PrintWriter registryFile2 = new PrintWriter(new FileOutputStream(new File(registerFilePath), true )); 
			
			String hashValueEndOfRegistryFile = hash.getHashValueRegistry(registerFilePath, hashAlgorithm);
			sign.signHashValue(hashValueEndOfRegistryFile,priKey,registryFile2,registerFilePath);
			registryFile2.close();
		}
		else if(parts[1].equals("stop")){
			//System.out.println(command);
		}
	}
	
	//integrity -p /Users/Elmas/Documents/hacettepe/bbm/java/ex2/files -r registry.txt -l L -h MD5 -k PubKey
	public static void callPeriodicCommand(String parts[]) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, InvalidKeySpecException, InvalidKeyException, SignatureException{
		Hash hash = new Hash();
		Sign sign = new Sign();
		File registryFile;
		String registryString = "";
		
		publicKey = parts[10];
		PublicKey pubKey = sign.readPublicKeyFile(publicKey);

		registerFilePath = parts[4];
		File registerFile = new File(registerFilePath); 
		BufferedReader br = new BufferedReader(new FileReader(registerFile)); 
		String hashData="";
		
		String signature="";
		String st; 
		while ((st = br.readLine()) != null) {
			if(!st.contains("##signature:")){
				//hashData = hashData + st + '\n';
			}
			else{
				String p[] = st.split(" ");
				signature= p[1];
			}
		  }
		
		registryString=hash.createFilePathListForVerification(parts[2], parts[8]);
	
		 
		 registryString=registryString.substring(0, registryString.length()-1);
		hashData=hash.getHashValueRegistryForVerification(registryString, parts[8]);
		Verification veri = new Verification();
		boolean verific = veri.verification(pubKey, hashData, signature);
		if (verific == false){
			String partsRegistryString[]= registryString.split("\n");
			File file = new File(registerFilePath); 
			BufferedReader br2 = new BufferedReader(new FileReader(file)); 
			String line; 
			int i=0;
			while ((line = br2.readLine()) != null){
				if(!line.contains("##sign")){
					String partsLine[]=line.split(" ");
					if(!line.equals(partsRegistryString[i])){
						System.out.println(partsLine[0]+" --> dosyada değişiklik yapılmış");
					}
					else{
						
					}
					i++;
				}
				else{
					
				}
			}
		}
		else{
			System.out.println("dosyalarda değişiklik yok");
		}
		}
	
	public static void readConsole() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, SignatureException, IOException{
		sc = new Scanner(System.in);
		String command = sc.nextLine();
		String[] parts = command.split(" ");
		File file =null;
		if(parts[1].equals("start")){
			file = new File(parts[5]);
		}
		else{
			file = new File(parts[4]);
		}
		//call starting command
		if(file.length()==0){
			callStartingCommand(parts);
		}
		else{
			callPeriodicCommand(parts);
		}
	}
	
public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, InvalidKeySpecException, SignatureException {
		readConsole();
		}
}