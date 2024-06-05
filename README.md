# usergod
A simple RESTful Web Application for registering and managing users without any security features. Implemented in Jakarta EE 10.

# Prerequisites
For launching and using the application, you should have Docker installed.

A manual installation of Maven or JDK is not required. Maven is available in the project root as a wrapper. 

# Build and Run
On Linux and MacOS, it is just enough to launch the `deploy.sh` script in the project root.

Go to the project root folder using the `cd` command and simply run the script with the following command

```bash
./deploy.sh
```

If the script is not running, it is most probably not marked as an executable. Run the following command and try executing the script again:
```bash
chmod +x deploy.sh
```