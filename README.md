# Java Practice

A small desktop library of coding challenge solutions, built with Java Swing.

## Run

Install a JDK, then compile and launch the application from the project root:

```powershell
New-Item -ItemType Directory -Force classes | Out-Null
javac -d classes src\JavaPractice.java
java -cp classes JavaPractice
```

Java source files live in `src`, and compiled classes are written to `classes`. The window includes topic filters, text search, and expandable Java solutions with sample results. Add new challenge entries to `createChallenges()` in `src\JavaPractice.java` as the collection grows.