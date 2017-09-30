package com.ctmp01.web.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用列变更记录表
 * </p>
 *
 * @author 
 * @since 2017-09-30
 */
@TableName("t_migu_case_record")
public class MiguCaseRecord extends Model<MiguCaseRecord> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 变更人
     */
	@TableField("change_person")
	private String changePerson;
    /**
     * 变更时间
     */
	@TableField("change_time")
	private String changeTime;
    /**
     * 变更内容
     */
	@TableField("change_content")
	private String changeContent;
    /**
     * 任务id
     */
	@TableField("task_id")
	private Integer taskId;
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChangePerson() {
		return changePerson;
	}

	public void setChangePerson(String changePerson) {
		this.changePerson = changePerson;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public String getChangeContent() {
		return changeContent;
	}

	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
