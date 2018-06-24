
package com.ardublock.translator.block.shassis2wd;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Left extends BaseMove{
    public static final String SHASSIS2WD_LEFT_HBRIDGE_DEFINE = 
            "void __left2WD()\n"+
            "{\n" +
            "  pinMode(%1$s, OUTPUT);\n" +
            "  digitalWrite(%1$s, HIGH);\n" +
            "  pinMode(%2$s, OUTPUT);\n" +
            "  digitalWrite(%2$s, LOW);\n" +
            "  pinMode(%3$s, OUTPUT);\n" +
            "  digitalWrite(%3$s, LOW);\n" +
            "  pinMode(%4$s, OUTPUT);\n" +
            "  digitalWrite(%4$s, LOW);\n" +
            "}\n";
    public static final String SHASSIS2WD_LEFT_DEFINE = 
            "void __left2WD()\n"+
            "{\n" +
            "  pinMode(%1$s, OUTPUT);\n" +
            "  digitalWrite(%1$s, HIGH);\n" +
            "  pinMode(%2$s, OUTPUT);\n" +
            "  digitalWrite(%2$s, HIGH);\n" +
            "  pinMode(%3$s, OUTPUT);\n" +
            "  digitalWrite(%3$s, LOW);\n" +
            "  pinMode(%4$s, OUTPUT);\n" +
            "  digitalWrite(%4$s, LOW);\n" +
            "}\n";
    public Left(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
            if(hasHBridge()){
                    moveWithHBridge(SHASSIS2WD_LEFT_HBRIDGE_DEFINE);
            }else{
                    moveWithSpeedAndDirection(SHASSIS2WD_LEFT_DEFINE);
            }
            return "__left2WD();\n";
    }
    
}
