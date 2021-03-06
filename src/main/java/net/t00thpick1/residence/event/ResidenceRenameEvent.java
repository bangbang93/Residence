package net.t00thpick1.residence.event;

import net.t00thpick1.residence.protection.ClaimedResidence;

public class ResidenceRenameEvent extends ResidenceEvent {
    protected String NewResName;
    protected String OldResName;
    protected ClaimedResidence res;

    public ResidenceRenameEvent(ClaimedResidence resref, String NewName, String OldName) {
        super("RESIDENCE_RENAME", resref);
        NewResName = NewName;
        OldResName = OldName;
        res = resref;
    }

    public String getNewResidenceName() {
        return NewResName;
    }

    public String getOldResidenceName() {
        return OldResName;
    }

    public ClaimedResidence getResidence() {
        return res;
    }
}
