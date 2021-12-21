import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sorter {

    int[] numbers;
    int numOfLines;
    int porownania;
    int przypisania;
    long czas;

    // Konstruktor plikow
    public Sorter(String file) throws IOException {
        File f = new File(file);
        Random r = new Random();
        int num = 0;

        if (!f.exists()) {
            PrintStream output = new PrintStream("test.txt");

            System.out.println("Ten plik nie istnieje, utworzylem pusty plik test.txt");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj ilosc liczb do wygenerowania do utworzonego pliku: ");
            int amount = scanner.nextInt();

            for (int i = 0; i < amount; i++) {
                int x = r.nextInt(99);
                output.println(x);
            }

            f = new File("test.txt");
            scanner = new Scanner(f);
            int temp1 = 0;
            this.numOfLines = temp1;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                temp1++;
                numOfLines++;
            }
            scanner.close();
            this.numbers = new int[temp1];
            scanner = new Scanner(f);
            while (scanner.hasNextInt()) {
                numbers[num++] = scanner.nextInt();
            }

            System.out.println("\nZapisalem do pliku losowe liczby");
            System.out.println("Ilosc liczb: " + temp1);
//            System.out.println("Liczby w pliku:" + Arrays.toString(numbers));
        } else {
            System.out.println("Plik istnieje");

            Scanner scanner = new Scanner(f);
            scanner = new Scanner(f);
            int temp2 = 0;
            this.numOfLines = temp2;

            while (scanner.hasNextLine()) {
                scanner.nextLine();
                temp2++;
                numOfLines++;
            }
            scanner.close();
            this.numbers = new int[temp2];
            scanner = new Scanner(f);
            while (scanner.hasNextInt()) {
                numbers[num++] = scanner.nextInt();
            }

            System.out.println("Ilosc liczb: " + temp2);
//            System.out.println("Liczby z pliku:" + Arrays.toString(numbers));
        }

    }

    // Zapis do pliku
    public void saveAs() throws FileNotFoundException {
        File f = new File("test.txt");
        PrintStream output = new PrintStream(f);

        while (numOfLines != 0) {
            for (int number : numbers) {
                output.println(number);
                numOfLines--;
            }
        }
        System.out.println("Dane zapisane do pliku test.txt");
    }

    // Diagnostyka wybranego sortowania
    public void setDebug(int n) {
        if (n == 1) {
            System.out.println("\nNazwa algorytmu: BubbleSort");
            setDebugInfo();
        } else if (n == 2) {
            System.out.println("\nNazwa algorytmu: BubbleSortG");
            setDebugInfo();
        } else if (n == 3) {
            System.out.println("\nNazwa algorytmu: InsertionSort");
            setDebugInfo();
        } else if (n == 4) {
            System.out.println("\nNazwa algorytmu: SelectionSort");
            setDebugInfo();
        }
        else if (n == 5){
            System.out.println("\nNazwa algorytmu: QuickSort");
            setDebugInfo();
        }
        else if (n == 6){
            System.out.println("\nNazwa algorytmu: MergeSort");
            setDebugInfo();
        }
        else if (n == 7){
            System.out.println("\nNazwa algorytmu: CountingSort");
            System.out.println("Ilosc sortowanych liczb: " + numOfLines);
            System.out.println("Czas dzialania: " + czas + " nanosekund");
        }
        else if (n == 8){
            System.out.println("\nNazwa algorytmu: JavaSort");
            System.out.println("Ilosc sortowanych liczb: " + numOfLines);
            System.out.println("Czas dzialania: " + czas + " nanosekund");
        }
    }

    public void setDebugInfo(){
        System.out.println("Ilosc sortowanych liczb: " + numOfLines);
        System.out.println("Czas dzialania: " + czas + " nanosekund");
        System.out.println("Liczba porownan: " + porownania);
        System.out.println("Liczba przypisan: " + przypisania);
    }

    // Sortowania
    //-----------------------------------------------------------------

    public void doBubbleSort(int arr[]) {
        przypisania = 0;
        porownania = 0;
        int n = arr.length;

        long start = System.nanoTime();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                porownania++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    przypisania++;
                    arr[j] = arr[j + 1];
                    przypisania++;
                    arr[j + 1] = temp;
                    przypisania++;
                }
            }
        }
        long stop = System.nanoTime();
        long time = stop - start;
        czas = time;
    }

    public void doBubbleSortG(int arr[]) {
        przypisania = 0;
        porownania = 0;

        int n = arr.length;
        int temp;
        boolean swapped;

        long start = System.nanoTime();
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                porownania++;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    przypisania++;
                    arr[j] = arr[j + 1];
                    przypisania++;
                    arr[j + 1] = temp;
                    przypisania++;
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
        }
        long stop = System.nanoTime();
        long time = stop - start;
        czas = time;
    }

    public void doInsertionSort(int arr[]) {
        przypisania = 0;
        porownania = 0;
        int n = arr.length;

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int p = arr[i];
            przypisania++;
            int j = i - 1;
            przypisania++;
            while (j >= 0 && arr[j] > p) {
                porownania++;
                arr[j + 1] = arr[j];
                przypisania++;
                j -= 1;
                przypisania++;
            }
            arr[j + 1] = p;
            przypisania++;
        }
        long stop = System.nanoTime();
        long time = stop - start;
        czas = time;
    }

    public void doSelectionSort(int arr[]) {
        przypisania = 0;
        porownania = 0;
        int n = arr.length;

        long start = System.nanoTime();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            przypisania++;
            for (int j = i + 1; j < n; j++) {
                porownania++;
                if (arr[j] < arr[min]) {
                    min = j;
                    przypisania++;
                }
            }
            int temp = arr[min];
            przypisania++;
            arr[min] = arr[i];
            przypisania++;
            arr[i] = temp;
            przypisania++;
        }
        long stop = System.nanoTime();
        long time = stop - start;
        czas = time;
    }

    public void doQuickSort(int arr[], int low, int high){
        int i = low;
        int j = high;

        int pivot = arr[low + (high - low) / 2];
        int exchange;

        long start = System.nanoTime();
        while (i <= j) {
            porownania++;
            while (arr[i] < pivot) {
                porownania++;
                i++;
            }
            while (arr[j] > pivot) {
                j--;
                porownania++;
            }
            porownania++;
            if (i <= j) {
                exchange = arr[i];
                przypisania++;
                arr[i] = arr[j];
                przypisania++;
                arr[j] = exchange;
                przypisania++;
                i++;
                j--;
            }
        }
        if (low < j)
            doQuickSort(arr, low, j);
        if (i < high)
            doQuickSort(arr, i, high);
        long stop = System.nanoTime();
        long time = stop - start;
        czas = time;
    }

    public void doMergeSort(int arr[], int l, int r){
        long start = System.nanoTime();
        if (l < r){
            int m = l + (r-l)/2;

            doMergeSort(arr, l, m);
            doMergeSort(arr, m + 1, r);
            merge(arr, l, m, r);

        }
        long stop = System.nanoTime();
        long time = stop - start;
        czas = time;
    }

    public void merge(int arr[], int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i){
            L[i] = arr[l + i];
            przypisania++;
        }
        for (int j = 0; j < n2; ++j){
            R[j] = arr[m + 1 + j];
            przypisania++;
        }

        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2){
            porownania++;
            if (L[i] <= R[j]){
                arr[k] = L[i];
                przypisania++;
                i++;
            }
            else {
                arr[k] = R[j];
                przypisania++;
                j++;
            }
            k++;
        }

        while (i < n1){
            przypisania++;
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2){
            przypisania++;
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public void doCountingSort(int arr[]){
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];

        long start = System.nanoTime();
        for (int i = 0; i < arr.length; i++){
            count[arr[i] - min]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        for (int i = 0; i < arr.length; i++){
            arr[i] = output[i];
        }
        long stop = System.nanoTime();
        long time = stop - start;
        czas = time;
    }

    public void doJavaSort(int arr[]){
        long start = System.nanoTime();
        Arrays.sort(arr);
        long stop = System.nanoTime();
        long time = stop - start;
        czas = time;
    }

    //-----------------------------------------------------------------

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj nazwe pliku: ");
        String fileName = scanner.nextLine();
        Sorter sorter = new Sorter(fileName);

        System.out.println("\n1 - Bubble Sort");
        System.out.println("2 - Bubble Sort ze straznikiem");
        System.out.println("3 - Insert sort");
        System.out.println("4 - Select Sort");
        System.out.println("5 - Quick Sort");
        System.out.println("6 - Merge Sort");
        System.out.println("7 - Counting Sort");
        System.out.println("8 - Java Sort");
        System.out.print("Wybierz metode sortowania: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            sorter.doBubbleSort(sorter.numbers);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs();
        } else if (choice == 2) {
            sorter.doBubbleSortG(sorter.numbers);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs();
        } else if (choice == 3) {
            sorter.doInsertionSort(sorter.numbers);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs();
        } else if (choice == 4) {
            sorter.doSelectionSort(sorter.numbers);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs();
        } else if (choice == 5){
            sorter.przypisania = 0;
            sorter.porownania = 0;
            sorter.doQuickSort(sorter.numbers, 0, sorter.numbers.length - 1);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs();
        } else if (choice == 6){
            sorter.przypisania = 0;
            sorter.porownania = 0;
            sorter.doMergeSort(sorter.numbers, 0, sorter.numbers.length - 1);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs();
        } else if (choice == 7){
            sorter.doCountingSort(sorter.numbers);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs();
        } else if (choice == 8){
            sorter.doJavaSort(sorter.numbers);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs();
        } else {
            System.out.println("Podano zla wartosc");
        }

    }
}
