package com.company.organization.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.ZonedDateTime;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public final class ResponseData<D> implements Serializable {

    private Boolean success;
    private Integer code;
    private String message;
    private D data;
    private String path;

}