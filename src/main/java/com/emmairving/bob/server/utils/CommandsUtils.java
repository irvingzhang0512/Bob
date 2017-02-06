package com.emmairving.bob.server.utils;

import com.emmairving.bob.server.common.ConstantValueForServer;
import com.emmairving.bob.server.model.RawLocalData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by irving on 17/2/4.
 */
public class CommandsUtils {
    private static final Logger logger = LogManager.getLogger(CommandsUtils.class.getName());

    public static boolean isLegalRawLocalDataCommands(String commands) {
        String[] words = commands.split(" ");
        if( words.length != 4 ) {
            logger.error("Bad commands: "+commands + ". length = "+ words.length);
            return false;
        }
        if( !"SEND".equals(words[0]) ) {
            logger.error("Bad commands: "+commands+" . First string is "+ words[0] );
            return false;
        }

        Double d = null;
        Integer index = null;

        try {
            d = Double.parseDouble(words[3]);
            index = ConstantValueForServer.RAW_DATA_INDEX_MAP.get(words[2]);
        } catch(Exception e) {
            logger.error("Bad commands: "+commands);
            return false;
        }


        return true;
    }


}
