package com.mercury.util;

import javax.servlet.http.Cookie;

import com.sun.istack.Nullable;

import lombok.Data;
/**
*
*	Required Variable name, value, comment, maxAge, secure, httpOnly
*
* @author nkt
*
* Create by User Date : 2020. 9. 23.
*
*/
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
	    private int maxAge;
	    private boolean secure;
	    private boolean httpOnly;
	    
	    @Nullable
	    private String domain;
	    @Nullable
	    private String path;
	    @Nullable
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
		public Builder maxAge(int maxAge) {
			this.maxAge = maxAge;
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
		
		@Nullable
		public Builder domain(String domain) {
			this.domain = domain;
			return this;
		}
		@Nullable
		public Builder path(String path) {
			this.path = path;
			return this;
		}
		@Nullable
		public Builder newValue(String newValue) {
			this.newValue = newValue;
			return this;
		}
		@Nullable
		public Builder version(int version) {
			this.version = version;
			return this;
		}
		
		public CookieUtil build() {
			return new CookieUtil(this); 
		};
	}
}
