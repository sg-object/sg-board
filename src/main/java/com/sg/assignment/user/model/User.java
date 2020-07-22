package com.sg.assignment.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.DynamicUpdate;
import com.sg.assignment.common.exception.VerificationException;
import com.sg.assignment.common.model.AbstractModel;
import com.sg.assignment.common.util.VerificationUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

@DynamicUpdate
@Data
@Entity(name = "USER")
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractModel {

	@Id
	@Column(columnDefinition = "varchar(30)")
	private String id;

	@Column(nullable = false, columnDefinition = "varchar(100)")
	private String password;

	@Column(nullable = false, columnDefinition = "varchar(10)")
	private String name;

	public void checkValue() {
		boolean check = VerificationUtils.isNullOrBlank(this.id) || VerificationUtils.isNullOrBlank(this.password)
				|| VerificationUtils.isNullOrBlank(this.name);
		if (check) {
			throw new VerificationException();
		}
	}
}
