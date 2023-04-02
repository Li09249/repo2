import java.io.File;
import java.util.*;

public class ReadStaffData {

	public static void main(String[] args) throws Exception{
		//parsing and reading the CSV file data into the film (object) array
		// provide the path here...
        File directory = new File("src/");
  		String name = directory.getAbsolutePath() + "//Staff.csv";
		Scanner sc = new Scanner(new File(name));
		Staff[] staffs = new Staff[10000];

		// this will just print the header in CSV file
		sc.nextLine();

		int i = 0; String st = "";
		while (sc.hasNextLine())  //returns a boolean value
		{
			st = sc.nextLine();
			String[] data = st.split(",");
			staffs[i++] = new Staff(Integer.parseInt(data[0]), data[1], data[2], data[3], Float.parseFloat(data[4]), Float.parseFloat(data[5]));
		}
		sc.close();  //closes the scanner

		// We can print film details due to overridden toString method in film class
		System.out.println(staffs[0]);
		System.out.println(staffs[1]);

		// we can compare films based on their ID due to overridden CompareTo method in film class
		System.out.println(staffs[0]==staffs[0]);
		System.out.println(staffs[0]==staffs[1]);
	}

}


