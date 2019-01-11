-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 17, 2018 at 08:17 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bcmanagementsystemdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FName` varchar(50) NOT NULL,
  `LName` varchar(50) NOT NULL,
  `DOB` varchar(10) NOT NULL,
  `Cellnr` varchar(10) NOT NULL,
  `Location` varchar(50) NOT NULL,
  `Department` varchar(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`ID`, `FName`, `LName`, `DOB`, `Cellnr`, `Location`, `Department`, `Username`, `Password`) VALUES
(1, 'Christian', 'Steinmann', '2018-10-02', '0764434345', 'Pretoria', 'Admin', 'c.steinmann', 'jack123'),
(2, 'Adam', 'Driver', '2018-10-02', '0765832145', 'Pretoria', 'Pending', 'a.driver', 'jack123'),
(5, 'Mark', 'Arrow', '1996-05-24', '0763215964', 'Pretoria', 'Staff', 'm.arrow', 'admin'),
(6, 'Adam', 'Smith', '1992-10-25', '0365896541', 'Pretoria', 'Pending', 'a.smith', 'admin'),
(7, 'Shawn', 'Blake', '2018-10-09', '0369854123', 'Pretoria', 'Staff', 's.blake', 'awe');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(50) NOT NULL,
  `CategoryName` varchar(50) NOT NULL,
  `Model` varchar(50) NOT NULL,
  `Date of Entry` varchar(10) NOT NULL,
  `Price` double NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ID`, `ProductName`, `CategoryName`, `Model`, `Date of Entry`, `Price`) VALUES
(1, 'Stamps_Kitty', 'Stamps', 'N/A', '2018/10/12', 1.25),
(2, 'Stamps_Doggie', 'Stamps', 'N/A', '2018/10/12', 1.25),
(3, 'Pencil_0.7m', 'Writing', 'N/A', '2018/10/12', 5.5),
(4, 'Paper_A4', 'Paper', 'Pack #1', '2018/10/12', 99.55);

-- --------------------------------------------------------

--
-- Table structure for table `purchaseorder`
--

DROP TABLE IF EXISTS `purchaseorder`;
CREATE TABLE IF NOT EXISTS `purchaseorder` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductID` int(11) NOT NULL,
  `PersonID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `TotalPrice` double NOT NULL,
  `Status` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ProductID` (`ProductID`),
  UNIQUE KEY `PersonID` (`PersonID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchaseorder`
--

INSERT INTO `purchaseorder` (`ID`, `ProductID`, `PersonID`, `Quantity`, `TotalPrice`, `Status`) VALUES
(1, 4, 5, 50, 4977.5, 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ProductID` (`ProductID`),
  UNIQUE KEY `ProductID_2` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`ID`, `ProductID`, `Quantity`) VALUES
(1, 1, 50),
(2, 4, 200),
(3, 2, 50),
(4, 3, 50);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `purchaseorder`
--
ALTER TABLE `purchaseorder`
  ADD CONSTRAINT `purchaseorder_ibfk_1` FOREIGN KEY (`PersonID`) REFERENCES `person` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `purchaseorder_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
