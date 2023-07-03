package TODOLIST.atom.service;

import TODOLIST.atom.domain.Board;
import TODOLIST.atom.domain.Comment;
import TODOLIST.atom.domain.Member;
import TODOLIST.atom.dto.comment.CommentEditRequestDto;
import TODOLIST.atom.dto.comment.CommentRequestDto;
import TODOLIST.atom.dto.comment.CommentResponseDto;
import TODOLIST.atom.exception.BoardNotFoundException;
import TODOLIST.atom.exception.CommentNotFoundException;
import TODOLIST.atom.exception.MemberNotFoundException;
import TODOLIST.atom.exception.WriteFailureException;
import TODOLIST.atom.repository.BoardRepository;
import TODOLIST.atom.repository.CommentRepository;
import TODOLIST.atom.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public CommentResponseDto save(@RequestBody CommentRequestDto commentRequestDto, Long boardId, String nickName) {

        Member member = memberRepository.findByNickname(nickName).orElseThrow(MemberNotFoundException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        commentRequestDto.setBoard(board);
        commentRequestDto.setMember(member);

        Comment comment = commentRepository.save(Comment.toEntity(commentRequestDto));
        return CommentResponseDto.toDto(comment);
    }
    public CommentResponseDto update(Long id, CommentEditRequestDto commentEditRequestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        comment.update(commentEditRequestDto.getComment());
        return CommentResponseDto.toDto(comment);
    }
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        List<Comment> comments = board.getComments();
        return comments.stream().map(c -> CommentResponseDto.toDto(c)).collect(Collectors.toList());
    }

}

