package edu.csu.cs370.foodStorage;
import spark.Spark;
import spark.Request;
import spark.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestfulServer 
{
	private final Logger log = LoggerFactory.getLogger(RestfulServer.class);
	private int port;
    
    public RestfulServer(int port)
    {
		this.port = port;
		this.configureResfulApiServer();
		this.processRestfulApiRequests();
    }

    private void configureResfulApiServer()
    {
		Spark.port(this.port);
		System.out.println("Server configured to run on port " + this.port);
    }

    private void processRestfulApiRequests()
    {
		Spark.post("/D2", this::d2);
		Spark.get("/", this::echoRequest);
    }

    private String d2(Request request, Response response)
    {
		System.out.println(request.body());

		return echoRequest(request, response);
    }

    private String echoRequest(Request request, Response response)
    {
		response.type("application/json");
		response.header("Access-Control-Allow-Origin", "*");
		response.status(200);

		return HttpRequestToJson(request);
    }

    private String HttpRequestToJson(Request request)
    {
		return "{\n"
			+ "\"attributes\":\""    + request.attributes()    + "\",\n"
			+ "\"body\":\""          + request.body()          + "\",\n"
			+ "\"contentLength\":\"" + request.contentLength() + "\",\n"
			+ "\"contentType\":\""   + request.contentType()   + "\",\n"
			+ "\"contextPath\":\""   + request.contextPath()   + "\",\n"
			+ "\"cookies\":\""       + request.cookies()       + "\",\n"
			+ "\"headers\":\""       + request.headers()       + "\",\n"
			+ "\"host\":\""          + request.host()          + "\",\n"
			+ "\"ip\":\""            + request.ip()            + "\",\n"
			+ "\"params\":\""        + request.params()        + "\",\n"
			+ "\"pathInfo\":\""      + request.pathInfo()      + "\",\n"
			+ "\"serverPort\":\""    + request.port()          + "\",\n"
			+ "\"protocol\":\""      + request.protocol()      + "\",\n"
			+ "\"queryParams\":\""   + request.queryParams()   + "\",\n"
			+ "\"requestMethod\":\"" + request.requestMethod() + "\",\n"
			+ "\"scheme\":\""        + request.scheme()        + "\",\n"
			+ "\"servletPath\":\""   + request.servletPath()   + "\",\n"
			+ "\"session\":\""       + request.session()       + "\",\n"
			+ "\"uri\":\""           + request.uri()           + "\",\n"
			+ "\"url\":\""           + request.url()           + "\",\n"
			+ "\"userAgent\":\""     + request.userAgent()     + "\",\n"
			+ "}\n";
    }
    
    public static void main( String[] args )
    {
        System.out.println( "Attempting to start server" );
		RestfulServer server = new RestfulServer(8080);
    }
}
