
package com.ardublock.translator.block.shassis2wd;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

abstract public class BaseMove extends TranslatorBlock{
        protected boolean isHBridge = false;
        protected String M1A;
        protected String M2A;
        protected String M1B;
        protected String M2B;
        protected String M1Speed;
        protected String M2Speed;
        protected String M1Direction;
        protected String M2Direction;
        
        protected void getSlot(String slotName, String pin, boolean addOutputMode){
                String internalVariableName = translator.getNumberVariable(slotName);
                if (internalVariableName == null)
                {
                        internalVariableName = translator.buildVariableName(slotName);
                        translator.addNumberVariable(slotName, internalVariableName);
                        translator.addDefinitionCommand("int " + internalVariableName + " = " + pin + ";");
                        if(addOutputMode == true){
                            translator.addOutputPin(pin);
                        }
                }
        }
        protected void getSlot(String slotName, String pin){
            getSlot(slotName, pin, false);
        }
        protected boolean hasHBridge(){
            M1A = translator.getNumberVariable("M1A");
            M2A = translator.getNumberVariable("M2A");
            M1B = translator.getNumberVariable("M1B");
            M2B = translator.getNumberVariable("M2B");
            M1Speed = translator.getNumberVariable("M1Speed");
            M2Speed = translator.getNumberVariable("M2Speed");
            M1Direction = translator.getNumberVariable("M1Direction");
            M2Direction = translator.getNumberVariable("M2Direction");
            if( M1A != null && M1B != null && M2A != null && M2B != null ){
                    return true;
            }
            if( M1Speed != null && M2Speed != null && M1Direction != null && M2Direction != null )
            {
                   return false;
            }
            return true;
        }

        protected void moveWithHBridge(String template){
                translator.addDefinitionCommand(
                       String.format(template,
                               M1A, M1B, M2A, M2B
                       )
                );
        }
        protected void moveWithSpeedAndDirection(String template){
                translator.addDefinitionCommand(
                       String.format(template,
                                M1Speed, M1Direction, M2Speed, M2Direction
                       )
                );
        }

        public BaseMove(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
            super(blockId, translator, codePrefix, codeSuffix, label);
        }

        @Override
        abstract public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException;
    
}
