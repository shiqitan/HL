/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;


/**
 *
 * @author Administrator
 */
public class Bed {

    private String bedId; 
    private String wardId; 
    private String availabilityStatus;

    public Bed(String dedId, String wardId, String availabilityStatus) {
        this.bedId = dedId;
        this.wardId = wardId;
        this.availabilityStatus = availabilityStatus;
    }

    public String getBedId() {
        return bedId;
    }

    public String getWardId() {
        return wardId;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setBedId(String dedId) {
        this.bedId = dedId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

}
