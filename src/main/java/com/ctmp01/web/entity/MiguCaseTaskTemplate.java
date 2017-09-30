package com.ctmp01.web.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用例任务模板表
 * </p>
 *
 * @author 
 * @since 2017-09-30
 */
@TableName("t_migu_case_task_template")
public class MiguCaseTaskTemplate extends Model<MiguCaseTaskTemplate> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 字段名
     */
	@TableField("field_name")
	private String fieldName;
    /**
     * 字段类型(1文本框，2下拉框，3时间控件)
     */
	@TableField("field_type")
	private Integer fieldType;
    /**
     * 字段内容
     */
	@TableField("field_content")
	private String fieldContent;
    /**
     * 优先展示(1不优先，2优先)
     */
	private Integer priority;
    /**
     * 是否必填（1否，2是）
     */
	private Integer required;
    /**
     * 类型（1用例，2任务）
     */
	private Integer type;
    /**
     * 测试类型id
     */
	@TableField("test_type_id")
	private Integer testTypeId;
    /**
     * 是否为记录行（0否，1是）
     */
	@TableField("is_rows")
	private Integer isRows;
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
     * 关联用列管理表
     */
	@TableField("case_manage_id")
	private Integer caseManageId;
    /**
     * 关联任务管理表
     */
	@TableField("task_manage_id")
	private Integer taskManageId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getFieldType() {
		return fieldType;
	}

	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldContent() {
		return fieldContent;
	}

	public void setFieldContent(String fieldContent) {
		this.fieldContent = fieldContent;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getRequired() {
		return required;
	}

	public void setRequired(Integer required) {
		this.required = required;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTestTypeId() {
		return testTypeId;
	}

	public void setTestTypeId(Integer testTypeId) {
		this.testTypeId = testTypeId;
	}

	public Integer getIsRows() {
		return isRows;
	}

	public void setIsRows(Integer isRows) {
		this.isRows = isRows;
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

	public Integer getCaseManageId() {
		return caseManageId;
	}

	public void setCaseManageId(Integer caseManageId) {
		this.caseManageId = caseManageId;
	}

	public Integer getTaskManageId() {
		return taskManageId;
	}

	public void setTaskManageId(Integer taskManageId) {
		this.taskManageId = taskManageId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
