create database DAVDB;

use DAVDB;

CREATE TABLE `employeet` (  
  `empid` int(11) NOT NULL AUTO_INCREMENT,  
  `empaddress` varchar(255) DEFAULT NULL,  
  `empAge` int(11) DEFAULT NULL,  
  `empname` varchar(255) DEFAULT NULL,  
  `salary` bigint(20) DEFAULT NULL,  
  PRIMARY KEY (`empid`)  
);  
  
 
INSERT INTO employeet VALUES  
 ('1','California','27','John Doe','340000'),
 ('2','Achalpur','33','Chris Benoitte','450000'),
 ('3','Wokingham','54','Michael Harford','600000'),
 ('4','Warwick','19','Deepak Kabra','110000'),
 ('5','Glassgow','40','John Kerr','320000'); 