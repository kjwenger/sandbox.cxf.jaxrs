package com.u14n.sandbox.cxf.jaxrs;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.u14n.sandbox.model.geography.Location;

public class LocationsResourceTest {
	private static String endpointUrl = "http://localhost:8080";
	private static Server server;

	/**
	 * @throws Exception
	 * @see <a href="http://stackoverflow.com/questions/5267423/using-a-jetty-server-with-junit-tests">Using a Jetty Server with JUnit tests</a>
	 */
	@BeforeClass
	public static void setUpClass() throws Exception {
		server = new Server(8080);
		server.setStopAtShutdown(true);
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setResourceBase("src/main/webapp");
		webAppContext.setClassLoader(
				LocationsResourceTest.class.getClassLoader());
		server.addHandler(webAppContext);
		server.start();
	}

	@BeforeClass
	public static void beforeClass() {
//		endpointUrl = System.getProperty("service.url");
	}

	@Test
	public void shouldGetLocations() throws Exception {
		Response response = WebClient
				.create(endpointUrl + "/restapi/locations")
				.accept("text/xml")
				.get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//		String value = IOUtils.toString((InputStream) response.getEntity());
//		assertEquals("SierraTangoNevada", value);
	}

//	@Test
//	public void testJsonRoundtrip() throws Exception {
//		List<Object> providers = new ArrayList<Object>();
//		providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
//		Location inputBean = new Location();
//		inputBean.setVal1("Maple");
//		WebClient client = WebClient.create(endpointUrl + "/restapi/locations/jsonBean", providers);
//		Response r = client.accept("application/json").type("application/json").post(inputBean);
//		assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
//		MappingJsonFactory factory = new MappingJsonFactory();
//		JsonParser parser = factory.createJsonParser((InputStream) r.getEntity());
//		Location output = parser.readValueAs(Location.class);
//		assertEquals("Maple", output.getVal2());
//	}
}
