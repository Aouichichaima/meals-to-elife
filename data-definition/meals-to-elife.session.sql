select * from roles;

--@Block
select * from users order by id desc;

--@Block
select * from restaurants;

--@Block
select * from customerorders where restaurantid = 11;

--@Block
select * from stocks where RestaurantId = 5 ORDER BY id DESC;
--@Block
select * from products where stockid = 40 ORDER BY id DESC;

--@Block
select * from deliverystaffs;

--@Block
select * from feedback_delivery_staffs;


<<<<<<< main
--@Block
show tables;



=======
>>>>>>> main
--@Block
delete from roles where true;
--@Block
ALTER TABLE feedback_delivery_staffs AUTO_INCREMENT = 1;
