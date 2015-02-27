#Big Data Project 
####Requirements Specification

Version 0.5.0

*Created: 17/02/2015, Last Updated: 27/02/2015*

#1 Project overview
This project is being undertaken to solve  problem for ‘Democrata’ who wish to create an online resource that will rationalise the determination of environmental impact when  performing risk assessment in a variety of projects. More specifically the risk of finding archaeological artefacts at sites where major constructions may take place. When found, these artefacts bring construction projects to a halt and take time and money to be investigated. The project aims to create a predictive model using various forms of open data to inform planners/civil engineers of the likelihood that there is unknown archaeology at a a site they may want want to build at.

#2 Product Description

## 2.1 Statement of Deliverables
In conjunction with the system its self, we will provide an introduction and an explanation of the system as well as user guides on how to use the system. Using IBM’s Big Insights package as well as data from open sources about the UK’s historical archaeological finds, landscape features, UK historical sites (battlegrounds, settlements etc) and geological data we will create a system whereby the data is entered into the multi-core high performance computing facilities at Hartree where it is distributed and evaluated using some statistical model which would give us a percentage likelihood of finding an artefact at any position in the UK. 

The data will be prepared and then stored using ‘Big SQL’ as part of the ‘Big Insights’ package and then evaluated in ‘Big R’ using the predictive model we create (the data will be distributed and executed in parallel using Hadoop and Map Reduce within the Big Insights package). This will then output likelihood values for each location in the UK which can be visualised in the ‘Virtalis Geovisionary’ package. 

An evaluation of the predictive model and the system as a whole is needed to see if the system is a success and if it is, how accurate is the system? To do this we will create test cases on location where will already know historical artefacts have been found in the UK and locations where no artefacts have been found and see if the likelihoods correspond as they should. 

We can also look for recent data on archaeological finds that may not be in our data set as they are too recent and see if the locations these artefacts were found in had a high likelihood in our model. Also we will have a discussion on possible improvements and the next step to taking the project to the next level. We will also demonstrate the system and its uses at the Hartree Facility using their 3D visualisation suite.

##2.2 Product Context
Software described in this document depends only on technology listed in section 2.4 (i.e., does not need any external products to run). 

##2.2 User Characteristics
- User is not expected to have any knowledge about the prediction process.
- Outcome of the prediction should be visualized and easy to browse.

##2.3 Constraints
Data used for prediction can only come from non-commercial sources.

##2.4 Dependencies
Software that will be created is dependent upon:
- IBM Big Insights framework for running the prediction process in the Big Data cluster.
- GeoTIFF files for visualization
- Scripts in R language for prediction

##2.5 Project Conduct
We will carry out background research into various topics where we have little knowledge such as Big SQL, Big R, Big Insights, Virtalis Geovisionary as well as statistical model we use in the project. 

#3 Requirements

**Priority Definitions**

**Priority 1**  The requirement is a "must have"

**Priority 2**  The requirement is needed for improved processing, and the fulfillment of the requirement will create immediate benefits

**Priority 3**  The requirement is a "nice to have"  which may include new functionality

##3.1 Functional Requirements
Abbreviations used for requirements symbols are explained in the Appendix section 1.

|Req #|Requirement|Comments|Priority|
|-----|----------|-----|-----|-----|
|BR_PR_01|The system should use a statistical model for predicting the likelihood.||1|
|BR_PR_02|The statistical model should be programmed in R.||2|
|BR_DA_01|The software must understand the format of the input data and be able to load it.||1|
|BR_PR_03|The system must be able to run the prediction in the cluster infrastructure.||1|
|BR_VI_01|The output of the system should be visualized using technology provided by the Hartree Centre.||1|
|BR_VI_02|For testing purposes, the output must also be visualized on local machines used for development.||1|
|BR_PR_04|Prediction should be easy to run (using e.g. an online page).||2|
|BR_PR_05|Prediction phase should finish in reasonable time (e.g. in less than 10 minutes).||2|
|BR_PR_06|Prediction phase should finish in short time (e.g. less than a minute).||3|
|BR_PR_07|The system should offer more than one statistical model for prediction. ||3|
|BR_PR_08|The system should offer a way of comparing different statistical models (visually).||3|
|BR_DA_02|The system must only use open data.||1|

#4 Conduct of the Project

##4.1 Preparation phase

This begins next stages of the project, necessary steps need to be undertaken. First of all, a thorough research needs to be carried out on issues and technology, vital for the project. There are several crucial areas which must be understood to successfully advance to the next stage.

|Issue|Related requirement|
|---|---|
|Collection of data from open sources|BR_DA_02|
|Analysis of the collected data:<br />- understanding the format of the data and how it can be easily used<br />- data conversion may also be required to consolidate the data to a unified structure<br />- data cleaning task may be required to support quality analysis<br />- learning whether addition preprocessing is required|BR_DA_01|
|Learning and choosing the statistical model used for prediction|BR_PR_01|
|Learning the R language that should be used for implementing the prediction process|BR_PR_02|
|Learning about Big Insights technology|BR_PR_03|


##4.2 Design phase
This document should serve as the basis for creating design documentation. Requirements from section 3 should be broken into smaller tasks, described and assigned to a responsible member of the team. For that, "Issues" feature of the Github can be used. Then, those smaller tasks will be divided into separate periods of time and after completion of each part, a summary of completed work will be possible.  In case of any problems arising, we'll be able to adjust our tasks.

For specifying time limits of the project, Gantt chart will be created. 

##4.3 Implementation phase
The outcome of the prediction should be visualized on large screens provided by the Hartree Centre, but for testing purposes, visualization will be available on development machines.
Testing will be performed by the members of the team, possibly creating unit tests of parts of the system.

##4.4 Risk assessment
Major challenges for the team include:
- creating a valid statistical model
- learning and using new technologies (Big Insights, R)
- running created system on hardware not used previously (ie., Big Data system at Hartree)

#5 Appendix
##5.1 Definitions, Acronyms, and Abbreviations
__BR__ - Business Requirements

__PR__ - Prediction

__DA__ - Data

__VI__ - Visualisation