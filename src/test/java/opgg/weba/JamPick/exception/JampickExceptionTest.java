package opgg.weba.JamPick.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JampickExceptionTest {

    @Test
    void testExceptionType() {
        for (ExceptionType et : ExceptionType.values()) {
            assertThat(et.value).isEqualTo(et.getValue());
        }
    }

    @Test
    void testEntityType() {
        Set<String> classNames = findAllClassNames("opgg.weba.JamPick.domain");
        EntityType[] entityTypes = EntityType.values();

        assertThat(entityTypes.length).isEqualTo(classNames.size());

        for (EntityType et : entityTypes) {
            assertThat(et.value).isEqualTo(et.getValue());
            assertThat(classNames.contains(et.getValue())).isTrue();
        }
    }

    @Test
    void testJampickExceptionWithMessage() {
        String msgTemplate = "Test Exception {0} {1}";

        assertThat(JampickException.throwException(msgTemplate, "arg1", "arg2").getMessage()).isEqualTo(
                MessageFormat.format(msgTemplate, "arg1", "arg2")
        );
    }

    @Test
    void testJampickExceptionWithEntity() {
        for (ExceptionType exceptionType : ExceptionType.values()) {
            String msgTemplate = null;
            if (exceptionType.equals(ExceptionType.ENTITY_EXCEPTION)) {
                msgTemplate = JampickException.ENTITY_EXCEPTION_TEMPLATE;
            } else if (exceptionType.equals(ExceptionType.ENTITY_NOT_FOUND)) {
                msgTemplate = JampickException.ENTITY_NOT_FOUND_TEMPLATE;
            }

            for (EntityType entityType : EntityType.values()) {
                assertThat(JampickException.throwException(entityType, exceptionType, "arg1").getMessage()).isEqualTo(
                        MessageFormat.format(msgTemplate, entityType.getValue(), "arg1")
                );
            }
        }
    }

    private Set<String> findAllClassNames(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> packageName + "." + line.substring(0, line.lastIndexOf(".")))
                .collect(Collectors.toSet());
    }
}
