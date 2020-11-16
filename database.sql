--CREATE DATABASE YellowMoonCakeShop

--USE master
--go

USE YellowMoonCakeShop
Go

CREATE TABLE tblRoles(
	id int NOT NULL PRIMARY KEY,
	rolename varchar(10),
)

CREATE TABLE tblUsers(
	username varchar(50) NOT NULL PRIMARY KEY,
	firstname nvarchar(18) NOT NULL,
	lastname nvarchar(18) NOT NULL,
	password varchar(64) NOT NULL,
	registerdate date,
	roleID int NOT NULL FOREIGN KEY REFERENCES tblRoles(id),
	isactive bit NOT NULL,
)

CREATE TABLE tblCategories(
	id int NOT NULL PRIMARY KEY,
	categoryname varchar(30),
)

CREATE TABLE tblCakes(
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(100),
	categoryid int NOT NULL FOREIGN KEY REFERENCES tblCategories(id),
	descripton nvarchar(1000),
	img varchar(100),
	createdate date,
	expirationdate date,
	price float,
	quantity int,
	isVisible bit,
)

CREATE TABLE tblCashTypes(
	id int NOT NULL PRIMARY KEY,
	name nvarchar(100),
)

CREATE TABLE tblAddresses(
	id int IDENTITY(1,1) PRIMARY KEY,
	username varchar(50) FOREIGN KEY REFERENCES tblUsers(username),
	phone varchar(12),
	fullname varchar(36),
	addr varchar(50),
)

CREATE TABLE tblOrders(
	id int IDENTITY(1,1) PRIMARY KEY,
	username varchar(50) FOREIGN KEY REFERENCES tblUsers(username),
	addressid int FOREIGN KEY REFERENCES tblAddresses(id),
	orderdate datetime,
	cashtype int NOT NULL FOREIGN KEY REFERENCES tblCashTypes(id),
	iscashed bit,
	isdelivered bit,
)

CREATE TABLE tblOrderDetails(
	id int IDENTITY(1,1) PRIMARY KEY,
	orderID int NOT NULL FOREIGN KEY REFERENCES tblOrders(id),
	cakeID int NOT NULL FOREIGN KEY REFERENCES tblCakes(id),
	quantity int,
)

CREATE TABLE tblLogs(
	id int IDENTITY(1,1) PRIMARY KEY,
	username varchar(50) NOT NULL FOREIGN KEY REFERENCES tblUsers(username),
	act nvarchar(100),
	descripton nvarchar(1000),
	actionDate datetime,
)





--USE master 
--go

--DROP DATABASE YellowMoonCakeShop

--DROP TABLE tblLogs
