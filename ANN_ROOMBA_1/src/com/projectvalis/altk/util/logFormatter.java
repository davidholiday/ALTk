package com.projectvalis.altk.util;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class logFormatter extends Formatter {
	
    @Override
    public String format(LogRecord record) {
        return record.getThreadID()+"::"+record.getSourceClassName()+"::"
                +record.getSourceMethodName()+"::"
                +new Date(record.getMillis())+"::"
                +record.getMessage()+"\n";
    }
	
}

