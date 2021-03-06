package br.senai.sc.ti2014n1.daniel.dwgames.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sc.tii2014n1.pw4.daniel.dwgames.mb.SessaoMB;

@WebFilter(urlPatterns = "/admin/*")
public class SessaoFilterAdmin implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		SessaoMB sessaoMB = (SessaoMB) httpServletRequest.getSession()
				.getAttribute("sessaoMB");

		if (sessaoMB == null || !sessaoMB.estaLogadoAdimin()) {
			HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
			httpServletResponse.sendRedirect(httpServletRequest
					.getContextPath().concat("/index.xhtml"));
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}
