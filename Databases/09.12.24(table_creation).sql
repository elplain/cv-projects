create database cw2;

create table customer
(custid  varchar (20), 
custname char (100),
custemail char (100),
custphone varchar (20),
constraint PK_customer primary key (custid));




create table car
(regnum char (20),
make char (20),
model varchar (100),
dateofman  date,
mileage char (20),
nextsdate date,
custid varchar (20),
constraint PK_car primary key (regnum));



create table service
(sid char (20),
dropoff_date date,
dropoff_time time,
worktext varchar (255),
empid varchar(20),
regnum char (20),
constraint PK_proj primary key (sid));


create table emp
(empid  varchar (20),
empphone varchar (20),
empname varchar (40),
grade char(20)
constraint PK_emp primary key (empid));

create table work
(sid char (20),
timespent time,
empid varchar(20),
constraint PK_work primary key (sid));


ALTER table car
add constraint FK_car_customer
foreign key (custid) references customer(custid);

alter table service
add constraint FK_service_emp
foreign key (empid) references emp(empid);

alter table service
add constraint FK_service_car
foreign key (regnum) references car(regnum);


alter table work
add constraint FK_service_work
foreign key (sid) references service(sid);

alter table work
add constraint FK_emp_work
foreign key (empid) references emp(empid);


