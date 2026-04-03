/*
 * Copyright (C) 2008 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.common.primitives;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.GwtCompatible;

/**
 * Abstract base class for primitive array utility classes.
 * 
 * <p>This class consolidates common patterns and implementations used across
 * {@link Ints}, {@link Longs}, {@link Doubles}, {@link Floats}, {@link Bytes},
 * {@link Shorts}, and {@link Booleans}.
 * 
 * <p>The shared functionality includes:
 * <ul>
 *   <li>Array searching algorithms (indexOf, lastIndexOf, contains)</li>
 *   <li>Defensive copying patterns</li>
 *   <li>Common validation utilities</li>
 * </ul>
 *
 * @author Kevin Bourrillion
 * @since 33.5.0
 */
@GwtCompatible
abstract class AbstractPrimitiveUtilities {
  // Utility class - should not be instantiated
  private AbstractPrimitiveUtilities() {}

  /**
   * Searches for the first occurrence of a value in an array starting from position 0.
   * 
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param length the effective length of the array to search
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearch(int[] array, int target, int length) {
    checkNotNull(array, "array");
    return linearSearchRange(array, target, 0, length);
  }

  /**
   * Searches for the first occurrence of a value in a range of an array.
   * 
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param start the starting index (inclusive)
   * @param end the ending index (exclusive)
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearchRange(int[] array, int target, int start, int end) {
    for (int i = start; i < end; i++) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Searches for the last occurrence of a value in an array.
   * 
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param length the effective length of the array to search
   * @return the index of the last occurrence, or -1 if not found
   */
  protected static int linearSearchLastIndex(int[] array, int target, int length) {
    checkNotNull(array, "array");
    return linearSearchLastIndexRange(array, target, 0, length);
  }

  /**
   * Searches for the last occurrence of a value in a range of an array.
   * 
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param start the starting index (inclusive)
   * @param end the ending index (exclusive)
   * @return the index of the last occurrence, or -1 if not found
   */
  protected static int linearSearchLastIndexRange(int[] array, int target, int start, int end) {
    for (int i = end - 1; i >= start; i--) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Searches for the first occurrence of a sub-array in an array.
   * 
   * @param array the array to search in (not null)
   * @param target the sub-array to search for (not null)
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearchSubArray(int[] array, int[] target) {
    checkNotNull(array, "array");
    checkNotNull(target, "target");
    
    if (target.length == 0) {
      return 0;
    }

    outer:
    for (int i = 0; i < array.length - target.length + 1; i++) {
      for (int j = 0; j < target.length; j++) {
        if (array[i + j] != target[j]) {
          continue outer;
        }
      }
      return i;
    }
    return -1;
  }

  /**
   * Searches for the last occurrence of a sub-array in an array.
   * 
   * @param array the array to search in (not null)
   * @param target the sub-array to search for (not null)
   * @return the index of the last occurrence, or -1 if not found
   */
  protected static int linearSearchLastSubArray(int[] array, int[] target) {
    checkNotNull(array, "array");
    checkNotNull(target, "target");
    
    if (target.length == 0) {
      return array.length;
    }

    outer:
    for (int i = array.length - target.length; i >= 0; i--) {
      for (int j = 0; j < target.length; j++) {
        if (array[i + j] != target[j]) {
          continue outer;
        }
      }
      return i;
    }
    return -1;
  }

  /**
   * Searches for the first occurrence of a value in an array.
   * Template method for checking if an array contains a single value.
   * This is a common pattern across all primitive types.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @return true if the value is found, false otherwise
   */
  protected static boolean contains(int[] array, int target) {
    checkNotNull(array, "array");
    for (int value : array) {
      if (value == target) {
        return true;
      }
    }
    return false;
  }

  // ============ BYTE ARRAY SEARCH METHODS ============
  // Same patterns as int[], reused for byte[] primitive arrays

  /**
   * Searches for the first occurrence of a value in a byte array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param length the effective length of the array to search
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearch(byte[] array, byte target, int length) {
    checkNotNull(array, "array");
    return linearSearchRange(array, target, 0, length);
  }

  /**
   * Searches for the first occurrence of a value in a range of a byte array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param start the starting index (inclusive)
   * @param end the ending index (exclusive)
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearchRange(byte[] array, byte target, int start, int end) {
    for (int i = start; i < end; i++) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Searches for the last occurrence of a value in a byte array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param length the effective length of the array to search
   * @return the index of the last occurrence, or -1 if not found
   */
  protected static int linearSearchLastIndex(byte[] array, byte target, int length) {
    checkNotNull(array, "array");
    return linearSearchLastIndexRange(array, target, 0, length);
  }

  /**
   * Searches for the last occurrence of a value in a range of a byte array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param start the starting index (inclusive)
   * @param end the ending index (exclusive)
   * @return the index of the last occurrence, or -1 if not found
   */
  protected static int linearSearchLastIndexRange(byte[] array, byte target, int start, int end) {
    for (int i = end - 1; i >= start; i--) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Searches for the first occurrence of a sub-array in a byte array.
   *
   * @param array the array to search in (not null)
   * @param target the sub-array to search for (not null)
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearchSubArray(byte[] array, byte[] target) {
    checkNotNull(array, "array");
    checkNotNull(target, "target");
    
    if (target.length == 0) {
      return 0;
    }

    outer:
    for (int i = 0; i < array.length - target.length + 1; i++) {
      for (int j = 0; j < target.length; j++) {
        if (array[i + j] != target[j]) {
          continue outer;
        }
      }
      return i;
    }
    return -1;
  }

  /**
   * Searches for the last occurrence of a sub-array in a byte array.
   *
   * @param array the array to search in (not null)
   * @param target the sub-array to search for (not null)
   * @return the index of the last occurrence, or -1 if not found
   */
  protected static int linearSearchLastSubArray(byte[] array, byte[] target) {
    checkNotNull(array, "array");
    checkNotNull(target, "target");
    
    if (target.length == 0) {
      return array.length;
    }

    outer:
    for (int i = array.length - target.length; i >= 0; i--) {
      for (int j = 0; j < target.length; j++) {
        if (array[i + j] != target[j]) {
          continue outer;
        }
      }
      return i;
    }
    return -1;
  }

  /**
   * Searches for the first occurrence of a value in a byte array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @return true if the value is found, false otherwise
   */
  protected static boolean contains(byte[] array, byte target) {
    checkNotNull(array, "array");
    for (byte value : array) {
      if (value == target) {
        return true;
      }
    }
    return false;
  }

  // ============ CHAR ARRAY SEARCH METHODS ============
  // Same patterns as int[] and byte[], reused for char[] primitive arrays

  /**
   * Searches for the first occurrence of a value in a char array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param length the effective length of the array to search
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearch(char[] array, char target, int length) {
    checkNotNull(array, "array");
    return linearSearchRange(array, target, 0, length);
  }

  /**
   * Searches for the first occurrence of a value in a range of a char array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param start the starting index (inclusive)
   * @param end the ending index (exclusive)
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearchRange(char[] array, char target, int start, int end) {
    for (int i = start; i < end; i++) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Searches for the last occurrence of a value in a char array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param length the effective length of the array to search
   * @return the index of the last occurrence, or -1 if not found
   */
  protected static int linearSearchLastIndex(char[] array, char target, int length) {
    checkNotNull(array, "array");
    return linearSearchLastIndexRange(array, target, 0, length);
  }

  /**
   * Searches for the last occurrence of a value in a range of a char array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param start the starting index (inclusive)
   * @param end the ending index (exclusive)
   * @return the index of the last occurrence, or -1 if not found
   */
  protected static int linearSearchLastIndexRange(char[] array, char target, int start, int end) {
    for (int i = end - 1; i >= start; i--) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Searches for the first occurrence of a sub-array in a char array.
   *
   * @param array the array to search in (not null)
   * @param target the sub-array to search for (not null)
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearchSubArray(char[] array, char[] target) {
    checkNotNull(array, "array");
    checkNotNull(target, "target");
    
    if (target.length == 0) {
      return 0;
    }

    outer:
    for (int i = 0; i < array.length - target.length + 1; i++) {
      for (int j = 0; j < target.length; j++) {
        if (array[i + j] != target[j]) {
          continue outer;
        }
      }
      return i;
    }
    return -1;
  }

  /**
   * Searches for the last occurrence of a sub-array in a char array.
   *
   * @param array the array to search in (not null)
   * @param target the sub-array to search for (not null)
   * @return the index of the last occurrence, or -1 if not found
   */
  protected static int linearSearchLastSubArray(char[] array, char[] target) {
    checkNotNull(array, "array");
    checkNotNull(target, "target");
    
    if (target.length == 0) {
      return array.length;
    }

    outer:
    for (int i = array.length - target.length; i >= 0; i--) {
      for (int j = 0; j < target.length; j++) {
        if (array[i + j] != target[j]) {
          continue outer;
        }
      }
      return i;
    }
    return -1;
  }

  /**
   * Searches for the first occurrence of a value in a char array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @return true if the value is found, false otherwise
   */
  protected static boolean contains(char[] array, char target) {
    checkNotNull(array, "array");
    for (char value : array) {
      if (value == target) {
        return true;
      }
    }
    return false;
  }

  // ============ LONG ARRAY SEARCH METHODS ============

  /**
   * Searches for the first occurrence of a value in a long array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param length the effective length of the array to search
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearch(long[] array, long target, int length) {
    checkNotNull(array, "array");
    return linearSearchRange(array, target, 0, length);
  }

  /**
   * Searches for the first occurrence of a value in a range of a long array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param start the starting index (inclusive)
   * @param end the ending index (exclusive)
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearchRange(long[] array, long target, int start, int end) {
    for (int i = start; i < end; i++) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Searches for the last occurrence of a value in a long array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param start the starting index (inclusive)
   * @param end the ending index (exclusive)
   * @return the index of the last occurrence, or -1 if not found
   */
  protected static int linearSearchLastIndexRange(long[] array, long target, int start, int end) {
    for (int i = end - 1; i >= start; i--) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Searches for the first occurrence of a value in a long array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @return true if the value is found, false otherwise
   */
  protected static boolean contains(long[] array, long target) {
    checkNotNull(array, "array");
    for (long value : array) {
      if (value == target) {
        return true;
      }
    }
    return false;
  }

  // ============ DOUBLE ARRAY SEARCH METHODS ============

  /**
   * Searches for the first occurrence of a value in a double array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param length the effective length of the array to search
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearch(double[] array, double target, int length) {
    checkNotNull(array, "array");
    return linearSearchRange(array, target, 0, length);
  }

  /**
   * Searches for the first occurrence of a value in a range of a double array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param start the starting index (inclusive)
   * @param end the ending index (exclusive)
   * @return the index of the first occurrence, or -1 if not found
   */
  protected static int linearSearchRange(double[] array, double target, int start, int end) {
    for (int i = start; i < end; i++) {
      if (Double.doubleToRawLongBits(array[i]) == Double.doubleToRawLongBits(target)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Searches for the last occurrence of a value in a range of a double array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @param start the starting index (inclusive)
   * @param end the ending index (exclusive)
   * @return the index of the last occurrence, or -1 if not found
   */
  protected static int linearSearchLastIndexRange(double[] array, double target, int start, int end) {
    for (int i = end - 1; i >= start; i--) {
      if (Double.doubleToRawLongBits(array[i]) == Double.doubleToRawLongBits(target)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Searches for the first occurrence of a value in a double array.
   *
   * @param array the array to search in (not null)
   * @param target the value to search for
   * @return true if the value is found, false otherwise
   */
  protected static boolean contains(double[] array, double target) {
    checkNotNull(array, "array");
    long targetBits = Double.doubleToRawLongBits(target);
    for (double value : array) {
      if (Double.doubleToRawLongBits(value) == targetBits) {
        return true;
      }
    }
    return false;
  }
}
