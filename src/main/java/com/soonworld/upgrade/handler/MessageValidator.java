package com.soonworld.upgrade.handler;

import com.soonworld.upgrade.exception.CustomException;
import com.soonworld.upgrade.model.dto.UpgradeItemRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


//public class MessageValidator implements Validator {
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return UpgradeItemRequestDto.class.equals(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"currentItemLevel","field.required","currentItemLevel is null!");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"upgradeItemUsed","field.required","upgradeItemUsed is null!");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"upgradeDoubleItemUsed","field.required","upgradeDoubleItemUsed is null!");
//
//        if(!errors.getAllErrors().isEmpty()) {
//            throw new CustomException(errors.getAllErrors().get(0).getDefaultMessage());
//        }
//
//    }
//}
