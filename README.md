# Automation Task

1. Automated test cases available under src/test/java/
2. Report is available allure-results folder

#Test Execution
1. Test case can be executed right click on src/test/java folder and click Run 'All Tests'
2. Single test execution by clicking on @Test annotated method
3. Test case 2 - AutomationFormTest failed due to reCAPTCHA image while submitting the form.
4. Refer src/main/resources/TestCase2 findings file.

# Pre - requisites:
1. Download Java, Maven and Allure binaries and set path in your Environment Variables (if not available in local machine)
2. Use cmd allure serve project path + allure-results in allure-results folder
   example: allure serve {user.dir}/{project-folder}/allure-results