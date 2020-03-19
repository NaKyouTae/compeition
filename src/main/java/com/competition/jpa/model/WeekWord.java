package com.competition.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.competition.dto.weekword.WeekWordDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@SqlResultSetMapping(
        name="WeekWordMapping",
        classes = @ConstructorResult(
                targetClass = WeekWordDto.class,
                columns = {
                        @ColumnResult(name="idx", type = String.class),
                    	@ColumnResult(name="word_group", type = String.class),
                    	@ColumnResult(name="word", type = String.class),
                    	@ColumnResult(name="insert_date", type = String.class),
                    	@ColumnResult(name="start_date", type = String.class),
                    	@ColumnResult(name="end_date", type = String.class)
                })
)

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "WEEKWORD")
public class WeekWord implements Serializable {

	private static final long serialVersionUID = -1139838270144095544L;

	@Id
	@Column
	private String idx;
	
	@Column
	private String word_group;
	
	@Column
	private String word;
	
	@Column
	private String insert_date;
	@Column
	private String start_date;
	@Column
	private String end_date;

}
