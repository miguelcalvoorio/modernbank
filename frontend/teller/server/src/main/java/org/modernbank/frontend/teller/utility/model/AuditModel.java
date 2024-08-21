package org.modernbank.frontend.teller.utility.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "Trace of changes",
    description = "Traceability of the changes")
@JsonInclude(Include.NON_NULL)
public class AuditModel {
    private String uuid;
    private String fullName;
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "Party {" +
            "uuid: '" + this.uuid + "'," +
            "fullname: '" + this.fullName + "'," +
            "timestamp: '" + this.timestamp + "'}";
    }
}
