package indi.jackson;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PassCardPrintThreeDTO {

	//通行证组号
	private Integer itemNo;
	//申请主表id
	private Long passApplyId;
	//申请单位名称
	private String applyUnitName;
	//创建时间
	private Date createTime;
	//申请有效期开始时间
	private Date startTime;
	//申请有效期结束时间
	private Date endTime;
}
