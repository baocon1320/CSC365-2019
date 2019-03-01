
Team Name

Team Member:
   Bao Nguyen
   Austin Woo
   Wesley Benica
   ...

Bicycle Inventory Project
Spring 2019
Cal Poly University


I. Project guide

   Moldify the DB_URL, username and password of your DB in src/Connect DBCreating and DBConnection file before run project.
   Program will auto create a new DB (on DBCreate.java file) if your Machine doesn't have one.

II. Database Design
   Detail on file Database Design.pdf
   On DBCreate.java file has all the create table queries, check it out to more constraint, specially on delete and update    on foreign key

III. Project Overview:

   Manufacturer JPanel: This Panel work well, we can add new, update or delete a category. (will set manufacturer_id to       null in table Bicycle on delete)
   
   Category JPanel: This Panel also work well, we can add new, update or detete a category. (will set category_id to         null in table Bicycle on delete)
   
   Bicycle JPanel: This Panel also work, can add new, update or delete a category.
   
   Customer Jpanel: Already finished design the front-end, need to load the data to table, and perform delete, add new and   update a customer. Make sure when add a new customer, we need also add a new row on table address.
   
   Order JPanel: Already finished design the front-end, need to load data to table, perform update an order (Maybe just       only allow update the status) and perform delete. 
   
   Add New Order Jpanel: haven't done any thing, need to design the front-end, and perform add new order ( make sure update   table order_item table and add new order)
      
   *** Importan: Sale Report JPanel: 
   I think we should create some difficult queries here to get a good credit for this project. Everyone should come up with   3 different querries. Some of the querries that I think should be
      - Find all customer that bought bikes in all the categories
      - Find all customer that bought bikes in all the category and manufactureres
      - Find all sales for each category by month
      - Find all sales for a zipcode by month. 
      
  




