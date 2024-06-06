package com.papook.usergod.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an exception entity with a code and a message.
 * Helps the client understand what went wrong.
 * 
 * @author papook
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionEntity {
    private int code;
    private String message;
}
