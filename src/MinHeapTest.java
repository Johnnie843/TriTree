import java.util.Random;
public class MinHeapTest {

	public static void testWithFixedValues() {
		Integer[] inputArray = new Integer[]{184, 2};
		MinHeap<Integer> myMinHeap = new MinHeap<Integer>(inputArray, inputArray.length, 50);

		myMinHeap.printBreadthFirst();
		System.out.println();
		myMinHeap.printDepthFirst();
	}
	public static void testWithRandomValues() {
		System.err.println("--------------------");
		System.err.println("| Starting New Test|");
		System.err.println("--------------------");
		Integer[] inputArray = new Integer[randomNumbersForTesting(0, 20)];
		System.err.println("---------------");
		System.err.println("| Input Array |");
		System.err.println("---------------");
		for (int i = 0; i < inputArray.length; i++) {

			inputArray[i] = randomNumbersForTesting(0, 250);
			System.out.print(inputArray[i] + " ");
		}
		System.out.println();
		MinHeap<Integer> myMinHeap = new MinHeap<Integer>(inputArray, inputArray.length, randomNumbersForTesting(inputArray.length, 50));
		System.err.println("----------------------");
		System.err.println("| MinHeap 3-Ary Tree |");
		System.err.println("----------------------");
		myMinHeap.printBreadthFirst();
		System.err.println("------------------------------");
		System.err.println("| MinHeap Pre-Order Traverse |");
		System.err.println("------------------------------");
		myMinHeap.printDepthFirst();
		System.out.println();
		System.err.println("--------------");
		System.err.println("| End of Test|");
		System.err.println("--------------");

	}

	public static int randomNumbersForTesting(int min, int max) {
		Random randomNum = new Random();
		return randomNum.nextInt((max - min) + 1) + min;
	}

}
