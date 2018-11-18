import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Scanner;
//integrity start -p /Users/Elmas/Documents/hacettepe/bbm/java/ex2/files -r R -l L -h H -k PriKey PubKey -i #

public class Main {
	private static Scanner sc;
	static String folderPath;
	static String registerFilePath;
	static String logFilePath;
	static String hashFunctionName;
	static String privateKey;
	static String publicKey;
	static String periodTime;

	//take command from console
	//split command
	//call functions according to command's part
	public static void readConsole() throws IOException, NoSuchAlgorithmException, NoSuchProviderException{
		Hash hash = new Hash();
		GenerateKeys gKeys = new GenerateKeys(1024);
		PrintWriter registryFile;
		PrintWriter privateFile;
		PrintWriter publicFile;
		sc = new Scanner(System.in);
		String command = sc.nextLine();
		String[] parts = command.split(" ");
		
		registerFilePath = parts[5];
		registryFile = new PrintWriter(registerFilePath, "UTF-8");
		
		privateKey = parts[11];
		privateFile = new PrintWriter(privateKey, "UTF-8");
		
		publicKey = parts[12];
		publicFile = new PrintWriter(publicKey, "UTF-8");
		
		//for this command format:
		//"integrity start -p P -r R -l L -h H -k PriKey PubKey -i #"
		if(parts[1].equals("start")){
			if(parts[2].equals("-p")){
				folderPath = parts[3];
				hash.createFilePathList(folderPath,registryFile,registerFilePath,parts[9]);
			}
			else if(parts[4].equals("-r")){
				registerFilePath = parts[5];
			}
			else if(parts[6].equals("-l")){
				logFilePath = parts[7];
			}
			else if(parts[8].equals("-h")){
				hashFunctionName = parts[9];
				
			}
			else if(parts[10].equals("-k")){
				privateKey = parts[11];
				publicKey = parts[12];
				gKeys.createKeys();
				gKeys.writeToFile(privateFile,privateKey, gKeys.getPrivateKey().getEncoded());
				gKeys.writeToFile(publicFile,publicKey, gKeys.getPublicKey().getEncoded());
			}
			else if(parts[13].equals("-i")){
				periodTime = parts[14];
			}
			registryFile.close();
		}
		else if(parts[1].equals("stop")){
			System.out.println(command);
		}
		else{
			
		}
	}
	
public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
		readConsole();
		}
}
