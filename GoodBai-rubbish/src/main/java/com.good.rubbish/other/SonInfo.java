package com.good.rubbish.other;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mg on 2018/3/27.
 */
public class SonInfo implements Serializable {
    private static final long serialVersionUID = 5251770110226697653L;

    private  List<Son> sonList;

    public List<Son> getSonList() {
        return sonList;
    }

    public void setSonList(List<Son> sonList) {
        this.sonList = sonList;
    }
}
