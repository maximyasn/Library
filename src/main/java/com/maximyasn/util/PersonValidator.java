package com.maximyasn.util;

import com.maximyasn.entity.Person;
import com.maximyasn.services.PersonService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }


    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        Person person = (Person) target;

        if(person.getId() == 0 && personService.findByName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "",
                    "User with this name already exists");
        }
    }
}
