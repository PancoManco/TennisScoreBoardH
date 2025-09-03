package exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {
    public static final String REQUIRED_FORM_FIELD_MISSING = "Одно из полей ввода имени игрока пустое!";
    public static final String PLAYERS_NAMES_INVALID = "Имена игроков должно содержать только английские или русские буквы!";
    public static final String PLAYERS_NAMES_EQUAL = "Имена игроков не должно быть одинаковым!";
    public static final String PLAYERS_NAMES_LENGTH = "Имена игроков не должно превышать 20 букв!";

    public static final String ERROR_FINDING_BY_NAME = "Ошибка при поиске игрока по имени в базе данных";
    public static final String ERROR_SAVING_PLAYER = "Ошибка при сохранение игрока в базу данных";
    public static final String ERROR_SAVING_MATCH = "Ошибка при сохранения матча в базу данных";
}
