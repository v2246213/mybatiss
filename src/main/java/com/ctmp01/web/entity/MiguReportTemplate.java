package com.ctmp01.web.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 报告模板表
 * </p>
 *
 * @author 
 * @since 2017-09-29
 */
@TableName("t_migu_report_template")
public class MiguReportTemplate extends Model<MiguReportTemplate> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 邮件列表id
     */
	@TableField("list_id")
	private String listId;
    /**
     * 邮件历史
     */
	@TableField("email_history")
	private String emailHistory;
    /**
     * 开始时间
     */
	@TableField("begin_time")
	private String beginTime;
    /**
     * 结束时间
     */
	@TableField("end_time")
	private String endTime;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Integer userId;
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
	@TableField("t_migu_email_setting_id")
	private Integer tMiguEmailSettingId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public String getEmailHistory() {
		return emailHistory;
	}

	public void setEmailHistory(String emailHistory) {
		this.emailHistory = emailHistory;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
