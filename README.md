# DT261 - Extend SAP S/4HANA to meet unique business needs – the cloud-native way!

## Description

This repository contains the material for the SAP TechEd 2020 session called DT261 - Extend SAP S/4HANA to meet unique business needs – the cloud-native way!  

## Overview

Address your custom requirements in the cloud with side-by-side extensions. Leverage the latest cloud-native technology to communicate with SAP S/4HANA and other SAP systems. Learn how you can use the embedded support in SAP Cloud SDK to quickly launch an online, cloud-native application built on state-of-the-art technology that integrates with SAP S/4HANA Cloud.

## Requirements

You will need the following to execute our exercises.

1. Trial account on SAP Cloud Platform with access to the following services
    - Cloud Foundry
    - SAP Business Application Studio

2. A current version of Google Chrome

### Prepare upfront
After registering your [trial](https://www.sap.com/cmp/td/sap-cloud-platform-trial.html) account on SAP Cloud Platform, [enable Cloud Foundry](https://help.sap.com/viewer/a96b1df8525f41f79484717368e30626/Cloud/en-US/dc18bac42270468d84b6c030a668e003.html), and follow [this tutorial](https://developers.sap.com/tutorials/appstudio-onboarding.html) to enable SAP Business Application Studio.

## Exercises

- [Getting Started - Deploying the Web Shop](exercises/ex0/)
    - Set up your IDE
    - Run locally
    - Push to SAP Cloud Platform
- [Exercise 1 - Loading products from SAP S/4HANA Cloud](exercises/ex1/)
    - [Exercise 1.1 - Implement the products controller](exercises/ex1#exercise-11-sub-exercise-1-description)
    - [Exercise 1.2 - Configure the destination locally](exercises/ex1#exercise-12-sub-exercise-2-description)
    - Exercise 1.3 - Configure the destination on SAP Cloud Platform
- [Exercise 2 - Placing the order with SAP S/4HANA Cloud](exercises/ex2/)
    - [Exercise 2.1 - Implement the orders controller](exercises/ex2#exercise-21-sub-exercise-1-description)
    - [Exercise 2.2 - Reuse the destination configurations from exercise 1](exercises/ex2#exercise-22-sub-exercise-2-description)
- Exercise 3 - Loading promotions from SAP Promotion Pricing
    - Exercise 3.1 - Generate the client library
    - Exercise 3.2 - Implement the promotions controller
    - Exercise 3.3 - Configure the destination locally
    - Exercise 3.4 - Configure the destination on SAP Cloud Platform

## How to obtain support

Support for the content in this repository is available during the actual time of the online session for which this content has been designed. Otherwise, you may request support via the [Issues](../../issues) tab.

## License
Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved. This file is licensed under the Apache Software License, version 2.0 except as noted otherwise in the [LICENSE](LICENSES/Apache-2.0.txt) file.
