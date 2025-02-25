USE ASM_PRJ301

INSERT INTO Position (PositionCode, PositionName)
VALUES 
    ('GD', N'Giám đốc'),
    ('QL', N'Quản lý'),
    ('NV', N'Nhân viên')

INSERT INTO Department (DepartmentName)
VALUES 
	(N'Phòng Chiến Lược & Điều Hành'),
    (N'Phòng Kinh Doanh'),
    (N'Phòng Kế Toán'),
    (N'Phòng Nhân Sự'),
    (N'Phòng IT'),
    (N'Phòng Marketing');

INSERT INTO Employee(FirstName, LastName, BirthDate, Gender, Tel, Address, PositionID, DepartmentID) VALUES
(N'Công Chiến', N'Nguyễn', '20050804', 'Nam', '0343008127', N'Xuân Đỉnh', 1, 1)

UPDATE ACCOUNT
SET IsAdmin = 1 
WHERE Username = 'ChienNCGD0001'

INSERT INTO Employee (LastName, FirstName, BirthDate, Gender, Tel, [Address], PositionID, DepartmentID, BasicSalary)
VALUES 
(N'Nguyễn', N'Văn Hùng', '1990-01-01', N'Nam', '0123456789', N'123 Đường Trần Hưng Đạo, Phường Cửa Nam, Quận Hoàn Kiếm, Thành phố Hà Nội', 3, 1, 15000000),
(N'Trần', N'Thị Mai', '1992-02-02', N'Nữ', '0234567890', N'456 Đường Lạch Tray, Phường Lạch Tray, Quận Ngô Quyền, Thành phố Hải Phòng', 3, 2, 14000000),
(N'Lê', N'Minh Tuấn', '1993-03-03', N'Nam', '0345678901', N'789 Đường Nguyễn Văn Linh, Phường Bình Thuận, Quận Hải Châu, Thành phố Đà Nẵng', 3, 3, 16000000),
(N'Phạm', N'Thị Hồng', '1994-04-04', N'Nữ', '0456789012', N'101 Đường Nguyễn Trãi, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', 3, 4, 15500000),
(N'Hoàng', N'Quang Dũng', '1995-05-05', N'Nam', '0567890123', N'202 Đường 30/4, Phường Hưng Lợi, Quận Ninh Kiều, Thành phố Cần Thơ', 3, 5, 14500000),
(N'Đặng', N'Thị Lan', '1996-06-06', N'Nữ', '0678901234', N'303 Đường Nguyễn Gia Thiều, Phường Suối Hoa, Thành phố Bắc Ninh, Tỉnh Bắc Ninh', 3, 1, 13500000),
(N'Bùi', N'Văn Hảo', '1997-07-07', N'Nam', '0789012345', N'404 Đường Trần Đăng Ninh, Phường Cửa Bắc, Thành phố Nam Định, Tỉnh Nam Định', 3, 2, 14200000),
(N'Ngô', N'Thị Thu', '1998-08-08', N'Nữ', '0890123456', N'505 Đường Lý Thường Kiệt, Phường Kỳ Bá, Thành phố Thái Bình, Tỉnh Thái Bình', 3, 3, 14800000),
(N'Dương', N'Anh Quân', '1999-09-09', N'Nam', '0901234567', N'606 Đường Nguyễn Thị Minh Khai, Phường Hồng Sơn, Thành phố Vinh, Tỉnh Nghệ An', 3, 4, 15200000),
(N'Vũ', N'Thị Kim Anh', '2000-10-10', N'Nữ', '0912345678', N'707 Đường Hà Huy Tập, Phường Nam Hà, Thành phố Hà Tĩnh, Tỉnh Hà Tĩnh', 3, 5, 13700000);


INSERT INTO [Shift](ShiftName, StartTime, EndTime) VALUES
(N'Ca Sáng', '7:00', '12:00'),
(N'Ca Chiều', '12:00', '17:00'),
(N'Ca Tối', '17:00', '22:00')


SELECT * FROM Employee

SELECT * FROM ACCOUNT

SELECT * FROM POSITION

SELECT * FROM DEPARTMENT	

SELECT * FROM Work