public class Main {
    // Returns the index of the target, if it doesn't exist returns -1
    static int binarySearch(int[] arr, int target){
        sortArr(arr);
        int low = 0, high = arr.length - 1;

        while (low <= high){
            int mid = (low + high) / 2;
            if (arr[mid] == target) return mid;

            if (arr[mid] < target){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // Sort an array with selection sort algorithm
    static void sortArr(int[] arr){
        for (int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }

            int t = arr[minIndex]; // temporary file to store the value of index minIndex
            arr[minIndex] = arr[i];
            arr[i] = t;
        }
    }
}
