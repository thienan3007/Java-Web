USE [master]
GO
/****** Object:  Database [Blog]    Script Date: 10/31/2021 4:16:17 PM ******/
CREATE DATABASE [Blog]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Blog', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\Blog.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Blog_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\Blog_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Blog] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Blog].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Blog] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Blog] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Blog] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Blog] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Blog] SET ARITHABORT OFF 
GO
ALTER DATABASE [Blog] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Blog] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Blog] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Blog] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Blog] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Blog] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Blog] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Blog] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Blog] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Blog] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Blog] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Blog] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Blog] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Blog] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Blog] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Blog] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Blog] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Blog] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Blog] SET  MULTI_USER 
GO
ALTER DATABASE [Blog] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Blog] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Blog] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Blog] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Blog] SET DELAYED_DURABILITY = DISABLED 
GO
USE [Blog]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 10/31/2021 4:16:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[email] [varchar](200) NOT NULL,
	[name] [varchar](50) NULL,
	[password] [varchar](max) NULL,
	[statusID] [int] NULL,
	[roleId] [int] NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AccountStatus]    Script Date: 10/31/2021 4:16:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccountStatus](
	[accountStatusID] [int] IDENTITY(1,1) NOT NULL,
	[accountStatusName] [nvarchar](50) NULL,
 CONSTRAINT [PK_AccountStatus] PRIMARY KEY CLUSTERED 
(
	[accountStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Article]    Script Date: 10/31/2021 4:16:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Article](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](100) NULL,
	[Description] [nvarchar](max) NULL,
	[articelContent] [nvarchar](max) NULL,
	[authorEmail] [varchar](200) NULL,
	[postingDate] [date] NULL,
	[statusID] [int] NULL,
 CONSTRAINT [PK_Article] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ArticleStatus]    Script Date: 10/31/2021 4:16:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ArticleStatus](
	[statusId] [int] IDENTITY(1,1) NOT NULL,
	[statusName] [varchar](50) NULL,
 CONSTRAINT [PK_ArticleStatus] PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 10/31/2021 4:16:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Comment](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[articleID] [int] NULL,
	[commentContent] [nvarchar](max) NULL,
	[commentDate] [date] NULL,
	[statusID] [int] NULL,
	[userEmail] [varchar](200) NULL,
 CONSTRAINT [PK_Comment] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CommentStatus]    Script Date: 10/31/2021 4:16:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CommentStatus](
	[statusId] [int] IDENTITY(1,1) NOT NULL,
	[statusName] [varchar](50) NULL,
 CONSTRAINT [PK_CommentStatus] PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Role]    Script Date: 10/31/2021 4:16:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roleID] [int] IDENTITY(1,1) NOT NULL,
	[roleName] [nvarchar](100) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Account] ([email], [name], [password], [statusID], [roleId]) VALUES (N'abc@gmail.com', N'thien an', N'$2a$12$wCKt44OVLMXnficdPEXY4O7pCF346p4mQeap4k.erkgabKwnIsuAS', 1, 2)
INSERT [dbo].[Account] ([email], [name], [password], [statusID], [roleId]) VALUES (N'antruong@gmail.com', N'thien phuc', N'123', 1, 2)
INSERT [dbo].[Account] ([email], [name], [password], [statusID], [roleId]) VALUES (N'def@gmail.com', N'john', N'$2a$12$CbMP.1caec3H1jSrUh/GzeB8REAG6foNYU/MueMQwbEtYDMIr1RNy', 1, 2)
INSERT [dbo].[Account] ([email], [name], [password], [statusID], [roleId]) VALUES (N'Th3007@gmail.com', N'SE140048', N'$2a$12$4cD8fyUz.dA0yKMlU0M1Z.wWVp.pkzD3iMdQMWv0CDgll5v7hxr7u', 1, 2)
INSERT [dbo].[Account] ([email], [name], [password], [statusID], [roleId]) VALUES (N'thienan@gmail.com', N'nhu quynh', N'$2a$12$XM5tPKyS8YEzFke.Fx74MeDpQyqEHpC4Lhof5fmP8Oouxq1PNS6Ou', 1, 1)
SET IDENTITY_INSERT [dbo].[AccountStatus] ON 

INSERT [dbo].[AccountStatus] ([accountStatusID], [accountStatusName]) VALUES (1, N'new')
INSERT [dbo].[AccountStatus] ([accountStatusID], [accountStatusName]) VALUES (2, N'old')
SET IDENTITY_INSERT [dbo].[AccountStatus] OFF
SET IDENTITY_INSERT [dbo].[Article] ON 

INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (1, N'asca', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'asdasdzz', N'antruong@gmail.com', CAST(N'2021-10-28' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (2, N'asdas', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'antruong@gmail.com', CAST(N'2021-10-28' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (3, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 2)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (4, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 2)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (5, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 2)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (6, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (7, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (8, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (9, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (10, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (11, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (12, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (13, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (14, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (15, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (16, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (17, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (18, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (19, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (20, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (21, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (22, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (23, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (24, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (25, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'zz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (26, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (27, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (28, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (29, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (30, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (31, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (32, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (33, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (34, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (35, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (36, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (37, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (38, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (39, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (40, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (41, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (42, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadads', N'dasdad', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (43, N'asdadsa', N'adsadsadsasdadssdasdadsadasdadadadadadadaadasdadasdadadszzz', N'dasdadzzzzz', N'thienan@gmail.com', CAST(N'2021-10-29' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (44, N'Hoa vàng trên cỏ xanh', N'quá hay', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', N'abc@gmail.com', CAST(N'2021-10-30' AS Date), 2)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (45, N'Một ngày buồn', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.

The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.', N'abc@gmail.com', CAST(N'2021-10-30' AS Date), 3)
INSERT [dbo].[Article] ([id], [title], [Description], [articelContent], [authorEmail], [postingDate], [statusID]) VALUES (46, N'It is a long established fact that a reader', N'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).', N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.

The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.', N'Th3007@gmail.com', CAST(N'2021-10-31' AS Date), 3)
SET IDENTITY_INSERT [dbo].[Article] OFF
SET IDENTITY_INSERT [dbo].[ArticleStatus] ON 

INSERT [dbo].[ArticleStatus] ([statusId], [statusName]) VALUES (1, N'New')
INSERT [dbo].[ArticleStatus] ([statusId], [statusName]) VALUES (2, N'Delete')
INSERT [dbo].[ArticleStatus] ([statusId], [statusName]) VALUES (3, N'Active')
SET IDENTITY_INSERT [dbo].[ArticleStatus] OFF
SET IDENTITY_INSERT [dbo].[Comment] ON 

INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (1, 6, N'asdasda', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (2, 6, N'Ã¡dasdads', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (3, 6, N'Ã¡dasdasdasd', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (4, 6, N'Ã¡dasdasd', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (5, 6, N'Con sÆ° tá»­ Äáº¹p quÃ¡', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (6, 6, N'Con su tu dep qua', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (7, 2, N'Con su tu dep qua', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (8, 6, N'quá hay', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (9, 1, N'quá hay', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (10, 1, N'quá hay', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (11, 1, N'quá hay', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (12, 44, N'quá hay', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (13, 44, N'quá hay
', CAST(N'2021-10-30' AS Date), 1, N'antruong@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (14, 45, N'quá hay', CAST(N'2021-10-30' AS Date), 1, N'abc@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (15, 45, N'it''s really good', CAST(N'2021-10-30' AS Date), 1, N'def@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (16, 45, N'quá là hay', CAST(N'2021-10-31' AS Date), 1, N'Th3007@gmail.com')
INSERT [dbo].[Comment] ([id], [articleID], [commentContent], [commentDate], [statusID], [userEmail]) VALUES (17, 46, N'quá hay', CAST(N'2021-10-31' AS Date), 1, N'abc@gmail.com')
SET IDENTITY_INSERT [dbo].[Comment] OFF
SET IDENTITY_INSERT [dbo].[CommentStatus] ON 

INSERT [dbo].[CommentStatus] ([statusId], [statusName]) VALUES (1, N'new')
INSERT [dbo].[CommentStatus] ([statusId], [statusName]) VALUES (2, N'delete')
SET IDENTITY_INSERT [dbo].[CommentStatus] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (1, N'admin')
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (2, N'member')
SET IDENTITY_INSERT [dbo].[Role] OFF
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_AccountStatus] FOREIGN KEY([statusID])
REFERENCES [dbo].[AccountStatus] ([accountStatusID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_AccountStatus]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([roleId])
REFERENCES [dbo].[Role] ([roleID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD  CONSTRAINT [FK_Article_Account] FOREIGN KEY([authorEmail])
REFERENCES [dbo].[Account] ([email])
GO
ALTER TABLE [dbo].[Article] CHECK CONSTRAINT [FK_Article_Account]
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD  CONSTRAINT [FK_Article_ArticleStatus] FOREIGN KEY([statusID])
REFERENCES [dbo].[ArticleStatus] ([statusId])
GO
ALTER TABLE [dbo].[Article] CHECK CONSTRAINT [FK_Article_ArticleStatus]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Account] FOREIGN KEY([userEmail])
REFERENCES [dbo].[Account] ([email])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Account]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Article] FOREIGN KEY([articleID])
REFERENCES [dbo].[Article] ([id])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Article]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_CommentStatus] FOREIGN KEY([statusID])
REFERENCES [dbo].[CommentStatus] ([statusId])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_CommentStatus]
GO
USE [master]
GO
ALTER DATABASE [Blog] SET  READ_WRITE 
GO
