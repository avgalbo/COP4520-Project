// Anthony Galbo
// 5013858
// COP 4502 Spring 2022

#include <iostream>
#include <fstream>
#include <thread>

using namespace std;

#define NUM_TOP_PRIMES 10
#define NUM_THREADS 8
#define MAX_PRIME 100000000

// Global variables
bool *primes;
long *topPrimes, sum, counter;
thread *myThread;
int start, stop;
ofstream file;

// Sieve of Atkin algorithm
// https://www.geeksforgeeks.org/sieve-of-atkin/
void sieveOfAtkin(int limit)
{

    for (int i = 0; i < limit; i++)
        primes[i] = false;

    /* Mark sieve[n] is true if one
       of the following is true:
    a) n = (4*x*x)+(y*y) has odd number of
       solutions, i.e., there exist
       odd number of distinct pairs (x, y)
       that satisfy the equation and
        n % 12 = 1 or n % 12 = 5.
    b) n = (3*x*x)+(y*y) has odd number of
       solutions and n % 12 = 7
    c) n = (3*x*x)-(y*y) has odd number of
       solutions, x > y and n % 12 = 11 */
    for (int x = 1; x < MAX_PRIME && x * x < limit; x++) {
        for (int y = 1; y * y < limit; y++) {

            // Main part of Sieve of Atkin
            int n = (4 * x * x) + (y * y);
            if (n <= limit && (n % 12 == 1 || n % 12 == 5))
                primes[n] ^= true;

            n = (3 * x * x) + (y * y);
            if (n <= limit && n % 12 == 7)
                primes[n] ^= true;

            n = (3 * x * x) - (y * y);
            if (x > y && n <= limit && n % 12 == 11)
                primes[n] ^= true;
        }
    }

    // Mark all multiples of squares as non-prime
    for (int r = 5; r * r < limit; r++) {
        if (primes[r]) {
            for (int i = r * r; i < limit; i += r * r)
                primes[i] = false;
        }
    }
  }

// Algorithm to get all primes smaller than or equal to n.
// geeksforgeeks.org/sieve-of-eratosthenes
void sieveOfEratosthenes(int n)
{
    // Initialize all entries it as true.
    // A value in prime[i] will
    // finally be false if i is
    // Not a prime, else true.
    memset(primes, true, MAX_PRIME + 1);

    for (int p = 2; p < MAX_PRIME && p * p <= n; p++)
    {
        // If prime[p] is not changed,
        // then it is a prime
        if (primes[p] == true)
        {
            // Update all multiples
            // of p greater than or
            // equal to the square of it
            // numbers which are multiple
            // of p and are less than p^2
            // are already been marked.
            for (int i = p * p; i <= n; i += p)
                primes[i] = false;
        }
    }
}

// Function to run the thread of primes
void runThread(int n)
{
  // Start the clock
  start = clock();

  for(int i = 0; i < NUM_THREADS; i++)
  {
    myThread[i] = thread(sieveOfEratosthenes, n);

    n *= 10;
  }

  // Return the thread
  for(int i = 0; i < NUM_THREADS; i++)
    myThread[i].join();

  // Stop the clock
  stop = clock();
}

// Utility function to add to the sum.
void addToSum(long n)
{
  sum += n;
}

// Utility function to increment counter.
void incrementCounter()
{
  counter++;
}

// Function to calculate sum, counter and the top 10 primes.
void runCalculation(int n)
{
  n = NUM_TOP_PRIMES - 1;

  // Start backwards from the max prime value, add values to the
  // topPrimes array, add value to sum and increment counter.
  for(int i = MAX_PRIME; i >= 0; i--)
  {
    if(primes[i])
    {
      addToSum(i);
      incrementCounter();

      if(n < NUM_TOP_PRIMES && n >= 0)
      {
        topPrimes[n] = i;
        n--;
      }
    }
  }
}

void printPrimesToFile()
{
  // grab time and print it to the file along with counter and sum.
  file << (double)(stop - start) / CLOCKS_PER_SEC << "s " << counter << " " << sum << endl;

  // Print the top primes to the file
  for(int i = 0; i < NUM_TOP_PRIMES; i++)
  {
    file << topPrimes[i];

    file << ((i == NUM_TOP_PRIMES - 1) ? "\n" : ", ");
  }
}

int main(void)
{
  int n = 10;
  primes = new bool[MAX_PRIME + 1];
  topPrimes = new long[NUM_TOP_PRIMES];
  myThread = new thread[NUM_THREADS];

  // Open the file
  file.open("primes.txt");

  runThread(n);

  runCalculation(n);

  printPrimesToFile();

  file.close();

  return 0;
}
