package indi.dirt;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.google.common.collect.Lists;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class EsmsTest {

    @Test
    void go() {
        String content = "ffff";
        List<String> mobiles = Lists.newArrayList("515325", "214124");
        
        Account ac = new Account("zzbjpc", "123456");
        PostMsg pm = new PostMsg();
        
        MTPack pack = new MTPack();
        pack.setBatchID(UUID.randomUUID());
        pack.setBatchName("广州市公安局e平台");
        pack.setMsgType(MTPack.MsgType.SMS);
        pack.setBizType(1);
        pack.setDistinctFlag(false);
        
        pack.setSendType(MTPack.SendType.MASS);
        String orgCode = "1234";
        
        int msgFmt = 246;
        List<MessageData> msgs = mobiles.stream()
                .map(mobile -> MessageData.getInstance(mobile, content, orgCode, msgFmt))
                .collect(Collectors.toList());
        pack.setMsgs(msgs);
        GsmsResponse resp = null;
        try {
            resp = pm.post(ac, pack);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println(resp);
    }
}
