// October 30, 2017
// CS 316 - node.js

var http = require("http"),
    url = require('url');

const hostname = 'iris.cs.uky.edu';
const STARTPORT = 2000;
const ENDPORT = 30000;
//random port for a more secure connection
const randomPort = Math.floor(Math.random() * (30000 - 2000) + 2000);
const fs = require('fs');
var randomNum = (Math.floor(Math.random() * (4 - 1) + 1)); //33% 

var pattern = /[a-zA-Z0-9_][.][jpg|mp3]$/;

console.log("RANDOM PORT NUMBER IS: ", randomPort);
console.log("RANDOM NUMBER IS:      ", randomNum);


//function creates server
function doprocess(request, response){
	var data_copy;
	
	//Copy what is searched and see if it is a valid search
	var xurl = request.url;

	if (!xurl.match(pattern)){
		xurl = "wrongURL.html";}
	
	if (randomNum == 3){
		xurl = "advert.jpg";
	}
	xurl = xurl.substring(xurl.lastIndexOf("/") + 1, xurl.length);
	
fs.readFile(xurl, /*"utf8",*/function(err, data){
		if (err){
			xurl = "wrongURL.html";
			xurl = xurl.substring(xurl.lastIndexOf("/") + 1, xurl.length);
			fs.readFile(xurl, function(err, data){
			if (err) throw err;
			else{
				//copies data into another variable
				data_copy = data;
				//displays data saved
				response.end(data_copy);
			}
			});
		}
		else{
			data_copy = data;
			response.end(data_copy);
		}

});

	response.statusCode = 200;
	response.setHeader('Content-Type', 'text/plain');
	console.log("URL SEARCHED FOR IS: ", xurl); //shows what user actually searched for
}



console.log('Server started. Listing on '+hostname+':'+randomPort);
console.log('User is searching for the following:\n');
//create server, open connection with random # & hostname given
var server = http.createServer(doprocess);
server.listen(randomPort,hostname); //change port to randomPort
