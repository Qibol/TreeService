package com.treeservice.client;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import com.treeservice.util.Configuration;

/**
 * Servlet implementation class TemplateServlet
 */
public abstract class TemplateServlet extends VelocityViewServlet {
	private static final long serialVersionUID = 3637859013153315270L;
	private boolean isPage = true;
	protected String pageTitle = "";
	protected String baseUrl = "";
	protected String sharedBaseUrl = "";
	protected String velocityFile = "";

	// protected QibolUserPrincipal userPrincipal;
	protected BigInteger companyId;
	protected BigInteger userId;


	protected static final Logger log = Logger
			.getLogger(TemplateServlet.class.getName());

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		pageTitle = Configuration.getInstance().get("pageTitle");
		baseUrl = Configuration.getInstance().get("baseUrl");
		sharedBaseUrl = Configuration.getInstance().get("sharedBaseUrl");
		velocityFile = Configuration.getInstance().get("velocityFile");
		try {
			Velocity.init(getServletContext().getRealPath(
					"/WEB-INF/" + velocityFile)); //
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected Template handleRequest(HttpServletRequest request,
			HttpServletResponse response, Context context) {
		Template template = new Template();
		try {
			context.put("pageTitle", pageTitle);
			context.put("isPage", isPage);
			context.put("hasLeftMenu", false);
			context.put("hasRoof", false);
			context.put("baseUrl", baseUrl);
			context.put("sharedBaseUrl", sharedBaseUrl);
			fillContext(request, context);
			template = Velocity.getTemplate("template.vm", "UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			log.log(Level.SEVERE,
					"An error has occured while trying to serve template request:",
					e);
		}
		return template;
	}

	protected abstract void fillContext(HttpServletRequest request,
			Context context) throws Exception;

	public void setPageTitle(String title) {
		pageTitle = title;
	}

	public void NotPage() {
		isPage = false;
	}

}
