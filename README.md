# befloral
TSW E-Commerce Project for University of Study of Salerno

This is a project made by Students of University of Salerno for an ecommerce of flowers.

Quick Start on Eclipse:
- Clone this repo
- Enter the cloned folder and extract eclipse-settings-befloral.7z "here" into the same folder of the project root
- Open Eclipse and click File > Open Project from File System > Select the root directory for the project you cloned
- From eclipse, right click on the Project > Properties > Java Build Path
- Change the JRE System Library > Edit > Select the one you have or an Enviroment one.
- Click con Class Path > Add Jar > Select both jars in WebContent/WEB-INF/lib (jstl, mysql connector)
- Click Apply
- Go to Project Facets, make sure the Java version matching the one you added
- Open your DBMS for MySql (if you don't have Mysql, you need to install the server)
- Reach the SQL Folder, run one by one, in naming order the SQL queries in the .sql files
- After populating your database just Right click on the project > Run As > Run on Server

It should open a new page on your browser pointing to http://localhost/ProjectName/Home 
Well done!

Navigational Diagram

![image](https://user-images.githubusercontent.com/11262725/119989737-ce91f080-bfc7-11eb-8745-95b9b1ce8569.png)

Er Diagram

![image](https://user-images.githubusercontent.com/11262725/119989768-d81b5880-bfc7-11eb-85d2-1d09d46ca104.png)
