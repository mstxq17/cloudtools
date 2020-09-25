package com.xq17.cloudtools.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;

@RestController
@RequestMapping("/oauth")
public class OauthController {

	@RequestMapping("/render")
	public void renderAuth(HttpServletResponse response) throws IOException {
		AuthRequest authRequest = getAuthRequest();
		response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
	}

	@RequestMapping("/callback")
	public void login(AuthCallback callback, HttpServletResponse response) {
		AuthRequest authRequest = getAuthRequest();
		Object data = authRequest.login(callback);
		try {
			response.sendRedirect("../admin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private AuthRequest getAuthRequest() {
		return new AuthGithubRequest(
				AuthConfig.builder().clientId("8bfb365ace359d544d37")
						.clientSecret("74a7d9afd56e7ceb712d991d8478421877b4bdad")
						.redirectUri("http://10.211.55.53:8080/cloudtools/oauth/callback").build());
	}
}