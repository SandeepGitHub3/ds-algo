# Two Pointers

## Table of Contents
1. [Is Palindrome](#1-is-palindrome)
2. [Uncompress](#2-uncompress)
3. [Compress](#3-compress)
4. [Five Sort](#4-five-sort)
5. [Is Subsequence](#5-is-subsequence)

## 1. Is Palindrome
```
public static boolean isPalindrome(String s) {
    int front = 0;
    int back =s.length() -1 ;

    while(front<back){
      if(s.charAt(front) != s.charAt(back)) return false;
      front = front+1;
      back = back -1;
    }
    
    return true;
  }
```

- n = length of string
- Time: O(n)
- Space: O(1)

### 2. Uncompress

Write a method, uncompress, that takes in a string as an argument. The input string will be formatted into multiple groups according to the following pattern

```
Source.uncompress("2c3a1t"); // -> "ccaaat"
```

```
 public static String uncompress(String s) {
    String result = "";
    String numbers = "0123456789";
    int i = 0;
    int j = 0;
    while (j < s.length()) {
      if (numbers.contains(String.valueOf(s.charAt(j)))) {
        j += 1;
      } else {
        int num = Integer.parseInt(s.substring(i, j));
        for (int count = 0; count < num; count += 1) {
          result += s.charAt(j);
        }
        j += 1;
        i = j;
      }
    }
    return result;
  }
```

- n = number of groups
- m = max num found in any group
- Time: O(n*m)
- Space: O(n*m)

### 3. Compress

take in a string and return a compressed version of the string Single character occurrences should not be changed.

```
Source.compress("ccaaatsss"); // -> "2c3at3s"

'aaa' compresses to '3a'
'cc' compresses to '2c'
't' should remain as 't'
```

```
public static String compress(String s) {
    int i = 0;
    int j = 0;
    int count = 0;
    StringBuilder compressedString = new StringBuilder();
    
    while (j<s.length()){
      if(s.charAt(i) == s.charAt(j)) {
        count = count + 1;
        j++;
      } else{
        if(count != 1)
          compressedString.append(count);
        compressedString.append(s.charAt(i));
        count = 0;
        i = j;
      }
    }

    /* NOTE - Notice this check,this will take care of the last sequence
    Anoter way to handle this is by adding a dummy character at the end of the String like !*/
    if(count != 1)
          compressedString.append(count);
    compressedString.append(s.charAt(i));
    
    return compressedString.toString();
  }
```
- n = length of string
- Time: O(n)
- Space: O(n)


### 4. Five Sort
rearrange elements of the ArrayList such that all 5s appear at the end.
Important: Your function needs to mutate the original array in-place
```
List<Integer> array = new ArrayList<>(List.of(12, 5, 1, 5, 12, 7));
Source.fiveSort(array);
// -> [12, 7, 1, 12, 5, 5] 
```

```
public static List<Integer> fiveSort(List<Integer> array) {
    int i = 0;
    int j = array.size() - 1;
    while (i < j) {
      if (array.get(j) == 5) {
        j -= 1;
      } else if (array.get(i) != 5) {
        i += 1;
      } else {
        Collections.swap(array, i, j);
      }
    }
    return array;
  }
```
- n = length of array
- Time: O(n)
- Space: O(1)

### 5. Is Subsequence

Return a boolean indicating whether or not string1 is a subsequence of string2.

```
isSubsequence("bde", "abcdef"); // -> true
```

```
public static boolean isSubsequence(String string1, String string2) {
    int s1 = 0;
    int s2 = 0;
    while(s1 < string1.length() && s2 <string2.length()){
      if(string1.charAt(s1) == string2.charAt(s2)){
        s1++;
        s2++;
      }else{
        s2++;
      }
    }

    if(s1 == string1.length()) return true;
    return false;
  }
```

- n = length of string1
- m = length of string2
- Time: O(m)
- Space: O(1)
