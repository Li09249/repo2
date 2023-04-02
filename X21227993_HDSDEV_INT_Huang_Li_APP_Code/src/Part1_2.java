import java.io.File;
import java.util.Scanner;

public class Part1_2 {
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

        // Test 10 records
        long startTime10 = System.nanoTime();
        quickSort(staffs,0,9);
        //mergeSort(staffs, 0, 9);
        long endTime10 = System.nanoTime();

        // Test 100 records
        long startTime100 = System.nanoTime();
        quickSort(staffs,0,99);
        //mergeSort(staffs, 0, 99);
        long endTime100 = System.nanoTime();

        // Test 1000 records
        long startTime1000 = System.nanoTime();
        quickSort(staffs,0,999);
        //mergeSort(staffs, 0, 999);
        long endTime1000 = System.nanoTime();

        // Test 5000 records
        long startTime5000 = System.nanoTime();
        quickSort(staffs,0,4999);
        //mergeSort(staffs, 0, 4999);
        long endTime5000 = System.nanoTime();

        // Test 10,000 records
        long startTime10000 = System.nanoTime();
        quickSort(staffs,0,9999);
        //mergeSort(staffs, 0, 9999);
        long endTime10000 = System.nanoTime();

        System.out.println("Average elapsed time for 10 records：" + (endTime10-startTime10) / 10 + " ns");
        System.out.println("Average elapsed time for 100 records：" + (endTime100-startTime100) / 100 + " ns");
        System.out.println("Average elapsed time for 1000 records：" + (endTime1000-startTime1000) / 1000 + " ns");
        System.out.println("Average elapsed time for 5000 records：" + (endTime5000-startTime5000) / 5000 + " ns");
        System.out.println("Average elapsed time for 10000 records：" + (endTime10000-startTime10000) / 10000 + " ns");

    }

    // Quicksort
    static void quickSort(Staff[] staffs, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(staffs, start, end);
            quickSort(staffs, start, partitionIndex-1);
            quickSort(staffs, partitionIndex+1, end);
        }
    }

    static int partition(Staff[] staffs, int start, int end) {
        Staff pivot = staffs[end];
        int partitionIndex = start;
        for (int i = start; i < end; i++) {
            if (staffs[i].compareTo(pivot) <= 0){
                Staff temp = staffs[partitionIndex];
                staffs[partitionIndex] = staffs[i];
                staffs[i] = temp;
                partitionIndex++;
            }
        }
        Staff temp = staffs[partitionIndex];
        staffs[partitionIndex] = staffs[end];
        staffs[end] = temp;
        return partitionIndex;
    }

    // Mergesort
    /*static void mergeSort(Staff[] staffs, int lowerB, int upperB){
        if (lowerB + 1 < upperB){
            int mid = (lowerB + upperB) / 2;  // divide - split array in half

            // conquer each part separately
            mergeSort(staffs, lowerB, mid);
            mergeSort(staffs, mid, upperB);

            merge(staffs, lowerB, mid, upperB);  // combine our answers
        }
    }

    static void merge(Staff[] staffs, int lowerB, int mid, int upperB){
        int left_side_index = lowerB;
        int right_side_index = mid;

        // temporary array which will contain sorted values
        // going from lower bound to upper bound
        Staff temp[] = new Staff[upperB - lowerB];
        int to_be_sorted_index = 0;

        // this loop is comparing to two smaller arrays
        while(left_side_index < mid && right_side_index < upperB){

            if (staffs[left_side_index].compareTo(staffs[right_side_index]) <= 0){
                temp[to_be_sorted_index] = staffs[left_side_index];
                left_side_index++;
                to_be_sorted_index++;
            }
            else {
                temp[to_be_sorted_index] = staffs[right_side_index];
                right_side_index++;
                to_be_sorted_index++;
            }
        }

        // one half of the array has already been allocated at this point
        // now we need to allocate the other array
        while(left_side_index < mid){
            temp[to_be_sorted_index] = staffs[left_side_index];
            left_side_index++;
            to_be_sorted_index++;
        }

        while(right_side_index < upperB){
            temp[to_be_sorted_index] = staffs[right_side_index];
            right_side_index++;
            to_be_sorted_index++;
        }

        // copy data from sorted sub array to staffs array
        left_side_index = lowerB;
        to_be_sorted_index = 0;
        while(to_be_sorted_index < temp.length){
            staffs[left_side_index] = temp[to_be_sorted_index];
            left_side_index++;
            to_be_sorted_index++;
        }
    }*/
}
