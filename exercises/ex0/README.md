# Deploying the web shop

In this exercise, you will run the web shop in your IDE, then you will package the deployable artefact and deploy it to SAP Cloud Platform. At the end you should be able to access the deployed web shop using your browser.

## Set up your IDE
First you will make your IDE ready and import the exercise application so that you can start coding.

1. Open SAP Business Application Studio as described in [this tutorial](https://developers.sap.com/tutorials/appstudio-onboarding.html). You should see the `Dev Spaces` screen before proceeding to the next step.

2. Create a new dev space using the displayed button. Give it a name, then select `SAP Cloud Business Application`, and click `Create Dev Space`.
<br>![](/exercises/ex0/images/dev_space_creation.png)

3. Wait for the new dev space to start. Once it is in the state `RUNNING`, click the space name to launch the IDE.
<br>![](/exercises/ex0/images/dev_space_running_state.png)

4. Once the Business Application Studio has opened, make sure you are viewing the `Welcome` view. Close any other anouncements and popups. Click the link `Clone from Git` in the `Welcome` view, then paste the following git repository URL in the text field, and hit `ENTER` on your keyboard.
```
https://github.com/SAP-samples/teched2020-DT261.git
```
<br>![](/exercises/ex0/images/clone.png)

5. Click the button `Open Workspace`. In the dialog `Open Workspace`, navigate to `projects/teched2020-DT261/webshop` and click `Open`.
<br>![](/exercises/ex0/images/open_workspace.png)

Have a look at the view `EXPLORER` on the left. If you can expand the source directories to make it looks like this, you have successfully completed this section and your IDE is set up.
<br>![](/exercises/ex0/images/IDE_ready.png)


## Run the web shop in your IDE
In this section, you will run the exercise application in your IDE. This is very useful while coding as you will be able to have a look at your application without deploying it to SAP Cloud Platform.

6. Open a terminal. It will start in your workspace directory `~/projects/teched2020-DT261/webshop`.
<br>![](/exercises/ex0/images/open_terminal.png)

7. Change to the `application` subdirectory and run Spring Boot.
```
~/projects/teched2020-DT261/webshop/application
mvn spring-boot:run
```

8. Watch for the notification on the bottom right and allow it to open the application in a new browser tab. Note that you need to hit `ENTER` on your keyboard after clicking the button.
<br>![](/exercises/ex0/images/open_app_in_tab.png)

Did the web shop open in a new brower tab? If not, make sure to allow pop-up when chrome asks for it.
Congratulations, you have completed this section and can now run the exercise app in your IDE. This allows you to easily review and debug while coding the exercise.

Note that these steps did not deploy the application on SAP Cloud Platform. Your started application will be only accessible as long as you keep it running in your IDE.

To stop the application, close the browser tab, return to the terminal and hit `CTRL + C` on your keyboard.


## Deploy the web shop to SAP Cloud Platform

9. Open your SAP Cloud Platform Cockpit in a new browser tab and navigate to the subaccount overview page. Locate the section `Cloud Foundry` as described in the prerequisites. Copy the API Endpoint URL.
<br>![](/exercises/ex0/images/copy_api_endpoint.png)

10. Switch back to SAP Business Application Studio and run the following command in the terminal.

```
cf login
```

The command will ask you to provide the API endpoint copied in the previous step, as well as your credentials. Make sure to target the correct Cloud Fondry org and space where you want to deploy the exercise app. In our screen shot, we are using the org `TechEd` and space `2020`. An org should be already created in your account while activating Cloud Foundry. If you don't have any spaces, click the `Create Space` in the SAP Cloud Platform cockpit.

The result will look similar to this:

![cf login](/exercises/ex0/images/cf-login.png)

11. Make sure your terminal is in your workspace directory `~/projects/teched2020-DT261/webshop` (see first command below) and then run the subsequent commands.
```
cd ~/projects/teched2020-DT261/webshop
mvn package
cf push
```

12. Copy the application URL from the output of the above commands and open it it a new browser tab.
<br>![](/exercises/ex0/images/copy_app_URL.png)

Now you have deployed your application on SAP Cloud Platform and can access it using the URL as described above. You can also review the Cloud Foundry org and space to find more details about your application instance.


## Summary

Now that you have set up your IDE, run the application in the IDE, and deployed the application to SAP Cloud Platform, you are ready to start coding.
Continue to [Exercise 1 - Loading products from SAP S/4HANA Cloud](../ex1/README.md).
