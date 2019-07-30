-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 04, 2018 at 06:52 AM
-- Server version: 5.6.11
-- PHP Version: 5.5.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `institute_management`
--
CREATE DATABASE IF NOT EXISTS `institute_management` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `institute_management`;

-- --------------------------------------------------------

--
-- Table structure for table `admission`
--

CREATE TABLE IF NOT EXISTS `admission` (
  `s_id` int(20) DEFAULT NULL,
  `payment_date` varchar(20) DEFAULT NULL,
  `payment_amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `advance_payments`
--

CREATE TABLE IF NOT EXISTS `advance_payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lec_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  `status` varchar(20) NOT NULL,
  `settle_date` date DEFAULT NULL,
  `settled_payment_month` varchar(20) DEFAULT NULL COMMENT 'advanced payment setteled by which month payment',
  `settled_payment_year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `attendence`
--

CREATE TABLE IF NOT EXISTS `attendence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL,
  `c_id` varchar(50) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `date` int(11) DEFAULT NULL,
  `day` varchar(10) DEFAULT NULL,
  `time` varchar(10) DEFAULT NULL,
  `attendence` tinyint(1) DEFAULT '0',
  `completeDate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `course_id` varchar(50) NOT NULL,
  `course_description` varchar(200) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `lecturer_id` int(11) NOT NULL,
  `total_course_fee` double NOT NULL,
  `monthly_fee` double NOT NULL,
  `course_duration` int(11) NOT NULL,
  `lecture_hole_id` int(11) NOT NULL DEFAULT '0',
  `grade` varchar(20) NOT NULL,
  `class_type` varchar(20) NOT NULL,
  `medium` varchar(20) NOT NULL DEFAULT 'Sinhala',
  `batch_number` int(11) NOT NULL,
  `lecturer_payment_precentage` double NOT NULL DEFAULT '90',
  PRIMARY KEY (`course_id`),
  KEY `lecture_hole_id` (`lecture_hole_id`),
  KEY `subject_id` (`subject_id`),
  KEY `lecture_id` (`lecturer_id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `courses_dates`
--

CREATE TABLE IF NOT EXISTS `courses_dates` (
  `course_id` varchar(50) NOT NULL,
  `monday` varchar(20) DEFAULT NULL,
  `tuesday` varchar(20) DEFAULT NULL,
  `wednesday` varchar(20) DEFAULT NULL,
  `thursday` varchar(20) DEFAULT NULL,
  `friday` varchar(20) DEFAULT NULL,
  `saturday` varchar(30) DEFAULT NULL,
  `sunday` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `expences`
--

CREATE TABLE IF NOT EXISTS `expences` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,0) NOT NULL,
  `expence_date` varchar(200) NOT NULL,
  `expence_type` varchar(200) NOT NULL,
  `remark` varchar(2000) NOT NULL,
  `inserted_user` int(11) DEFAULT NULL,
  `creation_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `expences`
--

INSERT INTO `expences` (`id`, `amount`, `expence_date`, `expence_type`, `remark`, `inserted_user`, `creation_date`) VALUES
(1, '2345', 'MMM d, yyyy', 'item 1', 'rest', NULL, '2018-03-04'),
(2, '2345', 'MMM d, yyyy', 'item 1', 'rest', NULL, '2018-03-04'),
(3, '4555', 'MMM d, yyyy', 'item 1', 'dfdff', NULL, '2018-03-04'),
(4, '23', 'MMM d, yyyy', 'item 1', 'edffeff', NULL, '2018-03-04'),
(5, '23', 'MMM d, yyyy', 'item 2', 'edffeff', NULL, '2018-03-04'),
(6, '4324423', 'Fri Mar 02 10:59:51 IST 2018', 'item 1', '234r3423', NULL, '2018-03-04'),
(7, '3443', '2018-Mar-81', 'dfgdfgs', 'dfdfvdf', NULL, '2018-03-04');

-- --------------------------------------------------------

--
-- Table structure for table `expences_types`
--

CREATE TABLE IF NOT EXISTS `expences_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expence_type` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `expences_types`
--

INSERT INTO `expences_types` (`id`, `expence_type`) VALUES
(1, 'item 1'),
(2, 'item 2'),
(3, 'dfgdfgs');

-- --------------------------------------------------------

--
-- Table structure for table `extraclasses`
--

CREATE TABLE IF NOT EXISTS `extraclasses` (
  `courseID` varchar(300) NOT NULL,
  `date` varchar(30) NOT NULL,
  `startTime` varchar(30) NOT NULL,
  `endTime` varchar(30) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`courseID`,`date`,`startTime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lecturer`
--

CREATE TABLE IF NOT EXISTS `lecturer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nic` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `subject_code` varchar(100) NOT NULL,
  `email` varchar(40) NOT NULL,
  `contact` varchar(11) NOT NULL,
  `gender` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `regDate` date DEFAULT NULL,
  `firstName` varchar(100) NOT NULL,
  `title` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lecturer_payments`
--

CREATE TABLE IF NOT EXISTS `lecturer_payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecturer_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  `payment_month` varchar(30) NOT NULL DEFAULT 'NO',
  `patment_year` int(11) NOT NULL,
  `course_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `page_details`
--

CREATE TABLE IF NOT EXISTS `page_details` (
  `page_id` int(11) NOT NULL AUTO_INCREMENT,
  `page_description` varchar(40) NOT NULL,
  PRIMARY KEY (`page_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `page_details`
--

INSERT INTO `page_details` (`page_id`, `page_description`) VALUES
(1, 'Main Page'),
(2, 'User Privileges Assigning Page'),
(3, 'Subject Management'),
(4, 'Student Management'),
(5, 'Lecturer Management'),
(6, 'Attendance Details'),
(7, 'Course Management'),
(8, 'Payment Management'),
(9, 'Report Module'),
(10, 'Email SMS Management');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE IF NOT EXISTS `payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` varchar(110) DEFAULT NULL,
  `course_id` varchar(110) DEFAULT NULL,
  `year` varchar(110) DEFAULT NULL,
  `month` varchar(25) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `paid_to_lecture` tinyint(1) NOT NULL DEFAULT '0',
  `payment_issue_date` date NOT NULL,
  `payment_issue_month` varchar(100) DEFAULT NULL,
  `payment_issue_year` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `privileges`
--

CREATE TABLE IF NOT EXISTS `privileges` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `page_id` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `role_id` (`role_id`),
  KEY `page_id` (`page_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=186 ;

--
-- Dumping data for table `privileges`
--

INSERT INTO `privileges` (`ID`, `role_id`, `page_id`) VALUES
(55, 4, 1),
(56, 4, 2),
(57, 4, 9),
(58, 4, 10),
(141, 2, 1),
(142, 2, 2),
(143, 2, 3),
(144, 2, 5),
(145, 2, 7),
(146, 2, 9),
(157, 3, 4),
(176, 1, 1),
(177, 1, 2),
(178, 1, 3),
(179, 1, 4),
(180, 1, 5),
(181, 1, 6),
(182, 1, 7),
(183, 1, 8),
(184, 1, 9),
(185, 1, 10);

-- --------------------------------------------------------

--
-- Table structure for table `school`
--

CREATE TABLE IF NOT EXISTS `school` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT,
  `school` varchar(100) NOT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=103 ;

--
-- Dumping data for table `school`
--

INSERT INTO `school` (`school_id`, `school`) VALUES
(1, 'Taxila Central College,Horana'),
(2, 'Wickramasheela Central College,Giriulla'),
(3, 'Maliyadewa College,Kurunagala'),
(4, 'ST.Annes,Kurunagala'),
(5, 'Kannangara'),
(6, 'P . G . S .  Dehiwala'),
(7, 'Mahanama  Colleg'),
(8, 'Girl s high school.'),
(9, 'Holy  famali Convent  Dehiwala'),
(10, 'Methodist College'),
(11, 'Wesley  College'),
(12, 'Sujatha  Vidyalaya'),
(13, 'St. Joseph''s College  -  Nugegoda'),
(15, 'Anula Vidyalaya -  Nugegoda'),
(16, 'St.  Thomas college -  Mt. Lavinia'),
(17, 'Alethea International School'),
(18, 'Science College'),
(19, 'Ladies College -  Colombo 7'),
(20, 'Lalith Athulathmudali College'),
(21, 'Buddist  girl''S  College'),
(22, 'Prince  Of Weles'' College'),
(23, 'Shasthrananda Nawodaya'),
(24, 'St. Sebastian''s College'),
(25, 'Muslim  Ladies  College'),
(26, 'Hejaaz  International'),
(27, 'Harcourts  int School'),
(28, 'Tharsten College'),
(29, 'Musaeus  College'),
(30, 'Isipathana  College'),
(31, 'Lindsay Balika Vidyalaya'),
(32, 'St. Mary''s College Dehiwala'),
(33, 'St.  Lawrence  Convent'),
(34, 'St.Anthony''s Balika'),
(35, 'St.Peters Wallawatte'),
(36, 'St. John''s college'),
(37, 'Ashoka vidyalaya'),
(38, 'Royal college'),
(39, 'Daybridge International School'),
(40, 'Colombo South International'),
(41, 'Lisoshay Girls'' School'),
(42, 'St.Brudget''s Convent'),
(43, 'Defence  Servicers  Collage'),
(44, 'President  Collage'),
(45, 'Nalanda Collage'),
(46, 'St. Pauls'' Milagiriya'),
(47, 'Najaa International School'),
(48, 'Mahanama  Collage'),
(49, 'Devi Balika Vidyalaya'),
(50, 'Ilma  International  School'),
(51, 'St. Anthony  Balika  Vidyalaya'),
(52, 'Arethusa College Colombo'),
(53, 'Jayasinghe Central College'),
(54, 'Boralasgamuwa Maha Vidyalaya'),
(55, 'D.S. Senanayaka College'),
(56, 'Carey College'),
(57, 'Louver International School'),
(58, 'Sirimawo Bandaranayake Vidyalaya'),
(59, 'Mahamaya Balika Vidyalaya'),
(60, 'Holy  Famaly Convent  Bambalapitiya'),
(61, 'Zahira College'),
(62, 'Yashodara Balika Vidyalaya'),
(63, 'St. Bridgest Convent'),
(64, 'Vidura College'),
(65, 'Sussey College Nugegoda'),
(66, 'St. Clare''s College'),
(67, 'Ananda College'),
(68, 'St Jhons College'),
(69, 'De Soyza Maha Vidyalaya'),
(70, 'Thurstan College'),
(71, 'Mahanama  Colleg . '),
(72, 'Vidyakara Balika Vidyalaya'),
(73, 'St. Benedicts College'),
(74, 'Lumbini College'),
(75, 'Ananda Balika Vidyalaya'),
(76, 'St. Sebastian''s Girls School'),
(77, 'Harcourts International School'),
(78, 'Princess of  Wales College'),
(79, 'Dehiwala Central College'),
(80, 'Amal International College'),
(81, 'Bishops College - Colombo 3'),
(82, 'Highlands College'),
(83, 'Buddist Ladies College - Colombo'),
(84, 'Royal Intitute Internationa School'),
(85, 'City High School'),
(86, 'Leeds Interanational School'),
(87, 'Fath Academy '),
(88, 'Girl s high school'),
(89, 'Sri Thaksala MahaVidyalaya'),
(90, 'Mahinda Rajapaksha College'),
(91, 'St.  Lawrence  SChool Wallawttha'),
(92, 'Davi  Balika Vidyalaya'),
(93, 'Rewards International School'),
(94, 'Krithudawa School  Dehiwela'),
(95, 'Hindu  College'),
(96, 'Visakha Vidyalaya'),
(97, 'Kothalawala pura Maha Vidyalaya'),
(98, 'Central College Dehiwela '),
(99, 'Somaweera Chandrasiri College - Piliyandala'),
(100, 'harishchandra Collage'),
(101, 'nuwara'),
(102, '--');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `S_NAME` varchar(100) DEFAULT '',
  `S_DOB` varchar(20) DEFAULT '',
  `S_ADDRESS` varchar(150) DEFAULT '',
  `S_EMAIL` varchar(50) DEFAULT '',
  `S_GENDER` varchar(10) DEFAULT '',
  `S_YOR` varchar(20) DEFAULT '',
  `S_TELEPHONE` varchar(10) DEFAULT '',
  `S_school` varchar(50) DEFAULT '',
  `S_ID` int(11) NOT NULL AUTO_INCREMENT,
  `S_PARENT_CONTACT_NO` varchar(10) DEFAULT '',
  `S_PARENT_EMAIL` varchar(50) DEFAULT '',
  `S_PARENT_NAME` varchar(100) DEFAULT '',
  `S_IMAGE` longblob,
  `S_NIC` varchar(20) DEFAULT '',
  `S_FIRSTNAME` varchar(100) DEFAULT NULL,
  `card_number` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`S_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `student-course`
--

CREATE TABLE IF NOT EXISTS `student-course` (
  `S_ID` int(11) NOT NULL,
  `course_id` varchar(300) NOT NULL,
  `registation_date` date NOT NULL,
  `cardType` int(11) NOT NULL COMMENT '0 -free card,1 -50% free,2 - normal card',
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`S_ID`,`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `SUBJECT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUBJECT_CODE` varchar(200) NOT NULL,
  `SUBJECT_NAME` varchar(200) NOT NULL,
  `SUBJECT_MEDIUM` varchar(32) NOT NULL,
  PRIMARY KEY (`SUBJECT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`SUBJECT_ID`, `SUBJECT_CODE`, `SUBJECT_NAME`, `SUBJECT_MEDIUM`) VALUES
(3, 'ACCOUNTING9977', 'Accounting', 'Sinhala'),
(4, 'BUSINESS STUDIES5011', 'Business Studies', 'Sinhala'),
(5, 'SINHALA6293', 'Sinhala', 'Sinhala'),
(6, 'MATHEMATICS9124', 'Mathematics', 'Sinhala'),
(7, 'SCIENCE9280', 'Science', 'Sinhala'),
(8, 'HISTORY8881', 'History', 'Sinhala'),
(9, 'ENGLISH5481', 'English', 'English'),
(10, 'BUDDHISM5733', 'Buddhism', 'Sinhala'),
(11, 'TAMIL  LANGUAGE746', 'Tamil  Language', 'Tamil'),
(13, 'ECONOMICS6046', 'Economics', 'Sinhala'),
(14, 'I . C . T7903', 'I . C . T', 'Sinhala'),
(15, 'DANCEING 2896', 'Danceing', 'Sinhala'),
(16, 'BS:ACC: STUDIES8463', 'Bs:Acc: Studies', 'Sinhala');

-- --------------------------------------------------------

--
-- Table structure for table `sys_users`
--

CREATE TABLE IF NOT EXISTS `sys_users` (
  `user_id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `nic` varchar(50) NOT NULL,
  `contact` varchar(20) NOT NULL,
  `address` varchar(300) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1007 ;

--
-- Dumping data for table `sys_users`
--

INSERT INTO `sys_users` (`user_id`, `name`, `nic`, `contact`, `address`, `gender`, `user_name`, `password`, `role_id`) VALUES
(1001, '', '', '', '', '', 'admin', '44', 1),
(1004, '', '', '', '', '', 'malinda', '123', 1),
(1005, 'tharindu', '124521', '564564656', 'test', 'Male', 'pro1', '123', 2),
(1006, 'test100', '123', '123', 'qwe', 'Male', 'jayalath', '123', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_role_id`, `description`) VALUES
(1, 'Super Admin'),
(2, 'Admin'),
(3, 'Manager'),
(4, 'User');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `privileges`
--
ALTER TABLE `privileges`
  ADD CONSTRAINT `page_if_fk` FOREIGN KEY (`page_id`) REFERENCES `page_details` (`page_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `role_id_FK` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`user_role_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
