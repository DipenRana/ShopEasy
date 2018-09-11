# ShopEasy
Android shopping application with barcode scanner and java server at backend.

Shop Easy is an Android Application built to work on Smartphone which assist the user to conveniently shop when they visit 
any mall or super market. The users can save much amount of time and avoid complications faced during the traditional shopping 
by using our application.
While shopping sometime customers have inconvenience in carrying the shopping trolley from place to place and have to wait for 
some time at the billing counter for payment. The shopping Malls also have to struggle for handling large number of people during 
weekends or holidays. Our software provides solution to these problems and enhances customer convenience.

The working principle for the project is that the user can scan the barcode provided on the items by using their Smartphone. 
The Smartphone uses the camera to scan the barcode by using our application.
The idea is to use the mobile device of the customer to scan the barcode of the product and to maintain the shopping list over the
Smartphone device. The application source (apk) will be provided by the server at mall.
A server has to be set up at the location of shopping mall which has the access to all the items available there. As the user enters 
the mall, he/she has to launch our application on their mobile; the application connects the device to the server through Wi-Fi. The 
customer starts building shopping list on their phone by scanning items which they want to purchase. At last the payment is done through
the online payment portal provided by our application.
After 5 or 10 minutes after the successful payment by the customer, he/she can get there shopping bag at some outlet at the shopping mall.
Or the customer can select the option for home delivery, and then the bag will be delivered to their homes.

Story Board:
1) A customer walks into a Retail shopping store.
2) He /She open the Shop Easy mobile application on his/her smart phone.
3) The customer starts building shopping cart on their phone by scanning items of interest (Product scanner is provided by the 
pplication which uses inbuilt camera for scanning). The cart details are persisted onto a store server.
4) On background the handlers were collecting selected items from number of customers over a place.
5) Once customer wishes to checkout, he has to make the payment online through Net Banking or credit card which is provided by our 
application.
6) On successful payment the generated bill is downloaded on the user’s device and also stored on the database.
7) After 5 min, customer gets the sealed bag containing purchased items from an outlet at the mall. Or customer can choose the home 
delivery option and the shopping bags will be delivered to their home.


Get the aplication Apk form the folder.
ShopEasy > bin> shopeasy.apk
Install it in your smartpone. Run the server applicaiton given in the shopeasy-server project.
