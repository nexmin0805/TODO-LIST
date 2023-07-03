package TODOLIST.atom;

import TODOLIST.atom.domain.Board;
import TODOLIST.atom.domain.Comment;
import TODOLIST.atom.domain.Member;
import TODOLIST.atom.repository.BoardRepository;
import TODOLIST.atom.repository.CommentRepository;
import TODOLIST.atom.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @EventListener(value = ApplicationReadyEvent.class)
    @Transactional
    public void initDB() {
        init();
    }

    @Transactional
    public void init() {
        Member member1 = memberRepository.save(new Member("user1", "1q2w3e4r!", "한민서", "gksalstj@naver.com"));
        Member member2 = memberRepository.save(new Member("user2", "1q2w3e4r!", "조승호", "whtmdgh@naver.com"));

        Board board1 = boardRepository.save(new Board("제목1", "내용~~", member1));
        Board board2 = boardRepository.save(new Board("제목2", "내용~~", member2));

        Comment comment1 = commentRepository.save(new Comment("댓글1", board1, member2));
        Comment comment2 = commentRepository.save(new Comment("댓글2", board2, member1));
    }
}
