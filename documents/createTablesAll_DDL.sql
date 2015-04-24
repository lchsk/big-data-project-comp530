use bigDataGroupProject;

--drop table OSGB36MainEventDimension;
--drop table OSGB36Facts;
--drop table AreaDetailsDimension;
--drop table  NGRDimension ;
--drop table  SuperFicialThickness;
--drop table artfbdrmmdimension;
--drop table faultslandformsdimension;

CREATE HADOOP TABLE 
OSGB36MainEventDimension 
( EventID int,  
Name varchar(90), 
Shape float , 
StartDate TIMESTAMP, 
ExpireDate TIMESTAMP
) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';


CREATE HADOOP TABLE 
OSGB36Facts 
( FactID int,  
NGRCode varchar(12), 
EventCode int , 
StartTime TIMESTAMP, 
EndTime TIMESTAMP,
CaptaScale float,
Easting float,
Northing float,
latitude float,
longitude float  
) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';

CREATE HADOOP TABLE 
faultslandformsdimension
( FaultID int,  
  category varchar(20),
Feature varchar(30) , 
FeatureDesc varchar(100), 
NomScale float,
NomOsYear timestamp,
NomBGSYear timestamp,
MSLink    int,
Easting float,
Northing float,
latitude float,
longitude float  
)ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';

CREATE HADOOP TABLE 
AreaDetailsDimension 
( PostCode varchar(10),  
NGRCode varchar(12), 
CountyCode varchar(4) , 
CountyName varchar(30), 
DistrictCode varchar(10),
DistrictName varchar(30),
Easting float,
Northing float,
latitude float,
longitude float  
) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';

CREATE HADOOP TABLE 
NGRDimension 
(
NGRCode varchar(12), 
NGRName varchar(20) , 
NGRPrefix varchar(2) 
) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';

CREATE HADOOP TABLE 
SuperFicialThickness 
( SFTid int, 
LEX    varchar(10),
LEX_D    varchar(30),
LEX_RCS    varchar(20),
RCS    varchar(20),
RCS_X    varchar(10),
RCS_D    varchar(30),
RANK    varchar(30),
MAX_TIME_D    varchar(20),
MIN_TIME_D    varchar(20),
MAX_TIME_Y    double,
MIN_TIME_Y    double,
MAX_INDEX    double,
MIN_INDEX    double,
MAX_AGE    varchar(30),
MIN_AGE    varchar(30),
MAX_EPOCH    varchar(30),
MIN_EPOCH    varchar(30),
MAX_PERIOD    varchar(20),
MIN_PERIOD    varchar(20),
MAX_ERA    varchar(20),
MIN_ERA    varchar(20),
MAX_EON    varchar(20),
MIN_EON    varchar(20),
PREV_NAME    varchar(30),
BGSTYPE    varchar(20),
LEX_RCS_I    varchar(30),
LEX_RCS_D    varchar(50),
BGSREF    int,
BGSREF_LEX    int,
BGSREF_FM    int,
BGSREF_GP    int,
BGSREF_RK    int,
MSLINK    int,
LEX_ROCK varchar(20),    
Easting float,
Northing float,
latitude float,
longitude float  
) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';


CREATE HADOOP TABLE 
artfbdrmmdimension 
( MMId int,
category varchar(20), 
LEX    varchar(10),
LEX_D    varchar(30),
LEX_RCS    varchar(20),
RCS    varchar(20),
RCS_X    varchar(10),
RCS_D    varchar(30),
RANK    varchar(30),
BED_EQ    varchar(20),
BED_EQ_D varchar(20),
MB_EQ    varchar(20),
MB_EQ_D    varchar(20),
FM_EQ    varchar(5),
FM_EQ_D    varchar(20),
SUBGP_EQ varchar(20),    
SUBGP_EQ_D    varchar(20),
GP_EQ    varchar(10),
GP_EQ_D    varchar(30),
SUPGP_EQ    varchar(10),    
SUPGP_EQ_D    varchar(30),
MAX_TIME_D    varchar(20),
MIN_TIME_D    varchar(20),
MAX_TIME_Y    double,
MIN_TIME_Y    double,    
MAX_INDEX    double,
MIN_INDEX    double,
MAX_AGE    varchar(30),
MIN_AGE    varchar(30),
MAX_EPOCH    varchar(30),
MIN_EPOCH    varchar(30),
MAX_PERIOD    varchar(20),
MIN_PERIOD    varchar(20),
MAX_ERA    varchar(20),
MIN_ERA    varchar(20),
MAX_EON    varchar(20),
MIN_EON    varchar(20),
PREV_NAME    varchar(30),
BGSTYPE    varchar(20),
LEX_RCS_I    varchar(30),
LEX_RCS_D    varchar(50),
BGSREF    int,
BGSREF_LEX    int,
BGSREF_FM    int,
BGSREF_GP    int,
BGSREF_RK    int,
MSLINK    int,
LEX_ROCK varchar(20),    
NomScale float,
NomOsYear timestamp,
NomBGSYear timestamp,
Easting float,
Northing float,
latitude float,
longitude float  
) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';