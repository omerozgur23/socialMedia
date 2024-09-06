package com.socialMedia.business.abstracts;

import java.util.UUID;

import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.user.ChangePasswordRequest;
import com.socialMedia.dtos.user.GetAllUserResponse;
import com.socialMedia.dtos.user.UpdateUserRequest;
import com.socialMedia.entities.User;

public interface UserService {

	PageResponse<GetAllUserResponse> getAll();

	User update(UpdateUserRequest request);

	void softDelete(String email);

	void hardDelete();

	void changePassword(ChangePasswordRequest request, String email);

	User getUser(UUID id);

}
