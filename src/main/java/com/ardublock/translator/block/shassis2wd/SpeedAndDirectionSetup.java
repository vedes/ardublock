
package com.ardublock.translator.block.shassis2wd;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
import java.util.ResourceBundle;

/**
 * This is a 2WD shassis with a two DC motors controlled by L298N or L298P shield.
 * In the setup, the contact numbers for the lines for speed and direction 
 * of the right and left motor are determined.
 * Motor 1 is for move to left.
 * Motor 2 is for move to right.
 * Slot1 is the speed for the motor 1.
 * Slot2 is the direction for the motor 1.
 * Slot3 is the speed for the motor 2.
 * Slot4 is the direction for the motor 2.
 * @author vedes
 */
public class SpeedAndDirectionSetup extends BaseMove{
    private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");

    public SpeedAndDirectionSetup(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }   

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String ret = "";
        TranslatorBlock motor1Speed = this.getRequiredTranslatorBlockAtSocket(0);
        TranslatorBlock motor1Direction = this.getRequiredTranslatorBlockAtSocket(1);
        TranslatorBlock motor2Speed = this.getRequiredTranslatorBlockAtSocket(2);
        TranslatorBlock motor2Direction = this.getRequiredTranslatorBlockAtSocket(3);
       
//        String isHBridgeCode = translator.getBooleanVariable("isHBridge");
//        if(isHBridgeCode == null){
//            isHBridgeCode = translator.buildVariableName("isHBridge");
//            translator.addBooleanVariable("isHBridge", isHBridgeCode);
//            translator.addSetupCommand("bool " + isHBridgeCode + " = false;");
//        }
        String motor1SpeedPin = motor1Speed.toCode();
        //****** Bit long w but easy to see what's happening. Any other invalid pins? *********
        if ( ! ("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32/"
                        + " 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53").contains(motor1SpeedPin.trim()) )
        {
                throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.Digital_pin_slot"));
        }
        String motor1DirectionPin = motor1Direction.toCode();
        //****** Bit long w but easy to see what's happening. Any other invalid pins? *********
        if ( ! ("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32/"
                        + " 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53").contains(motor1DirectionPin.trim()) )
        {
                throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.Digital_pin_slot"));
        }
        String motor2SpeedPin = motor2Speed.toCode();
        //****** Bit long w but easy to see what's happening. Any other invalid pins? *********
        if ( ! ("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32/"
                        + " 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53").contains(motor2SpeedPin.trim()) )
        {
                throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.Digital_pin_slot"));
        }
        String motor2DirectionPin = motor2Direction.toCode();
        //****** Bit long w but easy to see what's happening. Any other invalid pins? *********
        if ( ! ("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32/"
                        + " 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53").contains(motor2DirectionPin.trim()) )
        {
                throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.Digital_pin_slot"));
        }

        getSlot("M1Speed", motor1SpeedPin, false);
        getSlot("M2Speed", motor2SpeedPin, false);
        getSlot("M1Direction", motor1DirectionPin, true);
        getSlot("M2Direction", motor2DirectionPin, true);
        return ret;
    }
}
