// Daniel Chen
// 9 February 2020
// More 2d arrays exercises

import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

class phones { // add comments
	static List<String> phone = new ArrayList<String>();
	static List<String> names = new ArrayList<String>();
	static Scanner input = new Scanner(System.in);
	static File file = new File("names.txt");

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("1. Load phone numbers from file");
		System.out.println("2. Add phone numbers");
		System.out.println("3. Show phone numbers");
		System.out.println("4. Exit");

		int user = 0;
		while (user != 4) {
			System.out.print("Option #: ");
			user = input.nextInt();
			input.nextLine();
			
			switch (user) {
				case 1:
					read();
					break;
				case 2:
					write();
					break;
				case 3:
					display();
					break;
			}
		}
	}
				
	static void read() throws FileNotFoundException {
		Scanner finput = new Scanner(file);
		while (finput.hasNextLine()) {
			names.add(finput.nextLine());
			phone.add(finput.nextLine());
		}
		finput.close();
	}
		
	static void write() throws FileNotFoundException {
		String user = "";

		while (true) {
			System.out.print("Name: ");
			user = input.nextLine();
			if (user.equals("STOP")) {
				break;
			}
			names.add(user);
			System.out.print("Phone number: ");
			phone.add(input.nextLine());
		}

		PrintWriter foutput = new PrintWriter(file);
		for (int i = 0; i < names.size(); i++) {
			foutput.println(names.get(i));
			foutput.println(phone.get(i));
		}
		foutput.close();
	}

	static void display() {
		System.out.println(String.format("%-10s", "Name") + String.format("%-15s", "Number"));
		for (int i = 0; i < names.size(); i++) {
			System.out.print(String.format("%-10s", names.get(i)));
			System.out.println(String.format("%-15s", phone.get(i)));
		}
	}
}
