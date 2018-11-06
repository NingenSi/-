package dao;

import entity.Airinfo;

import java.util.List;

public interface AirinfoDao {
    List<Airinfo> queryAirinfo(int status, String param);

    boolean delAirinfo(int id);

    boolean modifyAirinfo(Airinfo airinfo);

    boolean addAirinfo(Airinfo airinfo);
}
