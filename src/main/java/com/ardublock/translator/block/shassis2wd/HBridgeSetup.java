
package com.ardublock.translator.block.shassis2wd;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
import java.util.ResourceBundle;

/**
 * This is a 2WD shassis with a two DC motors controlled by L298N or L298P shield.
 * In the setup, the contact numbers for the lines A and B 
 * of the right and left motor are determined.
 * Motor 1 is for move to left.
 * Motor 2 is for move to right.
 * Slot1 is the line A for the motor 1.
 * Slot2 is the line B for the motor 1.
 * Slot3 is the line A for the motor 2.
 * Slot4 is the line B for the motor 2.
 * @author vedes
 */
public class HBridgeSetup extends BaseMove{
    
    private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
       
    public HBridgeSetup(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String ret = "";
        TranslatorBlock motor1A = this.getRequiredTranslatorBlockAtSocket(0);
        TranslatorBlock motor1B = this.getRequiredTranslatorBlockAtSocket(1);
        TranslatorBlock motor2A = this.getRequiredTranslatorBlockAtSocket(2);
        TranslatorBlock motor2B = this.getRequiredTranslatorBlockAtSocket(3);
       
//        String isHBridgeCode = translator.getBooleanVariable("isHBridge");
//        if(isHBridgeCode == null){
//            isHBridgeCode = translator.buildVariableName("isHBridge");
//            translator.addBooleanVariable("isHBridge", isHBridgeCode);
//            translator.addSetupCommand("bool " + isHBridgeCode + " = true;");
//        }
        String motor1APin = motor1A.toCode();
        //****** Bit long w but easy to see what's happening. Any other invalid pins? *********
        if ( ! ("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32/"
                        + " 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53").contains(motor1APin.trim()) )
        {
                throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.Digital_pin_slot"));
        }
        String motor2APin = motor2A.toCode();
        //****** Bit long w but easy to see what's happening. Any other invalid pins? *********
        if ( ! ("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32/"
                        + " 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53").contains(motor2APin.trim()) )
        {
                throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.Digital_pin_slot"));
        }
        String motor1BPin = motor1B.toCode();
        //****** Bit long w but easy to see what's happening. Any other invalid pins? *********
        if ( ! ("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32/"
                        + " 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53").contains(motor1BPin.trim()) )
        {
                throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.Digital_pin_slot"));
        }
        String motor2BPin = motor2B.toCode();
        //****** Bit long w but easy to see what's happening. Any other invalid pins? *********
        if ( ! ("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32/"
                        + " 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53").contains(motor2BPin.trim()) )
        {
                throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.Digital_pin_slot"));
        }

        getSlot("M1A", motor1APin);
        getSlot("M1B", motor1BPin);
        getSlot("M2A", motor2APin);
        getSlot("M2B", motor2BPin);
        return ret;
    }
    
}
