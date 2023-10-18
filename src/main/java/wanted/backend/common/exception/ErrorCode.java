package wanted.backend.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"유저 정보를 찾을 수 없습니다."),
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND,"회사 정보를 찾을 수 없습니다."),
    RECRUIT_NOT_FOUND(HttpStatus.NOT_FOUND,"채용 정보를 찾을 수 없습니다."),
    ALREADY_PROCESSED(HttpStatus.BAD_REQUEST,"이미 존재하는 프로세스입니다."),
    DB_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"Save 에러 발생.");

    private final HttpStatus httpStatus;
    private final String message;
}
