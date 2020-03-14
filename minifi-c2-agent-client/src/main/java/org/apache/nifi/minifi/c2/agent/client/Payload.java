package org.apache.nifi.minifi.c2.agent.client;

import org.apache.nifi.minifi.c2.model.AgentInfo;
import org.apache.nifi.minifi.c2.model.C2Heartbeat;
import org.apache.nifi.minifi.c2.model.DeviceInfo;
import org.apache.nifi.minifi.c2.model.FlowInfo;

public class Payload {

    private String operation;
    private AgentInfo agentInfo;
    private DeviceInfo deviceInfo;
    private FlowInfo flowInfo;

    public Payload(C2Heartbeat heartbeat) {
        this.operation = "heartbeat";
        if (heartbeat != null) {
            this.agentInfo = heartbeat.getAgentInfo();
            this.deviceInfo = heartbeat.getDeviceInfo();
            this.flowInfo = heartbeat.getFlowInfo();
        }
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public AgentInfo getAgentInfo() {
        return agentInfo;
    }

    public void setAgentInfo(AgentInfo agentInfo) {
        this.agentInfo = agentInfo;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public FlowInfo getFlowInfo() {
        return flowInfo;
    }

    public void setFlowInfo(FlowInfo flowInfo) {
        this.flowInfo = flowInfo;
    }
}
