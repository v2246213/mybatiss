package com.ctmp01.web.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 邮件设置表
 * </p>
 *
 * @author 
 * @since 2017-09-30
 */
@TableName("t_migu_email_setting")
public class MiguEmailSetting extends Model<MiguEmailSetting> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 收件人
     */
	private String receiver;
    /**
     * 抄送人
     */
	private String copyer;
    /**
     * 主题
     */
	private String theme;
    /**
     * 列表名称
     */
	@TableField("list_name")
	private String listName;
    /**
     * 测试类型id
     */
	@TableField("test_type_id")
	private Integer testTypeId;
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

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getCopyer() {
		return copyer;
	}

	public void setCopyer(String copyer) {
		this.copyer = copyer;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public Integer getTestTypeId() {
		return testTypeId;
	}

	public void setTestTypeId(Integer testTypeId) {
		this.testTypeId = testTypeId;
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
