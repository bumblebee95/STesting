package de.hsrm.aufgabe2;

import java.util.EnumSet;

public enum Status {
    OFFEN, IN_ARBEIT, BEENDET;

    private static final EnumSet<Status> inArbeitTo = EnumSet.of(BEENDET);
    private static final EnumSet<Status> offenTo = EnumSet.of(IN_ARBEIT);

    public boolean canSwitchTo(Status st){
        switch (this) {
            case OFFEN:
                return this.offenTo.contains(st);
            case IN_ARBEIT:
                return this.inArbeitTo.contains(st);
        }
        return false;
    }
}
