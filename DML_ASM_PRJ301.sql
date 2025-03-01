USE ASM_PRJ301

INSERT INTO Position (PositionCode, PositionName)
VALUES 
    ('GD', N'Giám đốc'),
    ('QL', N'Quản lý'),
    ('NV', N'Nhân viên')

INSERT INTO Department (DepartmentName, [Description], StartTime, EndTime, Tel)
VALUES 
    (N'Phòng Chiến Lược & Điều Hành', 
     N'Hoạch định và thực thi các chiến lược dài hạn của công ty. Giám sát hoạt động của các phòng ban. Đảm bảo sự phát triển bền vững.',
     '08:00:00', '17:00:00', '0923456789'),

    (N'Phòng Kinh Doanh', 
     N'Tìm kiếm khách hàng mới và duy trì quan hệ với khách hàng hiện tại. Lập kế hoạch tăng trưởng doanh thu và triển khai chiến lược bán hàng. Mở rộng thị trường và nâng cao lợi thế cạnh tranh.',
     '08:30:00', '17:00:00', '0934567890'),

    (N'Phòng Kế Toán', 
     N'Quản lý tài chính, kế toán và thuế. Giám sát dòng tiền và lập báo cáo tài chính. Đảm bảo tuân thủ các quy định kế toán.',
     '08:00:00', '17:00:00', '0945678901'),

    (N'Phòng Nhân Sự', 
     N'Tuyển dụng, đào tạo và đánh giá nhân viên. Xây dựng chính sách phúc lợi và quản lý chế độ lương thưởng. Thúc đẩy văn hóa doanh nghiệp.',
     '08:00:00', '17:30:00', '0956789012'),

    (N'Phòng IT', 
     N'Xây dựng, vận hành và bảo trì hệ thống công nghệ thông tin. Hỗ trợ các phòng ban trong việc áp dụng công nghệ vào quy trình làm việc. Đảm bảo an toàn bảo mật dữ liệu cho toàn công ty.',
     '07:30:00', '18:30:00', '0967890123'),

    (N'Phòng Marketing', 
     N'Xây dựng thương hiệu và triển khai các chiến dịch truyền thông. Nghiên cứu thị trường và phân tích xu hướng. Đưa ra chiến lược tiếp cận khách hàng hiệu quả.',
     '09:00:00', '17:00:00', '0978901234');



INSERT INTO Employee(FirstName, LastName, BirthDate, Gender, Tel, Address, PositionID, DepartmentID) VALUES
(N'Công Chiến', N'Nguyễn', '20050804', 'Nam', '0343008127', N'Xuân Đỉnh', 1, 1)

UPDATE ACCOUNT
SET IsAdmin = 1 
WHERE Username = 'ChienNCGD0001'

INSERT INTO Employee (LastName, FirstName, BirthDate, Gender, Tel, [Address], PositionID, DepartmentID, BasicSalary)
VALUES 
-- Quản lý (PositionID = 2) cho mỗi phòng ban, lương từ 20 - 30 triệu
(N'Nguyễn', N'Văn Hùng', '1990-01-01', N'Nam', '0123456789', N'123 Đường Trần Hưng Đạo, Phường Cửa Nam, Quận Hoàn Kiếm, Thành phố Hà Nội', 2, 1, 25000000),
(N'Trần', N'Thị Mai', '1992-02-02', N'Nữ', '0234567890', N'456 Đường Lạch Tray, Phường Lạch Tray, Quận Ngô Quyền, Thành phố Hải Phòng', 2, 2, 22000000),
(N'Lê', N'Minh Tuấn', '1993-03-03', N'Nam', '0345678901', N'789 Đường Nguyễn Văn Linh, Phường Bình Thuận, Quận Hải Châu, Thành phố Đà Nẵng', 2, 3, 28000000),
(N'Phạm', N'Thị Hồng', '1994-04-04', N'Nữ', '0456789012', N'101 Đường Nguyễn Trãi, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', 2, 4, 23000000),
(N'Hoàng', N'Quang Dũng', '1995-05-05', N'Nam', '0567890123', N'202 Đường 30/4, Phường Hưng Lợi, Quận Ninh Kiều, Thành phố Cần Thơ', 2, 5, 27000000),

-- Nhân viên (PositionID = 3), lương từ 10 - 15 triệu
(N'Đặng', N'Thị Lan', '1996-06-06', N'Nữ', '0678901234', N'303 Đường Nguyễn Gia Thiều, Phường Suối Hoa, Thành phố Bắc Ninh, Tỉnh Bắc Ninh', 3, 1, 12000000),
(N'Bùi', N'Văn Hảo', '1997-07-07', N'Nam', '0789012345', N'404 Đường Trần Đăng Ninh, Phường Cửa Bắc, Thành phố Nam Định, Tỉnh Nam Định', 3, 2, 11500000),
(N'Ngô', N'Thị Thu', '1998-08-08', N'Nữ', '0890123456', N'505 Đường Lý Thường Kiệt, Phường Kỳ Bá, Thành phố Thái Bình, Tỉnh Thái Bình', 3, 3, 14000000),
(N'Dương', N'Anh Quân', '1999-09-09', N'Nam', '0901234567', N'606 Đường Nguyễn Thị Minh Khai, Phường Hồng Sơn, Thành phố Vinh, Tỉnh Nghệ An', 3, 4, 13500000),
(N'Vũ', N'Thị Kim Anh', '2000-10-10', N'Nữ', '0912345678', N'707 Đường Hà Huy Tập, Phường Nam Hà, Thành phố Hà Tĩnh, Tỉnh Hà Tĩnh', 3, 5, 11000000);


INSERT INTO [Shift](ShiftName, StartTime, EndTime) VALUES
(N'Ca Sáng', '7:00', '12:00'),
(N'Ca Chiều', '12:00', '17:00'),
(N'Ca Tối', '17:00', '22:00')


SELECT * FROM Employee

SELECT * FROM Account

SELECT * FROM POSITION

SELECT * FROM DEPARTMENT	


SELECT 
	d.DepartmentID, 
	d.DepartmentName, 
	d.Description, 
	d.StartTime, 
	d.EndTime, 
	COALESCE(CONCAT(e.LastName, ' ', e.FirstName), '') as ManagerName,
	COALESCE(d.Tel, '') Tel,
	COALESCE((SELECT COUNT(*) FROM Employee e WHERE e.DepartmentID = d.DepartmentID), 0) as TotalEmployee,
	COALESCE((SELECT SUM(BasicSalary) FROM Employee e WHERE e.DepartmentID = d.DepartmentID), 0) as CostPerMonth
FROM Department d 
LEFT JOIN Employee e ON e.DepartmentID = d.DepartmentID and e.PositionID = 2
ORDER BY d.DepartmentID 

--delete department where departmentid = 1