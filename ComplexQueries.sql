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
WHERE G.bikeID = B.bid

## Top 10 bike models that made the most money
CREATE VIEW bikeByPrice(bikeID,price) AS
SELECT bicycle_id, sum(price) as price
FROM Item_order
GROUP BY bicycle_id
ORDER BY price desc
LIMIT 10;

SELECT B.model
FROM bikeByPrice P, Bicycle B
WHERE P.bikeID = B.bid

##Manufacturer that has the most bikes in stock
CREATE VIEW Popularity(manufacturer, stock)
AS SELECT manufacturer_id, sum(stock) as stock
FROM Bicycle 
GROUP BY manufacturer_id
ORDER BY stock desc
Limit 1;

SELECT M.name
FROM Manufacturer M, Popularity P
WHERE M.mid = P.manufacturer










