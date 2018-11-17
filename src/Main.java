import java.io.File;
import java.util.Scanner;


public class Main {

	private static Scanner sc;
	static String folderPath;
	static String registerFilePath;
	static String logFilePath;
	static String hashFunctionName;
	static String privateKey;
	static String publicKey;
	static String periodTime;

	public static void readConsole(){
		sc = new Scanner(System.in);
		String command = sc.nextLine();
		String[] parts = command.split(" ");
		
		if(parts[1].equals("start")){
			if(parts[2].equals("-p")){
				folderPath = parts[3];
				createFilePathList(folderPath);
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
		}
		else if(parts[1].equals("stop")){
			System.out.println(command);
		}
		else{
			
		}
	}
	public static void createFilePathList(String folderPath){
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile() && !listOfFiles[i].isHidden()) {
		    System.out.println("File " + listOfFiles[i].getPath());
		  }
		}
	}

	public static void main(String[] args) {
		readConsole();
		}
}
