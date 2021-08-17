package opgg.weba.JamPick.exception;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class JampickException {
    public final static String ENTITY_NOT_FOUND_TEMPLATE = "Requested {0} does not exists. Requested value: {1}";
    public final static String ENTITY_EXCEPTION_TEMPLATE = "Requested {0} raise exception. Detail: {1}";

    public static RuntimeException throwException(String template, String... args) {
        return new RuntimeException(format(template, args));
    }

    public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
        return new RuntimeException(format(entityType, exceptionType, args));
    }

    private static String format(String template, String... args) {
        return String.format(template, (Object[]) args);
    }

    private static String format(EntityType entityType, ExceptionType exceptionType, String... args) {
        ArrayList<String> argList = new ArrayList<>(Arrays.asList(args));
        argList.add(0, entityType.getValue());

        if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return String.format("Requested {0} does not exists. Requested value: {1}", argList.toArray());
        } else if (ExceptionType.ENTITY_EXCEPTION.equals(exceptionType)) {
            return String.format("Requested {0} raise exception. Detail: {1}", argList.toArray());
        }

        throw throwException("Unsupported exception type. Passed exception type: {0}", exceptionType.getValue());
    }
}
