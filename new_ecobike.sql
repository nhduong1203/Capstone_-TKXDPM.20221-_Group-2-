use new_ecobike;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE TABLE `bike` (
  `bikeCode` int(11) NOT NULL PRIMARY KEY,
  `licensePlate` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
--   `numOfPedal` int(11) NOT NULL,
  `valueOfBike` int(11) NOT NULL,
  `numOfSaddle` int(11) NOT NULL,
  `numOfSeat` int(11) NOT NULL,
  `dockID` int(11) NOT NULL,
  `bikeImage` varchar(45) NOT NULL
--   `remainBattery` int(11) NOT NULL DEFAULT 0,
--   `maxTime` int(11) NOT NULL DEFAULT 0,
--   `motor` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE singleBike (
  `bikeCode` int(11) NOT NULL PRIMARY KEY,
  `numOfPedal` int(11) NOT NULL,
  FOREIGN KEY (`bikeCode`) REFERENCES bike(`bikeCode`)
);

CREATE TABLE doubleBike (
  `bikeCode` int(11) NOT NULL PRIMARY KEY,
  `numOfPedal` int(11) NOT NULL,
  FOREIGN KEY (`bikeCode`) REFERENCES bike(`bikeCode`)
);

CREATE TABLE electricBike (
  `bikeCode` int(11) NOT NULL PRIMARY KEY,
  `remainBattery` int(11) NOT NULL DEFAULT 0,
  `maxTime` int(11) NOT NULL DEFAULT 0,
  `motor` varchar(45) DEFAULT NULL,
  FOREIGN KEY (`bikeCode`) REFERENCES bike(`bikeCode`)
);

select * from bike;
INSERT INTO `bike` VALUES (1,'30AB1000','Xe dap don','Xe mini 1',1,2000000,1,2,2,'assets/images/bike/xemini1.jpg'),(2,'30AB1001','Xe dap don','Xe đạp mini 2',1,2000000,1,2,2,'assets/images/bike/xemini2.jpg'),(3,'30AB1002','Xe dap don','Xe đạp mini ',1,2000000,1,2,2,'assets/images/bike/xemini3.jpg'),(4,'30AB1003','Xe dap don','Xe đạp mini Nhật',1,2000000,1,2,1,'assets/images/bike/xemini4.jpg'),(5,'30AB1010','Xe dap don','Xe đạp mini Vinfast',1,2000000,1,2,3,'assets/images/bike/xemini5.jpg'),(6,'30AB1100','Xe dap don','Xe đạp mini mini',1,2000000,1,2,3,'assets/images/bike/xemini6.jpg'),(7,'30AB1200','Xe dap doi','Xe đạp đôi',1,4000000,2,2,1,'assets/images/bike/xedapdoi1.jpg'),(8,'30AB1201','Xe dap doi','Xe đạp đôi Nhật Bổn',1,4000000,2,2,1,'assets/images/bike/xedapdoi2.jpg'),(9,'30AB1310','Xe dap doi','Xe đạp đôi Vinfast',1,4000000,2,2,3,'assets/images/bike/xedapdoi3.png'),(10,'30AB1122','Xe dap dien','Xe đạp điện Vinfast',1,15000000,1,2,1,'assets/images/bike/xedapdien1.jpg'),(11,'30AB1133','Xe dap dien','Xe đạp điện Nhật bản',1,15000000,1,2,2,'assets/images/bike/xedapdien2.jpg'),(12,'30AB1178','Xe dap dien','Xe đạp điện siêu lướt',1,15000000,1,2,3,'assets/images/bike/xedapdien3.jpg');
use new_ecobike;
INSERT INTO singleBike (bikeCode, numOfPedal) VALUES
(1, 2), (2,2), (3, 2), (4,2), (5,2), (6,2);

INSERT INTO doubleBike (bikeCode, numOfPedal) VALUES
(7,4), (8,4), (9,4);

INSERT INTO electricBike (bikeCode, remainBattery, maxTime, motor) VALUES
(10,70,2,'V15'), (11,65,3,'V12'), (12,80,4,'V18');


CREATE TABLE `card` (
  `id` int(11) NOT NULL,
  `cardCode` varchar(45) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `cvvCode` varchar(3) NOT NULL,
  `dateExpired` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `dock` (
  `dockID` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `area` varchar(45) NOT NULL,
  `dockImage` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Đang đổ dữ liệu cho bảng `dock`
INSERT INTO `dock` (`dockID`, `name`, `address`, `area`, `dockImage`) VALUES
(1, 'Bãi xe 1', 'Hai Bà Trưng, Hà Nội', '100', 'assets/images/dock/bachkhoa1.png'),
(2, 'Bãi xe 2', 'Cầu Giấy, Hà Nội', '100', 'assets/images/dock/bachkhoa2.jpg'),
(3, 'Bãi xe 3', 'Đống Đa, Hà Nội', '100', 'assets/images/dock/bachkhoa3.jpg');

-- --------------------------------------------------------

-- Cấu trúc bảng cho bảng `renttransaction`
CREATE TABLE `renttransaction` (
  `rentalCode` int(11) NOT NULL,
  `rentCardCode` varchar(45) NOT NULL,
  `rentTime` varchar(45) NOT NULL,
  `depositeCost` int(11) NOT NULL,
  `returnTime` varchar(45) DEFAULT NULL,
  `bikeCode` int(11) NOT NULL,
  `rentCost` int(11) NOT NULL DEFAULT 1000000
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `renttransaction`
INSERT INTO `renttransaction` (`rentalCode`, `rentCardCode`, `rentTime`, `depositeCost`, `returnTime`, `bikeCode`, `rentCost`) VALUES
(1, 'TTDuong', '11/02/2023 23:59', 800000, '11/02/2023 23:59', 1, 0),
(2, 'TTDuong', '12/02/2023 00:04', 800000, '12/02/2023 00:04', 1, 0),
(3, 'TTDuong', '21/02/2023 22:36', 800000, '21/02/2023 22:36', 1, 0),
(4, 'TTDuong', '22/02/2023 08:49', 800000, '22/02/2023 08:50', 1, 0),
(5, 'TTDuong', '22/02/2023 09:36', 800000, '22/02/2023 09:37', 1, 0),
(6, 'TTDuong', '22/02/2023 09:44', 1600000, '22/02/2023 09:44', 8, 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bike`
--
-- ALTER TABLE `bike`
--   ADD PRIMARY KEY (`bikeCode`),
--   ADD KEY `bikeCode` (`bikeCode`),
--   ADD KEY `dockID` (`dockID`);

--
-- Chỉ mục cho bảng `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cardCode` (`cardCode`),
  ADD UNIQUE KEY `cardCode_2` (`cardCode`);

--
-- Chỉ mục cho bảng `dock`
--
ALTER TABLE `dock`
  ADD PRIMARY KEY (`dockID`),
  ADD KEY `dockID` (`dockID`);

--
-- Chỉ mục cho bảng `renttransaction`
--
ALTER TABLE `renttransaction`
  ADD PRIMARY KEY (`rentalCode`),
  ADD KEY `rentalCode` (`rentalCode`),
  ADD KEY `bikeCode` (`bikeCode`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `card`
--
ALTER TABLE `card`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `dock`
--
ALTER TABLE `dock`
  MODIFY `dockID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bike`
--
ALTER TABLE `bike`
  ADD CONSTRAINT `bike_ibfk_1` FOREIGN KEY (`dockID`) REFERENCES `dock` (`dockID`);

--
-- Các ràng buộc cho bảng `renttransaction`
--
ALTER TABLE `renttransaction`
  ADD CONSTRAINT `renttransaction_ibfk_1` FOREIGN KEY (`bikeCode`) REFERENCES `bike` (`bikeCode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

select * from bike;
select * from singleBike;
select * from doubleBike;
select * from electricBike;

SELECT * FROM bike INNER JOIN singleBike ON bike.bikeCode = singleBike.bikeCode WHERE bike.type = 'Xe dap don';

SELECT * FROM bike WHERE status = 1 AND dockID = 1;

SELECT * FROM bike INNER JOIN singleBike ON bike.bikeCode = singleBike.bikeCode WHERE bike.bikeCode = 1
            

