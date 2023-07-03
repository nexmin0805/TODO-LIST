package TODOLIST.atom.controller;

import TODOLIST.atom.dto.board.BoardResponseDto;
import TODOLIST.atom.dto.comment.CommentEditRequestDto;
import TODOLIST.atom.dto.comment.CommentRequestDto;
import TODOLIST.atom.dto.comment.CommentResponseDto;
import TODOLIST.atom.dto.member.MemberResponseDto;
import TODOLIST.atom.response.Response;
import TODOLIST.atom.service.BoardService;
import TODOLIST.atom.service.CommentService;
import TODOLIST.atom.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final MemberService memberService;

    @GetMapping("/{boardId}/comment")
    public Response getCommentInBoard(@PathVariable Long boardId) {
        List<CommentResponseDto> commentAllResponseDto = commentService.findAll(boardId);
        return Response.success(commentAllResponseDto);
    }
    @PostMapping("/{boardId}/comment")
    public Response commentPost(@PathVariable Long boardId, @Valid @RequestBody CommentRequestDto commentRequestDto,
                                @RequestParam("name") String nickName) {
        BoardResponseDto boardResponseDto = boardService.findById(boardId);
        MemberResponseDto memberResponseDto = memberService.findByNickname(nickName);
        CommentResponseDto commentResponseDto = commentService.save(commentRequestDto, boardId, nickName);
        return Response.success(commentResponseDto);
    }
    @PatchMapping("/comment/{commentId}")
    public Response commentEdit(@PathVariable Long commentId, @Valid @RequestBody CommentEditRequestDto commentEditRequestDto) {
        CommentResponseDto commentResponseDto = commentService.update(commentId, commentEditRequestDto);
        return Response.success(commentResponseDto);
    }

    @DeleteMapping("/comment/{commentId}")
    public Response commentDelete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return Response.success("댓글 삭제 완료");
    }
}

