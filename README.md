# Employee Management System

Hệ thống quản lý nhân viên cơ bản viết bằng **Java Servlet, JSP, JDBC**, theo mô hình **MVC**. 

## 📌 Tính năng chính

- ✅ Hiển thị danh sách nhân viên
- ➕ Thêm nhân viên mới
- ✏️ Sửa thông tin nhân viên
- ❌ Xoá nhân viên
- 🔍 Tìm kiếm nhân viên theo tên
- 🏢 Quản lý phòng ban (Position)
- 🗂️ Quản lý dự án (Project)
- 📊 Báo cáo phân công nhân viên vào dự án (Employee - Project Report)

---

## 🛠️ Công nghệ sử dụng

- Java Servlet & JSP
- JDBC (SQL Server)
- Apache Tomcat 10.1
- NetBeans IDE
- HTML, CSS, Bootstrap cơ bản

---

## 📁 Cấu trúc project

```plaintext
EmployeeManagementSystem/
├── web/              # Giao diện người dùng JSP
├── web/WEB-INF/web.xml     # Cấu hình Servlet
├── Controller/             # Servlet xử lý logic 
├── DAO/                    # Lớp truy xuất CSDL
├── DTO/                    # Lớp trung gian chứa dữ liệu (Data Transfer Object) giữa DAO, Servlet, và JSP
├── Model/                  # Lớp model
├── Utils/                  # Các lớp tiện ích dùng chung
├── Utils/JDBCUtil.java     # Thông tin kết nối cơ sở dữ liệu
├── listenner/              # Khởi tạo dữ liệu lúc server chạy
```

🧑‍💻 Hướng dẫn cài đặt
1. Mở **SQL Server Management Studio (SSMS)**.
2. Chạy hai file SQL có sẵn (theo thứ tự):
- DDL_ASM_PRJ301.sql
- DML_ASM_PRJ301.sql

🔧 Cấu hình kết nối trong Utils/JDBCUtil.java
- private final String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=ASM_PRJ301";
- private final static String userID = "sa";; // hoặc tên user SQL khác
- private final static String password = "123"; // hoặc password SQL khác

🔃 Chạy project
- Mở project bằng NetBeans
- Gắn server Apache Tomcat 10.1
- Chuột phải project → Run
- Truy cập trình duyệt:
👉 http://localhost:8080/EmployeeManagementSystem

