package com.imzy.excel.processer.mapping;

import com.imzy.excel.exceptions.ExcelException;
import com.imzy.excel.processer.MappingProcessor;

/**
 * 映射器工厂
 * @author yangzhang7
 *
 */
public class MappingProcessorFactory {

	/**
	 * 构建映射器
	 * @param mappingProcessorClass
	 * @return
	 */
	public static MappingProcessor buildMappingProcessor(Class<? extends MappingProcessor> mappingProcessorClass) {
		MappingProcessor mappingProcessor = null;

		try {
			if (null != mappingProcessorClass) {
				mappingProcessor = mappingProcessorClass.newInstance();
			}
		} catch (InstantiationException e) {
			throw new ExcelException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new ExcelException(e.getMessage(), e);
		}

		return mappingProcessor;
	}
}