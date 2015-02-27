#Big Data Project 
####Requirements Specification

Version 0.5.0

*Created: 17/02/2015, Last Updated: 27/02/2015*

#1 Project overview
This project is intended to help inform of the likelihood of the archeological discovery anywhere in England. It could be used by both planners wishing to mitigate the risk involved with discovering archeological sites and those looking for a specific place of value. It should use a statistical model for the prediction. Outcome of the prediction phase should be visualized using infrastructure provided by the Hartree Centre. Prediction software will use IBM's Big Insights framework.
Project uses only open data.

#2 Product Description
The purpose of creating software described in this document is to assist people involved in planning construction sites, new buildings, roads, etc.in making decisions. There is a significant cost related to finding valuable archeological objects underground. When that happens, excavations must take place which causes the construction to be stalled, thereby resulting in an increase in the overall cost. Therefore, the aim is to use statistical model to predict the likelihood of finding objects underground and mitigate the risk as much as possible. There is relevant open data available which can help in the process. To speed up the whole process, cluster infrastructure may be used and then visualized on a map, so that no specific knowledge is needed.

##2.1 Product Context
Software described in this document depends only on technology listed in section 2.4 (i.e., does not need any external products to run). 

##2.2 User Characteristics
User is not expected to have any knowledge about the prediction process.
Outcome of the prediction should be visualized and easy to browse.

##2.3 Constraints
Data used for prediction can only come from non-commercial sources.

##2.4 Dependencies
Software that will be created is dependent upon:
- IBM Big Insights framework for running the prediction process in the Big Data cluster.
- GeoTIFF files for visualization
- Scripts in R language for prediction

#3 Requirements

**Priority Definitions**

**Priority 1**  The requirement is a “must have” as outlined by policy/law

**Priority 2**  The requirement is needed for improved processing, and the fulfillment of the requirement will create immediate benefits

**Priority 3**  The requirement is a “nice to have”  which may include new functionality

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
|Understanding the format of the data and how it can be easily used|BR_DA_01|
|Learning and choosing the statistical model used for prediction|BR_PR_01|
|Learning the R language that should be used for implementing the prediction process|BR_PR_02|
|Learning about Big Insights technology|BR_PR_03|


##4.2 Design phase
This document should serve as the basis for creating design documentation. Requirements from section 3 should be broken into smaller tasks, described and assigned to a responsible member of the team. For that, "Issues" feature of the Github can be used. For specifying time limits of the project, Gantt chart will be created. 

##4.3 Implementation phase
The outcome of the prediction should be visualized on the large screens, but for testing purposes, visualization will be available on development machines.
Testing will be performed by the members of the team, possibly utilizing unit tests of parts of the system.

#5 Appendix
##5.1 Definitions, Acronyms, and Abbreviations
__BR__ - Business Requirements

__PR__ - Prediction

__DA__ - Data

__VI__ - Visualization