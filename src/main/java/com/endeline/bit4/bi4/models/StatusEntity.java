package com.endeline.bit4.bi4.models;

import lombok.Data;

/**
 * Class have only status field, used in rest response.
 *
 */
@Data
public class StatusEntity {
    private String status;

    public StatusEntity(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "StatusEntity{" +
                "status='" + status + '\'' +
                '}';
    }
}
