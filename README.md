# Ratpack Track MY Stuff Simple CRUD Example

A simple Groovy/Ratpack CRUD application using the simple GStorm ORM (https://github.com/kdabir/gstorm) and HSQLDB (http://hsqldb.org/) as the database.
This is a port of a version I created previously using Sinatra/Ruby.

WARNING: GStorm is a new project and might change. I actually used the 0.3/0.4 versions

This was created as a learning and proof-of-concept project for BOTH Groovy and Ratpack, and is not expected to be beautiful by any means.

As, I said, this is a learning tool and is a works-in-progress.

Known "issue":
I am not closing the connection right now. There is a feature request in Ratpack for a shutdown hook so this can be done:

https://github.com/ratpack/ratpack/issues/67

Right now the tests are in a separate folder and should be run when the main project is up.
There is only actually one single functional test using HtmlUnit, which is invoked as a simple Groovy JUnit test without any of the usual @Before @After methods. 
I will modify this, eventually to use geb.

see:

http://www.gebish.org/

I'm still learning Groovy and Ratpack and haven't yet learned Groovy-style testing using geb.

Also, feel free to use this as you see fit, but BE WARNED this isn't really production-ready code and use it at your own risk.

You have been warned!!!

NOTE:

This is based upon several resources I discovered on the internet.
I will add resource links links when I update the project.

For now see:

http://www.ratpack-framework.org/

for details on the Ratpack Framework.

and:

https://github.com/kdabir/gstorm

for details on the GStorm ORM.

