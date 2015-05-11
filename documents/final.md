# COMP 530 Big Data Project
####Final Report

### 1 Introduction

#### 1.1 Aim of the project

#### 1.2 Available data sets

#### 1.3 Technologies used

Several different technologies were used to create the system described in this document.
The applications consists of three essential parts. First, all available data is loaded into the Java application. Second, the training set is used to train statistical mode built with Python. Test sets produced in stage 1 are then loaded into Python program to compute probabilities. In the third stage the data set (which includes probabilities now) is loaded into a visualisation website created with Python, HTML and JavaScript. The flow of the application is presented in figure 1.1.

![Diagram](diagram.png "Diagram")

Figure 1.1. Diagram of the whole system.

Additionally, there were several libraries and modules which were used in different parts of the application. They are listed in table 1.1.

Table 1.1. A list of libraries used in this project.

|Name|Description|
|----|-----------|
|JUnit|This Java library has been used to write various unit tests of crucial parts of the system. Link: http://junit.org/|
|Pandas|It is a Python library which makes it easier to work with different data sets. It consists of data types which help manipulate the data, and functions which ease the process of loading and saving data. Link: http://pandas.pydata.org/|
|Statsmodels|It is a Python module which consists of a number of statistical models. Logistic regression, among others that were tried, is included in this library. Link: http://statsmodels.sourceforge.net/|
|matplotlib|This Python library was used to plot graphs, used here for visualisation purposes and during development for observing trends and checking if the model is working. Link: http://matplotlib.org/|
|Flask|This small Python web framework was used to create a visualisation website. Link: http://flask.pocoo.org/|
|Leaflet|This JavaScript library was used to enable viewing results on the map. The actual map data comes from OpenStreeMap. Link: http://leafletjs.com/|


#### 1.4 Testing application

During development, it is obviously quite easy to make errors that may be difficult to spot. That may also make the process of development significantly longer, as developers need to take time to debug possibly well-hidden and not so obvious mistakes. That is why during the development stage, test driven development (TDD) was used to create crucial parts of the system, those where any mistake is very costly. So according to the principles of the TDD, developer first creates a test function (which tests a feature that will be implemented later). That approach, although possibly makes development longer, can help avoid serious problems and in the end helps create software with fewer errors. All units tests were created with JUnit library for Java and are listed in table 1.2.

Table 1.2. A list of created unit tests.

|Name|Description|
|----|-----------|
|`testLoading()`|Tests whether all data sets were correctly loaded. The test is performed by checking whether correct number of items is available in the application after the loading phase.|
|`testLanLonConverter()`|Checks whether Grid Reference points are correctly converted to latitude and longitued. Test is performed by comparing known values of latitude and longitude to the converted ones.|
|`testNGR()`|Tests whether National Grid Reference notation is correctly understood by the application. Each NGR value should be divided into three parts: two-letter square symbol and two five-digits numbers.|
|`testOS()`|This tests whether available data concerning the height over the sea level is correctly loaded, by checking if number of values are correct.|
|`testOSData()`|That's additional check for the height data. It tests whether all squares on the map have all data.|
|`testOSMetadata()`|Another test for the height data. It tests whether metadata (ie., number of rows/columns, position on the map etc.) for each square on the map is available.|
|`testOSCoordinates()`|It tests whether location obtained from the height data works correctly. It does so by trying to compare few points from the square to the known values.|
|`testFillZeroes()`|Some values used for location need to be filled with zeros at the beginning. This unit test checks if it is done correctly.|
|`testSquareSymbols()`|This test checks if conversion between 10 km squares and locations values (which can be in range 0 - 99999) works. |
|`testMinDistances()`|Tests if a function which searches for the closest monument or other landmark does so correctly.|
|`testHeightEastingNorthing()`|Checks whether location of some height values is correctly identified.|

### 2 Data

#### 2.1 Preparing training data

#### 2.2 Producing test sets

### 3 Analysis



#### 3.1 Approach

- selected features

#### 3.2 Testing algorithms

### 4 Visualisation

### 5 Conclusions

#### 5.1 Correlation of features

### 6 Summary
