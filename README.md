# plotData
plotData is a simple Java application that reads numerical data from a 2-column CSV file and generates a chart saved as an image (output.png).

## How to Run
### 1. Prepare your data file
Ensure a file named data.csv exists in the same directory.

Example format:

* Time,Velocity
* 1,10
* 2,15
* 3,7
* 4,20

### 2. Compile the project

Ensure you have Java installed. Open a terminal in the project directory and run:
```
javac *.java
```

### 3. Run the application
```
java Main
```

### 4. View the output
After running successfully, the chart will be saved as:

output.png

Open this file to view the generated chart.

---
### Tips
* Make sure data.csv is in the correct location
* Check file permissions if access is denied
* Ensure your CSV format matches what CsvReader expects

---
### Notes
* The output file (output.png) will be overwritten each time the program runs
* Modify filename in Main.java if you want to use a different input file

---
# TODO
* read any .csv file not just data.csv
* change output filename to {INPUT_FILENAME_WITHOUT_EXTENSION}_{YYYY.MM.DD HH:MM:SS}.png so the program can be run repeatedly