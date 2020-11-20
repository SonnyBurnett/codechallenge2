<h1> Coding challenge assignement 3</h1>

Solution for assignment 3. It determines shipping information of a list of customers and the productes they ordered.

Assigment3 run method needs two paramaters
| Variable | Description |
| -------- |:-----------:|
| inputFile | Location of the csv file with informationabout the customers and their products |
| outputFile | Location and filename to which the output needs to be written | 

<h2>CustomerFileReader</h2>
The CustomerFileReader class reads the lines. It checks whether the header of the file is correct and per line whether 
the data is of the type that is expected. 

If this is correct it looks for the customer in the database. A new customer is created if it is not in the database yet.
Finally, the product is added to the list of products of the customer.

It is assumed that the country is the country the customer lives in.  

<h2>ShippingInfoDeterminer</h2>
ShippingInfoDeterminer determines per customer in the database which shipper they are using, how much de shippingcosts are 
and how long it will take before the package arrives. It uses the total weight of the package and the country of the customer. 

There are three shipper options, which is set in the Shipper enum:

- BelgioPosto for all customers living in Belgium.
- PostNL for customers living in the Netherlands of which the package weights less dan 10 kg.
- DHL for all other customers.

The delivery time (duration) depends on the shipper and the weight of the package: 

- 2 days for all packages of BelioPosto.*1 
- 1 day for all packages of PostNL. 
- 4 days for packages with a weight below 10 kg of DHL.
- 8 days for all other packages of DHL.

The shippingcosts also depend on the shipper and the weight of the package:

- 1.95 + weight for all BelgioPosto packages.
- 6.95 for all PostNL package.
- 12.95 + 1.5*weight for all DHL packages.

* In example on slide 16, the duration BelgioPosto was set to 3 and the shipping costs was calculated using 1.95 + 1.5*weight. 
I used the rules slide as basis. For this reason, when the input of that slide is used, the outcome of this solution is different.

<h2>ShippingInfoWriter</h2>
The ShippingInfoWriter class writes all information needed for shipping to an output file per customer in the database. 

It writes the following information:

- CustomerId
- Name
- Shipper
- Duration (time to delivery)
- Shipping costs