
package com.ardublock.translator.block.shassis2wd;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Right extends BaseMove{
    public static final String SHASSIS2WD_RIGHT_HBRIDGE_DEFINE = 
            "void __right2WD()\n"+
            "{\n" +
            "  pinMode(%1$s, OUTPUT);\n" +
            "  digitalWrite(%1$s, LOW);\n" +
            "  pinMode(%2$s, OUTPUT);\n" +
            "  digitalWrite(%2$s, LOW);\n" +
            "  pinMode(%3$s, OUTPUT);\n" +
            "  digitalWrite(%3$s, HIGH);\n" +
            "  pinMode(%4$s, OUTPUT);\n" +
            "  digitalWrite(%4$s, LOW);\n" +
            "}\n";
    public static final String SHASSIS2WD_RIGHT_DEFINE = 
            "void __right2WD()\n"+
            "{\n" +
            "  pinMode(%1$s, OUTPUT);\n" +
            "  digitalWrite(%1$s, LOW);\n" +
            "  pinMode(%2$s, OUTPUT);\n" +
            "  digitalWrite(%2$s, LOW);\n" +
            "  pinMode(%3$s, OUTPUT);\n" +
            "  digitalWrite(%3$s, HIGH);\n" +
            "  pinMode(%4$s, OUTPUT);\n" +
            "  digitalWrite(%4$s, HIGH);\n" +
            "}\n";
    public Right(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
            if(hasHBridge()){
                    moveWithHBridge(SHASSIS2WD_RIGHT_HBRIDGE_DEFINE);
            }else{
                    moveWithSpeedAndDirection(SHASSIS2WD_RIGHT_DEFINE);
            }
            return "__right2WD();\n";
    }
    
}
