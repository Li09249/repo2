import java.io.File;
import java.util.Scanner;

public class Part2_1 {
    public static void main(String[] args) throws Exception{
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
        sc.close();

        Scanner newSc = new Scanner(System.in);
        System.out.println("Enter New Record Details");
        System.out.println("Please enter the first name:");
        String fName = newSc.nextLine();
        System.out.println("Please enter the surname:");
        String sName = newSc.nextLine();
        System.out.println("Please enter the department:");
        String department = newSc.nextLine();
        System.out.println("Please enter the wage:");
        float wage = newSc.nextFloat();
        System.out.println("Please enter the project completion rate:");
        float projectCompletionRate = newSc.nextFloat();

        // Invoke the addNewStaff method and pass the 5 parameters above into this method
        staffs = addNewStaff(staffs,fName,sName,department,wage,projectCompletionRate);
    }

    private static Staff[] addNewStaff(Staff[] staffs, String fName, String sName, String department, float wage, float projectCompletionRate) {
        int lastEmpNo = staffs[staffs.length - 1].getEmpNo();  // Get the empNo of the last staff in the current array.
        Staff[] newStaffs = new Staff[staffs.length+1];// Create a new bigger array.
        System.arraycopy(staffs, 0, newStaffs, 0, staffs.length); // Copy the data from the current array to this new bigger array.
        Staff newStaff = new Staff(lastEmpNo+1,fName,sName,department,wage,projectCompletionRate); // Assign the new staff record details to the newStaff object.
        System.out.println(newStaff); // Print this new staff record details.
        newStaffs[newStaffs.length-1] = newStaff; // Put this new staff into this new bigger array.
        return newStaffs;
    }

}
