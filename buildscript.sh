#!/bin/bash
echo "Starting java code";
read -p "Enter value to check if valid: " name
read -p "Enter type (PN/ON/SN) : " type
echo "Value that is entered $name with type $type";

javac checkValidity.java
java checkValidity.java $name $type
