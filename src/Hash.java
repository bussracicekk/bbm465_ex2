import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Hash {
	public String registryFileHash;

	public Hash() {
		// TODO Auto-generated constructor stub
	}
	
	public String getregistryFileHash(){
		return registryFileHash;
	}
	
	//input -> folder path
	//function : list files in this folder
	public void createFilePathList(String folderPath, PrintWriter registryFile, String registryFilePath,String hashAlgorithm) throws IOException, NoSuchAlgorithmException{
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && !listOfFiles[i].isHidden()) {
				getHashValue(listOfFiles[i].getPath(),registryFile,hashAlgorithm);
				}
			else if (listOfFiles[i].isDirectory() && !listOfFiles[i].isHidden()) {
				File subfolder = new File(listOfFiles[i].getPath());
				File[] listOfSubFiles = subfolder.listFiles();
				for (int k = 0; k < listOfSubFiles.length; k++) {
					getHashValue(listOfSubFiles[k].getPath(),registryFile,hashAlgorithm);
				}
			}
		}
		//getHashValueRegistry(registryFilePath,hashAlgorithm,registryFile);
	}
	
	//convert from file context(string format) to hash value
	//write "file path - file's hash value"
	public void getHashValue(String filePath, PrintWriter registryFile,String hashAlgorithm) throws IOException, NoSuchAlgorithmException{
		String allFileString="";
		File file = new File(filePath); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String line; 
		while ((line = br.readLine()) != null) {
			allFileString = allFileString+line+"\n";
		}
		br.close();
		MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
		md.update(allFileString.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		registryFile.write(filePath+' '+myHash+"\n");
	}
	
	public String getHashValueRegistry(String filePath,String hashAlgorithm) throws IOException, NoSuchAlgorithmException{
		String allFileString="";
		File file = new File(filePath); 
		
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String line; 
		while ((line = br.readLine()) != null) {
			allFileString = allFileString+line+'\n';
		}
		allFileString = allFileString.substring(0, allFileString.length() - 1);
		br.close();
		MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
		md.update(allFileString.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		registryFileHash = myHash;
		//registryFile.write("##signature: "+myHash);
		System.out.println(myHash);
		return myHash;
		//return allFileString;
	}
	
	public String getHashValueRegistryForVerification(String filePath,String hashAlgorithm) throws IOException, NoSuchAlgorithmException{
		String allFileString="";
		
		File file = new File(filePath); 
		
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String line; 
		while ((line = br.readLine()) != null) {
			if(!line.contains("##sig")){
				allFileString = allFileString+line+'\n';
			}
			else{
				
			}
		}
		allFileString = allFileString.substring(0, allFileString.length() - 1);
		br.close();
		MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
		md.update(allFileString.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		registryFileHash = myHash;
		//registryFile.write("##signature: "+myHash);
		System.out.println(myHash);
		return myHash;
		//return allFileString;
	}
	
	
	/*public String ForVerification(String registryString,String hashAlgorithm) throws IOException, NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
		md.update(registryString.getBytes());
		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		registryFileHash = myHash;
		return myHash;
	}*/
	
	
	//input -> folder path
		//function : list files in this folder
	/*	public void createFilePathListForVerification(String folderPath,String hashAlgorithm,String registryString) throws IOException, NoSuchAlgorithmException{
			File folder = new File(folderPath);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile() && !listOfFiles[i].isHidden()) {
					getHashValueForVerification(listOfFiles[i].getPath(),hashAlgorithm,registryString);
					}
				else if (listOfFiles[i].isDirectory() && !listOfFiles[i].isHidden()) {
					File subfolder = new File(listOfFiles[i].getPath());
					File[] listOfSubFiles = subfolder.listFiles();
					for (int k = 0; k < listOfSubFiles.length; k++) {
						getHashValueForVerification(listOfSubFiles[k].getPath(),hashAlgorithm,registryString);
					}
				}}
			}*/
			//getHashValueRegistry(registryFilePath, registryFile,hashAlgorithm);
			
		
		
		//convert from file context(string format) to hash value
		//write "file path - file's hash value"
	/*	public void getHashValueForVerification(String filePath,String hashAlgorithm,String registryString) throws IOException, NoSuchAlgorithmException{
			String allFileString="";
			File file = new File(filePath); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String line; 
			while ((line = br.readLine()) != null) {
				allFileString = allFileString+line+"\n";
			}
			br.close();
			MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
			md.update(allFileString.getBytes());
			byte[] digest = md.digest();
			String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			registryString = registryString + (filePath+" "+myHash+"\n");
		}*/
	
	
}