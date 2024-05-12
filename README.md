# Sensor Data Generation

This repository is designed for generating sensor data. After running the command `sbt run`, it will prompt you for the following inputs:

1. **Number of files to be generated:** Enter the desired number of CSV files to create.
2. **Number of sensors to be included:** Specify how many sensors you want to simulate.
3. **Number of records to be generated in individual files:** Input the record count for each generated file.

The generated CSV files will be named `leader-1.csv`, `leader-2.csv`, and so on.

## Prerequisites

Make sure you have Java, Scala and Sbt installed on your system before running the project.

## Usage

1. Clone this repository.
2. Open a terminal and navigate to the project directory.
3. Run the following command: `sbt run`
4. Follow the prompts to input the required parameters.

