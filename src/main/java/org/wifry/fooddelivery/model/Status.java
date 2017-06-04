package org.wifry.fooddelivery.model;

import org.wifry.fooddelivery.enumeration.GenericEnumUserType;
import org.wifry.fooddelivery.enumeration.PersistentEnum;
import org.wifry.fooddelivery.util.Utils;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public enum Status implements PersistentEnum {

    NUEVO(99), INACTIVO(0), ACTIVO(1), ELIMINADO(-1), ANULADO(-2);

    private Integer value;
    private ResourceBundle msg = Utils.RESOURCEBUNDLE;
    private static Map<Integer, Status> map = new HashMap<>();

    static {
        for (Status status : Status.values()) {
            map.put(status.value, status);
        }
    }

    public static Status valueOf(Object estado) {
        return map.get(Integer.valueOf(estado.toString()));
    }


    private Status(Integer value) {
        this.value = value;
    }

    public static Status[] valuesForm() {
        return (Status[]) EnumSet.of(ACTIVO, INACTIVO).toArray(new Status[0]);
    }

    public static Status[] valuesFormDocument() {
        return (Status[]) EnumSet.of(ACTIVO, ANULADO).toArray(new Status[0]);
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean isActivo() {
        return this == ACTIVO || this == NUEVO ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean isAnulado() {
        return this == ANULADO ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean isEliminado() {
        return this == ELIMINADO ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean isNuevo() {
        return this == NUEVO ? Boolean.TRUE : Boolean.FALSE;
    }

    public Status isEActivo() {
        return this == ACTIVO ? INACTIVO : ACTIVO;
    }

    public String toString() {
        return this.msg.getString(name());
    }

    public static class StatusConverter extends GenericEnumUserType<Status> {
        public static final String NAME = "org.wifry.fooddelivery.model.Status$StatusConverter";
    }
}
