package com.udaykale.spreadsheet.annotation;

import com.udaykale.spreadsheet.extension.CellDeserializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author uday
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Cell {

    int position();

    Class<? extends CellDeserializer> deserializer()
            default CellDeserializer.None.class;
}
