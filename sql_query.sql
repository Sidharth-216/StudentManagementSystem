/*CREATE DATABASE college;
USE college;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    course VARCHAR(50)
);
desc students;*/

delimiter $$ 
create procedure getstudent()
begin 
select * from students;
end $$

delimiter $$
create procedure putstudent(IN sid int,IN sname varchar(100),IN semail varchar(100),IN scourse varchar(50))
begin
    INSERT into students values(sid,sname,semail,scourse);
end $$

delimiter $$
create procedure updatestudent(IN sid int,IN sname varchar(100),IN semail varchar(100),IN scourse varchar(50))
begin
    update students set name=sname,email=semail,course=scourse where id=sid;
end $$

delimiter $$
create procedure deletestudent(IN sid int)
begin
    delete from students where id=sid;
end $$
