--------------------------------------------------------------------------------
|            /\  Welcome to the PRISM Gradebook System                         |
|           /__\ Portfolio of Records for Instructors' Student Marks           |
--------------------------------------------------------------------------------

COMPILING THE PRISM SOFTWARE:

A shell script is included to compile the program.  To do this, simply navigate
to the folder containing the files for the program and run the following
command in the console:
    sh compilePRISM.sh

--------------------------------------------------------------------------------

TO START A NEW GRADEBOOK:

To start the program with a new grade book, run the program in the console with
no additional inputs:
    java PRISM

--------------------------------------------------------------------------------

TO INITIALIZE FROM AN EXISTING GRADEBOOK:

To start the program with an existing gradebook, run the program with the input
of the path to the file where the gradebook is stored (as a text file):
    java PRISM $GRADEBOOK_FILE

--------------------------------------------------------------------------------

USING THE GRADEBOOK:

When the program starts, a menu will appear with options for all of PRISM's
functionality. Select the desired option from the menu by typing the
corresponding number, and you will be prompted to enter any additional
information needed for that operation.

To save a gradebook for future use, select the "Output grade book" option from
the menu.

Upon quitting PRISM (menu option 16), you will be given the option to save the
grade book.

--------------------------------------------------------------------------------
