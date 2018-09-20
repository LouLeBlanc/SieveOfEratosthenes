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
MAINTAINER lleblanc@brandeis.edu

# Update the package db and install openssh and openjkd-8-jre
RUN apt-get update && \
    apt-get install -y openssh-server openjdk-8-jre && \
    rm -rf /var/lib/apt/lists/*

# SSH daemon will need this
RUN mkdir /var/run/sshd && \
    sed -i -re 's/^@include common-auth/# @include common-auth/' /etc/pam.d/sshd

# Create user eratosthenes and allow passwordless login
RUN useradd -m -s /bin/bash eratosthenes && \
    sed -i -re 's/^eratosthenes:[^:]+:/eratosthenes::/' /etc/passwd /etc/shadow

# Allow blank passwords for SSH sessions in PAM:
#   Comment out the line that reads @include common-auth
#   And add the following:
RUN echo "" >> /etc/pam.d/sshd && \
    echo "auth [success=1 default=ignore] pam_unix.so nullok" >> /etc/pam.d/sshd && \
    echo "auth requisite pam_deny.so" >> /etc/pam.d/sshd && \
    echo "auth required pam_permit.so" >> /etc/pam.d/sshd

# Allow blank passwords for SSH sessions of eratosthenes in /etc/ssh/sshd_config:
RUN echo "" >> /etc/ssh/sshd_config && \
    echo "PrintLastLog  no" >> /etc/ssh/sshd_config && \
    echo "" >> /etc/ssh/sshd_config && \
    echo "######## Docker build additions" >> /etc/ssh/sshd_config && \
    echo "Match user eratosthenes" >> /etc/ssh/sshd_config && \
    echo "    PermitEmptyPasswords yes" >> /etc/ssh/sshd_config && \
    echo "" >> /etc/ssh/sshd_config

# Push in our files
RUN mkdir /usr/share/SieveOfEratosthenes
COPY src/resource/SieveOfEratosthenes /bin/SieveOfEratosthenes
COPY dist/lib/SieveOfEratosthenes.jar /usr/share/SieveOfEratosthenes/SieveOfEratosthenes.jar

# This is a quick explanation of the application.
# I'm still trying to find a way to omit the default splash message.
COPY src/resource/welcome.txt /etc/motd

# We want to be able to ssh into the container, so expose port 22
EXPOSE 22

# Finally, start our sshd service
CMD /usr/sbin/sshd -D

