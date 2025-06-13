# Binary Tree 2

- [1. Lowest Common Ancestor](#1-lowest-common-ancestor)
- [2. Flip Tree](#2-flip-tree)
- [3. Lefty Nodes](#3-lefty-nodes)
- [4. Binary Search Tree](#4-binary-search-tree)

## Problems
### 1. Lowest Common Ancestor

```
//      a
//    /    \
//   b      c
//  / \      \
// d   e      f
//    / \
//    g  h

Source.lowestCommonAncestor(a, "d", "h"); // b

/*node may be considered an ancestor of itself.*/

Source.lowestCommonAncestor(a, "f", "c"); // c
```

![alt text](image-32.png)

- Find Path from Both Nodes to Root
- Compare Paths from Both Nodes to Root to Find the First Common Node

![alt text](image-33.png)

- Note how the Path is found.
- Also note the use of Set to Find the First Common Node.

```
public static String lowestCommonAncestor(Node<String> root, String val1, String val2) {
    List<String> path1 = findPath(root, val1);
    List<String> path2 = findPath(root, val2);
    HashSet<String> set2 = new HashSet<>(path2);
    for (String val : path1) {
      if (set2.contains(val)) {
        return val;
      }
    }
    return null;
  }

public static List<String> findPath(Node<String> root, String targetVal) {
    if (root == null) {
      return null;
    }
    
    if (root.val == targetVal) {
      List<String> newList = new ArrayList<>();
      newList.add(root.val);
      return newList;
    }
    
    List<String> leftPath = findPath(root.left, targetVal);
    if (leftPath != null) {
      leftPath.add(root.val);
      return leftPath;
    }
    
    List<String> rightPath = findPath(root.right, targetVal);
    if (rightPath != null) {
      rightPath.add(root.val);
      return rightPath;
    }
    
    return null;
  }  
```
---
2. flip tree
- Easy Problem
```
//      a
//    /    \
//   b      c
//  / \      \
// d   e      f
//    / \
//    g  h

Source.flipTree(a); 

//       a
//    /    \
//   c      b
//  /     /   \
// f     e    d
//     /  \
//    h    g
```

![alt text](image-34.png) ![alt text](image-35.png)

```
public static Node<String> flipTree(Node<String> root) {
    if (root == null) return null;

    Node<String> leftFlipTree = flipTree(root.left);
    Node<String> righFliptTree = flipTree(root.right);
    
    root.right = leftFlipTree;
    root.left = righFliptTree;

    return root;
  }
```

```
n = number of nodes
Time: O(n)
Space: O(n)
```
---
3. lefty nodes

![alt text](image-36.png)

- Level Order Traversal
- Both DFS and BFS can be used.
- If using DFS, traverse from left to right, since we are looking for leftmost nodes.
- Maintin a list of leftmost nodes.

```
  public static List<String> leftyNodes(Node<String> root) {
    Map<Integer,String> levelLeftElementMap = new HashMap<>();
    getLevelLeftElement(root,0,levelLeftElementMap);
    return new ArrayList<>(levelLeftElementMap.values());
  }

  private static void getLevelLeftElement(Node<String>root,int level,Map<Integer,String> levelLeftElementMap){
    if (root == null) return;
    
    if (!levelLeftElementMap.containsKey(level)){
      levelLeftElementMap.put(level,root.val);
    }

    getLevelLeftElement(root.left,level+1,levelLeftElementMap);
    getLevelLeftElement(root.right,level+1,levelLeftElementMap);

    return;
  }
```

```
problem
approach
walkthrough
solution
submissions
add to favoritessettings
solution
depth first

import java.util.List;
import java.util.ArrayList;

class Node<T> {
  T val;
  Node<T> left;
  Node<T> right;
  
  public Node(T val) {
    this.val = val;
    this.left = null;
    this.right = null;
  }
}

class Source {
  public static List<String> leftyNodes(Node<String> root) {
    List<String> values = new ArrayList<>();
    traverse(root, values, 0);
    return values;
  }

  public static void traverse(Node<String> root, List<String> values, int level) {
    if (root == null) {
      return;
    }
    
    if (level == values.size()) {
      values.add(root.val);
    }
    
    traverse(root.left, values, level + 1);
    traverse(root.right, values, level + 1);
  }
  
  public static void run() {
    // this function behaves as `main()` for the 'run' command
    // you may sandbox in this function , but should not remove it
  }
}
```

```
n = number of nodes
Time: O(n)
Space: O(n)
```
---
### 4. Binary Search Tree
A Binary Search Tree is a binary tree where all values within a node's left subtree are smaller than the node's value and all values in a node's right subtree are greater than or equal to the node's value.

The solution should have a best case runtime of O(log(n)).

- Easy Problem

```
//      12
//    /   \
//   5     18
//  / \     \
// 3   9     19

Source.binarySearchTreeIncludes(a, 9); // -> true
Source.binarySearchTreeIncludes(a, 15); // -> false
```

![alt text](image-37.png)

![alt text](image-38.png)

```
public static boolean binarySearchTreeIncludes(Node<Integer> root, int target) {
    if(root == null) return false;
    if (root.val == target) return true;

    if(root.val > target) 
      return binarySearchTreeIncludes(root.left,target);
    else
      return binarySearchTreeIncludes(root.right,target);
  }
```



