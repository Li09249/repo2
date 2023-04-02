import java.io.File;
import java.util.Scanner;

public class Part2_2 {
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
        Scanner nowSc = new Scanner(System.in);
        String testFName;

        while (true) {
            System.out.println("Please enter the first name:");
            String fName = nowSc.nextLine();
            fName = fName.trim();

            // Check if it is all digits.
            boolean isNumber = true;
            try {
                Integer.valueOf(fName);
            } catch (NumberFormatException e) {
                isNumber = false;
            }

            // if the first name contains not all digits && it is not empty.
            if (!isNumber&&!"".equals(fName)) {
                testFName = fName;
                break;
            }else if (isNumber) {     // The first name contains all digits.
                try {
                    throw new AllDigitalException();
                } catch (AllDigitalException e) {
                    System.out.println(e.getMessage());
                }
            }else {           // The first name is empty.
                try {
                    throw new AllSpaceException();
                } catch (AllSpaceException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("Please enter the surname:");
        String sName = nowSc.nextLine();
        System.out.println("Please enter the department:");
        String department = nowSc.nextLine();
        System.out.println("Please enter the wage:");
        float wage = nowSc.nextFloat();
        System.out.println("Please enter the projectCompletionRate:");
        float projectCompletionRate = nowSc.nextFloat();

        // Invoke the addNewStaff method and pass the 5 parameters above into this method
        staffs = addNewStaff(staffs,testFName,sName,department,wage,projectCompletionRate);

        sc.close();
        nowSc.close();
    }

    private static Staff[] addNewStaff(Staff[] staffs, String fName, String sName, String department, float wage, float projectCompletionRate) {
        int lastEmpNo = staffs[staffs.length - 1].getEmpNo(); // Get the empNo of the last staff in the current array.
        Staff[] newStaffs = new Staff[staffs.length+1];// Create a new bigger array.
        System.arraycopy(staffs, 0, newStaffs, 0, staffs.length); // Copy the data from the current array to this new array.
        Staff newStaff = new Staff(lastEmpNo+1,fName,sName,department,wage,projectCompletionRate); // Assign the new staff record details to the newStaff object.
        System.out.println(newStaff); // Print this new staff record details.
        newStaffs[newStaffs.length-1] = newStaff; // Put this new staff into this new bigger array.
        return newStaffs;
    }
}
