package com.socialMedia.dtos.signUp;

import org.springframework.context.ApplicationEvent;

import com.socialMedia.entities.User;

//@Data
//@EqualsAndHashCode(callSuper=false)
public class RegistrationCompleteEvent extends ApplicationEvent {

	private User user;
	private String applicationUrl;

	public RegistrationCompleteEvent(User user, String applicationUrl) {
		super(user);
		this.user = user;
		this.applicationUrl = applicationUrl;
	}

	public User getUser() {
		return user;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

}
