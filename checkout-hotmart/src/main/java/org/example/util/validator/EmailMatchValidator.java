package org.example.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.checkout.CheckoutRequest;

public class EmailMatchValidator implements ConstraintValidator<EmailMatch, CheckoutRequest> {

    @Override
    public boolean isValid(CheckoutRequest emailDTO, ConstraintValidatorContext context) {
        return emailDTO.getEmail().equals(emailDTO.getConfirmacaoEmail());
    }
}