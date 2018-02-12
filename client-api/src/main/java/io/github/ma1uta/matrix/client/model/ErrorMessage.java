package io.github.ma1uta.matrix.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.github.ma1uta.matrix.ErrorResponse;
import io.github.ma1uta.matrix.client.model.auth.AuthenticationStage;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Any errors which occur at the Matrix API level MUST return a "standard error response".
 */
@Getter
@Setter
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage extends ErrorResponse {

    private String[] completed;
    private AuthenticationStage[] flows;
    private Map<String, Map<String, String>> params;
    private String session;

    public ErrorMessage(String errcode, String error) {
        super(errcode, error);
    }

    public ErrorMessage(String errcode, String error, Long retryAfterMs) {
        super(errcode, error, retryAfterMs);
    }

    public ErrorMessage(String errcode, String error, String[] completed, AuthenticationStage[] flows,
                        Map<String, Map<String, String>> params, String session) {
        super(errcode, error);
        this.completed = completed;
        this.flows = flows;
        this.params = params;
        this.session = session;
    }
}
