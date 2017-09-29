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
 * @since 2017-09-29
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
	@TableField("t_migu_task_manage_id")
	private Integer tMiguTaskManageId;
	@TableField("t_migu_task_manage_t_migu_use_task_template_id")
	private Integer tMiguTaskManageTMiguUseTaskTemplateId;
	@TableField("t_migu_case_manage_id")
	private Integer tMiguCaseManageId;
	@TableField("t_migu_case_manage_t_migu_use_task_template_id")
	private Integer tMiguCaseManageTMiguUseTaskTemplateId;
	@TableField("t_migu_case_task_template_id")
	private Integer tMiguCaseTaskTemplateId;
	@TableField("t_migu_email_setting_id")
	private Integer tMiguEmailSettingId;


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

	public Integer gettMiguTaskManageId() {
		return tMiguTaskManageId;
	}

	public void settMiguTaskManageId(Integer tMiguTaskManageId) {
		this.tMiguTaskManageId = tMiguTaskManageId;
	}

	public Integer gettMiguTaskManageTMiguUseTaskTemplateId() {
		return tMiguTaskManageTMiguUseTaskTemplateId;
	}

	public void settMiguTaskManageTMiguUseTaskTemplateId(Integer tMiguTaskManageTMiguUseTaskTemplateId) {
		this.tMiguTaskManageTMiguUseTaskTemplateId = tMiguTaskManageTMiguUseTaskTemplateId;
	}

	public Integer gettMiguCaseManageId() {
		return tMiguCaseManageId;
	}

	public void settMiguCaseManageId(Integer tMiguCaseManageId) {
		this.tMiguCaseManageId = tMiguCaseManageId;
	}

	public Integer gettMiguCaseManageTMiguUseTaskTemplateId() {
		return tMiguCaseManageTMiguUseTaskTemplateId;
	}

	public void settMiguCaseManageTMiguUseTaskTemplateId(Integer tMiguCaseManageTMiguUseTaskTemplateId) {
		this.tMiguCaseManageTMiguUseTaskTemplateId = tMiguCaseManageTMiguUseTaskTemplateId;
	}

	public Integer gettMiguCaseTaskTemplateId() {
		return tMiguCaseTaskTemplateId;
	}

	public void settMiguCaseTaskTemplateId(Integer tMiguCaseTaskTemplateId) {
		this.tMiguCaseTaskTemplateId = tMiguCaseTaskTemplateId;
	}

	public Integer gettMiguEmailSettingId() {
		return tMiguEmailSettingId;
	}

	public void settMiguEmailSettingId(Integer tMiguEmailSettingId) {
		this.tMiguEmailSettingId = tMiguEmailSettingId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
