//BASE DE DATOS ACTUALIZADA 27-05-2026


[AMPADMIN.sql](https://github.com/user-attachments/files/28311436/AMPADMIN.sql)
USE [master]
GO
/****** Object:  Database [AMPADMIN]    Script Date: 27/5/2026 11:10:45 ******/
CREATE DATABASE [AMPADMIN]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'AMPADMIN', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\AMPADMIN.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'AMPADMIN_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\AMPADMIN_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [AMPADMIN] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AMPADMIN].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AMPADMIN] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [AMPADMIN] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [AMPADMIN] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [AMPADMIN] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [AMPADMIN] SET ARITHABORT OFF 
GO
ALTER DATABASE [AMPADMIN] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [AMPADMIN] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [AMPADMIN] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [AMPADMIN] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [AMPADMIN] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [AMPADMIN] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [AMPADMIN] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [AMPADMIN] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [AMPADMIN] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [AMPADMIN] SET  DISABLE_BROKER 
GO
ALTER DATABASE [AMPADMIN] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [AMPADMIN] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [AMPADMIN] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [AMPADMIN] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [AMPADMIN] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [AMPADMIN] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [AMPADMIN] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [AMPADMIN] SET RECOVERY FULL 
GO
ALTER DATABASE [AMPADMIN] SET  MULTI_USER 
GO
ALTER DATABASE [AMPADMIN] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [AMPADMIN] SET DB_CHAINING OFF 
GO
ALTER DATABASE [AMPADMIN] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [AMPADMIN] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [AMPADMIN] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [AMPADMIN] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'AMPADMIN', N'ON'
GO
ALTER DATABASE [AMPADMIN] SET QUERY_STORE = ON
GO
ALTER DATABASE [AMPADMIN] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [AMPADMIN]
GO
/****** Object:  Table [dbo].[Autorizado]    Script Date: 27/5/2026 11:10:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Autorizado](
	[idAutorizado] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](100) NOT NULL,
	[dni] [varchar](20) NOT NULL,
	[tipoAutorizacion] [varchar](50) NOT NULL,
	[idCliente] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idAutorizado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 27/5/2026 11:10:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cliente](
	[idCliente] [int] IDENTITY(1,1) NOT NULL,
	[razonSocial] [varchar](150) NOT NULL,
	[telefono] [varchar](20) NULL,
	[cuit] [varchar](11) NOT NULL,
	[fchaIngreso] [date] NOT NULL,
	[domicilio] [varchar](200) NOT NULL,
	[mail] [varchar](100) NOT NULL,
	[tipoCliente] [varchar](20) NOT NULL,
	[estado] [varchar](20) NULL,
	[tipoPersona] [varchar](50) NULL,
	[idCondIva] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idCliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CondIva]    Script Date: 27/5/2026 11:10:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CondIva](
	[idCondIva] [int] IDENTITY(1,1) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[ivaDiscriminado] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idCondIva] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 27/5/2026 11:10:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuario](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[usuario] [varchar](50) NOT NULL,
	[contrasenia] [varchar](255) NOT NULL,
	[rol] [varchar](50) NOT NULL,
	[estado] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Cliente] ON 
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (24, N'Juan Perez', N'3514001001', N'20111222331', CAST(N'2026-01-10' AS Date), N'San Martin 123', N'juan.perez@gmail.com', N'Cliente', N'Activo', N'Persona', 3)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (25, N'Maria Gomez', N'3514001002', N'27222333442', CAST(N'2026-01-11' AS Date), N'Belgrano 456', N'maria.gomez@gmail.com', N'Cuenta Corriente', N'Activo', N'Persona', 2)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (26, N'Distribuidora Centro SRL', N'3514001003', N'30733444553', CAST(N'2026-01-12' AS Date), N'Ruta 9 Km 15', N'ventas@distribuidoracentro.com', N'Cuenta Corriente', N'Activo', N'Empresa', 1)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (27, N'Carlos Fernandez', N'3514001004', N'20344555664', CAST(N'2026-01-13' AS Date), N'Colon 789', N'cfernandez@gmail.com', N'Cliente', N'Activo', N'Persona', 3)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (28, N'Ferreteria Industrial SA', N'3514001005', N'30755666775', CAST(N'2026-01-14' AS Date), N'Av. Japon 456', N'info@ferreteriaindustrial.com', N'Cuenta Corriente', N'Activo', N'Empresa', 1)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (29, N'Lucia Martinez', N'3514001006', N'27455677886', CAST(N'2026-01-15' AS Date), N'9 de Julio 852', N'luciam@gmail.com', N'Cliente', N'Activo', N'Persona', 2)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (30, N'Comercial Norte SRL', N'3514001007', N'30766788997', CAST(N'2026-01-16' AS Date), N'Bv. Los Andes 741', N'admin@comercialnorte.com', N'Cuenta Corriente', N'Activo', N'Empresa', 1)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (31, N'Diego Ramirez', N'3514001008', N'20577899008', CAST(N'2026-01-17' AS Date), N'Italia 951', N'diego.ramirez@gmail.com', N'Cliente', N'Inactivo', N'Persona', 3)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (32, N'Electro Hogar SA', N'3514001009', N'30788990019', CAST(N'2026-01-18' AS Date), N'Av. Sabattini 1234', N'ventas@electrohogar.com', N'Cuenta Corriente', N'Activo', N'Empresa', 1)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (33, N'Sofia Lopez', N'3514001010', N'27699001120', CAST(N'2026-01-19' AS Date), N'Parana 654', N'sofia.lopez@gmail.com', N'Cliente', N'Activo', N'Persona', 2)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (34, N'Mercado Central SRL', N'3514001011', N'30710111231', CAST(N'2026-01-20' AS Date), N'Av. Fuerza Aerea 852', N'contacto@mercadocentral.com', N'Cuenta Corriente', N'Activo', N'Empresa', 1)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (35, N'Martin Suarez', N'3514001012', N'20121222342', CAST(N'2026-01-21' AS Date), N'Dean Funes 357', N'martins@gmail.com', N'Cliente', N'Activo', N'Persona', 3)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (36, N'Servicios Integrales SA', N'3514001013', N'30732333453', CAST(N'2026-01-22' AS Date), N'Hipolito Yrigoyen 951', N'info@serviciosintegrales.com', N'Cuenta Corriente', N'Activo', N'Empresa', 1)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (37, N'Valentina Torres', N'3514001014', N'27443444564', CAST(N'2026-01-23' AS Date), N'Santa Rosa 159', N'valentina@gmail.com', N'Cliente', N'Activo', N'Persona', 2)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (38, N'Corralon del Sur', N'3514001015', N'30754555675', CAST(N'2026-01-24' AS Date), N'Ruta 20 Km 5', N'ventas@corralondelsur.com', N'Cuenta Corriente', N'Activo', N'Empresa', 1)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (39, N'Federico Castro', N'3514001016', N'20565666786', CAST(N'2026-01-25' AS Date), N'Laprida 753', N'fcastro@gmail.com', N'Cliente', N'Activo', N'Persona', 3)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (40, N'Tecnologia Global SA', N'3514001017', N'30776777897', CAST(N'2026-01-26' AS Date), N'Av. Colon 2222', N'contacto@tecnologiaglobal.com', N'Cuenta Corriente', N'Activo', N'Empresa', 1)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (41, N'Camila Diaz', N'3514001018', N'27687888908', CAST(N'2026-01-27' AS Date), N'General Paz 741', N'camidiaz@gmail.com', N'Cliente', N'Activo', N'Persona', 2)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (42, N'Logistica Express SRL', N'3514001019', N'30798999019', CAST(N'2026-01-28' AS Date), N'Circunvalacion 1450', N'info@logisticaexpress.com', N'Cuenta Corriente', N'Activo', N'Empresa', 1)
GO
INSERT [dbo].[Cliente] ([idCliente], [razonSocial], [telefono], [cuit], [fchaIngreso], [domicilio], [mail], [tipoCliente], [estado], [tipoPersona], [idCondIva]) VALUES (43, N'Nicolas Herrera', N'3514001020', N'20109000120', CAST(N'2026-01-29' AS Date), N'Maipu 852', N'nherrera@gmail.com', N'Cliente', N'Activo', N'Persona', 3)
GO
SET IDENTITY_INSERT [dbo].[Cliente] OFF
GO
SET IDENTITY_INSERT [dbo].[CondIva] ON 
GO
INSERT [dbo].[CondIva] ([idCondIva], [descripcion], [ivaDiscriminado]) VALUES (1, N'Responsable Inscripto', 1)
GO
INSERT [dbo].[CondIva] ([idCondIva], [descripcion], [ivaDiscriminado]) VALUES (2, N'Monotributista', 0)
GO
INSERT [dbo].[CondIva] ([idCondIva], [descripcion], [ivaDiscriminado]) VALUES (3, N'Consumidor Final', 0)
GO
INSERT [dbo].[CondIva] ([idCondIva], [descripcion], [ivaDiscriminado]) VALUES (4, N'Exento', 1)
GO
SET IDENTITY_INSERT [dbo].[CondIva] OFF
GO
SET IDENTITY_INSERT [dbo].[Usuario] ON 
GO
INSERT [dbo].[Usuario] ([id], [usuario], [contrasenia], [rol], [estado]) VALUES (1, N'Lucas Bondaruk', N'lucas123', N'Administrador', N'Activo')
GO
SET IDENTITY_INSERT [dbo].[Usuario] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__CuentaCo__2CDD9897E6683CFB]    Script Date: 27/5/2026 11:10:46 ******/
ALTER TABLE [dbo].[Cliente] ADD UNIQUE NONCLUSTERED 
(
	[cuit] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Autorizado]  WITH CHECK ADD  CONSTRAINT [FK_Autorizado_Cliente] FOREIGN KEY([idCliente])
REFERENCES [dbo].[Cliente] ([idCliente])
GO
ALTER TABLE [dbo].[Autorizado] CHECK CONSTRAINT [FK_Autorizado_Cliente]
GO
ALTER TABLE [dbo].[Cliente]  WITH CHECK ADD  CONSTRAINT [FK_CuentaCorriente_CondIva] FOREIGN KEY([idCondIva])
REFERENCES [dbo].[CondIva] ([idCondIva])
GO
ALTER TABLE [dbo].[Cliente] CHECK CONSTRAINT [FK_CuentaCorriente_CondIva]
GO
USE [master]
GO
ALTER DATABASE [AMPADMIN] SET  READ_WRITE 
GO
