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


/*
CREATE PROCEDURE add_bill (
    IN p_bill_id INT,
    IN p_customer_name VARCHAR(100),
    IN p_address VARCHAR(255),
    IN p_meter_number VARCHAR(30),
    IN p_billing_month VARCHAR(15),
    IN p_billing_year INT,
    IN p_previous_reading INT,
    IN p_current_reading INT,
    IN p_rate_per_unit DECIMAL(6,2),
    IN p_fixed_charge DECIMAL(8,2),
    IN p_tax_amount DECIMAL(8,2),
    IN p_due_date DATE
)
BEGIN
    DECLARE v_units INT;
    DECLARE v_total DECIMAL(10,2);

    SET v_units = p_current_reading - p_previous_reading;
    SET v_total = (v_units * p_rate_per_unit) + p_fixed_charge + p_tax_amount;

    INSERT INTO electricity_bill
    VALUES (
        p_bill_id,
        p_customer_name,
        p_address,
        p_meter_number,
        p_billing_month,
        p_billing_year,
        p_previous_reading,
        p_current_reading,
        v_units,
        p_rate_per_unit,
        p_fixed_charge,
        p_tax_amount,
        v_total,
        p_due_date,
        'Unpaid'
    );
END;


CREATE PROCEDURE get_bill (
    IN p_bill_id INT
)
BEGIN
    SELECT * 
    FROM electricity_bill
    WHERE bill_id = p_bill_id;
END;


CREATE PROCEDURE update_payment_status (
    IN p_bill_id INT,
    IN p_status VARCHAR(10)
)
BEGIN
    UPDATE electricity_bill
    SET payment_status = p_status
    WHERE bill_id = p_bill_id;
END;


CREATE PROCEDURE update_readings (
    IN p_bill_id INT,
    IN p_previous_reading INT,
    IN p_current_reading INT
)
BEGIN
    UPDATE electricity_bill
    SET 
        previous_reading = p_previous_reading,
        current_reading = p_current_reading,
        units_consumed = p_current_reading - p_previous_reading,
        total_amount = ((p_current_reading - p_previous_reading) * rate_per_unit)
                       + fixed_charge + tax_amount
    WHERE bill_id = p_bill_id;
END;


CREATE PROCEDURE delete_bill (
    IN p_bill_id INT
)
BEGIN
    DELETE FROM electricity_bill
    WHERE bill_id = p_bill_id;
END;

*/
