create database registration;
use registration; 
create table reg(id int primary key auto_increment, uname varchar(50), uemail varchar(35), umobile varchar(15), upass varchar(35));
desc reg;
select * from reg;