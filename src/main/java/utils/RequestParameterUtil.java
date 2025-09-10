package utils;

import lombok.experimental.UtilityClass;

import java.security.InvalidParameterException;

import static exception.ErrorMessages.*;


@UtilityClass
public class RequestParameterUtil {
    public static void validateParameters(String parameter1, String parameter2) {
        parameter1 = parameter1.trim();
        parameter2 = parameter2.trim();
        if (parameter1 == null || parameter1.isBlank() || parameter2 == null || parameter2.isBlank()) {
            throw new InvalidParameterException(REQUIRED_FORM_FIELD_MISSING);
        }
        if (!parameter1.matches("[а-яА-яёЁa-zA-Z ]+") || !parameter2.matches("[а-яА-ЯёЁa-zA-Z ]+")) {
            throw new InvalidParameterException(PLAYERS_NAMES_INVALID);
        }
        if (parameter1.equalsIgnoreCase(parameter2)) {
            throw new InvalidParameterException(PLAYERS_NAMES_EQUAL);
        }
        if (parameter1.length() > 20 || parameter2.length() > 20) {
            throw new InvalidParameterException(PLAYERS_NAMES_LENGTH);
        }
    }
}
