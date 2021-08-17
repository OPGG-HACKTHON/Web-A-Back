package opgg.weba.JamPick.exception;

public enum ExceptionType {
    ENTITY_NOT_FOUND("not_found"),
    ENTITY_EXCEPTION("exception");

    String value;

    ExceptionType(String value) { this.value = value; }

    String getValue() { return this.value; }
}
