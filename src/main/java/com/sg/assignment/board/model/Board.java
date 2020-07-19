package com.sg.assignment.board.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import com.sg.assignment.common.model.AbstractModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "BOARD")
@EqualsAndHashCode(callSuper = false)
public class Board extends AbstractModel {

	@Id
	private Long id;

	private String title;

	private String content;
}
