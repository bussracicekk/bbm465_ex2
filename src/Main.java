import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
//integrity start -p /Users/Elmas/Documents/hacettepe/bbm/java/ex2/files -r R -l L -h H -k PriKey PubKey -i #

import javax.xml.bind.DatatypeConverter;

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
				createFilePathList(folderPath,registryFile);
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
	
	
	//input -> folder path
	//function : list files in this folder
	public static void createFilePathList(String folderPath, PrintWriter registryFile) throws IOException, NoSuchAlgorithmException{
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile() && !listOfFiles[i].isHidden()) {
		    getHashValue(listOfFiles[i].getPath(),registryFile);
		  }
		  else if (listOfFiles[i].isDirectory() && !listOfFiles[i].isHidden()) {
			  File subfolder = new File(listOfFiles[i].getPath());
			  File[] listOfSubFiles = subfolder.listFiles();
			  for (int k = 0; k < listOfSubFiles.length; k++) {
				  getHashValue(listOfSubFiles[k].getPath(),registryFile);
				  }
			  }
		}
	}
	
	//convert from file context(string format) to hash value
	//write file path - file's hash value
	public static void getHashValue(String filePath, PrintWriter registryFile) throws IOException, NoSuchAlgorithmException{
		String allFileString="";
		File file = new File(filePath); 
		System.out.println(filePath);
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String line; 
		while ((line = br.readLine()) != null) {
			allFileString = allFileString+line+"\n";
		}
		br.close();
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(allFileString.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		
		registryFile.write(filePath+" "+myHash+"\n");
	}

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		readConsole();
		}
}
