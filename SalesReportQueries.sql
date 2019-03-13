#### Bao Nguyen ####

##### Query for final project
use bicycleInventory;
select * from Customer;

select * from Order_status;

# Select top 10 customers with total order purchased and number of their orders which order already completed
select c.cid, c.name, c.email, c.phone, count(*) orderNum, sum(o.price) as totalAmount
from Order_info o, Customer c
where c.cid = o.customer_id and o.status_id = 2
group by c.cid, c.name, c.email, c.phone
order by totalAmount desc
limit 10;

#################################

# Select top 10 customers who bought much bike and order already completed
select c.cid, c.name, c.email, sum(i.quantity) as totalBikes
from Order_info o, Customer c, Item_order i
where c.cid = o.customer_id and o.id = i.order_id and o.status_id = 2
group by c.cid, c.name, c.email
order by totalBikes desc
limit 10;


#################################


#Customers who bought bikes in all category

select c.cid, c.name, c.email, c.phone
from Customer c
where not exists (select * from Category cc 
							where not exists (select * from Order_info o, item_order i, Bicycle b 
														 where o.status_id = 2 and c.cid = o.customer_id and o.id = i.order_id and i.bicycle_id = b.bid and b.category_id = cc.cid));

                                                         
#Customers who bought all category and all manufacturer
select *
from ( select c.cid, c.name, c.email
			from Customer c
			where not exists (select * from Category cc 
							where not exists (select * from Order_info o, item_order i, Bicycle b 
														 where o.status_id = 2 and c.cid = o.customer_id and o.id = i.order_id and i.bicycle_id = b.bid and b.category_id = cc.cid))) as T1,
         (select c.cid, c.name, c.email
			from Customer c
			where not exists (select * from Manufacturer m 
							where not exists (select * from Order_info o, item_order i, Bicycle b 
														 where o.status_id = 2 and c.cid = o.customer_id and o.id = i.order_id and i.bicycle_id = b.bid and b.manufacturer_id = m.mid))) as T2
where T1.cid = T2.cid and T1.email = T2.email and T1.name = T2.name;

## Update select * from customer where c.id in T1.id and c.id in T2.id


### Austin Woo ###

### Moldify O.customer_id to O.id and 
##Top 10 most popular bikes sold
drop view if exists BikesGroupedByBID;
CREATE VIEW BikesGroupedByBID(bikeID, num)
AS SELECT I.bicycle_id,sum(I.quantity) as num
FROM Item_order as I,Order_info as O
WHERE O.id = I.order_id and O.status_id = 2
GROUP BY I.bicycle_id
ORDER BY num desc
LIMIT 10;

select * from BikesGroupedByBID;

SELECT B.model
FROM BikesGroupedByBID G, Bicycle B
WHERE G.bikeID = B.bid;


## Top 10 bike models that made the most money
## Moldify this query since we need to include the quantity* price for bikes in item_order
drop view if exists bikeByPrice;
CREATE VIEW bikeByPrice(bikeID,price) AS
SELECT bicycle_id, sum(price) as price
FROM (select bicycle_id, (price * quantity) as price from Item_order) as T1
GROUP BY bicycle_id
ORDER BY price desc
LIMIT 10;


SELECT B.bid, B.model, P.price as amount, 
FROM bikeByPrice P, Bicycle B
WHERE P.bikeID = B.bid;

##Manufacturer that has the most bikes in stock
drop view if exists Popularity;
CREATE VIEW Popularity(manufacturer, stock)
AS SELECT manufacturer_id, sum(stock) as stock
FROM Bicycle 
GROUP BY manufacturer_id
ORDER BY stock desc
Limit 1;


SELECT M.name
FROM Manufacturer M, Popularity P
WHERE M.mid = P.manufacturer;


# Orders over 6 months old that are still processing
SELECT O.id, C.name, C.email, O.price, O.date_order
FROM Order_info O JOIN Customer C ON O.customer_id = C.cid 
JOIN Order_status S ON S.sid = O.status_id
WHERE O.date_order <= NOW() - INTERVAL 6 MONTH
AND S.description = "Processing"
ORDER BY O.date_order;

 #Customers with more than 2 open orders
 DROP VIEW IF EXISTS NumberOfProcOrdersByCust;
 
CREATE VIEW NumberOfProcOrdersByCust AS
SELECT C.cid, COUNT(*) AS open_orders
FROM Customer C JOIN Order_info O ON C.cid = O.customer_id 
JOIN Order_status S ON O.status_id = S.sid
WHERE S.description = "Processing"
GROUP BY C.cid
ORDER BY open_orders DESC;

SELECT C.cid, C.name, C.email, N.open_orders
FROM NumberOfProcOrdersByCust N NATURAL JOIN Customer C
WHERE N.open_orders > 2;


