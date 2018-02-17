/**
 * Copyright 2016-2017 The Reaktivity Project
 *
 * The Reaktivity Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.reaktivity.specification.http2.rfc7540;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.kaazing.k3po.junit.annotation.ScriptProperty;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;
import org.reaktivity.specification.nukleus.NukleusRule;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.rules.RuleChain.outerRule;

public class MessageFormatIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("spec", "org/reaktivity/specification/http2/rfc7540/message.format");

    private final TestRule timeout = new DisableOnDebug(new Timeout(10, SECONDS));

    private final NukleusRule nukleus = new NukleusRule().directory("target/nukleus-itests");

    @Rule
    public final TestRule chain = outerRule(nukleus).around(k3po).around(timeout);

    @Test
    @ScriptProperty("serverTransport \"nukleus://http2/streams/source\"")
    @Specification({
            "${spec}/continuation.frames/client",
            "${spec}/continuation.frames/server",
    })
    public void continuationFrames() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://http2/streams/source\"")
    @Specification({
            "${spec}/dynamic.table.requests/client",
            "${spec}/dynamic.table.requests/server",
    })
    public void dynamicTableRequests() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://http2/streams/source\"")
    @Specification({
            "${spec}/max.frame.size/client",
            "${spec}/max.frame.size/server",
    })
    public void maxFrameSize() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://http2/streams/source\"")
    @Specification({
            "${spec}/connection.headers/client",
            "${spec}/connection.headers/server",
    })
    public void connectionHeaders() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @ScriptProperty("serverTransport \"nukleus://http2/streams/source\"")
    @Specification({
            "${spec}/stream.id.order/client",
            "${spec}/stream.id.order/server",
    })
    public void streamIdOrder() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

}
