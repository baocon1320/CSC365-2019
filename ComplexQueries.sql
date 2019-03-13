##Top 10 most popular bikes sold
CREATE VIEW BikesGroupedByBID(bikeID, num)
AS SELECT I.bicycle_id,sum(I.quantity) as num
FROM Item_order as I,Order_info as O
WHERE O.customer_id = I.order_id
GROUP BY I.bicycle_id
ORDER BY num desc
LIMIT 10;

SELECT B.model
FROM BikesGroupedByBID G, Bicycle B
WHERE G.bikeID = B.bid;

## Top 10 bike models that made the most money
CREATE VIEW bikeByPrice(bikeID,price) AS
SELECT bicycle_id, sum(price) as price
FROM Item_order
GROUP BY bicycle_id
ORDER BY price desc
LIMIT 10;

SELECT B.model
FROM bikeByPrice P, Bicycle B
WHERE P.bikeID = B.bid;

##Manufacturer that has the most bikes in stock
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

DROP VIEW NumberOfProcOrdersByCust;

# Customers with more than 2 open orders
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

# Customers who haven't ordered in last 24 months (2 years)
SELECT C.cid, C.name, C.email, C.phone, MAX(date_order) AS last_order
FROM Customer C JOIN Order_info O ON C.cid = O.customer_id
WHERE NOT EXISTS ( SELECT *
					FROM Customer C1 JOIN Order_info O1 ON C1.cid = O1.customer_id
                    WHERE C.cid = O1.customer_id AND
                    O1.date_order >= NOW() - INTERVAL 24 MONTH )
GROUP BY C.cid, C.name, C.email, C.phone
ORDER BY date_order DESC;
