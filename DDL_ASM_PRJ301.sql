USE MASTER

IF EXISTS (SELECT 1 FROM sys.databases WHERE NAME = N'ASM_PRJ301')
	DROP DATABASE ASM_PRJ301

CREATE DATABASE ASM_PRJ301

USE ASM_PRJ301

CREATE TABLE Position(
	PositionID INT PRIMARY KEY IDENTITY,
	PositionCode CHAR(2) NOT NULL UNIQUE, --kiểu GD, NV, QL,...
	PositionName NVARCHAR(50) NOT NULL
)

CREATE TABLE Department(
	DepartmentID INT PRIMARY KEY IDENTITY,
	DepartmentName NVARCHAR(100) NOT NULL UNIQUE,
	[Description] NVARCHAR(MAX) NOT NULL,
	StartTime TIME NOT NULL,
	EndTime TIME NOT NULL,
	Tel CHAR(10) NOT NULL UNIQUE CHECK(Tel LIKE '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
)

CREATE TABLE Employee(
	EmployeeID INT PRIMARY KEY IDENTITY,
	EmployeeCode CHAR(6) NOT NULL DEFAULT '',
	FirstName NVARCHAR(50) NOT NULL,
	LastName NVARCHAR(50) NOT NULL,
	BirthDate DATE NOT NULL,
	Gender NCHAR(3) NOT NULL CHECK(Gender IN ('Nam', N'Nữ')),
	Tel CHAR(10) NOT NULL UNIQUE CHECK(Tel LIKE '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	[Address] NVARCHAR(MAX) NOT NULL,
	PositionID INT,
	DepartmentID INT,
	BasicSalary INT NOT NULL DEFAULT 0,
	FOREIGN KEY (PositionID) REFERENCES Position(PositionID) ON UPDATE CASCADE ON DELETE SET NULL,
	FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID) ON UPDATE CASCADE ON DELETE SET NULL
)

CREATE TABLE Account(
	AccountID INT PRIMARY KEY IDENTITY,
	EmployeeID INT NOT NULL,
	Username VARCHAR(50) NOT NULL ,
	[Password] VARCHAR(100) NOT NULL DEFAULT '123',
	IsAdmin BIT NOT NULL DEFAULT 0 --1: True, 0: False
	FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE Insurance(
	InsuranceID INT PRIMARY KEY IDENTITY,
    EmployeeID INT NOT NULL,
    InsuranceNumber VARCHAR(50),
    [Type] NVARCHAR(100), -- Loại bảo hiểm (y tế, xã hội, thất nghiệp,...)
    ExpiryDate DATE,
	PricePerMonth DECIMAL(18,2) NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE Salary(
    SalaryID INT PRIMARY KEY IDENTITY,             
    EmployeeID INT NOT NULL,              
    PayPeriodStart DATE NOT NULL,        
    PayPeriodEnd DATE NOT NULL,            
	NetSalary DECIMAL(18,2) NOT NULL,  
    [Status] NVARCHAR(50) DEFAULT N'Đang xử lý' CHECK([Status] in (N'Đang xử lý', N'Hoàn thành', N'Từ chối')),
    CreatedAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE ON DELETE CASCADE,
	CHECK (PayPeriodEnd >= PayPeriodStart)
)

CREATE TABLE Attendance(
	AttendanceID INT IDENTITY(1,1) PRIMARY KEY,  
    EmployeeID INT NOT NULL,
	CheckInDate Date NOT NULL,
    CheckInTime TIME NOT NULL,  
    [Status] NVARCHAR(50) DEFAULT N'Đang xử lý' CHECK (Status IN (N'Đang xử lý', N'Đúng giờ', N'Đi muộn')),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE Leave(
	LeaveID INT PRIMARY KEY IDENTITY,  
    EmployeeID INT NOT NULL,  
    LeaveType NVARCHAR(50) NOT NULL CHECK (LeaveType IN (N'Phép năm', N'Nghỉ bệnh', N'Nghỉ không lương', N'Nghỉ thai sản', N'Khác')), 
    StartDate DATE NOT NULL,  
    EndDate DATE NOT NULL,  
	TotalDay AS (DATEDIFF(DAY, EndDate, StartDate) + 1) PERSISTED,
    Reason NVARCHAR(255) NULL,  
    [Status] NVARCHAR(50) DEFAULT N'Chờ duyệt' CHECK (Status IN (N'Chờ duyệt', N'Đã duyệt', N'Từ chối')),
    CreatedAt DATETIME DEFAULT GETDATE(),    
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE BonusPenalty(
    RecordID INT PRIMARY KEY IDENTITY, 
    EmployeeID INT NOT NULL, 
    RecordType BIT NOT NULL, --1 là thưởng, 0 là phạt  
    Amount DECIMAL(18,2) NOT NULL CHECK (Amount > 0),  
    Reason NVARCHAR(255) NOT NULL,  
    RecordDate DATE DEFAULT GETDATE(),  
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE [Shift](
    ShiftID INT PRIMARY KEY IDENTITY,
    ShiftName NVARCHAR(100) NOT NULL CHECK(ShiftName in (N'Ca Sáng', N'Ca Chiều', N'Ca Tối')),
    StartTime TIME NOT NULL,
    EndTime TIME NOT NULL
)

CREATE TABLE Work(
    EmployeeID INT NOT NULL,
    ShiftID INT NOT NULL,
    WorkDate DATE NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (ShiftID) REFERENCES Shift(ShiftID) ON UPDATE CASCADE ON DELETE CASCADE
)

GO

CREATE FUNCTION dbo.ConvertToASCII(@text NVARCHAR(255))
RETURNS NVARCHAR(255)
AS
BEGIN
    DECLARE @Result NVARCHAR(255) = LOWER(@text);

	SET @Result = REPLACE(@Result, N'đ', 'd');
    SET @Result = REPLACE(@Result, N'á', 'a');
    SET @Result = REPLACE(@Result, N'à', 'a');
    SET @Result = REPLACE(@Result, N'ả', 'a');
    SET @Result = REPLACE(@Result, N'ã', 'a');
    SET @Result = REPLACE(@Result, N'ạ', 'a');
    SET @Result = REPLACE(@Result, N'â', 'a');
    SET @Result = REPLACE(@Result, N'ấ', 'a');
    SET @Result = REPLACE(@Result, N'ầ', 'a');
    SET @Result = REPLACE(@Result, N'ẩ', 'a');
    SET @Result = REPLACE(@Result, N'ẫ', 'a');
    SET @Result = REPLACE(@Result, N'ậ', 'a');
    SET @Result = REPLACE(@Result, N'ă', 'a');
    SET @Result = REPLACE(@Result, N'ắ', 'a');
    SET @Result = REPLACE(@Result, N'ằ', 'a');
    SET @Result = REPLACE(@Result, N'ẳ', 'a');
    SET @Result = REPLACE(@Result, N'ẵ', 'a');
    SET @Result = REPLACE(@Result, N'ặ', 'a');
    SET @Result = REPLACE(@Result, N'é', 'e');
    SET @Result = REPLACE(@Result, N'è', 'e');
    SET @Result = REPLACE(@Result, N'ẻ', 'e');
    SET @Result = REPLACE(@Result, N'ẽ', 'e');
    SET @Result = REPLACE(@Result, N'ẹ', 'e');
    SET @Result = REPLACE(@Result, N'ê', 'e');
    SET @Result = REPLACE(@Result, N'ế', 'e');
    SET @Result = REPLACE(@Result, N'ề', 'e');
    SET @Result = REPLACE(@Result, N'ể', 'e');
    SET @Result = REPLACE(@Result, N'ễ', 'e');
    SET @Result = REPLACE(@Result, N'ệ', 'e');
    SET @Result = REPLACE(@Result, N'í', 'i');
    SET @Result = REPLACE(@Result, N'ì', 'i');
    SET @Result = REPLACE(@Result, N'ỉ', 'i');
    SET @Result = REPLACE(@Result, N'ĩ', 'i');
    SET @Result = REPLACE(@Result, N'ị', 'i');
    SET @Result = REPLACE(@Result, N'ó', 'o');
    SET @Result = REPLACE(@Result, N'ò', 'o');
    SET @Result = REPLACE(@Result, N'ỏ', 'o');
    SET @Result = REPLACE(@Result, N'õ', 'o');
    SET @Result = REPLACE(@Result, N'ọ', 'o');
    SET @Result = REPLACE(@Result, N'ô', 'o');
    SET @Result = REPLACE(@Result, N'ố', 'o');
    SET @Result = REPLACE(@Result, N'ồ', 'o');
    SET @Result = REPLACE(@Result, N'ổ', 'o');
    SET @Result = REPLACE(@Result, N'ỗ', 'o');
    SET @Result = REPLACE(@Result, N'ộ', 'o');
    SET @Result = REPLACE(@Result, N'ơ', 'o');
    SET @Result = REPLACE(@Result, N'ớ', 'o');
    SET @Result = REPLACE(@Result, N'ờ', 'o');
    SET @Result = REPLACE(@Result, N'ở', 'o');
    SET @Result = REPLACE(@Result, N'ỡ', 'o');
    SET @Result = REPLACE(@Result, N'ợ', 'o');
    SET @Result = REPLACE(@Result, N'ú', 'u');
    SET @Result = REPLACE(@Result, N'ù', 'u');
    SET @Result = REPLACE(@Result, N'ủ', 'u');
    SET @Result = REPLACE(@Result, N'ũ', 'u');
    SET @Result = REPLACE(@Result, N'ụ', 'u');
    SET @Result = REPLACE(@Result, N'ư', 'u');
    SET @Result = REPLACE(@Result, N'ứ', 'u');
    SET @Result = REPLACE(@Result, N'ừ', 'u');
    SET @Result = REPLACE(@Result, N'ử', 'u');
    SET @Result = REPLACE(@Result, N'ữ', 'u');
    SET @Result = REPLACE(@Result, N'ự', 'u');
    SET @Result = REPLACE(@Result, N'ý', 'y');
    SET @Result = REPLACE(@Result, N'ỳ', 'y');
    SET @Result = REPLACE(@Result, N'ỷ', 'y');
    SET @Result = REPLACE(@Result, N'ỹ', 'y');
    SET @Result = REPLACE(@Result, N'ỵ', 'y');


    RETURN @Result
END

GO

CREATE TRIGGER tr_AI_Employee
ON Employee
AFTER INSERT
AS
BEGIN
    UPDATE e
    SET e.EmployeeCode = CONCAT(p.PositionCode, FORMAT(i.EmployeeID, '0000'))
    FROM INSERTED i
    JOIN Position p ON i.PositionID = p.PositionID
    JOIN Employee e ON e.EmployeeID = i.EmployeeID;

    INSERT INTO Account(EmployeeID, Username)  
	SELECT 
		e.EmployeeID,
		CONCAT(
			dbo.ConvertToASCII(RIGHT(i.FirstName, CHARINDEX(' ', REVERSE(i.FirstName) + ' ') - 1)), 
			dbo.ConvertToASCII(LEFT(i.LastName, 1)),
			dbo.ConvertToASCII(LEFT(i.FirstName, 1)),
			LOWER(e.EmployeeCode)
		)
	FROM INSERTED i 
	JOIN Employee e ON i.Tel = e.Tel
END
