package com.ctmp01.web.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 任务管理表
 * </p>
 *
 * @author 
 * @since 2017-09-30
 */
@TableName("t_migu_task_manage")
public class MiguTaskManage extends Model<MiguTaskManage> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 任务名称
     */
	@TableField("task_name")
	private String taskName;
    /**
     * 优先级
     */
	private Integer priority;
    /**
     * 任务状态（1未执行，2执行中，3已完成，4已取消，5已终止）
     */
	@TableField("task_status")
	private Integer taskStatus;
    /**
     * 完成时间
     */
	@TableField("finish_time")
	private String finishTime;
    /**
     * 任务要求
     */
	@TableField("task_require")
	private String taskRequire;
    /**
     * 测试说明
     */
	@TableField("test_explain")
	private String testExplain;
    /**
     * 测试步骤
     */
	@TableField("test_step")
	private String testStep;
    /**
     * 用例描述
     */
	@TableField("case_description")
	private String caseDescription;
    /**
     * 最后更新人
     */
	@TableField("update_person")
	private String updatePerson;
    /**
     * 项目id
     */
	@TableField("project_id")
	private Integer projectId;
    /**
     * 任务邮件通知
     */
	@TableField("email_notice")
	private String emailNotice;
    /**
     * 任务执行人
     */
	@TableField("execute_person")
	private Integer executePerson;
    /**
     * 创建人
     */
	@TableField("create_person")
	private String createPerson;
    /**
     * 用列id
     */
	@TableField("case_id")
	private Integer caseId;
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

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getTaskRequire() {
		return taskRequire;
	}

	public void setTaskRequire(String taskRequire) {
		this.taskRequire = taskRequire;
	}

	public String getTestExplain() {
		return testExplain;
	}

	public void setTestExplain(String testExplain) {
		this.testExplain = testExplain;
	}

	public String getTestStep() {
		return testStep;
	}

	public void setTestStep(String testStep) {
		this.testStep = testStep;
	}

	public String getCaseDescription() {
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getEmailNotice() {
		return emailNotice;
	}

	public void setEmailNotice(String emailNotice) {
		this.emailNotice = emailNotice;
	}

	public Integer getExecutePerson() {
		return executePerson;
	}

	public void setExecutePerson(Integer executePerson) {
		this.executePerson = executePerson;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
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
