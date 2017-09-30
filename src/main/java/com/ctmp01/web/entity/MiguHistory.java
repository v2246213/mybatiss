package com.ctmp01.web.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 历史记录表
 * </p>
 *
 * @author 
 * @since 2017-09-30
 */
@TableName("t_migu_history")
public class MiguHistory extends Model<MiguHistory> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 报道模板id
     */
	@TableField("report_id")
	private Integer reportId;
    /**
     * 记录内容
     */
	@TableField("record_content")
	private String recordContent;
    /**
     * 插入时间
     */
	@TableField("insert_time")
	private String insertTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private String updateTime;
    /**
     * 关联报告模板表
     */
	@TableField("report_template_id")
	private Integer reportTemplateId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getRecordContent() {
		return recordContent;
	}

	public void setRecordContent(String recordContent) {
		this.recordContent = recordContent;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getReportTemplateId() {
		return reportTemplateId;
	}

	public void setReportTemplateId(Integer reportTemplateId) {
		this.reportTemplateId = reportTemplateId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
