package com.endeline.bit4.bi4.enums;

/**
 * Enum class to hold some text status.
 */
public enum Status {
    OK("OK");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
