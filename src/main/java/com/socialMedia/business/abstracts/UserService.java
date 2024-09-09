package com.socialMedia.business.abstracts;

import java.util.UUID;

import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.signUp.SignUpRequest;
import com.socialMedia.dtos.user.ChangePasswordRequest;
import com.socialMedia.dtos.user.GetAllUserResponse;
import com.socialMedia.dtos.user.SuspendedUserRequest;
import com.socialMedia.dtos.user.UpdateUserRequest;
import com.socialMedia.entities.User;

public interface UserService {

	PageResponse<GetAllUserResponse> getAll();

	User create(SignUpRequest request);

	User update(UpdateUserRequest request);

	void softDelete(String email);

	void hardDelete();

	void suspendedAccount(SuspendedUserRequest request);

	void changePassword(ChangePasswordRequest request, String email);

	int enableUser(String email);

	User getUser(UUID id);

}
