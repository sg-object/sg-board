package com.sg.assignment.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sg.assignment.board.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

	public Page<Board> findAllByDeleteYn(boolean deleteYn, Pageable pageable);

	public Page<Board> findAllByDeleteYnAndTitleContaining(boolean deleteYn, String title, Pageable pageable);

	public Page<Board> findAllByDeleteYnAndUserIdContaining(boolean deleteYn, String userId, Pageable pageable);
}
