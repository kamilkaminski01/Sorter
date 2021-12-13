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

    public Sorter(String file) throws IOException {
        File f = new File(file);
        Random r = new Random();
        int num = 0;

        if (!f.exists()) {
            PrintStream output = new PrintStream("test.txt");

            System.out.println("Ten plik nie istnieje, utowrzylem plik test.txt");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj ilosc liczb do wygenerowania: ");
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
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
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
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
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
            arr[min] = arr[i];
            arr[i] = temp;
        }
        long stop = System.nanoTime();
        long time = stop - start;
        czas = time;
    }

    public void saveAs(String file) throws FileNotFoundException {
        File f = new File(file);
        PrintStream output = new PrintStream(f);

        while (numOfLines != 0) {
            for (int number : numbers) {
                output.println(number);
                numOfLines--;
            }
        }
        System.out.println("Dane zapisane do pliku");
    }

    public void setDebug(int n) {

        if (n == 1) {
            System.out.println("\nNazwa algorytmu: BubbleSort");
            System.out.println("Ilosc sortowanych liczb: " + numOfLines);
            System.out.println("Czas dzialania: " + czas + " nanosekund");
            System.out.println("Liczba porownan: " + porownania);
            System.out.println("Liczba przypisan: " + przypisania);
        } else if (n == 2) {
            System.out.println("\nNazwa algorytmu: BubbleSortG");
            System.out.println("Ilosc sortowanych liczb: " + numOfLines);
            System.out.println("Czas dzialania: " + czas + " nanosekund");
            System.out.println("Liczba porownan: " + porownania);
            System.out.println("Liczba przypisan: " + przypisania);
        } else if (n == 3) {
            System.out.println("\nNazwa algorytmu: InsertionSort");
            System.out.println("Ilosc sortowanych liczb: " + numOfLines);
            System.out.println("Czas dzialania: " + czas + " nanosekund");
            System.out.println("Liczba porownan: " + porownania);
            System.out.println("Liczba przypisan: " + przypisania);
        } else if (n == 4) {
            System.out.println("\nNazwa algorytmu: SelectionSort");
            System.out.println("\nIlosc sortowanych liczb: " + numOfLines);
            System.out.println("Czas dzialania: " + czas + " nanosekund");
            System.out.println("Liczba porownan: " + porownania);
            System.out.println("Liczba przypisan: " + przypisania);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj nazwe pliku: ");
        String fileName = scanner.nextLine();
        Sorter sorter = new Sorter(fileName);

        System.out.println("\n1 - Bubble Sort");
        System.out.println("2 - Bubble Sort ze straznikiem");
        System.out.println("3 - Insert sort");
        System.out.println("4 - Select Sort");
        System.out.print("Wybierz metode sortowania: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            sorter.doBubbleSort(sorter.numbers);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs(fileName);
        } else if (choice == 2) {
            sorter.doBubbleSortG(sorter.numbers);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs(fileName);
        } else if (choice == 3) {
            sorter.doInsertionSort(sorter.numbers);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs(fileName);
        } else if (choice == 4) {
            sorter.doSelectionSort(sorter.numbers);
            sorter.setDebug(choice);
            System.out.println("\nDane posortowane");
            sorter.saveAs(fileName);
        } else {
            System.out.println("Podano zla wartosc");
        }

    }
}
