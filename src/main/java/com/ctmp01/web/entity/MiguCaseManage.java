package com.ctmp01.web.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用例管理表
 * </p>
 *
 * @author 
 * @since 2017-09-29
 */
@TableName("t_migu_case_manage")
public class MiguCaseManage extends Model<MiguCaseManage> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用例名称
     */
	@TableField("case_name")
	private String caseName;
    /**
     * 用例编号
     */
	@TableField("case_id")
	private Integer caseId;
    /**
     * 用例描述
     */
	@TableField("case_description")
	private String caseDescription;
    /**
     * 用例级别
     */
	@TableField("case_level")
	private String caseLevel;
    /**
     * 用例类型
     */
	@TableField("case_type")
	private String caseType;
    /**
     * 用例版本
     */
	@TableField("case_version")
	private String caseVersion;
    /**
     * 用列所在目录
     */
	@TableField("case_dir")
	private Integer caseDir;
    /**
     * 测试数据
     */
	@TableField("test_data")
	private String testData;
    /**
     * 测试步骤
     */
	@TableField("test_step")
	private String testStep;
    /**
     * 测试说明

     */
	@TableField("test_explain")
	private String testExplain;
    /**
     * 测试环境
     */
	@TableField("test_environment")
	private String testEnvironment;
    /**
     * 测试结果(1未测试，2通过，3不通过，4阻塞)
     */
	@TableField("test_result")
	private Integer testResult;
    /**
     * 测试附件
     */
	@TableField("test_accessory")
	private String testAccessory;
    /**
     * 测试要求
     */
	@TableField("test_require")
	private String testRequire;
    /**
     * 预制条件
     */
	private String condition;
    /**
     * 预期结果
     */
	@TableField("expect_result")
	private String expectResult;
    /**
     * 创建人
     */
	@TableField("create_person")
	private String createPerson;
    /**
     * 所属人id
     */
	@TableField("belong_id")
	private Integer belongId;
    /**
     * 产品id
     */
	@TableField("product_id")
	private String productId;
    /**
     * 功能模块
     */
	private String function;
    /**
     * 修订人
     */
	@TableField("modify_person")
	private String modifyPerson;
    /**
     * 修订描述
     */
	@TableField("modify_description")
	private String modifyDescription;
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
	@TableField("t_migu_use_task_template_id")
	private Integer tMiguUseTaskTemplateId;
	@TableField("t_migu_task_manage_id")
	private Integer tMiguTaskManageId;
	@TableField("t_migu_task_manage_t_migu_use_task_template_id")
	private Integer tMiguTaskManageTMiguUseTaskTemplateId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getCaseDescription() {
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

	public String getCaseLevel() {
		return caseLevel;
	}

	public void setCaseLevel(String caseLevel) {
		this.caseLevel = caseLevel;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getCaseVersion() {
		return caseVersion;
	}

	public void setCaseVersion(String caseVersion) {
		this.caseVersion = caseVersion;
	}

	public Integer getCaseDir() {
		return caseDir;
	}

	public void setCaseDir(Integer caseDir) {
		this.caseDir = caseDir;
	}

	public String getTestData() {
		return testData;
	}

	public void setTestData(String testData) {
		this.testData = testData;
	}

	public String getTestStep() {
		return testStep;
	}

	public void setTestStep(String testStep) {
		this.testStep = testStep;
	}

	public String getTestExplain() {
		return testExplain;
	}

	public void setTestExplain(String testExplain) {
		this.testExplain = testExplain;
	}

	public String getTestEnvironment() {
		return testEnvironment;
	}

	public void setTestEnvironment(String testEnvironment) {
		this.testEnvironment = testEnvironment;
	}

	public Integer getTestResult() {
		return testResult;
	}

	public void setTestResult(Integer testResult) {
		this.testResult = testResult;
	}

	public String getTestAccessory() {
		return testAccessory;
	}

	public void setTestAccessory(String testAccessory) {
		this.testAccessory = testAccessory;
	}

	public String getTestRequire() {
		return testRequire;
	}

	public void setTestRequire(String testRequire) {
		this.testRequire = testRequire;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getExpectResult() {
		return expectResult;
	}

	public void setExpectResult(String expectResult) {
		this.expectResult = expectResult;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Integer getBelongId() {
		return belongId;
	}

	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public String getModifyDescription() {
		return modifyDescription;
	}

	public void setModifyDescription(String modifyDescription) {
		this.modifyDescription = modifyDescription;
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

	public Integer gettMiguUseTaskTemplateId() {
		return tMiguUseTaskTemplateId;
	}

	public void settMiguUseTaskTemplateId(Integer tMiguUseTaskTemplateId) {
		this.tMiguUseTaskTemplateId = tMiguUseTaskTemplateId;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
