USE [master]
GO
/****** Object:  Database [HotelBooking]    Script Date: 10/10/2021 8:30:28 PM ******/
CREATE DATABASE [HotelBooking]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HotelBooking', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\HotelBooking.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'HotelBooking_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\HotelBooking_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [HotelBooking] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HotelBooking].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HotelBooking] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HotelBooking] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HotelBooking] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HotelBooking] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HotelBooking] SET ARITHABORT OFF 
GO
ALTER DATABASE [HotelBooking] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HotelBooking] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HotelBooking] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HotelBooking] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HotelBooking] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HotelBooking] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HotelBooking] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HotelBooking] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HotelBooking] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HotelBooking] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HotelBooking] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HotelBooking] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HotelBooking] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HotelBooking] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HotelBooking] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HotelBooking] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HotelBooking] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HotelBooking] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HotelBooking] SET  MULTI_USER 
GO
ALTER DATABASE [HotelBooking] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HotelBooking] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HotelBooking] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HotelBooking] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [HotelBooking] SET DELAYED_DURABILITY = DISABLED 
GO
USE [HotelBooking]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 10/10/2021 8:30:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[email] [varchar](100) NOT NULL,
	[phone] [nchar](20) NULL,
	[name] [nvarchar](50) NULL,
	[address] [text] NULL,
	[createDate] [date] NULL,
	[accountRoleID] [int] NULL,
	[password] [text] NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AccountRole]    Script Date: 10/10/2021 8:30:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccountRole](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nchar](10) NULL,
 CONSTRAINT [PK_AccountRole] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Booking]    Script Date: 10/10/2021 8:30:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Booking](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[hotelID] [int] NULL,
	[accountEmail] [varchar](100) NULL,
	[RoomAmount] [int] NULL,
	[totalPrice] [float] NULL,
	[bookingDate] [date] NULL,
	[BookingStattusID] [int] NULL,
 CONSTRAINT [PK_Booking] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[BookingDetail]    Script Date: 10/10/2021 8:30:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookingDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[bookingID] [int] NULL,
	[price] [float] NULL,
	[roomId] [int] NULL,
	[roomType] [int] NULL,
	[status] [bit] NULL,
	[DateFrom] [datetime] NULL,
	[DateTo] [datetime] NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_BookingDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BookIngStatus]    Script Date: 10/10/2021 8:30:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookIngStatus](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[description] [text] NULL,
 CONSTRAINT [PK_BookIngStatus] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Hotel]    Script Date: 10/10/2021 8:30:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Hotel](
	[hotelID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[address] [text] NULL,
	[phone] [nchar](20) NULL,
	[image] [text] NULL,
	[hotelStatusID] [int] NULL,
 CONSTRAINT [PK_Hotel] PRIMARY KEY CLUSTERED 
(
	[hotelID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HotelStatus]    Script Date: 10/10/2021 8:30:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HotelStatus](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[description] [text] NULL,
 CONSTRAINT [PK_HotelStatus] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Room]    Script Date: 10/10/2021 8:30:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Room](
	[roomId] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[hotelID] [int] NULL,
	[roomNumber] [int] NULL,
	[price] [float] NULL,
	[RoomTypeId] [int] NULL,
	[Description] [text] NULL,
	[Image] [text] NULL,
	[RoomStatusId] [int] NULL,
 CONSTRAINT [PK_Room] PRIMARY KEY CLUSTERED 
(
	[roomId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[RoomStatus]    Script Date: 10/10/2021 8:30:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoomStatus](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[description] [text] NULL,
 CONSTRAINT [PK_RoomStatus] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[RoomType]    Script Date: 10/10/2021 8:30:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoomType](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[price] [float] NULL,
	[roomAmount] [int] NULL,
	[Description] [text] NULL,
 CONSTRAINT [PK_RoomType] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[Account] ([email], [phone], [name], [address], [createDate], [accountRoleID], [password]) VALUES (N'abc@gmail.com                                                                                       ', N'09090909            ', N'thien an', N'District 2', CAST(N'2021-09-29' AS Date), 2, N'123                                               ')
INSERT [dbo].[Account] ([email], [phone], [name], [address], [createDate], [accountRoleID], [password]) VALUES (N'abc123@gmail.com', N'0932204824          ', N'thien an', N'213123', CAST(N'2021-10-10' AS Date), 2, N'123                                               ')
INSERT [dbo].[Account] ([email], [phone], [name], [address], [createDate], [accountRoleID], [password]) VALUES (N'Th3007@gmail.com', N'0932204824          ', N'thien an', N'123123', CAST(N'2021-10-10' AS Date), 2, N'$2a$12$qx0DkSlDapAG1eEn6YIUT.8JkxonWwpbfb6u6qKykYFbihBOgiLJu')
SET IDENTITY_INSERT [dbo].[AccountRole] ON 

INSERT [dbo].[AccountRole] ([id], [name]) VALUES (1, N'admin     ')
INSERT [dbo].[AccountRole] ([id], [name]) VALUES (2, N'user      ')
SET IDENTITY_INSERT [dbo].[AccountRole] OFF
SET IDENTITY_INSERT [dbo].[Booking] ON 

INSERT [dbo].[Booking] ([id], [hotelID], [accountEmail], [RoomAmount], [totalPrice], [bookingDate], [BookingStattusID]) VALUES (12, 1, N'abc@gmail.com', 4, 8800, CAST(N'2021-10-08' AS Date), 3)
INSERT [dbo].[Booking] ([id], [hotelID], [accountEmail], [RoomAmount], [totalPrice], [bookingDate], [BookingStattusID]) VALUES (13, 1, N'abc@gmail.com', 2, 2200, CAST(N'2021-10-08' AS Date), 1)
INSERT [dbo].[Booking] ([id], [hotelID], [accountEmail], [RoomAmount], [totalPrice], [bookingDate], [BookingStattusID]) VALUES (14, 1, N'abc@gmail.com', 3, 7700.0000000000009, CAST(N'2021-10-09' AS Date), 1)
SET IDENTITY_INSERT [dbo].[Booking] OFF
SET IDENTITY_INSERT [dbo].[BookingDetail] ON 

INSERT [dbo].[BookingDetail] ([id], [bookingID], [price], [roomId], [roomType], [status], [DateFrom], [DateTo], [quantity]) VALUES (13, 12, 1000, 1, 1, 0, CAST(N'2021-10-11 17:45:00.000' AS DateTime), CAST(N'2021-10-20 13:45:00.000' AS DateTime), 1)
INSERT [dbo].[BookingDetail] ([id], [bookingID], [price], [roomId], [roomType], [status], [DateFrom], [DateTo], [quantity]) VALUES (14, 12, 1000, 3, 1, 0, CAST(N'2021-10-11 17:45:00.000' AS DateTime), CAST(N'2021-10-20 13:45:00.000' AS DateTime), 1)
INSERT [dbo].[BookingDetail] ([id], [bookingID], [price], [roomId], [roomType], [status], [DateFrom], [DateTo], [quantity]) VALUES (15, 12, 1000, 4, 1, 0, CAST(N'2021-10-11 17:45:00.000' AS DateTime), CAST(N'2021-10-20 13:45:00.000' AS DateTime), 1)
INSERT [dbo].[BookingDetail] ([id], [bookingID], [price], [roomId], [roomType], [status], [DateFrom], [DateTo], [quantity]) VALUES (16, 12, 5000, 13, 3, 0, CAST(N'2021-10-11 17:45:00.000' AS DateTime), CAST(N'2021-10-20 13:45:00.000' AS DateTime), 1)
INSERT [dbo].[BookingDetail] ([id], [bookingID], [price], [roomId], [roomType], [status], [DateFrom], [DateTo], [quantity]) VALUES (17, 13, 1000, 1, 1, 1, CAST(N'2021-01-07 10:50:00.000' AS DateTime), CAST(N'2021-01-15 14:45:00.000' AS DateTime), 1)
INSERT [dbo].[BookingDetail] ([id], [bookingID], [price], [roomId], [roomType], [status], [DateFrom], [DateTo], [quantity]) VALUES (18, 13, 1000, 5, 1, 1, CAST(N'2021-01-07 10:50:00.000' AS DateTime), CAST(N'2021-01-15 14:45:00.000' AS DateTime), 1)
INSERT [dbo].[BookingDetail] ([id], [bookingID], [price], [roomId], [roomType], [status], [DateFrom], [DateTo], [quantity]) VALUES (19, 14, 1000, 1, 1, 1, CAST(N'2021-12-01 05:45:00.000' AS DateTime), CAST(N'2021-12-22 13:45:00.000' AS DateTime), 1)
INSERT [dbo].[BookingDetail] ([id], [bookingID], [price], [roomId], [roomType], [status], [DateFrom], [DateTo], [quantity]) VALUES (20, 14, 1000, 4, 1, 1, CAST(N'2021-12-01 05:45:00.000' AS DateTime), CAST(N'2021-12-22 13:45:00.000' AS DateTime), 1)
INSERT [dbo].[BookingDetail] ([id], [bookingID], [price], [roomId], [roomType], [status], [DateFrom], [DateTo], [quantity]) VALUES (21, 14, 5000, 15, 3, 1, CAST(N'2021-12-01 05:45:00.000' AS DateTime), CAST(N'2021-12-22 13:45:00.000' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[BookingDetail] OFF
SET IDENTITY_INSERT [dbo].[BookIngStatus] ON 

INSERT [dbo].[BookIngStatus] ([id], [name], [description]) VALUES (1, N'Nhận phòng', N'Nhan Phong')
INSERT [dbo].[BookIngStatus] ([id], [name], [description]) VALUES (2, N'Trả phòng', N'tra phong')
INSERT [dbo].[BookIngStatus] ([id], [name], [description]) VALUES (3, N'Xoa phong', N'xoa phong')
SET IDENTITY_INSERT [dbo].[BookIngStatus] OFF
SET IDENTITY_INSERT [dbo].[Hotel] ON 

INSERT [dbo].[Hotel] ([hotelID], [name], [address], [phone], [image], [hotelStatusID]) VALUES (1, N'L Hotel', N'District 2', N'09090909            ', N'image/1246280_16061017110043391702.jpg', 1)
INSERT [dbo].[Hotel] ([hotelID], [name], [address], [phone], [image], [hotelStatusID]) VALUES (2, N'N Hotel', N'District 3', N'0303030303          ', N'image/a82154f70124d973fb32feb216975a69.jpg', 1)
INSERT [dbo].[Hotel] ([hotelID], [name], [address], [phone], [image], [hotelStatusID]) VALUES (3, N'H Hotel', N'District 7', N'07070707            ', N'image/hotel-presidente-4s.jpg', 1)
INSERT [dbo].[Hotel] ([hotelID], [name], [address], [phone], [image], [hotelStatusID]) VALUES (4, N'A Hotel', N'District 4', N'04040404            ', N'image/pearl-river-hotel-home1.jpg', 1)
INSERT [dbo].[Hotel] ([hotelID], [name], [address], [phone], [image], [hotelStatusID]) VALUES (5, N'U Hotel', N'District 5', N'05050505            ', N'image/rex-hotel-vietnam-home-slideshow-image-01.jpg', 1)
SET IDENTITY_INSERT [dbo].[Hotel] OFF
SET IDENTITY_INSERT [dbo].[HotelStatus] ON 

INSERT [dbo].[HotelStatus] ([id], [name], [description]) VALUES (1, N'Còn phòng', N'trên 1 phòng')
INSERT [dbo].[HotelStatus] ([id], [name], [description]) VALUES (2, N'Hết phòng', N'không còn phòng tr?ng')
SET IDENTITY_INSERT [dbo].[HotelStatus] OFF
SET IDENTITY_INSERT [dbo].[Room] ON 

INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (1, N'Single room 1', 1, 204, 1000, 1, N'Phòng d?p', N'image/room/room1.jpg', 1)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (2, N'Single room 2', 3, 205, 1000, 1, N'Phòng d?p', N'image/room/room1.jpg', 1)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (3, N'Single room 2', 1, 206, 1000, 1, N'Phòng d?p', N'image/room/room1.jpg', 1)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (4, N'Single room 3', 2, 207, 1000, 1, N'Phòng d?p', N'image/room/room1.jpg', 1)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (5, N'Single room 4', 2, 208, 1000, 1, N'Phòng d?p', N'image/room/room1.jpg', 1)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (6, N'Single room 5', 2, 209, 1000, 1, N'Phòng d?p', N'image/room/room1.jpg', 1)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (7, N'Single room 6', 1, 210, 1000, 1, N'Phòng d?p', N'image/room/room1.jpg', 1)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (8, N'Single room 7', 3, 211, 1000, 1, N'Phòng d?p', N'image/room/room1.jpg', 1)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (9, N'Double room 1', 1, 101, 2000, 2, N'phong dep', N'image/room/room2.jpg', 2)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (10, N'Double room 2', 2, 102, 2000, 2, N'phong dep', N'image/room/room2.jpg', 2)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (11, N'Double room 3', 3, 103, 2000, 2, N'phong dep', N'image/room/room2.jpg', 2)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (12, N'Double room 4', 3, 104, 2000, 2, N'phong dep', N'image/room/room2.jpg', 2)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (13, N'Family room 1', 2, 303, 5000, 3, N'phong dep', N'image/room/room3.jpg', 2)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (14, N'Family room 2', 1, 304, 5000, 3, N'phong dep', N'image/room/room3.jpg', 2)
INSERT [dbo].[Room] ([roomId], [name], [hotelID], [roomNumber], [price], [RoomTypeId], [Description], [Image], [RoomStatusId]) VALUES (15, N' Family room 3', 3, 305, 5000, 3, N'phong dep', N'image/room/room3.jpg', 2)
SET IDENTITY_INSERT [dbo].[Room] OFF
SET IDENTITY_INSERT [dbo].[RoomStatus] ON 

INSERT [dbo].[RoomStatus] ([id], [name], [description]) VALUES (1, N'Phòng trống', N'chua có ngu?i ?')
INSERT [dbo].[RoomStatus] ([id], [name], [description]) VALUES (2, N'Phòng đã có người ở', N'Phòng dã có ngu?i ?')
INSERT [dbo].[RoomStatus] ([id], [name], [description]) VALUES (3, N'Phòng đang chờ vệ sinh', N'Phòng dang ch? v? sinh')
INSERT [dbo].[RoomStatus] ([id], [name], [description]) VALUES (4, N'Phòng đang chờ nâng cấp cơ sở vật chất', N'Phòng dang ch? nâng c?p co s? v?t ch?t')
SET IDENTITY_INSERT [dbo].[RoomStatus] OFF
SET IDENTITY_INSERT [dbo].[RoomType] ON 

INSERT [dbo].[RoomType] ([id], [name], [price], [roomAmount], [Description]) VALUES (1, N'Single', 1000, 5, N'Phòng cho m?t ngu?i ?')
INSERT [dbo].[RoomType] ([id], [name], [price], [roomAmount], [Description]) VALUES (2, N'Family', 5000, 10, N'Phòng gia dình')
INSERT [dbo].[RoomType] ([id], [name], [price], [roomAmount], [Description]) VALUES (3, N'Double', 2000, 10, N'Phòng dôi')
SET IDENTITY_INSERT [dbo].[RoomType] OFF
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_AccountRole] FOREIGN KEY([accountRoleID])
REFERENCES [dbo].[AccountRole] ([id])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_AccountRole]
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD  CONSTRAINT [FK_Booking_Account] FOREIGN KEY([accountEmail])
REFERENCES [dbo].[Account] ([email])
GO
ALTER TABLE [dbo].[Booking] CHECK CONSTRAINT [FK_Booking_Account]
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD  CONSTRAINT [FK_Booking_BookIngStatus] FOREIGN KEY([BookingStattusID])
REFERENCES [dbo].[BookIngStatus] ([id])
GO
ALTER TABLE [dbo].[Booking] CHECK CONSTRAINT [FK_Booking_BookIngStatus]
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD  CONSTRAINT [FK_Booking_Hotel] FOREIGN KEY([hotelID])
REFERENCES [dbo].[Hotel] ([hotelID])
GO
ALTER TABLE [dbo].[Booking] CHECK CONSTRAINT [FK_Booking_Hotel]
GO
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [FK_BookingDetail_Booking] FOREIGN KEY([bookingID])
REFERENCES [dbo].[Booking] ([id])
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [FK_BookingDetail_Booking]
GO
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [FK_BookingDetail_Room1] FOREIGN KEY([roomId])
REFERENCES [dbo].[Room] ([roomId])
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [FK_BookingDetail_Room1]
GO
ALTER TABLE [dbo].[Hotel]  WITH CHECK ADD  CONSTRAINT [FK_Hotel_HotelStatus] FOREIGN KEY([hotelStatusID])
REFERENCES [dbo].[HotelStatus] ([id])
GO
ALTER TABLE [dbo].[Hotel] CHECK CONSTRAINT [FK_Hotel_HotelStatus]
GO
ALTER TABLE [dbo].[Room]  WITH CHECK ADD  CONSTRAINT [FK_Room_Hotel] FOREIGN KEY([hotelID])
REFERENCES [dbo].[Hotel] ([hotelID])
GO
ALTER TABLE [dbo].[Room] CHECK CONSTRAINT [FK_Room_Hotel]
GO
ALTER TABLE [dbo].[Room]  WITH CHECK ADD  CONSTRAINT [FK_Room_RoomStatus] FOREIGN KEY([RoomStatusId])
REFERENCES [dbo].[RoomStatus] ([id])
GO
ALTER TABLE [dbo].[Room] CHECK CONSTRAINT [FK_Room_RoomStatus]
GO
ALTER TABLE [dbo].[Room]  WITH CHECK ADD  CONSTRAINT [FK_Room_RoomType] FOREIGN KEY([RoomTypeId])
REFERENCES [dbo].[RoomType] ([id])
GO
ALTER TABLE [dbo].[Room] CHECK CONSTRAINT [FK_Room_RoomType]
GO
USE [master]
GO
ALTER DATABASE [HotelBooking] SET  READ_WRITE 
GO
