Server shall execute and run properly on the CS.UKY.EDU systems.
Your server shall accept HTTP requests via URLs in the following
format:
/[a-zA-Z0-9_]*.mp3
or
/[a-zA-Z0-9_]*.jpg

[NOTE: these are NOT complete solutions to the reg. expressions]

Valid examples:

/song1.mp3
/SongA.mp3
/someMusic.mp3

/album1.jpg
/newalbum1.jpg
/weirdalbum8.jpg

Invalid examples:
/../somefile.jpg
/.jpg
/index.html
/somedir/song1.mp3


Your program will return one of the four following things:
1) the mp3 file they asked for (a song)
2) the jpg file they asked for (album art)
3) an HTML file denoting an error.
4) "advert.jpg" - see below:

5) 33% of the time, your program will return file file "advert.jpg",
instead of the requested mp3/jpg file they requested.


STEPS:

1) call http.createServer(serveURL)
serveURL() is a function that you shall write to process
requests from the user via their browser/client.
2) serveURL() will do the following 3 things:
1) output to the console the URL requested
2) use a regular expression to make sure the URL
requested only contains the upper and lower case
characters, the digits 0-9 and the _ (underscore), then
a . (period) and the extension "jpg" or "mp3".

3) call (3) separate functions, that you shall write.

giveAdvert() - 33% of the time, no matter what URL
they ask for, your program will call giveAdvert()
and that function will give them the file "advert.jpg"
instead of what they asked for.

giveJPG() - if the URL ends in ".jpg" your program
shall call this function and give them the file
requested, setting the content-type appropriately. 
If the file does not exist, return a 403 error code 
and an appropriate error text.

giveMP3() - if the URL ends in ".mp3" your program
shall call this function and give them the file
requested, setting the content-type appropriately.
If the file does not exist, return a 403 error code
and an appropriate error text.

OPTIONAL: you can combine giveJPG() and giveMP3()
into one function, giveFILE(). This is your choice.

OPTIONAL: you can check if a file exists for a requested URL
in serveURL() before calling the give*() functions.

REQUIRED: your program MUST use fs.readFile(), it MUST NOT
use fs.readFileSync() !

 The http object requires a call to the listen() method. Your
program shall call this with at least one option, a port number.
The port number shall be a random number between 
STARTPORT and ENDPORT inclusive.

Your program shall output to the console, the URL used, including
port, like this: 
"Server started. Listing on http://bel.cs.uky.edu:7876"

STARTPORT and ENDPORT will be defined constants at the top
of your program. You should use them as 2000 and 30000.
I will change them as a test. What happens when I set them
both to 8888 ? Your program should work correctly.

Attached advert.jpg, music11.mp3, wrongURL.html
=====================================================================

