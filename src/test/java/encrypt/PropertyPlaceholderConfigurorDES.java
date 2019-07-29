package encrypt;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyPlaceholderConfigurorDES extends PropertyPlaceholderConfigurer {

	//已被加密的字段们
	private String[] encryptPropName = {"username","password"};

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if(isEncryptProp(propertyName)) {
			//解密:被加密的字段们
			String decryptValue=DESUtil.getDecryptString(propertyValue);
			return decryptValue;
		}
		return propertyValue;
	}
	
	protected boolean isEncryptProp(String propertyName) {
		for(String encryptpropertyName:encryptPropName) {
			if(encryptpropertyName.equals(propertyName))
				return true;
		}
		return false;
	}
}
