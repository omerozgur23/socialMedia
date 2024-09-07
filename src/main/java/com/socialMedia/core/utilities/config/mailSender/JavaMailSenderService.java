package com.socialMedia.core.utilities.config.mailSender;

public interface JavaMailSenderService {

	void send(String to, String email);

	String buildEmail(String name, String token);
}
