#Big Data Project (COMP530)
##Design Document

###1 Summary of Proposal

This project is intended to help inform of the likelihood of the archeological discovery anywhere in England. It will be used by planners wishing to lower the risk involved with discovering archeological sites and those looking for a specific place of value.

The project is being conducted in conjunction with the Hartree Centre. The aims of the project involve allowing a stakeholder to visualise likely locations where archaeological artefacts may be discovered. The likelihood will be calculated using a statistical model with data fed into it from open sources.

The research we have carried out so far has involved gathering data from various sources, we have also researched the software and development tools necessary to complete this project which we had no experience in previously. These were mostly part of the IBM Big Insights package and included Big R, Big SQL, and various other sections of the software product. In further time we will seek to learn how to use the visualisation software involved
in the project.

###2 Data structures and storage

To store the data we will be using Hadoop within the InfoSphere BigInsights package and to query the data as it is spread over numerous nodes we will need to use a specialised querying software - Big SQL. The data with be distributed and parallelised using Hadoop and MapReduce. Then to query this data we will use the built in Hive platform within IBM's Big SQL. Big SQL uses IBM's knowledge of databases to provide access to Hadoop Distributed File Systems whether through JDBC or ODBC seamlessly.

The data we will be distributing using Hadoop includes the OS Terrain 50 which is in ASCII grid format which has the height above mean sea level for the whole of Britain defined at 50 metre intervals. Also, we have the York Archaeological Data Service grey literature which in CSV format contains the locations of 30,000 historical excavations across the UK. The English Heritage site contains the location data of historical battle sites, encampments and finds across the UK. Finally the British Geological Survey looks at soil composition at locations around the UK which is in the form of GIS grid data.

We will distribute this data as previously spoken about and then pass it through a statistical model which will compare the terrain type, historical events, previous archaeological finds to process a likelihood of finding an item of interest at that location.

###3 Algorithms and statistical model

In order to obtain proper results, we intend to combine several methods. That approach would let us learn those algorithms and should also benefit the quality of the outcome.

First, we are going to implement a solution based on the idea of supervised learning.
Using available data set containing a list of about 30,000 known archeological sites, a Perceptron algorithm will be trained. A portion of those data (for example from one county) will be chosen as a training set. After training stage, features will have weights assigned and based on that, results will be obtained.

Afterwards, during the prediction stage, we are going to measure relationship between features using linear and logistic regression. Greater similarity between training and "real" data would imply possibility of archeological artefacts to be found in a particular area.

One of the challenges we will need to face when implementing those algorithms, is to make sure that they use the same coordinate systems. Unfortunately, data sets that are available use different ways to specify locations. Some use WGS84 Latitude/Longitude and some use  OSGB36 Grid References that are based on Easting/Northing values. Obviously, to obtain correct outcome, a valid conversion method will need to be used.

###4 Interface design

####4.1 Visualisation
The outcome of the project is meant to be visualised on a large screen. But throughout development phase, as well as later, for demonstrational purposes, it would be very convenient to have a simpler visualisation method that could be run on any machine.

That is why we intend to create a small web application that allows to observe the results of our statistical model (requirement BR_VI_02). The outcome should be drawn on a map and easy to navigate from the browser for any user. The results should be straightforward to read and interpret, with different values (or classes) marked distinctly (for example by varying colours).

Apart from the map, the results can also be drawn as charts, so that a user has a thorough understanding where the probability is high and where it is low. Also, it should be possible to move between the two views (map and charts) smoothly.

If there is more that one statistical method available, there should be an easy way to distinguish the results produced by each method.

![Sketch of a visualisation website](vis1.png "Sketch of a visualisation website")
Figure 4.1. Sketch of a visualisation website. It shows the most important parts of the application: map and charts (as tabs) and a simple menu to show a list of methods.

To ease our work on the features described above, some additional libraries will be used. We are going to use [*Leaflet*](http://leafletjs.com), a free JavaScript library for working with online maps. To obtain interactive charts, we will use [*D3.js*](http://d3js.org)

For visualising the outcome on Hartree's screens, geo-tagged TIFF images will be created. They should be produced at the end of the execution of a program.

####4.2 Executing the program

As the software that will be created concerns obtaining correct results through use of the statistical model, there is no need for any advanced graphical interface for starting the program. Therefore, the application will be delivered as an executable `jar` file that can be started by writing commands in a Unix terminal. To ease the whole process, some shell scripts might be added as well.

###5 Testing and evaluation
Among others, one of our main goal is to ensure that the data from the disparate systems is processed error free and is of good quality to perform analysis. As part of testing phase, both functional and non-functional testing would be performed. Functional testing involves validation of MapReduce jobs, structured and unstructured data, data storage and data quality assurance. Non-functional testing includes performance testing and failover testing.

Prior to any data loading or model deployment to the server, we will perform a smoke test on the environment by processing a simple MapReduce and Hive jobs to ensure everything works as expected.

Since the project involves open data from numerous data providers, the data will be preprocessed before its loaded into HDFS. Preprocessing generally involves various task but our main focus would be on selection, splitting and joining of attributes where necessary. For the initial data load to HDFS, we would ensure that all files are correctly loaded and are split, moved and replicated to different data nodes as part of our initial testing during the data load. After loading the data to the HDFS, we will populate the respective HIVE tables. A major portion of our testing phase will be dedicated to HIVE tables since the entire query performance depends on how well the tables are structured and indexed.

We will also carry on stress testing on our system by performing full data load activity to ensure that the developed system is efficient to load high volume of data at an acceptable time without failure.

###6 Summary and Timeline

As indicated above, we expect a positive outcome of the project and a working solution within the time limits. We have come up with a basic timeline along with major tasks to be performed. Please note that we intend to review this timeline on a weekly basis and therefore what we have now is just a preliminary solution.

#### 6.1 Timeline

Tasks to be performed were divided into five groups, or "sprints" in Agile terminology, which in our case should be completed in a week. Therefore, we expect to complete a whole project in five weeks.
Detailed plan is [available online](https://github.com/lchsk/big-data-project-comp530/issues) and will be updated when needed.

##### Week 1

[Loading data set #1 (OS Terrain 50)](https://github.com/lchsk/big-data-project-comp530/issues/3)

[Create unit tests for loading data](https://github.com/lchsk/big-data-project-comp530/issues/13)

##### Week 2

[Loading data set #2 (York Archaeological Data Service grey literature)](https://github.com/lchsk/big-data-project-comp530/issues/4)

[Loading data set #3 (English Heritage)](https://github.com/lchsk/big-data-project-comp530/issues/5)

[Loading data set #4 (British Geological Survey)](https://github.com/lchsk/big-data-project-comp530/issues/6)

[Run an R script from Hadoop/Hive](https://github.com/lchsk/big-data-project-comp530/issues/7)

##### Week 3

[Implement statistical model #1 (linear regression) ](https://github.com/lchsk/big-data-project-comp530/issues/8)

[Implement statistical model #2 (logistic regression)](https://github.com/lchsk/big-data-project-comp530/issues/9)

[Obtain prediction results by running a script](https://github.com/lchsk/big-data-project-comp530/issues/10)

[Evaluate prediction results](https://github.com/lchsk/big-data-project-comp530/issues/11)

[Create a web application for visualisation](https://github.com/lchsk/big-data-project-comp530/issues/14)

##### Week 4

[Visualise results of prediction phase on a map and charts](https://github.com/lchsk/big-data-project-comp530/issues/15)

[Enhance statistical model to obtain best results](https://github.com/lchsk/big-data-project-comp530/issues/16)

##### Week 5

[Create geo-tagged TIFF images](https://github.com/lchsk/big-data-project-comp530/issues/12)

[Prepare execution scripts](https://github.com/lchsk/big-data-project-comp530/issues/17)

[Prepare demonstration](https://github.com/lchsk/big-data-project-comp530/issues/18)
