package api.endpoints;

/*here we will maintain all URLs 

Swagger URI -----> https://petstore.swagger.io  ----> Base URL, after this is Endpoint

Create User (POST) : https://petstore.swagger.io/v2/user
Get User (GET) : https://petstore.swagger.io/v2/user/{username}
Update User (PUT) : https://petstore.swagger.io/v2/user/{username}
Delete USer (DELETE) : https://petstore.swagger.io/v2/user/{username}


*/


public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2" ; //to access it from anywhere using classname - public & static 
	
	//User Module
	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String put_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";
	
	//pet module
		//here we will keep all the pet module endpoints 
	
	//store module 
		//here we will keep all the store module endpoints 
	
}
