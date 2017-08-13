/**
 * 
 */
package com.wf.smapp.memory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author kht
 *
 */
public class InMemory {
	
	private static InMemory instance;
	
	private static final Logger logger = LoggerFactory.getLogger(InMemory.class);
	
	//favorite , favorite aread
	public Map<String,JSONObject> favRestrant = Collections.synchronizedMap(new HashMap<String, JSONObject>());
	
	public List<String> conversion = Collections.synchronizedList(new ArrayList<String>());
	
    static synchronized public InMemory getInstance() {
        if (instance == null) {
            instance = new InMemory();
        }
        return instance;
    }

	
}
