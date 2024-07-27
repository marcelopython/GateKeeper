package com.gateKeeper.manager.validation.validator;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.gateKeeper.manager.validation.CPFOrCNPJ;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class CPFOrCNPJValidator implements ConstraintValidator<CPFOrCNPJ, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        try {
            if (value.length() == 11) {
                new CPFValidator().assertValid(value);
            } else if (value.length() == 14) {
                new CNPJValidator().assertValid(value);
            } else {
                return false;
            }
        } catch (InvalidStateException e) {
            return false;
        }

        return true;
    }
}
