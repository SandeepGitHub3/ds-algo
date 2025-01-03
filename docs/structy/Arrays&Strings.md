## Intro

Important techniques
- Two Pointer
- Hash Map
- Hash Set

## Problems

### 1. Anagrams
Return a boolean indicating whether or not the strings are anagrams. Anagrams are strings that contain the same characters, but in any order.
 - HashMap

```
public static HashMap<Character, Integer> charCount(String s) {
    HashMap<Character, Integer> count = new HashMap<Character, Integer>();
    for (char c : s.toCharArray()) {
      if (count.get(c) == null) {
        count.put(c, 0);
      }
      int current = count.get(c);
      count.put(c, current + 1);
    }
    return count;
  }

  public static boolean anagrams(String s1, String s2) {
    return charCount(s1).equals(charCount(s2));
  }
```

- n = length of string 1
- m = length of string 2
- Time: O(n + m)
- Space: O(n + m)

### 2. most frequent char
Return the most frequent character of the string. If there are ties, return the character that appears earlier in the string.

- HashMap

```
private static HashMap<Character, Integer> charCount(String s) {
    HashMap<Character, Integer> count = new HashMap<Character, Integer>();
    for (char c : s.toCharArray()) {
      if (count.get(c) == null) {
        count.put(c, 0);
      }
      count.put(c, count.get(c) + 1);
    }
    return count;
  }
  
  public static char mostFrequentChar(String s) {
    HashMap<Character, Integer> count = charCount(s);
    char mostFrequent = '\0';
    for (char c : s.toCharArray()) {
      if (mostFrequent == '\0' || count.get(c) > count.get(mostFrequent)) {
        mostFrequent = c;
      }
    }
    return mostFrequent;
  }
  ```


- n = length of string
- Time: O(n)
- Space: O(n)

### 3. pair sum
takes in a List and a target sum as arguments. The function should return a List containing a pair of indices whose elements sum to the given target. The indices returned must be unique.

- HashMap

```
public static List<Integer> pairSum(List<Integer> numbers, int target) {
    HashMap<Integer, Integer> previousNums = new HashMap<>();
    for (int i = 0; i < numbers.size(); i += 1) {
      int num = numbers.get(i);
      int complement = target - num;
      if (previousNums.containsKey(complement)) {
        return List.of(previousNums.get(complement), i);
      }
      previousNums.put(num, i);
    }
    return null;
  }
  ```
- n = size of input list
- Time: O(n)
- Space: O(n)

### 4. uncompress
return an uncompressed version of the string where each 'char' of a group is repeated 'number' times consecutively.

- Two Pointer  

```Source.uncompress("2c3a1t"); // -> "ccaaat"```

Use 2 pointers, to traverse the string and identify the numbers and characters

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

### 4. intersection
Write a method, intersection, that takes in two Lists, a,b, as arguments and returns a new list with common elements.

- Hash Set

```
List<Integer> a = List.of(4,2,1,6);
List<Integer> b = List.of(3,6,9,2,10);
Source.intersection(a, b) // -> [2,6]
```

```
public static List<Integer> intersection(List<Integer> listA, List<Integer> listB) {
    HashSet<Integer> setA = new HashSet<>(listA);
    List<Integer> result = new ArrayList<>();
    
    for (int ele : listB) {
      if (setA.contains(ele)) {
        result.add(ele);
      }
    }
    
    return result;
}
```

### 5. five sort
rearrange elements of the ArrayList such that all 5s appear at the end. Your method should perform this operation in-place by mutating the original ArrayList.

- Two Pointers

![Arrays-FiveSort.png](images/Arrays-FiveSort.png)
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

Second solution, where both pointers start at end.

```
public static List<Integer> fiveSort(List<Integer> array) {
    int back = array.size()-1;
    int front = back;

    while(front >=0){
      if (array.get(front) == 5){
        int backElement = array.get(back);
        array.set(front,backElement);
        array.set(back,5);
        back--;
      }
      front--;
    }
    return array;
}
```