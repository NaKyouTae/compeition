package com.competition.vo.kakao;

import lombok.Data;

@Data
public class KaKaoUserVO {
	
	private String id;
	private String connected_at;
	private Account kakao_account;
	private Properties properties;
	
	@Data
	public static class Account {
		private Boolean profile_needs_agreement;
		private Profile profile;
		private Boolean has_email;
		private Boolean email_needs_agreement;
		private Boolean is_email_valid;
		private Boolean is_email_verified;
		private String email;
		private Boolean has_age_range;
		private Boolean age_range_needs_agreement;
		private String age_range;
		private Boolean has_gender;
		private Boolean gender_needs_agreement;
		private String gender;
		
		@Data
		public static class Profile {
			private String nickname;
			private String thumbnail_image_url;
			private String profile_image_url;
		}
	}
	
	@Data
	public static class Properties {
		private String nickname;
		private String profile_image;
		private String thumbnail_image;
	}
	
	
}
