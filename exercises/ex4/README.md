# Exercise 4 - (Bonus) Setup SAP Cloud Platform Continuous Integration and Delivery

In this exercise, you will setup the SAP Cloud Platform Continuous Integration and Delivery service which helps you to automatically build, test and deploy each change we a preconfigured pipeline. 

## Subscribe to SAP Cloud Platform Continuous Integration and Delivery and configure roles

1. Open the subscription overview in your trial subaccount in the SAP Cloud Platform Cockpit and select "Continuous Integration & Delivery".
<br>![](/exercises/ex4/images/subscriptions.png)
  Subscribe to the service.
<br>![](/exercises/ex4/images/subscribe.png)

2. To be able to access the service, you have to assign the corresponding roles to your user. Open the "Trust Configuration" of your subaccount and click on "sap.default".
<br>![](/exercises/ex4/images/trust_configuration.png)

3. Enter you e-mail address you used to log in into the SAP Cloud Platform Cockpit, click on "Show Assignments" and afterwards on "Assign Role Collection". Select the collection "CICD Service Administrator" and confirm by clicking the "Assign Role Collection" button.
<br>![](/exercises/ex4/images/assign_role.png)

4. Go back to the overview of subscriptions from step 1 and click on "Go to Application" on the "Continuous Integration & Delivery" tile.

## Create a new Build Job
1. Click on "+" to create and configure a new build job.
<br>![](/exercises/ex4/images/createjob.png)

2. Fill out the form with the following values and click "Create":
  Job Name: webshop
  Repository Url: https://github.com/SAP-samples/teched2020-DT261.git
  Branch: main
<br>![](/exercises/ex4/images/createjob_form.png)

3. A "Webhook Creation" windows will open. You can ignore this windows for now as it will become relevant later when you want to start new build jobs automatically after each commit. To start a new build manually, press the arrow button in "Builds" tab. 
<br>![](/exercises/ex4/images/triggerjob.png)

4. By clicking on the build you can see in which stage of the pipeline the build currently is. After a few minutes the build should be marked as green to indicated it was running successfully.

5. By default the pipeline will build the project, run tests as well as linting and static code checks. You can enable additional features, such as the deployment at the end of the pipeline, by configuring them in the pipeline configuration file `.pipeline\config.yml`. You can find the corresponding information about how you can enable certain features and how to configure the corresponding credentials in the [Continuous Integration & Delivery documentation](https://help.sap.com/viewer/product/CONTINUOUS_DELIVERY/Cloud/en-US). However, you what have to push your changes to your own git repository. Therefore, we recommend to do this additional execercise at home if you want to explore this topic a little deeper. 

## Summary
You have successfully enabled and configured the Continuous Integration & Delivery service on SAP Cloud Platform. To achieve that you subscribed to the service, assigned the corresponding roles to your account and connected the service to our existing git repository.
