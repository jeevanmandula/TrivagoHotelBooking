# Instructions to Run the Script or Test cases

Clone the git repository to local eclipse IDE.

Import the code from local git to eclipse project. 

Make sure the local machine have Java and Maven. 

Setup env. variables for Java and Maven. 

Once everything is done

Run the TestNg file from project explorer

Or execute Cartexe.java as TestNG 






# assignment2
Automate following scenarios in this website http://automationpractice.com/index.php 
Scenario 1: Search a product and validate that the search result is the correct product being displayed 
Scenario 2: Scroll down from the homepage and add a product to cart and validate the addition of the product is successful to the cart. 
Scenario 3 : Scroll down to a product to a mouse over and click on More, validate the resulting page has the correct information and also the functionality in that page is working by validating increment and decrement of quantity, size and then Add to Cart 
Scenario 4: Create a login page failure validation 
The candidate is free to choose between JAVA or C#. Using BDD will be a bonus for their framework. Also please try to use tags like smoke, regression wherever appropriate.

# I have cteated this projcet using Selenium - Hybrid Framework with folloiwng guidlines. 
1. The values such as login id and Password pulled from Excel files. 
2. The xpath values are pulled from properties file. 
Created a base class for all the keyword related methods. 
Create other classes such as extent reports, read, write excel under utils. 
Created test scripts under test classes
Created executable classes under exe classes. 
Used Maven + testNG methods. 

# Pull the code from git using clone option. 
run the testng file or you can run them as groups thriugh exe classes. 
For test reorts go to test output and extend reports 
