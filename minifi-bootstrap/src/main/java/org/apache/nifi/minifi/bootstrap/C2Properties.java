package org.apache.nifi.minifi.bootstrap;

import java.util.Properties;

public class C2Properties {

    public static final String NIFI_PREFIX = "nifi.";

    public static final String C2_ENABLE_KEY = NIFI_PREFIX + "c2.enable";
    public static final String C2_AGENT_PROTOCOL_KEY = NIFI_PREFIX + "c2.agent.protocol.class";
    public static final String C2_COAP_HOST_KEY = NIFI_PREFIX + "c2.agent.coap.host";
    public static final String C2_COAP_PORT_KEY = NIFI_PREFIX + "c2.agent.coap.port";
    public static final String C2_REST_URL_KEY = NIFI_PREFIX + "c2.rest.url";
    public static final String C2_REST_URL_ACK_KEY = NIFI_PREFIX + "c2.rest.url.ack";
    public static final String C2_ROOT_CLASSES_KEY = NIFI_PREFIX + "c2.root.classes";
    public static final String C2_AGENT_HEARTBEAT_PERIOD_KEY = NIFI_PREFIX + "c2.agent.heartbeat.period";
    public static final String C2_AGENT_CLASS_KEY = NIFI_PREFIX + "c2.agent.class";
    public static final String C2_AGENT_IDENTIFIER_KEY = NIFI_PREFIX + "c2.agent.identifier";

    public static final String C2_ROOT_CLASS_DEFINITIONS_KEY = NIFI_PREFIX + "c2.root.class.definitions";
    public static final String C2_METRICS_NAME_KEY = C2_ROOT_CLASS_DEFINITIONS_KEY + ".metrics.name";
    public static final String C2_METRICS_METRICS_KEY = C2_ROOT_CLASS_DEFINITIONS_KEY + ".metrics.metrics";
    public static final String C2_METRICS_METRICS_TYPED_METRICS_NAME_KEY = C2_ROOT_CLASS_DEFINITIONS_KEY + ".metrics.metrics.typedmetrics.name";
    public static final String C2_METRICS_METRICS_QUEUED_METRICS_NAME_KEY = C2_ROOT_CLASS_DEFINITIONS_KEY + ".metrics.metrics.queuemetrics.name";
    public static final String C2_METRICS_METRICS_QUEUE_METRICS_CLASSES_KEY = C2_ROOT_CLASS_DEFINITIONS_KEY + ".metrics.metrics.queuemetrics.classes";
    public static final String C2_METRICS_METRICS_TYPED_METRICS_CLASSES_KEY = C2_ROOT_CLASS_DEFINITIONS_KEY + ".metrics.metrics.typedmetrics.classes";
    public static final String C2_METRICS_METRICS_PROCESSOR_METRICS_NAME_KEY = C2_ROOT_CLASS_DEFINITIONS_KEY + ".metrics.metrics.processorMetrics.name";
    public static final String C2_METRICS_METRICS_PROCESSOR_METRICS_CLASSES_KEY = C2_ROOT_CLASS_DEFINITIONS_KEY + ".metrics.metrics.processorMetrics.classes";

    private final Properties properties;

    public C2Properties() {
        this(null);
    }

    public C2Properties(Properties properties) {
        this.properties = properties == null ? new Properties() : properties;
    }

    String getProperty(String key) {
        // Prefer NIFI_PREFIX properties
        String value = null;
        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        }
        final String c2PrefixedKey = key.replaceFirst(NIFI_PREFIX, "");
        if (properties.containsKey(c2PrefixedKey)) {
            value = properties.getProperty(c2PrefixedKey);
            System.out.println(String.format("Using property %s with value %s. Please note that all c2.* prefixed configuration properties are deprecated and should be migrated to nifi.c2.*", c2PrefixedKey, value));
        }
        return value;
    }


    public boolean isEnabled() {
        return Boolean.parseBoolean(getProperty(C2_ENABLE_KEY));
    }

    // Agent configuration

    public String getAgentClass() {
        return getProperty(C2_AGENT_CLASS_KEY);
    }

    public String getAgentIdentifier() {
        return getProperty(C2_AGENT_IDENTIFIER_KEY);
    }

    // REST based protocol

    public String getRestUrl() {
        return getProperty(C2_REST_URL_KEY);
    }

    public String getRestAckUrl() {
        return getProperty(C2_REST_URL_ACK_KEY);
    }

}
