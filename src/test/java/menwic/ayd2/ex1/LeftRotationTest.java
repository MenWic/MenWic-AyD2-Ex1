package menwic.ayd2.ex1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeftRotationTest {

    @Test
    void rotatesLeft_basicExample_likeClassDemo() {
        int[] numbers = {1, 2, 3, 4, 5};

        int[] rotated = LeftRotation.rotateLeft(numbers, 2);

        assertArrayEquals(new int[]{3, 4, 5, 1, 2}, rotated);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, numbers); // original unchanged
    }

    @Test
    void rotatesLeft_inPlace_mutatesSameArray() {
        int[] numbers = {1, 2, 3, 4, 5};

        int[] returned = LeftRotation.rotateLeftInPlace(numbers, 2);

        assertSame(numbers, returned);
        assertArrayEquals(new int[]{3, 4, 5, 1, 2}, numbers);
    }

    @Test
    void rotatesLeft_zeroSteps_noChanges() {
        int[] numbers = {10, 20, 30};

        int[] rotated = LeftRotation.rotateLeft(numbers, 0);

        assertArrayEquals(new int[]{10, 20, 30}, rotated);
    }

    @Test
    void rotatesLeft_stepsEqualToLength_noChanges() {
        int[] numbers = {10, 20, 30};

        int[] rotated = LeftRotation.rotateLeft(numbers, 3);

        assertArrayEquals(new int[]{10, 20, 30}, rotated);
    }

    @Test
    void rotatesLeft_stepsGreaterThanLength_usesModulo() {
        int[] numbers = {10, 20, 30, 40, 50};

        int[] rotated = LeftRotation.rotateLeft(numbers, 7); // 7 % 5 = 2

        assertArrayEquals(new int[]{30, 40, 50, 10, 20}, rotated);
    }

    @Test
    void rotatesLeft_negativeSteps_rotatesToRightEquivalent() {
        int[] numbers = {1, 2, 3, 4, 5};

        int[] rotated = LeftRotation.rotateLeft(numbers, -1); // left -1 == right 1

        assertArrayEquals(new int[]{5, 1, 2, 3, 4}, rotated);
    }

    @Test
    void rotatesLeft_singleElement_noChanges() {
        int[] numbers = {467};

        int[] rotated = LeftRotation.rotateLeft(numbers, 2);

        assertArrayEquals(new int[]{467}, rotated);
    }

    @Test
    void rotatesLeft_emptyArray_noChanges() {
        int[] numbers = {};

        int[] rotated = LeftRotation.rotateLeft(numbers, 5);

        assertArrayEquals(new int[]{}, rotated);
    }

    @Test
    void rotatesLeft_nullArray_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> LeftRotation.rotateLeft(null, 2));
    }

    @Test
    void rotatesLeft_keepsDuplicatesCorrectly() {
        int[] numbers = {7, 7, 1, 7};

        int[] rotated = LeftRotation.rotateLeft(numbers, 1);

        assertArrayEquals(new int[]{7, 1, 7, 7}, rotated);
    }

    @Test
    void rotatesLeft_withNegativeNumbers() {
        int[] numbers = {-10, -20, 0, 30};

        int[] rotated = LeftRotation.rotateLeft(numbers, 3);

        assertArrayEquals(new int[]{30, -10, -20, 0}, rotated);
    }

    @Test
    void parsenumbers_basic() {
        int[] parsed = LeftRotation.parsenumbers("1,2,3");

        assertArrayEquals(new int[]{1, 2, 3}, parsed);
    }

    @Test
    void parsenumbers_trimsSpaces() {
        int[] parsed = LeftRotation.parsenumbers(" 10,  20 ,30 ");

        assertArrayEquals(new int[]{10, 20, 30}, parsed);
    }

    @Test
    void parsenumbers_emptyString_returnsEmpty() {
        int[] parsed = LeftRotation.parsenumbers("   ");

        assertArrayEquals(new int[]{}, parsed);
    }

    @Test
    void parsenumbers_invalidToken_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> LeftRotation.parsenumbers("1, two, 3"));
    }

    @Test
    void parsenumbers_null_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> LeftRotation.parsenumbers(null));
    }
}
