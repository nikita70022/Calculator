# Requirements:

#### 1. The calculator can perform operations of addition, subtraction, multiplication and division with two numbers: a + b, a - b, a * b, a / b. Data are transferred in one line (see example)! Decisions in which each number and arithmetic operation are passed from a new line are considered invalid.
#### 2. The calculator can work with both Arabic (1,2,3,4,5...) and Roman (I,II,III,IV,V...) numbers.
#### 3. The calculator should accept numbers from 1 to 10 inclusive, at most. The output numbers are not limited in size and can be any.
#### 4. The calculator only works with integers.
#### 5. The calculator can only work with Arabic or Roman numerals at the same time, when the user enters a line like 3 + II, the calculator must discard the exception and stop its work.
#### 6. When entering Roman numbers, the answer must be derived by Roman numerals, respectively, when entering Arabic - the answer is expected Arabic.
#### 7. When the user enters the wrong numbers, the application rejects the exception and exits.
#### 8. When a user enters a string that does not match one of the arithmetic operations described above, the application discards the exception and exits.
#### 9. The result of the division operation is an integer, discarding the remainder.
####  10. A calculator with Arabic numbers can result in negative numbers and zero. The result of the calculator with Roman numbers can be only positive numbers, if the result of work is less than one, the exception is thrown away.

----


####  Example of the program:

```
Input:
1 + 2

Output:
3

Input:
VI / III

Output:
II

Input:
I - II

Output:
throws Exception // there are no negative numbers in the Roman system

Input:
I + 1

Output:
throws Exception // different number systems are used simultaneously
Input:
1

Output:
throws Exception // string is not a mathematical operation

Input:
1 + 2 + 3

Output:
throws Exception // the format of the mathematical operation does not satisfy the task - two operands and one operator (+, -, /, *)
```