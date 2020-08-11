package com.ksf.beans.xml;

import com.ksf.beans.factory.config.BeanDefinition;
import com.ksf.beans.io.ResourceLoader;
import com.ksf.beans.factory.support.XmlBeanDefinitionReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class XmlBeanDefinitionReaderTest {

	@Test
	public void test() throws Exception {
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("spring_config.xml");
		Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
		Assert.assertTrue(registry.size() > 0);
	}
}
