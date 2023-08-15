# Noxus
## Introduction
It is a simple framework made using **java sockets**.
The goal is to implement http protocol using sockets.

## Requirements
To run this framework you will need
* Windows operating system
* java installed

## Functionalities
* Can make http get and post requests
* Can send response as
  * File
  * Download
  * JSON
* Has connection to inbuild sqlite database
* Restart automatically on changes

## Set up Noxus
* Download the files from the github repository
* Extract the file in any location
* Add the extracted folder which contain noxus-create.exe to PATH

## Create a project
create the project by the following command in your terminal
~~~
noxus-create <Project name>
~~~
## Run project
Go to the project folder and run the folling command in your terminal
~~~
run
~~~
## Output
Go to your browser and enter your url as localhost:8000
The port can be changed by modifying the main/Settings.java file

## Make your URL
* Open the project in your editor
* Go to main/Url.java
* You can add your URL by using the add function in the Url class
<pre>
public class Url extends UrlDefine {
    public Url(){
        <b>add("", new Home());</b>
    }    
}
</pre>
* Here the Home is the URL handler ie. the class that will run on the call of that URL.

## Make the URL Handler
* Go to main/UrlFunctions.java
* You can create your handlers for URL in this file
* In order to make a handler class your class implement **UrlFunctionCaller interface** which has function run of return type Responce data
<pre>
class Home implements UrlFunctionCaller{
    public ResponceData run(RequestData request) {
        ResponceData rd = Responce.fileResponce("welcome.html");
        return rd;
    }
}
</pre>
* We will discuss about the different availabe responces in the next session
* An object of ResponceData must be returned
## Responce Types
* There are 3 types of responce available
   * File
   * Download
   * JSON
### File Responce
* File responce is used to return the html file for that url
* Its argument is a string ie. the name of the file
<pre>
ResponceData rd = Responce.fileResponce("welcome.html");
</pre>
* The html file that is to be rendered must be stored in the main/templates folder
* you can change the location of the templates folder in the Settings.java file
### Download Responce
* Download responce is used to send a file for download
* This can be achieved by making an URL for download that is called by a form on the html file like
~~~
<form action="download">
    <button type="submit">submit</button>
</form>
~~~
* **Note :** The url for download must be followed by a **?**
<pre>
add("download?", new Download());
</pre>
* In the UrlFunction use the downloadResponce function whose argument is the name of the file as String
<pre>
class Download implements UrlFunctionCaller{
    public ResponceData run(RequestData request) {
        ResponceData rd = Responce.downloadResponce("image.png");
        return rd;
    }
}
</pre>
* The file that is downloaded must be in the media folder
### JSON Responce
* Json can be created by using the JSON class provided in the functionality.json package
<pre>
import functionality.json.JSON;
</pre>  
* Create a JSON object
* You can add data to it using its add function
* The add function has two parameters key and value
* the key must be of type string and the value can be of the folling types
  * integer
  * string
  * boolean
  * float
  * null
  * another JSON object
  * JSONArray object
<pre>
JSON json = new JSON();
json.add("0",120);
json.add("1","100");
</pre>
* This JSON onject must be passed to jsonResponce function to send it as an responce
<pre>
ResponceData rd = Responce.JSONResponce(json);
</pre>
#### JSONArray
* You can also add arrays to json by creating a JSONArray object
* they can contain all objects that a json contains
<pre>
JSON json = new JSON();
JSONArray arr = new JSONArray();
        
arr.add(1);
arr.add("hello");

json.add("0", arr);
</pre>
## Request Information
### Header Data
 you can access the Header information by using the header function given by the request parameter in the handler class
<pre>
class Home implements UrlFunctionCaller{
    public ResponceData run(RequestData request) {
        <b>System.out.println(request.header("Content-Length"));</b>
        ResponceData rd = Responce.fileResponce("welcome.html");
        return rd;
    }
}
</pre>

### Post Data
 you can access the Post information by using the post function given by the request parameter in the handler class
<pre>
class Home implements UrlFunctionCaller{
    public ResponceData run(RequestData request) {
        <b>System.out.println(request.post("name"));</b>
        ResponceData rd = Responce.fileResponce("welcome.html");
        return rd;
    }
}
</pre>
## Database connection
* You can get the connection instance for the inbuilt sqllite database by Database.getConnection() method present in functionality.DatabaseConnection
<pre>
import java.sql.Connection;
import functionality.DatabaseConnection;
</pre>
<pre>
Connection con = DatabaseConnection.getConnection();
</pre>
### Inspired by Django Python
