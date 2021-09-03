package opgg.weba.JamPick.exception;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class JampickException {
    public final static String ENTITY_NOT_FOUND_TEMPLATE = "요청한 {0}은 존재하지 않습니다. 요청 값: {1}";
    public final static String ENTITY_EXCEPTION_TEMPLATE = "오류가 발생 했습니다. Detail: {0}";

    public static RuntimeException throwException(String template, String... args) {
        return new RuntimeException(format(template, args));
    }

    public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
        return new RuntimeException(format(entityType, exceptionType, args));
    }

    private static String format(String template, String... args) {
        return MessageFormat.format(template, (Object[]) args);
    }

    private static String format(EntityType entityType, ExceptionType exceptionType, String... args) {
        ArrayList<String> argList = new ArrayList<>(Arrays.asList(args));
        argList.add(0, entityType.getValue());

        if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return MessageFormat.format(ENTITY_NOT_FOUND_TEMPLATE, argList.toArray());
        } else if (ExceptionType.ENTITY_EXCEPTION.equals(exceptionType)) {
            return MessageFormat.format(ENTITY_EXCEPTION_TEMPLATE, argList.toArray());
        }

        throw throwException(String.format("Unsupported exception type. Passed exception type: %s", exceptionType.getValue()));
    }
}
