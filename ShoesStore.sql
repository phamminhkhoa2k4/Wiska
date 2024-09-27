CREATE DATABASE ShoesStore;
DROP DATABASE ShoesStore;
GO
USE ShoesStore;
GO

CREATE TABLE Products (
  product_id INT PRIMARY KEY IDENTITY (1,1),
  title VARCHAR(255) NOT NULL,
  thumnail TEXT NOT NULL,
  description Text NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  category VARCHAR(50),
  availability VARCHAR(50),
  size VARCHAR(50),
  weight INT,
  material VARCHAR(100),
  color VARCHAR(50),
  designs VARCHAR(255),
  category_id INT NULL,
  brand_id INT NULL,
  FOREIGN KEY (category_id) REFERENCES Categories(category_id),
  FOREIGN KEY (brand_id) REFERENCES Brands(brand_id),
);
GO

CREATE TABLE Users (
  user_id INT PRIMARY KEY IDENTITY (1,1),
  username VARCHAR(50) NOT NULL UNIQUE,
  avartar TEXT NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL, -- Consider hashing passwords for security,
  role VARCHAR(50) DEFAULT 'user'
);
GO

CREATE TABLE Contacts (
  contact_id INT PRIMARY KEY IDENTITY (1,1),
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  subject VARCHAR(255),
  message TEXT
);
GO

CREATE TABLE Orders (
  order_id INT PRIMARY KEY IDENTITY (1,1),
  user_id int,
  order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  phone VARCHAR(20),
  name VARCHAR(255),
  address TEXT,
  paid BIT,
  FOREIGN KEY (user_id) REFERENCES Users(user_id), -- User foreign key
);
GO

CREATE TABLE Order_Detail (
  order_detail_id INT PRIMARY KEY IDENTITY (1,1),
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  order_id INT NOT NULL,
  FOREIGN KEY (product_id) REFERENCES Products(product_id), -- Product foreign key
  FOREIGN KEY (order_id) REFERENCES Orders(order_id) -- Order foreign key
);




CREATE TABLE Categories (
	category_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	category_name VARCHAR(255) NOT NULL,
);


CREATE TABLE Brands (
	brand_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	brand_name VARCHAR(255) NOT NULL,
);

CREATE TABLE Blogs (
	blog_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	user_id INT  NULL,
	thumbnail TEXT NOT NULL,
	title VARCHAR(255) NOT NULL,
	topic VARCHAR(255) NOT NULL,
	content Text NOT NULL,
	blog_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	blog_view INT ,
	FOREIGN KEY (user_id) REFERENCES Users(user_id),
);

