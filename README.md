# Java Practice

A small desktop library of coding challenge solutions, built with Java Swing.

## Run

Install a JDK, then compile and launch the application from the project root:

```powershell
New-Item -ItemType Directory -Force classes | Out-Null
javac -d classes (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp classes JavaPractice
```

Java source files live in category packages under `src`, and compiled classes are written to `classes`. The first category is `arraysandbasics`, with `CountAbovePreviousAverage` and `MergeIntervals` as its first challenges. Run them with `java -cp classes arraysandbasics.CountAbovePreviousAverage` and `java -cp classes arraysandbasics.MergeIntervals`. The window includes topic filters, text search, and expandable Java solutions with sample results.

The `stacksandqueues` category includes `ValidateProperlyNestedBrackets`; run it with `java -cp classes stacksandqueues.ValidateProperlyNestedBrackets`.

The `binarysearch` category includes `TargetIndexSearch`, `PivotedSearch`, and `MedianOfTwoCircularlySortedLogs`; run them with `java -cp classes binarysearch.TargetIndexSearch`, `java -cp classes binarysearch.PivotedSearch`, and `java -cp classes binarysearch.MedianOfTwoCircularlySortedLogs`.

It also includes `NextGreaterElementWithPositionOffset`; run it with `java -cp classes stacksandqueues.NextGreaterElementWithPositionOffset`.

`MaxAreaUnderHistogramAfterRemovingOneBar` is also in `stacksandqueues`; run it with `java -cp classes stacksandqueues.MaxAreaUnderHistogramAfterRemovingOneBar`.

## Test

Unit tests mirror the source package structure under `test`. Compile the sources and tests, then run the test class:

```powershell
javac -d classes (Get-ChildItem -Recurse -Path src,test -Filter *.java).FullName
java -cp classes arraysandbasics.CountAbovePreviousAverageTest
java -cp classes arraysandbasics.MergeIntervalsTest
java -cp classes binarysearch.TargetIndexSearchTest
java -cp classes binarysearch.PivotedSearchTest
java -cp classes binarysearch.MedianOfTwoCircularlySortedLogsTest
java -cp classes stacksandqueues.ValidateProperlyNestedBracketsTest
java -cp classes stacksandqueues.NextGreaterElementWithPositionOffsetTest
java -cp classes stacksandqueues.MaxAreaUnderHistogramAfterRemovingOneBarTest
```