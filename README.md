
 
# The Hamburger Company

The chianed restaurants management system

![alt tag](https://github.com/shubhamsasane/Restaurant-service/blob/main/Architecture.png)
  
## Functionalities

- Set up new restaurant
- Manage reservations at restaurants
- Manage orders by the customers
- Manage food items at each branch separately
- Manage menus at each branch separately
- Manage payments
- User management - authorization at Admin, Branch and basic level

  ## Technology

- Spring boot
- Apache Kafka
- Jenkins
- Docker
- MySQL
- RESTful APIs
- Swagger
- Authentication and Autherization using Spring security




  
## API Reference

#### Restaurant Specific Endpoints

```http
AUTHORIZATION LEVELS: 
LEVEL 3: admin        -  all operations
LEVEL 2: branch amid  -  all operations of a branch
LEVEL 1: end user     -  limited operations    
```


```http
    Functions                      Authorization LEVEL
  1. Restaurant : 
	a. add restaurant                LEVEL 3
	b. delete restaurant             LEVEL 3               
	c. update restaurant             LEVEL 2 + 3
	d. Get all Restaurant            LEVEL 1
	e. get restaurant by Id          LEVEL 1
	f. get restaurant by name        LEVEL 1 

2. Menu:
	a. Add menu from restaurant       LEVEL 2
	b. update menu                    LEVEL 2
		1. add item to menu    
		2. delete item from menu      
	c. delete menu                    LEVEL 2
	d. get menu of restaurant         LEVEL 2
	e. get menu of all restaurant     LEVEL 3
	d. get menu by Id 		  LEVEL 1	

3. Food Items:
	a. add item                       LEVEL 2
	b. delete item                    LEVEL 2
	c. update item                    LEVEL 2
		1. update into menu also 
	d. get item by id                 LEVEL 1
	e. get all items 		  LEVEL 2
```



#### Reservation Specific Endpoints
```http
AUTHORIZATION LEVELS: 
LEVEL 3: admin        -  all operations
LEVEL 2: branch amid  -  all operations of a branch
LEVEL 1: end user     -  limited operations    
```
```http
    Functions                              Authorization LEVEL
1. Booking
	a. Create Booking                       LEVEL 1
	*b. Delete Booking 			LEVEL 1
	c. Update Booking 			LEVEL 1
	d. Get Booking by id			LEVEL 1
	e. Get all bookings			LEVEL 3
	f. Get all booking of a resto 		LEVEL 2
	g. Get all scheduled booking of resto 	LEVEL 2
	*h. CRUD on open hours
	i. Get all scheduled bookings		LEVEL 3


2. Restaurant Tables
	a. Add table      		        LEVEL 2
	b. Batch add table                      LEVEL 2
	e. get all resto tables                 LEVEL 1
```


#### Order Specific Endpoints
```http
AUTHORIZATION LEVELS: 
LEVEL 3: admin        -  all operations
LEVEL 2: branch amid  -  all operations of a branch
LEVEL 1: end user     -  limited operations    
```
```http
    Functions                           		Authorization LEVEL

1. Order
	a. Place order by adding food items		LEVEL 1
	b. update order by removing items		LEVEL 1
	b. get order by id    		   		LEVEL 1
	c. get all orders of resto			LEVEL 2
	d. get all orders all resto			LEVEL 3
	e. get today's all orders of resto		LEVEL 2
	*f. get today's all orders all resto		LEVEL 3
	h. Delete orders if status = placed		LEVEL 1
	i. Get total bill				LEVEL 1
	j. calculate collection of the day.		LEVEL 2
	k. calculate collection all resto

```


  
