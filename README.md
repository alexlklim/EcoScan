![image](https://github.com/alexlklim/EcoScan/assets/91628959/766302aa-bc86-49dd-b272-22a9b5bbc7fe)

#EcoScan
The ScanCode is a Android-based application designed for Zebra devices equipped with embedded scanners. This solution enables seamless code reading, order creation, advanced filtering, and secure transmission to the server. This documentation serves as a guide to understanding, configuring, and customizing the application for various business needs.

# Start Guide
Configure DataWedge

1.Intent output block
a)Enabled +
b)Intent action com.dwexample.ACTION
c)Intent delivery Broadcast intent

Configure application

1.After first running the application, the identifier will be given randomly
2.Go to Settings. Click Configure server at Server configuration section
3.Type your server address and click Check connection. If server is okay and internet work properly, Success status will be shown.
4.Switch on Send data to server to have a possibility to synchronize data. Allow Auto synchronization if you want
5.Change for proper client identifier at Client Configuration section if needed
6.Click New order, type the number of order and start scanning
7.If Internet wont be available GPS location wont be added to code
8.Order will be sent to server after completing it if auto synch is enable. In case of success the status of order will be changed.


