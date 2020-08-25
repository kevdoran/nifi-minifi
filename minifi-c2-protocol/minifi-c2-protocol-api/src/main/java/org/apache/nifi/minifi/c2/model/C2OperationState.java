/**
 * (c) 2018-2019 Cloudera, Inc. All rights reserved.
 * <p>
 * This code is provided to you pursuant to your written agreement with Cloudera, which may be the terms of the
 * Affero General Public License version 3 (AGPLv3), or pursuant to a written agreement with a third party authorized
 * to distribute this code.  If you do not have a written agreement with Cloudera or with an authorized and
 * properly licensed third party, you do not have any rights to this code.
 * <p>
 * If this code is provided to you under the terms of the AGPLv3:
 * (A) CLOUDERA PROVIDES THIS CODE TO YOU WITHOUT WARRANTIES OF ANY KIND;
 * (B) CLOUDERA DISCLAIMS ANY AND ALL EXPRESS AND IMPLIED WARRANTIES WITH RESPECT TO THIS CODE, INCLUDING BUT NOT
 * LIMITED TO IMPLIED WARRANTIES OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE;
 * (C) CLOUDERA IS NOT LIABLE TO YOU, AND WILL NOT DEFEND, INDEMNIFY, OR HOLD YOU HARMLESS FOR ANY CLAIMS ARISING
 * FROM OR RELATED TO THE CODE; AND
 * (D) WITH RESPECT TO YOUR EXERCISE OF ANY RIGHTS GRANTED TO YOU FOR THE CODE, CLOUDERA IS NOT LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, PUNITIVE OR CONSEQUENTIAL DAMAGES INCLUDING, BUT NOT LIMITED
 * TO, DAMAGES RELATED TO LOST REVENUE, LOST PROFITS, LOSS OF INCOME, LOSS OF BUSINESS ADVANTAGE OR
 * UNAVAILABILITY, OR LOSS OR CORRUPTION OF DATA.
 */
package org.apache.nifi.minifi.c2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Simple model of operations. The current approach is to capture a shared state ( via agent(s)
 * and C2 server. This operation ID correspond with an operation requested of an agent. The OperationState
 * is the state of the operation at response time. The phrase 'state' is intentional to imply that
 * the state of the operation is transient and can potentially be different across attempts.
 *
 * This may suggest that a retry interval/attempt be added. Additionally, the details may provide user
 * feedback; however, it may be useful to separate failures into pre and post conditions. Details may provide
 * some insight, but a pre-condition and post-condition failure may better indicate how to arrive at operational
 * success.
 */
@ApiModel
public class C2OperationState {

    @ApiModelProperty(value = "State of the operation performed", required=true, example = "FULLY_APPLIED")
    private OperationState state;

    @ApiModelProperty(
            value = "Additional details about the state",
            example="Operation failed due to missing processor(s)")
    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(final String details){
        this.details = details;
    }

    public OperationState getState() {
        return state;
    }

    public void setState(final OperationState state){
        this.state=state;
    }

    /**
     * Sets the operation state via the ordinal value.
     * @param state ordinal value of this operation state.
     */
    public void setStateFromOrdinal(int state) {
        this.state = C2OperationState.OperationState.fromOrdinal(state);
    }


    public enum OperationState {
        FULLY_APPLIED,
        PARTIALLY_APPLIED,
        OPERATION_NOT_UNDERSTOOD,
        NOT_APPLIED;

        /**
         * We cannot rely on ordering within the ordinal set to translate
         * we must use a translation method as we're interconnecting between
         * different languages.
         *
         * @param state input ordinal
         * @return update state enumeration value.
         */
        public static OperationState fromOrdinal(int state) {
            switch (state) {
                case 0:
                    return FULLY_APPLIED;
                case 1:
                    return PARTIALLY_APPLIED;
                case 2:
                    return OPERATION_NOT_UNDERSTOOD;
                case 3:
                default:
                    return NOT_APPLIED;
            }
        }

        /**
         * We cannot rely on ordering within the ordinal set to translate
         * we must use a translation method as we're interconnecting between
         * different languages ( for binary protocols ).
         *
         * @param state enumeration value
         * @return predefined ordinal
         */
        public static int toOrdinal(OperationState state) {
            switch (state) {
                case FULLY_APPLIED:
                    return 0;
                case PARTIALLY_APPLIED:
                    return 1;
                case OPERATION_NOT_UNDERSTOOD:
                    return 2;
                case NOT_APPLIED:
                default:
                    return 3;
            }
        }
    }

}
