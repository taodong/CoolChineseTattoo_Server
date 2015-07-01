package com.daijt.cth.adaptor;

import javax.xml.transform.Source;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;


import com.daijt.cth.bo.AccessToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.representation.Form;


@Component
public class BingTranslatorAdaptor {
	
	public static AccessToken getAccessToken() {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		
		WebResource webResource = client.resource("https://datamarket.accesscontrol.windows.net/v2/OAuth2-13");
		
		Form input = new Form();
		input.add("client_id", "6d45fd93-c9c3-4416-8ea4-4dc9e03e37f8");
		input.add("client_secret", "cth1234567890cth123456");
		input.add("scope", "http://api.microsofttranslator.com");
		input.add("grant_type", "client_credentials");
		
		ClientResponse response = 
			webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, input);
		
		AccessToken output = response.getEntity(AccessToken.class);
        // String accessToken = output.getAccess_token();
        
        return output;
	}
	
	public static String translate(String url, String token, RestOperations restTemplate,
			Jaxp13XPathTemplate xpathTemplate) throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
		
		ResponseEntity<Source> response = restTemplate.exchange(url,
					HttpMethod.GET,
			      new HttpEntity<byte[]>(headers),
			      Source.class);
		
		Source xmlEl = response.getBody();
		
		String chText = xpathTemplate.evaluateAsString("/*", xmlEl);
		
		return chText;
	}
}
