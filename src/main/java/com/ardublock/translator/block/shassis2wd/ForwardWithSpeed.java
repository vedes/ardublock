package com.ardublock.translator.block.shassis2wd;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;


public class ForwardWithSpeed extends BaseMove{
    public static final String SHASSIS2WD_FORWARD_HBRIDGE_SPEED_DEFINE = 
            "void __forward2WD(int speed)\n"+
            "{\n" +
            "  analogWrite(%1$s, speed);\n" +
            "  pinMode(%2$s, OUTPUT);\n" +
            "  digitalWrite(%2$s, LOW);\n" +
            "  analogWrite(%3$s, speed);\n" +
            "  pinMode(%4$s, OUTPUT);\n" +
            "  digitalWrite(%4$s, LOW);\n" +
            "}\n";
    public static final String SHASSIS2WD_FORWARD_SPEED_DEFINE = 
            "void __forward2WD(int speed)\n"+
            "{\n" +
            "  analogWrite(%1$s, speed);\n" +
            "  pinMode(%2$s, OUTPUT);\n" +
            "  digitalWrite(%2$s, HIGH);\n" +
            "  analogWrite(%3$s, speed);\n" +
            "  pinMode(%4$s, OUTPUT);\n" +
            "  digitalWrite(%4$s, HIGH);\n" +
            "}\n";

    public ForwardWithSpeed(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
	String speed = translatorBlock.toCode();
        if(hasHBridge()){
                moveWithHBridge(SHASSIS2WD_FORWARD_HBRIDGE_SPEED_DEFINE);
        } else {
                moveWithSpeedAndDirection(SHASSIS2WD_FORWARD_SPEED_DEFINE);
        }
        return String.format("__forward2WD(%s);\n", speed);
    }
    
}
