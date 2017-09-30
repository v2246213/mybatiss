package com.ctmp01.web.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 测试类型表
 * </p>
 *
 * @author 
 * @since 2017-09-30
 */
@TableName("t_migu_test_type")
public class MiguTestType extends Model<MiguTestType> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 类型名称
     */
	@TableField("type_name")
	private String typeName;
    /**
     * 说明
     */
	private String description;
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
     * 关联任务管理表
     */
	@TableField("task_manage_id")
	private Integer taskManageId;
    /**
     * 关联用列管理表
     */
	@TableField("case_manage_id")
	private Integer caseManageId;
    /**
     * 关联用列任务模板表
     */
	@TableField("case_task_template_id")
	private Integer caseTaskTemplateId;
    /**
     * 关联邮件设置表
     */
	@TableField("email_setting_id")
	private Integer emailSettingId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getTaskManageId() {
		return taskManageId;
	}

	public void setTaskManageId(Integer taskManageId) {
		this.taskManageId = taskManageId;
	}

	public Integer getCaseManageId() {
		return caseManageId;
	}

	public void setCaseManageId(Integer caseManageId) {
		this.caseManageId = caseManageId;
	}

	public Integer getCaseTaskTemplateId() {
		return caseTaskTemplateId;
	}

	public void setCaseTaskTemplateId(Integer caseTaskTemplateId) {
		this.caseTaskTemplateId = caseTaskTemplateId;
	}

	public Integer getEmailSettingId() {
		return emailSettingId;
	}

	public void setEmailSettingId(Integer emailSettingId) {
		this.emailSettingId = emailSettingId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
