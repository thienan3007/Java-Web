USE [master]
GO
/****** Object:  Database [YellowMoonShop]    Script Date: 9/23/2021 9:58:26 PM ******/
CREATE DATABASE [YellowMoonShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'YellowMoonShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\YellowMoonShop.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'YellowMoonShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\YellowMoonShop_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [YellowMoonShop] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [YellowMoonShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [YellowMoonShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [YellowMoonShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [YellowMoonShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [YellowMoonShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [YellowMoonShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [YellowMoonShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [YellowMoonShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [YellowMoonShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [YellowMoonShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [YellowMoonShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [YellowMoonShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [YellowMoonShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [YellowMoonShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [YellowMoonShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [YellowMoonShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [YellowMoonShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [YellowMoonShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [YellowMoonShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [YellowMoonShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [YellowMoonShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [YellowMoonShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [YellowMoonShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [YellowMoonShop] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [YellowMoonShop] SET  MULTI_USER 
GO
ALTER DATABASE [YellowMoonShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [YellowMoonShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [YellowMoonShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [YellowMoonShop] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [YellowMoonShop] SET DELAYED_DURABILITY = DISABLED 
GO
USE [YellowMoonShop]
GO
/****** Object:  Table [dbo].[tblCakes]    Script Date: 9/23/2021 9:58:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCakes](
	[name] [nvarchar](50) NOT NULL,
	[price] [float] NOT NULL,
	[createDate] [date] NOT NULL,
	[expirationDate] [date] NOT NULL,
	[categoryID] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[statusID] [int] NOT NULL,
	[description] [text] NOT NULL,
	[image] [text] NOT NULL,
	[ID] [int] IDENTITY(1,1) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblCakesStatus]    Script Date: 9/23/2021 9:58:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCakesStatus](
	[statusID] [int] NOT NULL,
	[statusName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 9/23/2021 9:58:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[categoryID] [int] NOT NULL,
	[categoryName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 9/23/2021 9:58:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[orderID] [int] NOT NULL,
	[cakesID] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
	[statusID] [int] NOT NULL,
	[ID] [int] IDENTITY(1,1) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrders]    Script Date: 9/23/2021 9:58:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrders](
	[userID] [int] NULL,
	[address] [nvarchar](200) NOT NULL,
	[totalMoney] [float] NOT NULL,
	[orderDate] [date] NOT NULL,
	[statusID] [int] NOT NULL,
	[paymentStatus] [bit] NOT NULL,
	[paymentMethodID] [int] NOT NULL,
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[phone] [nchar](10) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[email] [nvarchar](100) NULL,
 CONSTRAINT [PK__tblOrder__3214EC2703ECAD6C] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrdersDetailStatus]    Script Date: 9/23/2021 9:58:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrdersDetailStatus](
	[statusID] [int] NOT NULL,
	[statusName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrdersStatus]    Script Date: 9/23/2021 9:58:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrdersStatus](
	[statusID] [int] NOT NULL,
	[statusName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblPaymentMethod]    Script Date: 9/23/2021 9:58:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPaymentMethod](
	[paymentMethodID] [int] NOT NULL,
	[paymentMethodName] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[paymentMethodID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 9/23/2021 9:58:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [int] NOT NULL,
	[roleName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 9/23/2021 9:58:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [int] NOT NULL,
	[fullname] [nvarchar](100) NOT NULL,
	[phone] [nchar](20) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[address] [text] NOT NULL,
	[createDate] [date] NOT NULL,
	[roleID] [int] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK__tblUsers__CB9A1CDFD5F9CC49] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[tblCakes] ON 

INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'Thiên Ân', 2200, CAST(N'2021-01-01' AS Date), CAST(N'2021-09-01' AS Date), 5, 1000, 1, N'qua ngon', N'img/150426555192962-b--nh-trung-thu-kh--ng-n--n---n-nhi---u.jpg', 1)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'bánh trung thu cuối cùng', 1000, CAST(N'2021-09-01' AS Date), CAST(N'2021-09-30' AS Date), 5, 123, 1, N'1', N'img/camnhi-193822093847-banh-trung-thu-truyen-thong.jpg', 11)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'1', 1231, CAST(N'2021-09-01' AS Date), CAST(N'2021-09-30' AS Date), 3, 123, 3, N'1', N'img/150426555192962-b--nh-trung-thu-kh--ng-n--n---n-nhi---u.jpg', 12)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'Banh trung', 123, CAST(N'2021-09-01' AS Date), CAST(N'2021-09-30' AS Date), 5, 123, 1, N'1', N'img/y-nghia-cua-banh-trung-thu.jpg', 13)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'Bánh mới', 123, CAST(N'2021-09-02' AS Date), CAST(N'2021-09-30' AS Date), 5, 123, 1, N'asd', N'img/y-nghia-cua-banh-trung-thu.jpg', 15)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'Bánh mới 1', 2200, CAST(N'2021-09-03' AS Date), CAST(N'2021-09-30' AS Date), 5, 123, 1, N'123123', N'img/y-nghia-cua-banh-trung-thu.jpg', 16)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'bánh trung thu cuối cùng', 1000, CAST(N'2021-09-01' AS Date), CAST(N'2021-09-30' AS Date), 2, 119, 1, N'123', N'img/y-nghia-cua-banh-trung-thu.jpg', 17)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'Banh trung', 123, CAST(N'2021-09-01' AS Date), CAST(N'2021-09-30' AS Date), 5, 123, 1, N'1', N'img/y-nghia-cua-banh-trung-thu.jpg', 20)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'Thiên Ân', 1000, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), 5, 0, 1, N'123123', N'img/1_banh_fxlr.jpg', 21)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'Thiên Ân', 12, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), 2, 123, 1, N'13123123', N'img/150426555192962-b--nh-trung-thu-kh--ng-n--n---n-nhi---u.jpg', 22)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'Thiên Ân', 12, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), 5, 123, 1, N'123123', N'img/camnhi-193822093847-banh-trung-thu-truyen-thong.jpg', 23)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'bánh trung thu cuối cùng', 12, CAST(N'2021-09-21' AS Date), CAST(N'2021-09-30' AS Date), 5, 0, 1, N'123123', N'img/camnhi-193822093847-banh-trung-thu-truyen-thong.jpg', 24)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'Test quantity', 2200, CAST(N'2021-09-01' AS Date), CAST(N'2021-09-30' AS Date), 1, 0, 1, N'r?t ngon', N'img/y-nghia-cua-banh-trung-thu.jpg', 25)
INSERT [dbo].[tblCakes] ([name], [price], [createDate], [expirationDate], [categoryID], [quantity], [statusID], [description], [image], [ID]) VALUES (N'Quantity test', 2200, CAST(N'2021-09-02' AS Date), CAST(N'2021-09-24' AS Date), 1, 0, 1, N'1', N'img/camnhi-193822093847-banh-trung-thu-truyen-thong.jpg', 26)
SET IDENTITY_INSERT [dbo].[tblCakes] OFF
INSERT [dbo].[tblCakesStatus] ([statusID], [statusName]) VALUES (1, N'Bánh còn hạn')
INSERT [dbo].[tblCakesStatus] ([statusID], [statusName]) VALUES (2, N'Bánh hết hạn')
INSERT [dbo].[tblCakesStatus] ([statusID], [statusName]) VALUES (3, N'Bánh chờ về hàng')
INSERT [dbo].[tblCakesStatus] ([statusID], [statusName]) VALUES (4, N'Bánh hết hàng')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (1, N'Bánh Ngọt')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (2, N'Bánh Mặn')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (3, N'Bánh Hình Chữ Nhật')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (4, N'Bánh Hình Tròn')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (5, N'Bánh Hình Tam Giác')
SET IDENTITY_INSERT [dbo].[tblOrderDetail] ON 

INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (13, 1, 1, 1200, 1, 1)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (14, 13, 1, 123, 1, 2)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (14, 1, 1, 1200, 1, 3)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (14, 15, 1, 123, 1, 4)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (15, 13, 1, 123, 1, 5)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (16, 13, 2, 246, 1, 6)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (16, 15, 1, 123, 1, 7)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (16, 16, 2, 246, 1, 8)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (17, 21, 1, 1000, 1, 9)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (18, 21, 3, 3000, 1, 10)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (18, 23, 2, 24, 1, 11)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (18, 22, 1, 12, 1, 12)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (19, 21, 1, 1000, 1, 13)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (19, 22, 1, 12, 1, 14)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (20, 21, 1, 1000, 1, 15)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (20, 22, 1, 12, 1, 16)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (21, 23, 5, 60, 1, 17)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (21, 21, 1, 1000, 1, 18)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (21, 16, 2, 4400, 1, 19)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (21, 15, 1, 123, 1, 20)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (21, 24, 2, 24, 1, 21)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (22, 24, 1, 12, 1, 22)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (23, 24, 1, 12, 1, 23)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (24, 21, 12, 12000, 1, 24)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (25, 26, 1, 2200, 1, 25)
INSERT [dbo].[tblOrderDetail] ([orderID], [cakesID], [quantity], [price], [statusID], [ID]) VALUES (26, 17, 4, 4000, 1, 26)
SET IDENTITY_INSERT [dbo].[tblOrderDetail] OFF
SET IDENTITY_INSERT [dbo].[tblOrders] ON 

INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 du?ng 31, Khu ph? 3', 1320, CAST(N'2021-09-21' AS Date), 1, 1, 2, 3, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 du?ng 31, Khu ph? 3', 1320, CAST(N'2021-09-21' AS Date), 1, 1, 2, 4, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 du?ng 31, Khu ph? 3', 1320, CAST(N'2021-09-21' AS Date), 1, 1, 2, 5, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 du?ng 31, Khu ph? 3', 1320, CAST(N'2021-09-21' AS Date), 1, 1, 2, 6, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 du?ng 31, Khu ph? 3', 2640, CAST(N'2021-09-21' AS Date), 1, 1, 2, 7, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'123', 123, CAST(N'2021-01-01' AS Date), 1, 1, 1, 8, N'123123123 ', N'123', N'123')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 du?ng 31, Khu ph? 3', 1320, CAST(N'2021-09-21' AS Date), 1, 1, 2, 9, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 du?ng 31, Khu ph? 3', 1320, CAST(N'2021-09-21' AS Date), 1, 1, 2, 10, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'123', 123, CAST(N'2021-01-01' AS Date), 1, 1, 1, 11, N'123123123 ', N'123', N'123')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 du?ng 31, Khu ph? 3', 1320, CAST(N'2021-09-21' AS Date), 1, 1, 2, 12, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 du?ng 31, Khu ph? 3', 1320, CAST(N'2021-09-21' AS Date), 1, 1, 2, 13, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 đường 31, Khu phố 3', 1590.6000000000001, CAST(N'2021-09-21' AS Date), 1, 1, 2, 14, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (2, N'123', 135.3, CAST(N'2021-09-21' AS Date), 1, 1, 2, 15, N'123123123 ', N'truong thien phuc', NULL)
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (2, N'123', 676.5, CAST(N'2021-09-21' AS Date), 1, 1, 1, 16, N'123123123 ', N'truong thien phuc', NULL)
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 đường 31, Khu phố 3', 1100, CAST(N'2021-09-22' AS Date), 1, 1, 1, 17, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (2, N'123', 3339.6000000000004, CAST(N'2021-09-23' AS Date), 1, 1, 1, 18, N'123123123 ', N'truong thien phuc', NULL)
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (2, N'123', 1113.2, CAST(N'2021-09-23' AS Date), 1, 1, 1, 19, N'123123123 ', N'truong thien phuc', NULL)
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (2, N'123', 1113.2, CAST(N'2021-09-23' AS Date), 1, 1, 1, 20, N'123123123 ', N'truong thien phuc', NULL)
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (2, N'123', 6167.7000000000007, CAST(N'2021-09-23' AS Date), 1, 1, 1, 21, N'123123123 ', N'truong thien phuc', NULL)
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 đường 31, Khu phố 3', 13.200000000000001, CAST(N'2021-09-23' AS Date), 1, 1, 2, 22, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 đường 31, Khu phố 3', 13.200000000000001, CAST(N'2021-09-23' AS Date), 1, 1, 2, 23, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (NULL, N'12/11/9 đường 31, Khu phố 3', 13200.000000000002, CAST(N'2021-09-23' AS Date), 1, 1, 2, 24, N'0932204824', N'Thiên Ân', N'antruong30072000@gmail.com')
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (2, N'123', 2420, CAST(N'2021-09-23' AS Date), 1, 1, 2, 25, N'123123123 ', N'truong thien phuc', NULL)
INSERT [dbo].[tblOrders] ([userID], [address], [totalMoney], [orderDate], [statusID], [paymentStatus], [paymentMethodID], [ID], [phone], [name], [email]) VALUES (4, N'12/11/9 &#273;&#432;&#7901;ng 31, Khu ph&#7889; 3', 4400, CAST(N'2021-09-23' AS Date), 1, 1, 2, 26, N'0932204824', N'Nhu Quynh', NULL)
SET IDENTITY_INSERT [dbo].[tblOrders] OFF
INSERT [dbo].[tblOrdersDetailStatus] ([statusID], [statusName]) VALUES (1, N'Đang giao hàng')
INSERT [dbo].[tblOrdersDetailStatus] ([statusID], [statusName]) VALUES (2, N'Đã giao')
INSERT [dbo].[tblOrdersDetailStatus] ([statusID], [statusName]) VALUES (3, N'Hủy đơn')
INSERT [dbo].[tblOrdersStatus] ([statusID], [statusName]) VALUES (1, N'Đang giao hàng')
INSERT [dbo].[tblOrdersStatus] ([statusID], [statusName]) VALUES (2, N'Đã nhận hàng')
INSERT [dbo].[tblPaymentMethod] ([paymentMethodID], [paymentMethodName]) VALUES (1, N'Cash')
INSERT [dbo].[tblPaymentMethod] ([paymentMethodID], [paymentMethodName]) VALUES (2, N'Banking')
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (1, N'admin')
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (2, N'user')
INSERT [dbo].[tblUsers] ([userID], [fullname], [phone], [password], [address], [createDate], [roleID], [status]) VALUES (1, N'Truong Duc Thien An', N'093204824           ', N'123', N'123', CAST(N'2021-01-01' AS Date), 1, 1)
INSERT [dbo].[tblUsers] ([userID], [fullname], [phone], [password], [address], [createDate], [roleID], [status]) VALUES (2, N'truong thien phuc', N'123123123           ', N'123', N'123', CAST(N'2021-01-01' AS Date), 2, 1)
INSERT [dbo].[tblUsers] ([userID], [fullname], [phone], [password], [address], [createDate], [roleID], [status]) VALUES (3, N'0932204824', N'0932204824          ', N'123', N'12/11/9 &#273;&#432;&#7901;ng 31, Khu ph&#7889; 3', CAST(N'2021-09-23' AS Date), 2, 0)
INSERT [dbo].[tblUsers] ([userID], [fullname], [phone], [password], [address], [createDate], [roleID], [status]) VALUES (4, N'Nhu Quynh', N'0932204824          ', N'123', N'12/11/9 &#273;&#432;&#7901;ng 31, Khu ph&#7889; 3', CAST(N'2021-09-23' AS Date), 2, 0)
ALTER TABLE [dbo].[tblCakes]  WITH CHECK ADD  CONSTRAINT [fk_cakes_id_category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblCakes] CHECK CONSTRAINT [fk_cakes_id_category]
GO
ALTER TABLE [dbo].[tblCakes]  WITH CHECK ADD  CONSTRAINT [fk_cakes_id_status] FOREIGN KEY([statusID])
REFERENCES [dbo].[tblCakesStatus] ([statusID])
GO
ALTER TABLE [dbo].[tblCakes] CHECK CONSTRAINT [fk_cakes_id_status]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [fk_orderDetail_cakesID_cakes] FOREIGN KEY([cakesID])
REFERENCES [dbo].[tblCakes] ([ID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [fk_orderDetail_cakesID_cakes]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [fk_orderdetail_id_status] FOREIGN KEY([statusID])
REFERENCES [dbo].[tblOrdersDetailStatus] ([statusID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [fk_orderdetail_id_status]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [fk_orderDetail_orderID_order] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrders] ([ID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [fk_orderDetail_orderID_order]
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD  CONSTRAINT [fk_orders_id_status] FOREIGN KEY([statusID])
REFERENCES [dbo].[tblOrdersStatus] ([statusID])
GO
ALTER TABLE [dbo].[tblOrders] CHECK CONSTRAINT [fk_orders_id_status]
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD  CONSTRAINT [fk_orders_id_user] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblOrders] CHECK CONSTRAINT [fk_orders_id_user]
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD  CONSTRAINT [fk_orders_paymentthodid_paymentmethod] FOREIGN KEY([paymentMethodID])
REFERENCES [dbo].[tblPaymentMethod] ([paymentMethodID])
GO
ALTER TABLE [dbo].[tblOrders] CHECK CONSTRAINT [fk_orders_paymentthodid_paymentmethod]
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD  CONSTRAINT [fk_users_id_role] FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers] CHECK CONSTRAINT [fk_users_id_role]
GO
USE [master]
GO
ALTER DATABASE [YellowMoonShop] SET  READ_WRITE 
GO
