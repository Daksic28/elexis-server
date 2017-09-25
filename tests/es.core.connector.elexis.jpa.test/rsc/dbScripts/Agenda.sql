CREATE TABLE `AGNTERMINE`
(
  `ID` varchar(127) NOT NULL,
  `lastupdate` bigint(20) DEFAULT NULL,
  `PatID` varchar(80) DEFAULT NULL,
  `Bereich` varchar(25) DEFAULT NULL,
  `Tag` char(8) DEFAULT NULL,
  `Beginn` char(4) DEFAULT NULL,
  `Dauer` char(4) DEFAULT NULL,
  `Grund` longtext,
  `StatusHistory` longtext,
  `TerminTyp` varchar(50) DEFAULT NULL,
  `TerminStatus` varchar(50) DEFAULT NULL,
  `ErstelltVon` varchar(25) DEFAULT NULL,
  `Angelegt` varchar(10) DEFAULT NULL,
  `lastedit` varchar(10) DEFAULT NULL,
  `PalmID` int(11) DEFAULT '0',
  `flags` varchar(10) DEFAULT NULL,
  `deleted` char(2) DEFAULT '0',
  `Extension` longtext,
  `linkgroup` varchar(50) DEFAULT NULL,
  `priority` char(1) DEFAULT NULL,
  `caseType` char(1) DEFAULT NULL,
  `insuranceType` char(1) DEFAULT NULL,
  `treatmentReason` char(1) DEFAULT NULL
);

CREATE INDEX it on AGNTERMINE (Tag,Beginn,Bereich);
CREATE INDEX pattern on AGNTERMINE (PatID);
CREATE INDEX agnbereich on AGNTERMINE (Bereich);
INSERT INTO AGNTERMINE (ID, PatId) VALUES (1, '');

INSERT INTO `agntermine` VALUES ('db53461227956f74f012',1400066773685,NULL,'Praxis','20140514','0','480',NULL,'23334446;geplant','gesperrt','-',NULL,'23334446','23334446',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('fb76a641cd860db4f013',1400066773688,NULL,'Praxis','20140514','1080','359',NULL,'23334446;geplant','gesperrt','-',NULL,'23334446','23334446',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('x550af987275f379901070',1479133043762,NULL,'Praxis','20161114','0','480',NULL,'24652217;geplant','gesperrt','-',NULL,'24652217','24652217',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('c6ce24cd102cd1f9a01071',1479133043762,NULL,'Praxis','20161114','1080','359',NULL,'24652217;geplant','gesperrt','-',NULL,'24652217','24652217',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('b775d86626092ef9c01073',1479133043762,NULL,'Arzt 1','20161114','0','480',NULL,'24652217;geplant','gesperrt','-',NULL,'24652217','24652217',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('n6e43c787a606279d01074',1479133043762,NULL,'Arzt 1','20161114','1080','359',NULL,'24652217;geplant','gesperrt','-',NULL,'24652217','24652217',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('g82ca6d2f61f50779014',1479134686278,NULL,'Claudia Einstein','20161114','0','480',NULL,'24652244;geplant','gesperrt','-',NULL,'24652244','24652244',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('h591af11dca24c326015',1479134686278,NULL,'Claudia Einstein','20161114','1080','359',NULL,'24652244;geplant','gesperrt','-',NULL,'24652244','24652244',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('Af322a333db4daf37093177',1483615525111,'OSME US li.P1\r\n','Michael Wildhauser','20170111','450','90','Peppito, Spezeguti','24680284;geplant\n24692927;Normal','OP','-','zgr','24680284','24726925',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('R1c4d165983572054093183',1481575608750,' \n','Kiril Checkov','20161228','420','840',NULL,'24680284;geplant\n24692926;Normal','Notfall','Normal',NULL,'24680284','24692926',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('Z3998aa29d2decc5a093189',1481575608813,' \n','Felix Toggenburger','20161228','420','120',NULL,'24680284;geplant\n24690300;Normal\n24692926;Normal','Notfall','Normal',NULL,'24680284','24692926',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('I59e5d0b9401ec850093195',1482941622992,' \n','Jürg Flamenco','20161228','420','840',NULL,'24680284;geplant\n24692926;Normal','Notfall','Normal',NULL,'24680284','24715693',0,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('w51bcdfb9efd93c86093201',1481575609016,' \n','Patricia Breit','20161228','900','360',NULL,'24680284;geplant\n24690300;Normal\n24692926;Normal','Notfall','Normal',NULL,'24680284','24692926',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('o497232fd1c63581e093207',1481575608766,' \n','Thomas Schellenberg','20161228','420','840',NULL,'24680284;geplant\n24692926;Normal','Notfall','Normal',NULL,'24680284','24692926',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('f6108177d5baefc54093213',1481575608828,' \n','Jonathan Elexianer','20161228','420','360',NULL,'24680284;geplant\n24690300;Normal\n24692926;Normal','Notfall','Normal',NULL,'24680284','24692926',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('y12017dcdb37ffc4a093219',1482941620647,' \n','Julian Marti','20161228','420','840',NULL,'24680284;geplant\n24692926;Normal','Notfall','Normal',NULL,'24680284','24715693',0,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('m357875164f027050093225',1481575608781,NULL,'Aldo Fischer','20161228','420','840',NULL,'24680284;geplant\n24692926;Normal','Notfall','Normal',NULL,'24680284','24692926',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('D3eeb6d9398e45029093231',1481575608922,' \n','Michael Wildhauser','20161228','480','540',NULL,'24680284;geplant\n24692926;Normal','Notfall','Normal',NULL,'24680284','24692926',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('G525443f7556debe1093237',1481575609078,' \n','Kiril Checkov','20161229','420','840',NULL,'24680284;geplant\n24692926;Normal','Notfall','Normal',NULL,'24680284','24692926',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('J5e3c133557e14786093243',1481575609078,' \n','Felix Toggenburger','20161229','420','840',NULL,'24680284;geplant\n24692926;Normal','Notfall','Normal',NULL,'24680284','24692926',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('P509ed00e3e240bbc093249',1482508654991,' \n','Jürg Flamenco','20161229','420','840',NULL,'24680284;geplant','normal','Normal',NULL,'24680284','24708477',0,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('z5791ab7293ec53b5093255',1481575609125,' \n','Patricia Breit','20161229','420','120',NULL,'24680284;geplant\n24690300;Normal\n24692926;Normal','Notfall','Normal',NULL,'24680284','24692926',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('se4bf3d3fa547776b093261',1481575609094,' \n','Thomas Schellenberg','20161229','420','840',NULL,'24680284;geplant\n24692926;Normal','Notfall','Normal',NULL,'24680284','24692926',0,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `agntermine` VALUES ('k5cec773d2c193b71093267',1482508955163,' \n','Jonathan Elexianer','20161229','420','840',NULL,'24680284;geplant','normal','Normal',NULL,'24680284','24708482',0,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL);