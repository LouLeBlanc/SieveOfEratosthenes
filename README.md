# SieveOfEratosthenes
Written by Louis LeBlanc<br>
For Homework Assignment 4<br>
RSEG-126 - Release Control and Continuous Integration/Continuous Delivery<br>
Brandeis University,<br>
Instructed by Eric Hemdal<br>

Java implementation of the Sieve of Eratosthenes, an algorithm to find all prime numbers up to a specified limit.

Implementation based on details retrieved from GeeksforGeeks website: https://www.geeksforgeeks.org/sieve-of-eratosthenes/

Java command to run:<br>
<code>$ java -cp  dist/lib/SieveOfEratosthenes-${DSTAMP}.jar edu.brandeis.rseg126.SOE.SieveOfEratosthenes</code>

Running commands through Ant:<br>
Targets:
&nbsp;<strong>clean:</strong> Clean out all derived artifacts.<br>
&nbsp;<strong>init:</strong>  Create directory structure for other targets.<br>
&nbsp;<strong>build:</strong> Run Java build. Depends: init<br>
&nbsp;<strong>SieveOfEratosthenesTest:</strong> Execute basic JUnit (4) tests. Depends: build<br>
&nbsp;<strong>junitreport:</strong> Create a JUnit report in HTML format. Depends: SieveOfEratosthenesTest<br>
&nbsp;<strong>dist:</strong> Build the jarfile. Depends: SieveOfEratosthenesTest, junitreport<br>

