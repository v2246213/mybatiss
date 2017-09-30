package com.ctmp01.web.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2017-09-30
 */
@TableName("t_migu_group_people")
public class MiguGroupPeople extends Model<MiguGroupPeople> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 分组名称
     */
	@TableField("group_name")
	private String groupName;
    /**
     * 成员id
     */
	@TableField("user_id")
	private Integer userId;
    /**
     * 是否是组长（1普通成员，2组长）
     */
	@TableField("is_leader")
	private Integer isLeader;
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
     * 关联任务管理表
     */
	@TableField("case_manage_id")
	private Integer caseManageId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
