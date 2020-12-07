# Exercise 4 - (Bonus) Setup SAP Cloud Platform Continuous Integration and Delivery

In this exercise, you will setup the SAP Cloud Platform Continuous Integration and Delivery service which helps you to automatically build, test and deploy each change with a preconfigured pipeline. 

## Subscribe to SAP Cloud Platform Continuous Integration and Delivery and configure roles

1. Open the subscriptions overview in your trial subaccount in the SAP Cloud Platform Cockpit and select "Continuous Integration & Delivery".
<br>![](/exercises/ex4/images/subscriptions.png)

2. Subscribe to the service.
<br>![](/exercises/ex4/images/subscribe.png)

3. To be able to access the service, you have to assign the corresponding roles to your user. Open the "Trust Configuration" of your subaccount and click on "sap.default".
<br>![](/exercises/ex4/images/trust_configuration.png)

4. Enter your e-mail address you used to login to the SAP Cloud Platform Cockpit, click on "Show Assignments" and afterwards on "Assign Role Collection". Select the collection "CICD Service Administrator" and confirm your selection by clicking the "Assign Role Collection" button.
<br>![](/exercises/ex4/images/assign_role.png)

5. Go back to the overview of the subscriptions from step 1 and click on "Go to Application" on the "Continuous Integration & Delivery" tile.
<br>![](/exercises/ex4/images/open_service.png)

## Create a new Build Job
1. Click on "+" to create and configure a new build job.
<br>![](/exercises/ex4/images/createjob.png)

2. Fill out the form with the following values and click "Create":
  - Job Name: webshop
  - Repository Url: https://github.com/SAP-samples/teched2020-DT261.git
  - Branch: main
<br>![](/exercises/ex4/images/createjob_form.png)

3. A "Webhook Creation" windows will open. You can ignore this window for now. It will become relevant later when you want to start new build jobs automatically after each commit. To start a new build manually, press the arrow button in the "Builds" tab. 
<br>![](/exercises/ex4/images/triggerjob.png)

4. By clicking on the build you can see in which stage of the pipeline the build currently is. After a few minutes the build should be marked as green to indicate the build was successful.

5. By default the pipeline will build the project and run tests, linting as well as static code checks. You can enable additional features, such as the deployment at the end of the pipeline, by configuring them in the pipeline configuration file `.pipeline\config.yml`. You can find the corresponding information about how to enable certain features and how to configure the corresponding credentials in the [Continuous Integration & Delivery documentation](https://help.sap.com/viewer/product/CONTINUOUS_DELIVERY/Cloud/en-US). 

Please note that the application built in the pipeline will not contain all the changes you have done during the exercise.
That is expected, since the URL you have provided points to the very same GitHub repository that you cloned initially.
If you want to explore the topic further and setup a pipeline that is triggered on every change to your project, you can push the application to your own repository on GitHub and setup webhooks.
You can get the webhook data by going back to the CICD service's dashboard, selecting the "webshop" build and clicking on "Webhook Data" on the right.

## Summary
You have successfully enabled and configured the Continuous Integration & Delivery service on SAP Cloud Platform. To achieve that, you subscribed to the service, assigned the corresponding roles to your account and connected the service to an existing git repository.
