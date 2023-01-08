


CREATE TABLE `bike` (
  `bikeCode` int(11) NOT NULL,
  `licensePlate` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `motor` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
  `valueOfBike` int(11) NOT NULL,
  `maxTime` int(11) NOT NULL,
  `remainBattery` int(11) NOT NULL,
  `dockID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `card` (
  `id` int(11) NOT NULL,
  `cardCode` varchar(15) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `cvvCode` varchar(3) NOT NULL,
  `dateExpired` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `dock` (
  `dockID` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `numOfBikes` int(11) NOT NULL,
  `address` varchar(45) NOT NULL,
  `area` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `totalAmount` int(11) NOT NULL,
  `rentID` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `paymenttransaction` (
  `id` int(11) NOT NULL,
  `createAt` datetime NOT NULL,
  `content` varchar(45) NOT NULL,
  `method` varchar(45) DEFAULT NULL,
  `cardId` int(11) NOT NULL,
  `invoiceId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `renttransaction` (
  `rentalCode` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `rentTime` varchar(45) NOT NULL,
  `depositeCost` int(11) NOT NULL,
  `returnTime` varchar(45) NOT NULL,
  `bikeCode` int(11) NOT NULL,
  `rentCost` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `bike`
  ADD PRIMARY KEY (`bikeCode`),
  ADD KEY `bikeCode` (`bikeCode`),
  ADD KEY `dockID` (`dockID`);

ALTER TABLE `card`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `dock`
  ADD PRIMARY KEY (`dockID`),
  ADD KEY `dockID` (`dockID`);


ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rentID` (`rentID`);


ALTER TABLE `paymenttransaction`
  ADD PRIMARY KEY (`id`,`cardId`,`invoiceId`),
  ADD KEY `invoiceId` (`invoiceId`),
  ADD KEY `cardId` (`cardId`);

ALTER TABLE `renttransaction`
  ADD PRIMARY KEY (`rentalCode`),
  ADD KEY `rentalCode` (`rentalCode`),
  ADD KEY `bikeCode` (`bikeCode`);

ALTER TABLE `card`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `dock`
  MODIFY `dockID` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `bike`
  ADD CONSTRAINT `bike_ibfk_1` FOREIGN KEY (`dockID`) REFERENCES `dock` (`dockID`);

ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`rentID`) REFERENCES `renttransaction` (`rentalCode`);

ALTER TABLE `paymenttransaction`
  ADD CONSTRAINT `paymenttransaction_ibfk_1` FOREIGN KEY (`invoiceId`) REFERENCES `invoice` (`id`),
  ADD CONSTRAINT `paymenttransaction_ibfk_2` FOREIGN KEY (`cardId`) REFERENCES `card` (`id`);

ALTER TABLE `renttransaction`
  ADD CONSTRAINT `renttransaction_ibfk_1` FOREIGN KEY (`bikeCode`) REFERENCES `bike` (`bikeCode`);
