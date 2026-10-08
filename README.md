# Java Practice

A small desktop library of coding challenge solutions, built with Java Swing.

## Run

Install a JDK, then compile and launch the application from the project root:

```powershell
New-Item -ItemType Directory -Force classes | Out-Null
javac -d classes (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp classes JavaPractice
```

Java source files live in category packages under `src`, and compiled classes are written to `classes`. The first category is `arraysandbasics`, with `CountAbovePreviousAverage` as its first challenge. Run it with `java -cp classes arraysandbasics.CountAbovePreviousAverage`. The window includes topic filters, text search, and expandable Java solutions with sample results.

## Test

Unit tests mirror the source package structure under `test`. Compile the sources and tests, then run the test class:

```powershell
javac -d classes (Get-ChildItem -Recurse -Path src,test -Filter *.java).FullName
java -cp classes arraysandbasics.CountAbovePreviousAverageTest
```