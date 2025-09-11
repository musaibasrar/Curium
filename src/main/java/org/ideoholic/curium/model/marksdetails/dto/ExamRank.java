package org.ideoholic.curium.model.marksdetails.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.student.dto.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "examrank")
public class ExamRank implements java.io.Serializable, Comparable<ExamRank> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "marksobtained", length = 12)
	private Float marksobtained;

	@Column(name = "status", length = 20)
	private String status;

	@Column(name = "rank")
	private Integer rank;

	@Column(name = "academicyear", length = 20)
	private String academicyear;

	@ManyToOne
	@JoinColumn(name = "examid", referencedColumnName = "exid")
	private Exams exams;

	@ManyToOne
	@JoinColumn(name = "sid", referencedColumnName = "sid")
	private Student student;

	@Column(name = "branchid")
	private Integer branchid;

	@Column(name = "userid")
	private Integer userid;

	@Override
	public int compareTo(ExamRank examRank) {
		float marksObtained = ((ExamRank) examRank).getMarksobtained();
		return Double.compare(marksObtained, this.marksobtained);
	}
	
	public int fetchExamid() {
		if (exams != null) {
			exams.getExid();
		}
		return 0;
	}

	public int fetchSid() {
		if (student != null) {
			return student.getSid();
		}
		return 0;
	}

}
