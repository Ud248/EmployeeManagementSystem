INSERT INTO Position (PositionCode, PositionName)
VALUES 
    ('GD', N'Giám đốc'),
    ('QL', N'Quản lý'),
    ('NV', N'Nhân viên')

INSERT INTO Department (DepartmentName)
VALUES 
    (N'Phòng Kinh Doanh'),
    (N'Phòng Kế Toán'),
    (N'Phòng Nhân Sự'),
    (N'Phòng IT'),
    (N'Phòng Marketing');

INSERT INTO Employee(FirstName, LastName, BirthDate, Gender, Tel, Address, PositionID, DepartmentID) VALUES
(N'Công Chiến', N'Nguyễn', '20050804', 'Nam', '0343008127', 'Xuân Đỉnh', 1, 1)

UPDATE ACCOUNT
SET IsAdmin = 1 
WHERE Username = 'ChienNCGD0001'

SELECT * FROM ACCOUNT

SELECT * FROM POSITION

SELECT * FROM DEPARTMENT	