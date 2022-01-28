# **Lab Report 2 Week 4**

## Code Change 1: Fixing Infinite While Loop
Fix for Infinite While Loop: ![](infiniteWhileLoopFix.png)

Link to Failure-Inducing File: [https://github.com/jwong1209/markdown-parse/blob/main/test-file2.md](https://github.com/jwong1209/markdown-parse/blob/main/test-file2.md)

Symptom of Failure-Inducing Input: 
![Infinite While Loop](infiniteWhileLoop.png)

Explanation: The line in the code `currentIndex = closeParen + 1` will reset the currentIndex back to the last parenthesis's index. This is an issue because if the last close parenthesis is not at the end as in the case of `test-file2.md`, then the condition `currentIndex < markdown.length()` would never be fulfilled and thus cause the while loop to keep repeating and the inside code to keep traversing through the file. This is fixed by checking if one of the variables is equal to -1 because at least one of them will be if you tried to find the indexOf a bracket or parenthesis during one of the while loop's faulty iterations.

## Code Change 2: Fixing Code Giving Images
Fix for Image: ![](imageGivenFix.png)

Link to Failure-Inducing File:[https://github.com/jwong1209/markdown-parse/blob/main/test-file4.md](https://github.com/jwong1209/markdown-parse/blob/main/test-file4.md)

Symptom of Failure-Inducing Input:
![imageGiven](imageGiven.png)

Explanation: The code only uses the index of the brackets and parenthesis to decide what is a link or not. This causes the code to identify images as links and add them to `toReturn` since the format for a image is nearly identical except that the image has a `!` at the beginning. To fix this, I used the exclamation mark as the differentiator and if a `[` had a `!` in front of it, then it would not pass the `if( nextOpenBracket == 0 || markdown.indexOf("!", nextOpenBracket-1) != nextOpenBracket-1)` statement. 