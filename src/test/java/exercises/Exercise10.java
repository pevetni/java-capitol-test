package exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

import org.junit.Test;

import com.google.common.collect.ImmutableList;


public class Exercise10 {

  //Approach One
  @Test
  public void printNextGreaterElement()  {
    List<Integer> numberList = ImmutableList.of(4,5,2,25);
    int nextGreaterNumber;
    // for each element we need to iterate the array in the actual position +1 to know the greater element
    for (int indexOne=0; indexOne<numberList.size(); indexOne++) {
      nextGreaterNumber = -1;
      for (int indexTwo=indexOne+1; indexTwo<numberList.size(); indexTwo ++) {
        if (numberList.get(indexOne) < numberList.get(indexTwo)) {
          nextGreaterNumber = numberList.get(indexTwo);
          break;
        }
      }
      System.out.println(numberList.get(indexOne)+" ---------> "+nextGreaterNumber);
    }
  }

  //Approach two
  @Test
  public void printNextGreaterElementWithStackRigthLeft()  {
    List<Integer> numberList = ImmutableList.of(4, 5, 2, 25);
    List<Integer> result = new ArrayList<>();
    IntStream.range(0, numberList.size()).forEach(num -> result.add(-1));

    // create an empty stack
    Stack<Integer> s = new Stack<>();

    // process each element from right to left
    for (int i = numberList.size() - 1; i >= 0; i--)
    {
      // loop till we have a greater element on top or stack becomes empty.
      while (!s.empty())
      {
        // pop elements that aren't greater than the current element
        if (s.peek() <= numberList.get(i)) {
          s.pop();
        }
        // the next greater element is now on the top of the stack
        else {
          result.set(i, s.peek());
          break;
        }
      }

      // push current element into the stack
      s.push(numberList.get(i));
    }

    IntStream.range(0, result.size()).forEach(index -> System.out.println(numberList.get(index)+" ---------> "+result.get(index)));
  }

  //Approach three
  @Test
  public void printNextGreaterElementWithStackLeftRigth()  {
    List<Integer> numberList = ImmutableList.of(4, 5, 2, 1, 25);
    HashMap<Integer, Integer> next_greater = new HashMap<>();
    Stack<Integer> stack = new Stack<>();

    // process each element from left to right
    for (Integer num : numberList) {
      // loop till we haven't elements and if we have one put it into the hashmap.
      while (!stack.isEmpty() && stack.peek() < num) {
        next_greater.put(stack.pop(), num);
      }
      stack.push(num);
    }

    for (int i = 0; i<numberList.size(); i++) {
      //for each element on the list we need to check if it has a matching value in the hashmap if not write -1 as greater value
      System.out.println(numberList.get(i)+"---->"+next_greater.getOrDefault(numberList.get(i), -1));
    }

  }
}
