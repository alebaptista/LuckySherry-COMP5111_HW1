package tests;
/**
 * A class that contains several sorting routines, implemented as static
 * methods. Arrays are rearranged with smallest item first, using compareTo.
 * 
 * @author Mark Allen Weiss
 */
public final class Sort {
	/**
	 * Simple insertion sort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static void insertionSort(int[] a) {
		int j;

		/* 1 */for (int p = 1; p < a.length; p++) {
			/* 2 */int tmp = a[p];
			/* 3 */for (j = p; j > 0 && tmp < a[j - 1]; j--)
				/* 4 */a[j] = a[j - 1];
			/* 5 */a[j] = tmp;
		}
	}

	/**
	 * Shellsort, using Shell's (poor) increments.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static void shellsort(int[] a) {
		int j;

		/* 1 */for (int gap = a.length / 2; gap > 0; gap /= 2)
			/* 2 */for (int i = gap; i < a.length; i++) {
				/* 3 */int tmp = a[i];
				/* 4 */for (j = i; j >= gap && tmp < a[j - gap]; j -= gap)
					/* 5 */a[j] = a[j - gap];
				/* 6 */a[j] = tmp;
			}
	}

	public static boolean isSorted(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Standard heapsort.
	 * 
	 * @param a
	 *            an array of integers.
	 */
	public static void heapsort(int[] a) {
		/* 1 */for (int i = a.length / 2; i >= 0; i--)
			/* buildHeap */
			/* 2 */percDown(a, i, a.length);
		/* 3 */for (int i = a.length - 1; i > 0; i--) {
			/* 4 */swap(a, 0, i); /* deleteMax */
			/* 5 */percDown(a, 0, i);
		}
	}

	/**
	 * Internal method for heapsort.
	 * 
	 * @param i
	 *            the index of an item in the heap.
	 * @return the index of the left child.
	 */
	public static int leftChild(int i) {
		return 2 * i + 1;
	}

	/**
	 * Internal method for heapsort that is used in deleteMax and buildHeap.
	 * 
	 * @param a
	 *            an array of integers.
	 * @index i the position from which to percolate down.
	 * @int n the logical size of the binary heap.
	 */
	public static void percDown(int[] a, int i, int n) {
		int child;
		int tmp;

		/* 1 */for (tmp = a[i]; leftChild(i) < n; i = child) {
			/* 2 */child = leftChild(i);
			/* 3 */if (child != n - 1 && a[child] < a[child + 1])
				/* 4 */child++;
			/* 5 */if (tmp > a[child])
				/* 6 */a[i] = a[child];
			else
				/* 7 */break;
		}
		/* 8 */a[i] = tmp;
	}

	/**
	 * Mergesort algorithm.
	 * 
	 * @param a
	 *            an array of integers.
	 */
	public static void mergeSort(int[] a) {
		int[] tmpArray = new int[a.length];

		mergeSort(a, tmpArray, 0, a.length - 1);
	}

	/**
	 * Internal method that makes recursive calls.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param tmpArray
	 *            an array to place the merged result.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 */
	public static void mergeSort(int[] a, int[] tmpArray, int left, int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(a, tmpArray, left, center);
			mergeSort(a, tmpArray, center + 1, right);
			merge(a, tmpArray, left, center + 1, right);
		}
	}

	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param tmpArray
	 *            an array to place the merged result.
	 * @param leftPos
	 *            the left-most index of the subarray.
	 * @param rightPos
	 *            the index of the start of the second half.
	 * @param rightEnd
	 *            the right-most index of the subarray.
	 */
	public static void merge(int[] a, int[] tmpArray, int leftPos,
			int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		// Main loop
		while (leftPos <= leftEnd && rightPos <= rightEnd)
			if (a[leftPos] <= a[rightPos])
				tmpArray[tmpPos++] = a[leftPos++];
			else
				tmpArray[tmpPos++] = a[rightPos++];

		while (leftPos <= leftEnd)
			// Copy rest of first half
			tmpArray[tmpPos++] = a[leftPos++];

		while (rightPos <= rightEnd)
			// Copy rest of right half
			tmpArray[tmpPos++] = a[rightPos++];

		// Copy tmpArray back
		for (int i = 0; i < numElements - 1; i++, rightEnd--)
			a[rightEnd] = tmpArray[rightEnd];
	}

	/**
	 * Quicksort algorithm.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static void quicksort(int[] a) {
		quicksort(a, 0, a.length - 1);
	}

	private static final int CUTOFF = 10;

	/**
	 * Method to swap to elements in an array.
	 * 
	 * @param a
	 *            an array of objects.
	 * @param index1
	 *            the index of the first object.
	 * @param index2
	 *            the index of the second object.
	 */
	public static final void swap(int[] a, int index1, int index2) {
		int tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
	}

	/**
	 * Return median of left, center, and right. Order these and hide the pivot.
	 */
	public static int median3(int[] a, int left, int right) {
		int center = (left + right) / 2;
		if (a[center] < a[left]) {
			swap(a, left, center);
		}
		if (a[right] < a[left]) {
			swap(a, left, right);
		}
		if (a[right] < a[center]) {
			swap(a, center, right);
		}
		// Place pivot at position right - 1
		swap(a, center, right - 1);
		return a[right - 1];
	}

	/**
	 * Internal quicksort method that makes recursive calls. Uses
	 * median-of-three partitioning and a cutoff of 10.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 */
	public static void quicksort(int[] a, int left, int right) {
		/* 1 */if (left + CUTOFF <= right) {
			/* 2 */int pivot = median3(a, left, right);

			// Begin partitioning
			/* 3 */int i = left, j = right - 1;
			/* 4 */for (;;) {
				/* 5 */while (a[++i] < pivot) {
				}
				/* 6 */while (a[--j] > pivot) {
				}
				/* 7 */if (i < j)
					/* 8 */swap(a, i, j);
				else
					/* 9 */break;
			}

			/* 10 */swap(a, i, right - 1); // Restore pivot

			/* 11 */quicksort(a, left, i - 1); // Sort small elements
			/* 12 */
			quicksort(a, i + 1, right); // Sort large elements
		} else
			// Do an insertion sort on the subarray
			/* 13 */insertionSort(a, left, right);
	}

	/**
	 * Internal insertion sort routine for subarrays that is used by quicksort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @param left
	 *            the left-most index of the subarray.
	 * @param right
	 *            the right-most index of the subarray.
	 */
	public static void insertionSort(int[] a, int left, int right) {
		for (int p = left + 1; p <= right; p++) {
			int tmp = a[p];
			int j;

			for (j = p; j > left && tmp < a[j - 1]; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}
}


