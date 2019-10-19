package com.xuelin.coke;

import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

public class FsmTest {


    public static void main(String[] args) {
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(MyStateMachine.class);

        builder.externalTransition()
                .from(VoiceprintRegisterState.NONE)
                .to(VoiceprintRegisterState.INIT)
                .on(VoiceprintRegisterEvent.APP_START)
                .callMethod("beginRegister");

        builder.externalTransition()
                .from(VoiceprintRegisterState.INIT)
                .to(VoiceprintRegisterState.STEP1)
                .on(VoiceprintRegisterEvent.SPEAKER_READY)
                .callMethod("step1");

        UntypedStateMachine fsm = builder.newStateMachine(VoiceprintRegisterState.NONE);
        fsm.fire(VoiceprintRegisterEvent.APP_START, "1");
        fsm.fire(VoiceprintRegisterEvent.SPEAKER_READY, "1");
    }

    enum VoiceprintRegisterState {
        NONE, INIT, STEP1, STEP2, STEP3, STEP4, STEP5, FIN_SUCC, FIN_FAIL;
    }

    enum VoiceprintRegisterEvent {
        APP_START, SPEAKER_READY, NEXT, ABORT;
    }

    @StateMachineParameters(stateType=VoiceprintRegisterState.class, eventType=VoiceprintRegisterEvent.class, contextType=String.class)
    static class MyStateMachine extends AbstractUntypedStateMachine {

        protected void beginRegister(VoiceprintRegisterState from, VoiceprintRegisterState to,
                                     VoiceprintRegisterEvent event, String context) {
            System.out.println("向音箱发送推送，启动声纹注册流程");
        }

        protected void step1(VoiceprintRegisterState from, VoiceprintRegisterState to,
                                     VoiceprintRegisterEvent event, String context) {
            System.out.println("在步骤1中");
        }
    }
}
