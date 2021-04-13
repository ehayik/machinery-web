package com.github.eljaiek.machinery.web.autoconfigure;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnJava.Range.OLDER_THAN;
import static org.springframework.boot.system.JavaVersion.NINE;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.eclipsecollections.EclipseCollectionsModule;
import com.fasterxml.jackson.datatype.hppc.HppcModule;
import com.fasterxml.jackson.datatype.jodamoney.JodaMoneyModule;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings({"unused", "java:S1118"})
class JacksonAddonsConfiguration {

    /**
     * Enables dynamic bytecode generation for standard Jackson POJO serializers and deserializers.
     *
     * <p>When using automatic data-binding Jackson offers, there is some amount of overhead
     * compared to manually writing equivalent code that would use Jackson streaming/incremental
     * parser and generator.
     *
     * <p>Byte code is generated to:
     *
     * <ol>
     *   <li>Replace <code>Class.newInstance()</code> calls with equivalent call to zero-argument
     *       constructor (currently same is not done for multi-argument Creator methods).
     *   <li>Replace Reflection-based field access (<code>Field.set()</code> / <code>Field.get()
     *       </code>) with equivalent field dereferencing.
     *   <li>Replace Reflection-based method calls (<code>Method.invoke(...)</code>) with equivalent
     *       direct calls.
     *   <li>For small subset of simple types (<code>int</code>, <code>long</code>, <code>String
     *       </code>, <code>boolean</code>), further streamline handling of
     *       serializers/deserializers to avoid auto-boxing.
     * </ol>
     *
     * <p>It is worth noting that there are certain limitations to access: for example, unlike with
     * Reflection, it is not possible to avoid visibility checks; which means that access to private
     * fields and methods must still be done using Reflection.
     */
    @Configuration
    @ConditionalOnProperty(
            prefix = "machinery.web.jackson",
            name = "performance-mode",
            matchIfMissing = true,
            havingValue = "true")
    static class EnablePerformanceMode {

        /**
         * Supports dynamic bytecode generation for applications running on JRE 8.
         *
         * <p>The Afterburner has long been the engine of choice for maximum Jackson performance.
         * But in the brave new Java 11 world, the trusty Afterburner is showing its age. It uses
         * horrifying bytecode manipulation and cracks <code>Unsafe.defineClass</code> which will
         * stop working soon.
         *
         * <p>There is, however, a potential future replacement available (as of Jackson 2.12):
         * Blackbird module.
         *
         * @return module to be registered
         */
        @Bean
        @ConditionalOnJava(range = OLDER_THAN, value = NINE)
        Module afterburnerModule() {
            return new AfterburnerModule();
        }
    }

    @Configuration
    static class EnableMiscModulesAutoRegistration {

        /**
         * Supports JSON serialization and deserialization of "org.json" JSON library datatypes.
         *
         * <p>https://github.com/FasterXML/jackson-datatypes-misc/tree/master/json-org
         *
         * @return module to be registered
         */
        @Bean
        @ConditionalOnClass(name = "com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule")
        Module jsonObjectModule() {
            return new JsonOrgModule();
        }

        /**
         * Supports JSON serialization and deserialization of High-Performance Primitive Collections
         * datatypes.
         *
         * <p>https://github.com/FasterXML/jackson-datatypes-collections/tree/master/hppc
         *
         * @return module to be registered
         */
        @Bean
        @ConditionalOnClass(name = "com.fasterxml.jackson.datatype.hppc.HppcModule")
        Module hppcModule() {
            return new HppcModule();
        }

        /**
         * Supports JSON serialization and deserialization of Eclipse Collections datatypes.
         *
         * @return module to be registered
         */
        @Bean
        @ConditionalOnClass(
                name = "com.fasterxml.jackson.datatype.eclipsecollections.EclipseCollectionsModule")
        Module eclipseCollectionsModule() {
            return new EclipseCollectionsModule();
        }

        /**
         * Supports JSON serialization and deserialization of Joda Money datatypes.
         *
         * @return module to be registered
         */
        @Bean
        @ConditionalOnClass(name = "com.fasterxml.jackson.datatype.jodamoney.JodaMoneyModule")
        Module jodaMoneyModule() {
            return new JodaMoneyModule();
        }
    }
}
