package menwic.ayd2.ex1;

import java.util.Arrays;
import java.util.Objects;

public final class LeftRotation {

    private LeftRotation() {
    }

    /**
     * Returns a new array with the elements rotated left by d steps.
     */
    public static int[] rotateLeft(int[] numbers, int d) {
        if (numbers == null) {
            throw new IllegalArgumentException("numbers cannot be null");
        }
        int n = numbers.length;
        if (n <= 1) {
            return Arrays.copyOf(numbers, n);
        }

        int steps = normalizeSteps(d, n);
        if (steps == 0) {
            return Arrays.copyOf(numbers, n);
        }

        int[] rotated = Arrays.copyOf(numbers, n);
        rotateLeftInPlace(rotated, steps);
        return rotated;
    }

    /**
     * Rotates the given array left by d steps, mutating it in place.
     * Returns the same array reference for convenience.
     */
    public static int[] rotateLeftInPlace(int[] numbers, int d) {
        if (numbers == null) {
            throw new IllegalArgumentException("numbers cannot be null");
        }
        int n = numbers.length;
        if (n <= 1) {
            return numbers;
        }

        int steps = normalizeSteps(d, n);
        if (steps == 0) {
            return numbers;
        }

        reverse(numbers, 0, steps - 1);
        reverse(numbers, steps, n - 1);
        reverse(numbers, 0, n - 1);

        return numbers;
    }

    /**
     * Same as rotateLeftInPlace, but prints key steps for tracing.
     */
    public static int[] rotateLeftInPlaceWithLog(int[] numbers, int d) {
        if (numbers == null) {
            throw new IllegalArgumentException("numbers cannot be null");
        }
        int n = numbers.length;
        System.out.println("Input: " + Arrays.toString(numbers));
        if (n <= 1) {
            System.out.println("No rotation needed (size <= 1)");
            return numbers;
        }

        int steps = normalizeSteps(d, n);
        System.out.println("Normalized steps: " + steps);
        if (steps == 0) {
            System.out.println("No rotation needed (steps == 0)");
            return numbers;
        }

        reverse(numbers, 0, steps - 1);
        System.out.println("After left segment reverse: " + Arrays.toString(numbers));

        reverse(numbers, steps, n - 1);
        System.out.println("After right segment reverse: " + Arrays.toString(numbers));

        reverse(numbers, 0, n - 1);
        System.out.println("After full reverse: " + Arrays.toString(numbers));

        return numbers;
    }

    /**
     * Parses a comma-separated string into an int array.
     * Example: "1, 2, 3" -> [1,2,3]
     */
    public static int[] parsenumbers(String commaSeparated) {
        if (commaSeparated == null) {
            throw new IllegalArgumentException("commaSeparated cannot be null");
        }
        String trimmed = commaSeparated.trim();
        if (trimmed.isEmpty()) {
            return new int[0];
        }

        String[] tokens = trimmed.split(",");
        int[] result = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i].trim();
            if (token.isEmpty()) {
                throw new IllegalArgumentException("Invalid number at position " + i);
            }
            try {
                result[i] = Integer.parseInt(token);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Invalid number at position " + i + ": '" + token + "'", ex);
            }
        }
        return result;
    }

    static int normalizeSteps(int d, int n) {
        if (n <= 0) {
            return 0;
        }
        int steps = d % n;
        if (steps < 0) {
            steps += n;
        }
        return steps;
    }

    static void reverse(int[] arr, int left, int right) {
        Objects.requireNonNull(arr, "arr");
        int i = left;
        int j = right;
        while (i < j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }
}
