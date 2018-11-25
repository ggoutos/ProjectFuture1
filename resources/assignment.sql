#CREATES

CREATE TABLE `owner` (
    `ID` INTEGER NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(15) NOT NULL,
    `Surname` VARCHAR(15) NOT NULL,
    CONSTRAINT `PK_Owner` PRIMARY KEY (`ID`)
);

CREATE TABLE `vehicle` (
    `ID` INTEGER NOT NULL AUTO_INCREMENT,
    `OwnerID` INTEGER NOT NULL,
    `Plate` VARCHAR(8) NOT NULL,
	`ExpireDate` DATE,
    CONSTRAINT `PK_vehicle` PRIMARY KEY (`ID`)
);

ALTER TABLE `vehicle` ADD CONSTRAINT `FK_Vehicle` 
    FOREIGN KEY (`OwnerID`) REFERENCES `owner` (`ID`);


#INSERTS

INSERT INTO owner VALUES(null, 'George','Goutos');
INSERT INTO owner VALUES(null, 'Kostas','Xristodoulou');
INSERT INTO owner VALUES(null, 'Nikos','Papamanolis');
	
INSERT INTO vehicle VALUES(null, 1, 'YXM-8000', '2018-09-19');
INSERT INTO vehicle VALUES(null, 1, 'ABB-0939', '2018-12-01');
INSERT INTO vehicle VALUES(null, 2, 'AAB-0004', '2019-09-19');
INSERT INTO vehicle VALUES(null, 3, 'AAB-0001', '2019-09-19');


#SELECTS

select * from vehicle
join owner
on vehicle.OwnerID =  owner.ID

