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
Targets:<br>
&nbsp;**clean:** Clean out all derived artifacts.<br>
&nbsp;**init:**  Create directory structure for other targets.<br>
&nbsp;**build:** Run Java build. **Depends:** init<br>
&nbsp;**test:** Execute basic JUnit (4) tests. **Depends:** build<br>
&nbsp;**junitreport:** Create a JUnit report in HTML format. **Depends:** test<br>
&nbsp;**dist:** Build the jarfile. **Depends:** test, junitreport<br>
&nbsp;**run:** Run the sieve. **Depends:** dist<br>

##Docker
To build a Docker image for this application, execute the following command from the project base directory:<br>
<code>$ docker build -t rseg:sieveoferatosthenes .</code><br>
The build will take several minutes, but should succeed.

To run the image, execute the following:<br>
<code>$ docker run -d -P rseg126:sieveoferatosthenes</code>

Find the **CONTAINER_ID** by executing<br>
<code>$ docker container ls</code>

The **IPAddress** associated with the container can be found with the following command:<br>
<code>$ docker inspect **CONTAINER_ID** | grep &quot;\&lt;IPAddress\\>&quot;</code>

Finally, ssh into the container as user eratosthenes:<br>
<code>$ ssh eratosthenes@**IPAddress**</code><br>
No password is needed.

Now that you're in the container, execute the sieve with the following command:<br>
<code>$ SieveOfEratosthenes</code><br>
