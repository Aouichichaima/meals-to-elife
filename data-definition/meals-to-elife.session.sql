select * from roles;

--@Block
select * from users order by id desc;

--@Block
select * from restaurants;

--@Block
select * from customer_orders where UserId < 100 ORDER BY UserId;

--@Block
select * from stocks;
--@Block
select * from products;

--@Block
select * from deliverystaffs;

--@Block
select * from feedback_delivery_staffs;




--@Block
delete from roles where true;
--@Block
ALTER TABLE feedback_delivery_staffs AUTO_INCREMENT = 1;

--@Block
show tables;