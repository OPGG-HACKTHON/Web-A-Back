package opgg.weba.JamPick.common;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class GetLocale {

    public static Locale getLocale() {

        return locale;
    }
}
