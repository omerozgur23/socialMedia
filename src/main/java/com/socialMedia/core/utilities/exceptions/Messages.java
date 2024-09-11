package com.socialMedia.core.utilities.exceptions;

public record Messages() {

	// Success Messages
	public static final String SUCCESSFULLY = "Successfully";

	// User Messages
	public static final String USER_NOT_FOUND = "User not found";
	public static final String AUTHENTICATED_USER_NOT_FOUND = "Authenticated user not found";
	public static final String OLD_PASSWORD_NOT_MATCH = "Old password not match";
	public static final String UNSUPPORTED_USER_STATUS = "Unsupported user status: ";
	public static final String USERNAME_CANNOT_BE_BLANK = "Username cannot be blank ";
	public static final String NAME_CANNOT_BE_BLANK = "Name cannot be blank ";
	public static final String BIRTH_DATE_CANNOT_BE_BLANK = "Birth date cannot be blank ";

	// SignUp Messages
	public static final String USER_EMAIL_ALREADY_EXISTS = "User email already exists";
	public static final String USER_USERNAME_ALREADY_EXISTS = "User username already exists";
	public static final String NO_ACCOUNT_FOUND = "No account found with this email";
	public static final String ACCOUNT_IS_UNCONFIRMED = "Account is unconfirmed. Please confirm your account";

	// Login Messages
	public static final String WRONG_PASSWORD = "Wrong password";
	public static final String USER_STATUS_ALREADY_ACTIVE = "User status already active";
	public static final String PLEASE_ENTER_A_VALID_EMAIL_ADDRESS = "Please enter a valid email address";
	public static final String EMAIL_CANNOT_BE_BLANK = "Email cannot be blank";
	public static final String PASSWORD_CANNOT_BE_BLANK = "Password cannot be blank";
	public static final String PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_LOWERCASE_LETTER = "Password must contain at least one lowercase letter";
	public static final String PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_UPPERCASE_LETTER = "Password must contain at least one uppercase letter";
	public static final String PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_NUMBER = "Password must contain at least one number";

	// Tweet Messages
	public static final String TWEET_NOT_FOUND = "Tweet not found with ID: ";
	public static final String TWEET_IMAGE_ID_NOT_FOUND = "Tweet Image not found with ID: ";
	public static final String TWEET_VIDEO_ID_NOT_FOUND = "Tweet Video not found with ID: ";
	public static final String TWEET_IMAGE_NOT_FOUND_WITH_TWEET_ID = "Tweet Image not found with tweet ID: ";
	public static final String TWEET_VIDEO_NOT_FOUND_WITH_TWEET_ID = "Tweet Video not found with tweet ID: ";
	public static final String YOU_HAVE_EXISTIS_EXCEEDED_THE_CHARACTER_LIMIT_280 = "You have existis exceeded the character limit (max=280)";
	public static final String YOU_HAVE_EXISTIS_EXCEEDED_THE_CHARACTER_LIMIT_200 = "You have existis exceeded the character limit (max=200)";

	// User Tweet Messages
	public static final String USER_TWEET_NOT_FOUND = "User tweet not found";

	// Token Messages
	public static final String TOKEN_NOT_FOUND = "Token not found";
	public static final String EMAIL_ALREADY_CONFIRMED = "Email already confirmed";
	public static final String TOKEN_EXPIRED = "Token expired";
	public static final String CONFIRMATION_MAIL_HAS_EXPIRED = "Confirmation mail has expired";

	// Email Sender Messages
	public static final String FAILED_TO_SEND_EMAIL = "Failed to send email";

	// Follow Messages
	public static final String USER_CANNOT_BE_FOLLOW_SELF = "User cannot be follow yourself";
	public static final String USER_ALREADY_FOLLOWED = "User is already followed";
	public static final String USER_NOT_FOLLOWED = "User is not followed";

}
