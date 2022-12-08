select * from roles;

--@Block
select * from users order by id desc;

--@Block
select * from restaurants;

--@Block
select * from customerorders where restaurantid = 11;

--@Block
select * from stocks;
--@Block
select * from products;
--@Block
select * from delivery_staffs;

--@Block
select * from feedback_delivery_staffs;


--@Block
show tables;



--@Block
delete from roles where true;
--@Block
ALTER TABLE feedback_delivery_staffs AUTO_INCREMENT = 1;
