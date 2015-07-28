package org.talend.component.common;

import org.talend.component.ComponentProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("oauthProperties")
public class OauthProperties extends ComponentProperties {

	@JsonProperty
	protected String clientId;
	
	@JsonProperty
	protected String clientSecret;
	
	@JsonProperty
	protected String callbackHost;
	
	@JsonProperty
	protected int callbackPort;

	@JsonProperty
	protected String tokenFile;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getCallbackHost() {
		return callbackHost;
	}

	public void setCallbackHost(String callbackHost) {
		this.callbackHost = callbackHost;
	}

	public int getCallbackPort() {
		return callbackPort;
	}

	public void setCallbackPort(int callbackPort) {
		this.callbackPort = callbackPort;
	}

	public String getTokenFile() {
		return tokenFile;
	}

	public void setTokenFile(String tokenFile) {
		this.tokenFile = tokenFile;
	}
}