# Deploying the web shop

In this exercise, you will run the web shop in your IDE, then you will package the deployable artefact and deploy it to SAP Cloud Platform. At the end you should be able to access the deployed web shop using your browser.

## Set up your IDE

## Run locally

## Push to SAP Cloud Platform

After completing these steps you will have....

1.	Click here.
<br>![](/exercises/ex0/images/00_00_0010.png)

2.	Insert this code.
```
 DATA(lt_params) = request->get_form_fields(  ).
 READ TABLE lt_params REFERENCE INTO DATA(lr_params) WITH KEY name = 'cmd'.
  IF sy-subrc <> 0.
    response->set_status( i_code = 400
                     i_reason = 'Bad request').
    RETURN.
  ENDIF.
```

## Summary

Now that you have ... 
Continue to - [Exercise 1 - Exercise 1 Description](../ex1/README.md)
