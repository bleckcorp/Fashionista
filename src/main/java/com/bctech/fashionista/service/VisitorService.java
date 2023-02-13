package com.bctech.fashionista.service;

import com.bctech.fashionista.dto.request.LoginRequestDto;
import com.bctech.fashionista.dto.request.VisitorRequestDto;
import com.bctech.fashionista.dto.response.AdminResponseDto;
import com.bctech.fashionista.dto.response.VisitorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface VisitorService {


    VisitorResponseDto createVisitor(VisitorRequestDto visitorRequestDto);

    VisitorResponseDto updateVisitor(VisitorRequestDto visitorRequestDto, Long id);

    Boolean deleteVisitor(Long id);

    VisitorResponseDto getVisitorByEmail(String email);

    VisitorResponseDto fetchVisitor(LoginRequestDto loginRequestDto);
}
