
public class MinHeap<E extends Comparable<? super E>> {
	private E[] Heap; // Pointer to the heap array
	private int size; // Maximum size of the heap
	private int n; // Number of things in heap

	/** Constructor supporting preloading of heap contents */
	public MinHeap(E[] h, int num, int max) {
		Heap = h;
		n = num;
		size = max;
		buildheap();
	}

	/** @return Current size of the heap */
	public int heapsize() {
		return n;
	}
	/** @return True if pos a leaf position, false otherwise */
	public boolean isLeaf(int pos) {
		return (pos >= n / 3) && (pos < n);
	}
	/** @return Position for left child of pos */
	public int leftchild(int pos) {
		assert pos < n / 3 : "Position has no left child";
		return 3 * pos + 1;
	}
	public int middleChild(int pos) {
		assert pos < (n - 1) / 3 : "Position has no right child";
		return 3 * pos + 2;
	}
	/** @return Position for right child of pos */
	public int rightchild(int pos) {
		assert pos < (n - 1) / 3 : "Position has no right child";
		return 3 * pos + 3;
	}
	// Modified by John-Tyler Cooper to accommodate 3-ary tree
	/** @return Position for parent */
	public int parent(int pos) {
		assert pos > 0 : "Position has no parent";
		return (pos - 1) / 3;
	}
	/** Insert val into heap */
	public void insert(E val) {
		assert n < size : "Heap is full";
		int curr = n++;
		Heap[curr] = val; // Start at end of heap
		// Now sift up until curr's parent's key > curr's key
		while ((curr != 0) && (Heap[curr].compareTo(Heap[parent(curr)]) > 0)) {
			DSutil.swap(Heap, curr, parent(curr));
			curr = parent(curr);
		}
	}
	/** Heapify contents of Heap */
	public void buildheap() {
		for (int i = n / 3 - 1; i >= 0; i--)
			siftdown(i);
	}

	/** Put element in its correct place */
	private void siftdown(int pos) {
		assert (pos >= 0) && (pos < n) : "Illegal heap position";
		while (!isLeaf(pos)) {
			int lowestValue = leftchild(pos);
			int j = leftchild(pos);
			if (j + 1 < n) {
				if ((j < (n - 1)) && (Heap[lowestValue].compareTo(Heap[j + 1]) > 0)) {
					lowestValue = j + 1; // lowest value is now the middle index
				}
			}
			if (j + 2 < n) {
				if ((j < (n - 1)) && (Heap[lowestValue].compareTo(Heap[j + 2]) > 0)) {
					lowestValue = j + 2; // lowest value is the right child
				}
			}
			if (Heap[pos].compareTo(Heap[lowestValue]) <= 0)
				return;
			DSutil.swap(Heap, pos, lowestValue);
			pos = lowestValue; // Move down
		}
	}

	/** Remove and return maximum value */
	public E removemin() {
		assert n > 0 : "Removing from empty heap";
		DSutil.swap(Heap, 0, --n); // Swap maximum with last value
		if (n != 0) // Not on last element
			siftdown(0); // Put new heap root val in correct place
		return Heap[n];
	}
	// Created by John-Tyler Cooper
	// Recursive call to return height of the tree
	public int height() {
		return height(0);
	}
	private int height(int root) {
		if (root > n - 1) {
			return 0;
		}
		if (isLeaf(root)) {
			return 1;
		} else {
			return 1 + height(leftchild(root));
		}
	}

	/** Remove and return element at specified position */
	public E remove(int pos) {
		assert (pos >= 0) && (pos < n) : "Illegal heap position";
		if (pos == (n - 1))
			n--; // Last element, no work to be done
		else {
			DSutil.swap(Heap, pos, --n); // Swap with last value
			// If we just swapped in a big value, push it up
			while ((pos > 0) && (Heap[pos].compareTo(Heap[parent(pos)]) > 0)) {
				DSutil.swap(Heap, pos, parent(pos));
				pos = parent(pos);
			}
			if (n != 0)
				siftdown(pos); // If it is little, push down
		}
		return Heap[n];
	}

	public void printBreadthFirst() {
		// print first value
		if (n >= 1) {
			System.out.println("[" + Heap[0] + "]");
		}
		int arrayLocation = 1;
		// loop through tree
		int i = 1;
		while (i < height()) {
			// loop through row and print values
			int rowCount = (int) Math.pow(3, i);
			int j = 0;
			while (j < rowCount) {
				if (arrayLocation < n && Heap[arrayLocation] != null) {
					System.out.print("[" + Heap[arrayLocation] + "]");
				} else {
					System.out.print("[--]");

				}
				arrayLocation++;
				j++;

			}
			i++;
			System.out.println();
		}

	}
	// Recursive call to print tree in Pre-Order form.
	public void printDepthFirst() {
		printDepthFirst(0);
	}
	private void printDepthFirst(int root) {
		// for last element
		if (root > n - 1) {
			return;
		}
		if (Heap[root] != null && root < n) {
			System.out.print(Heap[root] + " ");
			printDepthFirst(leftchild(root));
			printDepthFirst(middleChild(root));
			printDepthFirst(rightchild(root));
		}
	}
}
