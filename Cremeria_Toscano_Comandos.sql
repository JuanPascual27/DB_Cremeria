/**/
/*--1.crear la base de datos Cremeria_Toscano
create database Cremeria_Toscano
--2. Poner en uso la base de datos
use Cremeria_Toscano
--3.Borrar la base de datos
drop database Cremeria_Toscano

--4.Crear la tabla Proveedores con Nom_Proveedor varchar(30), IdProveedor char(8).
create table Proveedores(
	IdProveedor char(8) NOT NULL,
	NomProveedor varchar(40) NOT NULL,
	Primary Key (IdProveedor)
)

--5. Crear la tabla Compras con: IdCompra char(8), IdProveedor char(8), FechaC date, Total float
create table Compras(
	IdCompra char(8) NOT NULL primary key,
	IdProveedor char(8) NOT NULL foreign key references Proveedores(IdProveedor),
	FechaC date NOT NULL,
	Total float NOT NULL
)
insert into Productos values ('0001','Crema entera',12.5,25.5,'Crema entera vendida por gramos',39,'05/05/2022')
--6. Crear la tabla Productos con: IdProducto char(8), Stock float, NombreProducto varchar(30), Precio float, CantidadP float, FechaCaducidad date, Detalles varchar(50)
create table Productos(
	IdProducto char(8) NOT NULL,
	NombreProducto varchar(40) NOT NULL,
	CantidadP float NOT NULL,
	Precio float NOT NULL,
	Detalles varchar(60),
	Stock float NOT NULL,
	FechaCaducidad date NOT NULL,
	primary key(IdProducto)
)

--7. Crear la tabla DetallesCompras con: IdProducto char(8), IdCompra char(8), SubTotalC float, CostoP float, CantidadPC float
create table DetallesCompras(
	IdCompra char(8) NOT NULL foreign key references Compras(IdCompra),
	IdProducto char(8) NOT NULL foreign key references Productos(IdProducto),
	CantidadPC float NOT NULL,
	CostoP float NOT NULL,
	SubTotalC float NOT NULL,
	primary key(IdCompra, IdProducto)
)

--8. Crear la tabla Vendedores con: IdVendedor char(8), NombreV varchar(45)
create table Vendedores(
	IdVendedor char(8) NOT NULL primary key,
	NombreV varchar(45) NOT NULL
)

--9. Crear la tabla Rutas con: IdRuta char(8), IdVendedor char(8), DetalleRuta varchar(100)
create table Rutas(
	IdRuta char(8) NOT NULL primary key,
	IdVendedor char(8) NOT NULL,
	DetalleRuta varchar(100)
	foreign key(IdVendedor) references Vendedores(IdVendedor)
)

--10. Crear la tabla Clientes con: IdCliente char(8), IdRuta char(8), NombreCliente varchar(40), DireccionCliente varchar(50), TelefonoCliente varchar(10), Adeudos float
create table Clientes(
	IdCliente char(8) NOT NULL primary key,
	IdRuta char(8) NOT NULL foreign key references Rutas(IdRuta),
	NombreCliente varchar(40) NOT NULL,
	DireccionCliente varchar(50),
	TelefonoCliente varchar(10),
	Adeudos float
)

--11. Crear la tabla Ventas con: IdVenta char(8), IdCliente char(8), FechaV date, Total float
create table Ventas(
	IdVenta char(8) NOT NULL primary key,
	IdCliente char(8) NOT NULL,
	FechaV date NOT NULL,
	Total float NOT NULL,
	foreign key(IdCliente) references Clientes(IdCliente)
)

--12. Crear la tabla DetallesVentas con: IdVenta char(8), IdProducto char(8), CantidadPV int, SubTotalV float, CostoV float
create table DetallesVentas(
	IdVenta char(8) NOT NULL foreign key references Ventas(IdVenta),
	IdProducto char(8) NOT NULL foreign key references Productos(IdProducto),
	CantidadPV float NOT NULL,
	CostoV float NOT NULL,
	SubTotalV float NOT NULL
	primary key(IdVenta, IdProducto)
)*/
/*use Cremeria_Toscano
select * from Clientes
select * from Ventas
select * from DetallesVentas
select * from Productos
select * from Proveedores*/