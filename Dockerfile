# SieveOfEratosthenes
# Written by Louis LeBlanc
# For Homework Assignment 4
# Dockerized for project 7
# RSEG-126 - Release Control and Continuous Integration/Continuous Delivery
# Brandeis University,
# Instructed by Eric Hemdal
# 
# Use the Ubuntu 18.04 image as a base
FROM ubuntu:18.04

# Update the package db and install openssh and openjkd-8-jre
RUN apt-get update && apt-get install -y openssh-server openjdk-8-jre

# SSH daemon will need this, but it doesn't get created by default for some reason.
RUN mkdir /var/run/sshd

# Create user eratosthenes and allow passwordless login
RUN useradd -m -s /bin/bash eratosthenes
RUN sed -i -re 's/^eratosthenes:[^:]+:/eratosthenes::/' /etc/passwd /etc/shadow

# SSH login fix. Otherwise user is kicked off after login
RUN sed -i 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' /etc/pam.d/sshd

# Allow blank passwords for SSH sessions in PAM:
#   Comment out the line that reads @include common-auth
RUN sed -i -re 's/^@include common-auth/# @include common-auth/' /etc/pam.d/sshd
#   And add the following:
RUN echo "" >> /etc/pam.d/sshd
RUN echo "auth [success=1 default=ignore] pam_unix.so nullok" >> /etc/pam.d/sshd
RUN echo "auth requisite pam_deny.so" >> /etc/pam.d/sshd
RUN echo "auth required pam_permit.so" >> /etc/pam.d/sshd

# Allow root login, at least for now.  Password is "root"
RUN echo 'root:root' | chpasswd
RUN sed -i 's/^PermitRootLogin prohibit-password/# PermitRootLogin prohibit-password/' /etc/ssh/sshd_config
RUN echo "PermitRootLogin yes" >> /etc/ssh/sshd_config

# Allow blank passwords for SSH sessions of eratosthenes in /etc/ssh/sshd_config:
RUN echo "" >> /etc/ssh/sshd_config
RUN echo "PrintLastLog  no" >> /etc/ssh/sshd_config
RUN echo "" >> /etc/ssh/sshd_config
RUN echo "######## Docker build additions" >> /etc/ssh/sshd_config
RUN echo "Match user eratosthenes" >> /etc/ssh/sshd_config
RUN echo "    PermitEmptyPasswords yes" >> /etc/ssh/sshd_config
RUN echo "" >> /etc/ssh/sshd_config

ENV NOTVISIBLE "in users profile"
RUN echo "export VISIBLE=now" >> /etc/profile

# Push in our files
RUN mkdir /usr/share/SieveOfEratosthenes
COPY src/resource/SieveOfEratosthenes /bin/SieveOfEratosthenes
COPY dist/lib/SieveOfEratosthenes.jar /usr/share/SieveOfEratosthenes/SieveOfEratosthenes.jar
# This is a quick explanation of the application.
# I'm still trying to find a way to omit the default splash message.
COPY src/resource/welcome.txt /etc/motd

# Set permissions - just to make sure
RUN chmod a+x /bin/SieveOfEratosthenes

# We want to be able to ssh into the container, so expose port 22
EXPOSE 22

# Finally, start our sshd service
CMD /usr/sbin/sshd -D

