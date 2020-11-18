#Vedantu Basic UI Test Flow



### Install the required plugins
mvn clean install

### To run the Project-	
src/test/resources contains TestNg.xml file- Right click and Run with TestNg configuration

Reporting is also implemented in the project-
report/htmlReports -- Right click and open with web browser

##Project Structure Explaination-

src/main/resource- Contains The configuration file config.properties

src/test/java-
Contains the Test script- VedantuBasicTestFlow

src/main/java-
com.vendantu.base- This contains the base class for the test scripts.
com.vedantu.common.utils- ALl the common utils like Data Manager , helper Methods and ExtentManager for reporting are present.

Vedantu.common.utils-
All the common project utils are kept here


