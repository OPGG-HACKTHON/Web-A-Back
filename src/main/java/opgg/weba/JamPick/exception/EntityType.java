package opgg.weba.JamPick.exception;

import opgg.weba.JamPick.domain.*;

public enum EntityType {
    GENRE(Genre.class.getName()),
    INDIE_APP(IndieApp.class.getName()),
    INDIE_APP_DETAIL(IndieAppDetail.class.getName()),
    MOVIE(Movie.class.getName()),
    SCREENSHOT(Screenshot.class.getName());

    String value;

    EntityType(String value){ this.value = value; }

    String getValue() { return this.value; }
}
