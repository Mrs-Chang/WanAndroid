package com.chang.android.module_network.config

import okhttp3.EventListener

class NetworkConfig  internal constructor(builder: Builder = Builder()) {
    var maxIdleConnections = builder.maxIdleConnections
    var connectTimeoutMs = builder.connectTimeoutMs
    var readTimeoutMs = builder.readTimeoutMs
    var writeTimeoutMs = builder.writeTimeoutMs
    var maxRequests = builder.maxRequests
    var maxRequestsPerHost = builder.maxRequestsPerHost
    var enableHttp2 = builder.enableHttp2
    var pingIntervalMs = builder.pingIntervalMs
    var eventListenerFactory: EventListener.Factory? = builder.eventListenerFactory


    class Builder {
        internal var maxIdleConnections = 5
        internal var connectTimeoutMs = 10000L
        internal var readTimeoutMs = 10000L
        internal var writeTimeoutMs = 10000L
        internal var maxRequests = 64
        internal var maxRequestsPerHost = 5
        internal var enableHttp2 = true
        internal var pingIntervalMs = 2000L
        internal var eventListenerFactory: EventListener.Factory? = null

        fun maxIdleConnections(maxIdleConnections: Int) = apply {
            this.maxIdleConnections = maxIdleConnections
        }

        fun connectTimeoutMs(connectTimeoutMs: Long)= apply {
            this.connectTimeoutMs = connectTimeoutMs
        }

        fun readTimeoutMs(readTimeoutMs: Long)= apply {
            this.readTimeoutMs = readTimeoutMs
            return this
        }

        fun writeTimeoutMs(writeTimeoutMs: Long)= apply {
            this.writeTimeoutMs = writeTimeoutMs
            return this
        }

        fun maxRequests(maxRequests: Int)= apply {
            this.maxRequests = maxRequests
        }

        fun maxRequestsPerHost(maxRequestsPerHost: Int)= apply {
            this.maxRequestsPerHost = maxRequestsPerHost
            return this
        }

        fun enableHttp2(enableHttp2: Boolean)= apply {
            this.enableHttp2 = enableHttp2
        }

        fun pingIntervalMs(pingIntervalMs: Long)= apply {
            this.pingIntervalMs = pingIntervalMs
        }

        fun eventListenerFactory(eventListenerFactory: EventListener.Factory)= apply{
            this.eventListenerFactory = eventListenerFactory
        }

        fun build(): NetworkConfig {
            return NetworkConfig(this)
        }
    }
}