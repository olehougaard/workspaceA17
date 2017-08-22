package dk.via.sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SortingTest {
	private static void swap(long[] numbers, int i, int j) {
		long temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
	private static void shuffleRandomly(long[] numbers) {
		Random random = new Random();
		for(int i = 0; i < numbers.length - 1; i++) {
			int shuffleIndex = random.nextInt(numbers.length - i) + i;
			swap(numbers, i, shuffleIndex);
		}
	}
	
	private static long[] generateRandomArray(int n) {
		Random random = new Random();
		long[] numbers = new long[n];
		for(int i = 0; i < n; i++) {
			numbers[i] = random.nextLong();
		}
		return numbers;
	}
	
	private static boolean isSorted(long[] numbers) {
		for(int i = 0; i < numbers.length - 1; i++)  {
			if (numbers[i] > numbers[i + 1]) return false;
		}
		return true;
	}

	private static long timeBubbleSort(long[] numbers) {
		long startTime = System.currentTimeMillis();
		boolean sorted = false;
		while(!sorted) {
			sorted = true;
			for(int i = 0; i < numbers.length - 1; i++) {
				if (numbers[i] > numbers[i + 1]) {
					sorted = false;
					swap(numbers, i, i + 1);
				}
			}
		}
		return System.currentTimeMillis() - startTime;
	}
	
	private static long timeInsertionSort(long[] numbers) {
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < numbers.length; i++) {
			long insertionValue = numbers[i];
			int insertionPoint = 0;
			while(insertionPoint <= i && numbers[insertionPoint] < insertionValue) {
				insertionPoint++;
			}
			System.arraycopy(numbers, insertionPoint, numbers, insertionPoint + 1, i - insertionPoint);
			numbers[insertionPoint] = insertionValue;
		}
		return System.currentTimeMillis() - startTime;
	}
	
	private static long timeSelectionSort(long[] numbers) {
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < numbers.length - 1; i++) {
			int indexOfSmallest = i;
			for(int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] < numbers[indexOfSmallest]) {
					indexOfSmallest = j;
				}
			}
			swap(numbers, i, indexOfSmallest);
		}
		return System.currentTimeMillis() - startTime;
	}
	
	private static int[] partitionBentleyMcIlroy(long[] numbers, long pivot, int from, int to) {
		int low = from;
		int high = to;
		int lowEqual = low;
		int highEqual = high;
		while(low < high) {
			if (numbers[low] < pivot) low++;
			else if (numbers[low] == pivot) swap(numbers, lowEqual++, low++);
			else if (numbers[high - 1] > pivot) high--;
			else if (numbers[high - 1] == pivot) swap(numbers, --highEqual, --high);
			else /* numbers[low] > pivot && numbers[high - 1] < pivot */ swap(numbers, low++, --high);
		}
		while(lowEqual > from) swap(numbers, --lowEqual, --low);
		while(highEqual < to) swap(numbers, highEqual++, high++);
		return new int[]{low, high};
	}
	
	private static void quickSort(long[] numbers, int from, int to) {
		if (from >= to - 1) return;
		long pivot = numbers[from];
		int[] bounds = partitionBentleyMcIlroy(numbers, pivot, from, to);
		quickSort(numbers, from, bounds[0]);
		quickSort(numbers, bounds[1], to);
	}
	
	private static void quickSortSherwood(long[] numbers, int from, int to) {
		if (from >= to - 1) return;
		Random random = new Random();
		long pivot = numbers[from + random.nextInt(to - from)];
		int[] bounds = partitionBentleyMcIlroy(numbers, pivot, from, to);
		quickSortSherwood(numbers, from, bounds[0]);
		quickSortSherwood(numbers, bounds[1], to);
	}
	
	private static long timeQuickSort(long[] numbers) {
		long startTime = System.currentTimeMillis();
		quickSort(numbers, 0, numbers.length);
		return System.currentTimeMillis() - startTime;
	}
	
	private static long timeQuickSortSherwood(long[] numbers) {
		long startTime = System.currentTimeMillis();
		quickSortSherwood(numbers, 0, numbers.length);
		return System.currentTimeMillis() - startTime;
	}
	
	private static void mergeSort(long[] numbers) {
		if (numbers.length <= 1) return;
		long[] firstHalf = Arrays.copyOf(numbers, numbers.length / 2);
		long[] secondHalf = Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length);
		mergeSort(firstHalf);
		mergeSort(secondHalf);
		int firstIndex = 0, secondIndex = 0, numbersIndex = 0;
		while(numbersIndex < numbers.length) {
			if (firstIndex == firstHalf.length) numbers[numbersIndex++] = secondHalf[secondIndex++];
			else if (secondIndex == secondHalf.length) numbers[numbersIndex++] = firstHalf[firstIndex++];
			else if (firstHalf[firstIndex] < secondHalf[secondIndex]) numbers[numbersIndex++] = firstHalf[firstIndex++];
			else numbers[numbersIndex++] = secondHalf[secondIndex++];
		}
	}
	
	private static long timeMergeSort(long[] numbers) {
		long startTime = System.currentTimeMillis();
		mergeSort(numbers);
		return System.currentTimeMillis() - startTime;
	}
	
	private static long timeImFeelingLuckySort(long[] numbers) {
		long startTime = System.currentTimeMillis();
		while(!isSorted(numbers)) shuffleRandomly(numbers);
		return System.currentTimeMillis() - startTime;
	}
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("n = ");
		int n = keyboard.nextInt();
		keyboard.close();
		long[] test = generateRandomArray(n);
		
		long[] numbers = test.clone();
		System.out.print("Bubble sort\t\t\t\t");
		long t = timeBubbleSort(numbers);
		System.out.println(t);

		numbers = test.clone();
		System.out.print("Insertion sort\t\t\t\t");
		t = timeInsertionSort(numbers);
		System.out.println(t);

		numbers = test.clone();
		System.out.print("Selection sort\t\t\t\t");
		t = timeSelectionSort(numbers);
		System.out.println(t);

		numbers = test.clone();
		System.out.print("Quick sort\t\t\t\t");
		t = timeQuickSort(numbers);
		System.out.println(t);

		numbers = test.clone();
		System.out.print("Quick sort (Sherwood version)\t\t");
		t = timeQuickSortSherwood(numbers);
		System.out.println(t);

		numbers = test.clone();
		System.out.print("Merge sort\t\t\t\t");
		t = timeMergeSort(numbers);
		System.out.println(t);
		
		numbers = test.clone();
		System.out.print("\"I'm feeling lucky\" sort\t\t");
		t = timeImFeelingLuckySort(numbers);
		System.out.println(t);

		System.out.print("Bubble sort (sorted)\t\t\t");
		t = timeBubbleSort(numbers);
		System.out.println(t);
		
		System.out.print("Insertion sort (sorted)\t\t\t");
		t = timeInsertionSort(numbers);
		System.out.println(t);
		
		System.out.println();
		System.out.print("Selection sort (sorted)\t\t\t");
		t = timeSelectionSort(numbers);
		System.out.println(t);
		
		System.out.print("Quick sort (sorted)\t\t\t");
		t = timeQuickSort(numbers);
		System.out.println(t);
		
		System.out.print("Quick sort (Sherwood version, sorted)\t");
		t = timeQuickSortSherwood(numbers);
		System.out.println(t);

		System.out.print("Merge sort (sorted)\t\t\t");
		t = timeMergeSort(numbers);
		System.out.println(t);
}
}
