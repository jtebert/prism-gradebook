#!/bin/bash
echo "Compiling..."

# Remove old class files
find -name "*.class" > old_class.txt
if [ -s old_class.txt ]
    then
        xargs -a old_class.txt -d'\n' rm
fi

# Compile the sources
find -name "*.java" ! -name "*Test.java" > sources.txt
javac @sources.txt # -Xlint:unchecked

sed -i 's/.java/.class/g' sources.txt

if [ -e gradebook/MyGradeBook.class ]
    then
        echo "Compiling successful."
    else
        echo "Compiling failed."
fi

rm sources.txt
rm old_class.txt
