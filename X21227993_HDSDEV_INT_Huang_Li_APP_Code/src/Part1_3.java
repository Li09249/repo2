import java.io.File;
import java.util.Scanner;

public class Part1_3 {
    public static void main(String[] args) throws Exception {
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

        // First using quicksort to sort the array, then using binary search to search.
        quickSort(staffs,0,staffs.length-1);

        // Prompt the user to enter a target wage to search for
        Scanner newSc = new Scanner(System.in);
        System.out.println("Please enter the wage you want to search for:");
        float target = newSc.nextFloat();

        long binarySearchStart = System.nanoTime();
        //long linearSearchStart = System.nanoTime();

        // Create a boolean array to keep track of which Staff objects have been found
        boolean[] isSearch = new boolean[staffs.length];

        // Use an int variable searchNum to store the number of staff objects searched
        int searchNum = binarySearch(staffs, 0,staffs.length-1,target, isSearch);
        //int searchNum = linearSearch(staffs, target, isSearch);

        if (searchNum==0) {
            System.out.println("Not an existing wage");
            return;
        }

        // Create a new Staff array to store the searched staff objects
        Staff[] searched = new Staff[searchNum];
        int index = 0;
        for (int j = 0; j < isSearch.length; j++) {
            if (isSearch[j]) {
                searched[index] = staffs[j];  // if isSearch[j]==true, assign the staffs[j] value to the searched[index]
                ++index;
            }
        }
        for (int j = 0; j < searched.length; j++) {
            System.out.println(searched[j]);
        }

        long binarySearchEnd = System.nanoTime();
        System.out.println("Elapsed time for binarySearch：" + (binarySearchEnd - binarySearchStart) + " ns");
        /*long linearSearchEnd = System.nanoTime();
        System.out.println("Elapsed time for linearSearch：" + (linearSearchEnd - linearSearchStart) + " ns");*/
    }

    // Binary Search
    static int binarySearch(Staff[] staffs, int leftIndex, int rightIndex, float target, boolean[] isSearch) {
        while (leftIndex <= rightIndex){
            int midIndex = (leftIndex + rightIndex) / 2;
            if (staffs[midIndex].getWage() == target){
                isSearch[midIndex] = true;
                return binarySearch(staffs,leftIndex,midIndex-1,target,isSearch)+1+binarySearch(staffs,midIndex+1,rightIndex,target,isSearch);
            }
            else if (staffs[midIndex].getWage() > target){
                return binarySearch(staffs,leftIndex,midIndex-1,target,isSearch);
            }
            else{
                return binarySearch(staffs,midIndex+1,rightIndex,target,isSearch);
            }
        }
        return 0;
    }

    // Linear Search
    /*static int linearSearch(Staff[] staffs, float target, boolean[] isSearch){
        int searchNum = 0;
        for (int index = 0; index < staffs.length; index++){
            if (staffs[index].getWage() == target){
                isSearch[index] = true;
                searchNum++;
            }
        }
        return searchNum;
    }*/

    static void quickSort(Staff[] staffs,int start,int end) {
        if (start<end) {
            int partitionIndex = partition(staffs,start,end);
            quickSort(staffs,start,partitionIndex-1);
            quickSort(staffs,partitionIndex+1,end);
        }
    }

    static int partition(Staff[] staffs,int start,int end) {
        Staff pivot = staffs[end];
        int partitionIndex = start;
        for (int i = start; i < end; i++) {
            if (staffs[i].compareTo(pivot) <= 0) {
                Staff staff1 = staffs[i];
                Staff staff2 = staffs[partitionIndex];
                staffs[i] = staff2;
                staffs[partitionIndex] = staff1;
                partitionIndex++;
            }
        }
        Staff staff1 = staffs[end];
        Staff staff2 = staffs[partitionIndex];
        staffs[end] = staff2;
        staffs[partitionIndex] = staff1;
        return partitionIndex;
    }

}
