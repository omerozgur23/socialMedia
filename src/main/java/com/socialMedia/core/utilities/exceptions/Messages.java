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
	
	// Retweet Messages
	public static final String THIS_TWEET_ALREADY_RETWEETED = "This tweet already retweeted";
	public static final String THIS_TWEET_DOES_NOT_RETWEETED = "This tweet doesn't retweeted";

	// Like Messages
	public static final String TWEET_IS_ALREADY_LIKED = "Tweet is already liked";

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

	// Survey Messages
	public static final String SURVEY_NOT_FOUND = "Survey not found with ID: ";
	public static final String SURVEY_TITLE_CANNOT_BE_BLANK = "Survey title cannot be blank";
	public static final String SURVEY_TITLE_CHARACTER_LIMIT_EXCEEDED_200 = "Survey title charcter limit exceeded (max=200)";
	public static final String SURVEY_FINISHED_DATE_CANNOT_BE_BLANK = "Survey finished date cannot be blank";
	public static final String SURVEY_OPTIONS_DESCRIPTION_CANNOT_BE_BLANK = "Survey options cannot be blank";
	public static final String SURVEY_ID_CANNOT_BE_NULL = "Survey ID cannot be null";
	public static final String SURVEY_OPTION_ID_CANNOT_BE_NULL = "Survey option ID cannot be null";
	public static final String SURVEY_FINISHED_DATE_MUST_BE_AT_LEAST_5_MINUTES_LATER = "Survey finished date must be at least 5 minute later";
	public static final String SURVEY_FINISHED_DATE_MUST_BE_WITHIN_EIGHT_DAYS = "Survey finished date must be within eigth day";

	// Survey Option Messages
	public static final String SURVEY_OPTION_NOT_FOUND = "Survey option not found with ID: ";
	public static final String SURVEY_MUST_HAVE_BETWEEN_2_AND_4_OPTIONS = "Survey must have between 2 and 4 options";
	public static final String SURVEY_OPTION_DESCRIPTION_MUST_BE_25_CHARACTERS_OR_LESS = "Survey option description must be 25 characters or less";
	public static final String CANNOT_DELETE_SOMEONE_ELSE_SURVEY = "Cannot delete someone else's survey";

	// Survey Answer Messages
	public static final String SURVEY_ANSWER_NOT_FOUND = "Survey answer not found with ID: ";
	public static final String YOU_CAN_ONLY_VOTE_ONCE_IN_SURVEY = "You can only vote once in survey";
	public static final String SURVEY_CREATOR_CANNOT_VOTE_IN_THEIR_OWN_SURVEY = "Survey creator cannot vote in their own survey";
	public static final String SURVEY_IS_FINISHED_CANNOT_BE_ANSWER = "Survey is finished. Cannot be answer";

	// Comment Messages
	public static final String COMMENT_NOT_FOUND = "Comment not found with ID: ";
	public static final String COMMENT_ID_CANNOR_BE_NULL = "Comment ID cannot be null";
	public static final String COMMENT_CANNOT_BE_BLANK = "Comment cannot be blank";
	public static final String TWEET_ID_CANNOT_BE_NULL_IN_COMMENT = "Tweet ID cannot be null in comment";
	public static final String YOU_HAVE_EXISTIS_EXCEEDED_THE_COMMENT_CHARACTER_LIMIT_280 = "You have existis exceeded the character limit (max=280)";

}
