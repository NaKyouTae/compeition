package com.mercury.util;

import javax.servlet.http.Cookie;

import com.sun.istack.Nullable;

import lombok.Data;

@Data
public class CookieUtil {
	private Cookie cookie;
    
    private CookieUtil(Builder builder) {
    	Cookie cookie = new Cookie(builder.name, builder.value);
    	
    	cookie.setComment(builder.comment);
        cookie.setDomain(builder.domain);
        cookie.setMaxAge(builder.maxAge);
        cookie.setPath(builder.path);
        cookie.setSecure(builder.secure);
        cookie.setHttpOnly(builder.httpOnly);
        cookie.setValue(builder.newValue);
        cookie.setVersion(builder.version);
    	
    	this.cookie = cookie;
    }

	public static class Builder {
		private String name;
		private String value;
		
		private String comment;
		@Nullable
	    private String domain;
	    private int maxAge;
	    @Nullable
	    private String path;
	    private boolean secure;
	    private boolean httpOnly;
	    private String newValue;
	    @Nullable
	    private int version;
	    
	    public Builder() {}
	    
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder value(String value) {
			this.value = value;
			return this;
		}
		public Builder comment(String comment) {
			this.comment = comment;
			return this;
		}
		public Builder domain(String domain) {
			this.domain = domain;
			return this;
		}
		public Builder maxAge(int maxAge) {
			this.maxAge = maxAge;
			return this;
		}
		public Builder path(String path) {
			this.path = path;
			return this;
		}
		public Builder secure(boolean secure) {
			this.secure = secure;
			return this;
		}
		public Builder httpOnly(boolean httpOnly) {
			this.httpOnly = httpOnly;
			return this;
		}
		public Builder newValue(String newValue) {
			this.newValue = newValue;
			return this;
		}
		public Builder version(int version) {
			this.version = version;
			return this;
		}
		
		public CookieUtil build() {
			return new CookieUtil(this); 
		};
	}
}
