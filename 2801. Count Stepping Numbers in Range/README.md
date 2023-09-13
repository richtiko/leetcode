This folder contains steps for coming up with the solution for the leetcode problem https://leetcode.com/problems/count-stepping-numbers-in-range/submissions/
2801. Count Stepping Numbers in Range
countWithSpecifiedLength.java - is the first step when we count all the numbers that are of the specified length
Note how the numbers are generated and how they are iterated over with the recursion.
countForAllLengths.java - is second version where all the lengths between low and high ranges are considered
solutionConsideringBounds.java - does not count the numbers that are outside of range. note how the numbers are ruled out even before they are fully generated using the current digit. Also note how the check is disabled once we reach a digit that does not match the corresponding digit in the bounds
fullSolutionWithDP.java - This is a full solution which takes into account the duplicated work and caches the results based on the digit and remaining length. The length is important because the longer the numbers the more stepping numbers can be generated.