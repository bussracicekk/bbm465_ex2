import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
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
	public static void readConsole() throws IOException, NoSuchAlgorithmException{
		Hash hash = new Hash();
		PrintWriter registryFile;
		sc = new Scanner(System.in);
		String command = sc.nextLine();
		String[] parts = command.split(" ");
		
		registerFilePath = parts[5];
		registryFile = new PrintWriter(registerFilePath, "UTF-8");
		
		//for this command format:
		//"integrity start -p P -r R -l L -h H -k PriKey PubKey -i #"
		if(parts[1].equals("start")){
			if(parts[2].equals("-p")){
				folderPath = parts[3];
				hash.createFilePathList(folderPath,registryFile);
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
			else if(parts[9].equals("-k")){
				privateKey = parts[10];
				publicKey = parts[11];
			}
			else if(parts[12].equals("-i")){
				periodTime = parts[13];
			}
			registryFile.close();
		}
		else if(parts[1].equals("stop")){
			System.out.println(command);
		}
		else{
			
		}
	}
	
public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		readConsole();
		}
}
