Anthony Galbo
COP 4520 Spring 2022

Programming Assignment 1

File name: primes.cpp

Compile and Run Instructions:

1. In a terminal, navigate to the location of primes.cpp
2. To compile the program, run the following command:
3. Compile the program with the following command: (Note the addition of "" in the compile command is necessary because the C++11 standard library includes multithreading library. Without it the program will not compile)
    g++ -std=c++11 primes.cpp
4. To run the program, use the following command:
    ./a.out
5. A bonus shortcut: To compile and run the program on the same line, use the following command:
    g++ -std=c++11 primes.cpp && ./a.out

Output:

The results of the program will be printed to primes.txt in the following format:

<execution time> <total number of primes found> <sum of all primes found>
<top ten maximum primes, listed in order from lowest to highest>


## Proof of Correctness
The sieve of Eratosthenes algorithm was provided by GeeksforGeeks as well as the proof of correctness

  https://www.geeksforgeeks.org/sieve-of-eratosthenes/

An example is to take n = 50. So we print out all the prime numbers smaller than or equal to 50

  2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50

The algorithm will mark all the numbers that are divisible by 2 and are greater than or equal to the square of it. Which leaves:

  4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50

Now we move to our next unmarked number 3 and mark all the numbers which are multiples of 3 and are greater than or equal to the square of it.

  4, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 20, 21, 22, 24, 26, 27, 28, 30, 32, 33, 34, 36, 38, 39, 40, 42, 44, 45, 46, 48, 50.

We now move to the next unmarked number 5 and mark all the multiples of 5 and are greater than or equal to the square of it

  4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 36, 38, 39, 40, 42, 44, 45, 46, 48, 49, 50.

Finally our prime numbers are the unmarked ones:

  2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47


Since this assignment wants the the top 10 primes where n = 100000000. Therefore the algorithm will list all the primes from 2 all the way to 100000000. However, to list the top ones, we will run a loop backwards from index starting at 100000000 and stoping as soon as index is less than or equal to zero. If index is prime we add it to the topPrimes array. then decrement n.

Efficiency:

The outer loop runs in O(n) Then in the inner loop, lets assume that our current prime number is 2. So in our first iteration we we will mark n/2 elements. Next when our prime number is 3 we will mark n/3 elements. Therefore the total number of times the loop runs would be equal to:

  n/2 + n/3 + n/5 + n/7 + ...


Factoring n out of the equation, we have:

  n * (1/2 + 1/3 + 1/5 + 1/7 + ...)


Therefore using the harmonic progression of the sum of primes, it can be proved that:

  n * (1/2 + 1/3 + 1/5 + 1/7 + ...) = O(nlog(log(n)))

Therefore the run time of the Sieve of Eratosthenes algorithm is:

  O(nlog(log(n)))


Experimental Evaluation:

Let us compare the 2 algorithms Sieve of Atkin versus Sieve of Eratosthenes.

Note: Sieve of Atkin was also provided by GeeksforGeeks

  https://www.geeksforgeeks.org/sieve-of-atkin/


The runtime of Sieve of Atkin is:

  O(n/(log(log(n))))


Whereas the runtime of Sieve of Eratosthenes algorithm is:

  O(nlog(log(n)))


Almost the same runtime except one is division and one is multiplication. This is because multiplication breaks down into multiple additions and division breaks down into multiple subtractions. The difference is that additions in multiplication can be done in parallel whereas division, you cannot do the next subtraction until you finish the previous one and do a comparison. Therefore, division is more expensive than multiplication.

Thus, taking the average of 10 runs on both algorithms proves this concept perfectly.

The Sieve of Atkin algorithm runs for

  1.952113s

on an average of 10 runs.

Whereas the Sieve of Eratosthenes algorithm runs for

  1.688254s

Therefore, the Sieve of Eratosthenes algorithm is slightly more efficient due to experimental evaluation
