package com.socialMedia.core.utilities.exceptions;

public record Messages() {

	// Success Messages
	public static final String SUCCESSFULLY = "Successfully";

	// User Messages
	public static final String USER_NOT_FOUND = "User not found";
	public static final String AUTHENTICATED_USER_NOT_FOUND = "Authenticated user not found";
	public static final String OLD_PASSWORD_NOT_MATCH = "Old password not match";

	// SignUp Messages
	public static final String USER_EMAIL_ALREADY_EXISTS = "User email already exists";
	public static final String USER_USERNAME_ALREADY_EXISTS = "User username already exists";

	// Login Messages
	public static final String WRONG_PASSWORD = "Wrong password";
	public static final String USER_STATUS_ALREADY_ACTIVE = "User status already active";

	// Tweet Messages
	public static final String TWEET_NOT_FOUND = "Tweet not found with ID: ";
	public static final String TWEET_IMAGE_ID_NOT_FOUND = "Tweet Image not found with ID: ";
	public static final String TWEET_VIDEO_ID_NOT_FOUND = "Tweet Video not found with ID: ";
	public static final String TWEET_IMAGE_NOT_FOUND_WITH_TWEET_ID = "Tweet Image not found with tweet ID: ";
	public static final String TWEET_VIDEO_NOT_FOUND_WITH_TWEET_ID = "Tweet Video not found with tweet ID: ";

	// User Tweet Messages
	public static final String USER_TWEET_NOT_FOUND = "User tweet not found";

}
