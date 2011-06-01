# Overview

A concurrent, Java implementation of The Game of Life.  Run it from the command line:

    java -jar gameoflife.jar --rows=30 --cols=40
    
# Command line arguments

    -r=N, --rows=N (required)
       The number of rows in the grid.
    -c=N, --cols=N (required)
       The number of columns in the grid.
    -t=N, --threads=N
       The number of threads the game will use.
       (defaults to the computer's number of cores)
    -n=N, --noise=N
       Chooses N cells at random each iteration and toggles their states.
       (defaults to 0)
    -s=N, --sleep=N
       The number of milliseconds the program will pause for before iterating to
       the next grid state.
       (defaults to 100)
    -i=N, --iterations=N
       The number of iterations to perform.
       (defaults to infinite--the game will never end)
    -u, --suppressOutput
       Use this flag to stop the board from being displayed every iteration.
    -a=N, --startAlive=N
       The percent chance each cell has of starting in the "alive" state.
       (defaults to 0.25, unless -g is specified, in which case it is ignored)
    -g=FILE, --grid=FILE
       Specify the starting grid state in a file. Each character in the text file
       represents a cell. Alive cells are 'x', dead cells can be anything else.
       If the specified grid size (-r, -c) is greater than the grid size in the
       file, it will populate the upper-left corner of the grid. If it's less than
       the grid size in the file, it will populate as much as it can.
       Example:
       x..x.
       .xx..
       ..x.x
    -h, --help
       Displays this help message.

# Opening in Eclipse

To generate the necessary files needed to open the project in Eclipse, navigate to the project root and run the following Maven command:

    mvn eclipse:eclipse

# How to build

To build the project, navigate to the project root and run the following Maven command:

    mvn clean compile assembly:single
    
This will generate a runnable JAR file that contains all dependencies.