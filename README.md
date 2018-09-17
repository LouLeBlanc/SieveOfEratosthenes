# SieveOfEratosthenes
Written by Louis LeBlanc<br>
For Homework Assignment 4<br>
RSEG-126 - Release Control and Continuous Integration/Continuous Delivery<br>
Brandeis University,<br>
Instructed by Eric Hemdal<br>

## Project:
Java implementation of the Sieve of Eratosthenes, an algorithm to find all prime numbers up to a specified limit.

This implementation is based on details retrieved from the <a href="https://www.geeksforgeeks.org/sieve-of-eratosthenes/">GeeksforGeeks</a> website.

Java command to run the sieve:<br>
<code>$ java -jar  dist/lib/SieveOfEratosthenes.jar</code>

## Running Ant:
<table>
  <tr><strong>Targets:</strong></tr>
  <tr><td>&nbsp;<strong>build</strong></td><td>Build the Java classes - <strong>Default</strong></td></tr>
  <tr><td>&nbsp;<strong>clean</strong></td><td>Remove build artifacts from the build environment</td></tr>
  <tr><td>&nbsp;<strong>dist</strong></td><td>Build the distribution jar file</td></tr>
  <tr><td>&nbsp;<strong>docker</strong></td><td>Wrapper task to build Docker if it is installed</td></tr>
  <tr><td>&nbsp;<strong>docker-installed</strong></td><td>Build Docker image if Docker is installed</td></tr>
  <tr><td>&nbsp;<strong>docker-not-installed</strong></td><td>Display message if Docker is not installed</td></tr>
  <tr><td>&nbsp;<strong>init</strong></td><td>Initialize build environment</td></tr>
  <tr><td>&nbsp;<strong>junit</strong></td><td>Run JUnit tests</td></tr>
  <tr><td>&nbsp;<strong>junitreport</strong></td><td>Generate JUnit report in HTML format</td></tr>
  <tr><td>&nbsp;<strong>run</strong></td><td>Run the jar file</td></tr>
</table>


## Docker:
To build a Docker image for this application (without using the ant build), execute the following command from the project base directory:<br>
<code>$ docker build -t rseg:sieveoferatosthenes .</code><br>
The build will take several minutes, but should succeed.

To run the image, execute the following:<br>
<code>$ docker run -d -P rseg126:sieveoferatosthenes</code>

Find the **CONTAINER_ID** by executing<br>
<code>$ docker container ls</code>

The **IPAddress** associated with the container can be found with the following command:<br>
<code>$ docker inspect **CONTAINER_ID** | grep &quot;\\<IPAddress\\>&quot;</code>

Finally, ssh into the container as user eratosthenes:<br>
<code>$ ssh eratosthenes@**IPAddress**</code><br>
No password is needed.

Now that you're in the container, execute the sieve with the following command:<br>
<code>$ SieveOfEratosthenes</code><br>
