package com.cognizant.bank.filters;

import java.io.IOException;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import com.cognizant.bank.repositories.TokenRepository;
import com.cognizant.bank.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.DatatypeConverter;

public class JWTFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;

		String authHeader = httpReq.getHeader("authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer")) {
			throw new ServletException("Missing or invalid authentication header.");
		}

		String jwtToken = authHeader.substring(7);

		String SECRET_KEY = "testsecretkeydonotuseifapplicationispushedtoproduction";
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] signingKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
		Key signingKey = new SecretKeySpec(signingKeyBytes, signatureAlgorithm.getJcaName());
		Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwtToken).getBody();

		httpReq.setAttribute("username", claims);

		chain.doFilter(request, response);
	}
}
