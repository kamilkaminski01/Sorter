import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sorter {

    int[] numbers;

    public Sorter(String file) throws IOException {
        File f = new File(file);
        Random r = new Random();

        if (!f.exists()) {
            f.createNewFile();
            PrintStream output = new PrintStream("test.txt");

            for (int i = 0; i < 10; i++) {
                int x = r.nextInt(99);
                output.println(x);
            }

            System.out.println("Utowrzylem plik");
        } else {
            System.out.println("Plik istnieje");

            Scanner scanner = new Scanner(f);
            int numOfLines = 0;
            int num = 0;

            while (scanner.hasNextLine()) {
                scanner.nextLine();
                numOfLines++;
            }
            scanner.close();
            this.numbers = new int[numOfLines];
            scanner = new Scanner(f);
            while (scanner.hasNextInt()) {
                numbers[num++] = scanner.nextInt();
            }

            doSelectionSort(numbers);
            PrintStream output = new PrintStream(f);
            while (numOfLines != 0){
                for (int number: numbers){
                    output.println(number);
                    numOfLines--;
                }
            }
            System.out.println(Arrays.toString(numbers));
        }

    }

    public void doBubbleSort(int arr[]){
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public void doInsertionSort(int arr[]){
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int p = arr[i];
            int j = i-1;

            while(j >= 0 && arr[j] > p){
                arr[j+1] = arr[j];
                j -= 1;
            }
            arr[j+1] = p;
        }
    }

    public void doSelectionSort(int arr[]){
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i+1; j < n ; j++) {
                if (arr[j] < arr[min]){
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) throws IOException {
        Sorter sorter = new Sorter("test.txt");

    }
}
