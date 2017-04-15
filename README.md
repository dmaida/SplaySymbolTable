Description:
This Splay symboltable data structure is implemented by using parent pointers. 
Splay Tree insertion and search is performed in BigO(logn) amortized time. The SplaySymbolTable class
supports the following operations using parent pointers:

insert(key, val)
search(key)
Note: 
  The serialize() operation is provided so the the tree can print to an SVG image 
  using the provided TreePrinter class.

Build (depends on SymbolTable.java, and TreePrinter.java):
   javac SplayBSTSymbolTable.java

To run a simple test (and print an SVG image):
   java SplaySymbolTable

To run battery of unit tests:
  java SplayBSTSymbolTableUnitTest.java
  java TestSplaySymbolTable.java
  java Binomial.java

Speed test of Memoized vs. Non Memoized binomimial computation:
 
 The memoized computation of (n / k) for k = 2, . . . , n/2 for n = 35 is much faster than 
 the non memoized computation. 
 
 Memoized:
  binomial(35, 17) = 4537567650
  time = 0.004311607 seconds
  comparisons = 2509
  rotations = 2014

 Non-memoized:

  binomial(35, 17) = 4537567650
  time = 18.640561955000003 seconds

Testing: 
To run the rest harness you need to run the Binomial.java file. This will compute Binomial for an arbitrary 
number in the classs. It will also print a tree of the calculations as SVG file. 

List of files in archive:

README.txt......................this file 

Binomial.java...................test harness for Splay tree with parent pointers 

SplaySymbolTable.java...........Splay trees with parent pointers

SplaySymbolTableUnitTest.java...UnitTest for Splay tree with parent pointers 

SymbolTable.java................SymbolTable interface 

TestSplaySymbolTable.java.......Test for Splay trees with parent pointers 

TreePrinter.java................Utility for printing binary trees 
