
package org.wifry.fooddelivery.model;

import org.wifry.fooddelivery.util.Utils;

import java.util.ResourceBundle;

/**
 * @author wtuco
 */
public enum PaymentStatus {

    PROCESSED, PENDING, CANCELLED, FAILED, CHARGEBACK;

    private ResourceBundle msg = Utils.RESOURCEBUNDLE;

    public String toString() {
        return this.msg.getString(name());
    }
}
