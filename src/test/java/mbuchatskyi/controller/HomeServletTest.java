package mbuchatskyi.controller;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.Globals;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.reactive.server.WebTestClient;

public class HomeServletTest {
	private static Tomcat tomcat;
	private static final String WEB_PORT = "8080";

	@BeforeClass
	public static void startServer() throws ServletException, LifecycleException {
		String webappDirLocation = "src/main/webapp/";
		tomcat = new Tomcat();

		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = WEB_PORT;
		}

		tomcat.setPort(Integer.parseInt(webPort));

		StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
		ctx.getServletContext().setAttribute(Globals.ALT_DD_ATTR, webappDirLocation + "WEB-INF/web.xml");
		System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

		File additionWebInfClasses = new File("target/classes");
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(
				new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
		ctx.setResources(resources);

		tomcat.start();
	}

	@AfterClass
	public static void stopServer() throws LifecycleException {
		tomcat.stop();
		tomcat.destroy();
	}

	@Test
	public void testGetRequestWithValidImageId() {
		WebTestClient.ResponseSpec response = WebTestClient.bindToServer()
				.baseUrl("http://localhost:" + WEB_PORT)
				.build()
				.method(HttpMethod.GET)
				.uri("/home?image=1")
				.exchange();

		Assert.assertNotNull(response.expectStatus().is3xxRedirection());
	}
	
	@Test
	public void testGetRequestWithInvalidImageId() {
		WebTestClient.ResponseSpec response = WebTestClient.bindToServer()
				.baseUrl("http://localhost:" + WEB_PORT)
				.build()
				.method(HttpMethod.GET)
				.uri("/home?image=randomtext1@1")
				.exchange();

		Assert.assertNotNull(response.expectStatus().is3xxRedirection());
	}
}
