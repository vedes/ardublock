
package com.ardublock.translator.block.shassis2wd;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LeftWithSpeed extends BaseMove{
    // <boost> - it is the accelerate of the turn from main way.
    // <boost> = 255: the right motor speed = <speed> and the left motor speed = 0
    // <boost> = 0: the right motor speed = <speed> and the left motor speed = <speed>
    public static final String SHASSIS2WD_LEFT_HBRIDGE_SPEED_DEFINE = 
            "void __left2WD(int speed, int boost)\n"+
            "{\n" +
            "  int l_speed = int(speed*(1 - boost/255));\n"+
            "  int r_speed = speed;\n"+
            "  analogWrite(%1$s, r_speed);\n" +
            "  pinMode(%2$s, OUTPUT);\n" +
            "  digitalWrite(%2$s, LOW);\n" +
            "  if(l_speed == 0){\n" +
            "    pinMode(%3$s, OUTPUT);\n" +
            "    digitalWrite(%3$s, LOW);\n" +
            "  }else{\n" +
            "    analogWrite(%3$s, l_speed);\n" +
            "  }\n" +
            "  pinMode(%4$s, OUTPUT);\n" +
            "  digitalWrite(%4$s, LOW);\n" +          
            "}\n";
    public static final String SHASSIS2WD_LEFT_SPEED_DEFINE = 
            "void __left2WD(int speed, int boost)\n"+
            "{\n" +
            "  int l_speed = int(speed*(1 - boost/255));\n"+
            "  int r_speed = speed;\n"+
            "  analogWrite(%1$s, r_speed);\n" +
            "  pinMode(%2$s, OUTPUT);\n" +
            "  digitalWrite(%2$s, HIGH);\n" +
            "  if(l_speed == 0){\n" +
            "    pinMode(%3$s, OUTPUT);\n" +
            "    digitalWrite(%3$s, LOW);\n" +
            "  }else{\n" +
            "    analogWrite(%3$s, l_speed);\n" +
            "  }\n" +
            "  pinMode(%4$s, OUTPUT);\n" +
            "  digitalWrite(%4$s, LOW);\n" +
            "}\n";
    public LeftWithSpeed(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
	String speed = translatorBlock.toCode();
        if(hasHBridge()){
                moveWithHBridge(SHASSIS2WD_LEFT_HBRIDGE_SPEED_DEFINE);
        } else {
                moveWithSpeedAndDirection(SHASSIS2WD_LEFT_SPEED_DEFINE);
        }
        return String.format("__left2WD(%s);\n", speed);

    }
    
}

//boost = 255 => l_speed = 0
//boost = 0 => l_speed = speed -1
//
//rr_speed*boost/255