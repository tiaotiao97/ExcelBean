package com.imzy.excel.exceptions;

import com.alibaba.fastjson.JSONObject;
import com.imzy.excel.enums.ErrorType;
import com.imzy.excel.error.ErrorBean;
import com.imzy.excel.support.ThreadLocalHelper;
import com.imzy.excel.util.StringUtils;

/**
 * excel总异常
 * @author yangzhang7
 *
 */
public class ExcelException extends RuntimeException {

	private ErrorBean errorBean;

	public ErrorBean getErrorBean() {
		return errorBean;
	}

	public void setErrorBean(ErrorBean errorBean) {
		this.errorBean = errorBean;
	}

	public ExcelException setValidateErrorBean(String lineNo, String columnNo, String errorReason, String value,
			String validateErrorReason) {
		return setErrorBean(lineNo, columnNo, ErrorType.VALIDATE, errorReason, value, validateErrorReason);
	}

	public ExcelException setConfigErrorBean(String errorReason) {
		return setErrorBean(null, null, ErrorType.CONFIG, errorReason, null, null);
	}

	public ExcelException setCommonErrorBean(String errorReason) {
		return setErrorBean(null, null, ErrorType.COMMON, errorReason, null, null);
	}

	public ExcelException setErrorBean(String lineNo, String columnNo, ErrorType errorType, String errorReason,
			String value, String validateErrorReason) {
		String excelName = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(ThreadLocalHelper.getCurrentWorkbookName())) {
			excelName = ThreadLocalHelper.getCurrentWorkbookName();
		}
		String sheetName = StringUtils.EMPTY;
		if (null != ThreadLocalHelper.getCurrentSheet()
				&& StringUtils.isNotBlank(ThreadLocalHelper.getCurrentSheet().getSheetName())) {
			sheetName = ThreadLocalHelper.getCurrentSheet().getSheetName();
		}
		return setErrorBean(excelName, sheetName, lineNo, columnNo, errorType, errorReason, value, validateErrorReason);
	}

	public ExcelException setErrorBean(String excelName, String sheetName, String lineNo, String columnNo,
			ErrorType errorType, String errorReason, String value, String validateErrorReason) {
		ErrorBean errorBean = new ErrorBean();
		errorBean.setExcelName(excelName);
		errorBean.setSheetName(sheetName);
		errorBean.setLineNo(lineNo);
		errorBean.setColumnNo(columnNo);
		errorBean.setErrorType(errorType);
		errorBean.setErrorReason(errorReason);
		errorBean.setValue(value);
		errorBean.setValidateErrorReason(validateErrorReason);
		this.errorBean = errorBean;
		return this;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1840934084229497072L;

	public ExcelException() {
		super();
	}

	public ExcelException(String message, ExcelException cause) {
		super(message, cause);
		setErrorBean(cause.getErrorBean());
	}

	public ExcelException(String message) {
		super(message);
	}

	public ExcelException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(errorBean);
	}

}
