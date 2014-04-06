#!/bin/bash

echo "\n"
echo "--------------------------------"
echo "Compiling:"
echo "--------------------------------"
echo "\n"

# Classes with tests to run
TEST0="gradebook.GradebookWhiteboxTest"
TEST1="GradebookBlackboxTest"
TEST2="PRISM"

# JUnit variable(s)
CLASSPATH=".:../junit.jar"
JUNITMAIN="org.junit.runner.JUnitCore"

# Remove old class files
find -name "*.class" > old_class.txt
if [ -s old_class.txt ]
    then
        xargs -a old_class.txt -d'\n' rm
fi

# Compile the sources (+ Junit)
find -name "*.java" > sources.txt
javac -cp $CLASSPATH @sources.txt #-Xlint:unchecked

if [ -e gradebook/MyGradeBook.class ]
    then
        echo "\n---- Compiling successful. ----\n"

        # Run the tests
        echo "--------------------------------"
        echo "Running tests:"
        echo "--------------------------------"
        
        echo "\n**** $TEST0 ****"
        java -cp $CLASSPATH $JUNITMAIN $TEST0
        
        echo "\n**** $TEST1 ****"
        java -cp $CLASSPATH $JUNITMAIN $TEST1
        
        echo "\n**** $TEST2 ****"
        java $TEST2
    else
        echo "\n---- Compiling failed. ----\n"
fi

echo "\n"
