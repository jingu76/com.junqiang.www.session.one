-- phpMyAdmin SQL Dump
-- version phpStudy 2014
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2017 年 07 月 16 日 18:44
-- 服务器版本: 5.5.36
-- PHP 版本: 5.3.28

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `book`
--

-- --------------------------------------------------------

--
-- 表的结构 `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `book_title` varchar(30) NOT NULL DEFAULT '',
  `isbn` varchar(20) NOT NULL DEFAULT '',
  `date_of_printing` varchar(20) DEFAULT NULL,
  `author` varchar(15) DEFAULT NULL,
  `press` varchar(15) DEFAULT NULL,
  `category` char(1) DEFAULT NULL,
  `unit_price` float DEFAULT NULL,
  PRIMARY KEY (`book_title`,`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `book`
--

INSERT INTO `book` (`book_title`, `isbn`, `date_of_printing`, `author`, `press`, `category`, `unit_price`) VALUES
('Effective JAVA', '1561315135213', '2008', 'Joshua Bloch', '工业出版社', 'A', 88);

-- --------------------------------------------------------

--
-- 表的结构 `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `class_id` varchar(32) DEFAULT '',
  `class_name` varchar(50) DEFAULT '',
  `year` varchar(20) DEFAULT NULL,
  `spec_name` varchar(15) DEFAULT NULL,
  `team_name` varchar(50) NOT NULL DEFAULT '未名团队',
  `comp_name` varchar(20) NOT NULL,
  `dept_name` varchar(20) NOT NULL,
  PRIMARY KEY (`team_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `class`
--

INSERT INTO `class` (`class_id`, `class_name`, `year`, `spec_name`, `team_name`, `comp_name`, `dept_name`) VALUES
('', '', NULL, NULL, '刘健团队', '大冶尖峰水泥有限公司', '研发部'),
('', '', NULL, NULL, '顾静军团队', '大冶尖峰水泥有限公司', '财务部');

-- --------------------------------------------------------

--
-- 表的结构 `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `course_title` varchar(30) NOT NULL DEFAULT '',
  `type` varchar(10) DEFAULT NULL,
  `credits` float DEFAULT NULL,
  `speciality` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`course_title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `course`
--

INSERT INTO `course` (`course_title`, `type`, `credits`, `speciality`) VALUES
('萨达是', '易耗品', 12, '财务部'),
('阿萨德才', '耐用型', 34, '浙江大学联合研究室');

-- --------------------------------------------------------

--
-- 表的结构 `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `department`
--

INSERT INTO `department` (`dept_id`, `dept_name`) VALUES
(4, '大冶尖峰水泥有限公司'),
(5, '云南尖峰水泥有限公司'),
(6, '浙江尖峰药业有限公司');

-- --------------------------------------------------------

--
-- 表的结构 `order_book`
--

CREATE TABLE IF NOT EXISTS `order_book` (
  `staff_id` varchar(30) NOT NULL DEFAULT '',
  `sec_id` int(11) NOT NULL DEFAULT '0',
  `book_title` varchar(30) NOT NULL DEFAULT '',
  `isbn` varchar(20) NOT NULL DEFAULT '',
  `remark` varchar(30) DEFAULT NULL,
  `approval` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`staff_id`,`sec_id`,`book_title`,`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `order_book`
--

INSERT INTO `order_book` (`staff_id`, `sec_id`, `book_title`, `isbn`, `remark`, `approval`) VALUES
('teacher', 1, 'Effective JAVA', '1561315135213', '不要二手', 1);

-- --------------------------------------------------------

--
-- 表的结构 `resource`
--

CREATE TABLE IF NOT EXISTS `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_resource_parent_id` (`parent_id`),
  KEY `idx_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `resource_ids` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_role_resource_ids` (`resource_ids`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`id`, `role`, `description`, `resource_ids`, `available`) VALUES
(1, 'admin', '管理员', '', 1),
(2, 'student', '学生', '', 1),
(3, 'teacher', '老师', '', 1),
(4, 'supplier', '供应商', '', 1);

-- --------------------------------------------------------

--
-- 表的结构 `section`
--

CREATE TABLE IF NOT EXISTS `section` (
  `sec_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_title` varchar(15) DEFAULT NULL,
  `year` varchar(5) DEFAULT NULL,
  `limitCount` int(11) DEFAULT NULL,
  `staff_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `speciality`
--

CREATE TABLE IF NOT EXISTS `speciality` (
  `spec_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(10) DEFAULT NULL,
  `spec_name` varchar(15) DEFAULT NULL,
  `year` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`spec_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

--
-- 转存表中的数据 `speciality`
--

INSERT INTO `speciality` (`spec_id`, `dept_name`, `spec_name`, `year`) VALUES
(3, '大冶尖峰水泥有限公司', '财务部', NULL),
(4, '大冶尖峰水泥有限公司', '研发部', NULL),
(5, '大冶尖峰水泥有限公司', '生产部', NULL),
(6, '大冶尖峰水泥有限公司', '销售部', NULL),
(7, '大冶尖峰水泥有限公司', '售后服务部', NULL),
(8, '大冶尖峰水泥有限公司', ' 总裁办公室', NULL),
(9, '浙江尖峰药业有限公司', '行政副总办公室', NULL),
(11, '浙江尖峰药业有限公司', '浙江大学联合研究室', NULL),
(12, '浙江尖峰药业有限公司', '财务部', NULL),
(13, '浙江尖峰药业有限公司', '采购部', NULL),
(14, '大冶尖峰水泥有限公司', '市场营销部', NULL),
(15, '浙江尖峰药业有限公司', '市场营销部', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `staff_id` varchar(30) NOT NULL DEFAULT '',
  `staff_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `staff`
--

INSERT INTO `staff` (`staff_id`, `staff_name`) VALUES
('teacher', 'teacher');

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `student_id` varchar(30) NOT NULL DEFAULT '',
  `student_name` varchar(20) DEFAULT NULL,
  `id_card` varchar(20) DEFAULT NULL,
  `year` varchar(5) DEFAULT NULL,
  `class_id` varchar(10) DEFAULT NULL,
  `telephone_number` varchar(20) DEFAULT NULL,
  `student_origin_base` varchar(10) DEFAULT NULL,
  `gender` varchar(4) DEFAULT NULL,
  `pic_path` varchar(45) DEFAULT NULL,
  `leave_of_absence` tinyint(1) DEFAULT NULL,
  `leave_school` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `takes`
--

CREATE TABLE IF NOT EXISTS `takes` (
  `student_id` varchar(30) NOT NULL DEFAULT '',
  `sec_id` varchar(30) NOT NULL DEFAULT '',
  `score` float DEFAULT NULL,
  PRIMARY KEY (`student_id`,`sec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `takes`
--

INSERT INTO `takes` (`student_id`, `sec_id`, `score`) VALUES
('student', '1', 5),
('student2', '1', 5);

-- --------------------------------------------------------

--
-- 表的结构 `timetable`
--

CREATE TABLE IF NOT EXISTS `timetable` (
  `sec_id` int(11) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `weeks` varchar(20) DEFAULT NULL,
  `week` varchar(20) DEFAULT NULL,
  `classroom` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(50) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `role_ids` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idx_user_username` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`user_id`, `password`, `salt`, `role_ids`, `locked`) VALUES
('admin', '5476883b25e9c7bb0528b6fdfa0f5de7', '20d98880380112ff6404bdcaa4ba9c10', '1', 0),
('student', '5476883b25e9c7bb0528b6fdfa0f5de7', '20d98880380112ff6404bdcaa4ba9c10', '2', 0),
('student2', '5476883b25e9c7bb0528b6fdfa0f5de7', '20d98880380112ff6404bdcaa4ba9c10', '2', 0),
('supplier', '19a885f6627571598621fe5f5e9cbbc5', '9c64193184d34fa04a205d06b93ca3d6', '4', 0),
('teacher', 'f7e7844cad9aadb0c7f1722f2c9be050', 'a7bbf832a7472759146b0967a78e6ce5', '3', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
