package TODOLIST.atom.adviser;

import TODOLIST.atom.exception.BoardNotFoundException;
import TODOLIST.atom.exception.MemberNotFoundException;
import TODOLIST.atom.exception.UpdateFailureException;
import TODOLIST.atom.exception.WriteFailureException;
import TODOLIST.atom.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response boardNotFoundException() {
        return Response.failure(404, "게시글을 찾을 수 없습니다");
    }

    @ExceptionHandler(WriteFailureException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response writeFailureException() {
        return Response.failure(404, "게시글 저장에 실패하였습니다.");
    }

    @ExceptionHandler(UpdateFailureException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response updateFailureException() {
        return Response.failure(404, "게시글 수정에 실패하였습니다.");
    }
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response memberNotFoundException() {
        return Response.failure(404, "해당 회원을 찾을 수 없습니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e){
        return Response.failure(400,e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
