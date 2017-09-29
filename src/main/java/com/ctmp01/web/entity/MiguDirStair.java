package com.ctmp01.web.entity;

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
 * @since 2017-09-29
 */
@TableName("t_migu_dir_stair")
public class MiguDirStair extends Model<MiguDirStair> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 目录名
     */
	@TableField("dir_name")
	private String dirName;
    /**
     * 父类id
     */
	@TableField("parent_id")
	private Integer parentId;
    /**
     * 子节点id
     */
	@TableField("child_id")
	private Integer childId;
    /**
     * 插入时间
     */
	@TableField("insert_date")
	private String insertDate;
    /**
     * 更新时间
     */
	@TableField("update_date")
	private String updateDate;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getChildId() {
		return childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
