package com.bctech.fashionista.service.implementation;

import com.bctech.fashionista.dto.request.AdminRequestDto;
import com.bctech.fashionista.dto.request.LoginRequestDto;
import com.bctech.fashionista.dto.response.AdminResponseDto;
import com.bctech.fashionista.entity.Admin;
import com.bctech.fashionista.exceptions.customexceptions.AdminNotFoundException;
import com.bctech.fashionista.exceptions.customexceptions.EmailAddressAlreadyExistException;
import com.bctech.fashionista.exceptions.customexceptions.WrongCredentialsException;
import com.bctech.fashionista.repository.AdminRepository;
import com.bctech.fashionista.service.AdminService;
import com.bctech.fashionista.utils.ModelMapperUtils;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public AdminResponseDto createUser(AdminRequestDto adminRequestDto) {
        adminRepository.findByEmail(adminRequestDto.getEmail()).ifPresent(user -> {
            throw new EmailAddressAlreadyExistException(adminRequestDto.getEmail());
        });
        return ModelMapperUtils.map(adminRepository.save(Admin.builder()
                .name(adminRequestDto.getName())
                .password(adminRequestDto.getPassword())
                .email(adminRequestDto.getEmail())
                .build()), AdminResponseDto.class);
    }
    @Override
    public AdminResponseDto updateAdmin(AdminRequestDto adminRequestDto, Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> {
            throw new AdminNotFoundException(id);
        });

        if (!StringUtils.isBlank(adminRequestDto.getEmail())) {
            admin.setEmail(adminRequestDto.getEmail());
        }

        if (!StringUtils.isBlank(adminRequestDto.getName())) {
            admin.setName(adminRequestDto.getName());
        }

        if (!StringUtils.isBlank(adminRequestDto.getPassword())) {
            admin.setPassword(adminRequestDto.getPassword());
        }

        return ModelMapperUtils.map(adminRepository.save(admin), AdminResponseDto.class);
    }

    @Override
    public Boolean deleteAdmin(Long id) {

        Admin admin = adminRepository.findById(id).orElseThrow(() -> {
            throw new AdminNotFoundException(id);
        });
        adminRepository.delete(admin);
        return true;
    }

    @Override
    public AdminResponseDto fetchAdmin(LoginRequestDto loginRequestDto) {

        Optional<Admin> admin = adminRepository.findByEmail(loginRequestDto.getEmail());
        if (admin.isPresent()) {
            if (admin.get().getPassword().equals(loginRequestDto.getPassword())) {
                return ModelMapperUtils.map(admin.get(), AdminResponseDto.class);
            } else {
                throw new WrongCredentialsException(loginRequestDto.getEmail());
            }
        } else {
            throw new AdminNotFoundException(loginRequestDto.getEmail());
        }


}

}
