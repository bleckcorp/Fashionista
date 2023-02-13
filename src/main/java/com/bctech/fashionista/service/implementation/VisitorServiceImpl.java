package com.bctech.fashionista.service.implementation;

import com.bctech.fashionista.dto.request.LoginRequestDto;
import com.bctech.fashionista.dto.request.VisitorRequestDto;
import com.bctech.fashionista.dto.response.AdminResponseDto;
import com.bctech.fashionista.dto.response.VisitorResponseDto;
import com.bctech.fashionista.entity.Admin;
import com.bctech.fashionista.entity.Visitor;
import com.bctech.fashionista.exceptions.customexceptions.AdminNotFoundException;
import com.bctech.fashionista.exceptions.customexceptions.VisitorNotFoundException;
import com.bctech.fashionista.exceptions.customexceptions.WrongCredentialsException;
import com.bctech.fashionista.repository.VisitorRepository;
import com.bctech.fashionista.service.VisitorService;
import com.bctech.fashionista.utils.ModelMapperUtils;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    @Override
    public VisitorResponseDto createVisitor(VisitorRequestDto visitorRequestDto) {
        visitorRepository.findByEmail(visitorRequestDto.getEmail()).ifPresent(user -> {
            throw new VisitorNotFoundException(visitorRequestDto.getEmail());
        });
        return ModelMapperUtils.map(visitorRepository.save(Visitor.builder()
                .fullName(visitorRequestDto.getName())
                .email(visitorRequestDto.getEmail())
                .password(visitorRequestDto.getPassword())
                .build()), VisitorResponseDto.class);
    }
    @Override
    public VisitorResponseDto updateVisitor(VisitorRequestDto visitorRequestDto, Long id) {
        Visitor visitor = visitorRepository.findById(id).orElseThrow(() -> {
            throw new VisitorNotFoundException(id);
        });

        if (!StringUtils.isBlank(visitorRequestDto.getEmail())) {
            visitor.setEmail(visitorRequestDto.getEmail());
        }

        if (!StringUtils.isBlank(visitorRequestDto.getName())) {
            visitor.setFullName(visitorRequestDto.getName());
        }

        if (!StringUtils.isBlank(visitorRequestDto.getPassword())) {
            visitor.setPassword(visitorRequestDto.getPassword());
        }

        return ModelMapperUtils.map(visitorRepository.save(visitor), VisitorResponseDto.class);
    }

    @Override
    public Boolean deleteVisitor(Long id) {
        Visitor visitor = visitorRepository.findById(id).orElseThrow(() -> {
            throw new VisitorNotFoundException(id);
        });
        visitorRepository.delete(visitor);
        return true;
    }

    @Override
    public VisitorResponseDto getVisitorByEmail(String email) {
        Visitor visitor = visitorRepository.findByEmail(email).orElseThrow(() -> {
            throw new VisitorNotFoundException(email);
        });
        return ModelMapperUtils.map(visitor, VisitorResponseDto.class);
    }

    @Override
    public VisitorResponseDto fetchVisitor(LoginRequestDto loginRequestDto) {
        Optional<Visitor> visitor = visitorRepository.findByEmail(loginRequestDto.getEmail());
        if (visitor.isPresent()) {
            if (visitor.get().getPassword().equals(loginRequestDto.getPassword())) {
                return ModelMapperUtils.map(visitor.get(), VisitorResponseDto.class);
            } else {
                throw new WrongCredentialsException(loginRequestDto.getEmail());
            }
        } else {
            throw new VisitorNotFoundException(loginRequestDto.getEmail());
        }

    }
}
