import java.io.File;
import java.util.Scanner;

public class Part3 {
    public static void main(String[] args) throws Exception {
        File directory = new File("src/");
        String name = directory.getAbsolutePath() + "//Staff.csv";
        Scanner sc = new Scanner(new File(name));
        Staff[] staffs = new Staff[10000];

        // this will just print the header in CSV file
        sc.nextLine();

        int i = 0;
        String st = "";
        while (sc.hasNextLine())  //returns a boolean value
        {
            st = sc.nextLine();
            String[] data = st.split(",");
            staffs[i++] = new Staff(Integer.parseInt(data[0]), data[1], data[2], data[3], Float.parseFloat(data[4]), Float.parseFloat(data[5]));
        }
        sc.close();  //closes the scanner

        Scanner newSc = new Scanner(System.in);
        System.out.print("Please enter a float budget for a team project (more than 100000): ");
        float budget = newSc.nextFloat();
        Staff[] teamMembers = selectTeamMembers(staffs, budget);
        System.out.println("The selected team members are:");
        // Step 4: print all the selected team members.
        for (int j = 0; j < teamMembers.length; j++) {
            System.out.println(teamMembers[j]);
        }
    }

    private static Staff[] selectTeamMembers(Staff[] staffs, float budget) {
        Staff[] candidates = new Staff[staffs.length];
        Staff[] result = new Staff[staffs.length];

        int candidateCount = 0;
        // Step 1: find the candidate members from the ‘staffs’ array whose wage does not exceed the budget.
        for (int i = 0; i < staffs.length; i++) {
            if (staffs[i].getWage() <= budget) {
                candidates[candidateCount++] = staffs[i];
            }
        }

        // Step 2: use quicksort to sort the candidate members in descending order by “project completion rate”.
        quickSort(candidates, 0, candidates.length-1);

        // Step 3: take out the candidate members one by one from the array sorted in descending order
        // until their total wage does not exceed the budget.
        float totalWage = 0;
        int resultCount = 0;
        for (int i = 0; i < candidateCount; i++) {
            if (totalWage + candidates[i].getWage() <= budget) {
                result[resultCount++] = candidates[i];
                totalWage += candidates[i].getWage();
            }
        }

        Staff[] teamMembers = new Staff[resultCount];
        System.arraycopy(result, 0, teamMembers, 0, resultCount);
        return teamMembers;
    }

    static void quickSort(Staff[] candidates, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(candidates, start, end);
            quickSort(candidates, start, partitionIndex-1);
            quickSort(candidates, partitionIndex+1, end);
        }
    }

    static int partition(Staff[] candidates, int start, int end) {
        Staff pivot = candidates[end];
        int partitionIndex = start;
        for (int i = start; i < end; i++) {
            if (candidates[i].getProjectCompletionRate() > pivot.getProjectCompletionRate() ||
                    (candidates[i].getProjectCompletionRate()==pivot.getProjectCompletionRate()&&candidates[i].getEmpNo()<pivot.getEmpNo())){
                Staff temp = candidates[partitionIndex];
                candidates[partitionIndex] = candidates[i];
                candidates[i] = temp;
                partitionIndex++;
            }
        }
        Staff temp = candidates[partitionIndex];
        candidates[partitionIndex] = candidates[end];
        candidates[end] = temp;
        return partitionIndex;
    }

}
