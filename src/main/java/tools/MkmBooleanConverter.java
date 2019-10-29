package tools;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

import model.MkmBoolean;

public class MkmBooleanConverter extends AbstractSingleValueConverter {

	  public boolean canConvert(Class type) {    
	    return type.equals(MkmBoolean.class);    
	  }

	  public Object fromString(String str) {
	    return new MkmBoolean(str);
	  }
	}