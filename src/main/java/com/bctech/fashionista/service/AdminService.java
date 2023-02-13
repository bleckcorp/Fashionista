package com.bctech.fashionista.service;

import com.bctech.fashionista.dto.request.AdminRequestDto;
import com.bctech.fashionista.dto.request.LoginRequestDto;
import com.bctech.fashionista.dto.response.AdminResponseDto;

public interface AdminService {
    AdminResponseDto createUser(AdminRequestDto adminRequestDto);

    AdminResponseDto updateAdmin(AdminRequestDto adminRequestDto, Long id);

    Boolean deleteAdmin(Long id);

    AdminResponseDto fetchAdmin (LoginRequestDto loginRequestDto);

}
