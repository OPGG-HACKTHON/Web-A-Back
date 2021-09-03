package opgg.weba.JamPick.common;

public enum ResponseCode {
    SUCCESS(0),
    NOT_FOUND(100),
    SERVER_ERROR(150);

    Integer value;

    ResponseCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
