package dmu.noonsub_backend.domain.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private LocalDateTime timestamp;
    private String code;
    private String message;
    private String path;
    private List<FieldError> errors;

    private ErrorResponse(final ErrorCode code, final String path) {
        this.timestamp = LocalDateTime.now();
        this.code = code.getCode();
        this.message = code.getMessage();
        this.path = path;
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(final ErrorCode code, final String path, final List<FieldError> errors) {
        this.timestamp = LocalDateTime.now();
        this.code = code.getCode();
        this.message = code.getMessage();
        this.path = path;
        this.errors = errors;
    }

    public static ErrorResponse of(final ErrorCode code, final String path) {
        return new ErrorResponse(code, path);
    }

    public static ErrorResponse of(final ErrorCode code, final String path, final BindingResult bindingResult) {
        return new ErrorResponse(code, path, FieldError.of(bindingResult));
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String reason;

        private FieldError(final String field, final String reason) {
            this.field = field;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<FieldError> fieldErrors = bindingResult.getFieldErrors().stream()
                    .map(error -> new FieldError(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());
            return fieldErrors;
        }
    }
}

