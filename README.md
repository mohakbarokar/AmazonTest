# AmazonTest
Amazon Test  Assignment
Amazon Test
-----------------------------------------
About:
Amazon Test is a Maven project and dependencies are added in pom file.
This Project contains 6 Packages as below: 
common - Constants and reusable methods
Setupconfig- Base test script
suites - Suites with different combinations can be added here
testNGExecutor -Suite Executor (It runs test suite as a JAVA application)
testScripts - Actual test scripts where test scenario lies
uicomponentscreens - Locator elements are placed in this package According to the Page

This project contains TestData.Properties file from where screenshot manipulation, driver paths and URL's can be handled.

Support of browsers : Chrome, firefox, edge and ie(due to legacy issues may hamper performance on some machines)

There are 4 Test cases added in scripts out of which 2 testScripts, "Verify Sorted Results" and "Check Only Prime content" are prone to give failures as observed in website Sometimes result list is not sorted according to price low to high when filtered and same applies for prime content, Sometimes item which is not prime is listed in search results even after applying only prime filter.

--------------------------------------------

Steps to Execute:
1. Download project from git/google drive
2. Extract project
3. Open in any IDE (Eclipse/IntelliJ)
4. Navigate to testNGExecutor package in src folder and Run it as Java project.
5. to change browser preferences Go to suites/AmzonSuite and uncomment the Test part of desired browser
6. Emailable report can be found under test-output folder. Full report is names as index.html
7. Failed Screenshots can be found under screenshots/Failed folder
8. if the test is failed screenshot path is generated in logs.

--------------------------------------------
Issues Faced:
1. Search result elements for only prime content were tricky to get as only prime icon was reference to verify and not Text for being prime item. As the views of the results columns were dynamic I filtered out 2 Lists in which one returns rows of all the results found in page and another returns number of rows having prime icon present and verified if the count matches.
