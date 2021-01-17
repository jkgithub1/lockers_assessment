import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Lockers {

	String directory = "/home/jeyakumar/list";

	Scanner scan;
	
	boolean loopflag = true;

	public Lockers() {
		scan = new Scanner(System.in);
	}

	public void showMenu() {

		int option = 0;

		System.out.println("***********************************************************");

		System.out.println("Select Your option");

		System.out.println("1. Retrieving the file names in an ascending order");

		System.out.println("2. File Manipulation");

		System.out.println("3. Exit");

		System.out.println("***********************************************************");

		try {

			option = scan.nextInt();

		} catch (Exception e) {
			
			System.out.println("Invalid Input");
			
			this.loopflag = false;
			
		}

		switch (option) {
		case 1:
			this.listFile();
			break;
		case 2:
			this.showSubMenu();
			break;
		case 3:
			System.exit(0);
			break;
		default:
			break;
		}

	}

	public void listFile() {
		String[] files;
		File f = new File(this.directory);
		files = f.list();
		String temp;

		for (int j = 0; j < files.length; j++) {
			for (int i = j + 1; i < files.length; i++) {
				// comparing adjacent
				if (files[i].compareTo(files[j]) < 0) {
					temp = files[j];
					files[j] = files[i];
					files[i] = temp;
				}
			}
			System.out.println(files[j]);
		}

	}

	public void showSubMenu() {

		int option = 0;

		System.out.println("***********************************************************");

		System.out.println("Select Your option");

		System.out.println("1. Add a file to the application");

		System.out.println("2. Delete a file from the application");

		System.out.println("3. Search a from the application");

		System.out.println("4. Main Menu");

		System.out.println("***********************************************************");

		try {

			option = scan.nextInt();

		} catch (Exception e) {
			
			System.out.println("Invalid Input");
			
			this.loopflag = false;
			
		}
		

		switch (option) {
		case 1:

			this.createFile(this.getFileName());

			this.showSubMenu();

			break;
		case 2:

			this.delete(this.getFileName());

			this.showSubMenu();

			break;
		case 3:

			if (this.search(this.getFileName())) {
				System.out.println("The File is exist");
			} else {
				System.out.println("The File is not exist");
			}
			this.showSubMenu();

			break;
		case 4:
			this.showMenu();
			break;
		default:
			break;
		}

	}

	public String getFileName() {

		System.out.println("Enter File name with extension");

		return scan.next();

	}

	public void createFile(String fileName) {
		// Create file instance
		File file = new File(this.directory, fileName);
		try {
			if (file.createNewFile())
				System.out.println("File created!");
			else
				System.out.println("File already exists!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Incorrect file path!");
		}
	}

	public void delete(String fileName) {
		Path path = Paths.get(this.directory + '/' + fileName);
		try {
			Files.delete(path);
			System.out.println("File has been deleted successfully.");
		} catch (NoSuchFileException exce) {
			System.out.println("There is no file!!");
		} catch (DirectoryNotEmptyException directoryNotEmptyException) {
			directoryNotEmptyException.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean search(String x) {
		String[] files;
		File f = new File(this.directory);
		files = f.list();

		int n = files.length;
		for (int i = 0; i < n; i++) {
			if (files[i].equals(x))
				return true;
		}
		return false;
	}

	public static void main(String[] arg) {

		Lockers obj = new Lockers();

		System.out.println("LockedMe.com");

		while (obj.loopflag) {
			obj.showMenu();
		}

	}
}
