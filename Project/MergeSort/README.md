Anthony Galbo
Team 24
COP 4520 Spring 2022

Team Project

Source code name: ParallelMergeSort.java

## Compile and Run Instructions

1. In a terminal, navigate to the location of ParallelMergeSort.java using the command:
```bash
    cd
```
2. To compile the program, run the following command:
```bash
    javac ParallelMergeSort.java TestCases.java
```
3. To run the program, use the following command:
```bash
    java TestCases
```

## Output

The output will display to the console in the following format:
```txt
Array size <n> Parallel: Total computation time = <n>ms
Array size <n> Not Parallel: Total computation time = <n>ms
```

## Proof of Correctness
The merge sort algorithm works in a "divide and conquer" style algorithm where the algorithm divides the array into two halves and merges the two sorted halves.

However merge sort implemented in Parallel programming can be implemented by using the java ForkJoin framework and Recursive Action which is a subclass of the ForkJoin framework. It is an abstract class representing a task that can be executed on a separate core in a multicore system. So sorting a large array can be divided in to small manageable chunks and each part being sorted on a separate core. Thus the Recursive Action class is used for tasks that can be divided and executed parallelly.

## Efficiency

Since the only difference between the parallel implementation and the standard implementation is that the parallel implementation utilizes each core during each split.

With that said the runtime for both algorithms remain the same.

Let ```T(n)``` be the running time of Merge sort of input size ```n```. Then we have:
```txt
  T(n) = (Time in step 1) + (Time in step 2) + (Time in step 3)
```

We notice that step 1 and step 2 are sorting problems as well but of size ```n/2```, and that the last step runs in ```O(n)``` time, we get the following equation for ```T(n)```.

```txt
  T(n) = T(n/2) + T(n/2) + O(n)
       = 2T(n/2) + O(n)
```
Solving the recurrence relation we get
```txt
  T(n) = 2T(n/2) + n
  T(n) = 2[2T(n/4)+n/2] + n
       = 4T(n/4) + 2n
       = 4[2T(n/8)+n/4] + 2n
       = 8T(n/8) + 3n
```

The generalized form after the kth application we get:
```txt
  T(n) = 2kT(n/2^k) + kn
```

We assume ```T(n) = 1``` so we can solve for ```T(n)```. We also have ```T(n/2^k)```. Solving for ```k``` we get:
```txt
    n = 2^k = k = log_2(n)
```

We now find:
```txt
  T(n) = nT(1) + nlog_2n
```

Therefore the run time for the merge sort algorithm is:
```txt
  O(nlog(n))
```

## Experimental evaluation

Before I display the results, This experiment was tested on an Macbook pro intel i9 processor with 16gb of ram.

Results may differ on machines with different cores.

We tested array sizes:
```txt
  10, 100, 1,000, 10,000, 100,000, 1,000,000, 10,000,000, 100,000,000
```

Our results in computation time were:
```txt
Array size 10 Parallel: Total computation time = 6ms
Array size 10 Not Parallel: Total computation time = 0ms

Array size 100 Parallel: Total computation time = 2ms
Array size 100 Not Parallel: Total computation time = 0ms

Array size 1000 Parallel: Total computation time = 2ms
Array size 1000 Not Parallel: Total computation time = 0ms

Array size 10000 Parallel: Total computation time = 13ms
Array size 10000 Not Parallel: Total computation time = 1ms

Array size 100000 Parallel: Total computation time = 79ms
Array size 100000 Not Parallel: Total computation time = 9ms

Array size 1000000 Parallel: Total computation time = 84ms
Array size 1000000 Not Parallel: Total computation time = 54ms

Array size 10000000 Parallel: Total computation time = 530ms
Array size 10000000 Not Parallel: Total computation time = 586ms

Array size 100000000 Parallel: Total computation time = 4484ms
Array size 100000000 Not Parallel: Total computation time = 6746ms
```

Notice how for array sizes ```10 - 1000000```, the sizes are so small that spinning up these threads is a waste of time. However, once the array size is ```10000000 or 100000000```, this is now worth utilizing the threads in the machine for a more efficient computation time.
