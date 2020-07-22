package com.sg.assignment.board.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicUpdate;
import com.sg.assignment.common.converter.BooleanConverter;
import com.sg.assignment.common.exception.VerificationException;
import com.sg.assignment.common.model.AbstractModel;
import com.sg.assignment.common.util.VerificationUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@DynamicUpdate
@Data
@Entity(name = "BOARD")
@EqualsAndHashCode(callSuper = false)
public class Board extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "varchar(50)")
	private String title;

	@Column(nullable = false, columnDefinition = "clob")
	private String content;

	@Convert(converter = BooleanConverter.class)
	@Column(nullable = false, columnDefinition = "char(1)")
	private boolean deleteYn;

	@Column(nullable = false)
	private String userId;

	@ApiModelProperty(hidden = true)
	@Transient
	private String recaptchaResponse;

	@PrePersist
	private void preInset() {
		this.deleteYn = false;
	}

	public void checkValue() {
		boolean check = VerificationUtils.isNullOrBlank(this.title) || VerificationUtils.isNullOrBlank(this.content)
				|| VerificationUtils.isNullOrBlank(this.userId);
		if (check) {
			throw new VerificationException();
		}
	}
}
