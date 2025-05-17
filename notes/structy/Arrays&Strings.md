# Arrays and Strings

- [Arrays and Strings](#arrays-and-strings)
  - [Intro](#intro)
  - [Problems](#problems)
    - [1. uncompress](#1-uncompress)
    - [2. five sort](#2-five-sort)


## Intro

Important techniques
- Two Pointer

## Problems

### 1. uncompress
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


### 2. five sort
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