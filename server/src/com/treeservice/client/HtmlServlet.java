package com.treeservice.client;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.treeservice.util.ContextFiller;
import com.treeservice.util.VMPages;

public class HtmlServlet extends TemplateServlet {

	private static final long serialVersionUID = 6184830629733208245L;

	private static final String REQUEST_ATTR_IS_WRONG_PAGE = "com.treeservice.client.HtmlServlet.isWrongPage";

	@Override
	protected void fillContext(HttpServletRequest request, Context context)
			throws Exception {
		String pathInfo = request.getPathInfo();
		if (StringUtils.isEmpty(pathInfo) || pathInfo.length() < 2) {
			request.setAttribute(REQUEST_ATTR_IS_WRONG_PAGE, true);
			return;
		}
		String[] html = pathInfo.split("/");// .getParameter("html");
		context.put("page", pathInfo.substring(1) + ".vm");

		// все параметры из запроса, на всякий пожарный
		HashMap<String, Object> params = new HashMap<String, Object>();
		Enumeration<String> paramNames = request.getParameterNames();
		String key = null;
		while (paramNames.hasMoreElements()) {
			key = paramNames.nextElement();
			params.put(key, request.getParameter(key));
		}
		
		VMPages page = VMPages.cast(html);
		if (page != null) {
			ContextFiller.fillContext(page, context, companyId, userId, params);
		} else {
			request.setAttribute(REQUEST_ATTR_IS_WRONG_PAGE, true);
		}
	}

	@Override
	protected void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String pathInfo = request.getPathInfo();
		Boolean isWrongPage = (Boolean) request
				.getAttribute(REQUEST_ATTR_IS_WRONG_PAGE);
		if (isWrongPage != null && isWrongPage) {
			response.sendError(404, "/pages"
					+ (pathInfo != null ? pathInfo : ""));
			return;
		}

		try {
			super.doRequest(request, response);
		} catch (ResourceNotFoundException e) {
			log.log(Level.SEVERE,
					"A ResourceNotFoundException has occured while trying to serve template request:",
					e);
			response.sendError(404, "/secure/pages"
					+ (pathInfo != null ? pathInfo : ""));
		}
	}

}