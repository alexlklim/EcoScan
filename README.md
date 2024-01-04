![image](https://github.com/alexlklim/EcoScan/assets/91628959/766302aa-bc86-49dd-b272-22a9b5bbc7fe)

#EcoScan
The ScanCode is a Android-based application designed for Zebra devices equipped with embedded scanners. This solution enables seamless code reading, order creation, advanced filtering, and secure transmission to the server. This documentation serves as a guide to understanding, configuring, and customizing the application for various business needs.

# Start Guide
![image](https://github.com/alexlklim/EcoScan/assets/91628959/c4cb45e0-28d6-4fd2-b0bf-d01d26004366)

# ARCHITECTURE
Codes are scanned with embedded zebra scanner and sent to the server (if server is configured). It allows reading different types of codes, creating orders and synchronization.
![image](https://github.com/alexlklim/EcoScan/assets/91628959/ee8a830c-f605-4cc6-9476-5afbc070127a)

# SERVER CONFIGURATION
The structure of Json file. It includes information about order, identifier and list of codes.
![image](https://github.com/alexlklim/EcoScan/assets/91628959/808da58a-5ad4-490f-a63f-d60b89cabe68)
To connect application to your own server, create a endpoint which can receive data and return response code 200. After this check connection in Server configuration sections end switch on Send data to server
