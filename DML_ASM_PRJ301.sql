USE ASM_PRJ301

INSERT INTO Position (PositionCode, PositionName)
VALUES 
    ('GD', N'Giám đốc'),
    ('QL', N'Quản lý'),
    ('NV', N'Nhân viên')

INSERT INTO Department (DepartmentName, [Description], StartTime, EndTime, Tel)
VALUES 
    (N'Phòng Chiến Lược và Điều Hành', 
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

	 /*
	 --thêm để test phân trang
	 (N'Phòng Chiến Lược và Điều Hành', 
     N'Hoạch định và thực thi các chiến lược dài hạn của công ty. Giám sát hoạt động của các phòng ban. Đảm bảo sự phát triển bền vững.',
     '08:00:00', '17:00:00', '0123456789'),

    (N'Phòng Kinh Doanh', 
     N'Tìm kiếm khách hàng mới và duy trì quan hệ với khách hàng hiện tại. Lập kế hoạch tăng trưởng doanh thu và triển khai chiến lược bán hàng. Mở rộng thị trường và nâng cao lợi thế cạnh tranh.',
     '08:30:00', '17:00:00', '0123456788'),

    (N'Phòng Kế Toán', 
     N'Quản lý tài chính, kế toán và thuế. Giám sát dòng tiền và lập báo cáo tài chính. Đảm bảo tuân thủ các quy định kế toán.',
     '08:00:00', '17:00:00', '0123456787'),

    (N'Phòng Nhân Sự', 
     N'Tuyển dụng, đào tạo và đánh giá nhân viên. Xây dựng chính sách phúc lợi và quản lý chế độ lương thưởng. Thúc đẩy văn hóa doanh nghiệp.',
     '08:00:00', '17:30:00', '0123456786'),

    (N'Phòng IT', 
     N'Xây dựng, vận hành và bảo trì hệ thống công nghệ thông tin. Hỗ trợ các phòng ban trong việc áp dụng công nghệ vào quy trình làm việc. Đảm bảo an toàn bảo mật dữ liệu cho toàn công ty.',
     '07:30:00', '18:30:00', '0123456785'),

    (N'Phòng Marketing', 
     N'Xây dựng thương hiệu và triển khai các chiến dịch truyền thông. Nghiên cứu thị trường và phân tích xu hướng. Đưa ra chiến lược tiếp cận khách hàng hiệu quả.',
     '09:00:00', '17:00:00', '0123456784'),

	 (N'Phòng Nhân Sự', 
     N'Tuyển dụng, đào tạo và đánh giá nhân viên. Xây dựng chính sách phúc lợi và quản lý chế độ lương thưởng. Thúc đẩy văn hóa doanh nghiệp.',
     '08:00:00', '17:30:00', '0123456783'),

    (N'Phòng IT', 
     N'Xây dựng, vận hành và bảo trì hệ thống công nghệ thông tin. Hỗ trợ các phòng ban trong việc áp dụng công nghệ vào quy trình làm việc. Đảm bảo an toàn bảo mật dữ liệu cho toàn công ty.',
     '07:30:00', '18:30:00', '0123456782'),

    (N'Phòng Marketing', 
     N'Xây dựng thương hiệu và triển khai các chiến dịch truyền thông. Nghiên cứu thị trường và phân tích xu hướng. Đưa ra chiến lược tiếp cận khách hàng hiệu quả.',
     '09:00:00', '17:00:00', '0123456781'),

	 (N'Phòng Nhân Sự', 
     N'Tuyển dụng, đào tạo và đánh giá nhân viên. Xây dựng chính sách phúc lợi và quản lý chế độ lương thưởng. Thúc đẩy văn hóa doanh nghiệp.',
     '08:00:00', '17:30:00', '0123456790'),

    (N'Phòng IT', 
     N'Xây dựng, vận hành và bảo trì hệ thống công nghệ thông tin. Hỗ trợ các phòng ban trong việc áp dụng công nghệ vào quy trình làm việc. Đảm bảo an toàn bảo mật dữ liệu cho toàn công ty.',
     '07:30:00', '18:30:00', '0123456799'),

    (N'Phòng Marketing', 
     N'Xây dựng thương hiệu và triển khai các chiến dịch truyền thông. Nghiên cứu thị trường và phân tích xu hướng. Đưa ra chiến lược tiếp cận khách hàng hiệu quả.',
     '09:00:00', '17:00:00', '0123456798'),

	 (N'Phòng Nhân Sự', 
     N'Tuyển dụng, đào tạo và đánh giá nhân viên. Xây dựng chính sách phúc lợi và quản lý chế độ lương thưởng. Thúc đẩy văn hóa doanh nghiệp.',
     '08:00:00', '17:30:00', '0123456797'),

    (N'Phòng IT', 
     N'Xây dựng, vận hành và bảo trì hệ thống công nghệ thông tin. Hỗ trợ các phòng ban trong việc áp dụng công nghệ vào quy trình làm việc. Đảm bảo an toàn bảo mật dữ liệu cho toàn công ty.',
     '07:30:00', '18:30:00', '0123456796'),

    (N'Phòng Marketing', 
     N'Xây dựng thương hiệu và triển khai các chiến dịch truyền thông. Nghiên cứu thị trường và phân tích xu hướng. Đưa ra chiến lược tiếp cận khách hàng hiệu quả.',
     '09:00:00', '17:00:00', '0123456795');
	 */



INSERT INTO Employee(FirstName, LastName, BirthDate, Gender, Tel, Address, PositionID, DepartmentID, BasicSalary) VALUES
(N'Công Chiến', N'Nguyễn', '20050804', 'Nam', '0343008127', N'Xuân Đỉnh', 1, 1, 100000000)

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
(N'Vũ', N'Thị Kim Anh', '2000-10-10', N'Nữ', '0912345678', N'707 Đường Hà Huy Tập, Phường Nam Hà, Thành phố Hà Tĩnh, Tỉnh Hà Tĩnh', 3, 5, 11000000),
(N'Bùi', N'Hữu E', '1986-12-09', N'Nam', '0928719146', N'789 Đường G, Quận 7, Thành phố Huế', 3, 1, 14664967),
(N'Trần', N'Thế H', '1990-09-24', N'Nam', '0945966475', N'456 Đường D, Quận 4, Thành phố Hồ Chí Minh', 3, 2, 12999388),
(N'Đặng', N'Mỹ Duyên', '1998-08-28', N'Nữ', '0964807073', N'789 Đường G, Quận 7, Thành phố Huế', 3, 3, 14552487),
(N'Dương', N'Đình F', '1999-08-09', N'Nam', '0915115241', N'123 Đường A, Quận 1, Thành phố Hà Nội', 3, 4, 11830450),
(N'Vũ', N'Văn A', '1996-09-07', N'Nam', '0950811735', N'567 Đường E, Quận 5, Thành phố Cần Thơ', 3, 5, 13123285),
(N'Bùi', N'Bảo I', '1988-09-03', N'Nam', '0936200812', N'567 Đường E, Quận 5, Thành phố Cần Thơ', 3, 6, 10767045),
(N'Phạm', N'Minh C', '1986-06-03', N'Nam', '0991262629', N'123 Đường A, Quận 1, Thành phố Hà Nội', 3, 1, 14260448),
(N'Ngô', N'Hồng Nhung', '1985-02-11', N'Nữ', '0934287556', N'234 Đường B, Quận 2, Thành phố Hải Phòng', 3, 2, 11068930),
(N'Phạm', N'Minh C', '1993-09-15', N'Nam', '0932635270', N'567 Đường E, Quận 5, Thành phố Cần Thơ', 3, 3, 14296360),
(N'Đặng', N'Đình F', '1998-04-22', N'Nam', '0996469480', N'456 Đường D, Quận 4, Thành phố Hồ Chí Minh', 3, 4, 11215052),
(N'Vũ', N'Hữu E', '1994-09-15', N'Nam', '0915929679', N'678 Đường F, Quận 6, Thành phố Nha Trang', 3, 5, 11403857),
(N'Đặng', N'Ngọc Hân', '1999-08-05', N'Nữ', '0975274495', N'678 Đường F, Quận 6, Thành phố Nha Trang', 3, 6, 12340735),
(N'Bùi', N'Thu Hằng', '1985-05-07', N'Nữ', '0917087538', N'123 Đường A, Quận 1, Thành phố Hà Nội', 3, 1, 11570031),
(N'Hoàng', N'Tùng K', '1996-09-13', N'Nam', '0967501669', N'345 Đường C, Quận 3, Thành phố Đà Nẵng', 3, 2, 11879666),
(N'Bùi', N'Quang D', '1985-05-12', N'Nam', '0962337502', N'567 Đường E, Quận 5, Thành phố Cần Thơ', 3, 3, 10610101),
(N'Ngô', N'Thanh Trà', '2002-07-20', N'Nữ', '0930160921', N'567 Đường E, Quận 5, Thành phố Cần Thơ', 3, 4, 12958790),
(N'Lê', N'Kim Chi', '1995-08-22', N'Nữ', '0964770799', N'678 Đường F, Quận 6, Thành phố Nha Trang', 3, 5, 10238575),
(N'Lê', N'Quang D', '1988-08-24', N'Nam', '0966333646', N'567 Đường E, Quận 5, Thành phố Cần Thơ', 3, 6, 13417358),
(N'Trần', N'Tùng K', '2000-09-24', N'Nam', '0941112559', N'567 Đường E, Quận 5, Thành phố Cần Thơ', 3, 1, 10157865),
(N'Bùi', N'Thu Hằng', '1987-02-08', N'Nữ', '0983909174', N'123 Đường A, Quận 1, Thành phố Hà Nội', 3, 2, 11549263),
(N'Hoàng', N'Quang D', '2000-09-20', N'Nam', '0989113308', N'678 Đường F, Quận 6, Thành phố Nha Trang', 3, 3, 12014326),
(N'Vũ', N'Thanh Trà', '1998-10-22', N'Nữ', '0978869680', N'456 Đường D, Quận 4, Thành phố Hồ Chí Minh', 3, 4, 13055886),
(N'Đặng', N'Văn A', '1990-05-09', N'Nam', '0953822545', N'456 Đường D, Quận 4, Thành phố Hồ Chí Minh', 3, 5, 11845706),
(N'Phạm', N'Công G', '1992-12-07', N'Nam', '0979524287', N'234 Đường B, Quận 2, Thành phố Hải Phòng', 3, 6, 11541308),
(N'Bùi', N'Thế H', '1992-06-12', N'Nam', '0913305406', N'345 Đường C, Quận 3, Thành phố Đà Nẵng', 3, 1, 13960088),
(N'Phạm', N'Quang D', '1986-03-02', N'Nam', '0968844476', N'789 Đường G, Quận 7, Thành phố Huế', 3, 2, 13327637),
(N'Phạm', N'Phương Anh', '2002-12-14', N'Nữ', '0976873759', N'678 Đường F, Quận 6, Thành phố Nha Trang', 3, 3, 11097996),
(N'Nguyễn', N'Tùng K', '1990-06-02', N'Nam', '0974406222', N'123 Đường A, Quận 1, Thành phố Hà Nội', 3, 4, 14692394),
(N'Bùi', N'Phương Anh', '1996-05-16', N'Nữ', '0986110138', N'456 Đường D, Quận 4, Thành phố Hồ Chí Minh', 3, 5, 10276552),
(N'Vũ', N'Phương Anh', '1987-03-18', N'Nữ', '0996034483', N'789 Đường G, Quận 7, Thành phố Huế', 3, 6, 11302693),
(N'Phạm', N'Quang D', '1985-06-23', N'Nam', '0911525213', N'345 Đường C, Quận 3, Thành phố Đà Nẵng', 3, 1, 11803043),
(N'Ngô', N'Văn A', '1985-10-27', N'Nam', '0949155080', N'123 Đường A, Quận 1, Thành phố Hà Nội', 3, 2, 13773933),
(N'Phạm', N'Thanh Trà', '1988-10-24', N'Nữ', '0997925375', N'345 Đường C, Quận 3, Thành phố Đà Nẵng', 3, 3, 11499541),
(N'Vũ', N'Thị Lan', '1998-09-21', N'Nữ', '0919740381', N'345 Đường C, Quận 3, Thành phố Đà Nẵng', 3, 4, 14012757),
(N'Hoàng', N'Phương Anh', '1998-02-22', N'Nữ', '0989968483', N'678 Đường F, Quận 6, Thành phố Nha Trang', 3, 5, 10298160),
(N'Dương', N'Hữu E', '1986-01-15', N'Nam', '0941162353', N'678 Đường F, Quận 6, Thành phố Nha Trang', 3, 6, 10599141),
(N'Vũ', N'Thanh Trà', '1987-01-08', N'Nữ', '0970712441', N'567 Đường E, Quận 5, Thành phố Cần Thơ', 3, 1, 10169616),
(N'Hoàng', N'Mỹ Duyên', '1987-11-21', N'Nữ', '0924507551', N'678 Đường F, Quận 6, Thành phố Nha Trang', 3, 2, 10723108),
(N'Phạm', N'Thị Mai', '1987-04-22', N'Nữ', '0916050936', N'789 Đường G, Quận 7, Thành phố Huế', 3, 3, 14882760),
(N'Lê', N'Ngọc Hân', '1990-07-06', N'Nữ', '0973636208', N'567 Đường E, Quận 5, Thành phố Cần Thơ', 3, 4, 14433514),
(N'Trần', N'Công G', '1991-02-16', N'Nam', '0981153790', N'345 Đường C, Quận 3, Thành phố Đà Nẵng', 3, 5, 14260111),
(N'Lê', N'Tùng K', '1985-07-10', N'Nam', '0914220473', N'567 Đường E, Quận 5, Thành phố Cần Thơ', 3, 6, 10291180),
(N'Dương', N'Thu Hằng', '2002-12-27', N'Nữ', '0978950312', N'789 Đường G, Quận 7, Thành phố Huế', 3, 1, 10837659),
(N'Vũ', N'Đình F', '1986-05-15', N'Nam', '0969557135', N'678 Đường F, Quận 6, Thành phố Nha Trang', 3, 2, 10924509),
(N'Đặng', N'Bích Ngọc', '2001-06-28', N'Nữ', '0989099119', N'678 Đường F, Quận 6, Thành phố Nha Trang', 3, 3, 12403227),
(N'Nguyễn', N'Phương Anh', '1995-08-11', N'Nữ', '0969494849', N'345 Đường C, Quận 3, Thành phố Đà Nẵng', 3, 4, 12604630),
(N'Bùi', N'Tùng K', '1987-08-04', N'Nam', '0928053529', N'123 Đường A, Quận 1, Thành phố Hà Nội', 3, 5, 10400707),
(N'Vũ', N'Minh C', '1986-11-21', N'Nam', '0932525068', N'678 Đường F, Quận 6, Thành phố Nha Trang', 3, 6, 12159283),
(N'Ngô', N'Bảo I', '2002-05-05', N'Nam', '0961121814', N'345 Đường C, Quận 3, Thành phố Đà Nẵng', 3, 1, 12737982),
(N'Đặng', N'Thu Hằng', '1995-02-17', N'Nữ', '0952158258', N'456 Đường D, Quận 4, Thành phố Hồ Chí Minh', 3, 2, 11969637),
(N'Nguyễn', N'Minh C', '1999-08-07', N'Nam', '0930186016', N'234 Đường B, Quận 2, Thành phố Hải Phòng', 3, 3, 13577308),
(N'Vũ', N'Phương Anh', '1987-07-09', N'Nữ', '0943271671', N'789 Đường G, Quận 7, Thành phố Huế', 3, 4, 14332730),
(N'Bùi', N'Thị Lan', '1993-06-23', N'Nữ', '0993621416', N'234 Đường B, Quận 2, Thành phố Hải Phòng', 3, 5, 13833338),
(N'Đặng', N'Bích Ngọc', '1988-03-03', N'Nữ', '0917907098', N'234 Đường B, Quận 2, Thành phố Hải Phòng', 3, 6, 14140461),
(N'Lê', N'Quang D', '1999-08-28', N'Nam', '0917450275', N'345 Đường C, Quận 3, Thành phố Đà Nẵng', 3, 1, 12801273),
(N'Đặng', N'Bảo I', '2001-11-18', N'Nam', '0957952047', N'456 Đường D, Quận 4, Thành phố Hồ Chí Minh', 3, 2, 13802444),
(N'Đặng', N'Ngọc Hân', '1985-03-11', N'Nữ', '0921110115', N'789 Đường G, Quận 7, Thành phố Huế', 3, 3, 13069661),
(N'Trần', N'Thanh Trà', '2000-11-17', N'Nữ', '0963252819', N'567 Đường E, Quận 5, Thành phố Cần Thơ', 3, 4, 13882003),
(N'Bùi', N'Bích Ngọc', '1997-05-21', N'Nữ', '0914612691', N'456 Đường D, Quận 4, Thành phố Hồ Chí Minh', 3, 5, 12564724),
(N'Phạm', N'Quang D', '2001-05-03', N'Nam', '0989373632', N'456 Đường D, Quận 4, Thành phố Hồ Chí Minh', 3, 6, 13030618),
(N'Nguyễn', N'Thu Hằng', '1991-04-23', N'Nữ', '0910655335', N'234 Đường B, Quận 2, Thành phố Hải Phòng', 3, 1, 14713100),
(N'Hoàng', N'Anh B', '1997-06-21', N'Nam', '0943377894', N'234 Đường B, Quận 2, Thành phố Hải Phòng', 3, 2, 13449437);

INSERT INTO Project (ProjectName, [Description], Completion, StartDate, EndDate, Deadline, Budget, Profit, DepartmentID)
VALUES 
-- Các dự án đã hoàn thành (Completion = 100%)
(N'Xây dựng Cầu Long An', N'Xây cầu bắc qua sông giúp cải thiện giao thông', 100, '2023-01-15', '2024-07-30', '2025-06-30', 50000000, 120000000, 1),
(N'Hệ thống CRM Doanh Nghiệp', N'Phát triển nền tảng quản lý khách hàng', 100, '2023-11-01', '2024-10-05', '2024-12-31', 20000000, 500000000, 2),
(N'Nghiên cứu Vắc-xin COVID-25', N'Phát triển vắc-xin thế hệ mới', 100, '2024-03-10', '2024-12-12', '2026-09-15', 80000000, 150000000, 3),
(N'Nâng cấp Hạ tầng CNTT', N'Hiện đại hóa hệ thống máy chủ', 100, '2023-12-20', '2024-11-10', '2024-08-20', 30000000, 900000000, 4),
(N'Xây dựng Nhà Máy Điện Gió', N'Triển khai nhà máy năng lượng sạch', 100, '2024-02-05', '2024-12-01', '2025-11-01', 100000000, 250000000, 5),
(N'Bộ phim Khoa học Viễn tưởng', N'Sản xuất phim điện ảnh chất lượng cao', 100, '2024-04-25', '2025-02-18', '2024-12-20', 15000000, 400000000, 6),
(N'Dự án Xanh Hồi sinh Rừng', N'Tái tạo rừng tự nhiên', 100, '2024-01-10', '2024-08-29', '2025-07-15', 60000000, 180000000, 1),
(N'Năng lượng Mặt trời Miền Trung', N'Triển khai hệ thống điện tái tạo', 100, '2023-10-01', '2024-07-14', '2025-05-30', 75000000, 200000000, 2),
(N'Hệ thống Quản lý Tài chính', N'Ứng dụng theo dõi tài chính cá nhân', 100, '2023-09-15', '2024-08-01', '2025-03-30', 40000000, 150000000, 1),
(N'Nghiên cứu Năng lượng Hydro', N'Ứng dụng hydro vào sản xuất', 100, '2024-01-10', '2024-12-20', '2025-10-10', 75000000, 250000000, 2),
(N'Công viên Sinh thái Xanh', N'Xây dựng không gian xanh tại đô thị', 100, '2024-03-01', '2024-10-15', '2026-01-01', 65000000, 200000000, 3),
(N'Nâng cấp Mạng Viễn thông 6G', N'Phát triển thế hệ mạng không dây mới', 100, '2023-07-20', '2024-06-10', '2025-12-15', 90000000, 300000000, 4),
(N'Hệ thống AI Chăm sóc Khách hàng', N'Tích hợp AI vào hỗ trợ khách hàng', 100, '2024-04-05', '2025-02-01', '2025-09-30', 55000000, 180000000, 5),
(N'Ứng dụng Thanh toán Điện tử', N'Triển khai hệ thống thanh toán không tiền mặt', 100, '2023-06-10', '2024-09-25', '2025-06-01', 30000000, 120000000, 6),
(N'Khai thác và Tái chế Rác thải', N'Ứng dụng công nghệ xử lý rác tiên tiến', 100, '2023-12-01', '2024-10-10', '2025-11-20', 70000000, 220000000, 1),
(N'Hệ thống Cảnh báo Thiên tai', N'Triển khai công nghệ giám sát thời tiết', 100, '2024-02-10', '2024-11-30', '2026-05-01', 65000000, 200000000, 2),
(N'Mở rộng Trung tâm Dữ liệu Quốc gia', N'Nâng cấp hệ thống lưu trữ và bảo mật', 100, '2024-05-15', '2025-01-10', '2026-08-25', 85000000, 320000000, 3),
(N'Chương trình Hỗ trợ Khởi nghiệp', N'Cung cấp vốn và đào tạo cho startup', 100, '2023-08-05', '2024-07-20', '2025-04-30', 45000000, 150000000, 4),
-- Các dự án chưa hoàn thành (Completion < 100%)
(N'Ứng dụng AI vào An ninh mạng', N'Bảo mật hệ thống dữ liệu', 80, '2024-05-10', NULL, '2025-12-31', 60000000, 180000000, 3),
(N'Phát triển Thương mại Điện tử', N'Tối ưu hóa nền tảng bán hàng online', 50, '2023-11-15', NULL, '2024-09-10', 40000000, 120000000, 4),
(N'Bảo tồn Di sản Văn hóa', N'Khôi phục và gìn giữ di sản', 65, '2024-02-01', NULL, '2026-08-20', 50000000, 140000000, 5),
(N'Ứng dụng IoT vào Đô thị thông minh', N'Công nghệ quản lý đô thị', 75, '2024-03-10', NULL, '2026-06-30', 110000000, 330000000, 6),
(N'Trung tâm Nghiên cứu Sinh học', N'Ứng dụng công nghệ gen', 35, '2024-03-20', NULL, '2026-11-15', 30000000, 90000000, 1),
(N'Xây dựng Nhà Ở Xã Hội', N'Cung cấp nhà ở cho người lao động', 85, '2023-06-15', NULL, '2026-01-01', 100000000, 280000000, 6),
(N'Phát triển Nền tảng Metaverse', N'Triển khai công nghệ thực tế ảo', 70, '2024-06-01', NULL, '2026-09-15', 80000000, 250000000, 5),
(N'Ứng dụng Blockchain trong Logistics', N'Quản lý chuỗi cung ứng bằng blockchain', 55, '2024-03-20', NULL, '2026-06-30', 75000000, 220000000, 6),
(N'Nghiên cứu Trí tuệ Nhân tạo Y tế', N'Ứng dụng AI vào chẩn đoán bệnh', 45, '2024-02-15', NULL, '2025-12-31', 60000000, 180000000, 1),
(N'Xây dựng Nhà Máy Sản xuất Pin Điện', N'Phát triển năng lượng sạch cho xe điện', 60, '2024-04-10', NULL, '2026-08-20', 90000000, 300000000, 2),
(N'Triển khai Hệ thống Giao thông Thông minh', N'Ứng dụng công nghệ số vào quản lý giao thông', 50, '2023-10-10', NULL, '2025-07-01', 85000000, 280000000, 3);




SELECT * FROM Employee

SELECT * FROM Account

SELECT * FROM POSITION

SELECT * FROM DEPARTMENT	

SELECT * FROM Project	

/*
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
*/

--delete department where departmentid = 1

/*
SELECT 
	e.EmployeeCode, 
	e.LastName + ' ' + e.FirstName AS FullName, 
	e.Tel, 
    p.PositionName AS PositionName, 
	COALESCE(d.DepartmentName, '') AS DepartmentName 
FROM Employee e 
JOIN Position p ON e.PositionID = p.PositionID 
JOIN Department d ON e.DepartmentID = d.DepartmentID
WHERE (EmployeeCode LIKE '%0%') OR (e.LastName + ' ' + e.FirstName LIKE '%0%') OR (e.Tel LIKE '%0%') OR (PositionName LIKE '%0%') OR (DepartmentName LIKE '%0%')
ORDER BY p.PositionID
OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY
*/

/*
SELECT 
	e.EmployeeCode, 
	e.LastName + ' ' + e.FirstName AS Fullname, 
	e.Tel, 
	e.Gender, 
	e.BirthDate, 
	e.[Address],
	e.BasicSalary, 
	p.PositionID, 
	p.PositionName AS PositionName, 
	COALESCE(d.DepartmentID, -1) AS DepartmentID,
	COALESCE(d.DepartmentName, '') AS DepartmentName, 
	a.Username, 
	a.[Password]
FROM Employee e
JOIN Position p ON e.PositionID = p.PositionID
LEFT JOIN Department d ON e.DepartmentID = d.DepartmentID
JOIN Account a ON e.EmployeeID = a.EmployeeID
WHERE e.EmployeeCode = 'NV0011'
*/

/*
SELECT 1
FROM Employee 
WHERE DepartmentID = ? and PositionID = 2 
*/

/*
SELECT 
    d.DepartmentName, 
    COUNT(p.ProjectID) AS TotalCompletedProject
FROM Project p
JOIN Department d ON p.DepartmentID = d.DepartmentID
WHERE p.Completion = 100
GROUP BY d.DepartmentName

UNION 

SELECT d.DepartmentName, 0 
FROM Department d
WHERE d.DepartmentID NOT IN (
    SELECT DISTINCT p.DepartmentID 
    FROM Project p 
    WHERE p.Completion = 100);
*/



